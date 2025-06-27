package Logica;

import java.util.ArrayList;
import java.util.Collections;

public class EliminacionDirecta implements ModalidadJuego {
    private TipoDePartida normal;
    private TipoDePartida desempate;
    private ArrayList<ArrayList<Participante>> distribucion;
    private ArrayList<Participante> activos;

    public EliminacionDirecta(TipoDePartida normal, TipoDePartida desempate){
        this.normal=normal;
        this.desempate=desempate;
        this.distribucion=new ArrayList<>();
        this.activos=new ArrayList<>();



    }
    public void ordenarEnfrentamientos(ArrayList<Participante> jugadores){
        int n= jugadores.size();
        distribucion=new ArrayList<>();
        for(int i=0;i<n/2;i++){
            ArrayList<Participante> arrTemp= new ArrayList<>();
            arrTemp.add(activos.get(i));
            arrTemp.add(activos.get(n/2+i));
            distribucion.add(arrTemp);
        }

    }




    @Override
    public void ejuctarRondas(ArrayList<Participante> participantes) {
        Collections.sort(participantes);
        activos = participantes;
        ordenarEnfrentamientos(activos);
        int ronda=1;
        System.out.println("Enfrentamientos: "+distribucion);
        while (distribucion.size()>0){
            System.out.println("Ronda: "+ronda);
            for(ArrayList<Participante> pareja: distribucion){
                Participante p1=pareja.getFirst();
                Participante p2= pareja.getLast();
                System.out.println(p1.getNombre()+"vs"+p2.getNombre());
               EnfrentamientoJugadores enf= new EnfrentamientoJugadores(p1, p2,
                       normal,desempate);
               enf.jugar();
               if(enf.getResultado()==Resultado.VICTORIA_P1){
                   activos.remove(p2);
                   System.out.println("gana"+p1.getNombre());
               }
               else{
                   activos.remove(p1);
                   System.out.println("gana"+p2.getNombre());
               }
                ordenarEnfrentamientos(activos);
            }
            ronda+=1;

        }
        System.out.println("Ganador del torneo: "+activos.getFirst().getNombre());

    }
}
