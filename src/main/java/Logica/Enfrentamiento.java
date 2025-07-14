package Logica;

/**
 * Interfaz que representa un enfrentamiento
 */
public interface Enfrentamiento {
    void jugar();
    Resultado getResultado();
    Participante getP1();
    Participante getP2();
    Participante getGanador();
    int getTiempoPartidasJugadas();
}
