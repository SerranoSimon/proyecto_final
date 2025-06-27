package Logica;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ResultadoJuegoTest {
    private Jugador j1;
    private Jugador j2;
    private Jugador j3;
    private Jugador j4;


    @BeforeEach
    void setUp() {
         j1=new Jugador("Benjamin","Poblete","benjaminp@gmail.com",1000);
         j2= new Jugador("Scarlet","Valdebenito","scarletv@gmail.com",1000);
         j3= new Jugador("Fernando","Saez","fernandos@gmail.com",900);
         j4=new Jugador("Pilar","Oyarzun","pilaro@gmail.com",500);

    }
    //preguntar
    @Test
    void drawResult() {
        assertAll(
                () -> assertEquals('=', ResultadoJuego.drawResult(j1.getELO(), j2.getELO())),
                () -> assertEquals('=', ResultadoJuego.drawResult(j2.getELO(), j1.getELO())),
                () -> assertEquals('w', ResultadoJuego.drawResult(j2.getELO(), j3.getELO())),
                () -> assertEquals('b', ResultadoJuego.drawResult(j3.getELO(), j2.getELO())),
                () -> assertEquals('w', ResultadoJuego.drawResult(j3.getELO(), j4.getELO())),
                () -> assertEquals('b', ResultadoJuego.drawResult(j4.getELO(), j1.getELO()))
        );
    }
}