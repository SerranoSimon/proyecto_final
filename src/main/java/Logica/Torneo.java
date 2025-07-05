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
    protected ArrayList<Participante> disputaTercerLugar;
    protected Participante primerLugar;
    protected Participante segundoLugar;
    protected Participante tercerLugar;
    protected boolean ordenado;
   public Torneo(ModalidadJuego modalidadJuego,TipoDePartida partidaNormal, TipoDePartida partidaDesempate){
       this.solicitudesInscripcion=new ArrayList<>();
       this.participantes=new ArrayList<>();
       this.distribucion=new ArrayList<>();
       this.modalidadJuego=modalidadJuego;
       this.partidaNormal=partidaNormal;
       this.partidaDesempate=partidaDesempate;
       this.factory = new EnfrentamientoFactory();
       this.numeroRonda=0;
       this.disputaTercerLugar=new ArrayList<>();
       this.ordenado=false;

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
    public void ordenarEnfrentamientos() throws LimiteDeRondasSuperadoException {
       if(numeroRonda==numeroMaximoRondas){
           throw new LimiteDeRondasSuperadoException("Las rondas han acabado");
       }
       if(!ordenado){
           ordenado=true;
           modalidadJuego.ordenarParticipantes(participantes,numeroRonda+1);
           distribucion=modalidadJuego.obtenerDistribucionEnfrentamientos(participantes);}
       else{
           throw new RuntimeException("ya ha sido ordenado");
       }
    }
    public abstract void agregarParticipante(Participante participante) throws LimiteDeRondasSuperadoException;
    public void ejecutarRonda() throws LimiteDeRondasSuperadoException, OrdenarEnfrentamientosNoEjecutadoException, LimitesDeParticipantesException{
        if(participantes.size()<3 && numeroRonda==0){
            throw new LimitesDeParticipantesException("El minimo de participantes para inicar son 3");
        }
        if(ordenado){
            ordenado=false;
        numeroRonda+=1;
        if(numeroRonda<=numeroMaximoRondas) {
            System.out.println("RONDA: " +
                    "" + numeroRonda);
            for (ArrayList<Participante> pareja : distribucion) {
                Enfrentamiento enf = factory.crearEnfrentamiento(pareja.getFirst(), pareja.getLast(), partidaNormal, partidaDesempate);
                enf.jugar();
                if (modalidadJuego instanceof EliminacionDirecta) {
                    if (enf.getResultado() == Resultado.VICTORIA_P1) {
                        participantes.remove(pareja.getLast());
                        if (numeroRonda == numeroMaximoRondas - 1) {
                            disputaTercerLugar.add(pareja.getLast());
                        }
                        if (numeroRonda == numeroMaximoRondas) {
                            primerLugar = pareja.getFirst();
                            segundoLugar = pareja.getLast();
                        }
                    } else {
                        participantes.remove(pareja.getFirst());
                        if (numeroRonda == numeroMaximoRondas - 1) {
                            disputaTercerLugar.add(pareja.getFirst());
                        }
                        if (numeroRonda == numeroMaximoRondas) {
                            primerLugar = pareja.getLast();
                            segundoLugar = pareja.getFirst();
                        }
                    }
                }
            }
            if (numeroRonda == numeroMaximoRondas && modalidadJuego instanceof EliminacionDirecta) {
                System.out.println("Disputa tercer lugar eliminacion directa");
                Enfrentamiento enf = factory.crearEnfrentamiento(disputaTercerLugar.getFirst(), disputaTercerLugar.getLast(), partidaNormal, partidaDesempate);
                enf.jugar();
                if (enf.getResultado() == Resultado.VICTORIA_P1) {
                    tercerLugar = disputaTercerLugar.getFirst();
                } else {
                    tercerLugar = disputaTercerLugar.getLast();
                }

            }
        }
        else{
            throw new LimiteDeRondasSuperadoException("Las rondas ya han acabado");
        }


        }
        else{
            throw new OrdenarEnfrentamientosNoEjecutadoException("No se han ordenado los enfrentamientos");
        }
    }
    public void verEstado(){
        modalidadJuego.ordenarParticipantesParaMostrar(this.participantes,this.numeroRonda);
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
    public abstract void desempatar() throws OrdenarEnfrentamientosNoEjecutadoException;
    public void establecerGanadores() throws OrdenarEnfrentamientosNoEjecutadoException {
        if(numeroRonda==numeroMaximoRondas){
            modalidadJuego.ordenarParticipantesParaMostrar(participantes,numeroRonda);
            if(!(modalidadJuego instanceof  EliminacionDirecta)) {
                if (!seNecesitaDesempate()) {
                    primerLugar = participantes.get(0);
                    segundoLugar = participantes.get(1);
                    tercerLugar = participantes.get(2);
                } else {
                    System.out.println("Existe al menos un empate, se necesita desempatar");
                    desempatar();
                    System.out.println("Se ha desempatado");
                }
            }


        }
        else{

            System.out.println("aun no acaba el torneo");
        }
    }
    public void mostrarGanadores(){
        System.out.println("PRIMER LUGAR "+primerLugar);
        System.out.println("PRIMER SEGUNDO "+segundoLugar);
        System.out.println("PRIMER TERCERO"+tercerLugar);



    }

}
