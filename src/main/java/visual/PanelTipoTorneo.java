package visual;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

/**
 * Panel que se hace visible cuando el usuario decide crear un nuevo torneo.
 * Representa una interfaz visual que contiene 5 botones y el usuario debe hacer una selección.
 * Al seleccionar uno de ellos, esta información se pasa al panelDatosTorneo y obtiene de título el texto del boton escogido.
 */
public class PanelTipoTorneo extends JPanel {
    public PanelTipoTorneo(Ventana ventana) {
        setLayout(new BorderLayout());
        setBackground(new Color(30, 30, 40));

        JLabel titulo = new JLabel("Escoger tipo de torneo:", SwingConstants.CENTER);
        titulo.setFont(new Font("Monospaced", Font.BOLD, 30));
        titulo.setForeground(new Color(220, 220, 255));
        titulo.setBorder(BorderFactory.createEmptyBorder(50, 0, 50, 0));
        add(titulo, BorderLayout.NORTH);

        JPanel panelBotones = new JPanel();
        panelBotones.setLayout(new BoxLayout(panelBotones, BoxLayout.Y_AXIS));
        panelBotones.setOpaque(false);
        panelBotones.setAlignmentX(Component.CENTER_ALIGNMENT);

        JButton btnSuizoIndividual = BotonTipo("Sistema Suizo individual", new Color(70, 150, 220));
        JButton btnSuizoEquipos = BotonTipo("Sistema Suizo en equipos", new Color(200, 60, 60));
        JButton btnEliminacionDirecta = BotonTipo("Eliminación Directa", new Color(70, 150, 220));
        JButton btnTodosIndividual = BotonTipo("Todos contra todos Individual", new Color(70, 150, 220));
        JButton btnTodosEquipos = BotonTipo("Todos contra todos en equipos", new Color(200, 60, 60));

        ActionListener tipoTorneoListener = e -> {
            JButton boton = (JButton) e.getSource();
            String tipoTorneo = boton.getText();
            ventana.getPanelDatosTorneo().setVisible(false);
            ventana.getPanelDatosTorneo().removeAll();
            ventana.getPanelDatosTorneo().add(new PanelDatosTorneo(ventana, tipoTorneo));
            ventana.getPanelDatosTorneo().revalidate();
            ventana.getPanelDatosTorneo().repaint();
            setVisible(false);
            ventana.getPanelDatosTorneo().setVisible(true);
        };

        btnSuizoIndividual.addActionListener(tipoTorneoListener);
        btnSuizoEquipos.addActionListener(tipoTorneoListener);
        btnEliminacionDirecta.addActionListener(tipoTorneoListener);
        btnTodosIndividual.addActionListener(tipoTorneoListener);
        btnTodosEquipos.addActionListener(tipoTorneoListener);

        panelBotones.add(Box.createVerticalGlue());
        panelBotones.add(btnSuizoIndividual);
        panelBotones.add(Box.createRigidArea(new Dimension(0, 20)));
        panelBotones.add(btnSuizoEquipos);
        panelBotones.add(Box.createRigidArea(new Dimension(0, 20)));
        panelBotones.add(btnEliminacionDirecta);
        panelBotones.add(Box.createRigidArea(new Dimension(0, 20)));
        panelBotones.add(btnTodosIndividual);
        panelBotones.add(Box.createRigidArea(new Dimension(0, 20)));
        panelBotones.add(btnTodosEquipos);
        panelBotones.add(Box.createVerticalGlue());

        add(panelBotones, BorderLayout.CENTER);

    }

    /**
     * Metodo que usa paintComponent para asignar una estética más agradable a los botones de la interfaz.
     * @param texto texto del boton
     * @param color color del boton
     * @return retorna el boton
     */
    private JButton BotonTipo(String texto, Color color) {
        JButton boton = new JButton(texto) {
            @Override
            protected void paintComponent(Graphics g) {
                Graphics2D g2 = (Graphics2D) g.create();
                g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                g2.setColor(color);
                g2.fillRoundRect(0, 0, getWidth(), getHeight(), 20, 20);
                g2.drawRoundRect(0, 0, getWidth()-1, getHeight()-1, 20, 20);
                g2.setColor(Color.WHITE);
                FontMetrics fm = g2.getFontMetrics();
                int x = (getWidth() - fm.stringWidth(getText())) / 2;
                int y = ((getHeight() - fm.getHeight()) / 2) + fm.getAscent();
                g2.drawString(getText(), x, y);

            }
        };
        boton.setContentAreaFilled(false);
        boton.setBorderPainted(false);
        boton.setFocusPainted(false);
        boton.setFont(new Font("Monospaced", Font.BOLD, 14));
        boton.setCursor(new Cursor(Cursor.HAND_CURSOR));
        boton.setMaximumSize(new Dimension(300, 50));
        boton.setAlignmentX(Component.CENTER_ALIGNMENT);
        return boton;
    }

}