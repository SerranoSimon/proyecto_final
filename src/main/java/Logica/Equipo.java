package Logica;

import java.util.ArrayList;
import java.util.Collections;

public class Equipo implements Participante {
    private String nombre;
    private ArrayList<Participante> jugadores;
    private int ELOEquipo; //promedio de ELOs
    private int puntos;
    private boolean tuvoDescanso;


    public Equipo(String nombre, ArrayList<Participante> jugadores) throws LimitesDeJugadoresPorEquipoException{
        this.nombre=nombre;
        if(2<=jugadores.size() && jugadores.size()<=4){
            this.jugadores=jugadores;
        }
        else{
            throw new LimitesDeJugadoresPorEquipoException("Un equipo debe tener entre 2 y 4 jugadores");
        }

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
        return nombre+"| puntos: "+puntos+", ELO: "+ELOEquipo;
    }
}
