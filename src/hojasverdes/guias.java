/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hojasverdes;

import dominio.camion;
import dominio.guiaEnvio;
import dominio.guiaRecepcion;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Jaime
 */
public class guias extends javax.swing.JFrame {
    Calendar c2 = new GregorianCalendar();    
    DefaultTableModel modelo = new DefaultTableModel();
    conectar cnx = new conectar();
    Connection reg= cnx.conexion();
    //private TableRowSorter trsfiltro;
    int columna=0;
    int sw = 0;
    String sql;
    
    public guias() {
        //this.setExtendedState(MAXIMIZED_BOTH);
        initComponents();
        this.setLocationRelativeTo(null);
        this.setTitle("Guias");
        tbl_guias.setAutoCreateRowSorter(true);
        modelo.addColumn("N° Guia");
        modelo.addColumn("Fecha envio");
        modelo.addColumn("Fecha recepcion");
        modelo.addColumn("Patente");
        modelo.addColumn("Chofer");
        modelo.addColumn("Proveedor");
        modelo.addColumn("Campo");
        tbl_guias.setModel(modelo);
        btn_aceptar.setVisible(false);
        btn_cancelar.setVisible(false);
        cmbPatente("");
        //cmbChofer("");
        cmbProveedor("");
        cmb_fechaEnvio.setCalendar(c2);
        cmb_fechaRecepcion.setCalendar(c2);
        mostrardatostabla("");
    }
    
