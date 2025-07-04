package Logica;

import java.util.ArrayList;

public class TorneoIndividual extends Torneo {
    public TorneoIndividual(ModalidadJuego modalidadJuego,TipoDePartida partidaNormal, TipoDePartida partidaDesempate){
        super(modalidadJuego,partidaNormal,partidaDesempate);


    }
    @Override
    public void agregarParticipante(Participante participante) {
        if (participante instanceof Jugador) {
            participantes.add(participante);
        } else {
            System.out.println("El participante debe ser un jugador.");
        }
    }

    @Override
    public void desempatar(){
        ArrayList<Participante> porPrimerLugar=new ArrayList<>();
        porPrimerLugar.add(participantes.get(0));
        ArrayList<Participante> porSegundoLugar=new ArrayList<>();
        ArrayList<Participante> porTercerLugar=new ArrayList<>();

        // Asignar los participantes a las listas
        int cont = 1;
        ArrayList<Participante> base = porPrimerLugar;
        for(int i=1;i<participantes.size();i++){
            if(participantes.get(i).getPuntos()<participantes.get(i-1).getPuntos()){
                cont++;
                if(cont==2){
                    base=porSegundoLugar;
                }
                if(cont==3){
                    base=porTercerLugar;
                }
            }
            base.add(participantes.get(i));
            System.out.println("Enfrentamientos desempate");
            //casos de empate

            if(porPrimerLugar.size()==1){
                primerLugar=porPrimerLugar.get(0);
            }
            else if(porPrimerLugar.size()==2){
                Enfrentamiento enf =factory.crearEnfrentamiento(porPrimerLugar.get(0), porPrimerLugar.get(1),
                        partidaNormal, partidaDesempate);
                if(enf.getResultado()==Resultado.VICTORIA_P1){
                    primerLugar=porPrimerLugar.get(0);
                    segundoLugar=porPrimerLugar.get(1);
                }
                else{
                    primerLugar=porPrimerLugar.get(1);
                    segundoLugar=porSegundoLugar.get(0);
                }

            }
            else{
                Torneo desempate= new TorneoIndividual(new TodosContraTodos(), partidaNormal, partidaDesempate);

            }

        }

        System.out.println(porPrimerLugar);
        System.out.println(porSegundoLugar);
        System.out.println(porTercerLugar);

    }

}
