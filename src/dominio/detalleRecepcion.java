package dominio;

public class detalleRecepcion {
    private int cod_recepcion;
    private int cod_producto;
    private int kilogramos;
    private int num_bins;

    public detalleRecepcion() {
    }

    public int getCod_recepcion() {
        return cod_recepcion;
    }

    public void setCod_recepcion(int cod_recepcion) {
        this.cod_recepcion = cod_recepcion;
    }

    public int getCod_producto() {
        return cod_producto;
    }

    public void setCod_producto(int cod_producto) {
        this.cod_producto = cod_producto;
    }

    public int getKilogramos() {
        return kilogramos;
    }

    public void setKilogramos(int kilogramos) {
        this.kilogramos = kilogramos;
    }

    public int getNum_bins() {
        return num_bins;
    }

    public void setNum_bins(int num_bins) {
        this.num_bins = num_bins;
    }
    
    
    
}
