/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hojasverdes;

import dominio.proveedor;
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
public class mantenedorProveedores extends javax.swing.JFrame {
    DefaultTableModel modelo = new DefaultTableModel();
    conectar cnx = new conectar();
    Connection reg = cnx.conexion();
    int columna = 0 ;
    int sw = 0 ;
    String sql;
    /**
     * Creates new form mantenedorProveedores
     */
    public mantenedorProveedores() {
        initComponents();
        this.setLocationRelativeTo(null);
        this.setTitle("Mantenedor Proveedores");
        tbl_proveedor.setAutoCreateRowSorter(true);
        modelo.addColumn("Rut Proveedor");
        modelo.addColumn("Nombre Proveedor");
        modelo.addColumn("Direccion");
        modelo.addColumn("Telefono");
        tbl_proveedor.setModel(modelo);
        btn_aceptar.setVisible(false);
        btn_cancelar.setVisible(false);
        mostrardatostabla("");
    }
    
    void mostrardatostabla (String valor){
        String []datos = new String[4];
        int cod;
        String sql="";
        if(valor.equals("")){
            sql = "SELECT * FROM proveedor";
        }else{
            sql = "SELECT * FROM proveedor WHERE rut_proveedor='"+valor+"'";
        }
        try {
            Statement st = reg.createStatement();
            ResultSet rs = st.executeQuery(sql);
            while (rs.next()){
                datos[0]=rs.getString(1);
                datos[1]=rs.getString(2);
                datos[2]=rs.getString(3);
                datos[3]=rs.getString(4);
                modelo.addRow(datos);
            }
            tbl_proveedor.setModel(modelo);
            
        } catch (SQLException ex) {
            Logger.getLogger(producto.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    public void limpiartabla(){
       // tabla.setModel(new DefaultTableModel());
        for (int i = 0; i < tbl_proveedor.getRowCount(); i++) {
           modelo.removeRow(i);
           i-=1;
       }
    }  
    
    static public boolean validar(String rut) {
        boolean validacion = false;
        try {
            rut = rut.toUpperCase();
            int rutAux = Integer.parseInt(rut.substring(0, rut.length() - 1));

            char dv = rut.charAt(rut.length() - 1);

            int m = 0, s = 1;
            for (; rutAux != 0; rutAux /= 10) {
                s = (s + rutAux % 10 * (9 - m++ % 6)) % 11;
            }
            if (dv == (char) (s != 0 ? s + 47 : 75)) {
                validacion = true;
            }

        } catch (java.lang.NumberFormatException e) {
        } catch (Exception e) {
        }
        return validacion;
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
        jTable1 = new javax.swing.JTable();
        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        txt_rutproveedor = new javax.swing.JTextField();
        txt_nombreproveedor = new javax.swing.JTextField();
        txt_direccionproveedor = new javax.swing.JTextField();
        txt_telefonoproveedor = new javax.swing.JTextField();
        btn_agregar = new javax.swing.JButton();
        btn_modificar = new javax.swing.JButton();
        btn_eliminar = new javax.swing.JButton();
        btn_aceptar = new javax.swing.JButton();
        btn_cancelar = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        tbl_proveedor = new javax.swing.JTable();

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
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
        jScrollPane1.setViewportView(jTable1);

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setResizable(false);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent evt) {
                formWindowClosing(evt);
            }
        });

        jPanel1.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel1.setText("Rut proveedor:");
        jPanel1.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(12, 16, -1, -1));

