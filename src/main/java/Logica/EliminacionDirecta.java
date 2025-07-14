package Logica;

import java.util.ArrayList;
import java.util.Collections;

/**
 * Clase que representa la modalidad de juego ELiminacion directa, posee una copia de los
 * participantes para operar su logica
 */
public class EliminacionDirecta implements ModalidadJuego {
    private ArrayList<Participante> participantesCopia;
    public EliminacionDirecta(){

    }

    /**
     * ordena los participantes para prepararlos en la siguiente ronda
     * Se agregan los fantasmas necesarios para volver potencia de 2 a la cantidad de participantes
     * @param participantes participantes a los cuales ordenar
     * @param numeroDeRonda ronda de la cual se quiere obtener el orden
     */
    @Override
    public void ordenarParticipantes(ArrayList<Participante> participantes, int numeroDeRonda) {
        Collections.sort(participantes);
        participantesCopia=(ArrayList<Participante>) participantes.clone();
        if(participantes.size()==5 ){
            participantesCopia.add(new Fantasma());
            participantesCopia.add(new Fantasma());
            participantesCopia.add(new Fantasma());
        }
        if(participantes.size()==6){
            participantesCopia.add(new Fantasma());
            participantesCopia.add(new Fantasma());
        }
        if(participantes.size()==7 || participantes.size()==3){
            participantesCopia.add(new Fantasma());
        }
    }

    /**
     * Clase que distribuye los enfrentamientos considerando que se ordenaron, enfrenta al mejor de la primera mitad
     * con el mejor de la segunda mitad.
     * @param participantes participantes los cuales se quiere hacer los enfrentamientos
     * @return ArrayList que tiene los enfrentamientos de cada ronda
     */
    @Override
    public ArrayList<ArrayList<Participante>> obtenerDistribucionEnfrentamientos(ArrayList<Participante> participantes) {
        int n= participantesCopia.size();
        ArrayList<ArrayList<Participante>> distribucion=new ArrayList<>();
        for(int i=0;i<n/2;i++){
            ArrayList<Participante> arrTemp= new ArrayList<>();
            arrTemp.add(participantesCopia.get(i));
            arrTemp.add(participantesCopia.get(n/2+i));
            distribucion.add(arrTemp);
        }

        return distribucion;
    }

    /**
     * Calcula el numero de rondas que se requiere para la modalidad Eliminacion Directa
     * @param numeroDeParticipantes el numero de participantes inicial del torneo
     * @return numero de rondas necesarias para llevar a cabo el torneo con la modalidad Eliminacion Directa
     */
    @Override
    public int numeroDeRondas(int numeroDeParticipantes) {
        if(numeroDeParticipantes==4|| numeroDeParticipantes==8){
            return (int) (Math.log(numeroDeParticipantes) / Math.log(2));
        }
        if(numeroDeParticipantes==3){
            return 2;
        }
        else{
            return 3;
        }
    }

    /**
     * Metodo para visualizar los participantes ordenados
     * @param participantes participantes del torneo
     * @param numeroDeRonda numero de ronda para saber cómo ordenarlos
     */
    @Override
    public void ordenarParticipantesParaMostrar(ArrayList<Participante> participantes, int numeroDeRonda) {
        Collections.sort(participantes);
    }

    public ArrayList<Participante> getParticipantesCopia() {
        return participantesCopia;
    }

    @Override
    public String toString() {
        return "EliminacionDirecta";
    }
}
