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
        int n= participantes.size();
        int numeroRondas = (int) Math.ceil(Math.log(n) / Math.log(2));
        while(i< numeroRondas+1){
            System.out.println("Ronda "+i);
             if(i==1){
                 ordenarEnfrentamientos(participantes,true);
             }
             else{
                 ordenarEnfrentamientos(participantes,false);
             }

            for(ArrayList<Participante> pareja: distribucion){
                Enfrentamiento enf=factory.crearEnfrentamiento(pareja.getFirst(), pareja.getLast(), normal,desempate);
                enf.jugar();
            }

            i+=1;
        }

        participantes.sort(new ComparadorPorPuntos());



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

    public ArrayList<ArrayList<Participante>> getDistribucion() {
        return distribucion;
    }
}
