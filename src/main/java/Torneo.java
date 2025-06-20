import java.util.ArrayList;

public abstract class  Torneo {
    protected ArrayList<Participante> participantes;
    protected ModalidadJuego modalidadJuego;
    protected PartidaGenericaFactory partidaFactory;

    public void setModalidadJuego(ModalidadJuego modalidadJuego) {
        this.modalidadJuego = modalidadJuego;
    }
    public void setPartidaFactory(PartidaGenericaFactory partidaFactory){
        this.partidaFactory =partidaFactory;
    }
    public abstract void agregarParticipante(Participante participante);
}
