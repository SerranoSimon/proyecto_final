package visual;

import Logica.Participante;
import javax.swing.*;
import java.awt.*;
import java.util.List;

public class PanelEstadoTorneo extends JPanel {
    private JDialog Ventana;

    public PanelEstadoTorneo(JFrame parent, List<Participante> participantes) {

        Ventana = new JDialog(parent, "Torneo", false);
        Ventana.setSize(500, 400);
        Ventana.setLocationRelativeTo(parent);
        Ventana.setLayout(new BorderLayout());

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
        btnCerrar.addActionListener(e -> Ventana.setVisible(false));

        JPanel panelBoton = new JPanel();
        panelBoton.add(btnCerrar);
        panelEstado.add(panelBoton, BorderLayout.SOUTH);

        Ventana.add(panelEstado);
    }

    public void mostrar() {
        Ventana.setVisible(true);
    }
}