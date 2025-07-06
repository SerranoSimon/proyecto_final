package Logica;

/**
 * Clase que representa una partida de ajedrez
 */
public class Partida {
    protected Participante blancas;
    protected Participante negras;
    protected TipoDePartida tipoDePartida;
    protected Resultado resultado;

    /**
     * Constructor de la partida
     * @param blancas el participante que jugará con blancas
     * @param negras el participante que jugará con negras
     * @param tipoDePartida el tipo de partida (blitz, clasica, rapida)
     */
    public Partida(Participante blancas, Participante negras, TipoDePartida tipoDePartida){
        this.blancas=blancas;
        this.negras=negras;
        this.tipoDePartida=tipoDePartida;
    }

    /**
     * Usando la clase ResultadoJuego podemos, realistamete, determinar el resultado de la partida,
     * victoria_p1 para este metodo representa que el jugador con blancas gana.
     *
     */
    public void jugar(){
        char r= ResultadoJuego.drawResult(blancas.getELO(),negras.getELO());
        if(r=='w'){
            resultado= Resultado.VICTORIA_P1;
        }
        else if(r=='b'){
            resultado= Resultado.VICTORIA_P2;
        }
        else{
            resultado= Resultado.TABLAS;
        }
    }
    public Resultado getResultado(){
        return resultado;
    }
}
