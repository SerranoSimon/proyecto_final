package Logica;

import java.util.ArrayList;
import java.util.Collections;

public class SistemaSuizo implements ModalidadJuego {
    public SistemaSuizo(){
    }
    @Override
    public void ordenarParticipantes(ArrayList<Participante> participantes,
                                       int numeroDeRonda) {
        if(numeroDeRonda==1){
            Collections.sort(participantes);
        }
        else{
            participantes.sort(new ComparadorPorPuntos());
        }

    }

    @Override
    public ArrayList<ArrayList<Participante>> obtenerDistribucionEnfrentamientos(ArrayList<Participante> participantes) {
        ArrayList<ArrayList<Participante>> distribucion=new ArrayList<>();
        ArrayList<Participante> participantesCopia= (ArrayList<Participante>) participantes.clone(); //shallow copy
        if(participantes.size()%2!=0){
            distribucion.add(crearEnfrentamientoFantasma(participantesCopia));
        }
        for(int i=0; i< participantesCopia.size();i+=2){
            ArrayList<Participante> arrTemp=new ArrayList<>();
            arrTemp.add(participantesCopia.get(i));
            arrTemp.add(participantesCopia.get(i+1));
            distribucion.add(arrTemp);
        }
       return distribucion;
    }

    @Override
    public int numeroDeRondas(int numeroDeParticipantes) {
        return (int) Math.ceil(Math.log(numeroDeParticipantes) / Math.log(2));
    }

    @Override
    public void ordenarParticipantesParaMostrar(ArrayList<Participante> participantes,
                                                int numeroDeRonda) {
        ordenarParticipantes(participantes, numeroDeRonda);
    }

    public ArrayList<Participante> crearEnfrentamientoFantasma(ArrayList<Participante> participantes){
        ArrayList<Participante> enfFantasma= new ArrayList<>();
        for(int i=0;i< participantes.size();i++){
            if(!participantes.get(participantes.size()-1-i).getTuvoDescanso()){
                enfFantasma.add(new Fantasma());
                enfFantasma.add(participantes.get(participantes.size()-1-i));
                participantes.get(participantes.size()-1-i).setTuvoDescanso(true);
                participantes.remove(participantes.size()-1-i);
                break;


            }
        }
        return enfFantasma;
    }

}
