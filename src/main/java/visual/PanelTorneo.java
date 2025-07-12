package visual;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

import Logica.*;

public class PanelTorneo extends JPanel implements Observer {
    private DatosTorneo datosTorneo;
    protected Torneo torneo;
    private Timer timer;
    private int tiempoRestante;
    private JLabel contadorLabel;
    private JButton btnIniciarRonda;
    private JButton btnOrdenarEnfrentamientos;
    private JButton btnVerProximosEnfrentamientos;
    private JButton btnEstado;
    private JButton btnVerHistorial;
    private JPanel panelDeEnfrentamientos;
    private ArrayList<PanelEnfrentamiento> panelesEnfrentamientos;
    private boolean todosTerminados = false;

    public PanelTorneo(Ventana ventana) {
        this.datosTorneo = ventana.getDatosTorneo();
        panelesEnfrentamientos = new ArrayList<>();
        torneo = ventana.getPanelDatosTorneo().getTorneo();

        setBackground(Color.LIGHT_GRAY);
        setLayout(new BorderLayout());

        JPanel panelSuperior = new JPanel(new GridLayout(1, 3));
        panelSuperior.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        panelSuperior.setOpaque(false);

        JLabel lugar = new JLabel("Lugar: " + datosTorneo.getTorneoLugar());
        lugar.setFont(new Font("Monospaced", Font.PLAIN, 16));
        panelSuperior.add(lugar);

        contadorLabel = new JLabel("Tiempo: " + tiempoRestante + "s", SwingConstants.CENTER);
        contadorLabel.setFont(new Font("Arial", Font.BOLD, 18));
        contadorLabel.setForeground(Color.BLUE);
        panelSuperior.add(contadorLabel);

        JLabel infoTorneo = new JLabel(
                datosTorneo.getModalidadTorneo() + " - " + datosTorneo.getTorneoTiempoNormal() + " - " + datosTorneo.getTipoParticipantes(),
                SwingConstants.RIGHT
        );
        infoTorneo.setFont(new Font("Monospaced", Font.PLAIN, 16));
        panelSuperior.add(infoTorneo);

        add(panelSuperior, BorderLayout.NORTH);


        panelDeEnfrentamientos = new JPanel();
        panelDeEnfrentamientos.setLayout(new GridLayout(3, 2));
        add(panelDeEnfrentamientos, BorderLayout.CENTER);

        //BOTON VER ESTADO
        btnEstado = new JButton("Ver estado del torneo");
        btnEstado.setFont(new Font("Monospaced", Font.BOLD, 16));
        btnEstado.setBackground(new Color(70, 150, 220));
        btnEstado.setForeground(Color.WHITE);
        btnEstado.setFocusPainted(false);
        btnEstado.addActionListener(e -> {
            ArrayList<Participante> participantes = torneo.verEstado(); // primero ver estado del torneo

            JFrame parentFrame = (JFrame) SwingUtilities.getWindowAncestor(PanelTorneo.this);
            PanelEstadoTorneo panelEstado = new PanelEstadoTorneo(parentFrame, participantes);
            panelEstado.mostrar();
        });
        //BOTON INICIAR RONDA
        btnIniciarRonda = new JButton("Iniciar Ronda");
        btnIniciarRonda.setFont(new Font("Arial", Font.BOLD, 16));
        btnIniciarRonda.setBackground(new Color(60, 180, 75));
        btnIniciarRonda.setForeground(Color.WHITE);
        btnIniciarRonda.setFocusPainted(false);
        btnIniciarRonda.addActionListener(e -> {
            try {
                panelDeEnfrentamientos.removeAll();
                torneo.ejecutarRonda();
                for (Enfrentamiento enf : torneo.getEnfrentamientosJugadosPorRonda()) {
                    PanelEnfrentamiento p = new PanelEnfrentamiento(enf);
                    p.agregarObserver(this);
                    panelesEnfrentamientos.add(p);
                    panelDeEnfrentamientos.add(p);
                }
            } catch (OrdenarEnfrentamientosNoEjecutadoException ex) {
                JOptionPane.showMessageDialog(this, ex.getMessage(),
                        "Error", JOptionPane.ERROR_MESSAGE);

            }
            panelDeEnfrentamientos.repaint();
            panelDeEnfrentamientos.revalidate();
        });
        //BOTON ORDENAR ENFRENTAMIENTOS
        btnOrdenarEnfrentamientos = new JButton("Ordenar Enfrentamientos");
        btnOrdenarEnfrentamientos.setFont(new Font("Arial", Font.BOLD, 16));
        btnOrdenarEnfrentamientos.setBackground(new Color(60, 180, 75));
        btnOrdenarEnfrentamientos.setForeground(Color.WHITE);
        btnOrdenarEnfrentamientos.setFocusPainted(false);
        btnOrdenarEnfrentamientos.addActionListener(e -> {
            try {
                torneo.ordenarEnfrentamientos();
                btnOrdenarEnfrentamientos.setEnabled(false);
            } catch (EnfrentamientosYaOrdenadosException | LimiteDeRondasSuperadoException ex) {
                JOptionPane.showMessageDialog(this, ex.getMessage(),
                        "Error", JOptionPane.ERROR_MESSAGE);

            }

        });
        //BOTON VER HISTORIAL
        btnVerHistorial = new JButton("Ver historial");
        btnVerHistorial.setFont(new Font("Arial", Font.BOLD, 16));
        btnVerHistorial.setBackground(new Color(60, 180, 75));
        btnVerHistorial.setForeground(Color.WHITE);
        btnVerHistorial.setFocusPainted(false);
        btnVerHistorial.addActionListener(e -> {
            //accion
        });


        //panel inferior de botones
        JPanel panelBoton = new JPanel(new GridLayout(1, 3, 10, 0));
        panelBoton.setBorder(BorderFactory.createEmptyBorder(20, 100, 20, 100));
        panelBoton.setOpaque(false);

        panelBoton.add(btnEstado);
        panelBoton.add(btnIniciarRonda);
        panelBoton.add(btnOrdenarEnfrentamientos);
        add(panelBoton, BorderLayout.SOUTH);

        timer = new Timer(1000, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                tiempoRestante--;
                contadorLabel.setText("tiempo: " + tiempoRestante + "s");

                if (tiempoRestante <= 0) {
                    timer.stop();
                    contadorLabel.setForeground(Color.RED);
                    btnIniciarRonda.setEnabled(true);
                }
            }
        });
    }

    @Override
    public void actualizar() {
        todosTerminados = true;
        for (PanelEnfrentamiento pEnf : panelesEnfrentamientos) {
            if (!pEnf.isTerminado()) {
                todosTerminados = false;
                break;
            }
        }

        if (todosTerminados) {
            btnOrdenarEnfrentamientos.setEnabled(true);
        }
    }

}

