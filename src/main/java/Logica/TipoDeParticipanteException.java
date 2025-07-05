package Logica;

/**
 * Exception que se lanza cuando se quiere añadir participantes de distinto tipo a un torneo
 */
public class TipoDeParticipanteException extends RuntimeException {
    public TipoDeParticipanteException(String message) {
        super(message);
    }
}
