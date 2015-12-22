/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hojasverdes;

import dominio.camion;
import dominio.fechas;
import dominio.camionChofer;
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
import java.text.DateFormat;
import java.text.SimpleDateFormat;

/**
 *
 * @author Fralkayg
 */
public class mantenedorCamionChofer extends javax.swing.JFrame {
    
    DefaultTableModel modelo = new DefaultTableModel();
    Calendar c2 = new GregorianCalendar();
    conectar cnx = new conectar();
    Connection reg= cnx.conexion();
    //private TableRowSorter trsfiltro;
    int columna=0;
    int sw = 0;
    String sql;

    /**
     * Creates new form mantenedorCamionChofer
     */
    public mantenedorCamionChofer() {
        initComponents();
        this.setLocationRelativeTo(null);
        this.setTitle("Mantenedor Camion-Chofer");
        tbl_camionChofer.setAutoCreateRowSorter(true);
        //ingresa las columnas de tu tabla productos
        modelo.addColumn("Patente");
        modelo.addColumn("Chofer");
        modelo.addColumn("Fecha uso");
        modelo.addColumn("Hora uso");
        tbl_camionChofer.setModel(modelo);
        btn_aceptar.setVisible(false);
        btn_cancelar.setVisible(false);
        cmb_date.setCalendar(c2);
        txt_fechapass.setVisible(false);
        mostrardatostabla("");
        cmbPatente("");
        cmbChofer("");
    }
    
