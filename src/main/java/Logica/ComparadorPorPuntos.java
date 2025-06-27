package Logica;
import java.util.Comparator;

public class ComparadorPorPuntos implements Comparator<Jugador> {
    @Override
    public int compare(Jugador j1, Jugador j2) {
        return Integer.compare(j2.getPuntos(), j1.getPuntos());
    }
}

