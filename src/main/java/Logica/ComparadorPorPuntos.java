package Logica;
import java.util.Comparator;

public class ComparadorPorPuntos implements Comparator<Participante> {
    @Override
    public int compare(Participante p1,Participante p2) {
        return Integer.compare(p2.getPuntos(), p1.getPuntos());
    }
}

