/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hojasverdes;

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
 * @author Fralkayg
 */
public class mantenedorRecepcion extends javax.swing.JFrame {
    DefaultTableModel modelo = new DefaultTableModel();
    Calendar c2 = new GregorianCalendar();
    conectar cnx = new conectar();
    Connection reg= cnx.conexion();
    //private TableRowSorter trsfiltro;
    int columna=0;
    int sw = 0;
    String sql;

    /**
     * Creates new form mantenedorRecepcion
     */
    public mantenedorRecepcion() {
        initComponents();
        this.setLocationRelativeTo(null);
        this.setTitle("Mantenedor Guia recepcion");
        tbl_recepcion.setAutoCreateRowSorter(true);
        //ingresa las columnas de tu tabla productos
        modelo.addColumn("Codigo guia");
        modelo.addColumn("Codigo campo");
        modelo.addColumn("RUT Proveedor");
        modelo.addColumn("Codigo envio");
        modelo.addColumn("Patente");
        modelo.addColumn("RUT Chofer");
        modelo.addColumn("Fecha");
        tbl_recepcion.setModel(modelo);
        btn_aceptar.setVisible(false);
        btn_cancelar.setVisible(false);
        cmb_fecha.setCalendar(c2);
        cmbCampo("");
        cmbProveedor("");
        cmbChofer("");
        cmbPatente("");
        cmbEnvio("");
        mostrardatostabla("");
    }
    
    void mostrardatostabla(String valor){    
        String []datos=new String[7];
        String sql="";
        if(valor.equals("")){
            sql="SELECT * FROM guia_recepcion";
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
            tbl_recepcion.setModel(modelo);
            
        } catch (SQLException ex) {
            Logger.getLogger(producto.class.getName()).log(Level.SEVERE, null, ex);
        }   
    }
    
    public void limpiartabla(){
       // tabla.setModel(new DefaultTableModel());
        for (int i = 0; i < tbl_recepcion.getRowCount(); i++) {
           modelo.removeRow(i);
           i-=1;
       }
    }
    
    void cmbCampo(String valor){
        try{
            String sql="select cod_campo from campo";
            Statement st = reg.createStatement();
            ResultSet rs = st.executeQuery(sql);
            while (rs.next()){
                String name = rs.getString(1);
                cmb_campo.addItem(name);
            }    
        }catch(Exception e){
                
        }
    }
    
    void cmbProveedor(String valor){
        try{
            String sql="select rut_proveedor from proveedor";
            Statement st = reg.createStatement();
            ResultSet rs = st.executeQuery(sql);
            while (rs.next()){
                String name = rs.getString(1);
                cmb_proveedor.addItem(name);
            }    
        }catch(Exception e){
                
        }
    }
    
