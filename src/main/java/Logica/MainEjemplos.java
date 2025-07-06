package Logica;

import java.util.ArrayList;
import java.util.List;

public class MainEjemplos {
    public static void main(String[] args) throws OrdenarEnfrentamientosNoEjecutadoException {

        Jugador j1=new Jugador("Benjamin","Poblete","benjaminp@gmail.com",1800);
        Jugador j2= new Jugador("Scarlet","Valdebenito","scarletv@gmail.com",1800);
        Jugador j3= new Jugador("Fernando","Saez","fernandos@gmail.com",1800);
        Jugador j4=new Jugador("Pilar","Oyarzun","pilaro@gmail.com",1800);
        Jugador j5=new Jugador("Marcelo","Leiva","marcelol@gmail.com",1800);
        Jugador j6=new Jugador("Rosita","Curihual","rositac@gmail.com",1800);
        Jugador j7=new Jugador("Vicente","Leal","vicentel@gmail.com",1820);
        Jugador j8=new Jugador("Patricio","Diaz","patriciod@gmail.com",2320);
        ArrayList<Participante> jugadores1=new ArrayList<>(List.of(j1, j2));
        ArrayList<Participante> jugadores2=new ArrayList<>(List.of(j3, j4));
        ArrayList<Participante> jugadores3=new ArrayList<>(List.of(j5, j6));
        ArrayList<Participante> jugadores4=new ArrayList<>(List.of(j7, j8));
        Equipo e1=new Equipo("Equipo 1",jugadores1);
        Equipo e2=new Equipo("Equipo 2",jugadores2);
        Equipo e3=new Equipo("Equipo 3",jugadores3);
        Equipo e4=new Equipo("Equipo 4",jugadores4);

        //EJEMPLO 1
        System.out.println("EJEMPLO 1");
        //creamos torneo
        Torneo torneo1= new TorneoEquipos(new EliminacionDirecta(), TipoDePartida.CLASICA, TipoDePartida.RAPIDA);
        System.out.println(torneo1);
        //solicamos e inscribimos equipos
        torneo1.solicitarInscripcion(e1);
        torneo1.solicitarInscripcion(e2);
        torneo1.solicitarInscripcion(e3);
        torneo1.solicitarInscripcion(e4);
        torneo1.aceptarSolicitud(e1);
        torneo1.aceptarSolicitud(e2);
        torneo1.aceptarSolicitud(e3);
        torneo1.aceptarSolicitud(e4);
        //ACTUALIZAR NUMERO DE RONDAS
        torneo1.iniciar();
        //ronda 1
        torneo1.verEstado(); //ver la tabla
        torneo1.ordenarEnfrentamientos();
        torneo1.ejecutarRonda();
        //ronda 2
        torneo1.verEstado();
        torneo1.ordenarEnfrentamientos();
        torneo1.ejecutarRonda();
        torneo1.verEstado();
        //se termina el torneo
        torneo1.establecerGanadores();
        torneo1.mostrarGanadores();
         //iniciamos de nuevo
         j1=new Jugador("Benjamin","Poblete","benjaminp@gmail.com",1800);
         j2= new Jugador("Scarlet","Valdebenito","scarletv@gmail.com",1800);
         j3= new Jugador("Fernando","Saez","fernandos@gmail.com",1800);
         j4=new Jugador("Pilar","Oyarzun","pilaro@gmail.com",1800);
         j5=new Jugador("Marcelo","Leiva","marcelol@gmail.com",1800);
         j6=new Jugador("Rosita","Curihual","rositac@gmail.com",1800);

        //EJEMPLO 2
        System.out.println("EJEMPLO 2");
        Torneo torneo2=new TorneoIndividual(new SistemaSuizo(), TipoDePartida.CLASICA, TipoDePartida.RAPIDA);
        System.out.println(torneo2);
        //ya mostramos como se inscriben asi que omitiremos esa parte agregandolos directamente.
        torneo2.agregarParticipante(j1);
        torneo2.agregarParticipante(j2);
        torneo2.agregarParticipante(j3);
        torneo2.agregarParticipante(j4);
        torneo2.agregarParticipante(j5);
        torneo2.agregarParticipante(j6);

        torneo2.iniciar();
         //ronda 1
        torneo2.verEstado();
        torneo2.ordenarEnfrentamientos();
        torneo2.ejecutarRonda();
        // ronda 2
        torneo2.verEstado();
        torneo2.ordenarEnfrentamientos();
        torneo2.ejecutarRonda();
        // ronda 3
        torneo2.verEstado();
        torneo2.ordenarEnfrentamientos();
        torneo2.ejecutarRonda();
        torneo2.verEstado();
        torneo2.establecerGanadores();
        torneo2.mostrarGanadores(); //como en eliminacion directa no importan los puntos, al definir los nuevos
                                    //lugares, no miramos el puntaje.

        //iniciamos de nuevo
        j1=new Jugador("Benjamin","Poblete","benjaminp@gmail.com",1800);
        j2= new Jugador("Scarlet","Valdebenito","scarletv@gmail.com",1800);
        j3= new Jugador("Fernando","Saez","fernandos@gmail.com",1800);
        j4=new Jugador("Pilar","Oyarzun","pilaro@gmail.com",1800);
        j5=new Jugador("Marcelo","Leiva","marcelol@gmail.com",1800);


        //EJEMPLO 3
        System.out.println("EJEMPLO 3");
        Torneo torneo3=new TorneoIndividual(new TodosContraTodos(),TipoDePartida.CLASICA, TipoDePartida.BLITZ);
        System.out.println(torneo3);
        //agregamos los participantes
        torneo3.agregarParticipante(j1);
        torneo3.agregarParticipante(j2);
        torneo3.agregarParticipante(j3);
        torneo3.agregarParticipante(j4);
        torneo3.agregarParticipante(j5);

        torneo3.iniciar();
        //ronda 1
        torneo3.verEstado();
        torneo3.ordenarEnfrentamientos();
        torneo3.ejecutarRonda();
        //ronda 2
        torneo3.verEstado();
        torneo3.ordenarEnfrentamientos();
        torneo3.ejecutarRonda();
        //ronda 3
        torneo3.verEstado();
        torneo3.ordenarEnfrentamientos();
        torneo3.ejecutarRonda();
        //ronda 4
        torneo3.verEstado();
        torneo3.ordenarEnfrentamientos();
        torneo3.ejecutarRonda();
        //ronda 5
        torneo3.verEstado();
        torneo3.ordenarEnfrentamientos();
        torneo3.ejecutarRonda();

        torneo3.verEstado();
        torneo3.establecerGanadores();
        torneo3.mostrarGanadores();

        //ejemplo donde empatan todos
        //iniciamos de nuevo
        j1=new Jugador("Benjamin","Poblete","benjaminp@gmail.com",1800);
        j2= new Jugador("Scarlet","Valdebenito","scarletv@gmail.com",1800);
        j3= new Jugador("Fernando","Saez","fernandos@gmail.com",1800);
        j4=new Jugador("Pilar","Oyarzun","pilaro@gmail.com",1800);
        j5=new Jugador("Marcelo","Leiva","marcelol@gmail.com",1800);
        Torneo torneo4=new TorneoIndividual(new TodosContraTodos(),TipoDePartida.CLASICA, TipoDePartida.BLITZ);
        System.out.println("ejemplo 4");
        torneo4.agregarParticipante(j1);
        torneo4.agregarParticipante(j2);
        torneo4.agregarParticipante(j3);
        torneo4.agregarParticipante(j4);
        torneo4.agregarParticipante(j5);
        torneo4.iniciar();
        j1.agregarPuntos(1);
        j2.agregarPuntos(1);
        j3.agregarPuntos(1);
        j4.agregarPuntos(1);
        j5.agregarPuntos(1);
        torneo4.numeroRonda=5;
        torneo4.verEstado();
        torneo4.establecerGanadores();
        torneo4.mostrarGanadores();





    }
}
