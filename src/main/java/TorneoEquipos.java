import java.util.ArrayList;

public class TorneoEquipos extends Torneo{
    private static TorneoEquipos instancia;
    private TorneoEquipos(){
        participantes=new ArrayList<>();

    }
    public static TorneoEquipos getInstance(){
        if(instancia==null){
            instancia=new TorneoEquipos();
            return instancia;
        }
        return instancia;
    }

    @Override
    public void agregarParticipante(Participante participante) throws IllegalArgumentException {
        if(participante instanceof Equipo){
            participantes.add(participante);
        }
        else{
            throw new IllegalArgumentException("EL participante no es un equipo");
        }
    }
}