    void cmbChofer(String valor){
        try{
            String sql="select distinct(rut_chofer) from camion_chofer";
            Statement st = reg.createStatement();
            ResultSet rs = st.executeQuery(sql);
            while (rs.next()){
                String name = rs.getString(1);
                cmb_chofer.addItem(name);
            }    
        }catch(Exception e){
                
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
    
    void cmbEnvio(String valor){
        try{
            String sql="select cod_envio from guia_envio";
            Statement st = reg.createStatement();
            ResultSet rs = st.executeQuery(sql);
            while (rs.next()){
                String name = rs.getString(1);
                cmb_envio.addItem(name);
            }    
        }catch(Exception e){
                
        }
    }
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        txt_recepcion = new javax.swing.JTextField();
        cmb_campo = new javax.swing.JComboBox();
        cmb_proveedor = new javax.swing.JComboBox();
        cmb_envio = new javax.swing.JComboBox();
        cmb_patente = new javax.swing.JComboBox();
        cmb_chofer = new javax.swing.JComboBox();
        cmb_fecha = new com.toedter.calendar.JDateChooser();
        jScrollPane1 = new javax.swing.JScrollPane();
        tbl_recepcion = new javax.swing.JTable();
        btn_eliminar = new javax.swing.JButton();
        btn_aceptar = new javax.swing.JButton();
        btn_modificar = new javax.swing.JButton();
        btn_cancelar = new javax.swing.JButton();
        btn_agregar = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jLabel1.setText("Codigo recepcion:");

        jLabel2.setText("Proveedor:");

        jLabel3.setText("Campo:");

        jLabel4.setText("Guia envio:");
        jLabel4.setToolTipText("");

        jLabel5.setText("Patente:");

        jLabel6.setText("Chofer:");

        jLabel7.setText("Fecha recepcion:");

        tbl_recepcion.setModel(new javax.swing.table.DefaultTableModel(
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
        jScrollPane1.setViewportView(tbl_recepcion);

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

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLabel2)
                            .addComponent(jLabel5)
                            .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, 110, Short.MAX_VALUE)
                            .addComponent(jLabel7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(txt_recepcion)
                                    .addComponent(cmb_patente, javax.swing.GroupLayout.Alignment.TRAILING, 0, 152, Short.MAX_VALUE)
                                    .addComponent(cmb_proveedor, javax.swing.GroupLayout.Alignment.TRAILING, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel3)
                                    .addGroup(layout.createSequentialGroup()
                                        .addGap(1, 1, 1)
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jLabel6)
                                            .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE))))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(cmb_campo, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(cmb_envio, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(cmb_chofer, 0, 158, Short.MAX_VALUE))
                                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(cmb_fecha, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(btn_eliminar)
                                .addGap(35, 35, 35)
                                .addComponent(btn_aceptar)
                                .addGap(38, 38, 38)
                                .addComponent(btn_modificar)
                                .addGap(48, 48, 48)
                                .addComponent(btn_cancelar)
                                .addGap(53, 53, 53)
                                .addComponent(btn_agregar))
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 665, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(0, 22, Short.MAX_VALUE))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(19, 19, 19)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(jLabel3)
                    .addComponent(txt_recepcion, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cmb_campo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(33, 33, 33)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(jLabel4)
                    .addComponent(cmb_proveedor, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cmb_envio, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(29, 29, 29)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cmb_patente, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel5)
                    .addComponent(jLabel6)
                    .addComponent(cmb_chofer, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(28, 28, 28)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel7)
                    .addComponent(cmb_fecha, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 28, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btn_eliminar)
                    .addComponent(btn_aceptar)
                    .addComponent(btn_modificar)
                    .addComponent(btn_cancelar)
                    .addComponent(btn_agregar, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 303, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btn_agregarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_agregarActionPerformed
        guiaRecepcion dto = new guiaRecepcion();
        dto.setCod_recepcion(Integer.parseInt(txt_recepcion.getText()));
        dto.setCod_campo(Integer.parseInt(cmb_campo.getSelectedItem().toString()));
        dto.setRut_proveedor(Integer.parseInt(cmb_proveedor.getSelectedItem().toString()));
        dto.setRut_chofer(Integer.parseInt(cmb_chofer.getSelectedItem().toString()));
        dto.setPatente(cmb_patente.getSelectedItem().toString());
        dto.setCod_envio(Integer.parseInt(cmb_envio.getSelectedItem().toString()));
        Date fecha = cmb_fecha.getDate();
        java.sql.Date sqlfecha = new java.sql.Date(fecha.getTime());
        dto.setFecha_recepcion(sqlfecha);
        sql= "INSERT INTO guia_recepcion (cod_recepcion, cod_campo, rut_proveedor, cod_envio, patente, rut_chofer, fecha_recepcion)VALUES (?,?,?,?,?,?,?)";
        try {
            PreparedStatement pst=reg.prepareStatement(sql);
            pst.setInt(1, dto.getCod_recepcion());
            pst.setInt(2, dto.getCod_campo());
            pst.setInt(3, dto.getRut_proveedor());
            pst.setInt(4, dto.getCod_envio());
            pst.setString(5, dto.getPatente());
            pst.setInt(6, dto.getRut_chofer());
            pst.setDate(7, dto.getFecha_recepcion());
            int n = pst.executeUpdate();
            if (n>0){
                JOptionPane.showMessageDialog(null,"Guia registrada satisfactoriamente.");
            }                
        }catch (SQLException ex) {
            JOptionPane.showMessageDialog(null,"Error al agregar, guia duplicada.");
            //sw = 1;
        }       
        cmb_fecha.setCalendar(c2);
        limpiartabla();
        mostrardatostabla("");     
    }//GEN-LAST:event_btn_agregarActionPerformed

    private void btn_modificarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_modificarActionPerformed
        int fila=tbl_recepcion.getSelectedRow();
        if (fila>=0){
            txt_recepcion.setText(tbl_recepcion.getValueAt(fila, 0).toString());
            cmb_campo.setSelectedItem(tbl_recepcion.getValueAt(fila, 1).toString());
            cmb_proveedor.setSelectedItem(tbl_recepcion.getValueAt(fila, 2).toString());
            cmb_envio.setSelectedItem(tbl_recepcion.getValueAt(fila, 3).toString());
            cmb_patente.setSelectedItem(tbl_recepcion.getValueAt(fila, 4).toString());
            cmb_chofer.setSelectedItem(tbl_recepcion.getValueAt(fila, 5).toString());
            //txt_fecha.setText(tbl_guia.getValueAt(fila, 5).toString());
            txt_recepcion.setEditable(false);
            txt_recepcion.setEnabled(false);
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
        Date fecha = cmb_fecha.getDate();
        java.sql.Date sqlfecha = new java.sql.Date(fecha.getTime());
        sql="UPDATE guia_recepcion SET cod_campo="+Integer.parseInt(cmb_campo.getSelectedItem().toString())+",rut_proveedor= "+Integer.parseInt(cmb_proveedor.getSelectedItem().toString())+", cod_envio= "+Integer.parseInt(cmb_envio.getSelectedItem().toString())+",  patente ='"+cmb_patente.getSelectedItem().toString()+"', rut_chofer="+Integer.parseInt(cmb_chofer.getSelectedItem().toString())+", fecha_recepcion='"+sqlfecha+"'  WHERE cod_recepcion="+Integer.parseInt(txt_recepcion.getText())+"";
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
        txt_recepcion.setEnabled(true);
        txt_recepcion.setEditable(true);
        txt_recepcion.requestFocus();
        cmb_fecha.setCalendar(c2);
        txt_recepcion.setText("");
        limpiartabla();
        mostrardatostabla("");
    }//GEN-LAST:event_btn_aceptarActionPerformed

    private void btn_cancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_cancelarActionPerformed
        btn_aceptar.setVisible(false);
        btn_cancelar.setVisible(false);
        btn_eliminar.setVisible(true);
        btn_modificar.setVisible(true);
        btn_agregar.setVisible(true);
        txt_recepcion.setEnabled(true);
        txt_recepcion.requestFocus();
        txt_recepcion.setText("");
        cmb_fecha.setCalendar(c2);
    }//GEN-LAST:event_btn_cancelarActionPerformed

    private void btn_eliminarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_eliminarActionPerformed
        int fila = tbl_recepcion.getSelectedRow();
        if (fila >= 0){   
            if(JOptionPane.showConfirmDialog(null, new Object[]{"Seguro que desea Eliminar fila seleccionada?"},"Eliminar",JOptionPane.OK_CANCEL_OPTION)==JOptionPane.YES_OPTION){
            //qui se pone lo que hara si le das aceptar
                fila=tbl_recepcion.getSelectedRow();
                txt_recepcion.setText(tbl_recepcion.getValueAt(fila, 0).toString());
                try {
                    PreparedStatement pst = reg.prepareStatement("DELETE FROM guia_recepcion WHERE cod_recepcion="+Integer.parseInt(txt_recepcion.getText())+"");
                    pst.executeUpdate();
                    limpiartabla();
                    mostrardatostabla("");
                    txt_recepcion.setText("");
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
            java.util.logging.Logger.getLogger(mantenedorRecepcion.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(mantenedorRecepcion.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(mantenedorRecepcion.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(mantenedorRecepcion.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new mantenedorRecepcion().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btn_aceptar;
    private javax.swing.JButton btn_agregar;
    private javax.swing.JButton btn_cancelar;
    private javax.swing.JButton btn_eliminar;
    private javax.swing.JButton btn_modificar;
    private javax.swing.JComboBox cmb_campo;
    private javax.swing.JComboBox cmb_chofer;
    private javax.swing.JComboBox cmb_envio;
    private com.toedter.calendar.JDateChooser cmb_fecha;
    private javax.swing.JComboBox cmb_patente;
    private javax.swing.JComboBox cmb_proveedor;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable tbl_recepcion;
    private javax.swing.JTextField txt_recepcion;
    // End of variables declaration//GEN-END:variables
}
