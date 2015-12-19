/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hojasverdes;

import dominio.factura;
import java.sql.Connection;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DateFormat;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.Calendar;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.GregorianCalendar;

public class mantenedorFactura extends javax.swing.JFrame {
    DefaultTableModel modelo = new DefaultTableModel();
    Calendar c2 = new GregorianCalendar();
    conectar cnx = new conectar();
    Connection reg = cnx.conexion();
    int columna = 0 ;
    int sw = 0 ;
    String sql;
    /**
     * Creates new form mantenedorFactura
     */
    public mantenedorFactura() {
        initComponents();
        this.setLocationRelativeTo(null);
        this.setTitle("Factura");
        tbl_factura.setAutoCreateRowSorter(true);
        modelo.addColumn("Numero Factura");
        modelo.addColumn("Numero Nota");
        modelo.addColumn("Total Neto");
        modelo.addColumn("Fecha");
        tbl_factura.setModel(modelo);
        btn_aceptar.setVisible(false);
        btn_cancelar.setVisible(false);
        
        cmb_date.setCalendar(c2);
        mostrardatostabla("");
    }
    
    void mostrardatostabla (String valor){
        String []datos = new String[4];
        int cod;
        String sql="";
        if(valor.equals("")){
            sql = "SELECT * FROM factura";
        }else{
            sql = "SELECT * FROM factura WHERE nro_factura='"+valor+"'";
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
            tbl_factura.setModel(modelo);
            
        } catch (SQLException ex) {
            Logger.getLogger(producto.class.getName()).log(Level.SEVERE, null, ex);
        }
        llenarcmb();
    }
    
    public void llenarcmb(){
        try{
                cmb_notapedido.removeAllItems();
                String sql="select nro_nota from nota_pedido";
                Statement st = reg.createStatement();
                ResultSet rs = st.executeQuery(sql);
                while (rs.next()){
                    String name = rs.getString(1);
                    cmb_notapedido.addItem(name);
                }
            }catch(Exception e){
                
            }
    }
    
