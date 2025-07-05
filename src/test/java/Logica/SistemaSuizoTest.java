package Logica;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class SistemaSuizoTest {
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
    void ordenarParticipantesIndividual(){
        //prueba para torneo individual
        //primera ronda
        SistemaSuizo sistemaSuizo=new SistemaSuizo();
        ArrayList<Participante> jugadores=new ArrayList<>(List.of(j1,j2,j3));
        sistemaSuizo.ordenarParticipantes(jugadores,1);
        assertEquals(new ArrayList<>(List.of(j2,j1,j3)), jugadores);
        //segunda ronda
        j1.agregarPuntos(3);
        j2.agregarPuntos(1);
        j3.agregarPuntos(2);
        sistemaSuizo.ordenarParticipantes(jugadores,2);
        assertEquals(new ArrayList<>(List.of(j1,j3,j2)), jugadores);

        //prueba para equipos
        //ronda 1
        ArrayList<Participante> equipos=new ArrayList<>(List.of(e1,e2,e3,e4));
        sistemaSuizo.ordenarParticipantes(equipos,1);
        assertEquals(new ArrayList<>(List.of(e4,e3,e1,e2)), equipos);
        //ronda 2
        e1.agregarPuntos(12);
        e2.agregarPuntos(9);
        e3.agregarPuntos(3);
        e4.agregarPuntos(12);
        sistemaSuizo.ordenarParticipantes(equipos,2);
        assertTrue(new ArrayList<>(List.of(e1,e4,e2,e3)).equals(equipos)
                || new ArrayList<>(List.of(e4,e1,e2,e3)).equals(equipos) );


    }
    @Test
    void ordenarParticipantesEquipos(){
        SistemaSuizo sistemaSuizo=new SistemaSuizo();
        //ronda 1
        ArrayList<Participante> equipos=new ArrayList<>(List.of(e1,e2,e3,e4));
        sistemaSuizo.ordenarParticipantes(equipos,1);
        assertEquals(new ArrayList<>(List.of(e4,e3,e1,e2)), equipos);
        //ronda 2
        e1.agregarPuntos(12);
        e2.agregarPuntos(9);
        e3.agregarPuntos(3);
        e4.agregarPuntos(12);
        sistemaSuizo.ordenarParticipantes(equipos,2);
        assertTrue(new ArrayList<>(List.of(e1,e4,e2,e3)).equals(equipos)
                || new ArrayList<>(List.of(e4,e1,e2,e3)).equals(equipos) );

    }



    @Test
    void obtenerDistribucionEnfrentamientosImpar() throws OrdenarEnfrentamientosNoEjecutadoException {
        SistemaSuizo sistemaSuizo=new SistemaSuizo();
        //caso impar
        ArrayList<Participante> participantes=new ArrayList<>(List.of(j1,j2,j3));
        sistemaSuizo.ordenarParticipantes(participantes,1);
        ArrayList<ArrayList<Participante>> distribucion = sistemaSuizo.obtenerDistribucionEnfrentamientos(participantes);
        assertEquals(new ArrayList<Participante>(List.of(j2,j1)),distribucion.get(1));
        assertTrue(distribucion.get(0).get(0) instanceof Fantasma);
        assertEquals(j3,distribucion.get(0).get(1));
    }
    @Test
    void obtenerDistribucionEnfrentamientosPar() throws OrdenarEnfrentamientosNoEjecutadoException {
        SistemaSuizo sistemaSuizo=new SistemaSuizo();
        // caso par
        ArrayList<Participante> participantes=new ArrayList<>(List.of(j1,j2,j3,j4));
        sistemaSuizo.ordenarParticipantes(participantes,1);
        ArrayList<ArrayList<Participante>> distribucion = sistemaSuizo.obtenerDistribucionEnfrentamientos(participantes);

        assertEquals(new ArrayList<Participante>(List.of(j2,j1)), distribucion.get(0));
        assertEquals(new ArrayList<Participante>(List.of(j3,j4)), distribucion.get(1));
    }

    @Test
    void numeroDeRondas() {
        SistemaSuizo sistemaSuizo=new SistemaSuizo();
        assertEquals(2,sistemaSuizo.numeroDeRondas(3));
        assertEquals(2,sistemaSuizo.numeroDeRondas(4));
        assertEquals(3,sistemaSuizo.numeroDeRondas(5));
        assertEquals(3,sistemaSuizo.numeroDeRondas(6));
    }



    @Test
    void crearEnfrentamientoFantasma() {
        j1.setTuvoDescanso(true);
        j2.setTuvoDescanso(true);
        j3.setTuvoDescanso(false); //jugador que debe jugar con el fantasma
        ArrayList<Participante> participantes=new ArrayList<>(List.of(j1,j2,j3));
        SistemaSuizo sistemaSuizo=new SistemaSuizo();
        ArrayList<Participante> enf= sistemaSuizo.crearEnfrentamientoFantasma(participantes);
        assertTrue(enf.get(1)==j3 && enf.get(0) instanceof Fantasma);
    }
}