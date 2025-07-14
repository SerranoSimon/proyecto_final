package Logica;

/**
 * Clase que representa el enfrentamiento entre jugadores, implementando la interfaz Enfrentamiento
 */
public class EnfrentamientoJugadores implements Enfrentamiento{
    private Participante j1;
    private Participante j2;
    private Participante ganador;
    private TipoDePartida normal;
    private TipoDePartida desempate;
    private Resultado resultado;
    private int tiempoPartidasJugadas;

    public EnfrentamientoJugadores(Participante j1, Participante j2, TipoDePartida normal, TipoDePartida desempate){
        this.j1=j1;
        this.j2=j2;
        this.normal=normal;
        this.desempate=desempate;


    }

    /**
     * metodo que le asigna puntaje al jugador que ganó el enfrentamiento
     * @param resultado si fue tablas(empate), un jugador ganó o ganó el otro el enfrentamiento
     * @param juegaConBlancas booleano para saber a qué jugador asignarle puntos, en funcion de si es el que
     *                        juega con blancas o no.
     * @return 2 puntos si gana, 1 si empata y 0 si pierde.
     */
    private int asignarPuntos(Resultado resultado, boolean juegaConBlancas){
        if(juegaConBlancas){
            return switch (resultado){
                case Resultado.TABLAS -> 1;
                case Resultado.VICTORIA_P1 -> 2;
                case Resultado.VICTORIA_P2 -> 0;
            };
        }
        return switch (resultado){
            case Resultado.TABLAS -> 1;
            case Resultado.VICTORIA_P1 -> 0;
            case Resultado.VICTORIA_P2 -> 2;
        };
    }

    /**
     * Crea partidas, se alterna el que juega con blancas
     */
    public void jugar(){
        System.out.println("ENFRENTAMIENTO: "+j1.toString()+" v/s "+j2.toString());
        int puntosJ1=0;
        int puntosJ2=0;
        Partida p1=new Partida(j1,j2,normal);
        p1.jugar();
        tiempoPartidasJugadas+=normal.getMinutos();
        puntosJ1+=asignarPuntos(p1.getResultado(),true);
        puntosJ2+=asignarPuntos(p1.getResultado(),false);

        Partida p2=new Partida(j2,j1,normal);
        p2.jugar();
        tiempoPartidasJugadas+=normal.getMinutos();
        puntosJ1+=asignarPuntos(p2.getResultado(),false);
        puntosJ2+=asignarPuntos(p2.getResultado(),true);
        //Si empatan que vayan cambiando el lado (blancas o negras) hasta salir del desempate
        boolean a=true;
        boolean b=false;
        while (puntosJ1==puntosJ2){
            System.out.println("Partidas de desempate");
            if(a) {

                Partida pDesempate = new Partida(j1, j2, desempate);
                b=true;
                a=false;
                pDesempate.jugar();
                tiempoPartidasJugadas+=desempate.getMinutos();
                puntosJ1+=asignarPuntos(pDesempate.getResultado(),true);
                puntosJ2+=asignarPuntos(pDesempate.getResultado(),false);
            }
            if(b){
                Partida pDesempate = new Partida(j2, j1, desempate);
                b=false;
                a=true;
                pDesempate.jugar();
                tiempoPartidasJugadas+=desempate.getMinutos();
                puntosJ1+=asignarPuntos(pDesempate.getResultado(),false);
                puntosJ2+=asignarPuntos(pDesempate.getResultado(),true);
            }

        }
        if(puntosJ1>puntosJ2){
            resultado= Resultado.VICTORIA_P1;
            j1.agregarPuntos(2);
            ganador=j1;
            System.out.println("GANA EL ENFRENTAMIENTO: "+j1.toString());

        }
        else{
            resultado= Resultado.VICTORIA_P2;
            j2.agregarPuntos(2);
            ganador=j2;
            System.out.println("GANA EL ENFRENTAMIENTO: "+j2.toString());
        }
    }
    @Override
    public int getTiempoPartidasJugadas() {
        return tiempoPartidasJugadas;
    }



    @Override
    public Resultado getResultado(){
        return resultado;
    }

    @Override
    public Participante getP1() {
        return j1;
    }

    @Override
    public Participante getP2() {
        return j2;
    }
    @Override
    public Participante getGanador() {
        return ganador;
    }
}
