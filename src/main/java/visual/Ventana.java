package visual;

import javax.swing.*;
import java.awt.*;

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

        panelMenu.setAlignmentX(0.5f);
        panelMenu.setAlignmentY(0.5f);
        panelTipoTorneo.setAlignmentX(0.5f);
        panelTipoTorneo.setAlignmentY(0.5f);
        panelDatosTorneo.setAlignmentX(0.5f);
        panelDatosTorneo.setAlignmentY(0.5f);
        panelInscripciones.setAlignmentX(0.5f);
        panelInscripciones.setAlignmentY(0.5f);
        panelTorneo.setAlignmentX(0.5f);
        panelTorneo.setAlignmentY(0.5f);

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

    public PanelMenu getPanelMenu() {
        return panelMenu;
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