/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hojasverdes;

import dominio.campo;
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
public class mantenedorCampo extends javax.swing.JFrame {
    
    DefaultTableModel modelo = new DefaultTableModel();
    conectar cnx = new conectar();
    Connection reg= cnx.conexion();
    //private TableRowSorter trsfiltro;
    int columna=0;
    int sw = 0;
    String sql;


    public mantenedorCampo() {
        initComponents();
        this.setLocationRelativeTo(null);
        this.setTitle("Mantenedor Campo");
        tbl_campo.setAutoCreateRowSorter(true);
        //ingresa las columnas de tu tabla productos
        modelo.addColumn("Codigo campo");
        modelo.addColumn("RUT proveedor");
        modelo.addColumn("Nombre");
        modelo.addColumn("Direccion");
        tbl_campo.setModel(modelo);
        btn_aceptar.setVisible(false);
        btn_cancelar.setVisible(false);
        mostrardatostabla("");
        cmbProveedor("");
    }
    
    void mostrardatostabla(String valor){    
        String []datos=new String[4];
        int cod;
        String sql="";
        if(valor.equals("")){
            sql="SELECT c.cod_campo, p.nom_proveedor, c.nom_campo, c.direccion FROM campo c, proveedor p where c.rut_proveedor = p.rut_proveedor";
        }else{
            sql="SELECT * FROM campo WHERE cod_campo='"+valor+"'";
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
            tbl_campo.setModel(modelo);
            
        } catch (SQLException ex) {
            Logger.getLogger(producto.class.getName()).log(Level.SEVERE, null, ex);
        }   
    }
    
    public void limpiartabla(){
       // tabla.setModel(new DefaultTableModel());
        for (int i = 0; i < tbl_campo.getRowCount(); i++) {
           modelo.removeRow(i);
           i-=1;
       }
    }

    
    void cmbProveedor(String nombre){
        cmb_proveedor.removeAllItems();
        try{
            String sql="select nom_proveedor from proveedor";
            Statement st = reg.createStatement();
            ResultSet rs = st.executeQuery(sql);
            while (rs.next()){
                cmb_proveedor.addItem(rs.getString(1));
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
        txt_codCampo = new javax.swing.JTextField();
        txt_nomCampo = new javax.swing.JTextField();
        txt_direccion = new javax.swing.JTextField();
        btn_agregar = new javax.swing.JButton();
        btn_modificar = new javax.swing.JButton();
        btn_eliminar = new javax.swing.JButton();
        btn_aceptar = new javax.swing.JButton();
        btn_cancelar = new javax.swing.JButton();
        jButton1 = new javax.swing.JButton();
        cmb_proveedor = new javax.swing.JComboBox();
        jScrollPane1 = new javax.swing.JScrollPane();
        tbl_campo = new javax.swing.JTable();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent evt) {
                formWindowClosing(evt);
            }
        });

