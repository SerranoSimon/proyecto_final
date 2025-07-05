package Logica;

public class Fantasma implements Participante{
    @Override
    public int getPuntos() {
        return 0;
    }

    @Override
    public String getNombre() {
        return "";
    }

    @Override
    public int getELO() {
        return 0;
    }

    @Override
    public int compareTo(Participante j) {
        return 0;
    }

    @Override
    public void agregarPuntos(int i) {

    }

    @Override
    public boolean getTuvoDescanso() {
        return false;
    }

    @Override
    public void setTuvoDescanso(boolean tuvoDescanso) {

    }
}
