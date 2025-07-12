package Logica;

import java.util.ArrayList;

/**
 * Clase abstracta que moldea un torneo de ajedrez.
 */
public abstract class  Torneo {
    protected String fecha;
    protected String lugar;
    protected ArrayList<Participante> participantes;
    protected ArrayList<Participante> solicitudesInscripcion;
    protected ArrayList<ArrayList<Participante>> distribucion;
    private ArrayList<Enfrentamiento> enfrentamientosJugadosPorRonda;
    protected int numeroRonda;
    protected int numeroMaximoRondas;
    protected EnfrentamientoFactory factory;
    protected ModalidadJuego modalidadJuego;
    protected TipoDePartida partidaNormal;
    protected TipoDePartida partidaDesempate;
    protected ArrayList<Participante> disputaTercerLugar;
    protected Participante primerLugar;
    protected Participante segundoLugar;
    protected Participante tercerLugar;
    protected boolean ordenado;
    protected boolean torneoDeDesempate;

    /**
     * Constructor que inicializa las variables del torneo
     * @param modalidadJuego modalidad de juego del torneo(Sistema suizo, Eliminacion Directa, Todos contra Todos)
     * @param partidaNormal la partida de tiempo normal
     * @param partidaDesempate la partida con tiempo de desempate (usalmente es una partida con menos tiempo que la normal)
     */
   public Torneo(ModalidadJuego modalidadJuego,TipoDePartida partidaNormal, TipoDePartida partidaDesempate){
       this.solicitudesInscripcion=new ArrayList<>();
       this.participantes=new ArrayList<>();
       this.distribucion=new ArrayList<>();
       this.enfrentamientosJugadosPorRonda=new ArrayList<>();
       this.modalidadJuego=modalidadJuego;
       this.partidaNormal=partidaNormal;
       this.partidaDesempate=partidaDesempate;
       this.factory = new EnfrentamientoFactory();
       this.numeroRonda=0;
       this.disputaTercerLugar=new ArrayList<>();
       this.ordenado=false;
       this.torneoDeDesempate=false;

   }


    /**
     * Da la bienvenida al torneo y determina el numero maximo de jugadores.
     */
    public void iniciar() {
        if (!torneoDeDesempate) {
            System.out.println("Bienvenidos al torneo ");
            if (participantes.size() < 4) { //revisamos si podemos inicar el torneo
                throw new LimitesDeParticipantesException("El minimo de participantes para iniciar son 4");
            }
        }
        else {
            System.out.println("Torneo de desempate ");
        }
        numeroMaximoRondas = modalidadJuego.numeroDeRondas(participantes.size());
    }

    /**
     * prepara los enfrentamientos para una futura ronda
     * @throws LimiteDeRondasSuperadoException si se quiere preparar una ronda futura cuando ya se jugó la ultima.
     */
    public void ordenarEnfrentamientos() throws EnfrentamientosYaOrdenadosException, LimiteDeRondasSuperadoException{
       if(numeroRonda==numeroMaximoRondas){
           throw new LimiteDeRondasSuperadoException("Las rondas han acabado");
       }
       if(!ordenado){
           ordenado=true;
           modalidadJuego.ordenarParticipantes(participantes,numeroRonda+1);
           distribucion=modalidadJuego.obtenerDistribucionEnfrentamientos(participantes);}
       else{
           throw new EnfrentamientosYaOrdenadosException("ya ha sido ordenado");
       }
    }

    /**
     * Metodo para obtener los futuros enfrentamientos
     * @return un arraylist que contiene los enfrentamientos (los cuales son arraylist de participantes).
     * @throws OrdenarEnfrentamientosNoEjecutadoException si se desea obtener los proximos enfrentamientos sin ordenar.
     */
    public ArrayList<ArrayList<Participante>> obtenerProximosEnfrentamientos() throws OrdenarEnfrentamientosNoEjecutadoException {
        if(ordenado){
            return distribucion;
        }
        else{
            throw new OrdenarEnfrentamientosNoEjecutadoException("No has ordenado los enfrentamientos");
        }
    }

    /**
     * metodo para agregar participantes al torneo
     * @param participante participante que se va a agregar
     * @throws LimiteDeRondasSuperadoException
     */
    public abstract void agregarParticipante(Participante participante) throws LimitesDeParticipantesException;

