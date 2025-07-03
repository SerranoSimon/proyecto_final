package Logica;

import java.util.ArrayList;

public abstract class  Torneo {
    protected ArrayList<Participante> participantes;
    protected ArrayList<ArrayList<Participante>> distribucion=new ArrayList<>();
    protected ArrayList<Participante> solicitudesInscripcion;
    protected int numeroRonda=1;
    protected int numeroMaximoRondas;
    protected EnfrentamientoFactory factory = new EnfrentamientoFactory();
    protected ModalidadJuego modalidadJuego;
    protected TipoDePartida partidaNormal;
    protected TipoDePartida partidaDesempate;

    protected Participante primerLugar;
    protected Participante segundoLugar;
    protected Participante tercerLugar;




    public void setModalidadJuego(ModalidadJuego modalidadJuego) {

        this.modalidadJuego = modalidadJuego;
        this.numeroMaximoRondas=modalidadJuego.numeroDeRondas(participantes.size());
    }

    public void setPartidaNormal(TipoDePartida partidaNormal) {
        this.partidaNormal = partidaNormal;
    }

    public void setPartidaDesempate(TipoDePartida partidaDesempate) {
        this.partidaDesempate = partidaDesempate;
    }

    public abstract void agregarParticipante(Participante participante);
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
    public void ordenarEnfrentamientos(){
        modalidadJuego.ordenarParticipantes(participantes,numeroRonda);
        distribucion=modalidadJuego.obtenerDistribucionEnfrentamientos(participantes);
    }
    public void ejecutarRonda() throws LimiteDeRondasSuperadoException{
        if(numeroRonda<=numeroMaximoRondas) {
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
            numeroRonda+=1;
        }
        else{
            throw new LimiteDeRondasSuperadoException("Las rondas ya han acabado");
        }
    }
    public void verEstado(){
        modalidadJuego.ordenarParticipantesParaMostrar(participantes,numeroRonda);
        System.out.println(participantes);
    }
    public void establecerGanadores(){
        if(numeroRonda==numeroMaximoRondas){
            modalidadJuego.ordenarParticipantesParaMostrar(participantes,numeroRonda);
            if(modalidadJuego instanceof EliminacionDirecta){
                primerLugar=participantes.get(0);
                System.out.println("GANADOR DEL TORNEO: "+primerLugar);
            }
            else{
                if((participantes.get(0).getPuntos()!=participantes.get(1).getPuntos())
                   && (participantes.get(1).getPuntos()!=participantes.get(2).getPuntos())           ){
                    primerLugar=participantes.get(0);
                    segundoLugar=participantes.get(1);
                    tercerLugar=participantes.get(2);
                }
                else{
                    System.out.println("Existe un empate, se necesita desempatar");
                }
            }
        }
        else{
            System.out.println("aun no acaba el torneo");
        }
    }

}
