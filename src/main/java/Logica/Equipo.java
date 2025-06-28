package Logica;

import java.util.ArrayList;
import java.util.Collections;

public class Equipo implements Participante {
    private String nombre;
    private ArrayList<Jugador> jugadores;
    private int ELOEquipo; //promedio de ELOs
    private int puntos;
    public Equipo(String nombre, ArrayList<Jugador> jugadores){
        this.nombre=nombre;
        this.jugadores=jugadores;
        for(Jugador j: jugadores){
            ELOEquipo=+j.getELO();
        }
        ELOEquipo=ELOEquipo/ jugadores.size();
        Collections.sort(jugadores);

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

    public ArrayList<Jugador> getJugadores() {
        return jugadores;
    }
    private void actualizarPuntaje(){
        for(Jugador j: jugadores){
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
}
