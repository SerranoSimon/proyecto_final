package visual;
import javax.swing.*;
import java.awt.*;

public class PanelDatosTorneo extends JPanel {
    private JTextField textoFecha;
    private JTextField textoLugar;
    private DatosTorneo datosTorneo;

    public PanelDatosTorneo(Ventana ventana, DatosTorneo datosTorneo) {
        this.datosTorneo = datosTorneo;
        setLayout(new BorderLayout());
        setBackground(new Color(30, 30, 40));

        JLabel titulo = new JLabel("Información del Torneo", SwingConstants.CENTER);
        titulo.setFont(new Font("Monospaced", Font.BOLD, 30));
        titulo.setForeground(new Color(220, 220, 255));
        titulo.setBorder(BorderFactory.createEmptyBorder(50, 0, 30, 0));
        add(titulo, BorderLayout.NORTH);

        JPanel panelCentral = new JPanel();
        panelCentral.setLayout(new BoxLayout(panelCentral, BoxLayout.Y_AXIS));
        panelCentral.setOpaque(false);
        panelCentral.setBorder(BorderFactory.createEmptyBorder(0, 100, 0, 100));

        JPanel panelFecha = panelEntrada("Ingrese fecha y hora:");
        this.textoFecha = new JTextField(20);
        textoFecha.setMaximumSize(new Dimension(400, 30));
        textoFecha.setFont(new Font("Monospaced", Font.PLAIN, 14));
        textoFecha.setAlignmentX(Component.CENTER_ALIGNMENT);
        panelFecha.add(textoFecha);
        panelCentral.add(panelFecha);
        panelCentral.add(Box.createRigidArea(new Dimension(0, 20)));

        JPanel panelLugar = panelEntrada("Ingrese lugar del torneo:");
        this.textoLugar = new JTextField(20);
        textoLugar.setMaximumSize(new Dimension(400, 30));
        textoLugar.setFont(new Font("Monospaced", Font.PLAIN, 14));
        textoLugar.setAlignmentX(Component.CENTER_ALIGNMENT);
        panelLugar.add(textoLugar);
        panelCentral.add(panelLugar);

        JButton btnContinuar = getJButton(ventana);
        btnContinuar.setMaximumSize(new Dimension(300, 50));
        btnContinuar.addActionListener(e -> {
            try {
                validarDatosCompletos();
                datosTorneo.setTorneoFecha(textoFecha.getText());
                datosTorneo.setTorneoLugar(textoLugar.getText());
                ventana.getPanelInscripciones().setVisible(true);
                ventana.getPanelInscripciones().generarParticipantes();
                setVisible(false);

            } catch (DatosInsuficientesException ex) {
                JOptionPane.showMessageDialog(this,
                        ex.getMessage(),
                        "datos incompletos",
                        JOptionPane.WARNING_MESSAGE);
            }
        });
        panelCentral.add(Box.createRigidArea(new Dimension(0, 40)));
        panelCentral.add(btnContinuar);
        panelCentral.add(Box.createVerticalGlue());
        add(panelCentral, BorderLayout.CENTER);
    }

    private JButton getJButton(Ventana ventana) {
        JButton btnContinuar = new JButton("Continuar");
        btnContinuar.setFont(new Font("Monospaced", Font.BOLD, 16));
        btnContinuar.setBackground(new Color(70, 150, 220));
        btnContinuar.setForeground(Color.WHITE);
        btnContinuar.setAlignmentX(Component.CENTER_ALIGNMENT);
        btnContinuar.setMaximumSize(new Dimension(300, 50));
        btnContinuar.setBorder(BorderFactory.createEmptyBorder(10, 0, 10, 0));
        btnContinuar.setFocusPainted(false);
        return btnContinuar;
    }

    private JPanel panelEntrada(String texto) {
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.setOpaque(false);
        panel.setAlignmentX(Component.CENTER_ALIGNMENT);
        JLabel txt = new JLabel(texto);
        txt.setFont(new Font("Monospaced", Font.PLAIN, 16));
        txt.setForeground(Color.WHITE);
        txt.setAlignmentX(Component.CENTER_ALIGNMENT);
        txt.setBorder(BorderFactory.createEmptyBorder(0, 0, 5, 0));
        panel.add(txt);
        return panel;
    }

    private void validarDatosCompletos() throws DatosInsuficientesException {
        StringBuilder errores = new StringBuilder();

        if (textoLugar.getText().trim().isEmpty()) {
            errores.append("- El lugar del torneo es requerido\n");
        }
        if (textoFecha.getText().trim().isEmpty()) {
            errores.append("- La fecha y hora son requeridas\n");
        }

        if (errores.length() > 0) {
            throw new DatosInsuficientesException("Datos incompletos:\n" + errores.toString());
        }
    }
}