    void mostrardatostabla(String valor){    
        String []datos=new String[7];
        int cod;
        String sql="";
        if(valor.equals("")){
            sql="SELECT e.cod_envio, e.fecha_envio, r.fecha_recepcion, e.patente, e.rut_chofer, e.rut_proveedor, e.cod_campo  FROM guia_envio e, guia_recepcion r where e.cod_envio = r.cod_envio";
        }else{
            //sql="SELECT * FROM camion WHERE patente='"+valor+"'";
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
                datos[5]=rs.getString(6);
                datos[6]=rs.getString(7);
                modelo.addRow(datos);
            }
            tbl_guias.setModel(modelo);
            
        } catch (SQLException ex) {
            Logger.getLogger(producto.class.getName()).log(Level.SEVERE, null, ex);
        }   
    }
    
    public void limpiartabla(){
       // tabla.setModel(new DefaultTableModel());
        for (int i = 0; i < tbl_guias.getRowCount(); i++) {
           modelo.removeRow(i);
           i-=1;
       }
    }
    
    void cmbPatente(String valor){
        try{
            String sql="select distinct(patente) from camion_chofer";
            Statement st = reg.createStatement();
            ResultSet rs = st.executeQuery(sql);
            while (rs.next()){
                String name = rs.getString(1);
                cmb_patente.addItem(name);
            }    
        }catch(Exception e){
                
        }
    }
    
    void cmbChofer(String valor){
        cmb_chofer.removeAllItems();
        try{
            //String sql="select distinct(rut_chofer) from camion_chofer";
            String sql="select c.nom_chofer from chofer c, camion_chofer cc where cc.patente ='"+valor+"' and c.rut_chofer = cc.rut_chofer";
            Statement st = reg.createStatement();
            ResultSet rs = st.executeQuery(sql);
            while (rs.next()){
                String name = rs.getString(1);
                cmb_chofer.addItem(name);
            }    
        }catch(Exception e){
                
        }
    }
    
    void cmbProveedor(String nombre){
        cmb_proveedor.removeAllItems();
        try{
            String sql="select nom_proveedor from proveedor where rut_proveedor in (select rut_proveedor from campo)";
            Statement st = reg.createStatement();
            ResultSet rs = st.executeQuery(sql);
            while (rs.next()){
                cmb_proveedor.addItem(rs.getString(1));
            }  
        }catch(Exception e){
            
        }
    }
    
    void cmbCampo(String valor){
        cmb_campo.removeAllItems();
        try{
            String sql="select nom_campo from campo c, proveedor p where p.rut_proveedor ='"+valor+"' and c.rut_proveedor = p.rut_proveedor";
            Statement st = reg.createStatement();
            ResultSet rs = st.executeQuery(sql);
            while (rs.next()){
                cmb_campo.addItem(rs.getString(1));
            }    
        }catch(Exception e){
            
        }
    }
    
    public String getRutProveedor(){
        String codigo="";
        try{
            String sql="select rut_proveedor from proveedor where nom_proveedor ='"+cmb_proveedor.getSelectedItem()+"'";
            Statement st = reg.createStatement();
            ResultSet rs = st.executeQuery(sql);
            while (rs.next()){
                codigo = rs.getString(1);
            }   
        }catch(Exception e){
            
        }
        return codigo;
    }
    
    public int getCodCampo(){
        int codigo=0;
        try{
            String sql="select cod_campo from campo where nom_campo ='"+cmb_campo.getSelectedItem()+"'";
            Statement st = reg.createStatement();
            ResultSet rs = st.executeQuery(sql);
            while (rs.next()){
                codigo = Integer.parseInt(rs.getString(1));
            }   
        }catch(Exception e){
            
        }
        return codigo;
    }
    
    public  String getRutChofer(){
        String codigo="";
        try{
            String sql="select rut_chofer from chofer where nom_chofer ='"+cmb_chofer.getSelectedItem()+"'";
            Statement st = reg.createStatement();
            ResultSet rs = st.executeQuery(sql);
            while (rs.next()){
                codigo = rs.getString(1);
            }   
        }catch(Exception e){
            
        }
        return codigo;
    }
    
    public String getNomChofer(int fila){
        String nombre="";
        try{
            String sql="select nom_chofer from chofer where rut_chofer ="+tbl_guias.getValueAt(fila, 4).toString()+"";
            Statement st = reg.createStatement();
            ResultSet rs = st.executeQuery(sql);
            while (rs.next()){
                nombre = rs.getString(1);
            }   
        }catch(Exception e){
            
        }
        return nombre;
    }
    
    public String getNomProveedor(int fila){
        String nombre="";
        try{
            String sql="select nom_proveedor from proveedor where rut_proveedor ="+tbl_guias.getValueAt(fila, 5).toString()+"";
            Statement st = reg.createStatement();
            ResultSet rs = st.executeQuery(sql);
            while (rs.next()){
                nombre = rs.getString(1);
            }   
        }catch(Exception e){
            
        }
        return nombre;
    }

    public String getNomCampo(int fila){
        String nombre="";
        try{
            String sql="select nom_campo from campo where cod_campo ="+tbl_guias.getValueAt(fila, 6).toString()+"";
            Statement st = reg.createStatement();
            ResultSet rs = st.executeQuery(sql);
            while (rs.next()){
                nombre = rs.getString(1);
            }   
        }catch(Exception e){
            
        }
        return nombre;
    }
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        txt_guia = new javax.swing.JTextField();
        jLabel11 = new javax.swing.JLabel();
        cmb_proveedor = new javax.swing.JComboBox();
        btn_eliminar = new javax.swing.JButton();
        btn_modificar = new javax.swing.JButton();
        cmb_patente = new javax.swing.JComboBox();
        cmb_chofer = new javax.swing.JComboBox();
        jLabel18 = new javax.swing.JLabel();
        btn_camionChofer = new javax.swing.JButton();
        jLabel21 = new javax.swing.JLabel();
        cmb_campo = new javax.swing.JComboBox();
        btn_agregarCampo = new javax.swing.JButton();
        btn_agregarDetalle = new javax.swing.JButton();
        cmb_fechaEnvio = new com.toedter.calendar.JDateChooser();
        cmb_fechaRecepcion = new com.toedter.calendar.JDateChooser();
        btn_aceptar = new javax.swing.JButton();
        btn_cancelar = new javax.swing.JButton();
        btn_agregar = new javax.swing.JButton();
        jPanel5 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        tbl_guias = new javax.swing.JTable();
        jLabel19 = new javax.swing.JLabel();
        jLabel20 = new javax.swing.JLabel();
        combobox_filtro = new javax.swing.JComboBox();
        txt_filtro = new javax.swing.JTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jPanel1.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel7.setText("Fecha Envío:");
        jPanel1.add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 28, -1, -1));

        jLabel8.setText("Fecha Recepción:");
        jPanel1.add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(237, 22, -1, 20));

        jLabel9.setText("Proveedor:");
        jPanel1.add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(35, 100, -1, -1));

        jLabel10.setText("Nombre Chofer:");
        jPanel1.add(jLabel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(237, 63, -1, -1));

        jLabel12.setText("N° Guía:");
        jPanel1.add(jLabel12, new org.netbeans.lib.awtextra.AbsoluteConstraints(49, 132, -1, -1));

        txt_guia.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txt_guiaKeyTyped(evt);
            }
        });
        jPanel1.add(txt_guia, new org.netbeans.lib.awtextra.AbsoluteConstraints(102, 129, 117, -1));

        jLabel11.setText("Patente Camión:");
        jPanel1.add(jLabel11, new org.netbeans.lib.awtextra.AbsoluteConstraints(12, 63, -1, -1));

        cmb_proveedor.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmb_proveedorActionPerformed(evt);
            }
        });
        jPanel1.add(cmb_proveedor, new org.netbeans.lib.awtextra.AbsoluteConstraints(102, 97, 117, -1));

        btn_eliminar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/iconos/delete.png"))); // NOI18N
        btn_eliminar.setText("Eliminar");
        btn_eliminar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_eliminarActionPerformed(evt);
            }
        });
        jPanel1.add(btn_eliminar, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 210, -1, -1));

        btn_modificar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/iconos/update.png"))); // NOI18N
        btn_modificar.setText("Modificar");
        btn_modificar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_modificarActionPerformed(evt);
            }
        });
        jPanel1.add(btn_modificar, new org.netbeans.lib.awtextra.AbsoluteConstraints(240, 210, -1, -1));

        cmb_patente.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cmb_patenteItemStateChanged(evt);
            }
        });
        cmb_patente.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmb_patenteActionPerformed(evt);
            }
        });
        jPanel1.add(cmb_patente, new org.netbeans.lib.awtextra.AbsoluteConstraints(102, 60, 117, -1));

        cmb_chofer.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmb_choferActionPerformed(evt);
            }
        });
        jPanel1.add(cmb_chofer, new org.netbeans.lib.awtextra.AbsoluteConstraints(332, 60, 175, -1));

        jLabel18.setText("RECEPCIÓN DE FRUTA DESDE CAMPOS ");
        jPanel1.add(jLabel18, new org.netbeans.lib.awtextra.AbsoluteConstraints(174, 2, -1, -1));

        btn_camionChofer.setIcon(new javax.swing.ImageIcon(getClass().getResource("/iconos/add.png"))); // NOI18N
        btn_camionChofer.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_camionChoferActionPerformed(evt);
            }
        });
        jPanel1.add(btn_camionChofer, new org.netbeans.lib.awtextra.AbsoluteConstraints(513, 60, 23, 20));

        jLabel21.setText("Campo:");
        jPanel1.add(jLabel21, new org.netbeans.lib.awtextra.AbsoluteConstraints(275, 101, -1, -1));

        cmb_campo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmb_campoActionPerformed(evt);
            }
        });
        jPanel1.add(cmb_campo, new org.netbeans.lib.awtextra.AbsoluteConstraints(330, 98, 106, -1));

        btn_agregarCampo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/iconos/add.png"))); // NOI18N
        btn_agregarCampo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_agregarCampoActionPerformed(evt);
            }
        });
        jPanel1.add(btn_agregarCampo, new org.netbeans.lib.awtextra.AbsoluteConstraints(442, 98, 23, 20));

        btn_agregarDetalle.setIcon(new javax.swing.ImageIcon(getClass().getResource("/iconos/guias.png"))); // NOI18N
        btn_agregarDetalle.setText("Agregar detalle guias");
        btn_agregarDetalle.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_agregarDetalleActionPerformed(evt);
            }
        });
        jPanel1.add(btn_agregarDetalle, new org.netbeans.lib.awtextra.AbsoluteConstraints(330, 150, -1, -1));
        jPanel1.add(cmb_fechaEnvio, new org.netbeans.lib.awtextra.AbsoluteConstraints(102, 22, 117, -1));
        jPanel1.add(cmb_fechaRecepcion, new org.netbeans.lib.awtextra.AbsoluteConstraints(332, 22, 112, -1));

        btn_aceptar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/iconos/OK-20.png"))); // NOI18N
        btn_aceptar.setText("Aceptar");
        btn_aceptar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_aceptarActionPerformed(evt);
            }
        });
        jPanel1.add(btn_aceptar, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 210, -1, -1));

        btn_cancelar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/iconos/delete.png"))); // NOI18N
        btn_cancelar.setText("Cancelar");
        btn_cancelar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_cancelarActionPerformed(evt);
            }
        });
        jPanel1.add(btn_cancelar, new org.netbeans.lib.awtextra.AbsoluteConstraints(340, 210, -1, -1));

        btn_agregar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/iconos/add.png"))); // NOI18N
        btn_agregar.setText("Agregar");
        btn_agregar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_agregarActionPerformed(evt);
            }
        });
        jPanel1.add(btn_agregar, new org.netbeans.lib.awtextra.AbsoluteConstraints(460, 210, -1, 30));

        jPanel5.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jPanel5.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        tbl_guias.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4", "Title 5", "Title 6", "Title 7"
            }
        ));
        jScrollPane2.setViewportView(tbl_guias);

        jPanel5.add(jScrollPane2, new org.netbeans.lib.awtextra.AbsoluteConstraints(12, 44, 1201, 313));

        jLabel19.setText("Texto Filtro:");
        jPanel5.add(jLabel19, new org.netbeans.lib.awtextra.AbsoluteConstraints(372, 16, -1, -1));

        jLabel20.setText("Columna Filtro:");
        jPanel5.add(jLabel20, new org.netbeans.lib.awtextra.AbsoluteConstraints(623, 16, -1, -1));

        combobox_filtro.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        jPanel5.add(combobox_filtro, new org.netbeans.lib.awtextra.AbsoluteConstraints(713, 13, 101, -1));
        jPanel5.add(txt_filtro, new org.netbeans.lib.awtextra.AbsoluteConstraints(441, 13, 172, -1));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(324, 324, 324)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 560, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(361, Short.MAX_VALUE))
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 261, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, 369, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(19, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btn_camionChoferActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_camionChoferActionPerformed
        mantenedorCamionChofer abrirCC = new mantenedorCamionChofer();
        abrirCC.setVisible(true);
    }//GEN-LAST:event_btn_camionChoferActionPerformed

    private void btn_agregarDetalleActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_agregarDetalleActionPerformed
        mantenedorDetalleGuias abrirDetalle = new mantenedorDetalleGuias();
        abrirDetalle.setVisible(true);
    }//GEN-LAST:event_btn_agregarDetalleActionPerformed

    private void cmb_patenteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmb_patenteActionPerformed
        if (cmb_patente.getSelectedItem() ==null){
            
        }else{
            
            cmbChofer(cmb_patente.getSelectedItem().toString());   
        }
    }//GEN-LAST:event_cmb_patenteActionPerformed

    private void cmb_patenteItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cmb_patenteItemStateChanged

    }//GEN-LAST:event_cmb_patenteItemStateChanged

    private void cmb_choferActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmb_choferActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cmb_choferActionPerformed

    private void cmb_proveedorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmb_proveedorActionPerformed
    if (cmb_proveedor.getSelectedItem() ==null){
            
        }else{
            cmbCampo(getRutProveedor());   
        }
    }//GEN-LAST:event_cmb_proveedorActionPerformed

    private void cmb_campoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmb_campoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cmb_campoActionPerformed

    private void btn_agregarCampoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_agregarCampoActionPerformed
        mantenedorCampo abrirCampo = new mantenedorCampo();
        abrirCampo.setVisible(true);
    }//GEN-LAST:event_btn_agregarCampoActionPerformed

    private void btn_agregarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_agregarActionPerformed
        if (txt_guia.getText().equals("")){
            JOptionPane.showMessageDialog(null,"Debe ingresar codigo guia");
        }else{
            guiaEnvio dto = new guiaEnvio();
            guiaRecepcion dto2 = new guiaRecepcion();
            dto.setPatente(cmb_patente.getSelectedItem().toString());
            dto.setCod_campo(getCodCampo());
            dto.setRut_proveedor(getRutProveedor());
            dto.setCod_envio(Integer.parseInt(txt_guia.getText()));
            dto.setRut_chofer(getRutChofer());
            Date fecha = cmb_fechaEnvio.getDate();
            java.sql.Date sqlfecha = new java.sql.Date(fecha.getTime());
            dto.setFecha(sqlfecha);

            dto2.setPatente(cmb_patente.getSelectedItem().toString());
            dto2.setCod_campo(getCodCampo());
            dto2.setRut_proveedor(getRutProveedor());
            dto2.setCod_envio(Integer.parseInt(txt_guia.getText()));
            dto2.setCod_recepcion(Integer.parseInt(txt_guia.getText()));
            dto2.setRut_chofer(getRutChofer());
            Date fecha2 = cmb_fechaRecepcion.getDate();
            java.sql.Date sqlfecha2 = new java.sql.Date(fecha.getTime());
            dto2.setFecha_recepcion(sqlfecha2);
            sql= "INSERT INTO guia_envio (cod_envio, cod_campo, rut_proveedor, rut_chofer, patente, fecha_envio)VALUES (?,?,?,?,?,?)";
            try {
                PreparedStatement pst=reg.prepareStatement(sql);
                pst.setInt(1, dto.getCod_envio());
                pst.setInt(2, dto.getCod_campo());
                pst.setString(3, dto.getRut_proveedor());
                pst.setString(4, dto.getRut_chofer());
                pst.setString(5, dto.getPatente());
                pst.setDate(6, dto.getFecha());
                int n = pst.executeUpdate();
                if (n>0){
                    JOptionPane.showMessageDialog(null,"Registrado satisfactoriamente.");
                }                
            }catch (SQLException ex) {
                JOptionPane.showMessageDialog(null,"Error al agregar,");
                //sw = 1;
            }

            sql= "INSERT INTO guia_recepcion (cod_recepcion, cod_campo, rut_proveedor, cod_envio, patente, rut_chofer, fecha_recepcion)VALUES (?,?,?,?,?,?,?)";
            try {
                PreparedStatement pst=reg.prepareStatement(sql);
                pst.setInt(1, dto2.getCod_recepcion());
                pst.setInt(2, dto2.getCod_campo());
                pst.setString(3, dto2.getRut_proveedor());
                pst.setInt(4, dto2.getCod_envio());
                pst.setString(5, dto2.getPatente());
                pst.setString(6, dto2.getRut_chofer());
                pst.setDate(7, dto2.getFecha_recepcion());
                int n = pst.executeUpdate();
                if (n>0){
                    JOptionPane.showMessageDialog(null,"Registrado satisfactoriamente.");
                }                
            }catch (SQLException ex) {
                JOptionPane.showMessageDialog(null,"Error al agregar");
                //sw = 1;
            }  
            limpiartabla();
            mostrardatostabla(""); 
        }
    }//GEN-LAST:event_btn_agregarActionPerformed

    private void btn_modificarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_modificarActionPerformed
        int fila=tbl_guias.getSelectedRow();
        if (fila>=0){
            txt_guia.setText(tbl_guias.getValueAt(fila, 0).toString());
            cmb_patente.setSelectedItem(tbl_guias.getValueAt(fila, 3).toString());
            String nom = getNomChofer(fila);
            cmb_chofer.setSelectedItem(nom);
            String nomProv = getNomProveedor(fila);
            cmb_proveedor.setSelectedItem(nomProv);
            String nomCampo = getNomCampo(fila);
            cmb_campo.setSelectedItem(nomCampo);
            txt_guia.setEditable(false);
            txt_guia.setEnabled(false);
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
        Date fecha = cmb_fechaEnvio.getDate();
        java.sql.Date sqlfecha = new java.sql.Date(fecha.getTime());
        sql="UPDATE guia_envio SET cod_campo="+getCodCampo()+",rut_proveedor= "+getRutProveedor()+", rut_chofer="+getRutChofer()+", patente ='"+cmb_patente.getSelectedItem().toString()+"', fecha_envio='"+sqlfecha+"'  WHERE cod_envio="+Integer.parseInt(txt_guia.getText())+"";
        try {
            PreparedStatement pst = reg.prepareStatement(sql);
            pst.executeUpdate();
            limpiartabla();
            mostrardatostabla("");
        } catch (Exception e) {
            System.out.println(e.getMessage());
            JOptionPane.showMessageDialog(null,e.getMessage());
        }
        
        Date fecha2 = cmb_fechaEnvio.getDate();
        java.sql.Date sqlfecha2 = new java.sql.Date(fecha.getTime());
        sql="UPDATE guia_recepcion SET cod_campo="+getCodCampo()+",rut_proveedor= "+getRutProveedor()+", rut_chofer="+getRutChofer()+", patente ='"+cmb_patente.getSelectedItem().toString()+"', fecha_recepcion='"+sqlfecha2+"'  WHERE cod_recepcion="+Integer.parseInt(txt_guia.getText())+"";
        try {
            PreparedStatement pst = reg.prepareStatement(sql);
            pst.executeUpdate();
            limpiartabla();
            mostrardatostabla("");
        } catch (Exception e) {
            System.out.println(e.getMessage());
            JOptionPane.showMessageDialog(null,e.getMessage());
        }
        btn_aceptar.setVisible(false);
        btn_cancelar.setVisible(false);
        btn_eliminar.setVisible(true);
        btn_modificar.setVisible(true);
        btn_agregar.setVisible(true);
        txt_guia.setEnabled(true);
        txt_guia.setEditable(true);
        txt_guia.requestFocus();
        txt_guia.setText("");
        limpiartabla();
        mostrardatostabla("");
    }//GEN-LAST:event_btn_aceptarActionPerformed

    private void btn_cancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_cancelarActionPerformed
        btn_aceptar.setVisible(false);
        btn_cancelar.setVisible(false);
        btn_eliminar.setVisible(true);
        btn_modificar.setVisible(true);
        btn_agregar.setVisible(true);
        txt_guia.setEnabled(true);
        txt_guia.setEditable(true);
        txt_guia.requestFocus();
        txt_guia.setText("");
    }//GEN-LAST:event_btn_cancelarActionPerformed

    private void btn_eliminarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_eliminarActionPerformed
        int fila = tbl_guias.getSelectedRow();
        if (fila >= 0){   
            if(JOptionPane.showConfirmDialog(null, new Object[]{"Seguro que desea Eliminar fila seleccionada?"},"Eliminar",JOptionPane.OK_CANCEL_OPTION)==JOptionPane.YES_OPTION){
            //qui se pone lo que hara si le das aceptar
                fila=tbl_guias.getSelectedRow();
                txt_guia.setText(tbl_guias.getValueAt(fila, 0).toString());
                try{
                    PreparedStatement pst = reg.prepareStatement("DELETE FROM guia_recepcion WHERE cod_recepcion="+Integer.parseInt(txt_guia.getText())+"");
                    pst.executeUpdate();
                    limpiartabla();
                    mostrardatostabla("");
                }catch(Exception e){
                    System.out.println(e.getMessage());
                }
                try {
                    PreparedStatement pst = reg.prepareStatement("DELETE FROM guia_envio WHERE cod_envio="+Integer.parseInt(txt_guia.getText())+"");
                    pst.executeUpdate();
                    limpiartabla();
                    mostrardatostabla("");
                } catch (Exception e) {
                    System.out.println(e.getMessage());
                }
            }else{
            //aqui se pone lo que hara si le das cancelar
            }
            
        }else{
            JOptionPane.showMessageDialog(null,"Debe seleccionar una fila antes de eliminar.");
        }
        txt_guia.setText("");
    }//GEN-LAST:event_btn_eliminarActionPerformed

    private void txt_guiaKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_guiaKeyTyped
        int limite = 5;
        if (txt_guia.getText().length() == limite) {
            evt.consume();
        }
        char t = evt.getKeyChar();
        if (Character.isLetter(t)) {
            evt.consume();
            JOptionPane.showMessageDialog(null, "Ingrese solo numeros");
        }   
    }//GEN-LAST:event_txt_guiaKeyTyped

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
            java.util.logging.Logger.getLogger(guias.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(guias.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(guias.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(guias.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new guias().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btn_aceptar;
    private javax.swing.JButton btn_agregar;
    private javax.swing.JButton btn_agregarCampo;
    private javax.swing.JButton btn_agregarDetalle;
    private javax.swing.JButton btn_camionChofer;
    private javax.swing.JButton btn_cancelar;
    private javax.swing.JButton btn_eliminar;
    private javax.swing.JButton btn_modificar;
    private javax.swing.JComboBox cmb_campo;
    private javax.swing.JComboBox cmb_chofer;
    private com.toedter.calendar.JDateChooser cmb_fechaEnvio;
    private com.toedter.calendar.JDateChooser cmb_fechaRecepcion;
    private javax.swing.JComboBox cmb_patente;
    private javax.swing.JComboBox cmb_proveedor;
    private javax.swing.JComboBox combobox_filtro;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTable tbl_guias;
    private javax.swing.JTextField txt_filtro;
    private javax.swing.JTextField txt_guia;
    // End of variables declaration//GEN-END:variables
}
