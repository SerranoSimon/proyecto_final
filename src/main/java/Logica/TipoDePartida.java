package Logica;

/**
 * Enum que guarda las constantes de los tiempos de cada tipo de partida en minutos
 */
public enum TipoDePartida {
    BLITZ(1),
    RAPIDA(3),
    CLASICA(5);


    private final int minutos;
    TipoDePartida(int minutos){
        this.minutos=minutos;
    }

    public int getMinutos() {
        return minutos;
    }
}
