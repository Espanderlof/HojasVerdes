/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hojasverdes;

import dominio.detallePedido;
import dominio.detalleRecepcion;
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

public class mantenedorDetallePedido extends javax.swing.JFrame {
    DefaultTableModel modelo = new DefaultTableModel();
    DefaultTableModel modelo2 = new DefaultTableModel();
    conectar con= new conectar();
    Connection reg= con.conexion();
    private TableRowSorter trsfiltro;
    int columna=0;
    String sql;
    int fila;
    /**
     * Creates new form mantenedorDetallePedido
     */
    public mantenedorDetallePedido() {
        initComponents();
        this.setLocationRelativeTo(null);
        this.setTitle("Detalle Pedido");
        tbl_detallepedido.setAutoCreateRowSorter(true);
        modelo.addColumn("Numero Nota");
        modelo.addColumn("Codigo Producto");
        modelo.addColumn("Nombre producto");
        modelo.addColumn("Variedad");
        modelo.addColumn("Cantidad");
        modelo.addColumn("Precio");
        tbl_detallepedido.setModel(modelo);
        btn_aceptar.setVisible(false);
        btn_cancelar.setVisible(false);
        txt_label.setEnabled(false);
        mostrardatostabla("");
        lbl_codigo.setVisible(false);
        txt_label.setVisible(false);
        
    }
    
    void mostrardatostabla (String valor){
        String []datos = new String[6];
        int cod;
        String sql="";
        if(valor.equals("")){
            sql = "SELECT d.nro_nota, d.cod_producto, p.nom_producto, p.variedad, d.cantidad, d.precio FROM detalle_pedido d, producto p where d.cod_producto = p.cod_producto ";
        }else{
            sql = "SELECT * FROM detalle_pedido WHERE nro_nota='"+valor+"'";
        }
        try {
            Statement st = reg.createStatement();
            ResultSet rs = st.executeQuery(sql);
            while (rs.next()){
                datos[0]=rs.getString(1);
                datos[1]=rs.getString(2);
                datos[2]=rs.getString(3);
                datos[3]=rs.getString(4);
                datos[4]=rs.getString(5);
                datos[5]=rs.getString(6);
                modelo.addRow(datos);
            }
            tbl_detallepedido.setModel(modelo);
            
        } catch (SQLException ex) {
            Logger.getLogger(producto.class.getName()).log(Level.SEVERE, null, ex);
        }
        llenarcmb();
    }
    public void llenarcmb(){
        try{
                cmb_notapedido.removeAllItems();
                String sql="select nro_nota from nota_pedido order by nro_nota";
                Statement st = reg.createStatement();
                ResultSet rs = st.executeQuery(sql);
                while (rs.next()){
                    String name = rs.getString(1);
                    cmb_notapedido.addItem(name);
                }
            }catch(Exception e){
                
            }
        try{
                cmb_nombreproducto.removeAllItems();
                String sql="select distinct(nom_producto) from producto";
                Statement st = reg.createStatement();
                ResultSet rs = st.executeQuery(sql);
                while (rs.next()){
                    String name = rs.getString(1);
                    cmb_nombreproducto.addItem(name);
                }
            }catch(Exception e){
                
            }
        try{
            cmb_variedad.removeAllItems();
            String sql="select variedad from producto where nom_producto ='"+cmb_nombreproducto.getSelectedItem()+"'";
            Statement st = reg.createStatement();
            ResultSet rs = st.executeQuery(sql);
            while (rs.next()){
                cmb_variedad.addItem(rs.getString(1));
            }  
        }catch(Exception e){
            
        }
    }
    
    
    public String getCodProducto(){
        String codigo="";
        try{
            String sql="select cod_producto from producto where nom_producto ='"+cmb_nombreproducto.getSelectedItem()+"' and variedad='"+cmb_variedad.getSelectedItem()+"' ";
            Statement st = reg.createStatement();
            ResultSet rs = st.executeQuery(sql);
            while (rs.next()){
                codigo = rs.getString(1);
            }   
        }catch(Exception e){
            
        }
        return codigo;
    }
    
