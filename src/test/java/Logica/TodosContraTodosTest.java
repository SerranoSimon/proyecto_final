package Logica;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class TodosContraTodosTest {
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
       TodosContraTodos todosContraTodos=new TodosContraTodos();
        ArrayList<Participante> jugadores=new ArrayList<>(List.of(j1,j2,j3));
        //primera ronda
        todosContraTodos.ordenarParticipantes(jugadores,1);
        jugadores=todosContraTodos.getParticipantesCopia();
        assertEquals(jugadores.get(0),j2);
        assertEquals(jugadores.get(1),j1);
        assertTrue(jugadores.get(3) instanceof Fantasma);
        assertEquals(jugadores.get(2),j3);
        //segunda ronda
        todosContraTodos.ordenarParticipantes(todosContraTodos.getParticipantesCopia(),2);
        jugadores=todosContraTodos.getParticipantesCopia();
        assertEquals(jugadores.get(0),j2);
        assertEquals(jugadores.get(1),j3);
        assertTrue(jugadores.get(2) instanceof Fantasma);
        assertEquals(jugadores.get(3),j1);
        //tercera ronda
        todosContraTodos.ordenarParticipantes(todosContraTodos.getParticipantesCopia(),3);
        jugadores=todosContraTodos.getParticipantesCopia();
        assertEquals(jugadores.get(0),j2);
        assertEquals(jugadores.get(2),j1);
        assertTrue(jugadores.get(1) instanceof Fantasma);
        assertEquals(jugadores.get(3),j3);

    }
    @Test
    void ordenarParticipantesPar() {
        TodosContraTodos todosContraTodos=new TodosContraTodos();
        ArrayList<Participante> jugadores=new ArrayList<>(List.of(e1,e2,e3,e4));
        //primera ronda
        todosContraTodos.ordenarParticipantes(jugadores,1);
        jugadores=todosContraTodos.getParticipantesCopia();
        assertEquals(jugadores.get(0),e4);
        assertEquals(jugadores.get(1),e3);
        assertEquals(jugadores.get(2),e1);
        assertEquals(jugadores.get(3),e2);

        //segunda ronda
        todosContraTodos.ordenarParticipantes(todosContraTodos.getParticipantesCopia(),2);
        jugadores=todosContraTodos.getParticipantesCopia();
        assertEquals(jugadores.get(0),e4);
        assertEquals(jugadores.get(1),e1);
        assertEquals(jugadores.get(2),e2);
        assertEquals(jugadores.get(3),e3);
        //tercera ronda
        todosContraTodos.ordenarParticipantes(todosContraTodos.getParticipantesCopia(),3);
        jugadores=todosContraTodos.getParticipantesCopia();
        assertEquals(jugadores.get(0),e4);
        assertEquals(jugadores.get(1),e2);
        assertEquals(jugadores.get(2),e3);
        assertEquals(jugadores.get(3),e1);



    }

    @Test
    void obtenerDistribucionEnfrentamientosImpar() throws OrdenarEnfrentamientosNoEjecutadoException {
        TodosContraTodos todosContraTodos=new TodosContraTodos();

        ArrayList<Participante> participantes=new ArrayList<>(List.of(j1,j2,j3));
        todosContraTodos.ordenarParticipantes(participantes,1);

        ArrayList<ArrayList<Participante>> distribucion =todosContraTodos.obtenerDistribucionEnfrentamientos(participantes);
        assertEquals(new ArrayList<Participante>(List.of(j2,j3)),distribucion.get(0));
        assertTrue(distribucion.get(1).get(1) instanceof Fantasma);
        assertEquals(j1,distribucion.get(1).get(0));

    }
    @Test
    void obtenerDistribucionEnfrentamientosPar() throws OrdenarEnfrentamientosNoEjecutadoException {
        TodosContraTodos todosContraTodos=new TodosContraTodos();
        ArrayList<Participante> participantes=new ArrayList<>(List.of(j1,j2,j3,j4));
        todosContraTodos.ordenarParticipantes(participantes,1);
        ArrayList<ArrayList<Participante>> distribucion = todosContraTodos.obtenerDistribucionEnfrentamientos(participantes);

        assertEquals(new ArrayList<Participante>(List.of(j2,j3)), distribucion.get(0));
        assertEquals(new ArrayList<Participante>(List.of(j1,j4)), distribucion.get(1));
    }

    @Test
    void numeroDeRondas() {
        TodosContraTodos todosContraTodos=new TodosContraTodos();
        assertEquals(3,todosContraTodos.numeroDeRondas(3));
        assertEquals(3,todosContraTodos.numeroDeRondas(4));
        assertEquals(5,todosContraTodos.numeroDeRondas(5));
        assertEquals(5,todosContraTodos.numeroDeRondas(6));

    }
}