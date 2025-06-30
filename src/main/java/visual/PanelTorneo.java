package visual;

import javax.swing.*;
import java.awt.*;

public class PanelTorneo extends JPanel {
    private String lugarTorneo;
    private String tipoTiempo;
    public PanelTorneo(String lugar, String tiempo) {
        this.lugarTorneo = lugar;
        this.tipoTiempo = tiempo;
        setBackground(Color.WHITE);
        setLayout(new BorderLayout());
        JPanel panelSuperior = new JPanel(new GridLayout(1, 2));
        panelSuperior.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        panelSuperior.setOpaque(false);
        JLabel Lugar = new JLabel("Lugar del torneo: " + lugarTorneo);
        Lugar.setFont(new Font("Monospaced", Font.PLAIN, 16));
        panelSuperior.add(Lugar);
        JLabel Tipo = new JLabel("Tipo de torneo: " + tipoTiempo, SwingConstants.RIGHT);
        Tipo.setFont(new Font("Monospaced", Font.PLAIN, 16));
        panelSuperior.add(Tipo);
        add(panelSuperior, BorderLayout.NORTH);
        JPanel panelJuego = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                Graphics2D g2 = (Graphics2D) g;
                g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

                for (int i = 0; i < 3; i++) {
                    int mesaX = getWidth()/4 + i*(getWidth()/4);
                    int mesaY = getHeight()/2;

                    g2.setColor(new Color(139, 69, 19));
                    g2.fillRect(mesaX - 50, mesaY - 25, 100, 50);


                    g2.setColor(Color.BLUE);
                    g2.fillOval(mesaX - 100, mesaY - 40, 30, 30);
                    int[] xPointsLeft = {mesaX - 85, mesaX - 100, mesaX - 70};
                    int[] yPointsLeft = {mesaY - 10, mesaY + 20, mesaY + 20};
                    g2.fillPolygon(xPointsLeft, yPointsLeft, 3);

                    g2.setColor(Color.RED);
                    g2.fillOval(mesaX + 70, mesaY - 40, 30, 30);
                    int[] xPointsRight = {mesaX + 85, mesaX + 70, mesaX + 100};
                    int[] yPointsRight = {mesaY - 10, mesaY + 20, mesaY + 20};
                    g2.fillPolygon(xPointsRight, yPointsRight, 3);
                }
            }
        };
        panelJuego.setOpaque(false);
        add(panelJuego, BorderLayout.CENTER);

        JButton btnEstado = new JButton("Ver estado del torneo");
        btnEstado.setFont(new Font("Monospaced", Font.BOLD, 16));
        btnEstado.setBackground(new Color(70, 150, 220));
        btnEstado.setForeground(Color.WHITE);
        btnEstado.setBorder(BorderFactory.createEmptyBorder(10, 0, 10, 0));
        btnEstado.setFocusPainted(false);

        JPanel panelBoton = new JPanel();
        panelBoton.setBorder(BorderFactory.createEmptyBorder(20, 100, 20, 100));
        panelBoton.setOpaque(false);
        panelBoton.add(btnEstado);
        add(panelBoton, BorderLayout.SOUTH);
    }
}