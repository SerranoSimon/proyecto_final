package Logica;

public class EnfrentamientoJugadores implements Enfrentamiento{
    private Participante j1;
    private Participante j2;
    private TipoDePartida normal;
    private TipoDePartida desempate;
    private Resultado resultado;

    public EnfrentamientoJugadores(Participante j1, Participante j2, TipoDePartida normal, TipoDePartida desempate){
        this.j1=j1;
        this.j2=j2;
        this.normal=normal;
        this.desempate=desempate;
    }
    private int asignarPuntos(Resultado resultado, boolean esJ1){
        if(esJ1){
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
    public void jugar(){
        int puntosJ1=0;
        int puntosJ2=0;

        Partida p1=new Partida(j1,j2,normal);
        p1.jugar();
        puntosJ1+=asignarPuntos(p1.getResultado(),true);
        puntosJ2+=asignarPuntos(p1.getResultado(),false);

        Partida p2=new Partida(j2,j1,normal);
        p2.jugar();
        puntosJ1+=asignarPuntos(p2.getResultado(),true);
        puntosJ2+=asignarPuntos(p2.getResultado(),false);

        while (puntosJ1==puntosJ2){
            Partida pDesempate=new Partida(j1,j2,desempate);
            pDesempate.jugar();
            puntosJ1+=asignarPuntos(pDesempate.getResultado(),true);
            puntosJ2+=asignarPuntos(pDesempate.getResultado(),false);

        }
        if(puntosJ1>puntosJ2){
            resultado= Resultado.VICTORIA_P1;
            j1.agregarPuntos(2);
        }
        else{
            resultado= Resultado.VICTORIA_P2;
            j2.agregarPuntos(2);
        }
    }
    public Resultado getResultado(){
        return resultado;
    }
}
