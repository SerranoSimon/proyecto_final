package Logica;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class SistemaSuizoTest {
    Equipo e1;
    Equipo e2;
    Equipo e3;
    Equipo e4;
    Jugador j1;
    Jugador j2;
    Jugador j3;
    Jugador j4;
    Jugador j5;
    Jugador j6;
    Jugador j7;
    Jugador j8;
    @BeforeEach
    void setUp() {
         j1=new Jugador("Benjamin","Poblete","benjaminp@gmail.com",1800);
         j2= new Jugador("Scarlet","Valdebenito","scarletv@gmail.com",1000);
         j3= new Jugador("Fernando","Saez","fernandos@gmail.com",1000);
         j4=new Jugador("Pilar","Oyarzun","pilaro@gmail.com",1000);
         j5=new Jugador("Marcelo","Leiva","marcelol@gmail.com",2000);
         j6=new Jugador("Rosita","Curihual","rositac@gmail.com",2400);
         j7=new Jugador("Vicente","Leal","vicentel@gmail.com",2320);
         j8=new Jugador("Patricio","Diaz","patriciod@gmail.com",2320);
         ArrayList<Participante> jugadores1=new ArrayList<>(List.of(j1, j2));
        ArrayList<Participante> jugadores2=new ArrayList<>(List.of(j3, j4));
        ArrayList<Participante> jugadores3=new ArrayList<>(List.of(j5, j6));
        ArrayList<Participante> jugadores4=new ArrayList<>(List.of(j7, j8));
        e1=new Equipo("Equipo 1",jugadores1);
        e2=new Equipo("Equipo 2",jugadores2);
        e3=new Equipo("Equipo 3",jugadores3);
        e4=new Equipo("Equipo 4",jugadores4);



    }

    @Test
    void ejuctarRondas() {
    }
   //PROBLEMAS PORQUE NO PODEMOS SABER LOS RESULTADOS DESPUES DE LA RONDA 1
    @Test
    void ordenarEnfrentamientosJugadores() {
        Torneo torneo=TorneoIndividual.getInstance();
        torneo.setModalidadJuego(new SistemaSuizo());
        torneo.agregarParticipante(j1);
        torneo.agregarParticipante(j2);
        torneo.agregarParticipante(j3);
        torneo.agregarParticipante(j4);
        j1.agregarPuntos(4);
        j2.agregarPuntos(6);
        j3.agregarPuntos(2);
        j4.agregarPuntos(1);
         ArrayList<Participante> esperado = new ArrayList<>(List.of(j2, j1, j3, j4));
         torneo.ordenarEnfrentamientos();
         assertEquals(esperado,torneo.participantes);

    }
    @Test
    void ordenarEnfrentamientosEquipos(){
        e1.agregarPuntos(12);
        e2.agregarPuntos(9);
        e3.agregarPuntos(3);
        e4.agregarPuntos(12);
        ArrayList<Participante> esperado1 = new ArrayList<>(List.of(e1, e4, e2, e3));
        ArrayList<Participante> esperado2 = new ArrayList<>(List.of(e4, e1, e2, e3));
        Torneo torneo=TorneoEquipos.getInstance();
        torneo.setModalidadJuego(new SistemaSuizo());
        torneo.agregarParticipante(e1);
        torneo.agregarParticipante(e2);
        torneo.agregarParticipante(e3);
        torneo.agregarParticipante(e4);
        torneo.ordenarEnfrentamientos();
        assertTrue(esperado1.equals(torneo.participantes) ||esperado2.equals(torneo.participantes));

    }

    @Test
    void ordenarEnfrentamientosEquipos2(){
        e1.agregarPuntos(24);
        e2.agregarPuntos(14);
        e3.agregarPuntos(20);
        ArrayList<Participante> esperado = new ArrayList<>(List.of(e1, e3, e2));
        Torneo torneo=TorneoEquipos.getInstance();
        torneo.setModalidadJuego(new SistemaSuizo());
        torneo.agregarParticipante(e1);
        torneo.agregarParticipante(e2);
        torneo.agregarParticipante(e3);
        torneo.ordenarEnfrentamientos();
        assertEquals(esperado,torneo.participantes);


    }





}