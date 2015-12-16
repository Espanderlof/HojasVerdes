package dominio;

public class detalleEnvio {
    private int cod_envio;
    private int cod_producto;
    private int kilogramos;
    private int n_bins;

    public detalleEnvio() {
    }

    public int getCod_envio() {
        return cod_envio;
    }

    public void setCod_envio(int cod_envio) {
        this.cod_envio = cod_envio;
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

    public int getN_bins() {
        return n_bins;
    }

    public void setN_bins(int n_bins) {
        this.n_bins = n_bins;
    }
    
    
    
}
