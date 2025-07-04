package visual;
import javax.swing.*;
import java.awt.*;

/**
 * Panel que se hace visible cuando el usuario escoge un tipo de torneo,
 * representa una interfaz visual que permite al usuario escribir texto correspondiente a los datos del torneo.
 * Incluye botones que representan el tiempo de cada partida en un torneo.
 * Incluye un botonPublicar que hace visible el PanelInscripciones.
 */
public class PanelDatosTorneo extends JPanel {
    private JToggleButton botonSeleccionado;

    public PanelDatosTorneo(Ventana ventana, String tipoTorneo) {

        setLayout(new BorderLayout());
        setBackground(new Color(30, 30, 40));

        JLabel tituloTorneo = new JLabel(tipoTorneo, SwingConstants.CENTER);
        tituloTorneo.setFont(new Font("Monospaced", Font.BOLD, 30));
        tituloTorneo.setForeground(new Color(220, 220, 255));
        tituloTorneo.setBorder(BorderFactory.createEmptyBorder(50, 0, 30, 0));
        add(tituloTorneo, BorderLayout.NORTH);

        JPanel panelCentral = new JPanel();
        panelCentral.setLayout(new BoxLayout(panelCentral, BoxLayout.Y_AXIS));
        panelCentral.setOpaque(false);
        panelCentral.setBorder(BorderFactory.createEmptyBorder(0, 100, 0, 100));

        JLabel escogerTiempo = new JLabel("Escoge el tiempo de las partidas", SwingConstants.CENTER);
        escogerTiempo.setFont(new Font("Monospaced", Font.PLAIN, 20));
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

        JPanel panelFecha = panelEntrada("Ingrese fecha y hora:");
        JTextField textoFecha = new JTextField(20);
        textoFecha.setMaximumSize(new Dimension(400, 30));
        textoFecha.setFont(new Font("Monospaced", Font.PLAIN, 14));
        textoFecha.setAlignmentX(Component.CENTER_ALIGNMENT);
        panelFecha.add(textoFecha);
        panelCentral.add(panelFecha);
        panelCentral.add(Box.createRigidArea(new Dimension(0, 20)));

        JPanel panelLugar = panelEntrada("Ingrese lugar del torneo:");
        JTextField textoLugar = new JTextField(20);
        textoLugar.setMaximumSize(new Dimension(400, 30));
        textoLugar.setFont(new Font("Monospaced", Font.PLAIN, 14));
        textoLugar.setAlignmentX(Component.CENTER_ALIGNMENT);
        panelLugar.add(textoLugar);
        panelCentral.add(panelLugar);

        JButton btnPublicar = getJButton(ventana,textoLugar);
        btnPublicar.setMaximumSize(new Dimension(300, 50));
        btnPublicar.addActionListener(e -> {
            ventana.getPanelInscripciones().setVisible(true);
            setVisible(false);
        });
        panelCentral.add(btnPublicar);
        panelCentral.add(Box.createVerticalGlue());
        add(panelCentral, BorderLayout.CENTER);
    }

    /**
     * metodo que se utiliza para hacer visible la información escogida por el usuario en PanelDatosTorneo hacia PanelTorneo
     * @param ventana ventana de la aplicación, necesaria por sus metodos getter.
     * @param textoLugar texto que corresponde al lugar donde se hará el torneo.
     * @return retorna su mismo btnPublicar.
     */
    private JButton getJButton(Ventana ventana, JTextField textoLugar) {
        JButton btnPublicar = new JButton("Publicar y recibir inscripciones");
        btnPublicar.setFont(new Font("Monospaced", Font.BOLD, 16));
        btnPublicar.setBackground(new Color(70, 150, 220));
        btnPublicar.setForeground(Color.WHITE);
        btnPublicar.setAlignmentX(Component.CENTER_ALIGNMENT);
        btnPublicar.setMaximumSize(new Dimension(300, 50));
        btnPublicar.setBorder(BorderFactory.createEmptyBorder(10, 0, 10, 0));
        btnPublicar.setFocusPainted(false);

        btnPublicar.addActionListener(e -> {
            String tiempo = botonSeleccionado != null ? botonSeleccionado.getText() : "";
            ventana.getPanelTorneo().setVisible(false);
            ventana.getPanelTorneo().removeAll();
            ventana.getPanelTorneo().add(new PanelTorneo(textoLugar.getText(), tiempo));
            ventana.getPanelTorneo().revalidate();
            ventana.getPanelTorneo().repaint();
            setVisible(false);
            ventana.getPanelInscripciones().setVisible(true);
        });
        return btnPublicar;
    }

    /**
     * metodo para establecer una estética a los botones de tiempo, utiliza paintComponent y hace que cuando uno de ellos sea seleccionado, cambie su color.
     * @param texto texto de los botones.
     * @param color color de los botones.
     * @return retorna el boton.
     */
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
        boton.setFont(new Font("Monospaced", Font.BOLD, 14));
        boton.setCursor(new Cursor(Cursor.HAND_CURSOR));

        boton.addActionListener(e -> {
            botonSeleccionado = boton;
            repaint();
        });

        return boton;
    }

    /**
     * metodo para crear un panel donde el usuario puede escribir texto.
     * @param texto texto indicativo para el usuario.
     * @return retorna este panel.
     */
    private JPanel panelEntrada(String texto) {
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.setOpaque(false);
        panel.setAlignmentX(Component.CENTER_ALIGNMENT);
        JLabel txt = new JLabel(texto);
        txt.setFont(new Font("Monospaced", Font.PLAIN, 16));
        txt.setForeground(Color.WHITE);
        txt.setAlignmentX(Component.CENTER_ALIGNMENT);
        txt.setBorder(BorderFactory.createEmptyBorder(0, 0, 5, 0));
        panel.add(txt);
        return panel;
    }
}