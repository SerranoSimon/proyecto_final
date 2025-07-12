package visual;

import javax.swing.*;
import java.awt.*;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

public class PanelConsola {
    private static JDialog ventana;
    private static PanelConsola instance;
    private JTextArea areaTexto;
    private ByteArrayOutputStream baos;
    private PrintStream originalOut;
    private PrintStream customOut;

    private PanelConsola() {
    }

    public static PanelConsola getInstance() {
        if (instance == null) {
            instance = new PanelConsola();
        }
        return instance;
    }

    public void mostrarConsola(JFrame parent) {
        if (ventana == null) {
            crearVentana(parent);
            Consola();
        }
        ventana.setVisible(true);
    }

    private void crearVentana(JFrame parent) {
        ventana = new JDialog(parent, "Historial del torneo", false);
        ventana.setSize(700, 500);
        ventana.setLocationRelativeTo(parent);
        ventana.setLayout(new BorderLayout());
        ventana.setDefaultCloseOperation(JDialog.HIDE_ON_CLOSE);


        areaTexto = new JTextArea();
        areaTexto.setEditable(false);
        areaTexto.setFont(new Font("Monospaced", Font.PLAIN, 12));
        areaTexto.setBackground(new Color(30, 30, 40));
        areaTexto.setForeground(Color.WHITE);

        JScrollPane scrollPane = new JScrollPane(areaTexto);
        scrollPane.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        ventana.add(scrollPane, BorderLayout.CENTER);

        JButton btnCerrar = new JButton("Cerrar");
        btnCerrar.setBackground(Color.RED);
        btnCerrar.setForeground(Color.WHITE);
        btnCerrar.setFocusPainted(false);
        btnCerrar.addActionListener(e -> ventana.setVisible(false));

        JPanel panelBoton = new JPanel();
        panelBoton.add(btnCerrar);
        ventana.add(panelBoton, BorderLayout.SOUTH);
    }

    private void Consola() {

        originalOut = System.out;

        baos = new ByteArrayOutputStream();
        customOut = new PrintStream(baos) {
            @Override
            public void print(String s) {
                super.print(s);
                SwingUtilities.invokeLater(() -> {
                    areaTexto.append(s);
                    areaTexto.setCaretPosition(areaTexto.getDocument().getLength());
                });
            }

            @Override
            public void println(String s) {
                super.println(s);
                SwingUtilities.invokeLater(() -> {
                    areaTexto.append(s + "\n");
                    areaTexto.setCaretPosition(areaTexto.getDocument().getLength());
                });
            }
        };

        System.setOut(customOut);
        System.setErr(customOut);
    }

    public static void restaurarConsola() {
        if (instance != null && instance.originalOut != null) {
            System.setOut(instance.originalOut);
            System.setErr(instance.originalOut);
        }
    }
}