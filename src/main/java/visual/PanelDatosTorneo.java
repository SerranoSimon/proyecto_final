package visual;
import javax.swing.*;
import java.awt.*;

public class PanelDatosTorneo extends JPanel {
    public PanelDatosTorneo(Ventana ventana, String tipoTorneo) {
        setLayout(new BorderLayout());
        setBackground(new Color(30, 30, 40));
        JLabel tituloTorneo = new JLabel(tipoTorneo, SwingConstants.CENTER);
        tituloTorneo.setFont(new Font("Arial", Font.BOLD, 30));
        tituloTorneo.setForeground(new Color(220, 220, 255));
        tituloTorneo.setBorder(BorderFactory.createEmptyBorder(50, 0, 30, 0));
        add(tituloTorneo, BorderLayout.NORTH);
        JPanel panelCentral = new JPanel();
        panelCentral.setLayout(new BoxLayout(panelCentral, BoxLayout.Y_AXIS));
        panelCentral.setOpaque(false);
        panelCentral.setBorder(BorderFactory.createEmptyBorder(0, 100, 0, 100));
        JLabel escogerTiempo = new JLabel("Escoge el tiempo de las partidas", SwingConstants.CENTER);
        escogerTiempo.setFont(new Font("Arial", Font.PLAIN, 20));
        escogerTiempo.setForeground(Color.WHITE);
        escogerTiempo.setAlignmentX(Component.CENTER_ALIGNMENT);
        escogerTiempo.setBorder(BorderFactory.createEmptyBorder(0, 0, 20, 0));
        panelCentral.add(escogerTiempo);
        JPanel panelTiempo = new JPanel();
        panelTiempo.setLayout(new BoxLayout(panelTiempo, BoxLayout.X_AXIS));
        panelTiempo.setOpaque(false);
        panelTiempo.setAlignmentX(Component.CENTER_ALIGNMENT);
        JToggleButton botonRapido = botonTiempo("Rápido", new Color(60, 180, 75));
        JToggleButton botonBlitz = botonTiempo("Blitz", new Color(220, 180, 40));
        JToggleButton botonClasico = botonTiempo("Clásico", new Color(180, 120, 70));
        ButtonGroup grupo = new ButtonGroup();
        grupo.add(botonRapido);
        grupo.add(botonBlitz);
        grupo.add(botonClasico);
        panelTiempo.add(botonRapido);
        panelTiempo.add(Box.createRigidArea(new Dimension(20, 0)));
        panelTiempo.add(botonBlitz);
        panelTiempo.add(Box.createRigidArea(new Dimension(20, 0)));
        panelTiempo.add(botonClasico);
        panelCentral.add(panelTiempo);
        panelCentral.add(Box.createRigidArea(new Dimension(0, 40)));
        JPanel panelFecha = panelEntrada("Ingrese fecha y hora en UTC:");
        JTextField textoFecha = new JTextField(20);
        textoFecha.setMaximumSize(new Dimension(400, 30));
        textoFecha.setFont(new Font("Arial", Font.PLAIN, 14));
        textoFecha.setAlignmentX(Component.CENTER_ALIGNMENT);
        panelFecha.add(textoFecha);
        panelCentral.add(panelFecha);
        panelCentral.add(Box.createRigidArea(new Dimension(0, 20)));
        JPanel panelLugar = panelEntrada("Ingrese lugar del torneo:");
        JTextField textoLugar = new JTextField(20);
        textoLugar.setMaximumSize(new Dimension(400, 30));
        textoLugar.setFont(new Font("Arial", Font.PLAIN, 14));
        textoLugar.setAlignmentX(Component.CENTER_ALIGNMENT);
        panelLugar.add(textoLugar);
        panelCentral.add(panelLugar);
        panelCentral.add(Box.createVerticalGlue());
        add(panelCentral, BorderLayout.CENTER);
    }
    private JToggleButton botonTiempo(String texto, Color color) {
        JToggleButton boton = new JToggleButton(texto) {
            @Override
            protected void paintComponent(Graphics g) {
                Graphics2D g2 = (Graphics2D) g.create();
                g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

                if (isSelected()) {
                    g2.setColor(color.brighter());
                } else {
                    g2.setColor(color);
                }
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
        boton.setPreferredSize(new Dimension(120, 40));
        boton.setContentAreaFilled(false);
        boton.setBorderPainted(false);
        boton.setFocusPainted(false);
        boton.setFont(new Font("Arial", Font.BOLD, 14));
        boton.setCursor(new Cursor(Cursor.HAND_CURSOR));
        return boton;
    }
    private JPanel panelEntrada(String texto) {
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.setOpaque(false);
        panel.setAlignmentX(Component.CENTER_ALIGNMENT);
        JLabel txt = new JLabel(texto);
        txt.setFont(new Font("Arial", Font.PLAIN, 16));
        txt.setForeground(Color.WHITE);
        txt.setAlignmentX(Component.CENTER_ALIGNMENT);
        txt.setBorder(BorderFactory.createEmptyBorder(0, 0, 5, 0));
        panel.add(txt);
        return panel;
    }
}