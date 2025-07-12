package visual;

import javax.swing.*;
import java.awt.*;
import java.io.OutputStream;
import java.io.PrintStream;

public class PanelConsola {
    private static PanelConsola instance;
    private JDialog ventana;
    private JTextArea areaTexto;
    private PrintStream originalOut;
    private PrintStream customOut;

    private PanelConsola() {
        // Redirige la salida solo una vez
        originalOut = System.out;

        areaTexto = new JTextArea();
        areaTexto.setEditable(false);
        areaTexto.setFont(new Font("Monospaced", Font.PLAIN, 12));
        areaTexto.setBackground(new Color(30, 30, 40));
        areaTexto.setForeground(Color.WHITE);

        customOut = new PrintStream(new OutputStream() {
            private final StringBuilder buffer = new StringBuilder();

            @Override
            public void write(int b) {
                char c = (char) b;
                buffer.append(c);
                if (c == '\n') {
                    final String line = buffer.toString();
                    buffer.setLength(0);
                    SwingUtilities.invokeLater(() -> {
                        areaTexto.append(line);
                        areaTexto.setCaretPosition(areaTexto.getDocument().getLength());
                    });
                }
            }
        });

        System.setOut(customOut);
        System.setErr(customOut); // Opcional: redirige errores también
    }

    public static PanelConsola getInstance() {
        if (instance == null) {
            instance = new PanelConsola();
        }
        return instance;
    }

    public void mostrarConsola(JFrame parent) {
        if (ventana == null) {
            ventana = new JDialog(parent, "Historial del torneo", false);
            ventana.setSize(700, 500);
            ventana.setLocationRelativeTo(parent);
            ventana.setLayout(new BorderLayout());
            ventana.setDefaultCloseOperation(JDialog.HIDE_ON_CLOSE);

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

        ventana.setVisible(true);
    }

    public static void restaurarConsola() {
        if (instance != null && instance.originalOut != null) {
            System.setOut(instance.originalOut);
            System.setErr(instance.originalOut);
        }
    }
}
