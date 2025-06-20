import org.junit.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Collections;

import static org.junit.jupiter.api.Assertions.*;

public class JugadorTest {
    ArrayList<Jugador> jugadores=new ArrayList<>();
    @BeforeEach
    void setUp() {
        Jugador j1=new Jugador("Benjamin","Poblete","benjaminp@gmail.com",400);
        Jugador j2= new Jugador("Scarlet","Valdebenito","scarletv@gmail.com",650);
        Jugador j3= new Jugador("Fernando","Saez","fernandos@gmail.com",2000);
        Jugador j4=new Jugador("Pilar","Oyarzun","pilaro@gmail.com",400);
        jugadores.add(j1);
        jugadores.add(j2);
        jugadores.add(j3);
        jugadores.add(j4);

    }

    @Test
    public void compareTo() {
        Collections.sort(jugadores);
        assertEquals(400,jugadores.get(3).getELO());
        assertEquals(400,jugadores.get(2).getELO());
        assertEquals(650,jugadores.get(1).getELO());
        assertEquals(2000,jugadores.get(0).getELO());

    }
}