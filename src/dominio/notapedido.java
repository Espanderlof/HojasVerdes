
package dominio;

import java.sql.Date;

/**
 *
 * @author jaime
 */
public class notapedido {
    private int nro_nota;
    private int rut_cliente;
    private java.sql.Date fecha = new java.sql.Date(0);
    
    public notapedido(){
    }

    public int getNro_nota() {
        return nro_nota;
    }

    public void setNro_nota(int nro_nota) {
        this.nro_nota = nro_nota;
    }

    public int getRut_cliente() {
        return rut_cliente;
    }

    public void setRut_cliente(int rut_cliente) {
        this.rut_cliente = rut_cliente;
    }

    public java.sql.Date getFecha() {
        return fecha;
    }

    public void setFecha(java.sql.Date fecha) {
        this.fecha = fecha;
    }

    
    
    
}
