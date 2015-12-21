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
        btn_refrescar = new javax.swing.JButton();
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
        btn_refrescar2 = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        tbl_envio = new javax.swing.JTable();
        jScrollPane2 = new javax.swing.JScrollPane();
        tbl_recepcion = new javax.swing.JTable();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent evt) {
                formWindowClosing(evt);
            }
        });

        jPanel1.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel1.setText("Codigo guia envio:");
        jPanel1.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(12, 16, 102, -1));

        cmb_guiaEnvio.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmb_guiaEnvioActionPerformed(evt);
            }
        });
        jPanel1.add(cmb_guiaEnvio, new org.netbeans.lib.awtextra.AbsoluteConstraints(118, 13, 124, -1));

        jLabel2.setText("Nombre producto:");
        jPanel1.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(12, 54, -1, -1));

        jLabel3.setText("Kilogramos:");
        jLabel3.setToolTipText("");
        jPanel1.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(320, 20, -1, 20));

        txt_kilogramos.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txt_kilogramosKeyTyped(evt);
            }
        });
        jPanel1.add(txt_kilogramos, new org.netbeans.lib.awtextra.AbsoluteConstraints(420, 20, 124, 20));

        cmb_nomProducto.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmb_nomProductoActionPerformed(evt);
            }
        });
        jPanel1.add(cmb_nomProducto, new org.netbeans.lib.awtextra.AbsoluteConstraints(118, 51, 124, -1));

        btn_agregar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/iconos/add.png"))); // NOI18N
        btn_agregar.setText("Agregar");
        btn_agregar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_agregarActionPerformed(evt);
            }
        });
        jPanel1.add(btn_agregar, new org.netbeans.lib.awtextra.AbsoluteConstraints(480, 130, -1, 29));

        btn_modificar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/iconos/update.png"))); // NOI18N
        btn_modificar.setText("Modificar");
        btn_modificar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_modificarActionPerformed(evt);
            }
        });
        jPanel1.add(btn_modificar, new org.netbeans.lib.awtextra.AbsoluteConstraints(240, 130, -1, -1));

        btn_eliminar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/iconos/delete.png"))); // NOI18N
        btn_eliminar.setText("Eliminar");
        btn_eliminar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_eliminarActionPerformed(evt);
            }
        });
        jPanel1.add(btn_eliminar, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 130, -1, -1));

        jButton8.setIcon(new javax.swing.ImageIcon(getClass().getResource("/iconos/add.png"))); // NOI18N
        jPanel1.add(jButton8, new org.netbeans.lib.awtextra.AbsoluteConstraints(260, 51, 32, 20));

        jLabel7.setText("Variedad:");
        jPanel1.add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(12, 92, -1, -1));

        jPanel1.add(cmb_variedad, new org.netbeans.lib.awtextra.AbsoluteConstraints(118, 89, 124, -1));

        jLabel9.setText("Numero bins:");
        jPanel1.add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(310, 54, 76, 30));

        txt_numBins.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txt_numBinsKeyTyped(evt);
            }
        });
        jPanel1.add(txt_numBins, new org.netbeans.lib.awtextra.AbsoluteConstraints(420, 60, 124, 20));

        btn_aceptar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/iconos/OK-20.png"))); // NOI18N
        btn_aceptar.setText("Aceptar");
        btn_aceptar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_aceptarActionPerformed(evt);
            }
        });
        jPanel1.add(btn_aceptar, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 130, -1, -1));

        btn_cancelar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/iconos/delete.png"))); // NOI18N
        btn_cancelar.setText("Cancelar");
        btn_cancelar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_cancelarActionPerformed(evt);
            }
        });
        jPanel1.add(btn_cancelar, new org.netbeans.lib.awtextra.AbsoluteConstraints(350, 130, -1, 30));

        btn_refrescar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/iconos/refresh-20.png"))); // NOI18N
        btn_refrescar.setText("Refrescar");
        btn_refrescar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_refrescarActionPerformed(evt);
            }
        });
        jPanel1.add(btn_refrescar, new org.netbeans.lib.awtextra.AbsoluteConstraints(360, 130, -1, -1));

        jPanel2.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jPanel2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel4.setText("Codigo guia recepcion:");
        jPanel2.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(12, 16, 149, -1));

        cmb_guiaRecepcion.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmb_guiaRecepcionActionPerformed(evt);
            }
        });
        jPanel2.add(cmb_guiaRecepcion, new org.netbeans.lib.awtextra.AbsoluteConstraints(171, 13, 124, -1));

        jLabel5.setText("Nombre producto:");
        jPanel2.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(12, 59, 111, -1));

        cmb_nomProducto2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmb_nomProducto2ActionPerformed(evt);
            }
        });
        jPanel2.add(cmb_nomProducto2, new org.netbeans.lib.awtextra.AbsoluteConstraints(171, 56, 124, -1));

        jLabel6.setText("Kilogramos:");
        jPanel2.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(360, 20, 84, -1));

        txt_kilogramos2.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txt_kilogramos2KeyTyped(evt);
            }
        });
        jPanel2.add(txt_kilogramos2, new org.netbeans.lib.awtextra.AbsoluteConstraints(470, 20, 124, -1));

        jButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/iconos/add.png"))); // NOI18N
        jPanel2.add(jButton1, new org.netbeans.lib.awtextra.AbsoluteConstraints(313, 51, 31, -1));

        btn_agregar2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/iconos/add.png"))); // NOI18N
        btn_agregar2.setText("Agregar");
        btn_agregar2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_agregar2ActionPerformed(evt);
            }
        });
        jPanel2.add(btn_agregar2, new org.netbeans.lib.awtextra.AbsoluteConstraints(510, 130, -1, 29));

        btn_modificar2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/iconos/update.png"))); // NOI18N
        btn_modificar2.setText("Modificar");
        btn_modificar2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_modificar2ActionPerformed(evt);
            }
        });
        jPanel2.add(btn_modificar2, new org.netbeans.lib.awtextra.AbsoluteConstraints(250, 130, -1, -1));

        btn_eliminar2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/iconos/delete.png"))); // NOI18N
        btn_eliminar2.setText("Eliminar");
        btn_eliminar2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_eliminar2ActionPerformed(evt);
            }
        });
        jPanel2.add(btn_eliminar2, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 130, -1, -1));

        jLabel8.setText("Variedad:");
        jPanel2.add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(12, 102, 73, -1));

        jPanel2.add(cmb_variedad2, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 99, 124, -1));

        jLabel10.setText("Numero bins:");
        jPanel2.add(jLabel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(360, 60, 91, -1));

        txt_numBins2.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txt_numBins2KeyTyped(evt);
            }
        });
        jPanel2.add(txt_numBins2, new org.netbeans.lib.awtextra.AbsoluteConstraints(470, 60, 124, -1));

        btn_aceptar2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/iconos/OK-20.png"))); // NOI18N
        btn_aceptar2.setText("Aceptar");
        btn_aceptar2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_aceptar2ActionPerformed(evt);
            }
        });
        jPanel2.add(btn_aceptar2, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 130, -1, -1));

        btn_cancelar2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/iconos/delete.png"))); // NOI18N
        btn_cancelar2.setText("Cancelar");
        btn_cancelar2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_cancelar2ActionPerformed(evt);
            }
        });
        jPanel2.add(btn_cancelar2, new org.netbeans.lib.awtextra.AbsoluteConstraints(360, 130, -1, -1));

        btn_refrescar2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/iconos/refresh-20.png"))); // NOI18N
        btn_refrescar2.setText("Refrescar");
        btn_refrescar2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_refrescar2ActionPerformed(evt);
            }
        });
        jPanel2.add(btn_refrescar2, new org.netbeans.lib.awtextra.AbsoluteConstraints(380, 130, -1, -1));

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
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 583, Short.MAX_VALUE)
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, 611, Short.MAX_VALUE)
                    .addComponent(jScrollPane2))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, 172, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 394, Short.MAX_VALUE)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
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
        if (txt_kilogramos.getText().equals("")){
            JOptionPane.showMessageDialog(null,"Debe ingresar kilogramos");
        }else{
            if (txt_numBins.getText().equals("")){
                JOptionPane.showMessageDialog(null,"Debe ingresar numero de bins");
            }else{
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
            }
        }
        
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
        if (txt_kilogramos.getText().equals("")){
            JOptionPane.showMessageDialog(null,"Debe ingresar kilogramos");
        }else{
            if (txt_numBins.getText().equals("")){
                JOptionPane.showMessageDialog(null,"Debe ingresar numero de bins");
            }else{
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
            }
        }
        
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
        if (txt_kilogramos2.getText().equals("")){
            JOptionPane.showMessageDialog(null,"Debe ingresar kilogramos");
        }else{
            if (txt_numBins2.getText().equals("")){
                JOptionPane.showMessageDialog(null,"Debe ingresar numero de bins");
            }else{
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
            }
        }
        
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
        if (txt_kilogramos2.getText().equals("")){
            JOptionPane.showMessageDialog(null,"Debe ingresar kilogramos");
        }else{
            if (txt_numBins2.getText().equals("")){
                JOptionPane.showMessageDialog(null,"Debe ingresar numero de bins");
            }else{
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
            }
        }
        
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

    private void txt_kilogramosKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_kilogramosKeyTyped
        int limite = 10;
        if (txt_kilogramos.getText().length() == limite) {
            evt.consume();
        }
        char t = evt.getKeyChar();
        if (Character.isLetter(t)) {
            evt.consume();
            JOptionPane.showMessageDialog(null, "Ingrese solo numeros");
        }
    }//GEN-LAST:event_txt_kilogramosKeyTyped

    private void txt_numBinsKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_numBinsKeyTyped
        int limite = 3;
        if (txt_numBins.getText().length() == limite) {
            evt.consume();
        }
        char t = evt.getKeyChar();
        if (Character.isLetter(t)) {
            evt.consume();
            JOptionPane.showMessageDialog(null, "Ingrese solo numeros");
        }        
    }//GEN-LAST:event_txt_numBinsKeyTyped

    private void txt_kilogramos2KeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_kilogramos2KeyTyped
        int limite = 10;
        if (txt_kilogramos2.getText().length() == limite) {
            evt.consume();
        }
        char t = evt.getKeyChar();
        if (Character.isLetter(t)) {
            evt.consume();
            JOptionPane.showMessageDialog(null, "Ingrese solo numeros");
        }
    }//GEN-LAST:event_txt_kilogramos2KeyTyped

    private void txt_numBins2KeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_numBins2KeyTyped
        int limite = 3;
        if (txt_numBins2.getText().length() == limite) {
            evt.consume();
        }
        char t = evt.getKeyChar();
        if (Character.isLetter(t)) {
            evt.consume();
            JOptionPane.showMessageDialog(null, "Ingrese solo numeros");
        }   
    }//GEN-LAST:event_txt_numBins2KeyTyped

    private void formWindowClosing(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosing
        // TODO add your handling code here:
        con.cerrar();
    }//GEN-LAST:event_formWindowClosing

    private void btn_refrescarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_refrescarActionPerformed
        cmb_guiaEnvio("");
        cmb_productoVariedad("");
        limpiartablaEnvio();
        mostrardatostablaEnvio("");
    }//GEN-LAST:event_btn_refrescarActionPerformed

    private void btn_refrescar2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_refrescar2ActionPerformed
        cmb_guiaRecepcion("");
        cmb_productoVariedad2("");
        limpiartablaRecepcion();
        mostrardatostablaRecepcion("");
    }//GEN-LAST:event_btn_refrescar2ActionPerformed

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
    private javax.swing.JButton btn_refrescar;
    private javax.swing.JButton btn_refrescar2;
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
