package visual;

import javax.swing.*;
import java.awt.*;

public class Ventana extends JFrame {
    private JPanel mainPanel;
    private PanelMenu panelMenu;
    private PanelTipoTorneo panelTipoTorneo;
    private PanelDatosTorneo panelDatosTorneo;
    private PanelInscripciones panelInscripciones;
    private PanelTorneo panelTorneo;

    private DatosTorneo datosTorneo;

    public Ventana() {
        this.datosTorneo = new DatosTorneo();
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setSize(1920, 1080);
        this.setResizable(false);
        this.setTitle("Gestor de Torneos de Ajedrez");

        mainPanel = new JPanel();
        mainPanel.setLayout(new OverlayLayout(mainPanel));
        mainPanel.setBackground(new Color(30, 30, 40));

        panelMenu = new PanelMenu(this);
        panelTipoTorneo = new PanelTipoTorneo(this, datosTorneo);
        panelDatosTorneo = new PanelDatosTorneo(this, datosTorneo);
        panelInscripciones = new PanelInscripciones(this);

        mainPanel.add(panelInscripciones);
        mainPanel.add(panelDatosTorneo);
        mainPanel.add(panelTipoTorneo);
        mainPanel.add(panelMenu);

        panelTipoTorneo.setVisible(false);
        panelDatosTorneo.setVisible(false);
        panelInscripciones.setVisible(false);

        this.add(mainPanel);
        this.setVisible(true);
    }
    public PanelTipoTorneo getPanelTipoTorneo() {
        return panelTipoTorneo;
    }
    public PanelTorneo getPanelTorneo() {
        return panelTorneo;
    }
    public PanelDatosTorneo getPanelDatosTorneo() {
        return panelDatosTorneo;
    }

    public PanelInscripciones getPanelInscripciones() {
        return panelInscripciones;
    }

    public DatosTorneo getDatosTorneo() {
        return datosTorneo;
    }
    public void crearPanelTorneo() {
        panelTorneo = new PanelTorneo(this);
        mainPanel.add(panelTorneo);
    }
}