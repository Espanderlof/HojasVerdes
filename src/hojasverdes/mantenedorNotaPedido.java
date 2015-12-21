/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hojasverdes;

import dominio.notapedido;
import java.sql.Connection;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.ParseException;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.Calendar;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.GregorianCalendar;

public class mantenedorNotaPedido extends javax.swing.JFrame {
    DefaultTableModel modelo = new DefaultTableModel();
    Calendar c2 = new GregorianCalendar();
    conectar cnx = new conectar();
    Connection reg = cnx.conexion();
    int columna = 0 ;
    int sw = 0 ;
    String sql;
    /**
    /**
     * Creates new form mantenedorNotaPedido
     */
    public mantenedorNotaPedido() {
        initComponents();
        this.setLocationRelativeTo(null);
        this.setTitle("Mantenedor Nota Pedido");
        tbl_notapedido.setAutoCreateRowSorter(true);
        modelo.addColumn("Numero Nota");
        modelo.addColumn("Rut Cliente");
        modelo.addColumn("Fecha");
        tbl_notapedido.setModel(modelo);
        btn_aceptar.setVisible(false);
        btn_cancelar.setVisible(false);
        
        cmb_date.setCalendar(c2);
        mostrardatostabla("");
    }
    
    
    void mostrardatostabla (String valor){
        String []datos = new String[3];
        int cod;
        String sql="";
        if(valor.equals("")){
            sql = "SELECT * FROM nota_pedido";
        }else{
            sql = "SELECT * FROM nota_pedido WHERE nro_nota='"+valor+"'";
        }
        try {
            Statement st = reg.createStatement();
            ResultSet rs = st.executeQuery(sql);
            while (rs.next()){
                datos[0]=rs.getString(1);
                datos[1]=rs.getString(2);
                datos[2]=rs.getString(3);
                modelo.addRow(datos);
            }
            tbl_notapedido.setModel(modelo);
            
        } catch (SQLException ex) {
            Logger.getLogger(producto.class.getName()).log(Level.SEVERE, null, ex);
        }
        llenarcmb();
    }
    public void llenarcmb(){
        try{
                cmb_rutcliente.removeAllItems();
                String sql="select rut_cliente from cliente";
                Statement st = reg.createStatement();
                ResultSet rs = st.executeQuery(sql);
                while (rs.next()){
                    String name = rs.getString(1);
                    cmb_rutcliente.addItem(name);
                }
            }catch(Exception e){
                
            }
    }
    
