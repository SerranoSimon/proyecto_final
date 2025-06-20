import java.util.ArrayList;

public class Equipo implements Participante{
    private String nombre;
    private ArrayList<Jugador> jugadores;
    private int puntos;
    public Equipo(String nombre, ArrayList<Jugador> jugadores){
        this.nombre=nombre;
        this.jugadores=jugadores;
    }
    public void agregarPuntos(int puntos){
        this.puntos=+puntos;
    }
}
