package dominio;

/**
 *
 * @author Fralkayg
 */
public class bodega {
    private int cod_bodega;
    private String nom_bodega;
    private String direccion;

    public bodega() {
    }

    public int getCod_bodega() {
        return cod_bodega;
    }

    public void setCod_bodega(int cod_bodega) {
        this.cod_bodega = cod_bodega;
    }

    public String getNom_bodega() {
        return nom_bodega;
    }

    public void setNom_bodega(String nom_bodega) {
        this.nom_bodega = nom_bodega;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }
    
    
}
