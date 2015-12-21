/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hojasverdes;

import com.mysql.jdbc.exceptions.MySQLIntegrityConstraintViolationException;
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

/**
 *
 * @author Fralkayg
 */
public class mantenedorProductos extends javax.swing.JFrame {
    
    DefaultTableModel modelo = new DefaultTableModel();
    conectar con= new conectar();
    Connection reg= con.conexion();
    private TableRowSorter trsfiltro;
    int columna=0;
    int sw = 0;
    String cod,nom, variedad, precio, stockCritico,stockActual;
    String sql;
    
    
    public mantenedorProductos() {
        initComponents();
        this.setTitle("Mantenedor Productos");
        this.setLocationRelativeTo(null);
        tbl_producto.setAutoCreateRowSorter(true);
        //ingresa las columnas de tu tabla productos
        modelo.addColumn("Codigo");
        modelo.addColumn("Nombre");
        modelo.addColumn("Variedad");
        modelo.addColumn("Precio");
        modelo.addColumn("Stock Critico");
        modelo.addColumn("Stock Actual");
        
        tbl_producto.setModel(modelo);
        btn_aceptar.setVisible(false);
        btn_cancelar.setVisible(false);
        mostrardatostabla("");
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    
    void mostrardatostabla(String valor){    
        String []datos=new String[6];
        int cod;
        int colorcito=0;
        String sql="";
        if(valor.equals("")){
            sql="SELECT cod_producto, nom_producto, variedad, precio, stock_critico,stock_actual FROM producto";
        }else{
            sql="SELECT * FROM producto WHERE cod_producto='"+valor+"'";
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
                datos[3]=rs.getString(4);
                datos[4]=rs.getString(5);
                datos[5]=rs.getString(6);
                modelo.addRow(datos);
            }
            tbl_producto.setModel(modelo);
            
        } catch (SQLException ex) {
            Logger.getLogger(producto.class.getName()).log(Level.SEVERE, null, ex);
        }   
    }
    
