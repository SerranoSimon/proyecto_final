public class Enfrentamiento {
    private Jugador j1;
    private Jugador j2;
    private TipoDePartida normal;
    private TipoDePartida desempate;
    private PartidaGenericaFactory factory;
    private Resultado resultado;

    public Enfrentamiento(Jugador j1, Jugador j2,
                          TipoDePartida normal, TipoDePartida desempate, PartidaGenericaFactory factory){
        this.j1=j1;
        this.j2=j2;
        this.normal=normal;
        this.desempate=desempate;
        this.factory=factory;
    }
    private int asignarPuntos(Resultado resultado,boolean esJ1){
        if(esJ1){
            return switch (resultado){
                case EMPATE -> 1;
                case VICTORIA_P1 -> 2;
                case VICTORIA_P2 -> 0;
            };
        }
        return switch (resultado){
            case EMPATE -> 1;
            case VICTORIA_P1 -> 0;
            case VICTORIA_P2 -> 2;
        };
    }
    public void jugar(){
        int puntosJ1=0;
        int puntosJ2=0;

        Partida p1=factory.crearPartida(j1,j2,normal);
        p1.jugar();
        puntosJ1+=asignarPuntos(p1.getResultado(),true);
        puntosJ2+=asignarPuntos(p1.getResultado(),false);

        Partida p2=factory.crearPartida(j2,j1,normal);
        p2.jugar();
        puntosJ1+=asignarPuntos(p2.getResultado(),true);
        puntosJ2+=asignarPuntos(p2.getResultado(),false);

        if(puntosJ1==puntosJ2){
            Partida pDesempate=factory.crearPartida(j1,j2,desempate);
            pDesempate.jugar();
            puntosJ1+=asignarPuntos(pDesempate.getResultado(),true);
            puntosJ2+=asignarPuntos(pDesempate.getResultado(),false);

        }
        if(puntosJ1>puntosJ2){
            resultado=Resultado.VICTORIA_P1;
        }
        else{
            resultado=Resultado.VICTORIA_P2;
        }
    }
    public Resultado getResultado(){
        return resultado;
    }
}
