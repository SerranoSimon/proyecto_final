import java.util.ArrayList;

public class TorneoIndividual extends Torneo{
    private static TorneoIndividual instancia;
    private TorneoIndividual(){
        participantes=new ArrayList<>();

    }
    public static TorneoIndividual getInstance(){
        if(instancia==null){
            instancia=new TorneoIndividual();
            return instancia;
        }
        return instancia;
    }

    @Override
    public void agregarParticipante(Participante participante) throws IllegalArgumentException {
        if(participante instanceof Jugador){
            participantes.add(participante);
        }
        else{
            throw new IllegalArgumentException("EL participante no es un jugador");
        }
    }
}
