/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package persistencia;

import dominio.bodega;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import javax.swing.JOptionPane;


/**
 *
 * @author Fralkayg
 */
public class bodegaDAO {
    private Connection cnx;

    public bodegaDAO(Connection cnx) {
        this.cnx = cnx;
    }
    
    public void agregar(bodega bodega) {
        String sql = "insert into bodega "
                + "        (Cod_bodega, Nom_bodega, Direccion)"
                + "  values(?,      ?,       ?) ";
        // INDICE           1       2        3
        JOptionPane.showMessageDialog(null, "aqui?");
        try (PreparedStatement stmt = cnx.prepareStatement(sql)) {
            stmt.setInt(1, bodega.getCod_bodega());
            stmt.setString(2, bodega.getNom_bodega());
            stmt.setString(3, bodega.getDireccion());
            JOptionPane.showMessageDialog(null, "aqui");

            //int filasAfectas = stmt.executeUpdate();
        } catch (SQLException ex) {
            throw new RuntimeException("Error al agregar", ex);
        }
    }
}
