package visual;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class PanelTipoTorneo extends JPanel {
    private JToggleButton botonTiempoPrincipal;
    private JToggleButton botonTiempoDesempate;
    private DatosTorneo datosTorneo;

    public PanelTipoTorneo(Ventana ventana, DatosTorneo datosTorneo) {
        this.datosTorneo = datosTorneo;
        setLayout(new BorderLayout());
        setBackground(new Color(30, 30, 40));

        JLabel titulo = new JLabel("Configuración del Torneo", SwingConstants.CENTER);
        titulo.setFont(new Font("Monospaced", Font.BOLD, 30));
        titulo.setForeground(new Color(220, 220, 255));
        titulo.setBorder(BorderFactory.createEmptyBorder(50, 0, 30, 0));
        add(titulo, BorderLayout.NORTH);

        JPanel panelCentral = new JPanel();
        panelCentral.setLayout(new BoxLayout(panelCentral, BoxLayout.Y_AXIS));
        panelCentral.setOpaque(false);
        panelCentral.setAlignmentX(Component.CENTER_ALIGNMENT);
        panelCentral.setBorder(BorderFactory.createEmptyBorder(0, 100, 0, 100));

        panelCentral.add(SeccionModalidad());
        panelCentral.add(Box.createRigidArea(new Dimension(0, 20)));

        panelCentral.add(SeccionParticipantes());
        panelCentral.add(Box.createRigidArea(new Dimension(0, 30)));

        panelCentral.add(SeccionTiempo("Tiempo principal:", true));
        panelCentral.add(Box.createRigidArea(new Dimension(0, 20)));

        panelCentral.add(SeccionTiempo("Tiempo para desempates:", false));

        JButton btnContinuar = BotonContinuar(ventana);
        panelCentral.add(Box.createRigidArea(new Dimension(0, 40)));
        panelCentral.add(btnContinuar);
        panelCentral.add(Box.createVerticalGlue());

        JPanel contenedorCentral = new JPanel();
        contenedorCentral.setLayout(new BoxLayout(contenedorCentral, BoxLayout.Y_AXIS));
        contenedorCentral.setOpaque(false);
        contenedorCentral.add(Box.createVerticalGlue());
        contenedorCentral.add(panelCentral);
        contenedorCentral.add(Box.createVerticalGlue());

        add(contenedorCentral, BorderLayout.CENTER);
    }

    private JPanel SeccionModalidad() {
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.setOpaque(false);
        panel.setAlignmentX(Component.CENTER_ALIGNMENT);

        JLabel label = new JLabel("Modalidad del torneo:", SwingConstants.CENTER);
        label.setFont(new Font("Monospaced", Font.PLAIN, 16));
        label.setForeground(Color.WHITE);
        label.setAlignmentX(Component.CENTER_ALIGNMENT);
        panel.add(label);

        JPanel panelBotones = new JPanel();
        panelBotones.setLayout(new BoxLayout(panelBotones, BoxLayout.X_AXIS));
        panelBotones.setOpaque(false);
        panelBotones.setAlignmentX(Component.CENTER_ALIGNMENT);

        JButton btnSuizo = BotonModalidad("Sistema Suizo", new Color(70, 150, 220));
        JButton btnEliminacion = BotonModalidad("Eliminación Directa", new Color(200, 60, 60));
        JButton btnTodos = BotonModalidad("Todos contra Todos", new Color(70, 150, 220));

        panelBotones.add(btnSuizo);
        panelBotones.add(Box.createRigidArea(new Dimension(20, 0)));
        panelBotones.add(btnEliminacion);
        panelBotones.add(Box.createRigidArea(new Dimension(20, 0)));
        panelBotones.add(btnTodos);

        panel.add(Box.createRigidArea(new Dimension(0, 10)));
        panel.add(panelBotones);
        return panel;
    }

    private JPanel SeccionParticipantes() {
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.setOpaque(false);
        panel.setAlignmentX(Component.CENTER_ALIGNMENT);

        JLabel label = new JLabel("Tipo de participantes:", SwingConstants.CENTER);
        label.setFont(new Font("Monospaced", Font.PLAIN, 16));
        label.setForeground(Color.WHITE);
        label.setAlignmentX(Component.CENTER_ALIGNMENT);
        panel.add(label);

        JPanel panelBotones = new JPanel();
        panelBotones.setLayout(new BoxLayout(panelBotones, BoxLayout.X_AXIS));
        panelBotones.setOpaque(false);
        panelBotones.setAlignmentX(Component.CENTER_ALIGNMENT);

        JToggleButton btnIndividual = BotonParticipantes("Individual", new Color(248, 0, 255));
        JToggleButton btnEquipos = BotonParticipantes("Equipos", new Color(180, 100, 220));

        ButtonGroup grupo = new ButtonGroup();
        grupo.add(btnIndividual);
        grupo.add(btnEquipos);

        panelBotones.add(btnIndividual);
        panelBotones.add(Box.createRigidArea(new Dimension(20, 0)));
        panelBotones.add(btnEquipos);

        panel.add(Box.createRigidArea(new Dimension(0, 10)));
        panel.add(panelBotones);
        return panel;
    }

    private JPanel SeccionTiempo(String titulo, boolean esPrincipal) {
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.setOpaque(false);
        panel.setAlignmentX(Component.CENTER_ALIGNMENT);

        JLabel label = new JLabel(titulo, SwingConstants.CENTER);
        label.setFont(new Font("Monospaced", Font.PLAIN, 16));
        label.setForeground(Color.WHITE);
        label.setAlignmentX(Component.CENTER_ALIGNMENT);
        panel.add(label);

        JPanel panelBotones = new JPanel();
        panelBotones.setLayout(new BoxLayout(panelBotones, BoxLayout.X_AXIS));
        panelBotones.setOpaque(false);
        panelBotones.setAlignmentX(Component.CENTER_ALIGNMENT);

        JToggleButton botonRapido = BotonTiempo("Rápido", new Color(60, 180, 75));
        JToggleButton botonBlitz = BotonTiempo("Blitz", new Color(220, 180, 40));
        JToggleButton botonClasico = BotonTiempo("Clásico", new Color(180, 120, 70));

        ButtonGroup grupo = new ButtonGroup();
        grupo.add(botonRapido);
        grupo.add(botonBlitz);
        grupo.add(botonClasico);

        panelBotones.add(botonRapido);
        panelBotones.add(Box.createRigidArea(new Dimension(20, 0)));
        panelBotones.add(botonBlitz);
        panelBotones.add(Box.createRigidArea(new Dimension(20, 0)));
        panelBotones.add(botonClasico);

        ActionListener listener = e -> {
            JToggleButton boton = (JToggleButton) e.getSource();
            if (esPrincipal) {
                botonTiempoPrincipal = boton;
                datosTorneo.setTorneoTiempo(boton.getText());
            } else {
                botonTiempoDesempate = boton;
                datosTorneo.setTorneoDesempate(boton.getText());
            }
        };

        botonRapido.addActionListener(listener);
        botonBlitz.addActionListener(listener);
        botonClasico.addActionListener(listener);

        panel.add(Box.createRigidArea(new Dimension(0, 10)));
        panel.add(panelBotones);
        return panel;
    }

    private JButton BotonModalidad(String texto, Color color) {
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
        boton.setPreferredSize(new Dimension(180, 40));
        boton.setAlignmentX(Component.CENTER_ALIGNMENT);

        boton.addActionListener(e -> {
            datosTorneo.setModalidadTorneo(texto);
        });

        return boton;
    }

    private JToggleButton BotonParticipantes(String texto, Color color) {
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
        boton.setAlignmentX(Component.CENTER_ALIGNMENT);

        boton.addActionListener(e -> {
            datosTorneo.setTipoParticipantes(texto);
        });
        return boton;
    }

    private JToggleButton BotonTiempo(String texto, Color color) {
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
        boton.setAlignmentX(Component.CENTER_ALIGNMENT);
        return boton;
    }

    private JButton BotonContinuar(Ventana ventana) {
        JButton btnContinuar = new JButton("Continuar");
        btnContinuar.setFont(new Font("Monospaced", Font.BOLD, 16));
        btnContinuar.setBackground(new Color(70, 150, 220));
        btnContinuar.setForeground(Color.WHITE);
        btnContinuar.setAlignmentX(Component.CENTER_ALIGNMENT);
        btnContinuar.setMaximumSize(new Dimension(300, 50));
        btnContinuar.setBorder(BorderFactory.createEmptyBorder(10, 0, 10, 0));
        btnContinuar.setFocusPainted(false);

        btnContinuar.addActionListener(e -> {
            if (validarSelecciones()) {
                ventana.getPanelDatosTorneo().setVisible(true);
                setVisible(false);
            } else {
                JOptionPane.showMessageDialog(this,
                        "Debes completar todas las selecciones",
                        "Datos incompletos", JOptionPane.WARNING_MESSAGE);
            }
        });

        return btnContinuar;
    }

    private boolean validarSelecciones() {
        return datosTorneo.getModalidadTorneo() != null &&
                datosTorneo.getTipoParticipantes() != null &&
                botonTiempoPrincipal != null &&
                botonTiempoDesempate != null;
    }
}