        jPanel1.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel1.setText("Codigo campo:");
        jPanel1.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(12, 16, 99, -1));

        jLabel2.setText("RUT proveedor:");
        jLabel2.setToolTipText("");
        jPanel1.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(349, 16, -1, -1));

        jLabel3.setText("Nombre campo:");
        jPanel1.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(12, 62, -1, -1));

        jLabel4.setText("Direccion:");
        jPanel1.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(349, 62, 77, -1));

        txt_codCampo.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txt_codCampoKeyTyped(evt);
            }
        });
        jPanel1.add(txt_codCampo, new org.netbeans.lib.awtextra.AbsoluteConstraints(129, 13, 188, -1));

        txt_nomCampo.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txt_nomCampoKeyTyped(evt);
            }
        });
        jPanel1.add(txt_nomCampo, new org.netbeans.lib.awtextra.AbsoluteConstraints(129, 59, 188, -1));

        txt_direccion.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txt_direccionKeyTyped(evt);
            }
        });
        jPanel1.add(txt_direccion, new org.netbeans.lib.awtextra.AbsoluteConstraints(480, 59, 240, -1));

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
        jPanel1.add(btn_modificar, new org.netbeans.lib.awtextra.AbsoluteConstraints(320, 110, -1, -1));

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

        jButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/iconos/add.png"))); // NOI18N
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        jPanel1.add(jButton1, new org.netbeans.lib.awtextra.AbsoluteConstraints(671, 13, 19, 20));

        jPanel1.add(cmb_proveedor, new org.netbeans.lib.awtextra.AbsoluteConstraints(480, 13, 173, -1));

        tbl_campo.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Codigo campo", "RUT", "Nombre campo", "Direccion"
            }
        ));
        jScrollPane1.setViewportView(tbl_campo);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 733, Short.MAX_VALUE)
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 151, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 288, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btn_agregarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_agregarActionPerformed
        if (txt_codCampo.getText().equals("")){
            JOptionPane.showMessageDialog(null,"Debe ingresar codigo campo");
        }else{
            if (txt_nomCampo.getText().equals("")){
                JOptionPane.showMessageDialog(null,"Debe ingresar nombre campo");
            }else{
                if (txt_direccion.getText().equals("")){
                    JOptionPane.showMessageDialog(null,"Debe ingresar direccion");
                }else{
                    campo dto = new campo();
                    dto.setCod_campo(Integer.parseInt(txt_codCampo.getText()));
                    dto.setRut_proveedor(getRutProveedor());
                    dto.setNom_campo(txt_nomCampo.getText());
                    dto.setDireccion(txt_direccion.getText());
                    sql= "INSERT INTO campo (cod_campo, rut_proveedor, nom_campo, direccion)VALUES (?,?,?,?)";
                    try {
                        PreparedStatement pst=reg.prepareStatement(sql);
                        pst.setInt(1, dto.getCod_campo());
                        pst.setString(2, dto.getRut_proveedor());
                        pst.setString(3, dto.getNom_campo());
                        pst.setString(4, dto.getDireccion());
                        int n = pst.executeUpdate();
                        if (n>0){
                            JOptionPane.showMessageDialog(null,"Campo registrado satisfactoriamente.");
                        }                
                    }catch (SQLException ex) {
                        JOptionPane.showMessageDialog(null,"Error al agregar, campo duplicada.");
                        //sw = 1;
                    }       
                    limpiartabla();
                    mostrardatostabla("");
                }
            }
        }

    }//GEN-LAST:event_btn_agregarActionPerformed

    private void btn_modificarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_modificarActionPerformed
        int fila=tbl_campo.getSelectedRow();
        if (fila>=0){
            txt_codCampo.setText(tbl_campo.getValueAt(fila, 0).toString());
            cmb_proveedor.setSelectedItem(tbl_campo.getValueAt(fila, 1).toString());
            txt_nomCampo.setText(tbl_campo.getValueAt(fila, 2).toString());
            txt_direccion.setText(tbl_campo.getValueAt(fila, 3).toString());
            txt_codCampo.setEditable(false);
            txt_codCampo.setEnabled(false);
            cmb_proveedor.setEditable(false);
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
        if (txt_codCampo.getText().equals("")){
            JOptionPane.showMessageDialog(null,"Debe ingresar codigo campo");
        }else{
            if (txt_nomCampo.getText().equals("")){
                JOptionPane.showMessageDialog(null,"Debe ingresar nombre campo");
            }else{
                if (txt_direccion.getText().equals("")){
                    JOptionPane.showMessageDialog(null,"Debe ingresar direccion");
                }else{
                    sql="UPDATE campo SET nom_campo='"+txt_nomCampo.getText()+"', direccion ='"+txt_direccion.getText()+"', rut_proveedor = "+getRutProveedor()+"  WHERE cod_campo="+Integer.parseInt(txt_codCampo.getText())+" ";
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
                    txt_codCampo.setEnabled(true);
                    txt_codCampo.setEditable(true);
                    txt_codCampo.requestFocus();
                    cmb_proveedor.setEnabled(true);
                    txt_codCampo.setText("");
                    txt_nomCampo.setText("");
                    txt_direccion.setText("");
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
        txt_codCampo.setEnabled(true);
        cmb_proveedor.setEnabled(true);
        txt_codCampo.setEditable(true);
        txt_codCampo.requestFocus();
        txt_codCampo.setText("");
        txt_nomCampo.setText("");
        txt_direccion.setText("");
    }//GEN-LAST:event_btn_cancelarActionPerformed

    private void btn_eliminarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_eliminarActionPerformed
        int fila = tbl_campo.getSelectedRow();
        if (fila >= 0){   
            if(JOptionPane.showConfirmDialog(null, new Object[]{"Seguro que desea Eliminar fila seleccionada?"},"Eliminar",JOptionPane.OK_CANCEL_OPTION)==JOptionPane.YES_OPTION){
            //qui se pone lo que hara si le das aceptar
                fila=tbl_campo.getSelectedRow();
                txt_codCampo.setText(tbl_campo.getValueAt(fila, 0).toString());
                //cmb_proveedor.setText(tbl_campo.getValueAt(fila, 1).toString());
                try {
                    PreparedStatement pst = reg.prepareStatement("DELETE FROM campo WHERE cod_campo="+Integer.parseInt(txt_codCampo.getText())+" ");
                    pst.executeUpdate();
                    limpiartabla();
                    mostrardatostabla("");
                    txt_codCampo.setText("");
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
        mantenedorProveedores abrirProveedores = new mantenedorProveedores();
        abrirProveedores.setVisible(true);
    }//GEN-LAST:event_jButton1ActionPerformed

    private void txt_codCampoKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_codCampoKeyTyped
        int limite = 3;
        if (txt_codCampo.getText().length() == limite) {
            evt.consume();
        }
        char t = evt.getKeyChar();
        if (Character.isLetter(t)) {
            evt.consume();
            JOptionPane.showMessageDialog(null, "Ingrese solo numeros");
        }        
    }//GEN-LAST:event_txt_codCampoKeyTyped

    private void txt_nomCampoKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_nomCampoKeyTyped
        int limite = 50;
        if (txt_nomCampo.getText().length() == limite) {
            evt.consume();
        }
        char t = evt.getKeyChar();
        if (Character.isDigit(t)) {
            evt.consume();
            JOptionPane.showMessageDialog(null, "Ingrese solo letras");
        }          
    }//GEN-LAST:event_txt_nomCampoKeyTyped

    private void txt_direccionKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_direccionKeyTyped
        int limite = 30;
        if (txt_direccion.getText().length() == limite) {
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
            java.util.logging.Logger.getLogger(mantenedorCampo.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(mantenedorCampo.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(mantenedorCampo.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(mantenedorCampo.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new mantenedorCampo().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btn_aceptar;
    private javax.swing.JButton btn_agregar;
    private javax.swing.JButton btn_cancelar;
    private javax.swing.JButton btn_eliminar;
    private javax.swing.JButton btn_modificar;
    private javax.swing.JComboBox cmb_proveedor;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable tbl_campo;
    private javax.swing.JTextField txt_codCampo;
    private javax.swing.JTextField txt_direccion;
    private javax.swing.JTextField txt_nomCampo;
    // End of variables declaration//GEN-END:variables
}
