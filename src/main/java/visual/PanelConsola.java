
package visual;

import javax.swing.*;
import java.awt.*;
import java.io.OutputStream;
import java.io.PrintStream;
public class PanelConsola extends JPanel {
    private JTextArea textArea;
    private PrintStream originalSystemOut;

    public PanelConsola() {

        textArea = new JTextArea();
        textArea.setEditable(false);
        textArea.setFont(new Font("Monospaced", Font.PLAIN, 14));
        textArea.setBackground(Color.BLACK);
        textArea.setForeground(Color.GREEN);


        JScrollPane scrollPane = new JScrollPane(textArea);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        setLayout(new BorderLayout());
        add(scrollPane, BorderLayout.CENTER);


        redirectSystemOutput();
    }

    private void redirectSystemOutput() {
        PrintStream printStream = new PrintStream(new OutputStream() {
            @Override
            public void write(int b) {
                textArea.append(String.valueOf((char) b));
                textArea.setCaretPosition(textArea.getDocument().getLength());
            }
        });

        originalSystemOut = System.out;
        System.setOut(printStream);
        System.setErr(printStream);
    }

    public void restoreOriginalSystemOutput() {
        System.setOut(originalSystemOut);
        System.setErr(originalSystemOut);
    }
}
