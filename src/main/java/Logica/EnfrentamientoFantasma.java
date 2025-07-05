package Logica;

/**
 * Representa un enfrentamiento donde se deja libre al participante no fantasma
 */
public class EnfrentamientoFantasma implements Enfrentamiento{
Participante p1;
Participante p2;
    public EnfrentamientoFantasma(Participante p1, Participante p2) {
        this.p1=p1;
        this.p2=p2;
        if(p1 instanceof Fantasma){
            p2.agregarPuntos(2);
            System.out.println(p2+" Descansa");
        }
        else{
            p1.agregarPuntos(2);
            System.out.println(p1+" Descansa");
        }
    }

    @Override
    public void jugar() {
    }

    @Override
    public Resultado getResultado() {
        if(p1 instanceof Fantasma){
            return Resultado.VICTORIA_P2;
        }
        else{

            return Resultado.VICTORIA_P1;
        }
    }
}
