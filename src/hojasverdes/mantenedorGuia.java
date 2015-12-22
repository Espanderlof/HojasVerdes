/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hojasverdes;

import dominio.camion;
import dominio.guiaEnvio;
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
public class mantenedorGuia extends javax.swing.JFrame {
        
    DefaultTableModel modelo = new DefaultTableModel();
    Calendar c2 = new GregorianCalendar();
    conectar cnx = new conectar();
    Connection reg= cnx.conexion();
    //private TableRowSorter trsfiltro;
    int columna=0;
    int sw = 0;
    String sql;

    public mantenedorGuia() {
        initComponents();
        this.setLocationRelativeTo(null);
        this.setTitle("Mantenedor Guia");
        tbl_guia.setAutoCreateRowSorter(true);
        //ingresa las columnas de tu tabla productos
        modelo.addColumn("Codigo guia");
        modelo.addColumn("Codigo campo");
        modelo.addColumn("RUT Proveedor");
        modelo.addColumn("RUT Chofer");
        modelo.addColumn("Patente");
        modelo.addColumn("Fecha");
        tbl_guia.setModel(modelo);
        btn_aceptar.setVisible(false);
        btn_cancelar.setVisible(false);
        cmb_date.setCalendar(c2);
        cmbCampo("");
        cmbProveedor("");
        cmbChofer("");
        cmbPatente("");
        mostrardatostabla("");
    }
    
