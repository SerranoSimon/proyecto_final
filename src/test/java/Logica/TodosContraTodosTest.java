package Logica;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class TodosContraTodosTest {
    static Participante j1;
    static Participante j2;
    static Participante j3;
    static Participante j4;
    static Participante j5;
    static Participante j6;
    static Participante e1;
    static Participante e2;
    static Participante e3;
    static Torneo torneoIndividual;
    static Torneo torneoEquipos;

    /*
    @BeforeAll
    static void setUp() {
         j1=new Jugador("Benjamin","Poblete","benjaminp@gmail.com",1800);
         j2= new Jugador("Scarlet","Valdebenito","scarletv@gmail.com",1700);
         j3= new Jugador("Fernando","Saez","fernandos@gmail.com",1200);
         j4=new Jugador("Pilar","Oyarzun","pilaro@gmail.com",1300);
        j5=new Jugador("Marcelo","Leiva","marcelol@gmail.com",900);
        j6=new Jugador("Rosita","Curihual","rositac@gmail.com",900);
        ArrayList<Participante> jugadores1=new ArrayList<>(List.of(j1, j2));
        ArrayList<Participante> jugadores2=new ArrayList<>(List.of(j3, j4));
        ArrayList<Participante> jugadores3=new ArrayList<>(List.of(j5, j6));
        e1=new Equipo("Equipo 1",jugadores1);
        e2=new Equipo("Equipo 2",jugadores2);
        e3=new Equipo("Equipo 3",jugadores3);
        torneoIndividual= TorneoIndividual.getInstance();
        torneoIndividual.setPartidaDesempate(TipoDePartida.BLITZ);
        torneoIndividual.setPartidaNormal(TipoDePartida.CLASICA);
        ModalidadJuego modalidadJuego=new TodosContraTodos();
        torneoIndividual.setModalidadJuego(modalidadJuego);
        torneoEquipos=TorneoEquipos.getInstance();
        torneoEquipos.setPartidaNormal(TipoDePartida.CLASICA);
        torneoEquipos.setPartidaDesempate(TipoDePartida.RAPIDA);
        torneoEquipos.setModalidadJuego(modalidadJuego);
    }

    @Test
    void obtenerDistribucionEnfrentamientosParPrimeraRonda() {
        torneoIndividual.agregarParticipante(j1);
        torneoIndividual.agregarParticipante(j2);
        torneoIndividual.agregarParticipante(j3);
        torneoIndividual.agregarParticipante(j4);
        torneoIndividual.ordenarEnfrentamientos();
        ArrayList<ArrayList<Participante>> esperado1=new ArrayList<>();

        ArrayList<Participante> enf1=new ArrayList<>(List.of(j1,j4));
        ArrayList<Participante> enf2=new ArrayList<>(List.of(j2,j3));
        esperado1.add(enf1);
        esperado1.add(enf2);
        assertEquals(esperado1,torneoIndividual.distribucion);

    }
    @Test
    void obtenerDistribucionEnfrentamientosParSegundaRonda() {
        torneoIndividual.ejecutarRonda();
        torneoIndividual.ordenarEnfrentamientos();
        ArrayList<ArrayList<Participante>> esperado=new ArrayList<>();

        ArrayList<Participante> enf3=new ArrayList<>(List.of(j1,j3));
        ArrayList<Participante> enf4=new ArrayList<>(List.of(j4,j2));
        esperado.add(enf3);
        esperado.add(enf4);
        assertEquals(esperado,torneoIndividual.distribucion);

    }
    @Test
    void obtenerDistribucionEnfrentamientosImparTerceraRonda() {
        torneoEquipos.agregarParticipante(e1);
        torneoEquipos.agregarParticipante(e2);
        torneoEquipos.agregarParticipante(e3);

        torneoEquipos.ordenarEnfrentamientos();
        torneoEquipos.ejecutarRonda();
        torneoEquipos.ordenarEnfrentamientos();
        torneoEquipos.ejecutarRonda();
        torneoEquipos.ordenarEnfrentamientos();

        ArrayList<ArrayList<Participante>> esperado=new ArrayList<>();

        //sabemos que uno será fantasma
        ArrayList<Participante> enf2=new ArrayList<>(List.of(e1,e2));

        esperado.add(enf2);
        assertEquals(esperado.get(0),torneoEquipos.distribucion.get(0));

    }*/
}