package visual;

import Logica.Participante;
import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

/**
 * Panel que genera una ventana para visualizar los proximos enfrentamientos una vez ordenados, patron de uso singleton
 */
public class PanelProximosEnfrentamientos extends JPanel {
    private JButton btnCerrar;
    private static PanelProximosEnfrentamientos instance;
    private JPanel panelEnfrentamientos;

    private PanelProximosEnfrentamientos() {
        setLayout(new BorderLayout());
        setBackground(new Color(240, 240, 245));
        setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        configurarTitulo();
        configurarBotonCerrar();
    }

    public static PanelProximosEnfrentamientos getInstance() {
        if (instance == null) {
            instance = new PanelProximosEnfrentamientos();
        }
        return instance;
    }

    public void actualizarEnfrentamientos(ArrayList<ArrayList<Participante>> nuevosEnfrentamientos) {

        if (panelEnfrentamientos != null) {
            remove(panelEnfrentamientos);
        }

        panelEnfrentamientos = crearPanelEnfrentamientos(nuevosEnfrentamientos);

        JScrollPane scrollPane = new JScrollPane(panelEnfrentamientos);
        scrollPane.setBorder(BorderFactory.createEmptyBorder());
        scrollPane.getViewport().setBackground(new Color(240, 240, 245));
        scrollPane.setPreferredSize(new Dimension(500, 400));

        JPanel contenedorCentral = new JPanel(new BorderLayout());
        contenedorCentral.add(scrollPane, BorderLayout.CENTER);
        contenedorCentral.setOpaque(false);

        add(contenedorCentral, BorderLayout.CENTER);

        revalidate();
        repaint();
    }

    /**
     *
     */
    private void configurarTitulo() {
        JLabel titulo = new JLabel("Próximos Enfrentamientos", SwingConstants.CENTER);
        titulo.setFont(new Font("Arial", Font.BOLD, 24));
        titulo.setForeground(new Color(50, 50, 150));
        titulo.setBorder(BorderFactory.createEmptyBorder(0, 0, 20, 0));
        add(titulo, BorderLayout.NORTH);
    }

    /**
     *
     * @param enfrentamientos
     * @return
     */
    private JPanel crearPanelEnfrentamientos(ArrayList<ArrayList<Participante>> enfrentamientos) {
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.setOpaque(false);
        panel.setAlignmentX(Component.CENTER_ALIGNMENT);

        if (enfrentamientos != null && !enfrentamientos.isEmpty()) {
            for (ArrayList<Participante> enfrentamiento : enfrentamientos) {
                if (enfrentamiento.size() == 2) {
                    Participante p1 = enfrentamiento.get(0);
                    Participante p2 = enfrentamiento.get(1);
                    panel.add(crearPanelEnfrentamientoIndividual(p1, p2));
                    panel.add(Box.createVerticalStrut(20));
                }
            }
        }

        return panel;
    }

    /**
     *
     * @param p1
     * @param p2
     * @return
     */
    private JPanel crearPanelEnfrentamientoIndividual(Participante p1, Participante p2) {
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(new Color(70, 130, 180), 2),
                BorderFactory.createEmptyBorder(10, 10, 10, 10)
        ));
        panel.setBackground(new Color(220, 240, 255));
        panel.setAlignmentX(Component.CENTER_ALIGNMENT);
        panel.setMaximumSize(new Dimension(450, 120));

        JLabel lblTitulo = new JLabel("Enfrentamiento", SwingConstants.CENTER);
        lblTitulo.setFont(new Font("Arial", Font.BOLD, 16));
        lblTitulo.setAlignmentX(Component.CENTER_ALIGNMENT);
        panel.add(lblTitulo);

        panel.add(Box.createVerticalStrut(10));

        JLabel lblParticipante1 = new JLabel(p1.toString());
        lblParticipante1.setFont(new Font("Arial", Font.PLAIN, 14));
        lblParticipante1.setAlignmentX(Component.CENTER_ALIGNMENT);
        panel.add(lblParticipante1);

        JLabel lblVs = new JLabel("VS", SwingConstants.CENTER);
        lblVs.setFont(new Font("Arial", Font.BOLD, 14));
        lblVs.setAlignmentX(Component.CENTER_ALIGNMENT);
        panel.add(lblVs);

        JLabel lblParticipante2 = new JLabel(p2.toString());
        lblParticipante2.setFont(new Font("Arial", Font.PLAIN, 14));
        lblParticipante2.setAlignmentX(Component.CENTER_ALIGNMENT);
        panel.add(lblParticipante2);

        return panel;
    }

    /**
     * Configuracion visual y actionlistener del boton cerrar del panel
     */
    private void configurarBotonCerrar() {
        btnCerrar = new JButton("Cerrar");
        btnCerrar.setFont(new Font("Arial", Font.BOLD, 16));
        btnCerrar.setBackground(new Color(220, 80, 60));
        btnCerrar.setForeground(Color.WHITE);
        btnCerrar.setFocusPainted(false);
        btnCerrar.setPreferredSize(new Dimension(120, 40));
        btnCerrar.addActionListener(e -> {
            JDialog dialogo = (JDialog) SwingUtilities.getWindowAncestor(this);
            if (dialogo != null) {
                dialogo.dispose();
            }
        });

        JPanel panelBoton = new JPanel();
        panelBoton.setOpaque(false);
        panelBoton.add(btnCerrar);

        add(panelBoton, BorderLayout.SOUTH);
    }
}