package Logica;
public class EnfrentamientoFactory {

    public Enfrentamiento crearEnfrentamiento(Participante p1, Participante p2, TipoDePartida tiempoNormal, TipoDePartida tiempoDesempate) {
        if (p1 instanceof Jugador && p2 instanceof Jugador) {
            return new EnfrentamientoJugadores(p1, p2, tiempoNormal, tiempoDesempate);  // Crear enfrentamiento entre jugadores
        } else if (p1 instanceof Equipo && p2 instanceof Equipo) {
            return new EnfrentamientoEquipo((Equipo) p1, (Equipo) p2, tiempoNormal, tiempoDesempate);  // Crear enfrentamiento entre equipos
        }
        return  new EnfrentamientoFantasma(p1, p2);
    }
}