    void mostrardatostabla(String valor){    
        String []datos=new String[4];
        int cod;
        String sql="";
        if(valor.equals("")){
            sql="SELECT * FROM camion_chofer";
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
                
                //Date date = new Date();
                //date = rs.getDate(3);
                //fechas.deDateToString(rs.getDate(3));
                //System.out.println(date.toString());
                //DateFormat fecha = new SimpleDateFormat("dd-MM-yyyy");
		//String dateConvertido = fecha.format(date);
		//System.out.println(dateConvertido);
                datos[2]=fechas.deDateToString(rs.getDate(3));;
                //datos[2]=dateConvertido;
                datos[3]=rs.getString(4);
                modelo.addRow(datos);
                Calendar c3 = new GregorianCalendar();
                cmb_date.setCalendar(c3);
                
            }
            tbl_camionChofer.setModel(modelo);
            
        } catch (SQLException ex) {
            Logger.getLogger(producto.class.getName()).log(Level.SEVERE, null, ex);
        }   
    }
    
    public void limpiartabla(){
       // tabla.setModel(new DefaultTableModel());
        for (int i = 0; i < tbl_camionChofer.getRowCount(); i++) {
           modelo.removeRow(i);
           i-=1;
       }
    }
    
    void cmbPatente(String valor){
        try{
            cmb_patente.removeAllItems();
            String sql="select patente from camion order by patente";
            Statement st = reg.createStatement();
            ResultSet rs = st.executeQuery(sql);
            while (rs.next()){
                cmb_patente.addItem(rs.getString(1));
            }    
        }catch(Exception e){
            }
    }
    
    void cmbChofer(String valor){
        try{
            cmb_chofer.removeAllItems();
            String sql="select nom_chofer from chofer order by nom_chofer";
            Statement st = reg.createStatement();
            ResultSet rs = st.executeQuery(sql);
            while (rs.next()){
                cmb_chofer.addItem(rs.getString(1));
            }    
        }catch(Exception e){
            }
    }

    public String getRutChofer(){
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
            String sql="select nom_chofer from chofer where rut_chofer ="+tbl_camionChofer.getValueAt(fila, 1).toString()+"";
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
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        btn_eliminar = new javax.swing.JButton();
        btn_aceptar = new javax.swing.JButton();
        btn_modificar = new javax.swing.JButton();
        btn_cancelar = new javax.swing.JButton();
        btn_agregar = new javax.swing.JButton();
        cmb_date = new com.toedter.calendar.JDateChooser();
        txt_hora = new javax.swing.JTextField();
        cmb_chofer = new javax.swing.JComboBox();
        cmb_patente = new javax.swing.JComboBox();
        btn_patente = new javax.swing.JButton();
        btn_chofer = new javax.swing.JButton();
        btn_refrescar = new javax.swing.JButton();
        txt_fechapass = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tbl_camionChofer = new javax.swing.JTable();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent evt) {
                formWindowClosing(evt);
            }
        });

        jPanel1.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel1.setText("Patente:");
        jPanel1.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(12, 16, 76, -1));

        jLabel2.setText("Chofer:");
        jPanel1.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(371, 16, -1, -1));

        jLabel3.setText("Fecha uso:");
        jPanel1.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(12, 77, -1, -1));

        jLabel4.setText("Hora uso:");
        jPanel1.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(371, 77, -1, -1));

        btn_eliminar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/iconos/delete.png"))); // NOI18N
        btn_eliminar.setText("Eliminar");
        btn_eliminar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_eliminarActionPerformed(evt);
            }
        });
        jPanel1.add(btn_eliminar, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 120, -1, -1));

        btn_aceptar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/iconos/OK-20.png"))); // NOI18N
        btn_aceptar.setText("Aceptar");
        btn_aceptar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_aceptarActionPerformed(evt);
            }
        });
        jPanel1.add(btn_aceptar, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 120, -1, -1));

        btn_modificar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/iconos/update.png"))); // NOI18N
        btn_modificar.setText("Modificar");
        btn_modificar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_modificarActionPerformed(evt);
            }
        });
        jPanel1.add(btn_modificar, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 120, -1, -1));

        btn_cancelar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/iconos/delete.png"))); // NOI18N
        btn_cancelar.setText("Cancelar");
        btn_cancelar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_cancelarActionPerformed(evt);
            }
        });
        jPanel1.add(btn_cancelar, new org.netbeans.lib.awtextra.AbsoluteConstraints(390, 120, -1, -1));

        btn_agregar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/iconos/add.png"))); // NOI18N
        btn_agregar.setText("Agregar");
        btn_agregar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_agregarActionPerformed(evt);
            }
        });
        jPanel1.add(btn_agregar, new org.netbeans.lib.awtextra.AbsoluteConstraints(560, 120, -1, 30));
        jPanel1.add(cmb_date, new org.netbeans.lib.awtextra.AbsoluteConstraints(106, 74, 163, -1));

        txt_hora.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_horaActionPerformed(evt);
            }
        });
        txt_hora.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txt_horaKeyTyped(evt);
            }
        });
        jPanel1.add(txt_hora, new org.netbeans.lib.awtextra.AbsoluteConstraints(445, 74, 196, -1));

        jPanel1.add(cmb_chofer, new org.netbeans.lib.awtextra.AbsoluteConstraints(445, 13, 157, -1));

        jPanel1.add(cmb_patente, new org.netbeans.lib.awtextra.AbsoluteConstraints(106, 13, 163, -1));

        btn_patente.setIcon(new javax.swing.ImageIcon(getClass().getResource("/iconos/add.png"))); // NOI18N
        btn_patente.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_patenteActionPerformed(evt);
            }
        });
        jPanel1.add(btn_patente, new org.netbeans.lib.awtextra.AbsoluteConstraints(287, 13, 29, 20));

        btn_chofer.setIcon(new javax.swing.ImageIcon(getClass().getResource("/iconos/add.png"))); // NOI18N
        btn_chofer.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_choferActionPerformed(evt);
            }
        });
        jPanel1.add(btn_chofer, new org.netbeans.lib.awtextra.AbsoluteConstraints(620, 13, 29, 20));

        btn_refrescar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/iconos/refresh-20.png"))); // NOI18N
        btn_refrescar.setText("Refrescar");
        btn_refrescar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_refrescarActionPerformed(evt);
            }
        });
        jPanel1.add(btn_refrescar, new org.netbeans.lib.awtextra.AbsoluteConstraints(350, 120, 110, 30));

        txt_fechapass.setText("fecha pass");
        jPanel1.add(txt_fechapass, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 50, 70, 20));

        tbl_camionChofer.setModel(new javax.swing.table.DefaultTableModel(
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
        jScrollPane1.setViewportView(tbl_camionChofer);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 660, Short.MAX_VALUE)
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 320, Short.MAX_VALUE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btn_patenteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_patenteActionPerformed
        mantenedorCamiones abrirCamiones = new mantenedorCamiones();
        abrirCamiones.setVisible(true);
    }//GEN-LAST:event_btn_patenteActionPerformed

    private void btn_choferActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_choferActionPerformed
        mantenedorChoferes abrirChoferes = new mantenedorChoferes();
        abrirChoferes.setVisible(true);
    }//GEN-LAST:event_btn_choferActionPerformed

    private void btn_agregarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_agregarActionPerformed
        if (txt_hora.getText().equals("")){
            JOptionPane.showMessageDialog(null,"Debe ingresar hora uso");
        }else{
            camionChofer dto = new camionChofer();
            dto.setPatente(cmb_patente.getSelectedItem().toString());
            dto.setRut_chofer(getRutChofer());

            Date fecha = cmb_date.getDate();
            java.sql.Date sqlfecha = new java.sql.Date(fecha.getTime());
            dto.setFecha(sqlfecha);

            dto.setHora_uso(txt_hora.getText());

            sql= "INSERT INTO camion_chofer (patente, rut_chofer, fecha_uso, hora_uso) VALUES (?,?,?,?)";
            try {
                PreparedStatement pst=reg.prepareStatement(sql);
                pst.setString(1, dto.getPatente());
                pst.setString(2, dto.getRut_chofer());
                pst.setDate(3, dto.getFecha());
                pst.setString(4, dto.getHora_uso());
                int n = pst.executeUpdate();
                if (n>0){
                    JOptionPane.showMessageDialog(null,"Registrado satisfactoriamente");
                }                
            }catch (SQLException ex) {
                JOptionPane.showMessageDialog(null,"Error al agregar.");
                //sw = 1;
            }
            txt_hora.setText("");
            txt_fechapass.setText("");
            limpiartabla();
            mostrardatostabla("");
        }
         
    }//GEN-LAST:event_btn_agregarActionPerformed

    private void btn_modificarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_modificarActionPerformed
        int fila=tbl_camionChofer.getSelectedRow();
        if (fila>=0){
            cmb_patente.setSelectedItem(tbl_camionChofer.getValueAt(fila, 0).toString());
            cmb_chofer.setSelectedItem(getNomChofer(fila));
            txt_fechapass.setText(tbl_camionChofer.getValueAt(fila, 2).toString());
            Date fecha = fechas.deStringToDate(tbl_camionChofer.getValueAt(fila, 2).toString());
            cmb_date.setDate(fecha);
            txt_hora.setText(tbl_camionChofer.getValueAt(fila, 3).toString());
            cmb_patente.setEnabled(false);
            cmb_chofer.setEnabled(false);
            btn_agregar.setVisible(false);
            btn_modificar.setVisible(false);
            btn_eliminar.setVisible(false);
            btn_chofer.setVisible(false);
            btn_patente.setVisible(false);
            btn_refrescar.setVisible(false);
            btn_aceptar.setVisible(true);
            btn_cancelar.setVisible(true);
        }else{
            JOptionPane.showMessageDialog(null,"No selecciono fila");
        }
    }//GEN-LAST:event_btn_modificarActionPerformed

    private void btn_aceptarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_aceptarActionPerformed
        if (txt_hora.getText().equals("")){
            JOptionPane.showMessageDialog(null,"Debe ingresar hora uso");
        }else{
            Date fecha = cmb_date.getDate();
            //JOptionPane.showMessageDialog(null,fecha);
            java.sql.Date sqlfecha = new java.sql.Date(fecha.getTime());
            //JOptionPane.showMessageDialog(null,sqlfecha);
            Date sqlfechaclave1 = fechas.deStringToDate(txt_fechapass.getText());
            java.sql.Date sqlfechaclave = new java.sql.Date(sqlfechaclave1.getTime());
            sql="UPDATE camion_chofer SET fecha_uso='"+sqlfecha+"',hora_uso='"+txt_hora.getText()+"' WHERE patente='"+cmb_patente.getSelectedItem().toString()+"' and rut_chofer='"+getRutChofer()+"' and fecha_uso='"+sqlfechaclave+"'";
            try {
                PreparedStatement pst = reg.prepareStatement(sql);
                pst.executeUpdate();
                limpiartabla();
                mostrardatostabla("");
                //JOptionPane.showMessageDialog(null,"actualizo");
            } catch (Exception e) {
                System.out.println(e.getMessage());
                JOptionPane.showMessageDialog(null,e.getMessage());
            }
            btn_aceptar.setVisible(false);
            btn_cancelar.setVisible(false);
            btn_eliminar.setVisible(true);
            btn_modificar.setVisible(true);
            btn_agregar.setVisible(true);
            cmb_patente.setEnabled(true);
            cmb_chofer.setEnabled(true);
            btn_chofer.setVisible(true);
            btn_patente.setVisible(true);
            btn_refrescar.setVisible(true);
            cmb_patente.requestFocus();
            txt_hora.setText("");
            txt_fechapass.setText("");
            limpiartabla();
            mostrardatostabla("");
        }

    }//GEN-LAST:event_btn_aceptarActionPerformed

    private void btn_cancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_cancelarActionPerformed
        btn_aceptar.setVisible(false);
        btn_cancelar.setVisible(false);
        btn_eliminar.setVisible(true);
        btn_modificar.setVisible(true);
        btn_agregar.setVisible(true);
        cmb_patente.setEnabled(true);
        cmb_chofer.setEnabled(true);
        btn_chofer.setVisible(true);
        btn_patente.setVisible(true);
        btn_refrescar.setVisible(true);
        cmb_patente.requestFocus();
        txt_hora.setText("");
        txt_fechapass.setText("");
        limpiartabla();
        mostrardatostabla("");
    }//GEN-LAST:event_btn_cancelarActionPerformed

    private void btn_eliminarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_eliminarActionPerformed
        int fila = tbl_camionChofer.getSelectedRow();
        if (fila >= 0){   
            if(JOptionPane.showConfirmDialog(null, new Object[]{"Seguro que desea Eliminar fila seleccionada?"},"Eliminar",JOptionPane.OK_CANCEL_OPTION)==JOptionPane.YES_OPTION){
            //qui se pone lo que hara si le das aceptar
                fila=tbl_camionChofer.getSelectedRow();
                String patente = tbl_camionChofer.getValueAt(fila, 0).toString();
                txt_fechapass.setText(tbl_camionChofer.getValueAt(fila, 2).toString());
                String chofer = getRutChofer();
                try {
                    PreparedStatement pst = reg.prepareStatement("DELETE FROM camion_chofer WHERE patente='"+
                            patente+"' and rut_chofer ='"+chofer+"' and fecha_uso='"+txt_fechapass.getText()+"'");
                    pst.executeUpdate();
                    limpiartabla();
                    txt_fechapass.setText("");
                    mostrardatostabla("");
                } catch (Exception e) {
                    System.out.println(e.getMessage());
                    JOptionPane.showMessageDialog(null,e.getMessage());
                }
            }else{
            //aqui se pone lo que hara si le das cancelar
            }
        }else{
            JOptionPane.showMessageDialog(null,"Debe seleccionar una fila antes de eliminar.");
        }
    }//GEN-LAST:event_btn_eliminarActionPerformed

    private void txt_horaKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_horaKeyTyped
        int limite = 5;
        if (txt_hora.getText().length() == limite) {
            evt.consume();
        }
    }//GEN-LAST:event_txt_horaKeyTyped

    private void txt_horaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_horaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_horaActionPerformed

    private void formWindowClosing(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosing
        // TODO add your handling code here:
        cnx.cerrar();
    }//GEN-LAST:event_formWindowClosing

    private void btn_refrescarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_refrescarActionPerformed
        cmb_date.setCalendar(c2);
        cmbPatente("");
        cmbChofer("");
        limpiartabla();
        mostrardatostabla("");
    }//GEN-LAST:event_btn_refrescarActionPerformed

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
            java.util.logging.Logger.getLogger(mantenedorCamionChofer.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(mantenedorCamionChofer.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(mantenedorCamionChofer.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(mantenedorCamionChofer.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new mantenedorCamionChofer().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btn_aceptar;
    private javax.swing.JButton btn_agregar;
    private javax.swing.JButton btn_cancelar;
    private javax.swing.JButton btn_chofer;
    private javax.swing.JButton btn_eliminar;
    private javax.swing.JButton btn_modificar;
    private javax.swing.JButton btn_patente;
    private javax.swing.JButton btn_refrescar;
    private javax.swing.JComboBox cmb_chofer;
    private com.toedter.calendar.JDateChooser cmb_date;
    private javax.swing.JComboBox cmb_patente;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable tbl_camionChofer;
    private javax.swing.JLabel txt_fechapass;
    private javax.swing.JTextField txt_hora;
    // End of variables declaration//GEN-END:variables
}
