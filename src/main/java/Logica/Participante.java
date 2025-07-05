package Logica;

/**
 * interfaz que define los metodos de cualquier Participante.
 */
public interface Participante extends Comparable<Participante>{
    int getPuntos();
    String getNombre();
    int getELO();

    int compareTo(Participante j);

    void agregarPuntos(int i);
    boolean getTuvoDescanso();
    void setTuvoDescanso(boolean tuvoDescanso);
}
