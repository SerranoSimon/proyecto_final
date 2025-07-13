package visual;

import Logica.Enfrentamiento;
import Logica.Participante;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;
import javax.swing.ToolTipManager;

public class PanelEnfrentamiento extends JPanel {
    private List<Observer> observers = new ArrayList<>();
    private boolean terminado;
    private Participante p1;
    private Participante p2;
    private Enfrentamiento enfrentamiento;
    private boolean mostrarGanador;
    public PanelEnfrentamiento(Enfrentamiento enf){
        enfrentamiento=enf;
        p1=enf.getP1();
        p2=enf.getP2();
        ToolTipManager.sharedInstance().registerComponent(this);
        Timer timer = new Timer(enf.getTiempoPartidasJugadas()*1000, e -> {
            mostrarGanador = true;
            terminado=true;
            notificar();
            repaint();
        });
        timer.setRepeats(false);
        timer.start();

    }
    public void agregarObserver(Observer observer) {
        observers.add(observer);
    }
    public void notificar() {
        for (Observer observer : observers) {
            observer.actualizar();
        }
    }
    @Override
    public String getToolTipText(MouseEvent e) {
        int x = e.getX();
        int y = e.getY();

        int mesaX = getWidth() / 4 + (getWidth() / 4);
        int mesaY = getHeight() / 2 - 30;

        //Coordenadas Participante 1 (izquierda)
        Rectangle jugador1 = new Rectangle(mesaX - 100, mesaY - 30, 25, 25);
        //Coordenadas Participante 2 (derecha)
        Rectangle jugador2 = new Rectangle(mesaX + 75, mesaY - 30, 25, 25);
        //Coordendas mesa
        Rectangle mesaRect = new Rectangle(mesaX - 60, mesaY - 10, 120, 70);

        if (jugador1.contains(x, y)) {
            return p1.toString();
        } else if (jugador2.contains(x, y)) {
            return p2.toString();
        } else if (mesaRect.contains(x, y)) {
            return mostrarGanador ? "Terminado" : "En juego";
        }

        return null;
    }
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        g2.setColor(Color.white);
        g2.fillRect(0, 0, getWidth(), getHeight());

            int mesaX = getWidth()/4 + (getWidth()/4);
            int mesaY = getHeight()/2 - 30;

            g2.setColor(new Color(139, 69, 19));
            g2.fillRoundRect(mesaX - 60, mesaY - 10, 120, 70, 15, 15);

            g2.setColor(new Color(222, 184, 135));
            g2.fillRect(mesaX - 40, mesaY, 80, 40);
            g2.setColor(Color.BLACK);
            g2.drawRect(mesaX - 40, mesaY, 80, 40);

            g2.setColor(new Color(101, 67, 33));
            g2.fillRect(mesaX - 30, mesaY + 10, 20, 20);
            g2.fillRect(mesaX + 10, mesaY + 10, 20, 20);

            g2.setColor(new Color(30, 144, 255));

            g2.fillOval(mesaX - 100, mesaY - 30, 25, 25);

            int[] xPointsLeft = {mesaX - 88, mesaX - 100, mesaX - 76};
            int[] yPointsLeft = {mesaY - 5, mesaY + 20, mesaY + 20};
            g2.fillPolygon(xPointsLeft, yPointsLeft, 3);

            g2.drawLine(mesaX - 88, mesaY + 5, mesaX - 78, mesaY);

            g2.setColor(new Color(220, 20, 60));

            g2.fillOval(mesaX + 75, mesaY - 30, 25, 25);

            int[] xPointsRight = {mesaX + 87, mesaX + 75, mesaX + 99};
            int[] yPointsRight = {mesaY - 5, mesaY + 20, mesaY + 20};
            g2.fillPolygon(xPointsRight, yPointsRight, 3);

            g2.drawLine(mesaX + 75, mesaY + 5, mesaX + 65, mesaY);

            g2.setColor(new Color(160, 82, 45));
            g2.fillRoundRect(mesaX - 110, mesaY + 15, 20, 10, 5, 5);
            g2.fillRoundRect(mesaX + 90, mesaY + 15, 20, 10, 5, 5);


        g2.setColor(new Color(200, 200, 200, 100));
        g2.fillOval(mesaX - 50, getHeight()/2 + 40, 100, 20);

        if (mostrarGanador) {
            Participante ganador= enfrentamiento.getGanador();
            g2.setColor(Color.BLACK);
            g2.setFont(new Font("Arial", Font.BOLD, 16));
            g2.drawString("Ganador: " + ganador.toString(), getWidth() / 2 - 250, 25);
        }

    }

    public boolean isTerminado() {
        return terminado;
    }
}