    public void limpiartabla(){
       // tabla.setModel(new DefaultTableModel());
        for (int i = 0; i < tbl_detallepedido.getRowCount(); i++) {
           modelo.removeRow(i);
           i-=1;
       }
    }
    
    public void verificarStock(){
        int stock = 0;
        int sw = 0;
        try{
            String sql="select stock_actual from producto where cod_producto ="+getCodProducto()+"";
            Statement st = reg.createStatement();
            ResultSet rs = st.executeQuery(sql);
            while (rs.next()){
                stock = (Integer.parseInt(rs.getString(1)));
            }
            if (stock >= Integer.parseInt(txt_cantidad.getText())){
                //String []datos = new String[3];
                int cantidad = Integer.parseInt(txt_cantidad.getText());
                try{
                    int codigo=0;
                    int kilos=0;
                    sql = "select cod_lote, kilos_final from lote where cod_producto = "+getCodProducto()+" and kilos_final > 0 order by fecha";
                    rs = st.executeQuery(sql);
                    while (rs.next()){
                        codigo = Integer.parseInt(rs.getString(1)); //300  //450
                        kilos= Integer.parseInt(rs.getString(2)); //1
                        if (sw == 0){
                            try{
                                if (kilos>= cantidad){
                                    int aux = kilos - cantidad;
                                    cantidad = kilos - cantidad;
                                    String sql2 = "update lote set kilos_final = "+aux+" where cod_lote = "+codigo+" ";
                                    sw = 1;
                                    PreparedStatement pst = reg.prepareStatement(sql2);
                                    pst.executeUpdate();
                                }else{
                                    int aux = cantidad - kilos; // 450 - 300 // 150
                                    cantidad = cantidad - kilos; //450 - 300 // 150
                                    int x = 0;
                                    String sql2 = "update lote set kilos_final = "+x+" where cod_lote = "+codigo+"";
                                    PreparedStatement pst = reg.prepareStatement(sql2);
                                    pst.executeUpdate();
                                }
                            }catch(Exception e){
                            }
                        }
                    }
                try{
                    int aux = stock - Integer.parseInt(txt_cantidad.getText());
                    String sql3 = "update producto set stock_actual ="+aux+" where cod_producto = "+getCodProducto()+"";
                    PreparedStatement pst = reg.prepareStatement(sql3);
                    pst.executeUpdate();
                }catch(Exception e){
                    
                }
                }catch(Exception e){
                }
            }else{
                JOptionPane.showMessageDialog(null,"Stock de producto no es suficiente para realizar el pedido");
            }
        }catch(Exception e){
        }
        
    }
    
    public boolean verificarStock2(){
        int stock = 0;
        int sw = 0;
        try{
            String sql="select stock_actual from producto where cod_producto ="+getCodProducto()+"";
            Statement st = reg.createStatement();
            ResultSet rs = st.executeQuery(sql);
            while (rs.next()){
                stock = (Integer.parseInt(rs.getString(1)));
            }
        }catch(Exception e){
        }
        if (stock >= Integer.parseInt(txt_cantidad.getText())){
            return true;
        }else{
            return false;
        }
    }
    
    public void verificarStock3(){
        int stock = 0;
        int sw = 0;
        try{
            String sql="select stock_actual from producto where cod_producto ="+getCodProducto()+"";
            Statement st = reg.createStatement();
            ResultSet rs = st.executeQuery(sql);
            while (rs.next()){
                stock = (Integer.parseInt(rs.getString(1)));
            }
            stock = stock - Integer.parseInt(txt_cantidad.getText());
            String sql4 = "update producto set stock_actual ="+stock+" where cod_producto = '"+getCodProducto()+"'";
            PreparedStatement pst = reg.prepareStatement(sql4);
            pst.executeUpdate();
        }catch(Exception e){
        }

    }
    
