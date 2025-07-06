package Logica;

/**
 * Enum que guarda las constantes de los tiempos de cada tipo de partida en minutos
 */
public enum TipoDePartida {
    BLITZ(3),
    RAPIDA(5),
    CLASICA(10);


    private final int minutos;
    TipoDePartida(int minutos){
        this.minutos=minutos;
    }

    public int getMinutos() {
        return minutos;
    }
}
