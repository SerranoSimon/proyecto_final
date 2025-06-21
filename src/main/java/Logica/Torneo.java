package Logica;

import java.util.ArrayList;

public abstract class  Torneo {
    protected ArrayList<Participante> participantes;
    protected ModalidadJuego modalidadJuego;

    public void setModalidadJuego(ModalidadJuego modalidadJuego) {
        this.modalidadJuego = modalidadJuego;
    }
    public abstract void agregarParticipante(Participante participante);
    public void iniciar(){
        modalidadJuego.ejuctarRondas(participantes);
    }
}