    public void actualizarStock2(){
        int aux = Integer.parseInt(tbl_detallepedido.getValueAt(fila, 4).toString());
        String cod = tbl_detallepedido.getValueAt(fila, 1).toString();
        //JOptionPane.showMessageDialog(null,aux);
        int stock = 0;
        int sw = 0;
        try{
            String sql="select stock_actual from producto where cod_producto ='"+cod+"'";
            Statement st = reg.createStatement();
            ResultSet rs = st.executeQuery(sql);
            while (rs.next()){
                stock = (Integer.parseInt(rs.getString(1)));
            }
            //JOptionPane.showMessageDialog(null,stock);
            //aux = 200
            //stock = 1500
            //nuevo = 100
            //JOptionPane.showMessageDialog(null, Integer.parseInt(txt_cantidad.getText()));
            if (aux > Integer.parseInt(txt_cantidad.getText())){
                stock = stock + (aux - Integer.parseInt(txt_cantidad.getText()));
            }else{
                stock = stock + (Integer.parseInt(txt_cantidad.getText()) - aux);
            }
            //JOptionPane.showMessageDialog(null,stock);
            
            String sql3 = "update producto set stock_actual ="+stock+" where cod_producto = '"+cod+"'";
            PreparedStatement pst = reg.prepareStatement(sql3);
            pst.executeUpdate();
        }catch(Exception e){
            
        }
    }
    
