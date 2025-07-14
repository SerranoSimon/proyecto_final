package visual;

import Logica.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Clase que genera jugadores variados para un torneo
 */
public class GeneradorJugadores {
    public GeneradorJugadores() {

    }

    /**
     * Metodo que genera jugadores
     * @return Lista de 24 jugadores con datos aleatorios
     */
    public ArrayList<Participante> generarJugadores() {
        String[] nombres = {"Juan", "Pedro", "Alejandro", "Carlos", "Ana", "Luis", "Patricia", "Diego",
                "Sofia", "Miguel", "Wladimir", "David", "Emanuel", "Fernando", "Simon",
                "Ricardo", "Isabel", "Andres", "Camila", "Gabriel","Baltazar","Jack","Antonia","Florencia", "Carmen"};
        ArrayList<String> nombresList= new ArrayList<>(List.of(nombres));
        String[] apellidos = {"Gomez", "Rodriguez", "Lopez", "Martinez", "Garcia", "Perez",
                "Sanchez", "Ramirez", "Torres", "Flores", "Diaz", "Hernandez",
                "Vargas", "Vidal", "Romero", "Suarez", "Alvarez", "Mendoza",
                "Silva", "Rojas","Leiva","Suazo","Henriquez","Molina","Herrera","Concha"};
        ArrayList<String> apellidosList= new ArrayList<>(List.of(apellidos));
        Random random = new Random();
        ArrayList<Participante> jugadores=new ArrayList<>();
        for (int i = 0; i<24; i++) {
            String nombre = nombresList.get(random.nextInt(nombresList.size()-1));
            String apellido = apellidosList.get(random.nextInt(apellidosList.size()-1));
            String correo = nombre.toLowerCase() + apellido.toLowerCase() + random.nextInt(100) + "@gmail.com";
            int ELO = 800 + random.nextInt(1200);
            jugadores.add(new Jugador(nombre, apellido, correo, ELO));
            nombresList.remove(nombre);
            apellidosList.remove(apellido);

        }
        return jugadores;
    }

    /**
     * Metodo para generar equipos de dos participantes(Jugadores en terminos practicos del torneo)
     * @return Lista de 12 equipos
     */
    public ArrayList<Participante> generarEquipos() {
        ArrayList<Participante> jugadoresEquipo = new ArrayList<>(generarJugadores());
        ArrayList<Participante> equipos=new ArrayList<>();
        for (int i = 0; i < jugadoresEquipo.size()-1; i += 2) {
            ArrayList<Participante> miembrosEquipo = new ArrayList<>();
            miembrosEquipo.add(jugadoresEquipo.get(i));
            miembrosEquipo.add(jugadoresEquipo.get(i+1));
            equipos.add(new Equipo("Equipo"+(i/2 + 1), miembrosEquipo));
        }
        return equipos;
    }
}