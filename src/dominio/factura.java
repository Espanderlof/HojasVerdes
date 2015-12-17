
package dominio;

import java.sql.Date;

/**
 *
 * @author jaime
 */
public class factura {
    private int nro_factura;
    private int nro_nota;
    private int total_neto;
    private java.sql.Date fecha = new java.sql.Date(0);
    
    public factura(){
    }

    public int getNro_factura() {
        return nro_factura;
    }

    public void setNro_factura(int nro_factura) {
        this.nro_factura = nro_factura;
    }

    public int getNro_nota() {
        return nro_nota;
    }

    public void setNro_nota(int nro_nota) {
        this.nro_nota = nro_nota;
    }

    public int getTotal_neto() {
        return total_neto;
    }

    public void setTotal_neto(int total_neto) {
        this.total_neto = total_neto;
    }

    public java.sql.Date getFecha() {
        return fecha;
    }

    public void setFecha(java.sql.Date fecha) {
        this.fecha = fecha;
    }
    
    
}
