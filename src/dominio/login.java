
package dominio;

import hojasverdes.Login;
import hojasverdes.conectar;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;


public class login {
    String Usuario;
    
    public int validar_ingreso(){
       conectar cnx = new conectar();
       Connection reg = cnx.conexion();
       String sql;
       String usuario = Login.txt_usuario.getText();
       String clave = String.valueOf(Login.txt_contraseña.getPassword());
       int resultado = 0;
       sql = "SELECT * FROM usuario WHERE rut='"+usuario+"' AND contraseña='"+clave+"'";
        try {
            Statement st = reg.createStatement();
            ResultSet rs = st.executeQuery(sql);
            
            if(rs.next()){
                resultado=1;
            }
            
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null,ex,"Error de Conexión",JOptionPane.ERROR_MESSAGE);
            
        
        
        }
           
               cnx.cerrar();
           
        
    
    return resultado;
    
    }

    public String guardarUsuario(){
            Usuario = Login.txt_usuario.getText();
        return Usuario;
    }

    public String getUsuario() {
        return Usuario;
    }
    
    
    
}
