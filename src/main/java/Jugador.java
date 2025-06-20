public class Jugador implements Participante{
    private String nombre;
    private String apellido;
    private String correo;
    private int ELO;
    private int puntos;
    public Jugador(String nombre, String apellido, String correo, int ELO){
        this.nombre=nombre;
        this.apellido=apellido;
        this.correo=correo;
        this.ELO=ELO;

    }
    public void agregarPuntos(int puntos){
        this.puntos=+puntos;
    }
}
