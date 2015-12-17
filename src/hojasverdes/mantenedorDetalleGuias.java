/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hojasverdes;

import dominio.detalleEnvio;
import dominio.detalleRecepcion;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;

/**
 *
 * @author Fralkayg
 */
public class mantenedorDetalleGuias extends javax.swing.JFrame {
    
    DefaultTableModel modelo = new DefaultTableModel();
    DefaultTableModel modelo2 = new DefaultTableModel();
    conectar con= new conectar();
    Connection reg= con.conexion();
    private TableRowSorter trsfiltro;
    int columna=0;
    String sql;

    /**
     * Creates new form mantenedorDetalleGuias
     */
    public mantenedorDetalleGuias() {
        initComponents();
        this.setLocationRelativeTo(null);
        this.setTitle("Detalle Guias");
        tbl_envio.setAutoCreateRowSorter(true);
        tbl_recepcion.setAutoCreateRowSorter(true);
        //ingresa las columnas de tu tabla productos
        modelo.addColumn("Codigo");
        modelo.addColumn("Producto");
        modelo.addColumn("Variedad");
        modelo.addColumn("Kilogramos");
        modelo.addColumn("Numero bins");
        modelo2.addColumn("Codigo");
        modelo2.addColumn("Producto");
        modelo2.addColumn("Variedad");
        modelo2.addColumn("Kilogramos");
        modelo2.addColumn("Numero bins");
        tbl_envio.setModel(modelo);
        tbl_recepcion.setModel(modelo2);
        btn_aceptar.setVisible(false);
        btn_cancelar.setVisible(false);
        btn_aceptar2.setVisible(false);
        btn_cancelar2.setVisible(false);
        cmb_guiaEnvio("");
        cmb_guiaRecepcion("");
        cmb_productoVariedad("");
        cmb_productoVariedad2("");
        limpiartablaRecepcion();
        limpiartablaEnvio();
        mostrardatostablaRecepcion("");
        mostrardatostablaEnvio("");
    }

    void mostrardatostablaEnvio(String valor){    
        String []datos=new String[5];
        String sql="";
        if(valor.equals("")){
            sql="SELECT e.cod_envio, p.nom_producto, p.variedad, e.kilogramos, e.num_bins FROM detalle_envio e, producto p where cod_envio="+Integer.parseInt(cmb_guiaEnvio.getSelectedItem().toString())+"  and e.cod_producto = p.cod_producto ";
        }else{
            sql="SELECT * FROM detalle_envio WHERE cod_envio="+valor+"";
        }
        try {
            Statement st = reg.createStatement();
            ResultSet rs = st.executeQuery(sql);
            while(rs.next()){
                //aqui se agregan los campos
                datos[0]=rs.getString(1);
                datos[1]=rs.getString(2);
                datos[2]=rs.getString(3);
                datos[3]=rs.getString(4);
                datos[4]=rs.getString(5);
                modelo.addRow(datos);
            }
            tbl_envio.setModel(modelo);
            
        } catch (SQLException ex) {
            Logger.getLogger(producto.class.getName()).log(Level.SEVERE, null, ex);
        }   
    }
    
    public void limpiartablaEnvio(){
       // tabla.setModel(new DefaultTableModel());
        for (int i = 0; i < tbl_envio.getRowCount(); i++) {
           modelo.removeRow(i);
           i-=1;
       }
    }    
 
    
    void mostrardatostablaRecepcion(String valor){    
        String []datos=new String[5];
        String sql="";
        if(valor.equals("")){
            sql="SELECT r.cod_recepcion, p.nom_producto, p.variedad, r.kilogramos, r.num_bins FROM detalle_recepcion r, producto p where cod_recepcion="+Integer.parseInt(cmb_guiaRecepcion.getSelectedItem().toString())+"  and r.cod_producto = p.cod_producto ";
        }else{
            sql="SELECT * FROM detalle_recepcion WHERE cod_recepcion="+valor+"";
        }
        try {
            Statement st = reg.createStatement();
            ResultSet rs = st.executeQuery(sql);
            while(rs.next()){
                //aqui se agregan los campos
                datos[0]=rs.getString(1);
                datos[1]=rs.getString(2);
                datos[2]=rs.getString(3);
                datos[3]=rs.getString(4);
                datos[4]=rs.getString(5);
                modelo2.addRow(datos);
            }
            tbl_recepcion.setModel(modelo2);
            
        } catch (SQLException ex) {
            Logger.getLogger(producto.class.getName()).log(Level.SEVERE, null, ex);
        }   
    }
    
   
    public void limpiartablaRecepcion(){
       // tabla.setModel(new DefaultTableModel());
        for (int i = 0; i < tbl_recepcion.getRowCount(); i++) {
           modelo2.removeRow(i);
           i-=1;
       }
    } 
    
