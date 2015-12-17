
package dominio;

/**
 *
 * @author jaime
 */
public class detallePedido {
    private int nro_nota;
    private int cod_producto;
    private int cantidad;
    private int precio;
    
    public detallePedido(){
    }

    public int getNro_nota() {
        return nro_nota;
    }

    public void setNro_nota(int nro_nota) {
        this.nro_nota = nro_nota;
    }

    public int getCod_producto() {
        return cod_producto;
    }

    public void setCod_producto(int cod_producto) {
        this.cod_producto = cod_producto;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public int getPrecio() {
        return precio;
    }

    public void setPrecio(int precio) {
        this.precio = precio;
    }
    
    
    
}
