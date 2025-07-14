package visual;

/**
 * Exception utilizada en PanelDatosTorneo, sirve para evitar un torneo sin información.
 */
public class DatosInsuficientesException extends RuntimeException {
    public DatosInsuficientesException(String message) {
        super(message);
    }
}
