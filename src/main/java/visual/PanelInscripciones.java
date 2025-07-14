package visual;

import Logica.*;
import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class PanelInscripciones extends JPanel {
    private PanelDatosTorneo panelDatosTorneo;
    private DatosTorneo datosTorneo;
    private JPanel panelContenedorParticipantes;
    private Image fondo;
    public PanelInscripciones(Ventana ventana) {
        fondo = new ImageIcon(getClass().getResource("/AjedrezFondo.jpg")).getImage();
        panelDatosTorneo=ventana.getPanelDatosTorneo();
        datosTorneo=ventana.getDatosTorneo();
        setLayout(new BorderLayout());
        setBackground(new Color(30, 30, 40));

        JLabel titulo = new JLabel("Aceptar Inscripciones", SwingConstants.CENTER);
        titulo.setFont(new Font("Arial", Font.BOLD, 30));
        titulo.setForeground(new Color(220, 220, 255));
        titulo.setBorder(BorderFactory.createEmptyBorder(50, 0, 30, 0));
        add(titulo, BorderLayout.NORTH);

        panelContenedorParticipantes = new JPanel();
        panelContenedorParticipantes.setLayout(new BoxLayout(panelContenedorParticipantes, BoxLayout.Y_AXIS));
        panelContenedorParticipantes.setOpaque(false);

        JScrollPane scrollPane = new JScrollPane(panelContenedorParticipantes);
        scrollPane.setBorder(null);
        scrollPane.setOpaque(false);
        scrollPane.getViewport().setOpaque(false);
        scrollPane.setPreferredSize(new Dimension(800, 400));
        add(scrollPane, BorderLayout.CENTER);

        JButton btnContinuar = Boton("Iniciar torneo", new Color(70, 150, 220));
        btnContinuar.addActionListener(e -> {
            int cantidadParticipantes = ventana.getPanelDatosTorneo().getTorneo().getParticipantes().size();
            if (cantidadParticipantes < 4) {
                JOptionPane.showMessageDialog(this,
                        "El mínimo de participantes es 4. Tienes  " + cantidadParticipantes + ".",
                        "Error",
                        JOptionPane.ERROR_MESSAGE);
                return;
            }
            ventana.crearPanelTorneo();
            ventana.getPanelTorneo().torneo.iniciar();
            ventana.getPanelTorneo().setRondasMaximasLabel();
            ventana.getPanelInscripciones().setVisible(false);
            ventana.getPanelTorneo().setVisible(true);
            ventana.getPanelTorneo().setVisible(true);
            this.setVisible(false);
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
    public void generarParticipantes(){
        GeneradorJugadores generador = new GeneradorJugadores();
        ArrayList<Participante> participantes;

        if ("Individual".equals(datosTorneo.getTipoParticipantes())) {
            participantes = generador.generarJugadores();
        } else {
            participantes = generador.generarEquipos();
        }

        for (Participante participante : participantes) {
            panelContenedorParticipantes.add(new PanelParticipante(participante, panelDatosTorneo.getTorneo()));
            panelContenedorParticipantes.add(Box.createRigidArea(new Dimension(0, 10)));
        }
        panelContenedorParticipantes.revalidate();
        panelContenedorParticipantes.repaint();
    }
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(fondo, 0, 0, getWidth(), getHeight(), this);

    }
}