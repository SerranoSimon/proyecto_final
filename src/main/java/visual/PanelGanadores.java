package visual;

import Logica.Participante;
import javax.swing.*;
import java.awt.*;

public class PanelGanadores extends JPanel {
    private JButton btnCerrar;

    public PanelGanadores(Participante primerLugar, Participante segundoLugar, Participante tercerLugar) {
        setLayout(new BorderLayout());
        setBackground(new Color(240, 240, 245));
        setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        JLabel titulo = new JLabel("Ganadores del Torneo", SwingConstants.CENTER);
        titulo.setFont(new Font("Arial", Font.BOLD, 24));
        titulo.setForeground(new Color(50, 50, 150));
        titulo.setBorder(BorderFactory.createEmptyBorder(0, 0, 20, 0));
        add(titulo, BorderLayout.NORTH);

        JPanel panelGanadores = new JPanel();
        panelGanadores.setLayout(new BoxLayout(panelGanadores, BoxLayout.Y_AXIS));
        panelGanadores.setOpaque(false);
        panelGanadores.setAlignmentX(Component.CENTER_ALIGNMENT);

        if (primerLugar != null) {
            panelGanadores.add(crearPanelGanador(primerLugar, "1° Lugar", new Color(255, 215, 0)));
            panelGanadores.add(Box.createVerticalStrut(20));
        }

        if (segundoLugar != null) {
            panelGanadores.add(crearPanelGanador(segundoLugar, "2° Lugar", new Color(192, 192, 192)));
            panelGanadores.add(Box.createVerticalStrut(20));
        }

        if (tercerLugar != null) {
            panelGanadores.add(crearPanelGanador(tercerLugar, "3° Lugar", new Color(205, 127, 50)));
        }

        JPanel contenedorCentral = new JPanel();
        contenedorCentral.setLayout(new BoxLayout(contenedorCentral, BoxLayout.Y_AXIS));
        contenedorCentral.setOpaque(false);
        contenedorCentral.add(Box.createVerticalGlue());
        contenedorCentral.add(panelGanadores);
        contenedorCentral.add(Box.createVerticalGlue());

        add(contenedorCentral, BorderLayout.CENTER);

        btnCerrar = new JButton("Cerrar");
        btnCerrar.setFont(new Font("Arial", Font.BOLD, 16));
        btnCerrar.setBackground(new Color(220, 80, 60));
        btnCerrar.setForeground(Color.WHITE);
        btnCerrar.setFocusPainted(false);
        btnCerrar.setPreferredSize(new Dimension(120, 40));
        btnCerrar.addActionListener(e -> System.exit(0));
        JPanel panelBoton = new JPanel();
        panelBoton.setOpaque(false);
        panelBoton.add(btnCerrar);

        add(panelBoton, BorderLayout.SOUTH);
    }

    private JPanel crearPanelGanador(Participante participante, String posicion, Color color) {
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(color.darker(), 2),
                BorderFactory.createEmptyBorder(15, 15, 15, 15)
        ));
        panel.setBackground(color.brighter());
        panel.setAlignmentX(Component.CENTER_ALIGNMENT);

        JLabel lblMedalla = new JLabel(posicion);
        lblMedalla.setFont(new Font("Arial", Font.BOLD, 20));
        lblMedalla.setForeground(color.darker());
        lblMedalla.setAlignmentX(Component.CENTER_ALIGNMENT);
        panel.add(lblMedalla);

        panel.add(Box.createVerticalStrut(10));

        JLabel lblInfo = new JLabel(participante.toString());
        lblInfo.setFont(new Font("Arial", Font.BOLD, 18));
        lblInfo.setAlignmentX(Component.CENTER_ALIGNMENT);
        panel.add(lblInfo);

        panel.add(Box.createVerticalStrut(5));

        JLabel lblPuntos = new JLabel("Puntos: " + participante.getPuntos());
        lblPuntos.setFont(new Font("Arial", Font.PLAIN, 16));
        lblPuntos.setAlignmentX(Component.CENTER_ALIGNMENT);
        panel.add(lblPuntos);

        return panel;
    }

    public JButton getCerrarButton() {
        return btnCerrar;
    }
}