package Logica;

import java.util.ArrayList;
import java.util.Collections;

public class TodosContraTodos implements ModalidadJuego {
    ArrayList<Participante> participantesCopia;
    public TodosContraTodos() {

    }

    @Override
    public void ordenarParticipantes(ArrayList<Participante> participantes, int numeroDeRonda) {
        if(numeroDeRonda==1){
            Collections.sort(participantes);
            participantesCopia=(ArrayList<Participante>) participantes.clone();
            if(participantes.size()%2!=0){
                participantesCopia.add(new Fantasma());
            }
        }
        else {
            //vamos a hacer una especie de rotacion
            ArrayList<Participante> arrayRotado=new ArrayList<>();
            int n=participantesCopia.size();
            ArrayList<Participante> primeraMitad=new ArrayList<>();
            ArrayList<Participante> segundaMitad=new ArrayList<>();
            for(int i=0;i<n;i++){
                if (i < n / 2) {
                    primeraMitad.add(participantesCopia.get(i));
                }
                else{
                    segundaMitad.add(participantesCopia.get(i));
                }
            }
            arrayRotado.add(primeraMitad.getFirst());
            arrayRotado.add(segundaMitad.getFirst());
            Participante ultimo= primeraMitad.getLast();
            for(int i=1;i<n/2-1;i++){
                arrayRotado.add(primeraMitad.get(i));

                System.out.println(arrayRotado);
            }
            for(int j=1;j<n/2;j++){
                arrayRotado.add(segundaMitad.get(j));
            }
            arrayRotado.add(ultimo);
            participantesCopia=arrayRotado;



        }
    }

    @Override
    public ArrayList<ArrayList<Participante>> obtenerDistribucionEnfrentamientos(ArrayList<Participante> participantes) {
        //no usamos participantes en este metodo para esta modalidad, sino su copia.
        ArrayList<ArrayList<Participante>> distribucion=new ArrayList<>();
        int n=participantesCopia.size();
        for(int j=0;j<n/2;j++){
            ArrayList<Participante> enfrentamiento=new ArrayList<>();
            enfrentamiento.add(participantesCopia.get(j));
            //enfrentamiento.add(participantesRonda.get(n-1-j));
            enfrentamiento.add(participantesCopia.get(j+n/2));
            distribucion.add(enfrentamiento);
        }

           return distribucion;

    }


    @Override
    public int numeroDeRondas(int numeroDeParticipantes) {
        if(numeroDeParticipantes%2==0){
            return numeroDeParticipantes-1;
        }
        else{
            return numeroDeParticipantes;
        }
    }

    @Override
    public void ordenarParticipantesParaMostrar(ArrayList<Participante> participantes, int numeroDeRonda) {
        if(numeroDeRonda==1){
            Collections.sort(participantes);
        }
        else{
            participantes.sort(new ComparadorPorPuntos());
        }
    }

}