        jLabel2.setText("Nombre proveedor:");
        jPanel1.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(310, 14, -1, 20));

        jLabel3.setText("Direccion:");
        jPanel1.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(12, 66, -1, -1));

        jLabel4.setText("Telefono:");
        jPanel1.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(320, 66, -1, -1));

        txt_rutproveedor.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_rutproveedorActionPerformed(evt);
            }
        });
        txt_rutproveedor.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                txt_rutproveedorFocusLost(evt);
            }
        });
        txt_rutproveedor.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txt_rutproveedorKeyTyped(evt);
            }
        });
        jPanel1.add(txt_rutproveedor, new org.netbeans.lib.awtextra.AbsoluteConstraints(104, 13, 181, -1));

        txt_nombreproveedor.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txt_nombreproveedorKeyTyped(evt);
            }
        });
        jPanel1.add(txt_nombreproveedor, new org.netbeans.lib.awtextra.AbsoluteConstraints(414, 13, 233, -1));

        txt_direccionproveedor.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txt_direccionproveedorKeyTyped(evt);
            }
        });
        jPanel1.add(txt_direccionproveedor, new org.netbeans.lib.awtextra.AbsoluteConstraints(104, 63, 181, -1));

        txt_telefonoproveedor.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txt_telefonoproveedorKeyTyped(evt);
            }
        });
        jPanel1.add(txt_telefonoproveedor, new org.netbeans.lib.awtextra.AbsoluteConstraints(414, 63, 233, -1));

        btn_agregar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/iconos/add.png"))); // NOI18N
        btn_agregar.setText("Agregar");
        btn_agregar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_agregarActionPerformed(evt);
            }
        });
        jPanel1.add(btn_agregar, new org.netbeans.lib.awtextra.AbsoluteConstraints(560, 100, -1, 29));

        btn_modificar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/iconos/update.png"))); // NOI18N
        btn_modificar.setText("Modificar");
        btn_modificar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_modificarActionPerformed(evt);
            }
        });
        jPanel1.add(btn_modificar, new org.netbeans.lib.awtextra.AbsoluteConstraints(280, 100, -1, -1));

        btn_eliminar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/iconos/delete.png"))); // NOI18N
        btn_eliminar.setText("Eliminar");
        btn_eliminar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_eliminarActionPerformed(evt);
            }
        });
        jPanel1.add(btn_eliminar, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 100, -1, -1));

        btn_aceptar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/iconos/OK-20.png"))); // NOI18N
        btn_aceptar.setText("Aceptar");
        btn_aceptar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_aceptarActionPerformed(evt);
            }
        });
        jPanel1.add(btn_aceptar, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 100, -1, -1));

        btn_cancelar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/iconos/delete.png"))); // NOI18N
        btn_cancelar.setText("Cancelar");
        btn_cancelar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_cancelarActionPerformed(evt);
            }
        });
        jPanel1.add(btn_cancelar, new org.netbeans.lib.awtextra.AbsoluteConstraints(420, 100, -1, -1));

        tbl_proveedor.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Rut proveedor", "Nombre proveedor", "Direccion", "Telefono"
            }
        ));
        jScrollPane2.setViewportView(tbl_proveedor);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, 659, Short.MAX_VALUE)
                    .addComponent(jScrollPane2))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 253, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(12, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void txt_rutproveedorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_rutproveedorActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_rutproveedorActionPerformed

    private void btn_aceptarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_aceptarActionPerformed
        if (txt_rutproveedor.getText().equals("")){
            JOptionPane.showMessageDialog(null,"Debe ingresar rut proveedor");
        }else{
            if (txt_nombreproveedor.getText().equals("")){
                JOptionPane.showMessageDialog(null,"Debe ingresar nombre proveedor");
            }else{
                if (txt_direccionproveedor.getText().equals("")){
                    JOptionPane.showMessageDialog(null,"Debe ingresar direccion proveedor");
                }else{
                    if (txt_telefonoproveedor.getText().equals("")){
                        JOptionPane.showMessageDialog(null,"Debe ingresar telefono proveedor");
                    }else{
                        sql = "Update proveedor SET nom_proveedor='"+txt_nombreproveedor.getText()+"',direccion='"+txt_direccionproveedor.getText()+"',telefono='"+txt_telefonoproveedor.getText()+"' WHERE rut_proveedor='"+txt_rutproveedor.getText()+"'";
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
                        txt_rutproveedor.setEnabled(true);
                        txt_rutproveedor.setEditable(true);
                        txt_rutproveedor.requestFocus();
                        txt_rutproveedor.setText("");
                        txt_nombreproveedor.setText("");
                        txt_telefonoproveedor.setText("");
                        txt_direccionproveedor.setText("");
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
        txt_rutproveedor.setEnabled(true);
        txt_rutproveedor.setEditable(true);
        
        txt_rutproveedor.setText("");
        txt_rutproveedor.requestFocus();
        txt_nombreproveedor.setText("");
        txt_telefonoproveedor.setText("");
        txt_direccionproveedor.setText("");
        limpiartabla();
        mostrardatostabla("");
    }//GEN-LAST:event_btn_cancelarActionPerformed

    private void btn_agregarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_agregarActionPerformed
        if (txt_rutproveedor.getText().equals("")){
            JOptionPane.showMessageDialog(null,"Debe ingresar rut proveedor");
        }else{
            if (txt_nombreproveedor.getText().equals("")){
                JOptionPane.showMessageDialog(null,"Debe ingresar nombre proveedor");
            }else{
                if (txt_direccionproveedor.getText().equals("")){
                    JOptionPane.showMessageDialog(null,"Debe ingresar direccion proveedor");
                }else{
                    if (txt_telefonoproveedor.getText().equals("")){
                        JOptionPane.showMessageDialog(null,"Debe ingresar telefono proveedor");
                    }else{
                        proveedor dto = new proveedor();
                        dto.setRut_proveedor(txt_rutproveedor.getText());
                        dto.setNom_proveedor(txt_nombreproveedor.getText());
                        dto.setDireccion_proveedor(txt_direccionproveedor.getText());
                        dto.setTelefono_proveedor(Integer.parseInt(txt_telefonoproveedor.getText()));

                        sql = "INSERT INTO proveedor (rut_proveedor, nom_proveedor, direccion, telefono) VALUES (?,?,?,?)";
                        try {
                            PreparedStatement pst = reg.prepareStatement(sql);
                            pst.setString(1, dto.getRut_proveedor());
                            pst.setString(2, dto.getNom_proveedor());
                            pst.setString(3, dto.getDireccion_proveedor());
                            pst.setInt(4, dto.getTelefono_proveedor());

                            int n = pst.executeUpdate();
                            if (n>0){
                                JOptionPane.showMessageDialog(null,"Usuario registrado satisfactoriamente.");
                            }
                        } catch (SQLException ex) {
                            JOptionPane.showMessageDialog(null,"Error al agregar, Usuario duplicado.");
                        }
                        txt_rutproveedor.setText("");
                        txt_nombreproveedor.setText("");
                        txt_direccionproveedor.setText("");
                        txt_telefonoproveedor.setText("");

                        limpiartabla();
                        mostrardatostabla("");
                    }
                }
            }
        }
        
    }//GEN-LAST:event_btn_agregarActionPerformed

    private void btn_modificarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_modificarActionPerformed
        int fila = tbl_proveedor.getSelectedRow();
        if (fila>=0){
            txt_rutproveedor.setText(tbl_proveedor.getValueAt(fila, 0).toString());
            txt_nombreproveedor.setText(tbl_proveedor.getValueAt(fila, 1).toString());
            txt_direccionproveedor.setText(tbl_proveedor.getValueAt(fila, 2).toString());
            txt_telefonoproveedor.setText(tbl_proveedor.getValueAt(fila, 3).toString());
            txt_rutproveedor.setEditable(false);
            txt_rutproveedor.setEnabled(false);
            btn_agregar.setVisible(false);
            btn_modificar.setVisible(false);
            btn_eliminar.setVisible(false);
            btn_aceptar.setVisible(true);
            btn_cancelar.setVisible(true);
        }else{
            JOptionPane.showMessageDialog(null,"No selecciono fila");
        }
    }//GEN-LAST:event_btn_modificarActionPerformed

    private void btn_eliminarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_eliminarActionPerformed
        int fila = tbl_proveedor.getSelectedRow();
        if (fila >= 0){
            if (JOptionPane.showConfirmDialog(null, new Object[]{"Seguro que desea Eliminar fila seleccionada?"},"Eliminar",JOptionPane.OK_CANCEL_OPTION)==JOptionPane.YES_OPTION){
                fila = tbl_proveedor.getSelectedRow();
                txt_rutproveedor.setText(tbl_proveedor.getValueAt(fila, 0).toString());
                try {
                    PreparedStatement pst = reg.prepareStatement("DELETE FROM proveedor WHERE rut_proveedor="+Integer.parseInt(txt_rutproveedor.getText()+""));
                    pst.executeUpdate();
                    limpiartabla();
                    mostrardatostabla("");
                    txt_rutproveedor.setText("");
                } catch (Exception e) {
                    System.out.println(e.getMessage());
                    JOptionPane.showMessageDialog(null,e.getMessage());
                }
            }else{
                
            }
        }else{
            JOptionPane.showMessageDialog(null,"Debe seleccionar una fila antes de eliminar.");
        }
    }//GEN-LAST:event_btn_eliminarActionPerformed

    private void txt_rutproveedorKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_rutproveedorKeyTyped
        int limite = 9;
        if (txt_rutproveedor.getText().length() == limite) {
            evt.consume();
        }
    }//GEN-LAST:event_txt_rutproveedorKeyTyped

    private void txt_nombreproveedorKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_nombreproveedorKeyTyped
        int limite = 50;
        if (txt_nombreproveedor.getText().length() == limite) {
            evt.consume();
        }
        char t = evt.getKeyChar();
        if (Character.isDigit(t)) {
            evt.consume();
            JOptionPane.showMessageDialog(null, "Ingrese solo letras");
        }   
    }//GEN-LAST:event_txt_nombreproveedorKeyTyped

    private void txt_direccionproveedorKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_direccionproveedorKeyTyped
        int limite = 30;
        if (txt_direccionproveedor.getText().length() == limite) {
            evt.consume();
        }
    }//GEN-LAST:event_txt_direccionproveedorKeyTyped

    private void txt_telefonoproveedorKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_telefonoproveedorKeyTyped
        int limite = 8;
        if (txt_telefonoproveedor.getText().length() == limite) {
            evt.consume();
        }
        char t = evt.getKeyChar();
        if (Character.isLetter(t)) {
            evt.consume();
            JOptionPane.showMessageDialog(null, "Ingrese solo numeros");
        }
    }//GEN-LAST:event_txt_telefonoproveedorKeyTyped

    private void txt_rutproveedorFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txt_rutproveedorFocusLost
        if (txt_rutproveedor.getText().equals("")){
           
        }else{
        if (!validar(txt_rutproveedor.getText())) {
            JOptionPane.showMessageDialog(null, "Error RUT invalido", "Error", JOptionPane.ERROR_MESSAGE);
            txt_rutproveedor.setText("");
            txt_rutproveedor.requestFocus();
        }
        }
    }//GEN-LAST:event_txt_rutproveedorFocusLost

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
            java.util.logging.Logger.getLogger(mantenedorProveedores.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(mantenedorProveedores.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(mantenedorProveedores.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(mantenedorProveedores.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new mantenedorProveedores().setVisible(true);
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
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTable jTable1;
    private javax.swing.JTable tbl_proveedor;
    private javax.swing.JTextField txt_direccionproveedor;
    private javax.swing.JTextField txt_nombreproveedor;
    private javax.swing.JTextField txt_rutproveedor;
    private javax.swing.JTextField txt_telefonoproveedor;
    // End of variables declaration//GEN-END:variables
}
