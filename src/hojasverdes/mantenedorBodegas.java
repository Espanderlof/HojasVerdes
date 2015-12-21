/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hojasverdes;

import dominio.bodega;
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
import persistencia.bodegaDAO;

/**
 *
 * @author Fralkayg
 */
public class mantenedorBodegas extends javax.swing.JFrame {
    
    DefaultTableModel modelo = new DefaultTableModel();
    conectar cnx = new conectar();
    Connection reg= cnx.conexion();
    //private TableRowSorter trsfiltro;
    int columna=0;
    int sw = 0;
    String sql;
    //private bodegaDAO dao;
   
    public mantenedorBodegas() {
        initComponents();
        this.setLocationRelativeTo(null);
        this.setTitle("Mantenedor Bodegas");
        tbl_bodega.setAutoCreateRowSorter(true);
        //ingresa las columnas de tu tabla productos
        modelo.addColumn("Codigo");
        modelo.addColumn("Nombre");
        modelo.addColumn("Direccion");
        tbl_bodega.setModel(modelo);
        btn_aceptar.setVisible(false);
        btn_cancelar.setVisible(false);
        mostrardatostabla("");
        //this.dao = dao;
    }
    
    void mostrardatostabla(String valor){    
        String []datos=new String[3];
        int cod;
        String sql="";
        if(valor.equals("")){
            sql="SELECT * FROM bodega";
        }else{
            sql="SELECT * FROM bodega WHERE cod_bodega='"+valor+"'";
        }
        try {
            Statement st = reg.createStatement();
            ResultSet rs = st.executeQuery(sql);
            while(rs.next()){
                //aqui se agregan los campos
                cod=rs.getInt(1);
                datos[0]=Integer.toString(cod);
                datos[1]=rs.getString(2);
                datos[2]=rs.getString(3);
                modelo.addRow(datos);
            }
            tbl_bodega.setModel(modelo);
            
        } catch (SQLException ex) {
            Logger.getLogger(producto.class.getName()).log(Level.SEVERE, null, ex);
        }   
    }
    
    public void limpiartabla(){
       // tabla.setModel(new DefaultTableModel());
        for (int i = 0; i < tbl_bodega.getRowCount(); i++) {
           modelo.removeRow(i);
           i-=1;
       }
    }
    
