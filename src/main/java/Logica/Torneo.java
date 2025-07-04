package Logica;

import java.util.ArrayList;

public abstract class  Torneo {
    protected ArrayList<Participante> participantes;
    protected ArrayList<Participante> solicitudesInscripcion;
    protected ArrayList<ArrayList<Participante>> distribucion;
    protected int numeroRonda;
    protected int numeroMaximoRondas;
    protected EnfrentamientoFactory factory;
    protected ModalidadJuego modalidadJuego;
    protected TipoDePartida partidaNormal;
    protected TipoDePartida partidaDesempate;

    protected Participante primerLugar;
    protected Participante segundoLugar;
    protected Participante tercerLugar;
   public Torneo(ModalidadJuego modalidadJuego,TipoDePartida partidaNormal, TipoDePartida partidaDesempate){
       this.solicitudesInscripcion=new ArrayList<>();
       this.participantes=new ArrayList<>();
       this.distribucion=new ArrayList<>();
       this.modalidadJuego=modalidadJuego;
       this.partidaNormal=partidaNormal;
       this.partidaDesempate=partidaDesempate;
       this.factory = new EnfrentamientoFactory();
       this.numeroRonda=0;
   }
    public void solicitarInscripcion(Participante participante) {
        solicitudesInscripcion.add(participante);
        System.out.println("Solicitud de inscripción recibida para: " + participante);
    }
    public void aceptarSolicitud(Participante participante) {
        solicitudesInscripcion.remove(participante);
        agregarParticipante(participante);
        System.out.println(participante + " ha sido aceptado en el torneo.");
    }
    public void rechazarSolicitud(Participante participante) {
        solicitudesInscripcion.remove(participante);
        System.out.println(participante + " ha sido rechazado.");
    }
    public void actualizarNumeroMaximoRondas(){
       numeroMaximoRondas=modalidadJuego.numeroDeRondas(participantes.size());
    }
    public void ordenarEnfrentamientos(){
        modalidadJuego.ordenarParticipantes(participantes,numeroRonda+1);
        distribucion=modalidadJuego.obtenerDistribucionEnfrentamientos(participantes);
    }
    public abstract void agregarParticipante(Participante participante);
    public void ejecutarRonda() throws LimiteDeRondasSuperadoException{
        numeroRonda+=1;
        if(numeroRonda<=numeroMaximoRondas) {
            System.out.println("RONDA: " +
                    ""+numeroRonda);
            for (ArrayList<Participante> pareja : distribucion) {
                Enfrentamiento enf = factory.crearEnfrentamiento(pareja.getFirst(), pareja.getLast(), partidaNormal, partidaDesempate);
                enf.jugar();
                if (modalidadJuego instanceof EliminacionDirecta) {
                    if (enf.getResultado() == Resultado.VICTORIA_P1) {
                        participantes.remove(pareja.getLast());
                    } else {
                        participantes.remove(pareja.getFirst());
                    }
                }
            }

        }
        else{
            throw new LimiteDeRondasSuperadoException("Las rondas ya han acabado");
        }
    }
    public void verEstado(){
        modalidadJuego.ordenarParticipantesParaMostrar(participantes,numeroRonda);
        System.out.println(participantes);
    }
    public boolean seNecesitaDesempate(){
        if((participantes.get(0).getPuntos()!=participantes.get(1).getPuntos())
                && (participantes.get(1).getPuntos()!=participantes.get(2).getPuntos())
                && (participantes.get(2).getPuntos())!=participantes.get(3).getPuntos()){
            return false;
        }
        return true;
    }
    public Torneo crearTorneoDesempate(boolean esIndividual, ArrayList<Participante> empatados){
        if(esIndividual){
            return new TorneoIndividual(new TodosContraTodos(),partidaNormal, partidaDesempate);
        }
        else{
            return new TorneoEquipos(new TodosContraTodos(), partidaNormal, partidaDesempate);
        }
    }
    public abstract void desempatar();
    public void establecerGanadores(){
        if(numeroRonda==numeroMaximoRondas){
            modalidadJuego.ordenarParticipantesParaMostrar(participantes,numeroRonda);
            if(modalidadJuego instanceof EliminacionDirecta){
                primerLugar=participantes.get(0);
                System.out.println("GANADOR DEL TORNEO: "+primerLugar);
            }
            else{
                if(!seNecesitaDesempate()){
                    primerLugar=participantes.get(0);
                    segundoLugar=participantes.get(1);
                    tercerLugar=participantes.get(2);
                }
                else{
                    System.out.println("Existe al menos un empate, se necesita desempatar");
                    desempatar();
                }
            }
        }
        else{

            System.out.println("aun no acaba el torneo");
        }
    }

}
