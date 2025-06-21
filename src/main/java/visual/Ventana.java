package visual;

import javax.swing.*;
import java.awt.*;

public class Ventana extends JFrame {
    private PanelMenu panelMenu;
    private PanelTipoTorneo panelTipoTorneo;

    public Ventana() {
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setSize(1920, 1080);
        this.setResizable(false);
        this.setTitle("Gestor de Torneos de Ajedrez");


        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new OverlayLayout(mainPanel));
        mainPanel.setBackground(new Color(30, 30, 40));


        panelMenu = new PanelMenu(this);
        panelTipoTorneo = new PanelTipoTorneo(this);


        panelMenu.setAlignmentX(0.5f);
        panelMenu.setAlignmentY(0.5f);
        panelTipoTorneo.setAlignmentX(0.5f);
        panelTipoTorneo.setAlignmentY(0.5f);


        mainPanel.add(panelTipoTorneo);
        mainPanel.add(panelMenu);


        panelTipoTorneo.setVisible(false);

        this.add(mainPanel);
        this.setVisible(true);
    }


    public PanelMenu getPanelMenu() {
        return panelMenu;
    }

    public PanelTipoTorneo getPanelTipoTorneo() {
        return panelTipoTorneo;
    }
}