package Logica;

public class PartidaGenericaFactory implements PartidaFactory {

    @Override
    public Partida crearPartida(Jugador blancas, Jugador negras, TipoDePartida tipoDePartida) {
        return new Partida(blancas,negras, tipoDePartida);
    }
}
