package visual;

import javax.swing.*;
import java.awt.*;

public class PanelInscripciones extends JPanel {

    public PanelInscripciones(Ventana ventana) {
        setLayout(new BorderLayout());
        setBackground(new Color(30, 30, 40));

        JLabel titulo = new JLabel("Aceptar Inscripciones", SwingConstants.CENTER);
        titulo.setFont(new Font("Arial", Font.BOLD, 30));
        titulo.setForeground(new Color(220, 220, 255));
        titulo.setBorder(BorderFactory.createEmptyBorder(50, 0, 50, 0));
        add(titulo, BorderLayout.NORTH);

        JPanel panelCentral = new JPanel();
        panelCentral.setLayout(new BoxLayout(panelCentral, BoxLayout.Y_AXIS));
        panelCentral.setOpaque(false);
        panelCentral.setBorder(BorderFactory.createEmptyBorder(0, 50, 0, 50));
        panelCentral.add(Box.createVerticalGlue());
        panelCentral.add(Box.createVerticalGlue());
        add(panelCentral, BorderLayout.CENTER);

        JButton btnContinuar = Boton("Continuar", new Color(70, 150, 220));
        btnContinuar.addActionListener(e -> {
            ventana.getPanelTorneo().setVisible(false);
            ventana.getPanelTorneo().removeAll();
            ventana.getPanelTorneo().add(new PanelTorneo(ventana.getDatosTorneo()));
            ventana.getPanelTorneo().revalidate();
            ventana.getPanelTorneo().repaint();
            ventana.getPanelIniciarTorneo().setVisible(true);
            setVisible(false);
        });

        JPanel panelBoton = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        panelBoton.setOpaque(false);
        panelBoton.setBorder(BorderFactory.createEmptyBorder(0, 0, 20, 20));
        panelBoton.add(btnContinuar);
        add(panelBoton, BorderLayout.SOUTH);
    }

    private JButton Boton(String texto, Color color) {
        JButton boton = new JButton(texto) {
            @Override
            protected void paintComponent(Graphics g) {
                Graphics2D g2 = (Graphics2D) g.create();
                g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                g2.setColor(color);
                g2.fillRoundRect(0, 0, getWidth(), getHeight(), 20, 20);
                g2.setColor(color.darker());
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
        boton.setFont(new Font("Arial", Font.BOLD, 16));
        boton.setCursor(new Cursor(Cursor.HAND_CURSOR));
        boton.setPreferredSize(new Dimension(180, 45));
        return boton;
    }
}