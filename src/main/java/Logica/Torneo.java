package Logica;

import java.util.ArrayList;

public abstract class  Torneo {
    protected ArrayList<Participante> participantes;
    protected ModalidadJuego modalidadJuego;
    protected TipoDePartida partidaNormal;
    protected TipoDePartida partidaDesempate;
    protected Participante primerLugar;
    protected Participante segundoLugar;
    protected Participante tercerLugar;



    public void setModalidadJuego(ModalidadJuego modalidadJuego) {
        this.modalidadJuego = modalidadJuego;
    }

    public void setPartidaNormal(TipoDePartida partidaNormal) {
        this.partidaNormal = partidaNormal;
    }

    public void setPartidaDesempate(TipoDePartida partidaDesempate) {
        this.partidaDesempate = partidaDesempate;
    }

    public abstract void agregarParticipante(Participante participante);
    public void iniciar(){
        modalidadJuego.ejuctarRondas(participantes);
    }

}
