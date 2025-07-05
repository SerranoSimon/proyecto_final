package Logica;

import java.util.ArrayList;

public class TorneoEquipos extends Torneo {

    public TorneoEquipos(ModalidadJuego modalidadJuego,TipoDePartida partidaNormal, TipoDePartida partidaDesempate) {
        super(modalidadJuego,partidaNormal,partidaDesempate);

    }

    @Override
    public void agregarParticipante(Participante participante) throws LimitesDeParticipantesException,TipoDeParticipanteException {
        if(participantes.size()>6){
            throw new LimitesDeParticipantesException("No se pueden agregar más de 6 equipos al torneo");
        }
        if (participante instanceof Equipo) {
            participantes.add(participante);
        } else {
           throw new TipoDeParticipanteException("El participante debe ser un equipo");
        }
    }

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
            System.out.println("Torneo desempate");
            Torneo desempate= new TorneoEquipos(new EliminacionDirecta(), partidaNormal, partidaDesempate);
            for(Participante p: porPrimerLugar){
                desempate.solicitarInscripcion(p);
                desempate.aceptarSolicitud(p);
            }
            desempate.actualizarNumeroMaximoRondas();
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
            System.out.println("Torneo desempate");
            Torneo desempate= new TorneoEquipos(new EliminacionDirecta(), partidaNormal, partidaDesempate);
            for(Participante p: porSegundoLugar){
                desempate.solicitarInscripcion(p);
                desempate.aceptarSolicitud(p);
            }
            desempate.actualizarNumeroMaximoRondas();
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
            System.out.println("Torneo desempate");
            Torneo desempate= new TorneoEquipos(new EliminacionDirecta(), partidaNormal, partidaDesempate);
            for(Participante p: porTercerLugar){
                desempate.solicitarInscripcion(p);
                desempate.aceptarSolicitud(p);
            }
            desempate.actualizarNumeroMaximoRondas();
            for(int i=0;i<desempate.numeroMaximoRondas;i++){
                desempate.ordenarEnfrentamientos();
                desempate.ejecutarRonda();
            }
            tercerLugar= desempate.primerLugar;

        }


    }

}


