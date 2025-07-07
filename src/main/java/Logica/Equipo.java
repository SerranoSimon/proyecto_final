package Logica;

import java.util.ArrayList;
import java.util.Collections;

/**
 * Clase que representa un Equipo e implementa Participante, contiene un nombre de equipo,
 * una ArrayList de jugadores, un ELO promediado por sus jugadores, el puntaje y si tuvo descanso para
 * saber si dejarlo libre para los torneos con numero impar de participantes.
 */
public class Equipo implements Participante {
    private String nombre;
    private ArrayList<Participante> jugadores;
    private int ELOEquipo; //promedio de ELOs
    private int puntos;
    private boolean tuvoDescanso;

    /**
     * Constructor que inicializa con los datos dados y verifica que el equipo sea entre 2 y 4 jugadores
     * @param nombre nombre del equipo
     * @param jugadores arraylist de los jugadores que lo componen
     * @throws LimitesDeJugadoresPorEquipoException error si se la cantidad de jugadores no es la indicada
     */
    public Equipo(String nombre, ArrayList<Participante> jugadores){
        this.nombre=nombre;
        this.jugadores=jugadores;

        for(Participante j: jugadores){
            ELOEquipo+=j.getELO();
        }
        ELOEquipo=ELOEquipo/ jugadores.size();
        Collections.sort(jugadores);
        this.tuvoDescanso=false;
        this.puntos=0;

    }

    @Override
    public int getPuntos() {
        return puntos;
    }

    @Override
    public String getNombre() {
        return nombre;
    }

    @Override
    public int getELO() {
        return ELOEquipo;
    }

    public ArrayList<Participante> getJugadores() {
        return jugadores;
    }


    @Override
    public int compareTo(Participante e) {
        if(this.getELO()>e.getELO()){
            return -1;
        }
        else if(this.getELO()<e.getELO()){
            return 1;
        }
        else{
            return 0;
        }
    }

    @Override
    public void agregarPuntos(int i) {
        puntos+=i;
    }

    @Override
    public boolean getTuvoDescanso() {
        return tuvoDescanso;
    }

    @Override
    public void setTuvoDescanso(boolean tuvoDescanso) {
        this.tuvoDescanso=tuvoDescanso;
    }

    @Override
    public String toString() {
        return "Equipo "+nombre+"| puntos: "+puntos+"  ELO: "+ELOEquipo;
    }
}
