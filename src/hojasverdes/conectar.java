package hojasverdes;

import java.sql.*;
import javax.swing.JOptionPane;


public class conectar {
    Connection con=null;
    
    public Connection conexion(){
        try{
            //cargar driver
            Class.forName("com.mysql.jdbc.Driver");
            //cambia por tu nombre de base de datos
            con=DriverManager.getConnection("jdbc:mysql://localhost/hojasverdes","root","");
            
            System.out.println("conexion establecida");
            //JOptionPane.showMessageDialog(null, "conexion establecida");
        }catch (ClassNotFoundException | SQLException e){
            System.out.println("error de conexion");
            //JOptionPane.showMessageDialog(null, "error de conexion"+e);  
        }
        return con;
    }
    
    public boolean close(){
        try {
            con.close();
            System.out.println("Conexion cerrada");
            return true;
        } catch (Exception e) {
            System.out.println("no se pudo cerrar a conexion");
            return false;
        }
    }

    Statement prepareStatement(String sql) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    
    
}