    void mostrardatostabla(String valor){    
        String []datos=new String[6];
        String sql="";
        if(valor.equals("")){
            sql="SELECT * FROM guia_envio";
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
                modelo.addRow(datos);
            }
            tbl_guia.setModel(modelo);
            
        } catch (SQLException ex) {
            Logger.getLogger(producto.class.getName()).log(Level.SEVERE, null, ex);
        }   
    }
    
    public void limpiartabla(){
       // tabla.setModel(new DefaultTableModel());
        for (int i = 0; i < tbl_guia.getRowCount(); i++) {
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
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        grupoGuia = new javax.swing.ButtonGroup();
        jPanel1 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        cmb_campo = new javax.swing.JComboBox();
        jLabel4 = new javax.swing.JLabel();
        cmb_proveedor = new javax.swing.JComboBox();
        jLabel5 = new javax.swing.JLabel();
        cmb_patente = new javax.swing.JComboBox();
        jButton2 = new javax.swing.JButton();
        jLabel6 = new javax.swing.JLabel();
        btn_agregar = new javax.swing.JButton();
        btn_modificar = new javax.swing.JButton();
        btn_eliminar = new javax.swing.JButton();
        btn_aceptar = new javax.swing.JButton();
        btn_cancelar = new javax.swing.JButton();
        txt_guia = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        cmb_chofer = new javax.swing.JComboBox();
        cmb_date = new com.toedter.calendar.JDateChooser();
        jScrollPane1 = new javax.swing.JScrollPane();
        tbl_guia = new javax.swing.JTable();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setResizable(false);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent evt) {
                formWindowClosing(evt);
            }
        });

        jPanel1.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel2.setText("Codigo guia:");
        jPanel1.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(12, 19, 88, 20));

        jLabel3.setText("Codigo campo:");
        jPanel1.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(352, 22, -1, -1));

        jButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/iconos/add.png"))); // NOI18N
        jPanel1.add(jButton1, new org.netbeans.lib.awtextra.AbsoluteConstraints(609, 19, 27, -1));

        jPanel1.add(cmb_campo, new org.netbeans.lib.awtextra.AbsoluteConstraints(441, 19, 150, -1));

        jLabel4.setText("Rut proveedor:");
        jPanel1.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(12, 65, 88, -1));

        jPanel1.add(cmb_proveedor, new org.netbeans.lib.awtextra.AbsoluteConstraints(118, 62, 147, -1));

        jLabel5.setText("Patente:");
        jPanel1.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(352, 65, 71, -1));

        jPanel1.add(cmb_patente, new org.netbeans.lib.awtextra.AbsoluteConstraints(441, 62, 150, -1));

        jButton2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/iconos/add.png"))); // NOI18N
        jPanel1.add(jButton2, new org.netbeans.lib.awtextra.AbsoluteConstraints(609, 62, 27, -1));

        jLabel6.setText("Fecha:");
        jPanel1.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(12, 115, -1, -1));

        btn_agregar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/iconos/add.png"))); // NOI18N
        btn_agregar.setText("Agregar");
        btn_agregar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_agregarActionPerformed(evt);
            }
        });
        jPanel1.add(btn_agregar, new org.netbeans.lib.awtextra.AbsoluteConstraints(590, 160, -1, 29));

        btn_modificar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/iconos/update.png"))); // NOI18N
        btn_modificar.setText("Modificar");
        btn_modificar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_modificarActionPerformed(evt);
            }
        });
        jPanel1.add(btn_modificar, new org.netbeans.lib.awtextra.AbsoluteConstraints(300, 160, -1, -1));

        btn_eliminar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/iconos/delete.png"))); // NOI18N
        btn_eliminar.setText("Eliminar");
        btn_eliminar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_eliminarActionPerformed(evt);
            }
        });
        jPanel1.add(btn_eliminar, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 160, -1, -1));

        btn_aceptar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/iconos/OK-20.png"))); // NOI18N
        btn_aceptar.setText("Aceptar");
        btn_aceptar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_aceptarActionPerformed(evt);
            }
        });
        jPanel1.add(btn_aceptar, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 160, -1, -1));

        btn_cancelar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/iconos/delete.png"))); // NOI18N
        btn_cancelar.setText("Cancelar");
        btn_cancelar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_cancelarActionPerformed(evt);
            }
        });
        jPanel1.add(btn_cancelar, new org.netbeans.lib.awtextra.AbsoluteConstraints(410, 160, -1, -1));

        txt_guia.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txt_guiaKeyTyped(evt);
            }
        });
        jPanel1.add(txt_guia, new org.netbeans.lib.awtextra.AbsoluteConstraints(118, 19, 147, -1));

        jLabel1.setText("Chofer:");
        jPanel1.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(352, 115, -1, -1));

        jPanel1.add(cmb_chofer, new org.netbeans.lib.awtextra.AbsoluteConstraints(441, 112, 150, -1));
        jPanel1.add(cmb_date, new org.netbeans.lib.awtextra.AbsoluteConstraints(118, 112, 147, -1));

        tbl_guia.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null}
            },
            new String [] {
                "Guia", "Campo", "Proveedor", "Patente", "Fecha"
            }
        ));
        jScrollPane1.setViewportView(tbl_guia);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, 694, Short.MAX_VALUE)
                    .addComponent(jScrollPane1))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 199, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 299, Short.MAX_VALUE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btn_agregarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_agregarActionPerformed
        if (txt_guia.getText().equals("")){
            JOptionPane.showMessageDialog(null,"Debe ingresar codigo guia");
        }else{
            guiaEnvio dto = new guiaEnvio();
            dto.setCod_envio(Integer.parseInt(txt_guia.getText()));
            dto.setCod_campo(Integer.parseInt(cmb_campo.getSelectedItem().toString()));
            dto.setRut_proveedor(cmb_proveedor.getSelectedItem().toString());
            dto.setRut_chofer(cmb_chofer.getSelectedItem().toString());
            dto.setPatente(cmb_patente.getSelectedItem().toString());
            Date fecha = cmb_date.getDate();
            java.sql.Date sqlfecha = new java.sql.Date(fecha.getTime());
            dto.setFecha(sqlfecha);
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
                    JOptionPane.showMessageDialog(null,"Guia registrada satisfactoriamente.");
                }                
            }catch (SQLException ex) {
                JOptionPane.showMessageDialog(null,"Error al agregar, guia duplicada.");
                //sw = 1;
            }       
            cmb_date.setCalendar(c2);
            limpiartabla();
            mostrardatostabla(""); 
        }
        
    }//GEN-LAST:event_btn_agregarActionPerformed

    private void btn_modificarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_modificarActionPerformed
        int fila=tbl_guia.getSelectedRow();
        if (fila>=0){
            txt_guia.setText(tbl_guia.getValueAt(fila, 0).toString());
            cmb_campo.setSelectedItem(tbl_guia.getValueAt(fila, 1).toString());
            cmb_proveedor.setSelectedItem(tbl_guia.getValueAt(fila, 2).toString());
            cmb_chofer.setSelectedItem(tbl_guia.getValueAt(fila, 3).toString());
            cmb_patente.setSelectedItem(tbl_guia.getValueAt(fila, 4).toString());
            //txt_fecha.setText(tbl_guia.getValueAt(fila, 5).toString());
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
        Date fecha = cmb_date.getDate();
        java.sql.Date sqlfecha = new java.sql.Date(fecha.getTime());
        sql="UPDATE guia_envio SET cod_campo="+Integer.parseInt(cmb_campo.getSelectedItem().toString())+",rut_proveedor= "+Integer.parseInt(cmb_proveedor.getSelectedItem().toString())+", rut_chofer="+Integer.parseInt(cmb_chofer.getSelectedItem().toString())+", patente ='"+cmb_patente.getSelectedItem().toString()+"', fecha_envio='"+sqlfecha+"'  WHERE cod_envio="+Integer.parseInt(txt_guia.getText())+"";
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
        cmb_date.setCalendar(c2);
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
        cmb_date.setCalendar(c2);
    }//GEN-LAST:event_btn_cancelarActionPerformed

    private void btn_eliminarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_eliminarActionPerformed
        int fila = tbl_guia.getSelectedRow();
        if (fila >= 0){   
            if(JOptionPane.showConfirmDialog(null, new Object[]{"Seguro que desea Eliminar fila seleccionada?"},"Eliminar",JOptionPane.OK_CANCEL_OPTION)==JOptionPane.YES_OPTION){
            //qui se pone lo que hara si le das aceptar
                fila=tbl_guia.getSelectedRow();
                txt_guia.setText(tbl_guia.getValueAt(fila, 0).toString());
                try {
                    PreparedStatement pst = reg.prepareStatement("DELETE FROM guia_envio WHERE cod_envio="+Integer.parseInt(txt_guia.getText())+"");
                    pst.executeUpdate();
                    limpiartabla();
                    mostrardatostabla("");
                    txt_guia.setText("");
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

    private void formWindowClosing(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosing
        // TODO add your handling code here:
        cnx.cerrar();
    }//GEN-LAST:event_formWindowClosing

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
            java.util.logging.Logger.getLogger(mantenedorGuia.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(mantenedorGuia.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(mantenedorGuia.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(mantenedorGuia.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new mantenedorGuia().setVisible(true);
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
    private com.toedter.calendar.JDateChooser cmb_date;
    private javax.swing.JComboBox cmb_patente;
    private javax.swing.JComboBox cmb_proveedor;
    private javax.swing.ButtonGroup grupoGuia;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable tbl_guia;
    private javax.swing.JTextField txt_guia;
    // End of variables declaration//GEN-END:variables
}
