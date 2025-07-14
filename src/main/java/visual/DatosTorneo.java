package visual;

import Logica.ModalidadJuego;
import Logica.Participante;
import Logica.TipoDePartida;

/**
 * Clase que representa los datos del torneo, la usaramemos para setters y getters a medida que se va creando el torneo
 */
public class DatosTorneo {
    private String torneoFecha;
    private String torneoLugar;
    private ModalidadJuego modalidadTorneo;
    private String tipoParticipantes;
    private TipoDePartida tiempoNormal;
    private TipoDePartida tiempoDesempate;

    public DatosTorneo() {
    }
    public String getTorneoFecha() {
        return torneoFecha;
    }
    public void setTorneoFecha(String fecha) {
        this.torneoFecha = fecha;
    }

    public String getTorneoLugar() {
        return torneoLugar;
    }
    public void setTorneoLugar(String lugar) {
        this.torneoLugar = lugar;
    }

    public ModalidadJuego getModalidadTorneo() {
        return modalidadTorneo;
    }
    public void setModalidadTorneo(ModalidadJuego modalidad) {
        this.modalidadTorneo = modalidad;
    }

    public TipoDePartida getTorneoTiempoNormal() {
        return tiempoNormal;
    }
    public void setTorneoTiempoNormal(TipoDePartida tiempoNormal) {
        this.tiempoNormal = tiempoNormal;
    }

    public TipoDePartida getTiempoDesempate() {
        return tiempoDesempate;
    }
    public void setTorneoTiempoDesempate(TipoDePartida tiempoDesempate) {
        this.tiempoDesempate = tiempoDesempate;
    }
    public String getTipoParticipantes() {
        return tipoParticipantes;
    }
    public void setTipoParticipantes(String tipo) {
        this.tipoParticipantes = tipo;
    }
}