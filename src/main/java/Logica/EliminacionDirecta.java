package Logica;

import java.util.ArrayList;
import java.util.Collections;

public class EliminacionDirecta implements ModalidadJuego {
    ArrayList<Participante> participantesCopia;
    public EliminacionDirecta(){

    }

    @Override
    public void ordenarParticipantes(ArrayList<Participante> participantes, int numeroDeRonda) {
        Collections.sort(participantes);
        participantesCopia=(ArrayList<Participante>) participantes.clone();
        if(participantes.size()%2!=0){
            participantesCopia.add(new Fantasma());
        }
    }

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

    @Override
    public int numeroDeRondas(int numeroDeParticipantes) {
        if(numeroDeParticipantes%2==0){
            return numeroDeParticipantes/2;
        }
        else{
            return (int) Math.ceil(Math.log(numeroDeParticipantes) / Math.log(2));
        }
    }

    @Override
    public void ordenarParticipantesParaMostrar(ArrayList<Participante> participantes, int numeroDeRonda) {
        Collections.sort(participantes);
    }
}
