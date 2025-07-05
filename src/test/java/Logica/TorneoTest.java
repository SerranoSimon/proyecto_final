package Logica;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
class TorneoTest {
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
    Torneo torneo;

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
        torneo=new TorneoIndividual(new EliminacionDirecta(),TipoDePartida.CLASICA, TipoDePartida.BLITZ);
        torneo.agregarParticipante(j1);
        torneo.agregarParticipante(j2);
        torneo.agregarParticipante(j3);
        torneo.agregarParticipante(j4);
        ArrayList<Participante> jugadores1=new ArrayList<>(List.of(j1, j2));
        ArrayList<Participante> jugadores2=new ArrayList<>(List.of(j3, j4));
        ArrayList<Participante> jugadores3=new ArrayList<>(List.of(j5, j6));
        ArrayList<Participante> jugadores4=new ArrayList<>(List.of(j7, j8));
        e1=new Equipo("Equipo 1",jugadores1);
        e2=new Equipo("Equipo 2",jugadores2);
        e3=new Equipo("Equipo 3",jugadores3);
        e4=new Equipo("Equipo 4",jugadores3);

    }

    @Test
    void ordenarEnfrentamientosMasDeUnaVez () {
        Torneo torneo1=new TorneoIndividual(new SistemaSuizo(),TipoDePartida.CLASICA,TipoDePartida.RAPIDA);
        torneo1.numeroRonda=0;
        torneo1.numeroMaximoRondas=1;
        torneo1.ordenarEnfrentamientos();
        Exception exception1 = assertThrows(RuntimeException.class, () -> {
           torneo1.ordenarEnfrentamientos();
        });
    }
    @Test
    void ordenarEnfrentamientosDespuesDeUltimaRonda () {
        Torneo torneo1=new TorneoIndividual(new SistemaSuizo(),TipoDePartida.CLASICA,TipoDePartida.RAPIDA);
        torneo1.numeroRonda=1;
        torneo1.numeroMaximoRondas=1;
        Exception exception1 = assertThrows(LimiteDeRondasSuperadoException.class, () -> {
            torneo1.ordenarEnfrentamientos();
        });
    }


    @Test
    void ejecutarRondaSinOrdenar() throws OrdenarEnfrentamientosNoEjecutadoException {
        Torneo torneo=new TorneoIndividual(new EliminacionDirecta(),TipoDePartida.CLASICA, TipoDePartida.BLITZ);
        torneo.agregarParticipante(j1);
        torneo.agregarParticipante(j2);
        torneo.agregarParticipante(j3);;
        torneo.agregarParticipante(j4);
        Exception exception = assertThrows(OrdenarEnfrentamientosNoEjecutadoException.class, () -> {
            torneo.ejecutarRonda();
        });
    }
    //solo hay algunos casos ya que son muchos, pero en el main se puede ir probando que se cumple
    //que siempre que las listas de los 3 mejores puntajes tengan longitud 1 no se necesita desempate
    @Test
    void seNecesitaDesempatePorPrimerLugar() {
        j1.agregarPuntos(1);
        j2.agregarPuntos(1);
        j3.agregarPuntos(0);
        assertTrue(torneo.seNecesitaDesempate());
    }
    @Test
    void seNecesitaDesempatePorSegundoLugar() {
        j1.agregarPuntos(2);
        j2.agregarPuntos(1);
        j3.agregarPuntos(1);
        assertTrue(torneo.seNecesitaDesempate());
    }
    @Test
    void seNecesitaDesempatePorTercerLugar() {
        j1.agregarPuntos(1);
        j2.agregarPuntos(1);
        j3.agregarPuntos(1);
        assertTrue(torneo.seNecesitaDesempate());
        j1.agregarPuntos(2);
        j2.agregarPuntos(1);
        assertTrue(!torneo.seNecesitaDesempate());
    }

   //Para establecer ganadores veremos que siempre son no nulos independientes de la modalidad
    @Test
    void establecerGanadoresSistemaSuizo() throws OrdenarEnfrentamientosNoEjecutadoException {
        Torneo torneoSistemaSuizo= new TorneoIndividual(new SistemaSuizo(),TipoDePartida.CLASICA, TipoDePartida.BLITZ);
        torneoSistemaSuizo.agregarParticipante(j1);
        torneoSistemaSuizo.agregarParticipante(j2);
        torneoSistemaSuizo.agregarParticipante(j3);
        torneoSistemaSuizo.agregarParticipante(j4);
        torneoSistemaSuizo.actualizarNumeroMaximoRondas();
        torneoSistemaSuizo.ordenarEnfrentamientos();
        torneoSistemaSuizo.ejecutarRonda();
        torneoSistemaSuizo.ordenarEnfrentamientos();
        torneoSistemaSuizo.ejecutarRonda();
        torneoSistemaSuizo.establecerGanadores();
        assertNotNull(torneoSistemaSuizo.primerLugar);
        assertNotNull(torneoSistemaSuizo.segundoLugar);
        assertNotNull(torneoSistemaSuizo.tercerLugar);
    }
    @Test
    void establecerGanadoresTodosContraTodos() throws OrdenarEnfrentamientosNoEjecutadoException {
        Torneo torneoTodosContraTodos= new TorneoIndividual(new TodosContraTodos(),TipoDePartida.CLASICA, TipoDePartida.BLITZ);
        torneoTodosContraTodos.agregarParticipante(j1);
        torneoTodosContraTodos.agregarParticipante(j2);
        torneoTodosContraTodos.agregarParticipante(j3);
        torneoTodosContraTodos.agregarParticipante(j4);
        torneoTodosContraTodos.actualizarNumeroMaximoRondas();
        torneoTodosContraTodos.ordenarEnfrentamientos();
        torneoTodosContraTodos.ejecutarRonda();
        torneoTodosContraTodos.ordenarEnfrentamientos();
        torneoTodosContraTodos.ejecutarRonda();
        torneoTodosContraTodos.ordenarEnfrentamientos();
        torneoTodosContraTodos.ejecutarRonda();
        torneoTodosContraTodos.establecerGanadores();
        assertNotNull(torneoTodosContraTodos.primerLugar);
        assertNotNull(torneoTodosContraTodos.segundoLugar);
        assertNotNull(torneoTodosContraTodos.tercerLugar);
    }
    @Test
    void establecerGanadoresEliminacionDirecta() throws OrdenarEnfrentamientosNoEjecutadoException {
        Torneo torneoEliminacionDirecta= new TorneoEquipos(new EliminacionDirecta(),TipoDePartida.CLASICA, TipoDePartida.BLITZ);
        torneoEliminacionDirecta.agregarParticipante(e1);
        torneoEliminacionDirecta.agregarParticipante(e2);
        torneoEliminacionDirecta.agregarParticipante(e3);
        torneoEliminacionDirecta.agregarParticipante(e4);
        torneoEliminacionDirecta.actualizarNumeroMaximoRondas();
        torneoEliminacionDirecta.ordenarEnfrentamientos();
        torneoEliminacionDirecta.ejecutarRonda();
        torneoEliminacionDirecta.ordenarEnfrentamientos();
        torneoEliminacionDirecta.ejecutarRonda();
        torneoEliminacionDirecta.establecerGanadores();
        //eliminacion directa no necesita desempate
        assertNotNull(torneoEliminacionDirecta.primerLugar);
        assertNotNull(torneoEliminacionDirecta.segundoLugar);
        assertNotNull(torneoEliminacionDirecta.tercerLugar);
    }
}