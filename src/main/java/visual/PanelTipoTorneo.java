package visual;

import Logica.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.util.Objects;

/**
 * Panel que sirve para guardar la informacion acerca del torneo, cuenta con botones visibles e interactuables
 * para que el usuario pueda personalizar el torneo tomando el rol de un organizador.
 */
public class PanelTipoTorneo extends JPanel {
    private JToggleButton botonTiempoNormal;
    private JToggleButton botonTiempoDesempate;
    private DatosTorneo datosTorneo;
    private Image fondo;

    public PanelTipoTorneo(Ventana ventana) {
        this.datosTorneo = ventana.getDatosTorneo();
        this.botonTiempoNormal=new JToggleButton();
        this.botonTiempoDesempate=new JToggleButton();
        fondo = new ImageIcon(getClass().getResource("/AjedrezFondo.jpg")).getImage();
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

    /**
     * Panel donde se escoge la modalidad del torneo , sistema suizo, eliminacion directa o todos contra todos
     * @return retorna el panel
     */
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

        JToggleButton btnSuizo = BotonModalidad("Sistema Suizo", new Color(70, 150, 220));
        JToggleButton btnEliminacion = BotonModalidad("Eliminación Directa", new Color(200, 60, 60));
        JToggleButton btnTodos = BotonModalidad("Todos contra Todos", new Color(70, 150, 220));

        ButtonGroup grupoModalidad = new ButtonGroup();
        grupoModalidad.add(btnSuizo);
        grupoModalidad.add(btnEliminacion);
        grupoModalidad.add(btnTodos);

        panelBotones.add(btnSuizo);
        panelBotones.add(Box.createRigidArea(new Dimension(20, 0)));
        panelBotones.add(btnEliminacion);
        panelBotones.add(Box.createRigidArea(new Dimension(20, 0)));
        panelBotones.add(btnTodos);

        panel.add(Box.createRigidArea(new Dimension(0, 10)));
        panel.add(panelBotones);
        return panel;
    }

    /**
     * panel donde se escoge si los tipos de participantes son individuales o equipos
     * @return retorna el panel
     */
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

    /**
     * panel donde se escoge el tiempo para partidas normales y desempates del torneo
     * @param titulo titulo del panel
     * @param esPrincipal en caso de que sea principal o desempate
     * @return retorna el panel
     */
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
            String texto = boton.getText();

            if (esPrincipal) {
                switch (texto) {
                    case "Clásico" -> datosTorneo.setTorneoTiempoNormal(TipoDePartida.CLASICA);
                    case "Rápido" -> datosTorneo.setTorneoTiempoNormal(TipoDePartida.RAPIDA);
                    case "Blitz" -> datosTorneo.setTorneoTiempoNormal(TipoDePartida.BLITZ);
                }
            } else {
                switch (texto) {
                    case "Clásico" -> datosTorneo.setTorneoTiempoDesempate(TipoDePartida.CLASICA);
                    case "Rápido" -> datosTorneo.setTorneoTiempoDesempate(TipoDePartida.RAPIDA);
                    case "Blitz" -> datosTorneo.setTorneoTiempoDesempate(TipoDePartida.BLITZ);
                }
            }
        };

        botonRapido.addActionListener(listener);
        botonBlitz.addActionListener(listener);
        botonClasico.addActionListener(listener);

        panel.add(Box.createRigidArea(new Dimension(0, 10)));
        panel.add(panelBotones);
        return panel;
    }

    /**
     * metodo para configurar los botones de los tipos de juego.
     * @param texto texto del boton
     * @param color color del boton
     * @return retorna el boton
     */
    private JToggleButton BotonModalidad(String texto, Color color) {
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
        boton.setPreferredSize(new Dimension(180, 40));
        boton.setContentAreaFilled(false);
        boton.setBorderPainted(false);
        boton.setFocusPainted(false);
        boton.setFont(new Font("Monospaced", Font.BOLD, 14));
        boton.setCursor(new Cursor(Cursor.HAND_CURSOR));
        boton.setAlignmentX(Component.CENTER_ALIGNMENT);

        boton.addActionListener(e -> {
            if (Objects.equals(texto, "Sistema Suizo")) {
                datosTorneo.setModalidadTorneo(new SistemaSuizo());
            } else if (Objects.equals(texto, "Eliminación Directa")) {
                datosTorneo.setModalidadTorneo(new EliminacionDirecta());
            } else {
                datosTorneo.setModalidadTorneo(new TodosContraTodos());
            }
        });

        return boton;
    }

    /**
     * metodo para configurar el boton de los participantes
     * @param texto texto del boton
     * @param color color del boton
     * @return
     */
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

    /**
     * metodo para configurar los botones respectivos al tiempo
     * @param texto texto del boton
     * @param color color del boton
     * @return retorna el boton
     */
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

    /**
     * metodo para configurar el boton de continuar
     * @param ventana ventana donde vive el boton, necesaria para establecer visibilidad a otro panel
     * @return
     */
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
            }
        });

        return btnContinuar;
    }

    /**
     * Se valida si no se llena con la informacion
     * @return booleano que indica si se llenó correctamente
     */
    private boolean validarSelecciones() {
        if (datosTorneo.getModalidadTorneo() == null) {
            mostrarError("Debes seleccionar una modalidad del torneo.");
            return false;
        }

        if (datosTorneo.getTipoParticipantes() == null) {
            mostrarError("Debes seleccionar el tipo de participantes.");
            return false;
        }

        if (datosTorneo.getTorneoTiempoNormal() == null) {
            mostrarError("Debes seleccionar un tiempo principal para las partidas.");
            return false;
        }

        if (datosTorneo.getTiempoDesempate() == null) {
            mostrarError("Debes seleccionar un tiempo para los desempates.");
            return false;
        }

        return true;
    }

    /**
     * Muestra el dato que no se completó
     * @param mensaje
     */
    private void mostrarError(String mensaje) {
        JOptionPane.showMessageDialog(this, mensaje, "Datos incompletos", JOptionPane.WARNING_MESSAGE);
    }
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(fondo, 0, 0, getWidth(), getHeight(), this);

    }

}