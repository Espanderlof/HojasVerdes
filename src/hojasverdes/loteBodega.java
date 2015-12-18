/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hojasverdes;

import dominio.bodegaLote;
import dominio.camion;
import dominio.lote;
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
 * @author Fralkayg
 */
public class loteBodega extends javax.swing.JFrame {
    
    DefaultTableModel modelo = new DefaultTableModel();
    Calendar c2 = new GregorianCalendar();
    conectar cnx = new conectar();
    Connection reg= cnx.conexion();
    //private TableRowSorter trsfiltro;
    int columna=0;
    int sw = 0;
    String sql;

    /**
     * Creates new form loteBodega
     */
    public loteBodega() {
        initComponents();
        this.setLocationRelativeTo(null);
        this.setTitle("Lote Bodega");
        tbl_lote.setAutoCreateRowSorter(true);
        //ingresa las columnas de tu tabla productos
        modelo.addColumn("Lote");
        modelo.addColumn("Producto");
        modelo.addColumn("Variedad");
        modelo.addColumn("Calibre");
        modelo.addColumn("Bodega");
        modelo.addColumn("Kilos inicial");
        modelo.addColumn("Kilos final");
        modelo.addColumn("Elaboracion");
        modelo.addColumn("Ingreso");
        tbl_lote.setModel(modelo);
        cmb_elaboracion.setCalendar(c2);
        cmb_ingreso.setCalendar(c2);
        cmb_productoVariedad("");
        cmbBodega("");
        btn_aceptar.setVisible(false);
        btn_cancelar.setVisible(false);
        mostrardatostabla("");
    }
    