    /**
     * Metodo para ejecutar una ronda de enfrentamientos
     * @throws LimiteDeRondasSuperadoException si se quiere ejecutar más rondas de las establecidas
     * @throws OrdenarEnfrentamientosNoEjecutadoException se quiere ejecutar la ronda sin haber ordenado previamente
     * @throws LimitesDeParticipantesException si se quiere inicar el torneo sin los participantes suficientes
     */
    public void ejecutarRonda() throws LimiteDeRondasSuperadoException, OrdenarEnfrentamientosNoEjecutadoException, LimitesDeParticipantesException{
        if(ordenado){
            ordenado=false;
            numeroRonda+=1;
            System.out.println("RONDA: " +
                    "" + numeroRonda);
            enfrentamientosJugadosPorRonda=new ArrayList<>();
            for (ArrayList<Participante> pareja : distribucion) {
                Enfrentamiento enf = factory.crearEnfrentamiento(pareja.getFirst(), pareja.getLast(), partidaNormal, partidaDesempate);
                enf.jugar();
                enfrentamientosJugadosPorRonda.add(enf);
                if (modalidadJuego instanceof EliminacionDirecta) {
                    if (enf.getResultado() == Resultado.VICTORIA_P1) {
                        participantes.remove(pareja.getLast());
                        if (numeroRonda == numeroMaximoRondas - 1) {
                            disputaTercerLugar.add(pareja.getLast()); //Como gana el 1ero, este es el
                                                                        // jugador que perdio y va por el 3er lugar
                        }
                        if (numeroRonda == numeroMaximoRondas) {
                            primerLugar = pareja.getFirst();
                            segundoLugar = pareja.getLast();
                        }
                    } else {
                        participantes.remove(pareja.getFirst());
                        if (numeroRonda == numeroMaximoRondas - 1) {
                            disputaTercerLugar.add(pareja.getFirst()); //Como gana el 2do, este es el
                                                                       // jugador que perdio y va por el 3er lugar
                        }
                        if (numeroRonda == numeroMaximoRondas) {
                            primerLugar = pareja.getLast();
                            segundoLugar = pareja.getFirst();
                        }
                    }
                }

            if (numeroRonda == numeroMaximoRondas && modalidadJuego instanceof EliminacionDirecta) {
                System.out.println("Disputa tercer lugar eliminacion directa");
                Enfrentamiento enfTercerLugar = factory.crearEnfrentamiento(disputaTercerLugar.getFirst(), disputaTercerLugar.getLast(), partidaNormal, partidaDesempate);
                enfTercerLugar.jugar();
                enfrentamientosJugadosPorRonda.add(enfTercerLugar);
                if (enfTercerLugar.getResultado() == Resultado.VICTORIA_P1) {
                    tercerLugar = disputaTercerLugar.getFirst();
                } else {
                    tercerLugar = disputaTercerLugar.getLast();
                }

            }
        }



        }
        else{
            throw new OrdenarEnfrentamientosNoEjecutadoException("No se han ordenado los enfrentamientos");
        }
    }

    /**
     * Metodo para ver el estado actual del torneo( "tabla" de posiciones de los participantes)
     */
    public ArrayList<Participante> verEstado(){
        modalidadJuego.ordenarParticipantesParaMostrar(this.participantes,this.numeroRonda);
       return participantes;
    }

    /**
     * Verica si es necesario un empate comparando los puntos de los 4 primeros jugadores,
     * para que no exista empate debe haber solo una participante para cada mejor puntaje
     * (3 mejores puntajes).
     * @return true si existe desempate, false si no existe.
     */
    public boolean seNecesitaDesempate(){
        if( (participantes.get(0).getPuntos()!=participantes.get(1).getPuntos())
                && (participantes.get(1).getPuntos()!=participantes.get(2).getPuntos())
                && (participantes.get(2).getPuntos())!=participantes.get(3).getPuntos() ){
            return false;
        }
        return true;
    }

    /**
     * metodo abstracto para desempatar
     * @throws OrdenarEnfrentamientosNoEjecutadoException si no se ordenan los enfrentamientos del desempate lanza excepcion.
     */
    public abstract void desempatar() throws OrdenarEnfrentamientosNoEjecutadoException;

    /**
     * Establece los ganadores, si hay empate se genera un desempate.
     * @throws OrdenarEnfrentamientosNoEjecutadoException desempatar puede mostrar tal excepcion.
     */
    public void establecerGanadores() throws  ExisteEmpateException {
        if(numeroRonda==numeroMaximoRondas){
            modalidadJuego.ordenarParticipantesParaMostrar(participantes,numeroRonda);
            if(!(modalidadJuego instanceof  EliminacionDirecta)) {
                if (!seNecesitaDesempate()) {
                    primerLugar = participantes.get(0);
                    segundoLugar = participantes.get(1);
                    tercerLugar = participantes.get(2);
                } else {
                    throw new ExisteEmpateException("Existe al menos un desempate");
                }
            }

        }
        else{

            System.out.println("aun no acaba el torneo");
        }
    }


    public ArrayList<Participante> getParticipantes() {
        return this.participantes;
    }

    public String getFecha() {
        return fecha;
    }

    public String getLugar() {
        return lugar;
    }

    public ArrayList<ArrayList<Participante>> getDistribucion() {
        return distribucion;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public void setLugar(String lugar) {
        this.lugar = lugar;
    }

    public ArrayList<Enfrentamiento> getEnfrentamientosJugadosPorRonda() {
        return enfrentamientosJugadosPorRonda;
    }
}
