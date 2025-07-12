package visual;

import Logica.Participante;
import javax.swing.*;
import java.awt.*;
import java.util.List;

public class PanelEstadoTorneo {
    private static JDialog ventana;
    private static PanelEstadoTorneo instance;

    private PanelEstadoTorneo() {
    }

    public static PanelEstadoTorneo getInstance() {
        if (instance == null) {
            instance = new PanelEstadoTorneo();
        }
        return instance;
    }

    public void mostrarEstado(JFrame parent, List<Participante> participantes) {

        if (ventana == null) {
            crearVentana(parent);
        }
        actualizarContenido(participantes);
        ventana.setVisible(true);
    }

    private void crearVentana(JFrame parent) {
        ventana = new JDialog(parent, "Torneo", false);
        ventana.setSize(500, 400);
        ventana.setLocationRelativeTo(parent);
        ventana.setLayout(new BorderLayout());
        ventana.setDefaultCloseOperation(JDialog.HIDE_ON_CLOSE);
    }

    private void actualizarContenido(List<Participante> participantes) {

        ventana.getContentPane().removeAll();

        JPanel panelEstado = new JPanel();
        panelEstado.setLayout(new BorderLayout());
        panelEstado.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        JLabel titulo = new JLabel("Estado actual del torneo", SwingConstants.CENTER);
        titulo.setFont(new Font("Arial", Font.BOLD, 20));
        panelEstado.add(titulo, BorderLayout.NORTH);

        JPanel ordenParticipantes = new JPanel();
        ordenParticipantes.setLayout(new BoxLayout(ordenParticipantes, BoxLayout.Y_AXIS));
        ordenParticipantes.setBorder(BorderFactory.createEmptyBorder(10, 0, 10, 0));

        int posicion = 1;
        for (Participante p : participantes) {
            JPanel panelParticipante = new JPanel(new FlowLayout(FlowLayout.LEFT));
            panelParticipante.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
            panelParticipante.setMaximumSize(new Dimension(450, 30));

            JLabel lblPosicion = new JLabel(posicion + ". ");
            lblPosicion.setFont(new Font("Arial", Font.BOLD, 14));

            JLabel lblNombre = new JLabel(p.getNombre() + " " + p.getApellido());
            lblNombre.setFont(new Font("Arial", Font.PLAIN, 14));

            JLabel lblPuntos = new JLabel("Puntos: " + p.getPuntos());
            lblPuntos.setFont(new Font("Arial", Font.PLAIN, 14));
            lblPuntos.setBorder(BorderFactory.createEmptyBorder(0, 10, 0, 0));

            panelParticipante.add(lblPosicion);
            panelParticipante.add(lblNombre);
            panelParticipante.add(lblPuntos);

            ordenParticipantes.add(panelParticipante);
            ordenParticipantes.add(Box.createRigidArea(new Dimension(0, 5)));
            posicion++;
        }

        JScrollPane scrollPane = new JScrollPane(ordenParticipantes);
        scrollPane.setPreferredSize(new Dimension(450, 300));
        panelEstado.add(scrollPane, BorderLayout.CENTER);

        JButton btnCerrar = new JButton("Cerrar");
        btnCerrar.setBackground(Color.RED);
        btnCerrar.setForeground(Color.WHITE);
        btnCerrar.setFocusPainted(false);
        btnCerrar.addActionListener(e -> ventana.setVisible(false));

        JPanel panelBoton = new JPanel();
        panelBoton.add(btnCerrar);
        panelEstado.add(panelBoton, BorderLayout.SOUTH);

        ventana.add(panelEstado);
        ventana.revalidate();
        ventana.repaint();
    }
}