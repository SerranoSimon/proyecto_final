package Logica;

import java.util.ArrayList;

/**
 * interfaz que define los metodos de cualquier modalidad de juego
 */
public interface ModalidadJuego {
    void ordenarParticipantes(ArrayList<Participante> participantes,
                                int numeroDeRonda);
    ArrayList<ArrayList<Participante>> obtenerDistribucionEnfrentamientos(ArrayList<Participante> participantes);
    int numeroDeRondas(int numeroDeParticipantes);
    void ordenarParticipantesParaMostrar(ArrayList<Participante> participantes,
                                         int numeroDeRonda);
   
}
