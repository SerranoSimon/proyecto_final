package Logica;

import java.util.ArrayList;
import java.util.Collections;

/**
 * Clase que reprensenta el sistema suizo, que es una modalidad de juego,
 * por eso implementa ModalidadJuego.
 */

public class SistemaSuizo implements ModalidadJuego {
    private boolean ordenado = false;
    public SistemaSuizo(){
    }

    /**
     * ordena los participantes solo una vez antes de cada ronda, si es la primera ronda
     * se ordena por ELO de forma descendente y en las proximas rondas se ordena en funcion del
     * puntaje del participante en el torneo de forma descendente.
     *
     * @param participantes participantes los cuales ordenar
     * @param numeroDeRonda numero de ronda para la cual se quiere ordenar
     */
    @Override
    public void ordenarParticipantes(ArrayList<Participante> participantes,
                                       int numeroDeRonda) {
        if(ordenado){
            throw new IllegalStateException("El método ordenarEnfrentamientos ya se ha ejecutado previamente.");
        }

        if(numeroDeRonda==1){
            Collections.sort(participantes);
        }
        else{
            participantes.sort(new ComparadorPorPuntos());
        }

    }

    /**
     * Metodo para obtener las parejas que se van a enfrentar, si la cantidad de participantes es impar
     * se agrega un fantasma a la copia de los participantes y usamos esta copia para organizar los enfrentamientos.
     * Los participantes con puntaje similar se enfrentan.
     * @param participantes participantes del torneo
     * @return ArrayList de enfrentamientos por realizar
     */
    @Override
    public ArrayList<ArrayList<Participante>> obtenerDistribucionEnfrentamientos(ArrayList<Participante> participantes) {
        ArrayList<ArrayList<Participante>> distribucion=new ArrayList<>();
        ArrayList<Participante> participantesCopia= (ArrayList<Participante>) participantes.clone(); //shallow copy
        if(participantes.size()%2!=0){
            distribucion.add(crearEnfrentamientoFantasma(participantesCopia)); //usamos la copia para agregar el fantasma
        }
        for(int i=0; i< participantesCopia.size();i+=2){
            ArrayList<Participante> arrTemp=new ArrayList<>();
            arrTemp.add(participantesCopia.get(i));
            arrTemp.add(participantesCopia.get(i+1));
            distribucion.add(arrTemp);
        }
       return distribucion;
    }

    /**
     * En este tipo de modalidad lo recomendado es usar log base 2 del numero de participantes
     * redondeado hacia arriba, usamos el cambio de base de los logaritmos.
     * @param numeroDeParticipantes numero de participantes del torneo
     * @return el numero de rondas que se deben jugar.
     */
    @Override
    public int numeroDeRondas(int numeroDeParticipantes) {
        return (int) Math.ceil(Math.log(numeroDeParticipantes) / Math.log(2));
    }

    /**
     * ordena los participantes para su visualizacion, ya que usa la lista original y no
     * la copia que podria contener fantasma.
     * @param participantes participantes del torneo
     * @param numeroDeRonda el numero de ronda de actual para ver el orden de la ronda siguiente (considerar que inicalmente
     *                      la ronda empieza en 0)
     */
    @Override
    public void ordenarParticipantesParaMostrar(ArrayList<Participante> participantes,
                                                int numeroDeRonda) {
        ordenarParticipantes(participantes, numeroDeRonda+1);
    }

    /**
     * Metodo que revisa los jugadores que no han descansado para asignarle un enfrentamiento fantasma,
     * se recorren los participantes de menor a mayor puntuacion para hacer pasar a las siguientes rondas los de menor puntuacion.
     * @param participantes cre
     * @return
     */
    public ArrayList<Participante> crearEnfrentamientoFantasma(ArrayList<Participante> participantes){
        ArrayList<Participante> enfFantasma= new ArrayList<>();
        for(int i=0;i< participantes.size();i++){
            if(!participantes.get(participantes.size()-1-i).getTuvoDescanso()){
                enfFantasma.add(new Fantasma());
                enfFantasma.add(participantes.get(participantes.size()-1-i));
                participantes.get(participantes.size()-1-i).setTuvoDescanso(true);
                participantes.remove(participantes.size()-1-i); //se remueve para poder establecer enfrentamientos normales con numero par,
                                                                      //nota: no desaparece del torneo puesto que al llamar al metodo obtenerDistribucion()
                                                                      //se vuelven a copiar los participantes.
                break;
            }
        }
        return enfFantasma;
    }
    @Override
    public String toString() {
        return "Sistema Suizo";
    }

}
