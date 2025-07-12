package visual;

/**
 * Interfaz de la aplicación, ejecutable que inicializa una nueva ventana y establece un título al programa.
 */
public class InterfazGUI {
    public static void main(String[] args) {
        Runtime.getRuntime().addShutdownHook(new Thread(PanelConsola::restaurarConsola));
        Ventana a = new Ventana();
        a.setTitle("Gestor de torneos");
    }
}
