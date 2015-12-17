package dominio;

import java.sql.Date;

public class guiaEnvio {
    private int cod_envio;
    private int cod_campo;
    private int rut_proveedor;
    private int rut_chofer;
    private int patente;
    private String fecha;

    public guiaEnvio() {
    }

    public int getCod_envio() {
        return cod_envio;
    }

    public void setCod_envio(int cod_envio) {
        this.cod_envio = cod_envio;
    }

    public int getCod_campo() {
        return cod_campo;
    }

    public void setCod_campo(int cod_campo) {
        this.cod_campo = cod_campo;
    }

    public int getRut_proveedor() {
        return rut_proveedor;
    }

    public void setRut_proveedor(int rut_proveedor) {
        this.rut_proveedor = rut_proveedor;
    }

    public int getRut_chofer() {
        return rut_chofer;
    }

    public void setRut_chofer(int rut_chofer) {
        this.rut_chofer = rut_chofer;
    }

    public int getPatente() {
        return patente;
    }

    public void setPatente(int patente) {
        this.patente = patente;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }
    
    
}
