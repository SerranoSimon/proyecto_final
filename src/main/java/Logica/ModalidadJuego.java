package Logica;

import java.util.ArrayList;

public interface ModalidadJuego {
    void ordenarParticipantes(ArrayList<Participante> participantes,
                                int numeroDeRonda);
    ArrayList<ArrayList<Participante>> obtenerDistribucionEnfrentamientos(ArrayList<Participante> participantes);
    int numeroDeRondas(int numeroDeParticipantes);
   
}
