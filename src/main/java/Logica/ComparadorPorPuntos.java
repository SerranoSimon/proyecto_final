package Logica;
import java.util.Comparator;

/**
 * Clase que compara por la puntuacion de los participantes, para así ordenarlos de mayor a
 * menor puntuacion.
 */
public class ComparadorPorPuntos implements Comparator<Participante> {
    @Override
    public int compare(Participante p1,Participante p2) {
        return Integer.compare(p2.getPuntos(), p1.getPuntos());
    }
}

