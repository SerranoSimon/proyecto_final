package Logica;

import java.util.ArrayList;
import java.util.Collections;

public class Equipo implements Participante {
    private String nombre;
    private ArrayList<Participante> jugadores;
    private int ELOEquipo; //promedio de ELOs
    private int puntos;
    private boolean tuvoDescanso;


    public Equipo(String nombre, ArrayList<Participante> jugadores){
        this.nombre=nombre;
        this.jugadores=jugadores;
        for(Participante j: jugadores){
            ELOEquipo+=j.getELO();
        }
        ELOEquipo=ELOEquipo/ jugadores.size();
        Collections.sort(jugadores);
        this.tuvoDescanso=false;

    }

    @Override
    public int getPuntos() {
        actualizarPuntaje();
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
    public void actualizarPuntaje(){
        puntos=0;
        for(Participante j: jugadores){
            puntos+=j.getPuntos();
        }
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
