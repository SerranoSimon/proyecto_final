package Logica;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class EnfrentamientoFactoryTest {
static Jugador j1;
static Jugador j2;
static Equipo e1;
static Equipo e2;
static Fantasma  f;
static EnfrentamientoFactory factory;

    @BeforeAll
    static void setUp() {
        j1=new Jugador("Benjamin","Poblete","benjaminp@gmail.com",1800);
        j2= new Jugador("Scarlet","Valdebenito","scarletv@gmail.com",1000);
        e1=new Equipo("e1",new ArrayList<>(List.of(j1)));
        e2=new Equipo("e1",new ArrayList<>(List.of(j2)));
        f=new Fantasma();
        factory=new EnfrentamientoFactory();

    }

    @Test
    void crearEnfrentamientoJugadores() {
        assertEquals(EnfrentamientoJugadores.class, factory.crearEnfrentamiento(j1,j2, TipoDePartida.RAPIDA,TipoDePartida.BLITZ).getClass());
    }
    @Test
    void crearEnfrentamientoEquipos() {
        assertEquals(EnfrentamientoEquipo.class, factory.crearEnfrentamiento(e1,e2, TipoDePartida.RAPIDA,TipoDePartida.BLITZ).getClass());
    }
    @Test
    void crearEnfrentamientoFantasmaJugadores1() {
        assertEquals(EnfrentamientoFantasma.class, factory.crearEnfrentamiento(j1,f, TipoDePartida.RAPIDA,TipoDePartida.BLITZ).getClass());
    }
    @Test
    void crearEnfrentamientoFantasmaJugadores2() {
        assertEquals(EnfrentamientoFantasma.class, factory.crearEnfrentamiento(f,j2, TipoDePartida.RAPIDA,TipoDePartida.BLITZ).getClass());
    }
    @Test
    void crearEnfrentamientoFantasmaEquipos1() {
        assertEquals(EnfrentamientoFantasma.class, factory.crearEnfrentamiento(f,e1, TipoDePartida.RAPIDA,TipoDePartida.BLITZ).getClass());
    }
    @Test
    void crearEnfrentamientoFantasmaEquipos2() {
        assertEquals(EnfrentamientoFantasma.class, factory.crearEnfrentamiento(e2,f, TipoDePartida.RAPIDA,TipoDePartida.BLITZ).getClass());
    }

}