package Logica;

/**
 * Exception que se lanza cuando se quiere ejecutar una ronda sin haber ordenado los participantes
 * para enfrentarse previamente.
 *
 */
public class OrdenarEnfrentamientosNoEjecutadoException extends Exception {
    public OrdenarEnfrentamientosNoEjecutadoException(String message) {
        super(message);
    }
}
