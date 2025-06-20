public class Partida {
    protected Participante blancas;
    protected Participante negras;
    protected TipoDePartida tipoDePartida;
    protected Resultado resultado;

    public Partida(Participante blancas, Participante negras, TipoDePartida tipoDePartida){
        this.blancas=blancas;
        this.negras=negras;
        this.tipoDePartida=tipoDePartida;
    }
    public void jugar(){

    }
    public Resultado getResultado(){
        return resultado;
    }
}
