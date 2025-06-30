package Logica;

import java.util.ArrayList;
import java.util.Collections;

public class TodosContraTodos implements ModalidadJuego {
    private TipoDePartida normal;
    private TipoDePartida desempate;
    private ArrayList<ArrayList<Participante>> distribucion;
    @Override
    public void ejuctarRondas(ArrayList<Participante> participantes) {
               
    }

    @Override
    public void ordenarEnfrentamientos(ArrayList<Participante> participantes, boolean esPrimeraRonda) {
        if(esPrimeraRonda){
            Collections.sort(participantes);
        }
        else{
            participantes.sort(new ComparadorPorPuntos());
        }
        int n= participantes.size();
        distribucion=new ArrayList<>();
        for(int i=0; i<n;i+=2){
            ArrayList<Participante> arrTemp=new ArrayList<>();
            arrTemp.add(participantes.get(i));
            arrTemp.add(participantes.get(i+1));
            distribucion.add(arrTemp);
        }
    }
}
