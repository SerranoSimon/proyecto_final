package visual;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

/**
 * Clase que representa un Menú Principal para la aplicación, contiene:
 * - Título simple: "Gestor de Torneos de Ajedrez"
 * - Dos botones:
 *  . "Crear Torneo" Hace visible el PanelTipoTorneo e invisible el PanelMenu.
 *  . "Salir del programa" Cierra la aplicación.
 */

public class PanelMenu extends JPanel {
    private Image fondo;
    public PanelMenu(Ventana ventana) {
        fondo = new ImageIcon(getClass().getResource("/AjedrezFondo.jpg")).getImage();
        setLayout(new BorderLayout());
        setBackground(new Color(30, 30, 40));

        JLabel titulo = new JLabel("Gestor de Torneos de Ajedrez", SwingConstants.CENTER);
        titulo.setFont(new Font("Monospaced", Font.BOLD, 36));
        titulo.setForeground(new Color(220, 220, 255));
        titulo.setBorder(BorderFactory.createEmptyBorder(50, 0, 100, 0));
        titulo.setOpaque(false);
        add(titulo, BorderLayout.NORTH);

        JPanel panelBotones = new JPanel();
        panelBotones.setLayout(new BoxLayout(panelBotones, BoxLayout.Y_AXIS));
        panelBotones.setOpaque(false);
        panelBotones.setAlignmentX(Component.CENTER_ALIGNMENT);

        JButton btnCrear = crearBoton("Crear torneo", new Color(70, 150, 220));
        btnCrear.setMaximumSize(new Dimension(250, 60));
        btnCrear.setAlignmentX(Component.CENTER_ALIGNMENT);

        JButton btnSalir = crearBoton("X Salir del programa", new Color(200, 60, 60));
        btnSalir.setMaximumSize(new Dimension(150, 40));
        btnSalir.setAlignmentX(Component.CENTER_ALIGNMENT);
        btnSalir.addActionListener(e -> System.exit(0));


        btnCrear.addActionListener(e -> {
            setVisible(false);
            ventana.getPanelTipoTorneo().setVisible(true);
        });
        panelBotones.add(btnCrear);
        panelBotones.add(Box.createRigidArea(new Dimension(0, 20)));
        panelBotones.add(btnSalir);
        add(panelBotones, BorderLayout.CENTER);
    }

    /**
     * Metodo para crear los botones con una estética más amigable para el usuario usando paintComponent.
     * @param texto texto del boton.
     * @param colorFondo color del boton.
     * @return retorna el boton con la nueva estética.
     */
    private JButton crearBoton(String texto, Color colorFondo) {
        JButton boton = new JButton(texto) {
            @Override
            protected void paintComponent(Graphics g) {
                Graphics2D g2 = (Graphics2D) g.create();
                g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                g2.setColor(colorFondo);

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
        return boton;
    }
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
            g.drawImage(fondo, 0, 0, getWidth(), getHeight(), this);

    }
}