/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hojasverdes;

import dominio.chofer;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

public class mantenedorChoferes extends javax.swing.JFrame {
    
    DefaultTableModel modelo = new DefaultTableModel();
    conectar cnx = new conectar();
    Connection reg= cnx.conexion();
    //private TableRowSorter trsfiltro;
    int columna=0;
    int sw = 0;
    String sql;

    public mantenedorChoferes() {
        initComponents();
        this.setLocationRelativeTo(null);
        this.setTitle("Mantenedor Choferes");
        tbl_chofer.setAutoCreateRowSorter(true);
        //ingresa las columnas de tu tabla productos
        modelo.addColumn("RUT");
        modelo.addColumn("Nombre");
        modelo.addColumn("Telefono");
        modelo.addColumn("Direccion");
        tbl_chofer.setModel(modelo);
        btn_aceptar.setVisible(false);
        btn_cancelar.setVisible(false);
        mostrardatostabla("");
    }

   void mostrardatostabla(String valor){    
        String []datos=new String[4];
        int cod;
        String sql="";
        if(valor.equals("")){
            sql="SELECT * FROM chofer";
        }else{
            sql="SELECT * FROM chofer WHERE rut_chofer='"+valor+"'";
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
            tbl_chofer.setModel(modelo);
            
        } catch (SQLException ex) {
            Logger.getLogger(producto.class.getName()).log(Level.SEVERE, null, ex);
        }   
    }
    
