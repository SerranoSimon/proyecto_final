package visual;

import Logica.*;

import javax.swing.*;
import java.awt.*;

public class PanelParticipante extends JPanel {
    public PanelParticipante(Participante participante, Torneo torneo) {
       setLayout(new BorderLayout(10, 0));
       setBorder(BorderFactory.createEmptyBorder(5, 20, 5, 20));
        setBackground(new Color(50, 50, 60));
       setMaximumSize(new Dimension(700, 60));


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
        this.add(labelInfo, BorderLayout.CENTER);

        JButton btnAceptar = new JButton("Aceptar");
        btnAceptar.setFont(new Font("Arial", Font.PLAIN, 14));
        btnAceptar.setBackground(new Color(70, 180, 70));
        btnAceptar.setForeground(Color.WHITE);
        btnAceptar.setFocusPainted(false);
        btnAceptar.setPreferredSize(new Dimension(100, 35));
        btnAceptar.addActionListener(e -> {
            try{
            torneo.agregarParticipante(participante);
            setVisible(false);
            }
            catch (LimitesDeParticipantesException ex){
                JOptionPane.showMessageDialog(this,
                        ex.getMessage(),
                        "Error al añadir participantes", JOptionPane.WARNING_MESSAGE);
            }

        });

       this.add(btnAceptar, BorderLayout.EAST);
    }
}
