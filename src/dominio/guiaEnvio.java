package dominio;

import java.sql.Date;

public class guiaEnvio {
    private int cod_envio;
    private int cod_campo;
    private String rut_proveedor;
    private String rut_chofer;
    private String patente;
    private java.sql.Date fecha = new java.sql.Date(0);

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

    public String getRut_proveedor() {
        return rut_proveedor;
    }

    public void setRut_proveedor(String rut_proveedor) {
        this.rut_proveedor = rut_proveedor;
    }

    public String getRut_chofer() {
        return rut_chofer;
    }

    public void setRut_chofer(String rut_chofer) {
        this.rut_chofer = rut_chofer;
    }

    public String getPatente() {
        return patente;
    }

    public void setPatente(String patente) {
        this.patente = patente;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(java.sql.Date fecha) {
        this.fecha = fecha;
    }
    
    
}
