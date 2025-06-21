package Logica;

import java.util.ArrayList;
import java.util.Collections;
import java.util.stream.Collectors;

public class EliminacionDirecta implements ModalidadJuego {
    private TipoDePartida normal;
    private TipoDePartida desempate;
    private PartidaFactory factory;
    private ArrayList<ArrayList<Jugador>> distribucion;
    private ArrayList<Jugador> activos;

    public EliminacionDirecta(TipoDePartida normal, TipoDePartida desempate, PartidaFactory factory){
        this.normal=normal;
        this.desempate=desempate;
        this.factory=factory;
        this.distribucion=new ArrayList<>();
        this.activos=new ArrayList<>();



    }
    public void ordenarEnfrentamientos(ArrayList<Jugador> jugadores){
        int n= jugadores.size();
        distribucion=new ArrayList<>();
        for(int i=0;i<n/2;i++){
            ArrayList<Jugador> arrTemp= new ArrayList<>();
            arrTemp.add(activos.get(i));
            arrTemp.add(activos.get(n/2+i));
            distribucion.add(arrTemp);
        }

    }






    @Override
    public void ejuctarRondas(ArrayList<Participante> participantes) {
        //casteo de participantes a jugadores para ordenarlos
        activos = participantes.stream()
                .map(participante -> (Jugador) participante)
                .sorted()
                .collect(Collectors.toCollection(ArrayList::new));
        Collections.sort(activos);
        ordenarEnfrentamientos(activos);
        int ronda=1;
        System.out.println("Enfrentamientos: "+distribucion);
        while (distribucion.size()>0){
            System.out.println("Ronda: "+ronda);
            for(ArrayList<Jugador> pareja: distribucion){
                Jugador p1=pareja.getFirst();
                Jugador p2= pareja.getLast();
                System.out.println(p1.getNombre()+"vs"+p2.getNombre());
               Enfrentamiento enf= new Enfrentamiento(p1, p2,
                       TipoDePartida.CLASICA,TipoDePartida.BLITZ, factory);
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
