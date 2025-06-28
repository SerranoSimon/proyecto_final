package visual;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class PanelTipoTorneo extends JPanel {
    public PanelTipoTorneo(Ventana ventana) {
        setLayout(new BorderLayout());
        setBackground(new Color(30, 30, 40));

        JLabel titulo = new JLabel("Escoger tipo de torneo:", SwingConstants.CENTER);
        titulo.setFont(new Font("Arial", Font.BOLD, 30));
        titulo.setForeground(new Color(220, 220, 255));
        titulo.setBorder(BorderFactory.createEmptyBorder(50, 0, 50, 0));
        add(titulo, BorderLayout.NORTH);

        JPanel panelBotones = new JPanel();
        panelBotones.setLayout(new BoxLayout(panelBotones, BoxLayout.Y_AXIS));
        panelBotones.setOpaque(false);
        panelBotones.setAlignmentX(Component.CENTER_ALIGNMENT);

        JButton btnSuizoIndividual = crearBoton("Sistema Suizo individual", new Color(70, 150, 220));
        JButton btnSuizoEquipos = crearBoton("Sistema Suizo en equipos", new Color(200, 60, 60));
        JButton btnElimDirecta = crearBoton("Eliminación Directa", new Color(70, 150, 220));
        JButton btnTodosIndividual = crearBoton("Todos contra todos Individual", new Color(70, 150, 220));
        JButton btnTodosEquipos = crearBoton("Todos contra todos en equipos", new Color(200, 60, 60));

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
        btnElimDirecta.addActionListener(tipoTorneoListener);
        btnTodosIndividual.addActionListener(tipoTorneoListener);
        btnTodosEquipos.addActionListener(tipoTorneoListener);

        panelBotones.add(Box.createVerticalGlue());
        panelBotones.add(btnSuizoIndividual);
        panelBotones.add(Box.createRigidArea(new Dimension(0, 20)));
        panelBotones.add(btnSuizoEquipos);
        panelBotones.add(Box.createRigidArea(new Dimension(0, 20)));
        panelBotones.add(btnElimDirecta);
        panelBotones.add(Box.createRigidArea(new Dimension(0, 20)));
        panelBotones.add(btnTodosIndividual);
        panelBotones.add(Box.createRigidArea(new Dimension(0, 20)));
        panelBotones.add(btnTodosEquipos);
        panelBotones.add(Box.createVerticalGlue());

        add(panelBotones, BorderLayout.CENTER);


        JButton btnRegresar = new JButton("Regresar");
        btnRegresar.addActionListener(e -> {
            setVisible(false);
            ventana.getPanelMenu().setVisible(true);
        });
        btnRegresar.setBackground(new Color(60, 60, 70));
        btnRegresar.setForeground(Color.WHITE);
        btnRegresar.setFont(new Font("Arial", Font.PLAIN, 12));
        btnRegresar.setBorder(BorderFactory.createEmptyBorder(5, 15, 5, 15));
        btnRegresar.setFocusPainted(false);
        btnRegresar.setCursor(new Cursor(Cursor.HAND_CURSOR));

        JPanel panelInferior = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        panelInferior.setOpaque(false);
        panelInferior.setBorder(BorderFactory.createEmptyBorder(0, 0, 20, 20));
        panelInferior.add(btnRegresar);
        add(panelInferior, BorderLayout.SOUTH);
    }

    private JButton crearBoton(String texto, Color color) {
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
        boton.setFont(new Font("Arial", Font.BOLD, 14));
        boton.setCursor(new Cursor(Cursor.HAND_CURSOR));
        boton.setMaximumSize(new Dimension(300, 50));
        boton.setAlignmentX(Component.CENTER_ALIGNMENT);
        return boton;
    }

}