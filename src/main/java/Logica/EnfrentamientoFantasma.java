package Logica;

/**
 * Representa un enfrentamiento donde se deja libre al participante no fantasma
 */
public class EnfrentamientoFantasma implements Enfrentamiento{
Participante p1;
Participante p2;
Participante ganador;
    public EnfrentamientoFantasma(Participante p1, Participante p2) {
        this.p1=p1;
        this.p2=p2;
        if(p1 instanceof Fantasma){
            p2.agregarPuntos(1);
            ganador=p2;
            System.out.println(p2+" Descansa");
        }
        else{
            p1.agregarPuntos(1);
            ganador=p1;
            System.out.println(p1+" Descansa");
        }
    }

    @Override
    public void jugar() {
    }

    @Override
    public Resultado getResultado() {
        if(p1 instanceof Fantasma){
            ganador=p2;
            return Resultado.VICTORIA_P2;
        }
        else{
            return Resultado.VICTORIA_P1;
        }
    }

    @Override
    public Participante getP1() {
        return p1;
    }

    @Override
    public Participante getP2() {
        return p2;
    }

    @Override
    public Participante getGanador() {
        return ganador;
    }

    @Override
    public int getTiempoPartidasJugadas() {
        return 0;
    }
}