    //-----------------------------------------------------------------------------------------------------------
    //-------------------- Detalle envio ------------------------------------------------------------------------
    
    void cmb_guiaEnvio(String valor){
        try{
            String sql="select cod_envio from guia_envio order by cod_envio";
            Statement st = reg.createStatement();
            ResultSet rs = st.executeQuery(sql);
            while (rs.next()){
                cmb_guiaEnvio.addItem(rs.getString(1));
            }    
        }catch(Exception e){
            }
    }
    
    void cmb_productoVariedad(String valor){
        try{
            String sql="select nom_producto from producto";
            Statement st = reg.createStatement();
            ResultSet rs = st.executeQuery(sql);
            while (rs.next()){
                cmb_nomProducto.addItem(rs.getString(1));
            }    
        }catch(Exception e){
            
        }
    }
    
    void cmb_variedad(String nombre){
        cmb_variedad.removeAllItems();
        try{
            String sql="select variedad from producto where nom_producto ='"+nombre+"'";
            Statement st = reg.createStatement();
            ResultSet rs = st.executeQuery(sql);
            while (rs.next()){
                cmb_variedad.addItem(rs.getString(1));
            }  
        }catch(Exception e){
            
        }
    }
    
    public int getCodProducto(){
        int codigo=0;
        try{
            String sql="select cod_producto from producto where nom_producto ='"+cmb_nomProducto.getSelectedItem()+"' and variedad='"+cmb_variedad.getSelectedItem()+"' ";
            Statement st = reg.createStatement();
            ResultSet rs = st.executeQuery(sql);
            while (rs.next()){
                codigo = Integer.parseInt(rs.getString(1));
            }   
        }catch(Exception e){
            
        }
        return codigo;
    }
    

 

    
    //-------------------------------------------------------------------------------------------------------------------
    //-------------------------------------------------------------------------------------------------------------------
    //--------------------------------------- Guia recepcion ------------------------------------------------------------
    
    void cmb_guiaRecepcion(String valor){
        try{
            String sql="select cod_recepcion from guia_recepcion order by cod_recepcion";
            Statement st = reg.createStatement();
            ResultSet rs = st.executeQuery(sql);
            while (rs.next()){
                cmb_guiaRecepcion.addItem(rs.getString(1));
            }    
        }catch(Exception e){
            }
    }

    void cmb_productoVariedad2(String valor){
        try{
            String sql="select nom_producto from producto";
            Statement st = reg.createStatement();
            ResultSet rs = st.executeQuery(sql);
            while (rs.next()){
                cmb_nomProducto2.addItem(rs.getString(1));
            }    
        }catch(Exception e){
            
        }
    }
    
    void cmb_variedad2(String nombre){
        cmb_variedad2.removeAllItems();
        try{
            String sql="select variedad from producto where nom_producto ='"+nombre+"'";
            Statement st = reg.createStatement();
            ResultSet rs = st.executeQuery(sql);
            while (rs.next()){
                cmb_variedad2.addItem(rs.getString(1));
            }  
        }catch(Exception e){
            
        }
    }
    
