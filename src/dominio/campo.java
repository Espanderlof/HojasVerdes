package dominio;

public class campo {
    private int cod_campo;
    private int rut_proveedor;
    private String nom_campo;
    private String direccion;

    public campo() {
    }

    public int getCod_campo() {
        return cod_campo;
    }

    public void setCod_campo(int cod_campo) {
        this.cod_campo = cod_campo;
    }

    public int getRut_proveedor() {
        return rut_proveedor;
    }

    public void setRut_proveedor(int rut_proveedor) {
        this.rut_proveedor = rut_proveedor;
    }

    public String getNom_campo() {
        return nom_campo;
    }

    public void setNom_campo(String nom_campo) {
        this.nom_campo = nom_campo;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }
    
    
    
}
