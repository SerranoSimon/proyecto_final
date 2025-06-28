package Logica;

import java.sql.SQLOutput;

public class EnfrentamientoEquipo implements Enfrentamiento {

    public EnfrentamientoEquipo(Equipo e1, Equipo e2, TipoDePartida normal, TipoDePartida desempate){
         for(int i=0;i<e1.getJugadores().size();i++){
             Jugador j1=e1.getJugadores().get(i);
             Jugador j2=e2.getJugadores().get(i);
             System.out.println(j1.getNombre()+" vs "+j2.getNombre());
             EnfrentamientoJugadores enf=
                     new EnfrentamientoJugadores(j1, j2, normal, desempate);

             System.out.println("gana "+enf.getResultado());


         }
    }

    @Override
    public void jugar() {

    }
}
