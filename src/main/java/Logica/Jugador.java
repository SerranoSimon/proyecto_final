package Logica;

/**
 * Clase que representa un jugador, contiene sus datos personales.
 *
 */
public class Jugador implements Participante, Comparable<Participante>{
    private String nombre;
    private String apellido;
    private String correo;
    private int ELO;
    private int puntos;
    private boolean tuvoDescanso;

    /**
     * Constructor que inicializa las variables con los datos proporcionados
     * @param nombre nombre del jugador
     * @param apellido apellido del jugador
     * @param correo el correo del jugador
     * @param ELO rating de ajedrez del jugador
     */
    public Jugador(String nombre, String apellido, String correo, int ELO){
        this.nombre=nombre;
        this.apellido=apellido;
        this.correo=correo;
        this.ELO=ELO;
        this.tuvoDescanso=false;
        this.puntos=0;

    }
    @Override
    public void setTuvoDescanso(boolean tuvoDescanso) {
        this.tuvoDescanso = tuvoDescanso;
    }
    @Override
    public boolean getTuvoDescanso() {
        return tuvoDescanso;
    }
    public void agregarPuntos(int puntos){
        this.puntos+=puntos;
    }
    public void quitarPuntos(int puntos){
        this.puntos-=puntos;
    }
    public int getPuntos(){
        return puntos;
    }
    public int getELO(){
        return ELO;
    }

    @Override
    public int compareTo(Participante j) {
        if(this.getELO()>j.getELO()){
            return -1;
        }
        else if(this.getELO()<j.getELO()){
            return 1;
        }
        else{
            return 0;
        }
    }

    public String getNombre() {
        return nombre;
    }

    @Override
    public String toString() {
        return nombre+": "+puntos;
    }
}
