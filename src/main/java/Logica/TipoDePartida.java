package Logica;

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
