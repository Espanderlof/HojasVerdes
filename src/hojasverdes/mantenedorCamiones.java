/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hojasverdes;

import dominio.camion;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Fralkayg
 */
public class mantenedorCamiones extends javax.swing.JFrame {
    
    DefaultTableModel modelo = new DefaultTableModel();
    conectar cnx = new conectar();
    Connection reg= cnx.conexion();
    //private TableRowSorter trsfiltro;
    int columna=0;
    int sw = 0;
    String sql;

    public mantenedorCamiones() {
        initComponents();
        this.setLocationRelativeTo(null);
        this.setTitle("Mantenedor Camiones");
        tbl_camiones.setAutoCreateRowSorter(true);
        //ingresa las columnas de tu tabla productos
        modelo.addColumn("Patente");
        modelo.addColumn("Color");
        modelo.addColumn("Capacidad");
        modelo.addColumn("Ejes");
        tbl_camiones.setModel(modelo);
        btn_aceptar.setVisible(false);
        btn_cancelar.setVisible(false);
        mostrardatostabla("");
    }
    
    void mostrardatostabla(String valor){    
        String []datos=new String[4];
        int cod;
        String sql="";
        if(valor.equals("")){
            sql="SELECT * FROM camion";
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
                modelo.addRow(datos);
            }
            tbl_camiones.setModel(modelo);
            
        } catch (SQLException ex) {
            Logger.getLogger(producto.class.getName()).log(Level.SEVERE, null, ex);
        }   
    }
    
    public void limpiartabla(){
       // tabla.setModel(new DefaultTableModel());
        for (int i = 0; i < tbl_camiones.getRowCount(); i++) {
           modelo.removeRow(i);
           i-=1;
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

        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        lblColor = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        txt_patente = new javax.swing.JTextField();
        txt_capacidad = new javax.swing.JTextField();
        txt_color = new javax.swing.JTextField();
        txt_ejes = new javax.swing.JTextField();
        btn_agregar = new javax.swing.JButton();
        btn_modificar = new javax.swing.JButton();
        btn_eliminar = new javax.swing.JButton();
        btn_aceptar = new javax.swing.JButton();
        btn_cancelar = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        tbl_camiones = new javax.swing.JTable();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent evt) {
                formWindowClosing(evt);
            }
        });

        jPanel1.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel1.setText("Patente:");
        jPanel1.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 14, 50, 20));

        lblColor.setText("Color:");
        jPanel1.add(lblColor, new org.netbeans.lib.awtextra.AbsoluteConstraints(340, 10, 62, 20));

        jLabel3.setText("Capacidad (Kg):");
        jPanel1.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 64, 80, 20));

        jLabel4.setText("Ejes:");
        jPanel1.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(340, 60, 50, 20));

        txt_patente.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txt_patenteKeyTyped(evt);
            }
        });
        jPanel1.add(txt_patente, new org.netbeans.lib.awtextra.AbsoluteConstraints(94, 13, 167, -1));

        txt_capacidad.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txt_capacidadKeyTyped(evt);
            }
        });
        jPanel1.add(txt_capacidad, new org.netbeans.lib.awtextra.AbsoluteConstraints(94, 63, 167, -1));

        txt_color.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txt_colorKeyTyped(evt);
            }
        });
        jPanel1.add(txt_color, new org.netbeans.lib.awtextra.AbsoluteConstraints(423, 13, 195, -1));

        txt_ejes.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txt_ejesKeyTyped(evt);
            }
        });
        jPanel1.add(txt_ejes, new org.netbeans.lib.awtextra.AbsoluteConstraints(423, 63, 195, -1));

        btn_agregar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/iconos/add.png"))); // NOI18N
        btn_agregar.setText("Agregar");
        btn_agregar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_agregarActionPerformed(evt);
            }
        });
        jPanel1.add(btn_agregar, new org.netbeans.lib.awtextra.AbsoluteConstraints(551, 111, -1, 29));

        btn_modificar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/iconos/update.png"))); // NOI18N
        btn_modificar.setText("Modificar");
        btn_modificar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_modificarActionPerformed(evt);
            }
        });
        jPanel1.add(btn_modificar, new org.netbeans.lib.awtextra.AbsoluteConstraints(283, 111, -1, -1));

        btn_eliminar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/iconos/delete.png"))); // NOI18N
        btn_eliminar.setText("Eliminar");
        btn_eliminar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_eliminarActionPerformed(evt);
            }
        });
        jPanel1.add(btn_eliminar, new org.netbeans.lib.awtextra.AbsoluteConstraints(12, 111, -1, -1));

        btn_aceptar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/iconos/OK-20.png"))); // NOI18N
        btn_aceptar.setText("Aceptar");
        btn_aceptar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_aceptarActionPerformed(evt);
            }
        });
        jPanel1.add(btn_aceptar, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 110, -1, -1));

        btn_cancelar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/iconos/delete.png"))); // NOI18N
        btn_cancelar.setText("Cancelar");
        btn_cancelar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_cancelarActionPerformed(evt);
            }
        });
        jPanel1.add(btn_cancelar, new org.netbeans.lib.awtextra.AbsoluteConstraints(400, 110, -1, -1));

        tbl_camiones.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Patente", "Rut chofer", "Capacidad", "Ejes"
            }
        ));
        jScrollPane1.setViewportView(tbl_camiones);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 651, Short.MAX_VALUE)
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(13, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, 148, Short.MAX_VALUE)
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 249, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btn_agregarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_agregarActionPerformed
        if (txt_patente.getText().equals("")){
            JOptionPane.showMessageDialog(null,"Debe ingresar patente");
        }else{
            if (txt_color.getText().equals("")){
                JOptionPane.showMessageDialog(null,"Debe ingresar color");
            }else{
                if (txt_capacidad.getText().equals("")){
                    JOptionPane.showMessageDialog(null,"Debe ingresar capacidad");
                }else{
                    if (txt_ejes.getText().equals("")){
                        JOptionPane.showMessageDialog(null,"Debe ingresar ejes");
                    }else{
                        camion dto = new camion();
                        dto.setPatente(txt_patente.getText());
                        dto.setColor(txt_color.getText());
                        dto.setCapacidad(Integer.parseInt(txt_capacidad.getText()));
                        dto.setEjes(Integer.parseInt(txt_ejes.getText()));
                        sql= "INSERT INTO camion (patente, color, capacidad, ejes)VALUES (?,?,?,?)";
                        try {
                            PreparedStatement pst=reg.prepareStatement(sql);
                            pst.setString(1, dto.getPatente());
                            pst.setString(2, dto.getColor());
                            pst.setInt(3, dto.getCapacidad());
                            pst.setInt(4, dto.getEjes());
                            int n = pst.executeUpdate();
                            if (n>0){
                                JOptionPane.showMessageDialog(null,"Camion registrado satisfactoriamente.");
                            }                
                        }catch (SQLException ex) {
                            JOptionPane.showMessageDialog(null,"Error al agregar, patente duplicada.");
                            //sw = 1;
                        }       
                        txt_patente.setText("");
                        txt_color.setText("");
                        txt_capacidad.setText("");
                        txt_ejes.setText("");
                        limpiartabla();
                        mostrardatostabla(""); 
                    }
                }
            }
        }
        
    }//GEN-LAST:event_btn_agregarActionPerformed

    private void btn_modificarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_modificarActionPerformed
        int fila=tbl_camiones.getSelectedRow();
        if (fila>=0){
            txt_patente.setText(tbl_camiones.getValueAt(fila, 0).toString());
            txt_color.setText(tbl_camiones.getValueAt(fila, 1).toString());
            txt_capacidad.setText(tbl_camiones.getValueAt(fila, 2).toString());
            txt_ejes.setText(tbl_camiones.getValueAt(fila, 3).toString());
            txt_patente.setEditable(false);
            txt_patente.setEnabled(false);
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
        if (txt_patente.getText().equals("")){
            JOptionPane.showMessageDialog(null,"Debe ingresar patente");
        }else{
            if (txt_color.getText().equals("")){
                JOptionPane.showMessageDialog(null,"Campo color no puede quedar vacio");
            }else{
                if (txt_capacidad.getText().equals("")){
                    JOptionPane.showMessageDialog(null,"Campo capacidad no puede quedar vacio");
                }else{
                    if (txt_ejes.getText().equals("")){
                        JOptionPane.showMessageDialog(null,"Campo ejes no puede quedar vacio");
                    }else{
                        sql="UPDATE camion SET color='"+txt_color.getText()+"',Capacidad= "+Integer.parseInt(txt_capacidad.getText())+", Ejes="+Integer.parseInt(txt_ejes.getText())+"  WHERE Patente='"+txt_patente.getText()+"'";
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
                        txt_patente.setEnabled(true);
                        txt_patente.setEditable(true);
                        txt_patente.requestFocus();
                        txt_patente.setText("");
                        txt_color.setText("");
                        txt_capacidad.setText("");
                        txt_ejes.setText("");
                        limpiartabla();
                        mostrardatostabla("");
                    }
                }
            }
        }
    }//GEN-LAST:event_btn_aceptarActionPerformed

    private void btn_cancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_cancelarActionPerformed
        btn_aceptar.setVisible(false);
        btn_cancelar.setVisible(false);
        btn_eliminar.setVisible(true);
        btn_modificar.setVisible(true);
        btn_agregar.setVisible(true);
        txt_patente.setEnabled(true);
        txt_patente.setEditable(true);
        txt_patente.requestFocus();
        txt_patente.setText("");
        txt_color.setText("");
        txt_capacidad.setText("");
        txt_ejes.setText("");
    }//GEN-LAST:event_btn_cancelarActionPerformed

    private void btn_eliminarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_eliminarActionPerformed
        int fila = tbl_camiones.getSelectedRow();
        if (fila >= 0){   
            if(JOptionPane.showConfirmDialog(null, new Object[]{"Seguro que desea Eliminar fila seleccionada?"},"Eliminar",JOptionPane.OK_CANCEL_OPTION)==JOptionPane.YES_OPTION){
            //qui se pone lo que hara si le das aceptar
                fila=tbl_camiones.getSelectedRow();
                txt_patente.setText(tbl_camiones.getValueAt(fila, 0).toString());
                try {
                    PreparedStatement pst = reg.prepareStatement("DELETE FROM camion WHERE patente='"+txt_patente.getText()+"'");
                    pst.executeUpdate();
                    limpiartabla();
                    mostrardatostabla("");
                    txt_patente.setText("");
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

    private void txt_patenteKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_patenteKeyTyped
        int limite = 6;
        if (txt_patente.getText().length() == limite) {
            evt.consume();
        }
    }//GEN-LAST:event_txt_patenteKeyTyped

    private void txt_colorKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_colorKeyTyped
        int limite = 10;
        if (txt_color.getText().length() == limite) {
            evt.consume();
        }
        char t = evt.getKeyChar();
        if (Character.isDigit(t)) {
            evt.consume();
            JOptionPane.showMessageDialog(null, "Ingrese solo letras");
        }
    }//GEN-LAST:event_txt_colorKeyTyped

    private void txt_capacidadKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_capacidadKeyTyped
        int limite = 10;
        if (txt_capacidad.getText().length() == limite) {
            evt.consume();
        }
        char t = evt.getKeyChar();
        if (Character.isLetter(t)) {
            evt.consume();
            JOptionPane.showMessageDialog(null, "Ingrese solo numeros");
        }        
    }//GEN-LAST:event_txt_capacidadKeyTyped

    private void txt_ejesKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_ejesKeyTyped
        int limite = 2;
        if (txt_ejes.getText().length() == limite) {
            evt.consume();
        }
        char t = evt.getKeyChar();
        if (Character.isLetter(t)) {
            evt.consume();
            JOptionPane.showMessageDialog(null, "Ingrese solo numeros");
        }        
    }//GEN-LAST:event_txt_ejesKeyTyped

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
            java.util.logging.Logger.getLogger(mantenedorCamiones.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(mantenedorCamiones.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(mantenedorCamiones.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(mantenedorCamiones.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new mantenedorCamiones().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btn_aceptar;
    private javax.swing.JButton btn_agregar;
    private javax.swing.JButton btn_cancelar;
    private javax.swing.JButton btn_eliminar;
    private javax.swing.JButton btn_modificar;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lblColor;
    private javax.swing.JTable tbl_camiones;
    private javax.swing.JTextField txt_capacidad;
    private javax.swing.JTextField txt_color;
    private javax.swing.JTextField txt_ejes;
    private javax.swing.JTextField txt_patente;
    // End of variables declaration//GEN-END:variables
}
