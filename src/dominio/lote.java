
package dominio;

import java.sql.Date;

/**
 *
 * @author jaime
 */
public class lote {
    private int cod_lote;
    private int cod_producto;
    private int calibre;
    private java.sql.Date fecha = new java.sql.Date(0);
    private int kilos_inicial;
    private int kilos_final;
    
    public lote(){
    }

    public int getCod_lote() {
        return cod_lote;
    }

    public void setCod_lote(int cod_lote) {
        this.cod_lote = cod_lote;
    }

    public int getCod_producto() {
        return cod_producto;
    }

    public void setCod_producto(int cod_producto) {
        this.cod_producto = cod_producto;
    }

    public int getCalibre() {
        return calibre;
    }

    public void setCalibre(int calibre) {
        this.calibre = calibre;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(java.sql.Date fecha) {
        this.fecha = fecha;
    }

    public int getKilos_inicial() {
        return kilos_inicial;
    }

    public void setKilos_inicial(int kilos_inicial) {
        this.kilos_inicial = kilos_inicial;
    }

    public int getKilos_final() {
        return kilos_final;
    }

    public void setKilos_final(int kilos_final) {
        this.kilos_final = kilos_final;
    }
    
    
    
    
}
