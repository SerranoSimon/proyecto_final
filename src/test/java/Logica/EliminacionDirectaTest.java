package Logica;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
class EliminacionDirectaTest {
    Participante e1;
    Participante e2;
    Participante e3;
    Participante e4;
    Participante j1;
    Participante j2;
    Participante j3;
    Participante j4;
    Participante j5;
    Participante j6;
    Participante j7;
    Participante j8;

    @BeforeEach
    void setUp() {
        j1=new Jugador("Benjamin","Poblete","benjaminp@gmail.com",1700);
        j2= new Jugador("Scarlet","Valdebenito","scarletv@gmail.com",1800);
        j3= new Jugador("Fernando","Saez","fernandos@gmail.com",1000);
        j4=new Jugador("Pilar","Oyarzun","pilaro@gmail.com",900);
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
    void ordenarParticipantesImpar() {
        EliminacionDirecta eliminacionDirecta= new EliminacionDirecta();
        //caso impar
       ArrayList<Participante> jugadores=new ArrayList<>(List.of(j1,j2,j3));
       eliminacionDirecta.ordenarParticipantes(jugadores,1); //da lo mismo la ronda
       assertEquals(j2,eliminacionDirecta.getParticipantesCopia().get(0));
        assertEquals(j1,eliminacionDirecta.getParticipantesCopia().get(1));
        assertEquals(j3,eliminacionDirecta.getParticipantesCopia().get(2));
        assertTrue(eliminacionDirecta.getParticipantesCopia().get(3) instanceof Fantasma);

    }
    @Test
    void ordenarParticipantesPar() {
        EliminacionDirecta eliminacionDirecta= new EliminacionDirecta();
       ArrayList<Participante> jugadores=new ArrayList<>(List.of(j1,j2,j3,j4));
        eliminacionDirecta.ordenarParticipantes(jugadores,1); //da lo mismo la ronda
        assertEquals(j2,eliminacionDirecta.getParticipantesCopia().get(0));
        assertEquals(j1,eliminacionDirecta.getParticipantesCopia().get(1));
        assertEquals(j3,eliminacionDirecta.getParticipantesCopia().get(2));
        assertEquals(j4,eliminacionDirecta.getParticipantesCopia().get(3));

    }

    @Test
    void obtenerDistribucionEnfrentamientosImpar() {

        EliminacionDirecta eliminacionDirecta= new EliminacionDirecta();
        //caso impar
        ArrayList<Participante> participantes=new ArrayList<>(List.of(j1,j2,j3));
       eliminacionDirecta.ordenarParticipantes(participantes,1);

        ArrayList<ArrayList<Participante>> distribucion =eliminacionDirecta.obtenerDistribucionEnfrentamientos(participantes);
        assertEquals(new ArrayList<Participante>(List.of(j2,j3)),distribucion.get(0));
        assertTrue(distribucion.get(1).get(1) instanceof Fantasma);
        assertEquals(j1,distribucion.get(1).get(0));
    }
    @Test
    void obtenerDistribucionEnfrentamientosPar() {
        EliminacionDirecta eliminacionDirecta= new EliminacionDirecta();
        ArrayList<Participante> participantes=new ArrayList<>(List.of(j1,j2,j3,j4));
        eliminacionDirecta.ordenarParticipantes(participantes,1);
        ArrayList<ArrayList<Participante>> distribucion = eliminacionDirecta.obtenerDistribucionEnfrentamientos(participantes);

        assertEquals(new ArrayList<Participante>(List.of(j2,j3)), distribucion.get(0));
        assertEquals(new ArrayList<Participante>(List.of(j1,j4)), distribucion.get(1));



    }

    @Test
    void numeroDeRondas() {
        EliminacionDirecta eliminacionDirecta=new EliminacionDirecta();
        assertEquals(2,eliminacionDirecta.numeroDeRondas(3));
        assertEquals(2,eliminacionDirecta.numeroDeRondas(4));
        assertEquals(3,eliminacionDirecta.numeroDeRondas(5));
        assertEquals(3,eliminacionDirecta.numeroDeRondas(6));
    }
}