package Logica;
public class EnfrentamientoFactory {

    public Enfrentamiento crearEnfrentamiento(Participante p1, Participante p2, TipoDePartida tiempoNormal, TipoDePartida tiempoDesempate) {
        if (p1 instanceof Equipo && p2 instanceof Equipo) {
            return new EnfrentamientoEquipo((Equipo) p1, (Equipo) p2, tiempoNormal, tiempoDesempate);  // Crear enfrentamiento entre equipos
        } else {
            return new EnfrentamientoJugadores((Jugador) p1, (Jugador) p2, tiempoNormal, tiempoDesempate);  // Crear enfrentamiento entre jugadores
        }
    }
}

