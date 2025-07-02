package Logica;

import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        Jugador j1=new Jugador("Benjamin","Poblete","benjaminp@gmail.com",1800);
        Jugador j2= new Jugador("Scarlet","Valdebenito","scarletv@gmail.com",1800);
        Jugador j3= new Jugador("Fernando","Saez","fernandos@gmail.com",1000);
        Jugador j4=new Jugador("Pilar","Oyarzun","pilaro@gmail.com",1000);
        Jugador j5=new Jugador("Marcelo","Leiva","marcelol@gmail.com",900);
        Jugador j6=new Jugador("Rosita","Curihual","rositac@gmail.com",900);
        Jugador j7=new Jugador("Vicente","Leal","vicentel@gmail.com",2320);
        Jugador j8=new Jugador("Patricio","Diaz","patriciod@gmail.com",2320);
        ArrayList<Jugador> jugadores1=new ArrayList<>(List.of(j1, j2));
        ArrayList<Jugador> jugadores2=new ArrayList<>(List.of(j3, j4));
        ArrayList<Jugador> jugadores3=new ArrayList<>(List.of(j5, j6));
        ArrayList<Jugador> jugadores4=new ArrayList<>(List.of(j7, j8));
        Equipo e1=new Equipo("Equipo 1",jugadores1);
        Equipo e2=new Equipo("Equipo 2",jugadores2);
        Equipo e3=new Equipo("Equipo 3",jugadores3);
        Equipo e4=new Equipo("Equipo 4",jugadores4);

        Torneo torneo= TorneoIndividual.getInstance();
        torneo.setPartidaDesempate(TipoDePartida.BLITZ);
        torneo.setPartidaNormal(TipoDePartida.CLASICA);
        ModalidadJuego modalidadJuego=new TodosContraTodos();
        torneo.setModalidadJuego(modalidadJuego);
        torneo.agregarParticipante(j1);
        torneo.agregarParticipante(j2);
        torneo.agregarParticipante(j3);
        torneo.agregarParticipante(j4);
        torneo.agregarParticipante(j5);
        torneo.ordenarEnfrentamientos();
        torneo.ejecutarRonda();
        torneo.verEstado();
        torneo.ordenarEnfrentamientos();
        torneo.ejecutarRonda();

    }
}
