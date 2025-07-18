package visual;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

import Logica.*;

/**
 * Panel que representa un torneo en la interfaz grafica de la aplicación
 */
public class PanelTorneo extends JPanel implements Observer {
    private DatosTorneo datosTorneo;
    protected Torneo torneo;
    private JLabel rondaActualLabel;
    private JLabel rondasMaximasLabel;
    private JButton btnIniciarRonda;
    private JButton btnOrdenarEnfrentamientos;
    private JButton btnVerProximosEnfrentamientos;
    private JButton btnEstablecerGanadores;
    private JButton btnEstado;
    private JButton btnVerHistorial;
    private JPanel panelDeEnfrentamientos;
    private PanelConsola panelConsola;
    private ArrayList<PanelEnfrentamiento> panelesEnfrentamientos;
    private boolean todosTerminados = false;

    public PanelTorneo(Ventana ventana) {
        panelConsola = PanelConsola.getInstance();
        this.datosTorneo = ventana.getDatosTorneo();
        panelesEnfrentamientos = new ArrayList<>();
        torneo = ventana.getPanelDatosTorneo().getTorneo();

        setBackground(Color.LIGHT_GRAY);
        setLayout(new BorderLayout());

        JPanel panelSuperior = new JPanel(new GridLayout(1, 3));
        panelSuperior.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        panelSuperior.setOpaque(false);
        //Lugar
        JLabel lugar = new JLabel("Lugar: " + datosTorneo.getTorneoLugar());
        lugar.setFont(new Font("Monospaced", Font.PLAIN, 16));
        panelSuperior.add(lugar);
        // Fecha y hora

        JLabel fechaHora = new JLabel("Inicio "+datosTorneo.getTorneoFecha());
        fechaHora.setFont(new Font("Monospaced", Font.PLAIN, 16));
        panelSuperior.add(fechaHora);

        //RONDAS MAXIMAS LABEL
        rondasMaximasLabel = new JLabel("", SwingConstants.CENTER);
        rondasMaximasLabel.setFont(new Font("Arial", Font.BOLD, 18));
        rondasMaximasLabel.setForeground(Color.BLUE);
        panelSuperior.add(rondasMaximasLabel);

        //RONDA ACTUAL LABEL
        rondaActualLabel = new JLabel("", SwingConstants.CENTER);
        rondaActualLabel.setFont(new Font("Arial", Font.BOLD, 18));
        rondaActualLabel.setForeground(Color.BLUE);
        panelSuperior.add(rondaActualLabel);
        //info torneo
        JLabel infoTorneo = new JLabel(
                datosTorneo.getModalidadTorneo() + " - " + datosTorneo.getTorneoTiempoNormal()+" - "+datosTorneo.getTiempoDesempate()+" - "+datosTorneo.getTipoParticipantes(),
                SwingConstants.RIGHT
        );
        infoTorneo.setFont(new Font("Monospaced", Font.PLAIN, 8));
        panelSuperior.add(infoTorneo);

        add(panelSuperior, BorderLayout.NORTH);


        panelDeEnfrentamientos = new JPanel();
        panelDeEnfrentamientos.setLayout(new GridLayout(3, 2));
        add(panelDeEnfrentamientos, BorderLayout.CENTER);

        //BOTON VER ESTADO
        btnEstado = new JButton("Ver estado del torneo");
        btnEstado.setFont(new Font("Monospaced", Font.BOLD, 10));
        btnEstado.setBackground(new Color(70, 150, 220));
        btnEstado.setForeground(Color.WHITE);
        btnEstado.setFocusPainted(false);
        btnEstado.addActionListener(e -> {
            ArrayList<Participante> participantes = torneo.verEstado();
            PanelEstadoTorneo panelEstado = PanelEstadoTorneo.getInstance();
            JFrame parentFrame = (JFrame) SwingUtilities.getWindowAncestor(PanelTorneo.this);
            panelEstado.mostrarEstado(parentFrame, participantes);
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
                rondaActualLabel.setText("Ronda " + torneo.getNumeroRonda());
                for (Enfrentamiento enf : torneo.getEnfrentamientosJugadosPorRonda()) {
                    PanelEnfrentamiento p = new PanelEnfrentamiento(enf);
                    p.agregarObserver(this);
                    panelesEnfrentamientos.add(p);
                    panelDeEnfrentamientos.add(p);
                }
                btnEstado.setEnabled(false);
                btnIniciarRonda.setEnabled(false);
            } catch (OrdenarEnfrentamientosNoEjecutadoException ex) {
                JOptionPane.showMessageDialog(this, ex.getMessage(),
                        "Error", JOptionPane.ERROR_MESSAGE);

            }
            panelDeEnfrentamientos.repaint();
            panelDeEnfrentamientos.revalidate();
        });
        //BOTON ORDENAR ENFRENTAMIENTOS
        btnOrdenarEnfrentamientos = new JButton("Ordenar Enfrentamientos");
        btnOrdenarEnfrentamientos.setFont(new Font("Arial", Font.BOLD, 10));
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
        btnVerHistorial.setFont(new Font("Arial", Font.BOLD, 13));
        btnVerHistorial.setBackground(new Color(60, 180, 75));
        btnVerHistorial.setForeground(Color.WHITE);
        btnVerHistorial.setFocusPainted(false);
        btnVerHistorial.addActionListener(e -> {
            panelConsola = PanelConsola.getInstance();
            JFrame parentFrame = (JFrame) SwingUtilities.getWindowAncestor(PanelTorneo.this);
            panelConsola.mostrarConsola(parentFrame);
        });

