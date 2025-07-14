package Logica;

/**
 * Exception que se lanza cuando se quiere avanzar con el torneo cuando las rondas base acaban
 */
public class LimiteDeRondasSuperadoException extends RuntimeException {
    public LimiteDeRondasSuperadoException(String message) {
        super(message);
    }
}
