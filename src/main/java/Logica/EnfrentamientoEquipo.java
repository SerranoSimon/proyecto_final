package Logica;

/**
 * Clase que representa un enfrentamiento entre equipos
 */
public class EnfrentamientoEquipo implements Enfrentamiento {
private Equipo e1;
private Equipo e2;
private Participante ganador;
private TipoDePartida normal;
private TipoDePartida desempate;
private Resultado resultado;
private int tiempoPartidasJugadas;
    public EnfrentamientoEquipo(Equipo e1, Equipo e2, TipoDePartida normal, TipoDePartida desempate){
        this.e1=e1;
        this.e2=e2;
        this.normal=normal;
        this.desempate=normal;
    }

    /**
     * Enfrenta los equipos hasta obtener un ganador
     */
    @Override
    public void jugar() {
        System.out.println("ENFRENTAMIENTO DE EQUIPOS: "+e1.getNombre()+" v/s "+e2.getNombre());
        int puntosE1=0;
        int puntosE2=0;
        int cont=0;
        while(puntosE1==puntosE2) {
            if(cont>0){
                System.out.println("Enfrentamiento desempate equipos");
            }

            for (int i = 0; i < e1.getJugadores().size(); i++) {
                Participante j1 = e1.getJugadores().get(i);
                Participante j2 = e2.getJugadores().get(i);
                EnfrentamientoJugadores enf =
                        new EnfrentamientoJugadores(j1, j2, normal, desempate);
                enf.jugar();
                tiempoPartidasJugadas+=enf.getTiempoPartidasJugadas();
                if (enf.getResultado() == Resultado.VICTORIA_P1) {
                    puntosE1 += 2;
                } else {
                    puntosE2 += 2;
                }
            }


        }
        if(puntosE1>puntosE2){
            e1.agregarPuntos(2);
            resultado=Resultado.VICTORIA_P1;
            ganador=e1;
            System.out.println("GANA EQUIPO: "+e1);
        }
        else{
            e2.agregarPuntos(2);
            resultado=Resultado.VICTORIA_P2;
            ganador=e2;
            System.out.println("GANA EQUIPO: "+e2);
        }



    }

    /**
     *
     * @return resultado del enfrentamiento
     */
    @Override
    public Resultado getResultado() {
        return resultado;
    }

    @Override
    public Participante getP1() {
        return e1;
    }

    @Override
    public Participante getP2() {
        return e2;
    }

    @Override
    public Participante getGanador() {
        return ganador;
    }
    @Override
    public int getTiempoPartidasJugadas() {
        return tiempoPartidasJugadas;
    }
}
