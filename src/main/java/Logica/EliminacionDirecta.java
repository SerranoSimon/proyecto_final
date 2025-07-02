package Logica;

import java.util.ArrayList;
import java.util.Collections;

public class EliminacionDirecta implements ModalidadJuego {

    public EliminacionDirecta(){
    }

    @Override
    public void ordenarParticipantes(ArrayList<Participante> participantes, int numeroDeRonda) {
        Collections.sort(participantes);
    }

    @Override
    public ArrayList<ArrayList<Participante>> obtenerDistribucionEnfrentamientos(ArrayList<Participante> participantes) {
        int n= participantes.size();
        ArrayList<ArrayList<Participante>> distribucion=new ArrayList<>();
        for(int i=0;i<n/2;i++){
            ArrayList<Participante> arrTemp= new ArrayList<>();
            arrTemp.add(participantes.get(i));
            arrTemp.add(participantes.get(n/2+i));
            distribucion.add(arrTemp);
        }

        return distribucion;
    }

    @Override
    public int numeroDeRondas(int numeroDeParticipantes) {
        return numeroDeParticipantes/2;
    }
}