    public int getCodBodega(){
        int codigo=0;
        try{
            String sql="select distinct(cod_bodega) from lote_bodega where cod_bodega ="+Integer.parseInt(txt_codBodega.getText())+"";
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
        jLabel2 = new javax.swing.JLabel();
        txt_codBodega = new javax.swing.JTextField();
        txt_direccion = new javax.swing.JTextField();
        btn_agregar = new javax.swing.JButton();
        btn_modificar = new javax.swing.JButton();
        btn_eliminar = new javax.swing.JButton();
        jLabel3 = new javax.swing.JLabel();
        txt_nomBodega = new javax.swing.JTextField();
        btn_aceptar = new javax.swing.JButton();
        btn_cancelar = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        tbl_bodega = new javax.swing.JTable();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent evt) {
                formWindowClosing(evt);
            }
        });

        jPanel1.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel1.setText("Codigo bodega:");
        jPanel1.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(12, 16, -1, -1));

        jLabel2.setText("Direccion:");
        jPanel1.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(307, 47, -1, -1));

        txt_codBodega.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_codBodegaActionPerformed(evt);
            }
        });
        txt_codBodega.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txt_codBodegaKeyTyped(evt);
            }
        });
        jPanel1.add(txt_codBodega, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 13, 171, -1));

        txt_direccion.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txt_direccionKeyTyped(evt);
            }
        });
        jPanel1.add(txt_direccion, new org.netbeans.lib.awtextra.AbsoluteConstraints(372, 44, 203, -1));

        btn_agregar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/iconos/add.png"))); // NOI18N
        btn_agregar.setText("Agregar");
        btn_agregar.setToolTipText("");
        btn_agregar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_agregarActionPerformed(evt);
            }
        });
        jPanel1.add(btn_agregar, new org.netbeans.lib.awtextra.AbsoluteConstraints(497, 87, -1, 29));

        btn_modificar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/iconos/update.png"))); // NOI18N
        btn_modificar.setText("Modificar");
        btn_modificar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_modificarActionPerformed(evt);
            }
        });
        jPanel1.add(btn_modificar, new org.netbeans.lib.awtextra.AbsoluteConstraints(242, 87, -1, -1));

        btn_eliminar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/iconos/delete.png"))); // NOI18N
        btn_eliminar.setText("Eliminar");
        btn_eliminar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_eliminarActionPerformed(evt);
            }
        });
        jPanel1.add(btn_eliminar, new org.netbeans.lib.awtextra.AbsoluteConstraints(12, 87, -1, -1));

        jLabel3.setText("Nombre bodega:");
        jPanel1.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(12, 47, -1, -1));

        txt_nomBodega.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_nomBodegaActionPerformed(evt);
            }
        });
        txt_nomBodega.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txt_nomBodegaKeyTyped(evt);
            }
        });
        jPanel1.add(txt_nomBodega, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 44, 171, -1));

        btn_aceptar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/iconos/OK-20.png"))); // NOI18N
        btn_aceptar.setText("Aceptar");
        btn_aceptar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_aceptarActionPerformed(evt);
            }
        });
        jPanel1.add(btn_aceptar, new org.netbeans.lib.awtextra.AbsoluteConstraints(123, 87, -1, -1));

        btn_cancelar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/iconos/delete.png"))); // NOI18N
        btn_cancelar.setText("Cancelar");
        btn_cancelar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_cancelarActionPerformed(evt);
            }
        });
        jPanel1.add(btn_cancelar, new org.netbeans.lib.awtextra.AbsoluteConstraints(372, 87, -1, -1));

        tbl_bodega.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null},
                {null, null},
                {null, null},
                {null, null}
            },
            new String [] {
                "Codigo bodega", "Direccion"
            }
        ));
        jScrollPane1.setViewportView(tbl_bodega);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, 598, Short.MAX_VALUE)
                    .addComponent(jScrollPane1))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, 127, Short.MAX_VALUE)
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 261, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void txt_codBodegaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_codBodegaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_codBodegaActionPerformed

    private void txt_nomBodegaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_nomBodegaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_nomBodegaActionPerformed

    private void btn_agregarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_agregarActionPerformed
        if (txt_codBodega.getText().equals("")){
            JOptionPane.showMessageDialog(null,"Debe ingresar codigo bodega");
        }else{
            if (txt_nomBodega.getText().equals("")){
                JOptionPane.showMessageDialog(null,"Debe ingresar nombre de bodega.");
            }else{
                if (txt_direccion.getText().equals("")){
                    JOptionPane.showMessageDialog(null,"Debe ingresar direccion de bodega");
                }else{
                    bodega dto = new bodega();
                    dto.setCod_bodega(Integer.parseInt(txt_codBodega.getText()));
                    dto.setNom_bodega(txt_nomBodega.getText());
                    dto.setDireccion(txt_direccion.getText());
                    sql= "INSERT INTO bodega (cod_bodega, nom_bodega, direccion)VALUES (?,?,?)";
                    try {
                        PreparedStatement pst=reg.prepareStatement(sql);
                        pst.setInt(1, dto.getCod_bodega());
                        pst.setString(2, dto.getNom_bodega());
                        pst.setString(3, dto.getDireccion());
                        int n = pst.executeUpdate();
                        if (n>0){
                            JOptionPane.showMessageDialog(null,"Bodega registrada satisfactoriamente.");
                        }                
                    }catch (SQLException ex) {
                        JOptionPane.showMessageDialog(null,"Error al agregar, codigo de bodega duplicada.");
                        //sw = 1;
                    }
                    txt_codBodega.setText("");
                    txt_nomBodega.setText("");
                    txt_direccion.setText("");
                    limpiartabla();
                    mostrardatostabla("");  
                }
            }
        }
        
    }//GEN-LAST:event_btn_agregarActionPerformed

    private void btn_modificarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_modificarActionPerformed
        int fila=tbl_bodega.getSelectedRow();
        if (fila>=0){
            txt_codBodega.setText(tbl_bodega.getValueAt(fila, 0).toString());
            txt_nomBodega.setText(tbl_bodega.getValueAt(fila, 1).toString());
            txt_direccion.setText(tbl_bodega.getValueAt(fila, 2).toString());
            txt_codBodega.setEditable(false);
            txt_codBodega.setEnabled(false);
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
        if (txt_codBodega.getText().equals("")){
            JOptionPane.showMessageDialog(null,"Debe ingresar codigo bodega");
        }else{
            if (txt_nomBodega.getText().equals("")){
                JOptionPane.showMessageDialog(null,"Debe ingresar nombre de bodega.");
            }else{
                if (txt_direccion.getText().equals("")){
                    JOptionPane.showMessageDialog(null,"Debe ingresar direccion de bodega");
                }else{
                    sql="UPDATE bodega SET nom_bodega='"+txt_nomBodega.getText()+"',direccion='"+txt_direccion.getText()+"' WHERE cod_bodega='"+txt_codBodega.getText()+"'";
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
                    txt_codBodega.setEnabled(true);
                    txt_codBodega.setEditable(true);
                    txt_codBodega.requestFocus();
                    txt_nomBodega.setText("");
                    txt_direccion.setText("");
                    txt_codBodega.setText("");
                    limpiartabla();
                    mostrardatostabla("");
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
        txt_codBodega.setEnabled(true);
        txt_codBodega.setEditable(true);
        txt_codBodega.requestFocus();
        txt_nomBodega.setText("");
        txt_codBodega.setText("");
        txt_direccion.setText("");
    }//GEN-LAST:event_btn_cancelarActionPerformed

    private void btn_eliminarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_eliminarActionPerformed
        /*if (Integer.parseInt(txt_codBodega.getText()) == getCodBodega()){
            JOptionPane.showMessageDialog(null,"No puede eliminar esta bodega.");
        }else{*/
            int fila = tbl_bodega.getSelectedRow();
            if (fila >= 0){   
                if(JOptionPane.showConfirmDialog(null, new Object[]{"Seguro que desea Eliminar fila seleccionada?"},"Eliminar",JOptionPane.OK_CANCEL_OPTION)==JOptionPane.YES_OPTION){
                //qui se pone lo que hara si le das aceptar
                    fila=tbl_bodega.getSelectedRow();
                    txt_codBodega.setText(tbl_bodega.getValueAt(fila, 0).toString());
                    try {
                        PreparedStatement pst = reg.prepareStatement("DELETE FROM bodega WHERE cod_bodega='"+txt_codBodega.getText()+"'");
                        pst.executeUpdate();
                        limpiartabla();
                        mostrardatostabla("");
                        txt_codBodega.setText("");
                    } catch (Exception e) {
                        System.out.println(e.getMessage());
                    }
                }else{
                //aqui se pone lo que hara si le das cancelar
                }
            }else{
                JOptionPane.showMessageDialog(null,"Debe seleccionar una fila antes de eliminar.");
            }
        //}
        
    }//GEN-LAST:event_btn_eliminarActionPerformed

    private void txt_codBodegaKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_codBodegaKeyTyped
        int limite = 2;
        if (txt_codBodega.getText().length() == limite) {
            evt.consume();
        }
        char t = evt.getKeyChar();
        if (Character.isLetter(t)) {
            evt.consume();
            JOptionPane.showMessageDialog(null, "Ingrese solo Numeros");
        }
    }//GEN-LAST:event_txt_codBodegaKeyTyped

    private void txt_nomBodegaKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_nomBodegaKeyTyped
        int limite = 50;
        if (txt_nomBodega.getText().length() == limite) {
            evt.consume();
        }
        char t = evt.getKeyChar();
        if (Character.isDigit(t)) {
            evt.consume();
            JOptionPane.showMessageDialog(null, "Ingrese solo letras");
        }
    }//GEN-LAST:event_txt_nomBodegaKeyTyped

    private void txt_direccionKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_direccionKeyTyped
        int limite = 50;
        if (txt_nomBodega.getText().length() == limite) {
            evt.consume();
        }
    }//GEN-LAST:event_txt_direccionKeyTyped

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
            java.util.logging.Logger.getLogger(mantenedorBodegas.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(mantenedorBodegas.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(mantenedorBodegas.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(mantenedorBodegas.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new mantenedorBodegas().setVisible(true);
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
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable tbl_bodega;
    private javax.swing.JTextField txt_codBodega;
    private javax.swing.JTextField txt_direccion;
    private javax.swing.JTextField txt_nomBodega;
    // End of variables declaration//GEN-END:variables
}