    public void limpiartabla(){
       // tabla.setModel(new DefaultTableModel());
        for (int i = 0; i < tbl_notapedido.getRowCount(); i++) {
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
        txt_codigonotapedido = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        cmb_rutcliente = new javax.swing.JComboBox();
        btn_agregarCliente = new javax.swing.JButton();
        jLabel3 = new javax.swing.JLabel();
        btn_agregar = new javax.swing.JButton();
        btn_agregarDetalle = new javax.swing.JButton();
        btn_modificar = new javax.swing.JButton();
        btn_eliminar = new javax.swing.JButton();
        btn_aceptar = new javax.swing.JButton();
        btn_cancelar = new javax.swing.JButton();
        cmb_date = new com.toedter.calendar.JDateChooser();
        btn_refrescar = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        tbl_notapedido = new javax.swing.JTable();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent evt) {
                formWindowClosing(evt);
            }
        });

        jPanel1.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel1.setText("Codigo nota pedido:");
        jPanel1.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(12, 16, 119, -1));

        txt_codigonotapedido.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txt_codigonotapedidoKeyTyped(evt);
            }
        });
        jPanel1.add(txt_codigonotapedido, new org.netbeans.lib.awtextra.AbsoluteConstraints(141, 13, 192, -1));

        jLabel2.setText("Rut cliente:");
        jPanel1.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(12, 54, 102, -1));

        jPanel1.add(cmb_rutcliente, new org.netbeans.lib.awtextra.AbsoluteConstraints(141, 51, 192, -1));

        btn_agregarCliente.setIcon(new javax.swing.ImageIcon(getClass().getResource("/iconos/add.png"))); // NOI18N
        btn_agregarCliente.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_agregarClienteActionPerformed(evt);
            }
        });
        jPanel1.add(btn_agregarCliente, new org.netbeans.lib.awtextra.AbsoluteConstraints(343, 51, 29, 20));

        jLabel3.setText("Fecha:");
        jPanel1.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(12, 97, -1, -1));

        btn_agregar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/iconos/add.png"))); // NOI18N
        btn_agregar.setText("Agregar");
        btn_agregar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_agregarActionPerformed(evt);
            }
        });
        jPanel1.add(btn_agregar, new org.netbeans.lib.awtextra.AbsoluteConstraints(518, 141, -1, 29));

        btn_agregarDetalle.setIcon(new javax.swing.ImageIcon(getClass().getResource("/iconos/pedido.png"))); // NOI18N
        btn_agregarDetalle.setText("Agregar detalle");
        btn_agregarDetalle.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_agregarDetalleActionPerformed(evt);
            }
        });
        jPanel1.add(btn_agregarDetalle, new org.netbeans.lib.awtextra.AbsoluteConstraints(466, 84, -1, -1));

        btn_modificar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/iconos/update.png"))); // NOI18N
        btn_modificar.setText("Modificar");
        btn_modificar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_modificarActionPerformed(evt);
            }
        });
        jPanel1.add(btn_modificar, new org.netbeans.lib.awtextra.AbsoluteConstraints(260, 140, -1, -1));

        btn_eliminar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/iconos/delete.png"))); // NOI18N
        btn_eliminar.setText("Eliminar");
        btn_eliminar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_eliminarActionPerformed(evt);
            }
        });
        jPanel1.add(btn_eliminar, new org.netbeans.lib.awtextra.AbsoluteConstraints(12, 141, -1, -1));

        btn_aceptar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/iconos/OK-20.png"))); // NOI18N
        btn_aceptar.setText("Aceptar");
        btn_aceptar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_aceptarActionPerformed(evt);
            }
        });
        jPanel1.add(btn_aceptar, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 140, -1, -1));

        btn_cancelar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/iconos/delete.png"))); // NOI18N
        btn_cancelar.setText("Cancelar");
        btn_cancelar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_cancelarActionPerformed(evt);
            }
        });
        jPanel1.add(btn_cancelar, new org.netbeans.lib.awtextra.AbsoluteConstraints(370, 140, -1, -1));
        jPanel1.add(cmb_date, new org.netbeans.lib.awtextra.AbsoluteConstraints(141, 89, 146, -1));

        btn_refrescar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/iconos/refresh-20.png"))); // NOI18N
        btn_refrescar.setText("Refrescar");
        btn_refrescar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_refrescarActionPerformed(evt);
            }
        });
        jPanel1.add(btn_refrescar, new org.netbeans.lib.awtextra.AbsoluteConstraints(470, 50, -1, -1));

        tbl_notapedido.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null}
            },
            new String [] {
                "Codigo", "Rut", "Fecha"
            }
        ));
        jScrollPane1.setViewportView(tbl_notapedido);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 621, Short.MAX_VALUE)
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 181, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 252, Short.MAX_VALUE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btn_agregarDetalleActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_agregarDetalleActionPerformed
        mantenedorDetallePedido abrirDetallePedido = new mantenedorDetallePedido();
        abrirDetallePedido.setVisible(true);
    }//GEN-LAST:event_btn_agregarDetalleActionPerformed

    private void btn_aceptarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_aceptarActionPerformed
        String rut=(cmb_rutcliente.getSelectedItem().toString());
        Date fecha = cmb_date.getDate();
        java.sql.Date sqlfecha = new java.sql.Date(fecha.getTime());
        
        System.out.println("fecha:"+sqlfecha);
        sql = "Update nota_pedido SET rut_cliente='"+rut+"',fecha='"+sqlfecha+"' WHERE nro_nota="+txt_codigonotapedido.getText()+"";
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
        txt_codigonotapedido.setEnabled(true);
        txt_codigonotapedido.setEditable(true);
        txt_codigonotapedido.setText("");
        cmb_rutcliente.removeAllItems();
        cmb_date.setCalendar(c2);
        btn_agregarDetalle.setVisible(true);
        btn_agregarCliente.setVisible(true);
        btn_refrescar.setVisible(true);
        
        limpiartabla();
        mostrardatostabla("");
    }//GEN-LAST:event_btn_aceptarActionPerformed

    private void btn_cancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_cancelarActionPerformed
        btn_aceptar.setVisible(false);
        btn_cancelar.setVisible(false);
        btn_eliminar.setVisible(true);
        btn_modificar.setVisible(true);
        btn_agregar.setVisible(true);
        txt_codigonotapedido.setEnabled(true);
        txt_codigonotapedido.setEditable(true);
        txt_codigonotapedido.requestFocus();
        txt_codigonotapedido.setText("");
        cmb_rutcliente.removeAllItems();
        cmb_date.setCalendar(c2);
        btn_agregarDetalle.setVisible(true);
        btn_agregarCliente.setVisible(true);
        btn_refrescar.setVisible(true);
        limpiartabla();
        mostrardatostabla("");
    }//GEN-LAST:event_btn_cancelarActionPerformed

    private void btn_agregarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_agregarActionPerformed
        if (txt_codigonotapedido.getText().equals("")){
            JOptionPane.showMessageDialog(null,"Debe ingresar codigo nota pedido");
        }else{
            notapedido dto = new notapedido();
            dto.setNro_nota(Integer.parseInt(txt_codigonotapedido.getText()));
            dto.setRut_cliente(cmb_rutcliente.getSelectedItem().toString());
            Date fecha = cmb_date.getDate();
            java.sql.Date sqlfecha = new java.sql.Date(fecha.getTime());
            dto.setFecha(sqlfecha);


            sql = "INSERT INTO nota_pedido (nro_nota, rut_cliente, fecha) VALUES (?,?,?)";
            try {
                PreparedStatement pst = reg.prepareStatement(sql);
                pst.setInt(1, dto.getNro_nota());
                pst.setString(2, dto.getRut_cliente());
                pst.setDate(3, dto.getFecha());
                int n = pst.executeUpdate();
                if (n>0){
                    JOptionPane.showMessageDialog(null,"Nota Pedido registrada satisfactoriamente.");
                }
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(null,"Error al agregar.");
            }
            txt_codigonotapedido.setText("");
            cmb_rutcliente.removeAllItems();
            cmb_date.setCalendar(c2);


            limpiartabla();
            mostrardatostabla("");
        }
        
    }//GEN-LAST:event_btn_agregarActionPerformed

    private void btn_modificarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_modificarActionPerformed
        int fila = tbl_notapedido.getSelectedRow();
        if (fila>=0){
            txt_codigonotapedido.setText(tbl_notapedido.getValueAt(fila, 0).toString());
            
            String rut=(tbl_notapedido.getValueAt(fila, 1).toString());
            cmb_rutcliente.setSelectedItem(rut);
            String fecha=(tbl_notapedido.getValueAt(fila, 2).toString());
            
            
            /*String[] fechArray = fecha.split("/");
            int año = Integer.valueOf(fechArray[0]);
            int mes = Integer.valueOf(fechArray[1]) - 1;
            int dia = Integer.valueOf(fechArray[2]);
            Calendar c1 = new GregorianCalendar(año, mes, dia);
            
            cmb_date.setCalendar(c1);*/
            
            
            
            txt_codigonotapedido.setEditable(false);
            txt_codigonotapedido.setEnabled(false);
            btn_agregar.setVisible(false);
            btn_modificar.setVisible(false);
            btn_eliminar.setVisible(false);
            btn_aceptar.setVisible(true);
            btn_cancelar.setVisible(true);
            btn_agregarDetalle.setVisible(false);
            btn_agregarCliente.setVisible(false);
            btn_refrescar.setVisible(false);
        }else{
            JOptionPane.showMessageDialog(null,"No selecciono fila");
        }
    }//GEN-LAST:event_btn_modificarActionPerformed

    private void btn_eliminarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_eliminarActionPerformed
        int fila = tbl_notapedido.getSelectedRow();
        if (fila >= 0){
            if (JOptionPane.showConfirmDialog(null, new Object[]{"Seguro que desea Eliminar fila seleccionada?"},"Eliminar",JOptionPane.OK_CANCEL_OPTION)==JOptionPane.YES_OPTION){
                fila = tbl_notapedido.getSelectedRow();
                txt_codigonotapedido.setText(tbl_notapedido.getValueAt(fila, 0).toString());
                try {
                    PreparedStatement pst = reg.prepareStatement("DELETE FROM nota_pedido WHERE nro_nota="+Integer.parseInt(txt_codigonotapedido.getText()+""));
                    pst.executeUpdate();
                    limpiartabla();
                    mostrardatostabla("");
                    txt_codigonotapedido.setText("");
                } catch (Exception e) {
                    System.out.println(e.getMessage());
                }
            }else{
                
            }
        }else{
            JOptionPane.showMessageDialog(null,"Debe seleccionar una fila antes de eliminar.");
        }
    }//GEN-LAST:event_btn_eliminarActionPerformed

    private void btn_agregarClienteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_agregarClienteActionPerformed
        mantenedorCliente abrir = new mantenedorCliente();
        abrir.setVisible(true);
    }//GEN-LAST:event_btn_agregarClienteActionPerformed

    private void txt_codigonotapedidoKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_codigonotapedidoKeyTyped
        int limite = 5;
        if (txt_codigonotapedido.getText().length() == limite) {
            evt.consume();
        }
        char t = evt.getKeyChar();
        if (Character.isLetter(t)) {
            evt.consume();
            JOptionPane.showMessageDialog(null, "Ingrese solo numeros");
        }   
    }//GEN-LAST:event_txt_codigonotapedidoKeyTyped

    private void formWindowClosing(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosing
        // TODO add your handling code here:
        cnx.cerrar();
    }//GEN-LAST:event_formWindowClosing

    private void btn_refrescarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_refrescarActionPerformed
        
        cmb_date.setCalendar(c2);
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
            java.util.logging.Logger.getLogger(mantenedorNotaPedido.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(mantenedorNotaPedido.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(mantenedorNotaPedido.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(mantenedorNotaPedido.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new mantenedorNotaPedido().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btn_aceptar;
    private javax.swing.JButton btn_agregar;
    private javax.swing.JButton btn_agregarCliente;
    private javax.swing.JButton btn_agregarDetalle;
    private javax.swing.JButton btn_cancelar;
    private javax.swing.JButton btn_eliminar;
    private javax.swing.JButton btn_modificar;
    private javax.swing.JButton btn_refrescar;
    private com.toedter.calendar.JDateChooser cmb_date;
    private javax.swing.JComboBox cmb_rutcliente;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable tbl_notapedido;
    private javax.swing.JTextField txt_codigonotapedido;
    // End of variables declaration//GEN-END:variables
}
