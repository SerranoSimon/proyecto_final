package Logica;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
class TorneoEquiposTest {
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
    void desempatar() throws OrdenarEnfrentamientosNoEjecutadoException {
        TorneoEquipos torneo=new TorneoEquipos(new SistemaSuizo(), TipoDePartida.CLASICA, TipoDePartida.RAPIDA);
        torneo.agregarParticipante(e1);
        torneo.agregarParticipante(e2);
        torneo.agregarParticipante(e3);
        torneo.agregarParticipante(e4);
        //todos tienen 0 puntos de base
        torneo.numeroRonda=2; //ponemos la ultima ronda
        assertNull(torneo.primerLugar);
        assertNull(torneo.segundoLugar);
        assertNull(torneo.tercerLugar);
        torneo.desempatar();
        assertNotNull((torneo.primerLugar));
        assertNotNull((torneo.segundoLugar));
        assertNotNull((torneo.tercerLugar));
        assertTrue(torneo.primerLugar instanceof Equipo);
        assertTrue(torneo.segundoLugar instanceof Equipo);
        assertTrue(torneo.tercerLugar instanceof Equipo);
    }
}