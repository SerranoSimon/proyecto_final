package Logica;

/**
 * Exception que se lanza cuando se añaden más participantes del limite permitido.
 */
public class LimitesDeParticipantesException extends RuntimeException {
    public LimitesDeParticipantesException(String message) {
        super(message);
    }
}
