package Logica;

/**
 * Exception que se lanza cuando se quiere formar un equipo con un numero incorrecto de jugadores
 */
public class LimitesDeJugadoresPorEquipoException extends RuntimeException {
    public LimitesDeJugadoresPorEquipoException(String message) {
        super(message);
    }
}
