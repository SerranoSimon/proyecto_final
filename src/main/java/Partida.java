public class Partida {
    protected Jugador blancas;
    protected Jugador negras;
    protected TipoDePartida tipoDePartida;
    protected Resultado resultado;

    public Partida(Jugador blancas, Jugador negras, TipoDePartida tipoDePartida){
        this.blancas=blancas;
        this.negras=negras;
        this.tipoDePartida=tipoDePartida;
    }
    public void jugar(){
        char r=ResultadoJuego.DrawResult(blancas.getELO(),negras.getELO());
        if(r=='w'){
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