    public void limpiartabla(){
       // tabla.setModel(new DefaultTableModel());
        for (int i = 0; i < tbl_producto.getRowCount(); i++) {
           modelo.removeRow(i);
           i-=1;
       }
    }
    
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        txt_codProducto = new javax.swing.JTextField();
        txt_nomProducto = new javax.swing.JTextField();
        txt_variedad = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        txt_precio = new javax.swing.JTextField();
        btn_agregar = new javax.swing.JButton();
        btn_modificar = new javax.swing.JButton();
        btn_eliminar = new javax.swing.JButton();
        jLabel5 = new javax.swing.JLabel();
        txt_stockCritico = new javax.swing.JTextField();
        btn_aceptar = new javax.swing.JButton();
        btn_cancelar = new javax.swing.JButton();
        txt_stockactual = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tbl_producto = new javax.swing.JTable();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent evt) {
                formWindowClosing(evt);
            }
        });

        jPanel1.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel1.setText("Codigo producto:");
        jPanel1.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(12, 16, 101, -1));

        jLabel2.setText("Nombre producto:");
        jPanel1.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(316, 16, -1, -1));

        jLabel3.setText("Variedad:");
        jPanel1.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(12, 54, 101, -1));

        txt_codProducto.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txt_codProductoKeyTyped(evt);
            }
        });
        jPanel1.add(txt_codProducto, new org.netbeans.lib.awtextra.AbsoluteConstraints(117, 13, 153, -1));

        txt_nomProducto.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txt_nomProductoKeyTyped(evt);
            }
        });
        jPanel1.add(txt_nomProducto, new org.netbeans.lib.awtextra.AbsoluteConstraints(433, 13, 167, -1));

        txt_variedad.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_variedadActionPerformed(evt);
            }
        });
        txt_variedad.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txt_variedadKeyTyped(evt);
            }
        });
        jPanel1.add(txt_variedad, new org.netbeans.lib.awtextra.AbsoluteConstraints(117, 51, 153, -1));

        jLabel4.setText("Precio (Kg):");
        jPanel1.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(350, 50, -1, 20));

        txt_precio.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_precioActionPerformed(evt);
            }
        });
        txt_precio.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txt_precioKeyTyped(evt);
            }
        });
        jPanel1.add(txt_precio, new org.netbeans.lib.awtextra.AbsoluteConstraints(433, 51, 167, -1));

        btn_agregar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/iconos/add.png"))); // NOI18N
        btn_agregar.setText("Agregar");
        btn_agregar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_agregarActionPerformed(evt);
            }
        });
        jPanel1.add(btn_agregar, new org.netbeans.lib.awtextra.AbsoluteConstraints(529, 131, -1, 29));

        btn_modificar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/iconos/update.png"))); // NOI18N
        btn_modificar.setText("Modificar");
        btn_modificar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_modificarActionPerformed(evt);
            }
        });
        jPanel1.add(btn_modificar, new org.netbeans.lib.awtextra.AbsoluteConstraints(267, 131, -1, -1));

        btn_eliminar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/iconos/delete.png"))); // NOI18N
        btn_eliminar.setText("Eliminar");
        btn_eliminar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_eliminarActionPerformed(evt);
            }
        });
        jPanel1.add(btn_eliminar, new org.netbeans.lib.awtextra.AbsoluteConstraints(12, 131, -1, -1));

        jLabel5.setText("Stock critico (Kg):");
        jPanel1.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(12, 92, 93, -1));

        txt_stockCritico.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txt_stockCriticoKeyTyped(evt);
            }
        });
        jPanel1.add(txt_stockCritico, new org.netbeans.lib.awtextra.AbsoluteConstraints(115, 89, 155, -1));

        btn_aceptar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/iconos/OK-20.png"))); // NOI18N
        btn_aceptar.setText("Aceptar");
        btn_aceptar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_aceptarActionPerformed(evt);
            }
        });
        jPanel1.add(btn_aceptar, new org.netbeans.lib.awtextra.AbsoluteConstraints(136, 131, -1, -1));

        btn_cancelar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/iconos/delete.png"))); // NOI18N
        btn_cancelar.setText("Cancelar");
        btn_cancelar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_cancelarActionPerformed(evt);
            }
        });
        jPanel1.add(btn_cancelar, new org.netbeans.lib.awtextra.AbsoluteConstraints(380, 130, -1, -1));

        txt_stockactual.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txt_stockactualKeyTyped(evt);
            }
        });
        jPanel1.add(txt_stockactual, new org.netbeans.lib.awtextra.AbsoluteConstraints(435, 90, 160, -1));

        jLabel6.setText("Stock Actual (Kg):");
        jPanel1.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(320, 90, 93, 20));

        tbl_producto.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null}
            },
            new String [] {
                "Codigo", "Nombre", "Variedad", "Precio", "Stock critico"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, true, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane1.setViewportView(tbl_producto);
        if (tbl_producto.getColumnModel().getColumnCount() > 0) {
            tbl_producto.getColumnModel().getColumn(0).setResizable(false);
            tbl_producto.getColumnModel().getColumn(1).setResizable(false);
            tbl_producto.getColumnModel().getColumn(2).setResizable(false);
            tbl_producto.getColumnModel().getColumn(3).setResizable(false);
        }

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 632, Short.MAX_VALUE))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, 173, Short.MAX_VALUE)
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 207, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void txt_precioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_precioActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_precioActionPerformed

    private void btn_agregarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_agregarActionPerformed
        sw = 0;
        if (txt_codProducto.getText().equals("")){
            JOptionPane.showMessageDialog(null,"Para agregar debe al menos ingresar el codigo del producto.");
        }else{
            if (txt_nomProducto.getText().equals("")){
                JOptionPane.showMessageDialog(null,"Debe ingresar nombre producto");
            }else{
                if (txt_precio.getText().equals("")){
                    txt_precio.setText("0");
                }
                if (txt_variedad.getText().equals("")){
                    txt_variedad.setText(" ");
                }
                if (txt_stockCritico.getText().equals("")){
                    txt_stockCritico.setText("0");
                }
                if (txt_stockactual.getText().equals("")){
                    txt_stockactual.setText("0");
                }
                if (Integer.parseInt(txt_precio.getText()) == 0){
                    JOptionPane.showMessageDialog(null,"Debe ingresar un precio mayor a cero para el producto.");
                }else{
                    cod      = txt_codProducto.getText();
                    nom      = txt_nomProducto.getText();
                    variedad = txt_variedad.getText();
                    precio   = txt_precio.getText();
                    stockCritico = txt_stockCritico.getText();
                    stockActual = txt_stockactual.getText();
                    sql="INSERT INTO producto (cod_producto, nom_producto, variedad, precio, stock_critico, stock_actual) VALUES (?,?,?,?,?,?)";
                    try {
                        PreparedStatement pst=reg.prepareStatement(sql);
                        pst.setString(1,cod);
                        pst.setString(2,nom);
                        pst.setString(3,variedad);
                        pst.setString(4,precio);
                        pst.setString(5, stockCritico);
                        pst.setString(6, stockActual);
                        int n=pst.executeUpdate();
                        if (n>0){
                            JOptionPane.showMessageDialog(null,"Producto registrado satisfactoriamente.");
                        }                
                    }catch (SQLException ex) {
                        JOptionPane.showMessageDialog(null,ex);
                        sw = 1;
                    }
                    if (sw == 0){
                        txt_nomProducto.setText("");
                        txt_codProducto.setText("");
                        txt_variedad.setText("");
                        txt_precio.setText("");
                        txt_stockCritico.setText("");
                        txt_stockactual.setText("");
                        limpiartabla();
                        mostrardatostabla("");
                    }else{
                        txt_codProducto.requestFocus();
                    }
                }
            }
        }                                     
    }//GEN-LAST:event_btn_agregarActionPerformed

    private void btn_modificarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_modificarActionPerformed
        int fila=tbl_producto.getSelectedRow();
        if (fila>=0){
            txt_codProducto.setText(tbl_producto.getValueAt(fila, 0).toString());
            txt_nomProducto.setText(tbl_producto.getValueAt(fila, 1).toString());
            txt_variedad.setText(tbl_producto.getValueAt(fila, 2).toString());
            txt_precio.setText(tbl_producto.getValueAt(fila, 3).toString());
            txt_stockCritico.setText(tbl_producto.getValueAt(fila, 4).toString());
            txt_stockactual.setText(tbl_producto.getValueAt(fila, 5).toString());
            txt_codProducto.setEditable(false);
            txt_codProducto.setEnabled(false);
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
        if (txt_precio.getText().equals("")){
            txt_precio.setText("0");
        }
        if (txt_variedad.getText().equals("")){
            txt_variedad.setText(" ");
        }
        if (txt_stockCritico.getText().equals("")){
            txt_stockCritico.setText("0");
        }
        if (txt_stockactual.getText().equals("")){
            txt_stockactual.setText("0");
        }
        if (txt_nomProducto.getText().equals("")){
            JOptionPane.showMessageDialog(null,"Debe ingresar nombre de producto.");
            txt_nomProducto.requestFocus();
        }else{
            if (Integer.parseInt(txt_precio.getText()) == 0){
                JOptionPane.showMessageDialog(null,"Debe ingresar un precio mayor a cero para el producto.");
                txt_precio.requestFocus();
            }else{
                sql="UPDATE producto SET nom_producto='"+txt_nomProducto.getText()+"',variedad='"+txt_variedad.getText()+"', precio='"+txt_precio.getText()+"', stock_critico='"+txt_stockCritico.getText()+"', stock_actual='"+txt_stockactual.getText()+"' WHERE cod_producto='"+txt_codProducto.getText()+"'";
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
                    txt_codProducto.setEnabled(true);
                    txt_codProducto.setEditable(true);
                    txt_codProducto.requestFocus();
                    txt_nomProducto.setText("");
                    txt_variedad.setText("");
                    txt_precio.setText("");
                    txt_stockCritico.setText("");
                    txt_codProducto.setText("");
                    txt_stockactual.setText("");
                    limpiartabla();
                    mostrardatostabla("");
            }
        }
    }//GEN-LAST:event_btn_aceptarActionPerformed

    private void btn_eliminarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_eliminarActionPerformed
        int fila = tbl_producto.getSelectedRow();
        if (fila >= 0){   
            if(JOptionPane.showConfirmDialog(null, new Object[]{"Seguro que desea Eliminar fila seleccionada?"},"Eliminar",JOptionPane.OK_CANCEL_OPTION)==JOptionPane.YES_OPTION){
            //qui se pone lo que hara si le das aceptar
                fila=tbl_producto.getSelectedRow();
                txt_codProducto.setText(tbl_producto.getValueAt(fila, 0).toString());
                try {
                    PreparedStatement pst = reg.prepareStatement("DELETE FROM producto WHERE cod_producto='"+txt_codProducto.getText()+"'");
                    pst.executeUpdate();
                    limpiartabla();
                    mostrardatostabla("");
                    txt_codProducto.setText("");
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

    private void btn_cancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_cancelarActionPerformed
        // TODO add your handling code here:
                    btn_aceptar.setVisible(false);
                    btn_cancelar.setVisible(false);
                    btn_eliminar.setVisible(true);
                    btn_modificar.setVisible(true);
                    btn_agregar.setVisible(true);
                    txt_codProducto.setEnabled(true);
                    txt_codProducto.setEditable(true);
                    txt_codProducto.requestFocus();
                    txt_nomProducto.setText("");
                    txt_variedad.setText("");
                    txt_precio.setText("");
                    txt_stockCritico.setText("");
                    txt_stockactual.setText("");
                    txt_codProducto.setText("");        
    }//GEN-LAST:event_btn_cancelarActionPerformed

    private void txt_codProductoKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_codProductoKeyTyped
        int limite = 3;
        if (txt_codProducto.getText().length() == limite) {
            evt.consume();
        }
        char t = evt.getKeyChar();
        if (Character.isLetter(t)) {
            evt.consume();
            JOptionPane.showMessageDialog(null, "Ingrese solo numeros");
        }   
    }//GEN-LAST:event_txt_codProductoKeyTyped

    private void txt_nomProductoKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_nomProductoKeyTyped
        int limite = 30;
        if (txt_nomProducto.getText().length() == limite) {
            evt.consume();
        }
        char t = evt.getKeyChar();
        if (Character.isDigit(t)) {
            evt.consume();
            JOptionPane.showMessageDialog(null, "Ingrese solo letras");
        }   

    }//GEN-LAST:event_txt_nomProductoKeyTyped

    private void txt_variedadActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_variedadActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_variedadActionPerformed

    private void txt_variedadKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_variedadKeyTyped
        int limite = 10;
        if (txt_variedad.getText().length() == limite) {
            evt.consume();
        }
        char t = evt.getKeyChar();
        if (Character.isDigit(t)) {
            evt.consume();
            JOptionPane.showMessageDialog(null, "Ingrese solo letras");
        }   

    }//GEN-LAST:event_txt_variedadKeyTyped

    private void txt_precioKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_precioKeyTyped
        int limite = 10;
        if (txt_precio.getText().length() == limite) {
            evt.consume();
        }
        char t = evt.getKeyChar();
        if (Character.isLetter(t)) {
            evt.consume();
            JOptionPane.showMessageDialog(null, "Ingrese solo numeros");
        }  
    }//GEN-LAST:event_txt_precioKeyTyped

    private void txt_stockCriticoKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_stockCriticoKeyTyped
        int limite = 10;
        if (txt_stockCritico.getText().length() == limite) {
            evt.consume();
        }
        char t = evt.getKeyChar();
        if (Character.isLetter(t)) {
            evt.consume();
            JOptionPane.showMessageDialog(null, "Ingrese solo numeros");
        } 
        
     
     
    }//GEN-LAST:event_txt_stockCriticoKeyTyped

    private void formWindowClosing(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosing
        // TODO add your handling code here:
            con.cerrar();
    }//GEN-LAST:event_formWindowClosing

    private void txt_stockactualKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_stockactualKeyTyped
        int limite = 10;
        if (txt_stockactual.getText().length() == limite) {
            evt.consume();
        }
        char t = evt.getKeyChar();
        if (Character.isLetter(t)) {
            evt.consume();
            JOptionPane.showMessageDialog(null, "Ingrese solo numeros");
        }
    }//GEN-LAST:event_txt_stockactualKeyTyped

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
            java.util.logging.Logger.getLogger(mantenedorProductos.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(mantenedorProductos.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(mantenedorProductos.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(mantenedorProductos.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new mantenedorProductos().setVisible(true);
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
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable tbl_producto;
    private javax.swing.JTextField txt_codProducto;
    private javax.swing.JTextField txt_nomProducto;
    private javax.swing.JTextField txt_precio;
    private javax.swing.JTextField txt_stockCritico;
    private javax.swing.JTextField txt_stockactual;
    private javax.swing.JTextField txt_variedad;
    // End of variables declaration//GEN-END:variables
}
