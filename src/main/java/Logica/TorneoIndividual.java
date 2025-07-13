package Logica;

import java.sql.SQLOutput;
import java.util.ArrayList;

public class TorneoIndividual extends Torneo {
    public TorneoIndividual(ModalidadJuego modalidadJuego,TipoDePartida partidaNormal, TipoDePartida partidaDesempate){
        super(modalidadJuego,partidaNormal,partidaDesempate);


    }

    /**
     * al agregar los participantes se revisa que todos sean jugadores.
     * @param participante participante que se va a agregar.
     * @throws LimitesDeParticipantesException no pueden haber más de 6 participantes en un torneo
     * @throws TipoDeParticipanteException si un participante no es un jugador.
     */

    /**
     * Se contemplan los casos de empate, si empatan 2 se hace un enfrentamiento, de lo contrario se crea
     * un torneo individual de eliminacion directa para asegurarnos de ya no tener desempates.
     * @throws OrdenarEnfrentamientosNoEjecutadoException si no se ordenan los enfrentamientos, sin embargo
     * en el codigo del metodo ya se llama a ordenarEnfrentamientos().
     */
    @Override
    public void desempatar() throws OrdenarEnfrentamientosNoEjecutadoException {
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
        }
        //casos de empate 1

        if(porPrimerLugar.size()==1){
            primerLugar=porPrimerLugar.get(0);
        }
        else if(porPrimerLugar.size()==2){
            System.out.println("Enfrentamiento desempate");
            Enfrentamiento enf =factory.crearEnfrentamiento(porPrimerLugar.get(0), porPrimerLugar.get(1),
                    partidaNormal, partidaDesempate);
            enf.jugar();
            if(porSegundoLugar.size()==1){
                tercerLugar=porSegundoLugar.get(0);
            }
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
            Torneo desempate= new TorneoIndividual(new EliminacionDirecta(), partidaNormal, partidaDesempate);
            desempate.torneoDeDesempate=true;
            for(Participante p: porPrimerLugar){
               desempate.agregarParticipante(p);
            }
            desempate.iniciar();
            for(int i=0;i<desempate.numeroMaximoRondas;i++){
                desempate.ordenarEnfrentamientos();
                desempate.ejecutarRonda();
            }
            primerLugar= desempate.primerLugar;
            segundoLugar= desempate.segundoLugar;
            tercerLugar= desempate.tercerLugar;

        }

        //casos de empate 2
        if(segundoLugar==null && porSegundoLugar.size()==1){
            segundoLugar=porSegundoLugar.get(0);
        }
        else if(segundoLugar==null && tercerLugar==null && porSegundoLugar.size()==2){
            System.out.println("Enfrentamiento desempate");
            Enfrentamiento enf =factory.crearEnfrentamiento(porSegundoLugar.get(0), porSegundoLugar.get(1),
                    partidaNormal, partidaDesempate);
            enf.jugar();
            if(enf.getResultado()==Resultado.VICTORIA_P1){
               segundoLugar=porSegundoLugar.get(0);
               tercerLugar=porSegundoLugar.get(1);
            }
            else{
                segundoLugar=porSegundoLugar.get(1);
                tercerLugar=porSegundoLugar.get(0);
            }
        }
        else if(segundoLugar==null && tercerLugar==null && porSegundoLugar.size()>2){
            Torneo desempate= new TorneoIndividual(new EliminacionDirecta(), partidaNormal, partidaDesempate);
            desempate.torneoDeDesempate=true;
            for(Participante p: porSegundoLugar){
               desempate.agregarParticipante(p);
            }
            desempate.iniciar();
            for(int i=0;i<desempate.numeroMaximoRondas;i++){
                desempate.ordenarEnfrentamientos();
                desempate.ejecutarRonda();
            }
            segundoLugar= desempate.primerLugar;
            tercerLugar= desempate.segundoLugar;
        }
        // casos empate 3
        if(tercerLugar==null && porTercerLugar.size()==1){
            tercerLugar=porTercerLugar.get(0);
        } else if (tercerLugar==null && porTercerLugar.size()==2) {
            System.out.println("Enfrentamiento desempate");
            Enfrentamiento enf =factory.crearEnfrentamiento(porTercerLugar.get(0), porTercerLugar.get(1),
                    partidaNormal, partidaDesempate);
            enf.jugar();
            if(enf.getResultado()==Resultado.VICTORIA_P1){
              tercerLugar=porTercerLugar.get(0);
            }
            else{
               tercerLugar=porTercerLugar.get(1);
            }

        } else if (tercerLugar==null && porTercerLugar.size()>2) {
            Torneo desempate= new TorneoIndividual(new EliminacionDirecta(), partidaNormal, partidaDesempate);
            desempate.torneoDeDesempate=true;
            for(Participante p: porTercerLugar){
                desempate.agregarParticipante(p);
            }
            desempate.iniciar();
            for(int i=0;i<desempate.numeroMaximoRondas;i++){
                desempate.ordenarEnfrentamientos();
                desempate.ejecutarRonda();
            }
            tercerLugar= desempate.primerLugar;

        }
        System.out.println("Se ha desempatado");


    }

    @Override
    public String toString() {
        return "Torneo Individual "+modalidadJuego;
    }
}
