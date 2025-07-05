package Logica;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
class TorneoIndividualTest {
    TorneoIndividual torneo;
    Participante j1;
    Participante j2;
    Participante j3;
    Participante j4;
    Participante j5;
    Participante j6;
    Participante j7;
    Participante e1;
    @BeforeEach
    void setUp() {
         torneo=new TorneoIndividual(new TodosContraTodos(), TipoDePartida.CLASICA, TipoDePartida.BLITZ);
        j1=new Jugador("Benjamin","Poblete","benjaminp@gmail.com",1700);
        j2= new Jugador("Scarlet","Valdebenito","scarletv@gmail.com",1800);
        j3= new Jugador("Fernando","Saez","fernandos@gmail.com",1000);
        j4=new Jugador("Pilar","Oyarzun","pilaro@gmail.com",900);
        j5=new Jugador("Marcelo","Leiva","marcelol@gmail.com",2000);
        j6=new Jugador("Rosita","Curihual","rositac@gmail.com",2400);
        j7=new Jugador("Vicente","Leal","vicentel@gmail.com",2320);
        ArrayList<Participante> jugadores1=new ArrayList<>(List.of(j1, j2));
        e1=new Equipo("Equipo 1",jugadores1);
    }

    @Test
    void agregarMasDelMaximoDeParticipante() {

        Exception exception = assertThrows(LimitesDeParticipantesException.class, () -> {
            torneo.agregarParticipante(j1);
            torneo.agregarParticipante(j2);
            torneo.agregarParticipante(j3);
            torneo.agregarParticipante(j4);
            torneo.agregarParticipante(j5);
            torneo.agregarParticipante(j6);
            torneo.agregarParticipante(j7);

        });
    }

    @Test
    void agregarParticipanteIncorrecto() {
        Exception exception = assertThrows(TipoDeParticipanteException.class, () -> {
            torneo.agregarParticipante(e1);
        });
    }
    @Test
    void agregarParticipanteCorrecto() {
        torneo.agregarParticipante(j1);
        torneo.agregarParticipante(j2);
        torneo.agregarParticipante(j3);
        assertEquals(j1,torneo.participantes.get(0));
        assertEquals(j2,torneo.participantes.get(1));
        assertEquals(j3,torneo.participantes.get(2));
    }

    @Test
    void desempatar() throws OrdenarEnfrentamientosNoEjecutadoException {
        //seteamos para que se produzca un desempate
        torneo.agregarParticipante(j1);
        torneo.agregarParticipante(j2);
        torneo.agregarParticipante(j3);
        torneo.numeroRonda=2;
        j1.agregarPuntos(1);
        j2.agregarPuntos(1);
        j3.agregarPuntos(1);
        assertNull(torneo.primerLugar);
        assertNull(torneo.segundoLugar);
        assertNull(torneo.tercerLugar);
        torneo.desempatar();
        assertNotNull(torneo.primerLugar);
        assertTrue(torneo.primerLugar instanceof Jugador);
        assertNotNull(torneo.segundoLugar);
        assertTrue(torneo.segundoLugar instanceof Jugador);
        assertNotNull(torneo.tercerLugar);
        assertTrue(torneo.tercerLugar instanceof Jugador);

    }
}