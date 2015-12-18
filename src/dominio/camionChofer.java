package dominio;

import java.sql.Date;

public class camionChofer {
    private String patente;
    private int rut_chofer;
    private java.sql.Date fecha = new java.sql.Date(0);
    private String hora_uso;

    public camionChofer() {
    }

    public String getPatente() {
        return patente;
    }

    public void setPatente(String patente) {
        this.patente = patente;
    }

    public int getRut_chofer() {
        return rut_chofer;
    }

    public void setRut_chofer(int rut_chofer) {
        this.rut_chofer = rut_chofer;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public String getHora_uso() {
        return hora_uso;
    }

    public void setHora_uso(String hora_uso) {
        this.hora_uso = hora_uso;
    }
    
    
}
