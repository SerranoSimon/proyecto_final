package Logica;

import java.util.ArrayList;

public class TorneoIndividual extends Torneo {
    public TorneoIndividual(ModalidadJuego modalidadJuego,TipoDePartida partidaNormal, TipoDePartida partidaDesempate){
        super(modalidadJuego,partidaNormal,partidaDesempate);


    }
    @Override
    public void agregarParticipante(Participante participante) {
        if (participante instanceof Jugador) {
            participantes.add(participante);
        } else {
            System.out.println("El participante debe ser un jugador.");
        }
    }

}
