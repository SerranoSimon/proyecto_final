package visual;

public class DatosTorneo {
    private String torneoFecha;
    private String torneoLugar;
    private String modalidadTorneo;
    private String tipoParticipantes;
    private String torneoTiempo;
    private String torneoDesempate;

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

    public String getModalidadTorneo() {
        return modalidadTorneo;
    }
    public void setModalidadTorneo(String modalidad) {
        this.modalidadTorneo = modalidad;
    }

    public String getTorneoTiempo() {
        return torneoTiempo;
    }
    public void setTorneoTiempo(String tiempo) {
        this.torneoTiempo = tiempo;
    }

    public String getTorneoDesempate() {
        return torneoDesempate;
    }
    public void setTorneoDesempate(String desempate) {
        this.torneoDesempate = desempate;
    }
    public String getTipoParticipantes() {
        return tipoParticipantes;
    }
    public void setTipoParticipantes(String tipo) {
        this.tipoParticipantes = tipo;
    }
}