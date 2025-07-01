package visual;

import javax.swing.*;
import java.awt.*;

public class PanelTorneo extends JPanel {
    private String lugarTorneo;
    private String tipoTiempo;
    public PanelTorneo(String lugar, String tiempo) {
        this.lugarTorneo = lugar;
        this.tipoTiempo = tiempo;
        setBackground(Color.LIGHT_GRAY);
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

                g2.setColor(Color.white);
                g2.fillRect(0, 0, getWidth(), getHeight());

                for (int i = 0; i < 3; i++) {
                    int mesaX = getWidth()/4 + i*(getWidth()/4);
                    int mesaY = getHeight()/2 - 30;

                    g2.setColor(new Color(139, 69, 19));
                    g2.fillRoundRect(mesaX - 60, mesaY - 10, 120, 70, 15, 15);

                    g2.setColor(new Color(222, 184, 135));
                    g2.fillRect(mesaX - 40, mesaY, 80, 40);
                    g2.setColor(Color.BLACK);
                    g2.drawRect(mesaX - 40, mesaY, 80, 40);

                    g2.setColor(new Color(101, 67, 33));
                    g2.fillRect(mesaX - 30, mesaY + 10, 20, 20);
                    g2.fillRect(mesaX + 10, mesaY + 10, 20, 20);

                    g2.setColor(new Color(30, 144, 255));

                    g2.fillOval(mesaX - 100, mesaY - 30, 25, 25);

                    int[] xPointsLeft = {mesaX - 88, mesaX - 100, mesaX - 76};
                    int[] yPointsLeft = {mesaY - 5, mesaY + 20, mesaY + 20};
                    g2.fillPolygon(xPointsLeft, yPointsLeft, 3);

                    g2.drawLine(mesaX - 88, mesaY + 5, mesaX - 78, mesaY);

                    g2.setColor(new Color(220, 20, 60));

                    g2.fillOval(mesaX + 75, mesaY - 30, 25, 25);

                    int[] xPointsRight = {mesaX + 87, mesaX + 75, mesaX + 99};
                    int[] yPointsRight = {mesaY - 5, mesaY + 20, mesaY + 20};
                    g2.fillPolygon(xPointsRight, yPointsRight, 3);

                    g2.drawLine(mesaX + 75, mesaY + 5, mesaX + 65, mesaY);

                    g2.setColor(new Color(160, 82, 45));
                    g2.fillRoundRect(mesaX - 110, mesaY + 15, 20, 10, 5, 5);
                    g2.fillRoundRect(mesaX + 90, mesaY + 15, 20, 10, 5, 5);
                }

                g2.setColor(new Color(200, 200, 200, 100));
                for (int i = 0; i < 3; i++) {
                    int mesaX = getWidth()/4 + i*(getWidth()/4);
                    g2.fillOval(mesaX - 50, getHeight()/2 + 40, 100, 20);
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