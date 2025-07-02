package Logica;

public class EnfrentamientoFantasma implements Enfrentamiento{

    public EnfrentamientoFantasma(Participante p1, Participante p2) {
        if(p1 instanceof Fantasma){
            p2.agregarPuntos(2);
            System.out.println(p2+"Descansa");
        }
        else{
            p1.agregarPuntos(2);
            System.out.println(p1+"Descansa");
        }
    }

    @Override
    public void jugar() {

    }

    @Override
    public Resultado getResultado() {
        return null;
    }
}
