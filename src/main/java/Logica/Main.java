package Logica;

import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        Jugador j1=new Jugador("Benjamin","Poblete","benjaminp@gmail.com",1800);
        Jugador j2= new Jugador("Scarlet","Valdebenito","scarletv@gmail.com",1800);
        Jugador j3= new Jugador("Fernando","Saez","fernandos@gmail.com",1800);
        Jugador j4=new Jugador("Pilar","Oyarzun","pilaro@gmail.com",1800);
        Jugador j5=new Jugador("Marcelo","Leiva","marcelol@gmail.com",900);
        Jugador j6=new Jugador("Rosita","Curihual","rositac@gmail.com",900);
        Jugador j7=new Jugador("Vicente","Leal","vicentel@gmail.com",2320);
        Jugador j8=new Jugador("Patricio","Diaz","patriciod@gmail.com",2320);
        ArrayList<Participante> jugadores1=new ArrayList<>(List.of(j1, j2));
        ArrayList<Participante> jugadores2=new ArrayList<>(List.of(j3, j4));
        ArrayList<Participante> jugadores3=new ArrayList<>(List.of(j5, j6));
        ArrayList<Participante> jugadores4=new ArrayList<>(List.of(j7, j8));
        Equipo e1=new Equipo("Equipo 1",jugadores1);
        Equipo e2=new Equipo("Equipo 2",jugadores2);
        Equipo e3=new Equipo("Equipo 3",jugadores3);
        Equipo e4=new Equipo("Equipo 4",jugadores4);

        Torneo torneo= new TorneoIndividual(new TodosContraTodos(),TipoDePartida.CLASICA, TipoDePartida.RAPIDA);
        torneo.solicitarInscripcion(j1);
        torneo.solicitarInscripcion(j2);
        torneo.solicitarInscripcion(j3);
        torneo.solicitarInscripcion(j4);
        torneo.solicitarInscripcion(j5);
        torneo.aceptarSolicitud(j1);
        torneo.aceptarSolicitud(j2);
        torneo.aceptarSolicitud(j3);
        torneo.aceptarSolicitud(j4);
        torneo.aceptarSolicitud(j5);
        torneo.actualizarNumeroMaximoRondas();
        System.out.println(torneo.numeroMaximoRondas);
        torneo.verEstado();
        torneo.ordenarEnfrentamientos();
        torneo.ejecutarRonda();
        torneo.verEstado();
        torneo.ordenarEnfrentamientos();
        torneo.ejecutarRonda();
        torneo.verEstado();
        torneo.ordenarEnfrentamientos();
        torneo.ejecutarRonda();
        torneo.verEstado();
        torneo.ordenarEnfrentamientos();
        torneo.ejecutarRonda();
        torneo.verEstado();
        torneo.ordenarEnfrentamientos();
        torneo.ejecutarRonda();
        torneo.verEstado();
        torneo.establecerGanadores();



    }
}
