package Logica;
//Patron composite?
public interface Participante extends Comparable<Participante>{
    int getPuntos();
    String getNombre();
    int getELO();

    int compareTo(Participante j);

    void agregarPuntos(int i);
}
