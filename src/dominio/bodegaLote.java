package dominio;

import java.sql.Date;

public class bodegaLote {
    private int cod_bodega;
    private int cod_lote;
    private java.sql.Date fecha = new java.sql.Date(0);

    public bodegaLote() {
    }

    public int getCod_bodega() {
        return cod_bodega;
    }

    public void setCod_bodega(int cod_bodega) {
        this.cod_bodega = cod_bodega;
    }

    public int getCod_lote() {
        return cod_lote;
    }

    public void setCod_lote(int cod_lote) {
        this.cod_lote = cod_lote;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }
    
    
}
