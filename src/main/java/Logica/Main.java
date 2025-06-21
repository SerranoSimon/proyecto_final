package Logica;

import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {
        Jugador j1=new Jugador("Benjamin","Poblete","benjaminp@gmail.com",1000);
        Jugador j2= new Jugador("Scarlet","Valdebenito","scarletv@gmail.com",650);
        Jugador j3= new Jugador("Fernando","Saez","fernandos@gmail.com",900);
        Jugador j4=new Jugador("Pilar","Oyarzun","pilaro@gmail.com",800);
        PartidaFactory factory=new PartidaGenericaFactory();
        ModalidadJuego modalidadJuego=new EliminacionDirecta(TipoDePartida.CLASICA,TipoDePartida.BLITZ,factory);
        Torneo torneo= TorneoIndividual.getInstance();
        torneo.setModalidadJuego(modalidadJuego);
        torneo.agregarParticipante(j1);
        torneo.agregarParticipante(j2);
        torneo.agregarParticipante(j3);
        torneo.agregarParticipante(j4);
        torneo.iniciar();
    }
}
