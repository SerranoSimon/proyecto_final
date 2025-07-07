package visual;

import Logica.*;
import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class PanelInscripciones extends JPanel {
    private DatosTorneo datosTorneo;
    private JPanel panelParticipantes;

    public PanelInscripciones(Ventana ventana, DatosTorneo datosTorneo) {
        this.datosTorneo = datosTorneo;
        setLayout(new BorderLayout());
        setBackground(new Color(30, 30, 40));

        JLabel titulo = new JLabel("Aceptar Inscripciones", SwingConstants.CENTER);
        titulo.setFont(new Font("Arial", Font.BOLD, 30));
        titulo.setForeground(new Color(220, 220, 255));
        titulo.setBorder(BorderFactory.createEmptyBorder(50, 0, 30, 0));
        add(titulo, BorderLayout.NORTH);

        panelParticipantes = new JPanel();
        panelParticipantes.setLayout(new BoxLayout(panelParticipantes, BoxLayout.Y_AXIS));
        panelParticipantes.setOpaque(false);

        JScrollPane scrollPane = new JScrollPane(panelParticipantes);
        scrollPane.setBorder(null);
        scrollPane.setOpaque(false);
        scrollPane.getViewport().setOpaque(false);
        scrollPane.setPreferredSize(new Dimension(800, 400));
        add(scrollPane, BorderLayout.CENTER);

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

    public JPanel PanelParticipante(Participante participante) {
        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout(10, 0));
        panel.setBorder(BorderFactory.createEmptyBorder(5, 20, 5, 20));
        panel.setBackground(new Color(50, 50, 60));
        panel.setMaximumSize(new Dimension(700, 60));


        String info;
        if (participante instanceof Jugador) {
            Jugador j = (Jugador) participante;
            info = j.getNombre() + " " + j.getApellido() + " - ELO: " + j.getELO();
        } else {
            Equipo e = (Equipo) participante;
            info = e.getNombre() + " - ELO:" + e.getELO() + " ";
        }

        JLabel labelInfo = new JLabel(info);
        labelInfo.setFont(new Font("Arial", Font.PLAIN, 16));
        labelInfo.setForeground(Color.WHITE);
        panel.add(labelInfo, BorderLayout.CENTER);

        JButton btnAceptar = new JButton("Aceptar");
        btnAceptar.setFont(new Font("Arial", Font.PLAIN, 14));
        btnAceptar.setBackground(new Color(70, 180, 70));
        btnAceptar.setForeground(Color.WHITE);
        btnAceptar.setFocusPainted(false);
        btnAceptar.setPreferredSize(new Dimension(100, 35));

        panel.add(btnAceptar, BorderLayout.EAST);
        return panel;
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
    public void generarParticipantes(){
        GeneradorJugadores generador = new GeneradorJugadores();
        ArrayList<? extends Participante> participantes;

        if ("Individual".equals(datosTorneo.getTipoParticipantes())) {
            participantes = generador.seleccionarPostulantes();
        } else {
            participantes = generador.generarEquipos();
        }

        for (Participante participante : participantes) {
            panelParticipantes.add(PanelParticipante(participante));
            panelParticipantes.add(Box.createRigidArea(new Dimension(0, 10)));
        }
        panelParticipantes.revalidate();
        panelParticipantes.repaint();
    }
}