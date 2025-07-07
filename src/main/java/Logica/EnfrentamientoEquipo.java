package Logica;

/**
 * Clase que representa un enfrentamiento entre equipos
 */
public class EnfrentamientoEquipo implements Enfrentamiento {
private Equipo e1;
private Equipo e2;
private EnfrentamientoJugadores enf1;
private EnfrentamientoJugadores enf2;
private TipoDePartida normal;
private TipoDePartida desempate;
private Resultado resultado;
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
        while(puntosE1==puntosE2) {

            for (int i = 0; i < e1.getJugadores().size(); i++) {
                Participante j1 = e1.getJugadores().get(i);
                Participante j2 = e2.getJugadores().get(i);
                EnfrentamientoJugadores enf =
                        new EnfrentamientoJugadores(j1, j2, normal, desempate);
                if (i == 0) {
                    enf1 = enf;
                } else {
                    enf2 = enf;
                }
                enf.jugar();
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
            System.out.println("GANA EQUIPO: "+e1);
        }
        else{
            e2.agregarPuntos(2);
            resultado=Resultado.VICTORIA_P2;
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
}