        //BOTON ESTABLECER GANADORES
        btnEstablecerGanadores = new JButton("Establecer ganadores");
        btnEstablecerGanadores.setFont(new Font("Arial", Font.BOLD, 10));
        btnEstablecerGanadores.setBackground(new Color(60, 180, 75));
        btnEstablecerGanadores.setForeground(Color.WHITE);
        btnEstablecerGanadores.setFocusPainted(false);
        btnEstablecerGanadores.setEnabled(false);
        btnEstablecerGanadores.addActionListener(e -> {
            try {
                torneo.establecerGanadores();

            } catch ( ExisteEmpateException ex) {
                JOptionPane.showMessageDialog(this,"Ok para desempatar",
                        ex.getMessage(), JOptionPane.WARNING_MESSAGE);
                try {
                    torneo.desempatar();
                    btnEstablecerGanadores.setEnabled(false);

                } catch (OrdenarEnfrentamientosNoEjecutadoException exc) {
                    throw new RuntimeException(exc);
                }
            }
            Participante primerLugar = torneo.getPrimerLugar();
            Participante segundoLugar = torneo.getSegundoLugar();
            Participante tercerLugar = torneo.getTercerLugar();

            PanelGanadores panelGanadores = new PanelGanadores(primerLugar, segundoLugar, tercerLugar);

            JPanel nuevoPanelCentral = new JPanel(new BorderLayout());
            nuevoPanelCentral.add(panelGanadores, BorderLayout.CENTER);

            remove(panelDeEnfrentamientos);
            add(nuevoPanelCentral, BorderLayout.CENTER);
            btnEstablecerGanadores.setEnabled(false);
            btnOrdenarEnfrentamientos.setEnabled(false);
            btnIniciarRonda.setEnabled(false);
            btnEstado.setEnabled(false);
            revalidate();
            repaint();

        });
        // BOTON VER PROXIMOS ENFRENTAMIENTOS
        btnVerProximosEnfrentamientos = new JButton("Ver proximos enfrentamientos");
        btnVerProximosEnfrentamientos.setFont(new Font("Monospaced", Font.BOLD, 10));
        btnVerProximosEnfrentamientos.setBackground(new Color(70, 150, 220));
        btnVerProximosEnfrentamientos.setForeground(Color.WHITE);
        btnVerProximosEnfrentamientos.setFocusPainted(false);
        btnVerProximosEnfrentamientos.addActionListener(e -> {
            ArrayList<ArrayList<Participante>> proximosEnfrentamientos = null;
            try {
                proximosEnfrentamientos = torneo.obtenerProximosEnfrentamientos();
            } catch (OrdenarEnfrentamientosNoEjecutadoException ex) {
                JOptionPane.showMessageDialog(this,
                        "Aún no se han ordenado los enfrentamientos",
                        "Enfrentamientos no ordenados", JOptionPane.WARNING_MESSAGE);
                return;
            }

            JDialog dialog = new JDialog((JFrame) SwingUtilities.getWindowAncestor(PanelTorneo.this),
                    "Próximos Enfrentamientos", true);
            dialog.setSize(600, 500);
            dialog.setLocationRelativeTo(PanelTorneo.this);

            PanelProximosEnfrentamientos panelProximos = PanelProximosEnfrentamientos.getInstance();
            panelProximos.actualizarEnfrentamientos(proximosEnfrentamientos);

            JPanel container = new JPanel(new BorderLayout());
            container.add(panelProximos, BorderLayout.CENTER);

            dialog.add(container);
            dialog.setVisible(true);
        });
        JPanel panelBoton = new JPanel(new GridLayout(1, 3, 10, 0));
        panelBoton.setBorder(BorderFactory.createEmptyBorder(20, 100, 20, 100));
        panelBoton.setOpaque(false);

        panelBoton.add(btnEstado);
        panelBoton.add(btnIniciarRonda);
        panelBoton.add(btnOrdenarEnfrentamientos);
        panelBoton.add(btnVerHistorial);
        panelBoton.add(btnEstablecerGanadores);
        panelBoton.add(btnVerProximosEnfrentamientos);
        add(panelBoton, BorderLayout.SOUTH);

    }

    /**
     * se revisa si todos los enfrentamientos terminaron, para habilitar o deshabilitar botones
     */
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
            btnEstado.setEnabled(true);
            btnIniciarRonda.setEnabled(true);
        }
        if(torneo.getNumeroMaximoRondas()== torneo.getNumeroRonda()){
            btnEstablecerGanadores.setEnabled(true);
        }
    }

    public void setRondasMaximasLabel() {
      rondasMaximasLabel.setText("Rondas máximas: "+torneo.getNumeroMaximoRondas());
    }
}

