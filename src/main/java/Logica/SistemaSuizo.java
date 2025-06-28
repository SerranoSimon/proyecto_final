package Logica;

import java.util.ArrayList;
import java.util.Collections;

public class SistemaSuizo implements ModalidadJuego {
    private TipoDePartida normal;
    private TipoDePartida desempate;
    private ArrayList<ArrayList<Participante>> distribucion;
    public SistemaSuizo(TipoDePartida normal, TipoDePartida desempate){
        this.normal=normal;
        this.desempate=desempate;
        this.distribucion=new ArrayList<>();
    }
    @Override
    public void ejuctarRondas(ArrayList<Participante> participantes) {

        EnfrentamientoFactory factory = new EnfrentamientoFactory();
        int i=1;
        if(i==1){
            Collections.sort(participantes);
        }
        else{
            ordenarEnfrentamientos(participantes);
        }
        while(i< distribucion.size()+1){
            System.out.println("Ronda "+i);
            ordenarEnfrentamientos(participantes);
            for(ArrayList<Participante> pareja: distribucion){
                factory.crearEnfrentamiento(pareja.getFirst(), pareja.getLast(), normal,desempate);
            }

            i+=1;
        }


    }

    @Override
    public void ordenarEnfrentamientos(ArrayList<Participante> participantes) {
        participantes.sort(new ComparadorPorPuntos());
        int n= participantes.size();
        distribucion=new ArrayList<>();
        for(int i=0; i<n;i+=2){
            ArrayList<Participante> arrTemp=new ArrayList<>();
            arrTemp.add(participantes.get(i));
            arrTemp.add(participantes.get(i+1));
            distribucion.add(arrTemp);
        }


    }

    public ArrayList<ArrayList<Participante>> getDistribucion() {
        return distribucion;
    }
}