    public void limpiartabla(){
       // tabla.setModel(new DefaultTableModel());
        for (int i = 0; i < tbl_factura.getRowCount(); i++) {
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
        txt_numerofactura = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        cmb_notapedido = new javax.swing.JComboBox();
        jLabel3 = new javax.swing.JLabel();
        btn_agregar = new javax.swing.JButton();
        btn_modificar = new javax.swing.JButton();
        btn_eliminar = new javax.swing.JButton();
        btn_aceptar = new javax.swing.JButton();
        btn_cancelar = new javax.swing.JButton();
        btn_agregarnotapedido = new javax.swing.JButton();
        cmb_date = new com.toedter.calendar.JDateChooser();
        jLabel4 = new javax.swing.JLabel();
        txt_totalneto = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        tbl_factura = new javax.swing.JTable();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jPanel1.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jLabel1.setText("Numero factura:");

        txt_numerofactura.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txt_numerofacturaKeyTyped(evt);
            }
        });

        jLabel2.setText("Nota pedido:");

        jLabel3.setText("Fecha:");

        btn_agregar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/iconos/add.png"))); // NOI18N
        btn_agregar.setText("Agregar");
        btn_agregar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_agregarActionPerformed(evt);
            }
        });

        btn_modificar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/iconos/update.png"))); // NOI18N
        btn_modificar.setText("Modificar");
        btn_modificar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_modificarActionPerformed(evt);
            }
        });

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

        btn_cancelar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/iconos/delete.png"))); // NOI18N
        btn_cancelar.setText("Cancelar");
        btn_cancelar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_cancelarActionPerformed(evt);
            }
        });

        btn_agregarnotapedido.setIcon(new javax.swing.ImageIcon(getClass().getResource("/iconos/add.png"))); // NOI18N
        btn_agregarnotapedido.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_agregarnotapedidoActionPerformed(evt);
            }
        });

        jLabel4.setText("Total Neto:");

        txt_totalneto.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txt_totalnetoKeyTyped(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(btn_eliminar)
                        .addGap(26, 26, 26)
                        .addComponent(btn_aceptar)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btn_modificar)
                        .addGap(25, 25, 25)
                        .addComponent(btn_cancelar)
                        .addGap(18, 18, 18)
                        .addComponent(btn_agregar))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(jLabel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(txt_numerofactura)
                                    .addComponent(cmb_notapedido, 0, 148, Short.MAX_VALUE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(btn_agregarnotapedido, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(cmb_date, javax.swing.GroupLayout.PREFERRED_SIZE, 146, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txt_totalneto, javax.swing.GroupLayout.PREFERRED_SIZE, 148, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(txt_numerofactura, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel2)
                        .addComponent(cmb_notapedido, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(btn_agregarnotapedido))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(txt_totalneto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 11, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(cmb_date, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btn_modificar)
                    .addComponent(btn_eliminar)
                    .addComponent(btn_agregar)
                    .addComponent(btn_aceptar)
                    .addComponent(btn_cancelar))
                .addContainerGap())
        );

        tbl_factura.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Factura", "Nota pedido", "Fecha", "Total neto"
            }
        ));
        jScrollPane1.setViewportView(tbl_factura);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 596, Short.MAX_VALUE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 285, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(18, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btn_aceptarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_aceptarActionPerformed
        String notapedido=(cmb_notapedido.getSelectedItem().toString());
        Date fecha = cmb_date.getDate();
        java.sql.Date sqlfecha = new java.sql.Date(fecha.getTime());
        String totalneto=(txt_totalneto.getText().toString());

        System.out.println("fecha:"+sqlfecha);
        sql = "Update factura SET nro_nota='"+notapedido+"',total_neto='"+totalneto+"',fecha='"+sqlfecha+"' WHERE nro_factura="+txt_numerofactura.getText()+"";
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
        txt_numerofactura.setEnabled(true);
        txt_numerofactura.setEditable(true);
        txt_numerofactura.setText("");
        txt_totalneto.setText("");
        cmb_notapedido.removeAllItems();
        cmb_date.setCalendar(c2);

        limpiartabla();
        mostrardatostabla("");
    }//GEN-LAST:event_btn_aceptarActionPerformed

    private void btn_cancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_cancelarActionPerformed
        btn_aceptar.setVisible(false);
        btn_cancelar.setVisible(false);
        btn_eliminar.setVisible(true);
        btn_modificar.setVisible(true);
        btn_agregar.setVisible(true);
        txt_numerofactura.setEnabled(true);
        txt_numerofactura.requestFocus();
        txt_numerofactura.setText("");
        txt_totalneto.setText("");
        cmb_notapedido.removeAllItems();
        cmb_date.setCalendar(c2);
        limpiartabla();
        mostrardatostabla("");
    }//GEN-LAST:event_btn_cancelarActionPerformed

    private void btn_agregarnotapedidoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_agregarnotapedidoActionPerformed
        mantenedorNotaPedido abrir = new mantenedorNotaPedido();
        abrir.setVisible(true);
    }//GEN-LAST:event_btn_agregarnotapedidoActionPerformed

    private void btn_eliminarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_eliminarActionPerformed
        int fila = tbl_factura.getSelectedRow();
        if (fila >= 0){
            if (JOptionPane.showConfirmDialog(null, new Object[]{"Seguro que desea Eliminar fila seleccionada?"},"Eliminar",JOptionPane.OK_CANCEL_OPTION)==JOptionPane.YES_OPTION){
                fila = tbl_factura.getSelectedRow();
                txt_numerofactura.setText(tbl_factura.getValueAt(fila, 0).toString());
                try {
                    PreparedStatement pst = reg.prepareStatement("DELETE FROM factura WHERE nro_factura="+Integer.parseInt(txt_numerofactura.getText()+""));
                    pst.executeUpdate();
                    limpiartabla();
                    mostrardatostabla("");
                    txt_numerofactura.setText("");
                } catch (Exception e) {
                    System.out.println(e.getMessage());
                }
            }else{
                
            }
        }else{
            JOptionPane.showMessageDialog(null,"Debe seleccionar una fila antes de eliminar.");
        }
    }//GEN-LAST:event_btn_eliminarActionPerformed

    private void btn_modificarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_modificarActionPerformed
        int fila = tbl_factura.getSelectedRow();
        if (fila>=0){
            txt_numerofactura.setText(tbl_factura.getValueAt(fila, 0).toString());
            
            String rut=(tbl_factura.getValueAt(fila, 1).toString());
            cmb_notapedido.setSelectedItem(rut);
            txt_totalneto.setText(tbl_factura.getValueAt(fila, 2).toString());
            String fecha=(tbl_factura.getValueAt(fila, 3).toString());
            
            
            /*String[] fechArray = fecha.split("/");
            int año = Integer.valueOf(fechArray[0]);
            int mes = Integer.valueOf(fechArray[1]) - 1;
            int dia = Integer.valueOf(fechArray[2]);
            Calendar c1 = new GregorianCalendar(año, mes, dia);
            
            cmb_date.setCalendar(c1);*/
            
            
            
            txt_numerofactura.setEditable(false);
            txt_numerofactura.setEnabled(false);
            btn_agregar.setVisible(false);
            btn_modificar.setVisible(false);
            btn_eliminar.setVisible(false);
            btn_aceptar.setVisible(true);
            btn_cancelar.setVisible(true);
        }else{
            JOptionPane.showMessageDialog(null,"No selecciono fila");
        }
    }//GEN-LAST:event_btn_modificarActionPerformed

    private void btn_agregarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_agregarActionPerformed
        factura dto = new factura();
        dto.setNro_factura(Integer.parseInt(txt_numerofactura.getText()));
        dto.setNro_nota(Integer.parseInt(cmb_notapedido.getSelectedItem().toString()));
        dto.setTotal_neto(Integer.parseInt(txt_totalneto.getText()));
        Date fecha = cmb_date.getDate();
        java.sql.Date sqlfecha = new java.sql.Date(fecha.getTime());
        dto.setFecha(sqlfecha);
        
        
        sql = "INSERT INTO factura (nro_factura, nro_nota, total_neto, fecha) VALUES (?,?,?,?)";
        try {
            PreparedStatement pst = reg.prepareStatement(sql);
            pst.setInt(1, dto.getNro_factura());
            pst.setInt(2, dto.getNro_nota());
            pst.setInt(3, dto.getTotal_neto());
            pst.setDate(4, dto.getFecha());
            int n = pst.executeUpdate();
            if (n>0){
                JOptionPane.showMessageDialog(null,"Nota Pedido registrada satisfactoriamente.");
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null,"Error al agregar.");
        }
        txt_numerofactura.setText("");
        txt_totalneto.setText("");
        cmb_notapedido.removeAllItems();
        cmb_date.setCalendar(c2);
        
        
        limpiartabla();
        mostrardatostabla("");
    }//GEN-LAST:event_btn_agregarActionPerformed

    private void txt_numerofacturaKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_numerofacturaKeyTyped
        int limite = 5;
        if (txt_numerofactura.getText().length() == limite) {
            evt.consume();
        }
        char t = evt.getKeyChar();
        if (Character.isLetter(t)) {
            evt.consume();
            JOptionPane.showMessageDialog(null, "Ingrese solo numeros");
        }
    }//GEN-LAST:event_txt_numerofacturaKeyTyped

    private void txt_totalnetoKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_totalnetoKeyTyped
        int limite = 10;
        if (txt_totalneto.getText().length() == limite) {
            evt.consume();
        }
        
    }//GEN-LAST:event_txt_totalnetoKeyTyped

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
            java.util.logging.Logger.getLogger(mantenedorFactura.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(mantenedorFactura.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(mantenedorFactura.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(mantenedorFactura.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new mantenedorFactura().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btn_aceptar;
    private javax.swing.JButton btn_agregar;
    private javax.swing.JButton btn_agregarnotapedido;
    private javax.swing.JButton btn_cancelar;
    private javax.swing.JButton btn_eliminar;
    private javax.swing.JButton btn_modificar;
    private com.toedter.calendar.JDateChooser cmb_date;
    private javax.swing.JComboBox cmb_notapedido;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable tbl_factura;
    private javax.swing.JTextField txt_numerofactura;
    private javax.swing.JTextField txt_totalneto;
    // End of variables declaration//GEN-END:variables
}
