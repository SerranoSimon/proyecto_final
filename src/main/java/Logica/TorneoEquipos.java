package Logica;

import java.util.ArrayList;

public class TorneoEquipos extends Torneo {

    public TorneoEquipos(ModalidadJuego modalidadJuego,TipoDePartida partidaNormal, TipoDePartida partidaDesempate) {
        super(modalidadJuego,partidaNormal,partidaDesempate);

    }
    @Override
    public void agregarParticipante(Participante participante) {
        if (participante instanceof Equipo) {
            participantes.add(participante);
        } else {
            System.out.println("El participante debe ser un equipo.");
        }
    }

}


