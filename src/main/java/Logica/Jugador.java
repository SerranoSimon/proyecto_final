package Logica;

public class Jugador implements Participante, Comparable<Participante>{
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

}