    public void actualizarStock3(){
        int aux = Integer.parseInt(tbl_detallepedido.getValueAt(fila, 4).toString());
        String cod = tbl_detallepedido.getValueAt(fila, 1).toString();
        int stock = 0;
        int sw = 0;
        try{
            String sql="select stock_actual from producto where cod_producto ='"+cod+"'";
            Statement st = reg.createStatement();
            ResultSet rs = st.executeQuery(sql);
            while (rs.next()){
                stock = (Integer.parseInt(rs.getString(1)));
            }
            //aux = 200
            //stock = 1500
            //nuevo = 100
            stock = stock + aux;            
            String sql3 = "update producto set stock_actual ="+stock+" where cod_producto = '"+cod+"'";
            PreparedStatement pst = reg.prepareStatement(sql3);
            pst.executeUpdate();
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

        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        cmb_notapedido = new javax.swing.JComboBox();
        jLabel2 = new javax.swing.JLabel();
        cmb_nombreproducto = new javax.swing.JComboBox();
        btn_agregarProducto = new javax.swing.JButton();
        jLabel3 = new javax.swing.JLabel();
        cmb_variedad = new javax.swing.JComboBox();
        jLabel4 = new javax.swing.JLabel();
        txt_cantidad = new javax.swing.JTextField();
        btn_agregar = new javax.swing.JButton();
        btn_modificar = new javax.swing.JButton();
        btn_eliminar = new javax.swing.JButton();
        jLabel5 = new javax.swing.JLabel();
        txt_precio = new javax.swing.JTextField();
        btn_agregarNota = new javax.swing.JButton();
        lbl_codigo = new javax.swing.JLabel();
        txt_label = new javax.swing.JTextField();
        btn_aceptar = new javax.swing.JButton();
        btn_cancelar = new javax.swing.JButton();
        btn_refrescar = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        tbl_detallepedido = new javax.swing.JTable();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setResizable(false);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent evt) {
                formWindowClosing(evt);
            }
        });

        jPanel1.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel1.setText("Seleccione nota pedido:");
        jPanel1.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(12, 16, -1, -1));

        cmb_notapedido.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmb_notapedidoActionPerformed(evt);
            }
        });
        jPanel1.add(cmb_notapedido, new org.netbeans.lib.awtextra.AbsoluteConstraints(153, 13, 155, -1));

        jLabel2.setText("Nombre producto:");
        jPanel1.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(12, 60, -1, -1));

        cmb_nombreproducto.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        cmb_nombreproducto.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cmb_nombreproductoItemStateChanged(evt);
            }
        });
        jPanel1.add(cmb_nombreproducto, new org.netbeans.lib.awtextra.AbsoluteConstraints(153, 57, 155, -1));

        btn_agregarProducto.setIcon(new javax.swing.ImageIcon(getClass().getResource("/iconos/add.png"))); // NOI18N
        btn_agregarProducto.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_agregarProductoActionPerformed(evt);
            }
        });
        jPanel1.add(btn_agregarProducto, new org.netbeans.lib.awtextra.AbsoluteConstraints(318, 57, 33, 20));

        jLabel3.setText("Variedad:");
        jPanel1.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(12, 98, -1, -1));

        cmb_variedad.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        cmb_variedad.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cmb_variedadItemStateChanged(evt);
            }
        });
        jPanel1.add(cmb_variedad, new org.netbeans.lib.awtextra.AbsoluteConstraints(153, 95, 155, -1));

        jLabel4.setText("Cantidad (Kg):");
        jPanel1.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(12, 136, -1, -1));

        txt_cantidad.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txt_cantidadKeyTyped(evt);
            }
        });
        jPanel1.add(txt_cantidad, new org.netbeans.lib.awtextra.AbsoluteConstraints(153, 133, 155, -1));

        btn_agregar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/iconos/add.png"))); // NOI18N
        btn_agregar.setText("Agregar");
        btn_agregar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_agregarActionPerformed(evt);
            }
        });
        jPanel1.add(btn_agregar, new org.netbeans.lib.awtextra.AbsoluteConstraints(460, 170, -1, 29));

        btn_modificar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/iconos/update.png"))); // NOI18N
        btn_modificar.setText("Modificar");
        btn_modificar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_modificarActionPerformed(evt);
            }
        });
        jPanel1.add(btn_modificar, new org.netbeans.lib.awtextra.AbsoluteConstraints(240, 170, -1, -1));

        btn_eliminar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/iconos/delete.png"))); // NOI18N
        btn_eliminar.setText("Eliminar");
        btn_eliminar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_eliminarActionPerformed(evt);
            }
        });
        jPanel1.add(btn_eliminar, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 170, -1, -1));

        jLabel5.setText("Precio:");
        jPanel1.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(339, 136, -1, -1));

        txt_precio.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txt_precioKeyTyped(evt);
            }
        });
        jPanel1.add(txt_precio, new org.netbeans.lib.awtextra.AbsoluteConstraints(390, 133, 123, -1));

        btn_agregarNota.setIcon(new javax.swing.ImageIcon(getClass().getResource("/iconos/add.png"))); // NOI18N
        btn_agregarNota.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_agregarNotaActionPerformed(evt);
            }
        });
        jPanel1.add(btn_agregarNota, new org.netbeans.lib.awtextra.AbsoluteConstraints(318, 13, 33, 20));

        lbl_codigo.setText("Codigo Producto:");
        jPanel1.add(lbl_codigo, new org.netbeans.lib.awtextra.AbsoluteConstraints(370, 20, -1, -1));

        txt_label.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_labelActionPerformed(evt);
            }
        });
        jPanel1.add(txt_label, new org.netbeans.lib.awtextra.AbsoluteConstraints(460, 20, 93, -1));

        btn_aceptar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/iconos/OK-20.png"))); // NOI18N
        btn_aceptar.setText("Aceptar");
        btn_aceptar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_aceptarActionPerformed(evt);
            }
        });
        jPanel1.add(btn_aceptar, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 170, -1, -1));

        btn_cancelar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/iconos/delete.png"))); // NOI18N
        btn_cancelar.setText("Cancelar");
        btn_cancelar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_cancelarActionPerformed(evt);
            }
        });
        jPanel1.add(btn_cancelar, new org.netbeans.lib.awtextra.AbsoluteConstraints(350, 170, -1, -1));

        btn_refrescar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/iconos/refresh-20.png"))); // NOI18N
        btn_refrescar.setText("Refrescar");
        btn_refrescar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_refrescarActionPerformed(evt);
            }
        });
        jPanel1.add(btn_refrescar, new org.netbeans.lib.awtextra.AbsoluteConstraints(400, 70, -1, -1));

        tbl_detallepedido.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null}
            },
            new String [] {
                "Nota pedido", "Producto", "Variedad", "Cantidad", "Precio"
            }
        ));
        jScrollPane1.setViewportView(tbl_detallepedido);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 567, Short.MAX_VALUE)
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 206, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 313, Short.MAX_VALUE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btn_aceptarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_aceptarActionPerformed
        if (txt_cantidad.getText().equals("")){
            JOptionPane.showMessageDialog(null,"Debe ingresar cantidad");
        }else{
            if (txt_precio.getText().equals("")){
                JOptionPane.showMessageDialog(null,"Debe ingresar precio");
            }else{
                if (Integer.parseInt(txt_precio.getText()) == 0){
                    JOptionPane.showMessageDialog(null,"Kilos inicial no puede ser 0.");
                 }else{
                        //actualizarStock2();
                        String envio = cmb_notapedido.getSelectedItem().toString();
                        String nom = cmb_nombreproducto.getSelectedItem().toString();
                        String var = cmb_variedad.getSelectedItem().toString();
                        sql="UPDATE detalle_pedido SET cantidad="+Integer.parseInt(txt_cantidad.getText())+",precio= "+Integer.parseInt(txt_precio.getText())+"  WHERE nro_nota="+Integer.parseInt(cmb_notapedido.getSelectedItem().toString())+" and cod_producto="+txt_label.getText()+"";
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
                        //cmb_guiaEnvio.setEditable(true);
                        cmb_notapedido.setEnabled(true);
                        //cmb_nomProducto.setEditable(true);
                        cmb_nombreproducto.setEnabled(true);
                        //cmb_variedad.setEditable(true);
                        cmb_variedad.setEnabled(true);
                        txt_cantidad.setText("");
                        txt_precio.setText("");
                        cmb_notapedido.removeAllItems();
                        cmb_nombreproducto.removeAllItems();
                        cmb_variedad.removeAllItems();
                        btn_refrescar.setVisible(true);
                        btn_agregarProducto.setVisible(true);
                        btn_agregarNota.setVisible(true);
                        limpiartabla();
                        mostrardatostabla("");
                }
            }
        }
    }//GEN-LAST:event_btn_aceptarActionPerformed

    private void btn_cancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_cancelarActionPerformed
        String envio = cmb_notapedido.getSelectedItem().toString();
        String nom = cmb_nombreproducto.getSelectedItem().toString();
        String var = cmb_variedad.getSelectedItem().toString();
        btn_aceptar.setVisible(false);
        btn_cancelar.setVisible(false);
        btn_eliminar.setVisible(true);
        btn_modificar.setVisible(true);
        btn_agregar.setVisible(true);
        //cmb_guiaEnvio.setEditable(true);
        cmb_notapedido.setEnabled(true);
        //cmb_nomProducto.setEditable(true);
        cmb_nombreproducto.setEnabled(true);
        //cmb_variedad.setEditable(true);
        cmb_variedad.setEnabled(true);
        cmb_notapedido.requestFocus();
        txt_cantidad.setText("");
        txt_precio.setText("");
        cmb_notapedido.removeAllItems();
        cmb_nombreproducto.removeAllItems();
        cmb_variedad.removeAllItems();
        limpiartabla();
        mostrardatostabla("");
        btn_refrescar.setVisible(true);
        btn_agregarProducto.setVisible(true);
        btn_agregarNota.setVisible(true);
    }//GEN-LAST:event_btn_cancelarActionPerformed

    private void btn_agregarNotaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_agregarNotaActionPerformed
        mantenedorNotaPedido abrir = new mantenedorNotaPedido();
        abrir.setVisible(true);
    }//GEN-LAST:event_btn_agregarNotaActionPerformed

    private void btn_agregarProductoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_agregarProductoActionPerformed
        mantenedorProductos abrir = new mantenedorProductos();
        abrir.setVisible(true);
    }//GEN-LAST:event_btn_agregarProductoActionPerformed

    private void cmb_nombreproductoItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cmb_nombreproductoItemStateChanged
        try{
            cmb_variedad.removeAllItems();
            String sql="select variedad from producto where nom_producto ='"+cmb_nombreproducto.getSelectedItem()+"'";
            Statement st = reg.createStatement();
            ResultSet rs = st.executeQuery(sql);
            while (rs.next()){
                cmb_variedad.addItem(rs.getString(1));
            }  
        }catch(Exception e){
            
        }
        String cod = getCodProducto();
        txt_label.setText(cod);
    }//GEN-LAST:event_cmb_nombreproductoItemStateChanged

    private void txt_labelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_labelActionPerformed

    }//GEN-LAST:event_txt_labelActionPerformed

    private void cmb_variedadItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cmb_variedadItemStateChanged
        String cod = getCodProducto();
        txt_label.setText(cod);
    }//GEN-LAST:event_cmb_variedadItemStateChanged

    private void btn_eliminarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_eliminarActionPerformed
        int fila = tbl_detallepedido.getSelectedRow();
        if (fila >= 0){   
            if(JOptionPane.showConfirmDialog(null, new Object[]{"Seguro que desea Eliminar fila seleccionada?"},"Eliminar",JOptionPane.OK_CANCEL_OPTION)==JOptionPane.YES_OPTION){
            //qui se pone lo que hara si le das aceptar
                
                fila=tbl_detallepedido.getSelectedRow();
                //actualizarStock3();
                //txt_patente.setText(tbl_camiones.getValueAt(fila, 0).toString());
                cmb_notapedido.setSelectedItem(tbl_detallepedido.getValueAt(fila, 0).toString());
                String x = tbl_detallepedido.getValueAt(fila, 1).toString();
                //JOptionPane.showMessageDialog(null, cmb_notapedido.getSelectedItem().toString());
                //JOptionPane.showMessageDialog(null, x);
                try {
                    PreparedStatement pst = reg.prepareStatement("DELETE FROM detalle_pedido WHERE nro_nota='"+Integer.parseInt(cmb_notapedido.getSelectedItem().toString())+"' and cod_producto='"+x+"' ");
                    pst.executeUpdate();
                    limpiartabla();
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

    private void btn_modificarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_modificarActionPerformed
        int fila=tbl_detallepedido.getSelectedRow();
        if (fila>=0){
            cmb_notapedido.removeAllItems();
            cmb_notapedido.addItem(tbl_detallepedido.getValueAt(fila, 0).toString());
            cmb_nombreproducto.removeAllItems();
            cmb_nombreproducto.addItem(tbl_detallepedido.getValueAt(fila, 2).toString());
            cmb_variedad.removeAllItems();
            cmb_variedad.addItem(tbl_detallepedido.getValueAt(fila, 3).toString());
            
            txt_cantidad.setText(tbl_detallepedido.getValueAt(fila, 4).toString());
            txt_precio.setText(tbl_detallepedido.getValueAt(fila, 5).toString());
            String cod = getCodProducto();
            txt_label.setText(cod);
            cmb_notapedido.setEditable(false);
            cmb_notapedido.setEnabled(false);
            cmb_nombreproducto.setEditable(false);
            cmb_nombreproducto.setEnabled(false);
            cmb_variedad.setEditable(false);
            cmb_variedad.setEnabled(false);
            btn_agregar.setVisible(false);
            btn_modificar.setVisible(false);
            btn_eliminar.setVisible(false);
            btn_aceptar.setVisible(true);
            btn_cancelar.setVisible(true);
            btn_refrescar.setVisible(false);
            btn_agregarProducto.setVisible(false);
            btn_agregarNota.setVisible(false);
        }else{
            JOptionPane.showMessageDialog(null,"No selecciono fila");
        }
    }//GEN-LAST:event_btn_modificarActionPerformed

    private void btn_agregarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_agregarActionPerformed
        if (txt_cantidad.getText().equals("")){
            JOptionPane.showMessageDialog(null,"Debe ingresar cantidad");
        }else{
            if (txt_precio.getText().equals("")){
                JOptionPane.showMessageDialog(null,"Debe ingresar precio");
            }else{
                 if (Integer.parseInt(txt_precio.getText()) == 0){
                    JOptionPane.showMessageDialog(null,"Kilos inicial no puede ser 0.");
                 }else{
                        if (!verificarStock2()){
                            JOptionPane.showMessageDialog(null,"Stock de producto insuficiente para realizar el pedido.");
                        }else{
                            detallePedido dto = new detallePedido();
                            dto.setNro_nota(Integer.parseInt(cmb_notapedido.getSelectedItem().toString()));
                            dto.setCod_producto(Integer.parseInt(getCodProducto()));
                            dto.setCantidad(Integer.parseInt(txt_cantidad.getText()));
                            dto.setPrecio(Integer.parseInt(txt_precio.getText()));
                            //verificarStock3();
                            sql = "INSERT INTO detalle_pedido (nro_nota, cod_producto, cantidad, precio) VALUES (?,?,?,?)";
                            try {
                                PreparedStatement pst = reg.prepareStatement(sql);
                                pst.setInt(1, dto.getNro_nota());
                                pst.setInt(2, dto.getCod_producto());
                                pst.setInt(3, dto.getCantidad());
                                pst.setInt(4, dto.getPrecio());
                                int n = pst.executeUpdate();
                                if (n>0){
                                    JOptionPane.showMessageDialog(null,"Detalle envio registrado satisfactoriamente.");
                                }
                            } catch (SQLException ex) {
                                JOptionPane.showMessageDialog(null,"Error al agregar, codigos duplicado.");
                            }
                            txt_cantidad.setText("");
                            txt_label.setText("");
                            txt_precio.setText("");
                            limpiartabla();
                            mostrardatostabla("");
                        }
                 }

                    }
                }
        
    }//GEN-LAST:event_btn_agregarActionPerformed

    private void txt_cantidadKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_cantidadKeyTyped
        int limite = 10;
        if (txt_cantidad.getText().length() == limite) {
            evt.consume();
        }
        char t = evt.getKeyChar();
        if (Character.isLetter(t)) {
            evt.consume();
            JOptionPane.showMessageDialog(null, "Ingrese solo numeros");
        }
    }//GEN-LAST:event_txt_cantidadKeyTyped

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

    private void formWindowClosing(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosing
        // TODO add your handling code here:
        con.cerrar();
    }//GEN-LAST:event_formWindowClosing

    private void btn_refrescarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_refrescarActionPerformed
        limpiartabla();
        mostrardatostabla("");
    }//GEN-LAST:event_btn_refrescarActionPerformed

    private void cmb_notapedidoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmb_notapedidoActionPerformed

    }//GEN-LAST:event_cmb_notapedidoActionPerformed

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
            java.util.logging.Logger.getLogger(mantenedorDetallePedido.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(mantenedorDetallePedido.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(mantenedorDetallePedido.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(mantenedorDetallePedido.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new mantenedorDetallePedido().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btn_aceptar;
    private javax.swing.JButton btn_agregar;
    private javax.swing.JButton btn_agregarNota;
    private javax.swing.JButton btn_agregarProducto;
    private javax.swing.JButton btn_cancelar;
    private javax.swing.JButton btn_eliminar;
    private javax.swing.JButton btn_modificar;
    private javax.swing.JButton btn_refrescar;
    private javax.swing.JComboBox cmb_nombreproducto;
    private javax.swing.JComboBox cmb_notapedido;
    private javax.swing.JComboBox cmb_variedad;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lbl_codigo;
    private javax.swing.JTable tbl_detallepedido;
    private javax.swing.JTextField txt_cantidad;
    private javax.swing.JTextField txt_label;
    private javax.swing.JTextField txt_precio;
    // End of variables declaration//GEN-END:variables
}
