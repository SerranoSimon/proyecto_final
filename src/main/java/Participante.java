public interface Participante {
    default int getPuntos(){
        return 0;
    }
    default void agregarPuntos(int puntos){};
}
