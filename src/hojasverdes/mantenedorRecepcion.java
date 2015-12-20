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

        jScrollPane1 = new javax.swing.JScrollPane();
        tbl_recepcion = new javax.swing.JTable();
        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        txt_recepcion = new javax.swing.JTextField();
        cmb_proveedor = new javax.swing.JComboBox();
        jLabel2 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        cmb_patente = new javax.swing.JComboBox();
        cmb_fecha = new com.toedter.calendar.JDateChooser();
        jLabel7 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        cmb_campo = new javax.swing.JComboBox();
        cmb_envio = new javax.swing.JComboBox();
        cmb_chofer = new javax.swing.JComboBox();
        btn_eliminar = new javax.swing.JButton();
        btn_aceptar = new javax.swing.JButton();
        btn_modificar = new javax.swing.JButton();
        btn_cancelar = new javax.swing.JButton();
        btn_agregar = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        tbl_recepcion.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4", "Title 5", "Title 6", "Title 7"
            }
        ));
        jScrollPane1.setViewportView(tbl_recepcion);

        jPanel1.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel1.setText("Codigo recepcion:");
        jPanel1.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(12, 16, 110, -1));

        txt_recepcion.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txt_recepcionKeyTyped(evt);
            }
        });
        jPanel1.add(txt_recepcion, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 13, 152, -1));

        jPanel1.add(cmb_proveedor, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 66, 152, -1));

        jLabel2.setText("Proveedor:");
        jPanel1.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(12, 69, -1, -1));

        jLabel5.setText("Patente:");
        jPanel1.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(12, 118, -1, -1));

        jPanel1.add(cmb_patente, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 115, 152, -1));
        jPanel1.add(cmb_fecha, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 163, 160, -1));

        jLabel7.setText("Fecha recepcion:");
        jPanel1.add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(12, 163, 110, -1));

        jLabel6.setText("Chofer:");
        jPanel1.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(399, 118, -1, -1));

        jLabel4.setText("Guia envio:");
        jLabel4.setToolTipText("");
        jPanel1.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(399, 69, 80, -1));

        jLabel3.setText("Campo:");
        jPanel1.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(398, 16, -1, -1));

        jPanel1.add(cmb_campo, new org.netbeans.lib.awtextra.AbsoluteConstraints(489, 13, 158, -1));

        jPanel1.add(cmb_envio, new org.netbeans.lib.awtextra.AbsoluteConstraints(489, 66, 158, -1));

        jPanel1.add(cmb_chofer, new org.netbeans.lib.awtextra.AbsoluteConstraints(489, 115, 158, -1));

        btn_eliminar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/iconos/delete.png"))); // NOI18N
        btn_eliminar.setText("Eliminar");
        btn_eliminar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_eliminarActionPerformed(evt);
            }
        });
        jPanel1.add(btn_eliminar, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 210, -1, -1));

        btn_aceptar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/iconos/OK-20.png"))); // NOI18N
        btn_aceptar.setText("Aceptar");
        btn_aceptar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_aceptarActionPerformed(evt);
            }
        });
        jPanel1.add(btn_aceptar, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 210, -1, -1));

        btn_modificar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/iconos/update.png"))); // NOI18N
        btn_modificar.setText("Modificar");
        btn_modificar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_modificarActionPerformed(evt);
            }
        });
        jPanel1.add(btn_modificar, new org.netbeans.lib.awtextra.AbsoluteConstraints(280, 210, -1, -1));

        btn_cancelar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/iconos/delete.png"))); // NOI18N
        btn_cancelar.setText("Cancelar");
        btn_cancelar.setToolTipText("");
        btn_cancelar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_cancelarActionPerformed(evt);
            }
        });
        jPanel1.add(btn_cancelar, new org.netbeans.lib.awtextra.AbsoluteConstraints(390, 210, -1, -1));

        btn_agregar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/iconos/add.png"))); // NOI18N
        btn_agregar.setText("Agregar");
        btn_agregar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_agregarActionPerformed(evt);
            }
        });
        jPanel1.add(btn_agregar, new org.netbeans.lib.awtextra.AbsoluteConstraints(560, 210, -1, 29));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(10, 10, 10)
                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 662, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 662, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, 248, Short.MAX_VALUE)
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 338, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void btn_agregarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_agregarActionPerformed
        if (txt_recepcion.getText().equals("")){
            JOptionPane.showMessageDialog(null,"Debe ingresar codigo guia");
        }else{
            guiaRecepcion dto = new guiaRecepcion();
            dto.setCod_recepcion(Integer.parseInt(txt_recepcion.getText()));
            dto.setCod_campo(Integer.parseInt(cmb_campo.getSelectedItem().toString()));
            dto.setRut_proveedor(cmb_proveedor.getSelectedItem().toString());
            dto.setRut_chofer(cmb_chofer.getSelectedItem().toString());
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
                pst.setString(3, dto.getRut_proveedor());
                pst.setInt(4, dto.getCod_envio());
                pst.setString(5, dto.getPatente());
                pst.setString(6, dto.getRut_chofer());
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
        }
         
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
        txt_recepcion.setEditable(true);
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

    private void txt_recepcionKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_recepcionKeyTyped
        int limite = 5;
        if (txt_recepcion.getText().length() == limite) {
            evt.consume();
        }
        char t = evt.getKeyChar();
        if (Character.isLetter(t)) {
            evt.consume();
            JOptionPane.showMessageDialog(null, "Ingrese solo numeros");
        }   
    }//GEN-LAST:event_txt_recepcionKeyTyped

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
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable tbl_recepcion;
    private javax.swing.JTextField txt_recepcion;
    // End of variables declaration//GEN-END:variables
}