    void mostrardatostabla(String valor){    
        String []datos=new String[9];
        int cod;
        String sql="";
        if(valor.equals("")){
            sql="SELECT l.cod_lote, p.nom_producto, p.variedad, l.calibre, b.nom_bodega, l.kilos_inicial, l.kilos_final, l.fecha, lb.fecha_ingreso FROM lote l, lote_bodega lb, bodega b, producto p where p.cod_producto = l.cod_producto and l.cod_lote = lb.cod_lote and lb.cod_bodega = b.cod_bodega";
        }else{
            sql="SELECT * FROM camion WHERE patente='"+valor+"'";
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
                datos[7]=rs.getString(8);
                datos[8]=rs.getString(9);
                modelo.addRow(datos);
            }
            tbl_lote.setModel(modelo);
            
        } catch (SQLException ex) {
            Logger.getLogger(producto.class.getName()).log(Level.SEVERE, null, ex);
        }   
    }
    
    public void limpiartabla(){
       // tabla.setModel(new DefaultTableModel());
        for (int i = 0; i < tbl_lote.getRowCount(); i++) {
           modelo.removeRow(i);
           i-=1;
       }
    }

    
    void cmb_productoVariedad(String valor){
        try{
            String sql="select nom_producto from producto";
            Statement st = reg.createStatement();
            ResultSet rs = st.executeQuery(sql);
            while (rs.next()){
                cmb_nombreProducto.addItem(rs.getString(1));
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
    
    void cmbBodega(String valor){
        try{
            String sql="select nom_bodega from bodega";
            Statement st = reg.createStatement();
            ResultSet rs = st.executeQuery(sql);
            while (rs.next()){
                String name = rs.getString(1);
                cmb_bodega.addItem(name);
            }    
        }catch(Exception e){
                
        }
    }
    
    public int getCodProducto(){
        int codigo=0;
        try{
            String sql="select cod_producto from producto where nom_producto ='"+cmb_nombreProducto.getSelectedItem()+"' and variedad='"+cmb_variedad.getSelectedItem()+"' ";
            Statement st = reg.createStatement();
            ResultSet rs = st.executeQuery(sql);
            while (rs.next()){
                codigo = Integer.parseInt(rs.getString(1));
            }   
        }catch(Exception e){
            
        }
        return codigo;
    }
    
    public int getCodBodega(){
        int codigo=0;
        try{
            String sql="select cod_bodega from bodega where nom_bodega ='"+cmb_bodega.getSelectedItem()+"' ";
            Statement st = reg.createStatement();
            ResultSet rs = st.executeQuery(sql);
            while (rs.next()){
                codigo = Integer.parseInt(rs.getString(1));
            }   
        }catch(Exception e){
            
        }
        return codigo;
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
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        cmb_nombreProducto = new javax.swing.JComboBox();
        jButton1 = new javax.swing.JButton();
        txt_lote = new javax.swing.JTextField();
        txt_calibre = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        cmb_bodega = new javax.swing.JComboBox();
        jButton2 = new javax.swing.JButton();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        txt_kilosInicial = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        txt_kilosFinal = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        cmb_elaboracion = new com.toedter.calendar.JDateChooser();
        cmb_ingreso = new com.toedter.calendar.JDateChooser();
        cmb_variedad = new javax.swing.JComboBox();
        btn_eliminar = new javax.swing.JButton();
        btn_aceptar = new javax.swing.JButton();
        btn_modificar = new javax.swing.JButton();
        btn_cancelar = new javax.swing.JButton();
        btn_agregar = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        tbl_lote = new javax.swing.JTable();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jPanel1.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jLabel1.setText("Lote:");

        jLabel2.setText("Producto:");

        cmb_nombreProducto.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmb_nombreProductoActionPerformed(evt);
            }
        });

        jButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/iconos/add.png"))); // NOI18N
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jLabel3.setText("Bodega");

        jButton2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/iconos/add.png"))); // NOI18N
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jLabel4.setText("Calibre");

        jLabel5.setText("Kilos inicial");

        jLabel6.setText("Kilos final");

        jLabel7.setText("Elaboracion:");

        jLabel8.setText("Ingreso bodega:");

        btn_eliminar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/iconos/delete.png"))); // NOI18N
        btn_eliminar.setText("Eliminar");
        btn_eliminar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_eliminarActionPerformed(evt);
            }
        });

        btn_aceptar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/iconos/OK-20.png"))); // NOI18N
        btn_aceptar.setText("Aceptar");
        btn_aceptar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_aceptarActionPerformed(evt);
            }
        });

        btn_modificar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/iconos/update.png"))); // NOI18N
        btn_modificar.setText("Modificar");
        btn_modificar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_modificarActionPerformed(evt);
            }
        });

        btn_cancelar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/iconos/delete.png"))); // NOI18N
        btn_cancelar.setText("Cancelar");
        btn_cancelar.setToolTipText("");
        btn_cancelar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_cancelarActionPerformed(evt);
            }
        });

        btn_agregar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/iconos/add.png"))); // NOI18N
        btn_agregar.setText("Agregar");
        btn_agregar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_agregarActionPerformed(evt);
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
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel1)
                            .addComponent(jLabel4)
                            .addComponent(jLabel5)
                            .addComponent(jLabel7))
                        .addGap(56, 56, 56)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(txt_calibre, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txt_lote, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(cmb_elaboracion, javax.swing.GroupLayout.DEFAULT_SIZE, 181, Short.MAX_VALUE)
                            .addComponent(txt_kilosInicial))
                        .addGap(44, 44, 44)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel2)
                            .addComponent(jLabel3)
                            .addComponent(jLabel6)
                            .addComponent(jLabel8))
                        .addGap(38, 38, 38)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(cmb_ingreso, javax.swing.GroupLayout.PREFERRED_SIZE, 198, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 0, Short.MAX_VALUE))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(btn_agregar)
                                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(jPanel1Layout.createSequentialGroup()
                                            .addComponent(cmb_nombreProducto, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addGap(18, 18, 18)
                                            .addComponent(cmb_variedad, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addComponent(txt_kilosFinal, javax.swing.GroupLayout.PREFERRED_SIZE, 198, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(cmb_bodega, javax.swing.GroupLayout.PREFERRED_SIZE, 198, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)))))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(btn_eliminar)
                        .addGap(36, 36, 36)
                        .addComponent(btn_aceptar)
                        .addGap(52, 52, 52)
                        .addComponent(btn_modificar)
                        .addGap(72, 72, 72)
                        .addComponent(btn_cancelar)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(jLabel2)
                    .addComponent(cmb_nombreProducto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton1)
                    .addComponent(txt_lote, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cmb_variedad, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(txt_calibre, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel3)
                        .addComponent(cmb_bodega, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel4))
                    .addComponent(jButton2))
                .addGap(24, 24, 24)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(txt_kilosInicial, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel6)
                    .addComponent(txt_kilosFinal, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(35, 35, 35)
                        .addComponent(jLabel7))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(cmb_elaboracion, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel8, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(cmb_ingreso, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 51, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btn_eliminar)
                    .addComponent(btn_aceptar)
                    .addComponent(btn_modificar)
                    .addComponent(btn_cancelar)
                    .addComponent(btn_agregar))
                .addContainerGap())
        );

        tbl_lote.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane1.setViewportView(tbl_lote);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jScrollPane1))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 291, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void cmb_nombreProductoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmb_nombreProductoActionPerformed
        if (cmb_nombreProducto.getSelectedItem() ==null){
            
        }else{
            cmb_variedad(cmb_nombreProducto.getSelectedItem().toString());   
        }
    }//GEN-LAST:event_cmb_nombreProductoActionPerformed

    private void btn_agregarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_agregarActionPerformed
        lote dto = new lote();
        dto.setCod_lote(Integer.parseInt(txt_lote.getText()));
        dto.setCod_producto(getCodProducto());
        dto.setCalibre(Integer.parseInt(txt_calibre.getText()));
        dto.setKilos_inicial(Integer.parseInt(txt_kilosInicial.getText()));
        dto.setKilos_final(Integer.parseInt(txt_kilosFinal.getText()));
        Date fecha = cmb_elaboracion.getDate();
        java.sql.Date sqlfecha = new java.sql.Date(fecha.getTime());
        dto.setFecha(sqlfecha);
        
        bodegaLote dto2 = new bodegaLote();
        dto2.setCod_bodega(getCodBodega());
        dto2.setCod_lote(Integer.parseInt(txt_lote.getText()));
        Date fecha2 = cmb_ingreso.getDate();
        java.sql.Date sqlfecha2 = new java.sql.Date(fecha.getTime());
        dto2.setFecha(sqlfecha2);
        sql = "INSERT INTO lote (cod_lote, cod_producto, calibre, fecha, kilos_inicial, kilos_final) VALUES (?,?,?,?,?,?)";
        try {
            PreparedStatement pst = reg.prepareStatement(sql);
            pst.setInt(1, dto.getCod_lote());
            pst.setInt(2, dto.getCod_producto());
            pst.setInt(3, dto.getCalibre());
            pst.setDate(4, dto.getFecha());
            pst.setInt(5, dto.getKilos_inicial());
            pst.setInt(6, dto.getKilos_final());
            int n = pst.executeUpdate();
            if (n>0){
                JOptionPane.showMessageDialog(null,"Lote registrado satisfactoriamente.");
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null,"Error al agregar.");
        }
        sql= "INSERT INTO lote_bodega (cod_bodega, cod_lote, fecha_ingreso)VALUES (?,?,?)";
        try {
            PreparedStatement pst=reg.prepareStatement(sql);
            pst.setInt(1, dto2.getCod_bodega());
            pst.setInt(2, dto2.getCod_lote());
            pst.setDate(3, dto2.getFecha());
            int n = pst.executeUpdate();
            if (n>0){
                JOptionPane.showMessageDialog(null,"Lote bodega registrado satisfactoriamente.");
            }                
        }catch (SQLException ex) {
            JOptionPane.showMessageDialog(null,"Error al agregar.");
            //sw = 1;
        }
        txt_lote.setText("");
        txt_calibre.setText("");
        txt_kilosInicial.setText("");
        txt_kilosFinal.setText("");
        cmb_elaboracion.setCalendar(c2);
        cmb_ingreso.setCalendar(c2);
        limpiartabla();
        mostrardatostabla("");
    }//GEN-LAST:event_btn_agregarActionPerformed

    private void btn_modificarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_modificarActionPerformed
        int fila=tbl_lote.getSelectedRow();
        if (fila>=0){
            txt_lote.setText(tbl_lote.getValueAt(fila, 0).toString());
            cmb_nombreProducto.setSelectedItem(tbl_lote.getValueAt(fila, 1).toString());
            cmb_variedad.setSelectedItem(tbl_lote.getValueAt(fila, 2).toString());
            txt_calibre.setText(tbl_lote.getValueAt(fila, 3).toString());
            cmb_bodega.setSelectedItem(tbl_lote.getValueAt(fila, 4).toString());
            txt_kilosInicial.setText(tbl_lote.getValueAt(fila, 5).toString());
            txt_kilosFinal.setText(tbl_lote.getValueAt(fila, 6).toString());
            txt_lote.setEditable(false);
            txt_lote.setEnabled(false);
            cmb_bodega.setEnabled(false);
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
        Date fecha = cmb_elaboracion.getDate();
        java.sql.Date sqlfecha = new java.sql.Date(fecha.getTime());
        sql = "Update lote SET cod_producto="+getCodProducto()+",calibre="+Integer.parseInt(txt_calibre.getText())+",fecha='"+sqlfecha+"',kilos_inicial="+Integer.parseInt(txt_kilosInicial.getText())+",kilos_final="+Integer.parseInt(txt_kilosFinal.getText())+" WHERE cod_lote="+Integer.parseInt(txt_lote.getText())+"";
        try {
            PreparedStatement pst = reg.prepareStatement(sql);
            pst.executeUpdate();
            limpiartabla();
            mostrardatostabla("");
        } catch (Exception e) {
            System.out.println(e.getMessage());
            JOptionPane.showMessageDialog(null,e.getMessage());
        }
        
        Date fecha2 = cmb_ingreso.getDate();
        java.sql.Date sqlfecha2 = new java.sql.Date(fecha2.getTime());
        sql = "update lote_bodega set fecha_ingreso='"+sqlfecha2+"' where cod_bodega ="+getCodBodega()+" and cod_lote="+Integer.parseInt(txt_lote.getText())+" ";
        try{
            PreparedStatement pst = reg.prepareStatement(sql);
            pst.executeUpdate();
            limpiartabla();
            mostrardatostabla("");
        }catch (Exception e){
            System.out.println(e.getMessage());
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
        btn_aceptar.setVisible(false);
        btn_cancelar.setVisible(false);
        btn_eliminar.setVisible(true);
        btn_modificar.setVisible(true);
        btn_agregar.setVisible(true);
        cmb_bodega.setEnabled(true);
        txt_lote.setEnabled(true);
        txt_lote.setEditable(true);
        txt_lote.requestFocus();
        txt_lote.setText("");
        txt_kilosInicial.setText("");
        txt_kilosFinal.setText("");
        limpiartabla();
        mostrardatostabla("");
    }//GEN-LAST:event_btn_aceptarActionPerformed

    private void btn_cancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_cancelarActionPerformed
        btn_aceptar.setVisible(false);
        btn_cancelar.setVisible(false);
        btn_eliminar.setVisible(true);
        btn_modificar.setVisible(true);
        btn_agregar.setVisible(true);
        txt_lote.setEnabled(true);
        txt_lote.setEditable(true);
        txt_lote.requestFocus();
        txt_kilosInicial.setText("");
        txt_kilosFinal.setText("");
        txt_calibre.setText("");
        txt_lote.setText("");
        cmb_bodega.setEnabled(true);
    }//GEN-LAST:event_btn_cancelarActionPerformed

    private void btn_eliminarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_eliminarActionPerformed
        int fila = tbl_lote.getSelectedRow();
        if (fila >= 0){   
            if(JOptionPane.showConfirmDialog(null, new Object[]{"Seguro que desea Eliminar fila seleccionada?"},"Eliminar",JOptionPane.OK_CANCEL_OPTION)==JOptionPane.YES_OPTION){
            //qui se pone lo que hara si le das aceptar
                fila=tbl_lote.getSelectedRow();
                txt_lote.setText(tbl_lote.getValueAt(fila, 0).toString());
                try {
                    PreparedStatement pst = reg.prepareStatement("DELETE FROM lote_bodega WHERE cod_lote="+Integer.parseInt(txt_lote.getText())+" and cod_bodega = "+getCodBodega()+"");
                    pst.executeUpdate();
                    limpiartabla();
                    mostrardatostabla("");
                    txt_lote.setText("");
                } catch (Exception e) {
                    System.out.println(e.getMessage());
                }
                txt_lote.setText(tbl_lote.getValueAt(fila, 0).toString());
                try {
                    PreparedStatement pst = reg.prepareStatement("DELETE FROM lote WHERE cod_lote="+Integer.parseInt(txt_lote.getText())+"");
                    pst.executeUpdate();
                    limpiartabla();
                    mostrardatostabla("");
                    txt_lote.setText("");
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

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        mantenedorProductos abrirP = new mantenedorProductos();
        abrirP.setVisible(true);
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        mantenedorBodegas abrirB = new mantenedorBodegas();
        abrirB.setVisible(true);
    }//GEN-LAST:event_jButton2ActionPerformed

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
            java.util.logging.Logger.getLogger(loteBodega.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(loteBodega.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(loteBodega.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(loteBodega.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new loteBodega().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btn_aceptar;
    private javax.swing.JButton btn_agregar;
    private javax.swing.JButton btn_cancelar;
    private javax.swing.JButton btn_eliminar;
    private javax.swing.JButton btn_modificar;
    private javax.swing.JComboBox cmb_bodega;
    private com.toedter.calendar.JDateChooser cmb_elaboracion;
    private com.toedter.calendar.JDateChooser cmb_ingreso;
    private javax.swing.JComboBox cmb_nombreProducto;
    private javax.swing.JComboBox cmb_variedad;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable tbl_lote;
    private javax.swing.JTextField txt_calibre;
    private javax.swing.JTextField txt_kilosFinal;
    private javax.swing.JTextField txt_kilosInicial;
    private javax.swing.JTextField txt_lote;
    // End of variables declaration//GEN-END:variables
}
