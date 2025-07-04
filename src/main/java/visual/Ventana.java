package visual;

import javax.swing.*;
import java.awt.*;

/**
 * ventana principal de la aplicación, aquí se establece la relación entre todos los paneles y sus métodos getter.
 * Todos los paneles existen al mismo tiempo, solo varian su visibilidad, lo que permite la modificacion de ellos mientras no son visibles por el usuario.
 */
public class Ventana extends JFrame {
    private PanelMenu panelMenu;
    private PanelTipoTorneo panelTipoTorneo;
    private PanelDatosTorneo panelDatosTorneo;
    private PanelInscripciones panelInscripciones;
    private PanelTorneo panelTorneo;

    public Ventana() {
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setSize(1280, 720);
        this.setResizable(false);
        this.setTitle("Gestor de Torneos de Ajedrez");

        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new OverlayLayout(mainPanel));
        mainPanel.setBackground(new Color(30, 30, 40));

        panelMenu = new PanelMenu(this);
        panelTipoTorneo = new PanelTipoTorneo(this);
        panelDatosTorneo = new PanelDatosTorneo(this, "");
        panelInscripciones = new PanelInscripciones(this);
        panelTorneo = new PanelTorneo("", "");

        mainPanel.add(panelTorneo);
        mainPanel.add(panelInscripciones);
        mainPanel.add(panelDatosTorneo);
        mainPanel.add(panelTipoTorneo);
        mainPanel.add(panelMenu);

        panelTipoTorneo.setVisible(false);
        panelDatosTorneo.setVisible(false);
        panelInscripciones.setVisible(false);
        panelTorneo.setVisible(false);

        this.add(mainPanel);
        this.setVisible(true);
    }
    public PanelTipoTorneo getPanelTipoTorneo() {
        return panelTipoTorneo;
    }
    public PanelDatosTorneo getPanelDatosTorneo() {
        return panelDatosTorneo;
    }
    public PanelTorneo getPanelTorneo() {
        return panelTorneo;
    }
    public PanelInscripciones getPanelInscripciones() {
        return panelInscripciones;
    }
}