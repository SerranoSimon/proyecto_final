package visual;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;
import java.awt.*;

import Logica.*;

public class PanelTorneo extends JPanel {
    private DatosTorneo datosTorneo;
    protected Torneo torneo;
    private Timer timer;
    private int tiempoRestante;
    private JLabel contadorLabel;
    private JButton btnIniciarRonda;
    private JButton btnOrdenarEnfrentamientos;
    private JButton btnVerProximosEnfrentamientos;
    private JButton btnEstado;
    private JPanel panelDeEnfrentamientos;

    public PanelTorneo(Ventana ventana) {
        this.datosTorneo = ventana.getDatosTorneo();
        torneo=ventana.getPanelDatosTorneo().getTorneo();

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


        JPanel panelDeEnfrentamientos=new JPanel();
        panelDeEnfrentamientos.setLayout(new GridLayout(3,2));
        add(panelDeEnfrentamientos, BorderLayout.CENTER);

       //BOTON VER ESTADO
        btnEstado = new JButton("Ver estado del torneo");
        btnEstado.setFont(new Font("Monospaced", Font.BOLD, 16));
        btnEstado.setBackground(new Color(70, 150, 220));
        btnEstado.setForeground(Color.WHITE);
        btnEstado.setFocusPainted(false);
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
                for(Enfrentamiento enf: torneo.getEnfrentamientosJugadosPorRonda()){
                    panelDeEnfrentamientos.add(new PanelEnfrentamiento(enf));
                }
            } catch (OrdenarEnfrentamientosNoEjecutadoException ex) {
                JOptionPane.showMessageDialog(this,  ex.getMessage(),
                        "Error", JOptionPane.ERROR_MESSAGE);

            }
            panelDeEnfrentamientos.repaint();
            panelDeEnfrentamientos.revalidate();
        });
        //BOTON ORDENAR ENFRENTAMIENTOS
        btnOrdenarEnfrentamientos= new JButton("Ordenar Enfrentamientos");
        btnOrdenarEnfrentamientos.setFont(new Font("Arial", Font.BOLD, 16));
        btnOrdenarEnfrentamientos.setBackground(new Color(60, 180, 75));
        btnOrdenarEnfrentamientos.setForeground(Color.WHITE);
        btnOrdenarEnfrentamientos.setFocusPainted(false);
        btnOrdenarEnfrentamientos.addActionListener(e -> {
            try {
                torneo.ordenarEnfrentamientos();

            } catch (EnfrentamientosYaOrdenadosException | LimiteDeRondasSuperadoException ex) {
                JOptionPane.showMessageDialog(this,  ex.getMessage(),
                        "Error", JOptionPane.ERROR_MESSAGE);

            }

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
    /*
    private void iniciarContador() {
            switch(datosTorneo.getTorneoTiempoNormal()) {
                case "blitz": tiempoRestante = 3; break;
                case "rápido": tiempoRestante = 15; break;
                case "clásico": tiempoRestante = 90; break;
                default: tiempoRestante = 30;
            }
            contadorLabel.setText("tiempo: " + tiempoRestante + "s");
            contadorLabel.setForeground(Color.BLUE);
            timer.start();
            btnIniciarRonda.setEnabled(false);
    } */
}