import java.util.ArrayList;

public abstract class  Torneo {
    protected ArrayList<Participante> participantes;
    protected ModalidadJuego modalidadJuego;
    protected PartidaFactory partidaFactory;

    public void setModalidadJuego(ModalidadJuego modalidadJuego) {
        this.modalidadJuego = modalidadJuego;
    }
    public void setPartidaFactory(PartidaFactory partidaFactory){
        this.partidaFactory =partidaFactory;
    }
    public abstract void agregarParticipante(Participante participante);
}