    public int getCodProducto2(){
        int codigo=0;
        try{
            String sql="select cod_producto from producto where nom_producto ='"+cmb_nomProducto2.getSelectedItem()+"' and variedad='"+cmb_variedad2.getSelectedItem()+"' ";
            Statement st = reg.createStatement();
            ResultSet rs = st.executeQuery(sql);
            while (rs.next()){
                codigo = Integer.parseInt(rs.getString(1));
            }   
        }catch(Exception e){
            
        }
        return codigo;
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        cmb_guiaEnvio = new javax.swing.JComboBox();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        txt_kilogramos = new javax.swing.JTextField();
        cmb_nomProducto = new javax.swing.JComboBox();
        btn_agregar = new javax.swing.JButton();
        btn_modificar = new javax.swing.JButton();
        btn_eliminar = new javax.swing.JButton();
        jButton8 = new javax.swing.JButton();
        jLabel7 = new javax.swing.JLabel();
        cmb_variedad = new javax.swing.JComboBox();
        jLabel9 = new javax.swing.JLabel();
        txt_numBins = new javax.swing.JTextField();
        btn_aceptar = new javax.swing.JButton();
        btn_cancelar = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        jLabel4 = new javax.swing.JLabel();
        cmb_guiaRecepcion = new javax.swing.JComboBox();
        jLabel5 = new javax.swing.JLabel();
        cmb_nomProducto2 = new javax.swing.JComboBox();
        jLabel6 = new javax.swing.JLabel();
        txt_kilogramos2 = new javax.swing.JTextField();
        jButton1 = new javax.swing.JButton();
        btn_agregar2 = new javax.swing.JButton();
        btn_modificar2 = new javax.swing.JButton();
        btn_eliminar2 = new javax.swing.JButton();
        jLabel8 = new javax.swing.JLabel();
        cmb_variedad2 = new javax.swing.JComboBox();
        jLabel10 = new javax.swing.JLabel();
        txt_numBins2 = new javax.swing.JTextField();
        btn_aceptar2 = new javax.swing.JButton();
        btn_cancelar2 = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        tbl_envio = new javax.swing.JTable();
        jScrollPane2 = new javax.swing.JScrollPane();
        tbl_recepcion = new javax.swing.JTable();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jPanel1.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jLabel1.setText("Codigo guia envio:");

        cmb_guiaEnvio.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmb_guiaEnvioActionPerformed(evt);
            }
        });

        jLabel2.setText("Nombre producto:");

        jLabel3.setText("Kilogramos:");
        jLabel3.setToolTipText("");

        cmb_nomProducto.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmb_nomProductoActionPerformed(evt);
            }
        });

        btn_agregar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/iconos/add.png"))); // NOI18N
        btn_agregar.setText("Agregar");
        btn_agregar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_agregarActionPerformed(evt);
            }
        });

        btn_modificar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/iconos/update.png"))); // NOI18N
        btn_modificar.setText("Modificar");
        btn_modificar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_modificarActionPerformed(evt);
            }
        });

        btn_eliminar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/iconos/delete.png"))); // NOI18N
        btn_eliminar.setText("Eliminar");
        btn_eliminar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_eliminarActionPerformed(evt);
            }
        });

        jButton8.setIcon(new javax.swing.ImageIcon(getClass().getResource("/iconos/add.png"))); // NOI18N

        jLabel7.setText("Variedad:");

        jLabel9.setText("Numero bins:");

        btn_aceptar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/iconos/OK-20.png"))); // NOI18N
        btn_aceptar.setText("Aceptar");
        btn_aceptar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_aceptarActionPerformed(evt);
            }
        });

        btn_cancelar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/iconos/delete.png"))); // NOI18N
        btn_cancelar.setText("Cancelar");
        btn_cancelar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_cancelarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(btn_eliminar)
                        .addGap(26, 26, 26)
                        .addComponent(btn_aceptar)
                        .addGap(18, 18, 18)
                        .addComponent(btn_modificar)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 24, Short.MAX_VALUE)
                        .addComponent(btn_cancelar)
                        .addGap(18, 18, 18)
                        .addComponent(btn_agregar)
                        .addGap(18, 18, 18))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel7)
                            .addComponent(jLabel3)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 102, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(jLabel2))
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                            .addComponent(cmb_guiaEnvio, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                            .addComponent(cmb_nomProducto, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                            .addComponent(cmb_variedad, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 124, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 76, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                            .addComponent(txt_kilogramos)
                                            .addComponent(txt_numBins, javax.swing.GroupLayout.DEFAULT_SIZE, 124, Short.MAX_VALUE))))
                                .addGap(18, 18, 18)
                                .addComponent(jButton8, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(cmb_guiaEnvio, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jButton8, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel2)
                        .addComponent(cmb_nomProducto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel7)
                    .addComponent(cmb_variedad, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(txt_kilogramos, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txt_numBins, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel9))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btn_agregar, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btn_modificar)
                    .addComponent(btn_eliminar)
                    .addComponent(btn_aceptar)
                    .addComponent(btn_cancelar))
                .addContainerGap())
        );

        jPanel2.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jLabel4.setText("Codigo guia recepcion:");

        cmb_guiaRecepcion.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmb_guiaRecepcionActionPerformed(evt);
            }
        });

        jLabel5.setText("Nombre producto:");

        cmb_nomProducto2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmb_nomProducto2ActionPerformed(evt);
            }
        });

        jLabel6.setText("Kilogramos:");

        jButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/iconos/add.png"))); // NOI18N

        btn_agregar2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/iconos/add.png"))); // NOI18N
        btn_agregar2.setText("Agregar");
        btn_agregar2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_agregar2ActionPerformed(evt);
            }
        });

        btn_modificar2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/iconos/update.png"))); // NOI18N
        btn_modificar2.setText("Modificar");
        btn_modificar2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_modificar2ActionPerformed(evt);
            }
        });

        btn_eliminar2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/iconos/delete.png"))); // NOI18N
        btn_eliminar2.setText("Eliminar");
        btn_eliminar2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_eliminar2ActionPerformed(evt);
            }
        });

        jLabel8.setText("Variedad:");

        jLabel10.setText("Numero bins:");

        btn_aceptar2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/iconos/OK-20.png"))); // NOI18N
        btn_aceptar2.setText("Aceptar");
        btn_aceptar2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_aceptar2ActionPerformed(evt);
            }
        });

        btn_cancelar2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/iconos/delete.png"))); // NOI18N
        btn_cancelar2.setText("Cancelar");
        btn_cancelar2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_cancelar2ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 111, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 149, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(cmb_nomProducto2, javax.swing.GroupLayout.PREFERRED_SIZE, 124, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(cmb_guiaRecepcion, javax.swing.GroupLayout.PREFERRED_SIZE, 124, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addGroup(jPanel2Layout.createSequentialGroup()
                            .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 73, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(85, 85, 85)
                            .addComponent(cmb_variedad2, javax.swing.GroupLayout.PREFERRED_SIZE, 124, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(jPanel2Layout.createSequentialGroup()
                            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                                    .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 84, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGap(51, 51, 51))
                                .addGroup(jPanel2Layout.createSequentialGroup()
                                    .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 91, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGap(44, 44, 44)))
                            .addGap(23, 23, 23)
                            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                .addComponent(txt_numBins2, javax.swing.GroupLayout.PREFERRED_SIZE, 124, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(txt_kilogramos2, javax.swing.GroupLayout.PREFERRED_SIZE, 124, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(8, 8, 8)
                .addComponent(btn_eliminar2)
                .addGap(28, 28, 28)
                .addComponent(btn_aceptar2)
                .addGap(18, 18, 18)
                .addComponent(btn_modificar2)
                .addGap(18, 18, 18)
                .addComponent(btn_cancelar2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 27, Short.MAX_VALUE)
                .addComponent(btn_agregar2)
                .addGap(24, 24, 24))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(cmb_guiaRecepcion, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(cmb_nomProducto2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel5))
                    .addComponent(jButton1))
                .addGap(23, 23, 23)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel8)
                    .addComponent(cmb_variedad2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6)
                    .addComponent(txt_kilogramos2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel10)
                    .addComponent(txt_numBins2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 28, Short.MAX_VALUE)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btn_modificar2)
                    .addComponent(btn_cancelar2)
                    .addComponent(btn_eliminar2)
                    .addComponent(btn_aceptar2)
                    .addComponent(btn_agregar2, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        tbl_envio.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Codigo guia", "Producto", "Variedad", "Kilogramos"
            }
        ));
        jScrollPane1.setViewportView(tbl_envio);

        tbl_recepcion.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Codigo guia", "Producto", "Variedad", "Kilogramos"
            }
        ));
        jScrollPane2.setViewportView(tbl_recepcion);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jScrollPane1))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jScrollPane2))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 289, Short.MAX_VALUE)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void cmb_guiaEnvioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmb_guiaEnvioActionPerformed
        if (cmb_guiaEnvio.getSelectedItem() == null){
            
        }else{
            limpiartablaEnvio();
            mostrardatostablaEnvio("");
        }
        
    }//GEN-LAST:event_cmb_guiaEnvioActionPerformed

    private void cmb_nomProductoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmb_nomProductoActionPerformed
        if (cmb_nomProducto.getSelectedItem() ==null){
            
        }else{
            cmb_variedad(cmb_nomProducto.getSelectedItem().toString());   
        }
    }//GEN-LAST:event_cmb_nomProductoActionPerformed

    private void btn_agregarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_agregarActionPerformed
        detalleEnvio dto = new detalleEnvio();
        dto.setCod_envio(Integer.parseInt(cmb_guiaEnvio.getSelectedItem().toString()));
        dto.setCod_producto(getCodProducto());
        dto.setKilogramos(Integer.parseInt(txt_kilogramos.getText()));
        dto.setN_bins(Integer.parseInt(txt_numBins.getText()));
        sql = "INSERT INTO detalle_envio (cod_envio, cod_producto, kilogramos, num_bins) VALUES (?,?,?,?)";
        try {
            PreparedStatement pst = reg.prepareStatement(sql);
            pst.setInt(1, dto.getCod_envio());
            pst.setInt(2, dto.getCod_producto());
            pst.setInt(3, dto.getKilogramos());
            pst.setInt(4, dto.getN_bins());
            int n = pst.executeUpdate();
            if (n>0){
                JOptionPane.showMessageDialog(null,"Detalle envio registrado satisfactoriamente.");
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null,"Error al agregar, codigos duplicado.");
        }
        limpiartablaEnvio();
        mostrardatostablaEnvio("");
    }//GEN-LAST:event_btn_agregarActionPerformed

    private void btn_modificarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_modificarActionPerformed
        int fila=tbl_envio.getSelectedRow();
        if (fila>=0){
            cmb_guiaEnvio.removeAllItems();
            cmb_guiaEnvio.addItem(tbl_envio.getValueAt(fila, 0).toString());
            cmb_nomProducto.removeAllItems();
            cmb_nomProducto.addItem(tbl_envio.getValueAt(fila, 1).toString());
            cmb_variedad.removeAllItems();
            cmb_variedad.addItem(tbl_envio.getValueAt(fila, 2).toString());
            txt_kilogramos.setText(tbl_envio.getValueAt(fila, 3).toString());
            txt_numBins.setText(tbl_envio.getValueAt(fila, 4).toString());
            cmb_guiaEnvio.setEditable(false);
            cmb_guiaEnvio.setEnabled(false);
            cmb_nomProducto.setEditable(false);
            cmb_nomProducto.setEnabled(false);
            cmb_variedad.setEditable(false);
            cmb_variedad.setEnabled(false);
            btn_agregar.setVisible(false);
            btn_modificar.setVisible(false);
            btn_eliminar.setVisible(false);
            btn_aceptar.setVisible(true);
            btn_cancelar.setVisible(true);
        }else{
            JOptionPane.showMessageDialog(null,"No selecciono fila");
        }
    }//GEN-LAST:event_btn_modificarActionPerformed

    private void btn_aceptarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_aceptarActionPerformed
        String envio = cmb_guiaEnvio.getSelectedItem().toString();
        String nom = cmb_nomProducto.getSelectedItem().toString();
        String var = cmb_variedad.getSelectedItem().toString();
        sql="UPDATE detalle_envio SET kilogramos="+Integer.parseInt(txt_kilogramos.getText())+",num_bins= "+Integer.parseInt(txt_numBins.getText())+"  WHERE cod_envio="+Integer.parseInt(cmb_guiaEnvio.getSelectedItem().toString())+" and cod_producto="+getCodProducto()+"";
        try {
            PreparedStatement pst = reg.prepareStatement(sql);
            pst.executeUpdate();
            limpiartablaEnvio();
            mostrardatostablaEnvio("");
        } catch (Exception e) {
            System.out.println(e.getMessage());
            JOptionPane.showMessageDialog(null,e.getMessage());
        }
        btn_aceptar.setVisible(false);
        btn_cancelar.setVisible(false);
        btn_eliminar.setVisible(true);
        btn_modificar.setVisible(true);
        btn_agregar.setVisible(true);
        //cmb_guiaEnvio.setEditable(true);
        cmb_guiaEnvio.setEnabled(true);
        //cmb_nomProducto.setEditable(true);
        cmb_nomProducto.setEnabled(true);
        //cmb_variedad.setEditable(true);
        cmb_variedad.setEnabled(true);
        txt_kilogramos.setText("");
        txt_numBins.setText("");
        cmb_guiaEnvio.removeAllItems();
        cmb_nomProducto.removeAllItems();
        cmb_variedad.removeAllItems();
        cmb_guiaEnvio("");
        cmb_productoVariedad("");
        cmb_guiaEnvio.setSelectedItem(envio);
        cmb_nomProducto.setSelectedItem(nom);
        cmb_variedad.setSelectedItem(var);
        limpiartablaEnvio();
        mostrardatostablaEnvio("");
    }//GEN-LAST:event_btn_aceptarActionPerformed

    private void btn_cancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_cancelarActionPerformed
        String envio = cmb_guiaEnvio.getSelectedItem().toString();
        String nom = cmb_nomProducto.getSelectedItem().toString();
        String var = cmb_variedad.getSelectedItem().toString();
        btn_aceptar.setVisible(false);
        btn_cancelar.setVisible(false);
        btn_eliminar.setVisible(true);
        btn_modificar.setVisible(true);
        btn_agregar.setVisible(true);
        //cmb_guiaEnvio.setEditable(true);
        cmb_guiaEnvio.setEnabled(true);
        //cmb_nomProducto.setEditable(true);
        cmb_nomProducto.setEnabled(true);
        //cmb_variedad.setEditable(true);
        cmb_variedad.setEnabled(true);
        cmb_guiaEnvio.requestFocus();
        txt_kilogramos.setText("");
        txt_numBins.setText("");
        cmb_guiaEnvio.removeAllItems();
        cmb_nomProducto.removeAllItems();
        cmb_variedad.removeAllItems();
        cmb_guiaEnvio("");
        cmb_productoVariedad("");
        cmb_guiaEnvio.setSelectedItem(envio);
        cmb_nomProducto.setSelectedItem(nom);
        cmb_variedad.setSelectedItem(var);
    }//GEN-LAST:event_btn_cancelarActionPerformed

    private void btn_eliminarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_eliminarActionPerformed
        int fila = tbl_envio.getSelectedRow();
        if (fila >= 0){   
            if(JOptionPane.showConfirmDialog(null, new Object[]{"Seguro que desea Eliminar fila seleccionada?"},"Eliminar",JOptionPane.OK_CANCEL_OPTION)==JOptionPane.YES_OPTION){
            //qui se pone lo que hara si le das aceptar
                fila=tbl_envio.getSelectedRow();
                //txt_patente.setText(tbl_camiones.getValueAt(fila, 0).toString());
                cmb_guiaEnvio.setSelectedItem(tbl_envio.getValueAt(fila, 0).toString());
                
                try {
                    PreparedStatement pst = reg.prepareStatement("DELETE FROM detalle_envio WHERE cod_envio="+Integer.parseInt(cmb_guiaEnvio.getSelectedItem().toString())+" and cod_producto="+getCodProducto()+" ");
                    pst.executeUpdate();
                    limpiartablaEnvio();
                    mostrardatostablaEnvio("");
                } catch (Exception e) {
                    System.out.println(e.getMessage());
                }
            }else{
            //aqui se pone lo que hara si le das cancelar
            }
        }else{
            JOptionPane.showMessageDialog(null,"Debe seleccionar una fila antes de eliminar.");
        }
    }//GEN-LAST:event_btn_eliminarActionPerformed

    private void cmb_guiaRecepcionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmb_guiaRecepcionActionPerformed
        if (cmb_guiaRecepcion.getSelectedItem() == null){
            
        }else{
            limpiartablaRecepcion();
            mostrardatostablaRecepcion("");
        }
    }//GEN-LAST:event_cmb_guiaRecepcionActionPerformed

    private void cmb_nomProducto2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmb_nomProducto2ActionPerformed
        if (cmb_nomProducto2.getSelectedItem() ==null){
            
        }else{
            cmb_variedad2(cmb_nomProducto2.getSelectedItem().toString());   
        }
    }//GEN-LAST:event_cmb_nomProducto2ActionPerformed

    private void btn_agregar2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_agregar2ActionPerformed
        detalleRecepcion dto = new detalleRecepcion();
        dto.setCod_recepcion(Integer.parseInt(cmb_guiaRecepcion.getSelectedItem().toString()));
        dto.setCod_producto(getCodProducto2());
        dto.setKilogramos(Integer.parseInt(txt_kilogramos2.getText()));
        dto.setNum_bins(Integer.parseInt(txt_numBins2.getText()));
        sql = "INSERT INTO detalle_recepcion (cod_recepcion, cod_producto, kilogramos, num_bins) VALUES (?,?,?,?)";
        try {
            PreparedStatement pst = reg.prepareStatement(sql);
            pst.setInt(1, dto.getCod_recepcion());
            pst.setInt(2, dto.getCod_producto());
            pst.setInt(3, dto.getKilogramos());
            pst.setInt(4, dto.getNum_bins());
            int n = pst.executeUpdate();
            if (n>0){
                JOptionPane.showMessageDialog(null,"Detalle recepcion registrado satisfactoriamente.");
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null,"Error al agregar, codigos duplicado.");
        }
        limpiartablaRecepcion();
        mostrardatostablaRecepcion("");
    }//GEN-LAST:event_btn_agregar2ActionPerformed

    private void btn_modificar2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_modificar2ActionPerformed
        int fila=tbl_recepcion.getSelectedRow();
        if (fila>=0){
            cmb_guiaRecepcion.removeAllItems();
            cmb_guiaRecepcion.addItem(tbl_recepcion.getValueAt(fila, 0).toString());
            cmb_nomProducto2.removeAllItems();
            cmb_nomProducto2.addItem(tbl_recepcion.getValueAt(fila, 1).toString());
            cmb_variedad2.removeAllItems();
            cmb_variedad2.addItem(tbl_recepcion.getValueAt(fila, 2).toString());
            txt_kilogramos2.setText(tbl_recepcion.getValueAt(fila, 3).toString());
            txt_numBins2.setText(tbl_recepcion.getValueAt(fila, 4).toString());
            cmb_guiaRecepcion.setEditable(false);
            cmb_guiaRecepcion.setEnabled(false);
            cmb_nomProducto2.setEditable(false);
            cmb_nomProducto2.setEnabled(false);
            cmb_variedad2.setEditable(false);
            cmb_variedad2.setEnabled(false);
            btn_agregar2.setVisible(false);
            btn_modificar2.setVisible(false);
            btn_eliminar2.setVisible(false);
            btn_aceptar2.setVisible(true);
            btn_cancelar2.setVisible(true);
        }else{
            JOptionPane.showMessageDialog(null,"No selecciono fila");
        }
    }//GEN-LAST:event_btn_modificar2ActionPerformed

    private void btn_cancelar2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_cancelar2ActionPerformed
        String rec = cmb_guiaRecepcion.getSelectedItem().toString();
        String nom = cmb_nomProducto2.getSelectedItem().toString();
        String var = cmb_variedad2.getSelectedItem().toString();        
        btn_aceptar2.setVisible(false);
        btn_cancelar2.setVisible(false);
        btn_eliminar2.setVisible(true);
        btn_modificar2.setVisible(true);
        btn_agregar2.setVisible(true);
        //cmb_guiaEnvio.setEditable(true);
        cmb_guiaRecepcion.setEnabled(true);
        //cmb_nomProducto.setEditable(true);
        cmb_nomProducto2.setEnabled(true);
        //cmb_variedad.setEditable(true);
        cmb_variedad2.setEnabled(true);
        cmb_guiaRecepcion.requestFocus();
        txt_kilogramos2.setText("");
        txt_numBins2.setText("");
        cmb_guiaRecepcion.removeAllItems();
        cmb_nomProducto2.removeAllItems();
        cmb_variedad2.removeAllItems();
        cmb_guiaRecepcion("");
        cmb_productoVariedad2("");
        cmb_guiaRecepcion.setSelectedItem(rec);
        cmb_nomProducto2.setSelectedItem(nom);
        cmb_variedad2.setSelectedItem(var);
    }//GEN-LAST:event_btn_cancelar2ActionPerformed

    private void btn_aceptar2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_aceptar2ActionPerformed
        String rec = cmb_guiaRecepcion.getSelectedItem().toString();
        String nom = cmb_nomProducto2.getSelectedItem().toString();
        String var = cmb_variedad2.getSelectedItem().toString();
        sql="UPDATE detalle_recepcion SET kilogramos="+Integer.parseInt(txt_kilogramos2.getText())+",num_bins= "+Integer.parseInt(txt_numBins2.getText())+"  WHERE cod_recepcion="+Integer.parseInt(cmb_guiaRecepcion.getSelectedItem().toString())+" and cod_producto="+getCodProducto2()+"";
        try {
            PreparedStatement pst = reg.prepareStatement(sql);
            pst.executeUpdate();
            limpiartablaRecepcion();
            mostrardatostablaRecepcion("");
        } catch (Exception e) {
            System.out.println(e.getMessage());
            JOptionPane.showMessageDialog(null,e.getMessage());
        }
        btn_aceptar2.setVisible(false);
        btn_cancelar2.setVisible(false);
        btn_eliminar2.setVisible(true);
        btn_modificar2.setVisible(true);
        btn_agregar2.setVisible(true);
        //cmb_guiaEnvio.setEditable(true);
        cmb_guiaRecepcion.setEnabled(true);
        //cmb_nomProducto.setEditable(true);
        cmb_nomProducto2.setEnabled(true);
        //cmb_variedad.setEditable(true);
        cmb_variedad2.setEnabled(true);
        txt_kilogramos2.setText("");
        txt_numBins2.setText("");
        cmb_guiaRecepcion.removeAllItems();
        cmb_nomProducto2.removeAllItems();
        cmb_variedad2.removeAllItems();
        cmb_guiaRecepcion("");
        cmb_productoVariedad2("");
        cmb_guiaRecepcion.setSelectedItem(rec);
        cmb_nomProducto2.setSelectedItem(nom);
        cmb_variedad2.setSelectedItem(var);
        limpiartablaRecepcion();
        mostrardatostablaRecepcion("");
    }//GEN-LAST:event_btn_aceptar2ActionPerformed

    private void btn_eliminar2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_eliminar2ActionPerformed
        int fila = tbl_recepcion.getSelectedRow();
        if (fila >= 0){   
            if(JOptionPane.showConfirmDialog(null, new Object[]{"Seguro que desea Eliminar fila seleccionada?"},"Eliminar",JOptionPane.OK_CANCEL_OPTION)==JOptionPane.YES_OPTION){
            //qui se pone lo que hara si le das aceptar
                fila=tbl_recepcion.getSelectedRow();
                //txt_patente.setText(tbl_camiones.getValueAt(fila, 0).toString());
                cmb_guiaRecepcion.setSelectedItem(tbl_recepcion.getValueAt(fila, 0).toString());
                
                try {
                    PreparedStatement pst = reg.prepareStatement("DELETE FROM detalle_recepcion WHERE cod_recepcion="+Integer.parseInt(cmb_guiaRecepcion.getSelectedItem().toString())+" and cod_producto="+getCodProducto2()+" ");
                    pst.executeUpdate();
                    limpiartablaRecepcion();
                    mostrardatostablaRecepcion("");
                } catch (Exception e) {
                    System.out.println(e.getMessage());
                }
            }else{
            //aqui se pone lo que hara si le das cancelar
            }
        }else{
            JOptionPane.showMessageDialog(null,"Debe seleccionar una fila antes de eliminar.");
        }
    }//GEN-LAST:event_btn_eliminar2ActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(mantenedorDetalleGuias.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(mantenedorDetalleGuias.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(mantenedorDetalleGuias.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(mantenedorDetalleGuias.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new mantenedorDetalleGuias().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btn_aceptar;
    private javax.swing.JButton btn_aceptar2;
    private javax.swing.JButton btn_agregar;
    private javax.swing.JButton btn_agregar2;
    private javax.swing.JButton btn_cancelar;
    private javax.swing.JButton btn_cancelar2;
    private javax.swing.JButton btn_eliminar;
    private javax.swing.JButton btn_eliminar2;
    private javax.swing.JButton btn_modificar;
    private javax.swing.JButton btn_modificar2;
    private javax.swing.JComboBox cmb_guiaEnvio;
    private javax.swing.JComboBox cmb_guiaRecepcion;
    private javax.swing.JComboBox cmb_nomProducto;
    private javax.swing.JComboBox cmb_nomProducto2;
    private javax.swing.JComboBox cmb_variedad;
    private javax.swing.JComboBox cmb_variedad2;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton8;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTable tbl_envio;
    private javax.swing.JTable tbl_recepcion;
    private javax.swing.JTextField txt_kilogramos;
    private javax.swing.JTextField txt_kilogramos2;
    private javax.swing.JTextField txt_numBins;
    private javax.swing.JTextField txt_numBins2;
    // End of variables declaration//GEN-END:variables
}
