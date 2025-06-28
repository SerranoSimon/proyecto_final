package Logica;

import java.sql.SQLOutput;

public class EnfrentamientoEquipo implements Enfrentamiento {
private Equipo e1;
private Equipo e2;
private TipoDePartida normal;
private TipoDePartida desempate;
    public EnfrentamientoEquipo(Equipo e1, Equipo e2, TipoDePartida normal, TipoDePartida desempate){
        this.e1=e1;
        this.e2=e2;
        this.normal=normal;
        this.desempate=normal;
    }

    @Override
    public void jugar() {
        System.out.println("ENFRENTAMIENTO DE EQUIPOS: "+e1.getNombre()+" v/s "+e2.getNombre());
        for(int i=0;i<e1.getJugadores().size();i++){
            Jugador j1=e1.getJugadores().get(i);
            Jugador j2=e2.getJugadores().get(i);
            EnfrentamientoJugadores enf=
                    new EnfrentamientoJugadores(j1, j2, normal, desempate);
            enf.jugar();

        }

    }
}
