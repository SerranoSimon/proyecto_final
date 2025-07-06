package Logica;

import java.util.ArrayList;
import java.util.Collections;

/**
 * Clase que representa la modalidad Todos contra todos, implementa ModalidadJuego.
 */
public class TodosContraTodos implements ModalidadJuego {
    private ArrayList<Participante> participantesCopia;
    public TodosContraTodos() {

    }
    /**
     * ordena los participantes solo una vez antes de cada ronda, si es la primera ronda.
     * Para las rondas posteriores lo que se hace es una especie de rotacion de la copia de la lista
     * de participantes(puede incluir fantasma)
     * ej. [a,b,c,d] lo vemos como
     *      a b       a c       a d
     *      c d  ->    d b  ->  b c

     * @param participantes participantes los cuales ordenar
     * @param numeroDeRonda numero de ronda para la cual se quiere ordenar
     */
    @Override
    public void ordenarParticipantes(ArrayList<Participante> participantes, int numeroDeRonda) {
        if(numeroDeRonda==1){
            Collections.sort(participantes);
            participantesCopia=(ArrayList<Participante>) participantes.clone();
            if(participantes.size()%2!=0){
                participantesCopia.add(new Fantasma());
            }
        }
        else {
            //rotacion
            ArrayList<Participante> arrayRotado=new ArrayList<>();
            int n=participantesCopia.size();
            ArrayList<Participante> primeraMitad=new ArrayList<>();
            ArrayList<Participante> segundaMitad=new ArrayList<>();
            for(int i=0;i<n;i++){
                if (i < n / 2) {
                    primeraMitad.add(participantesCopia.get(i));
                }
                else{
                    segundaMitad.add(participantesCopia.get(i));
                }
            }
            arrayRotado.add(primeraMitad.getFirst());
            arrayRotado.add(segundaMitad.getFirst());
            Participante ultimo= primeraMitad.getLast();
            for(int i=1;i<n/2-1;i++){
                arrayRotado.add(primeraMitad.get(i));

            }
            for(int j=1;j<n/2;j++){
                arrayRotado.add(segundaMitad.get(j));
            }
            arrayRotado.add(ultimo);
            participantesCopia=arrayRotado;



        }
    }

    /**
     * Para ordenar los enfrentamientos se enfrenta el primero de la mitad superior con el primero de la mitad inferior.
     * Si volvemos a nuestro esquema con el ej. [a,b,c,d]
     *                                           a b
     *                                           c d
     * se enfrenta el de abajo con el de arriba teniendo la distribucion como [[a,c],[b,d]]
     * @param participantes participantes ya ordenados los cuales enfrentar .
     * @return Arraylist de enfrentamientos.
     */
    @Override
    public ArrayList<ArrayList<Participante>> obtenerDistribucionEnfrentamientos (ArrayList<Participante> participantes)  {

        //no usamos participantes en este metodo para esta modalidad, sino su copia.
        ArrayList<ArrayList<Participante>> distribucion=new ArrayList<>();
        int n=participantesCopia.size();
        for(int j=0;j<n/2;j++){
            ArrayList<Participante> enfrentamiento=new ArrayList<>();
            enfrentamiento.add(participantesCopia.get(j));
            enfrentamiento.add(participantesCopia.get(j+n/2));
            distribucion.add(enfrentamiento);
        }

           return distribucion;

    }

    /**
     * Si el numero de participantes(n) es par, se necesitan n-1 rondas, si es impar
     * se agrega un fantasma quedando (n-1)+1=n rondas.
     * @param numeroDeParticipantes numero de participantes del torneo.
     * @return numero de rondas necesarias para llevar a cabo el torneo.
     */
    @Override
    public int numeroDeRondas(int numeroDeParticipantes) {
        if(numeroDeParticipantes%2==0){
            return numeroDeParticipantes-1;
        }
        else{
            return numeroDeParticipantes;
        }
    }

    @Override
    public void ordenarParticipantesParaMostrar(ArrayList<Participante> participantes, int numeroDeRonda) {
        if(numeroDeRonda==1){
            Collections.sort(participantes);
        }
        else{
            participantes.sort(new ComparadorPorPuntos());
        }
    }

    public ArrayList<Participante> getParticipantesCopia() {
        return participantesCopia;
    }
    @Override
    public String toString() {
        return "Todos contra todos";
    }



}