    public void limpiartabla(){
       // tabla.setModel(new DefaultTableModel());
        for (int i = 0; i < tbl_chofer.getRowCount(); i++) {
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
        
    
    

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        txt_rutChofer = new javax.swing.JTextField();
        txt_nomChofer = new javax.swing.JTextField();
        txt_telefono = new javax.swing.JTextField();
        txt_direccion = new javax.swing.JTextField();
        btn_agregar = new javax.swing.JButton();
        btn_modificar = new javax.swing.JButton();
        btn_eliminar = new javax.swing.JButton();
        btn_aceptar = new javax.swing.JButton();
        btn_cancelar = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        tbl_chofer = new javax.swing.JTable();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setResizable(false);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent evt) {
                formWindowClosing(evt);
            }
        });

        jPanel1.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel1.setText("RUT:");
        jPanel1.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(12, 16, 60, -1));

        jLabel2.setText("Telefono:");
        jPanel1.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(12, 62, 60, -1));

        jLabel3.setText("Nombre chofer:");
        jPanel1.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(388, 16, -1, -1));

        jLabel4.setText("Direccion:");
        jPanel1.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(388, 62, 75, -1));

        txt_rutChofer.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_rutChoferActionPerformed(evt);
            }
        });
        txt_rutChofer.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                txt_rutChoferFocusLost(evt);
            }
        });
        txt_rutChofer.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txt_rutChoferKeyTyped(evt);
            }
        });
        jPanel1.add(txt_rutChofer, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 13, 164, -1));

        txt_nomChofer.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txt_nomChoferKeyTyped(evt);
            }
        });
        jPanel1.add(txt_nomChofer, new org.netbeans.lib.awtextra.AbsoluteConstraints(481, 13, 209, -1));

        txt_telefono.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txt_telefonoKeyTyped(evt);
            }
        });
        jPanel1.add(txt_telefono, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 59, 164, -1));

        txt_direccion.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txt_direccionKeyTyped(evt);
            }
        });
        jPanel1.add(txt_direccion, new org.netbeans.lib.awtextra.AbsoluteConstraints(481, 59, 209, -1));

        btn_agregar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/iconos/add.png"))); // NOI18N
        btn_agregar.setText("Agregar");
        btn_agregar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_agregarActionPerformed(evt);
            }
        });
        jPanel1.add(btn_agregar, new org.netbeans.lib.awtextra.AbsoluteConstraints(630, 110, -1, 29));

        btn_modificar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/iconos/update.png"))); // NOI18N
        btn_modificar.setText("Modificar");
        btn_modificar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_modificarActionPerformed(evt);
            }
        });
        jPanel1.add(btn_modificar, new org.netbeans.lib.awtextra.AbsoluteConstraints(318, 106, -1, -1));

        btn_eliminar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/iconos/delete.png"))); // NOI18N
        btn_eliminar.setText("Eliminar");
        btn_eliminar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_eliminarActionPerformed(evt);
            }
        });
        jPanel1.add(btn_eliminar, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 110, -1, -1));

        btn_aceptar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/iconos/OK-20.png"))); // NOI18N
        btn_aceptar.setText("Aceptar");
        btn_aceptar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_aceptarActionPerformed(evt);
            }
        });
        jPanel1.add(btn_aceptar, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 110, -1, -1));

        btn_cancelar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/iconos/delete.png"))); // NOI18N
        btn_cancelar.setText("Cancelar");
        btn_cancelar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_cancelarActionPerformed(evt);
            }
        });
        jPanel1.add(btn_cancelar, new org.netbeans.lib.awtextra.AbsoluteConstraints(430, 110, -1, -1));

        tbl_chofer.setModel(new javax.swing.table.DefaultTableModel(
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
        jScrollPane1.setViewportView(tbl_chofer);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, 730, Short.MAX_VALUE)
                    .addComponent(jScrollPane1))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 147, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 252, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void txt_rutChoferActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_rutChoferActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_rutChoferActionPerformed

    private void btn_agregarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_agregarActionPerformed
        if (txt_rutChofer.getText().equals("")){
            JOptionPane.showMessageDialog(null,"Debe ingresar rut chofer");
        }else{
            if (txt_nomChofer.getText().equals("")){
                JOptionPane.showMessageDialog(null,"Debe ingresar nombre chofer");
            }else{
                if (txt_telefono.getText().equals("")){
                    JOptionPane.showMessageDialog(null,"Debe ingresar telefono chofer");
                }else{
                    if (txt_direccion.getText().equals("")){
                        JOptionPane.showMessageDialog(null,"Debe ingresar direccion chofer");
                    }else{
                        chofer dto = new chofer();
                        dto.setRut_chofer(txt_rutChofer.getText());
                        dto.setNom_chofer(txt_nomChofer.getText());
                        dto.setTelefono(Integer.parseInt(txt_telefono.getText()));
                        dto.setDireccion(txt_direccion.getText());
                        sql= "INSERT INTO chofer (rut_chofer, nom_chofer, telefono, direccion)VALUES (?,?,?,?)";
                        try {
                            PreparedStatement pst=reg.prepareStatement(sql);
                            pst.setString(1, dto.getRut_chofer());
                            pst.setString(2, dto.getNom_chofer());
                            pst.setInt(3, dto.getTelefono());
                            pst.setString(4, dto.getDireccion());
                            int n = pst.executeUpdate();
                            if (n>0){
                                JOptionPane.showMessageDialog(null,"Chofer registrado satisfactoriamente.");
                            }                
                        }catch (SQLException ex) {
                            JOptionPane.showMessageDialog(null,"Error al agregar, rut duplicada.");
                            //sw = 1;
                        }
                        txt_direccion.setText("");
                        txt_nomChofer.setText("");
                        txt_rutChofer.setText("");
                        txt_telefono.setText("");
                        limpiartabla();
                        mostrardatostabla(""); 
                    }
                }
            }
        }
        
    }//GEN-LAST:event_btn_agregarActionPerformed

    private void btn_modificarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_modificarActionPerformed
        int fila=tbl_chofer.getSelectedRow();
        if (fila>=0){
            txt_rutChofer.setText(tbl_chofer.getValueAt(fila, 0).toString());
            txt_nomChofer.setText(tbl_chofer.getValueAt(fila, 1).toString());
            txt_telefono.setText(tbl_chofer.getValueAt(fila, 2).toString());
            txt_direccion.setText(tbl_chofer.getValueAt(fila, 3).toString());
            txt_rutChofer.setEditable(false);
            txt_rutChofer.setEnabled(false);
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
         if (txt_rutChofer.getText().equals("")){
            JOptionPane.showMessageDialog(null,"Debe ingresar rut chofer");
        }else{
            if (txt_nomChofer.getText().equals("")){
                JOptionPane.showMessageDialog(null,"Debe ingresar nombre chofer");
            }else{
                if (txt_telefono.getText().equals("")){
                    JOptionPane.showMessageDialog(null,"Debe ingresar telefono chofer");
                }else{
                    if (txt_direccion.getText().equals("")){
                        JOptionPane.showMessageDialog(null,"Debe ingresar direccion chofer");
                    }else{
                        sql="UPDATE chofer SET nom_chofer='"+txt_nomChofer.getText()+"',telefono='"+Integer.parseInt(txt_telefono.getText())+"', direccion='"+txt_direccion.getText()+"'  WHERE rut_chofer='"+Integer.parseInt(txt_rutChofer.getText())+"'";
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
                        txt_rutChofer.setEnabled(true);
                        txt_rutChofer.setEditable(true);
                        txt_rutChofer.requestFocus();
                        txt_rutChofer.setText("");
                        txt_nomChofer.setText("");
                        txt_telefono.setText("");
                        txt_direccion.setText("");
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
        txt_rutChofer.setEnabled(true);
        txt_rutChofer.setEditable(true);
        txt_rutChofer.requestFocus();
        txt_rutChofer.setText("");
        txt_nomChofer.setText("");
        txt_telefono.setText("");
        txt_direccion.setText("");
    }//GEN-LAST:event_btn_cancelarActionPerformed

    private void btn_eliminarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_eliminarActionPerformed
        int fila = tbl_chofer.getSelectedRow();
        if (fila >= 0){   
            if(JOptionPane.showConfirmDialog(null, new Object[]{"Seguro que desea Eliminar fila seleccionada?"},"Eliminar",JOptionPane.OK_CANCEL_OPTION)==JOptionPane.YES_OPTION){
            //qui se pone lo que hara si le das aceptar
                fila=tbl_chofer.getSelectedRow();
                txt_rutChofer.setText(tbl_chofer.getValueAt(fila, 0).toString());
                try {
                    PreparedStatement pst = reg.prepareStatement("DELETE FROM chofer WHERE rut_chofer="+Integer.parseInt(txt_rutChofer.getText())+"");
                    pst.executeUpdate();
                    limpiartabla();
                    mostrardatostabla("");
                    txt_rutChofer.setText("");
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

    private void txt_rutChoferKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_rutChoferKeyTyped
        int limite = 9;
        if (txt_rutChofer.getText().length() == limite) {
            evt.consume();
        }
    }//GEN-LAST:event_txt_rutChoferKeyTyped

    private void txt_nomChoferKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_nomChoferKeyTyped
        int limite = 50;
        if (txt_nomChofer.getText().length() == limite) {
            evt.consume();
        }
        char t = evt.getKeyChar();
        if (Character.isDigit(t)) {
            evt.consume();
            JOptionPane.showMessageDialog(null, "Ingrese solo letras");
        }           
    }//GEN-LAST:event_txt_nomChoferKeyTyped

    private void txt_telefonoKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_telefonoKeyTyped
        int limite = 8;
        if (txt_telefono.getText().length() == limite) {
            evt.consume();
        }
        char t = evt.getKeyChar();
        if (Character.isLetter(t)) {
            evt.consume();
            JOptionPane.showMessageDialog(null, "Ingrese solo numeros");
        }

    }//GEN-LAST:event_txt_telefonoKeyTyped

    private void txt_direccionKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_direccionKeyTyped
        int limite = 30;
        if (txt_direccion.getText().length() == limite) {
            evt.consume();
        }
    }//GEN-LAST:event_txt_direccionKeyTyped

    private void txt_rutChoferFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txt_rutChoferFocusLost
        if (txt_rutChofer.getText().equals("")){
            
        }else{
            
            
        if (!validar(txt_rutChofer.getText())) {
                JOptionPane.showMessageDialog(null, "Error RUT invalido", "Error", JOptionPane.ERROR_MESSAGE);
                txt_rutChofer.setText("");
                txt_rutChofer.requestFocus();
            }
        }
    }//GEN-LAST:event_txt_rutChoferFocusLost

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
            java.util.logging.Logger.getLogger(mantenedorChoferes.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(mantenedorChoferes.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(mantenedorChoferes.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(mantenedorChoferes.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new mantenedorChoferes().setVisible(true);
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
    private javax.swing.JTable tbl_chofer;
    private javax.swing.JTextField txt_direccion;
    private javax.swing.JTextField txt_nomChofer;
    private javax.swing.JTextField txt_rutChofer;
    private javax.swing.JTextField txt_telefono;
    // End of variables declaration//GEN-END:variables
}
