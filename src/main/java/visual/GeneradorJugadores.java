package visual;

import Logica.*;
import java.util.ArrayList;
import java.util.Random;

public class GeneradorJugadores {
    private ArrayList<Jugador> jugadores;
    private ArrayList<Equipo> equipos;

    public GeneradorJugadores() {
        this.jugadores = new ArrayList<>();
        this.equipos = new ArrayList<>();
        generarJugadores();
    }
    private void generarJugadores() {
        String[] nombres = {"Juan", "Pedro", "Alejandro", "Carlos", "Ana", "Luis", "Patricia", "Diego",
                "Sofía", "Miguel", "Wladimir", "David", "Emanuel", "Fernando", "Simón",
                "Ricardo", "Isabel", "Andrés", "Camila", "Gabriel"};
        String[] apellidos = {"Gómez", "Rodríguez", "López", "Martínez", "García", "Pérez",
                "Sánchez", "Ramírez", "Torres", "Flores", "Díaz", "Hernández",
                "Vargas", "Vidal", "Romero", "Suárez", "Álvarez", "Mendoza",
                "Silva", "Rojas"};
        Random random = new Random();
        for (int i = 0; i < 20; i++) {
            String nombre = nombres[random.nextInt(nombres.length)];
            String apellido = apellidos[random.nextInt(apellidos.length)];
            String correo = nombre.toLowerCase() + apellido.toLowerCase() + random.nextInt(100) + "@gmail.com";
            int ELO = 800 + random.nextInt(1200);
            jugadores.add(new Jugador(nombre, apellido, correo, ELO));
        }
    }
    public ArrayList<Jugador> seleccionarPostulantes() {
        ArrayList<Jugador> Jugadores = new ArrayList<>(jugadores);
        ArrayList<Jugador> seleccionados = new ArrayList<>();
        int cantidad = Math.min(10, Jugadores.size());
        for (int i = 0; i < cantidad; i++) {
            seleccionados.add(Jugadores.get(i));
        }
        return seleccionados;
    }
    public ArrayList<Equipo> generarEquipos() {
        equipos.clear();
        ArrayList<Jugador> jugadoresEquipo = new ArrayList<>(jugadores);

        for (int i = 0; i < jugadoresEquipo.size() - 1; i += 2) {
            ArrayList<Participante> miembrosEquipo = new ArrayList<>();
            miembrosEquipo.add(jugadoresEquipo.get(i));
            miembrosEquipo.add(jugadoresEquipo.get(i+1));

            equipos.add(new Equipo("Equipo " + (i/2 + 1), miembrosEquipo));
        }
        return new ArrayList<>(equipos);
    }
}