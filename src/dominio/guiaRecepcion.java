package dominio;

import java.sql.Date;

public class guiaRecepcion {
    private int cod_recepcion;
    private int cod_campo;
    private String rut_proveedor;
    private int cod_envio;
    private String patente;
    private String rut_chofer;
    private java.sql.Date fecha_recepcion = new java.sql.Date(0);

    public guiaRecepcion() {
    }

    public int getCod_recepcion() {
        return cod_recepcion;
    }

    public void setCod_recepcion(int cod_recepcion) {
        this.cod_recepcion = cod_recepcion;
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

    public int getCod_envio() {
        return cod_envio;
    }

    public void setCod_envio(int cod_envio) {
        this.cod_envio = cod_envio;
    }

    public String getPatente() {
        return patente;
    }

    public void setPatente(String patente) {
        this.patente = patente;
    }

    public String getRut_chofer() {
        return rut_chofer;
    }

    public void setRut_chofer(String rut_chofer) {
        this.rut_chofer = rut_chofer;
    }

    public Date getFecha_recepcion() {
        return fecha_recepcion;
    }

    public void setFecha_recepcion(Date fecha_recepcion) {
        this.fecha_recepcion = fecha_recepcion;
    }

    
    
}
