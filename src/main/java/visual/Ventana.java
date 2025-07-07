package visual;

import javax.swing.*;
import java.awt.*;

public class Ventana extends JFrame {
    private PanelMenu panelMenu;
    private PanelTipoTorneo panelTipoTorneo;
    private PanelDatosTorneo panelDatosTorneo;
    private PanelInscripciones panelInscripciones;
    private PanelIniciarTorneo panelIniciarTorneo;
    private PanelTorneo panelTorneo;
    private DatosTorneo datosTorneo;

    public Ventana() {
        this.datosTorneo = new DatosTorneo();
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setSize(1280, 720);
        this.setResizable(false);
        this.setTitle("Gestor de Torneos de Ajedrez");

        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new OverlayLayout(mainPanel));
        mainPanel.setBackground(new Color(30, 30, 40));

        panelMenu = new PanelMenu(this);
        panelTipoTorneo = new PanelTipoTorneo(this, datosTorneo);
        panelDatosTorneo = new PanelDatosTorneo(this, datosTorneo);
        panelInscripciones = new PanelInscripciones(this, datosTorneo);
        panelIniciarTorneo = new PanelIniciarTorneo(this);
        panelTorneo = new PanelTorneo(datosTorneo);

        mainPanel.add(panelTorneo);
        mainPanel.add(panelIniciarTorneo);
        mainPanel.add(panelInscripciones);
        mainPanel.add(panelDatosTorneo);
        mainPanel.add(panelTipoTorneo);
        mainPanel.add(panelMenu);

        panelTipoTorneo.setVisible(false);
        panelDatosTorneo.setVisible(false);
        panelInscripciones.setVisible(false);
        panelIniciarTorneo.setVisible(false);
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
    public PanelIniciarTorneo getPanelIniciarTorneo() {
        return panelIniciarTorneo;
    }
    public DatosTorneo getDatosTorneo() {
        return datosTorneo;
    }
}