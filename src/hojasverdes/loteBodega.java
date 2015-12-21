/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hojasverdes;

import dominio.bodegaLote;
import dominio.camion;
import dominio.lote;
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

/**
 *
 * @author Fralkayg
 */
public class loteBodega extends javax.swing.JFrame {
    
    DefaultTableModel modelo = new DefaultTableModel();
    Calendar c2 = new GregorianCalendar();
    conectar cnx = new conectar();
    Connection reg= cnx.conexion();
    //private TableRowSorter trsfiltro;
    int columna=0;
    int sw = 0;
    int fila;
    String sql;

    /**
     * Creates new form loteBodega
     */
    public loteBodega() {
        initComponents();
        this.setLocationRelativeTo(null);
        this.setTitle("Lote Bodega");
        tbl_lote.setAutoCreateRowSorter(true);
        //ingresa las columnas de tu tabla productos
        modelo.addColumn("Lote");
        modelo.addColumn("Producto");
        modelo.addColumn("Variedad");
        modelo.addColumn("Calibre");
        modelo.addColumn("Bodega");
        modelo.addColumn("Kilos inicial");
        modelo.addColumn("Kilos final");
        modelo.addColumn("Elaboracion");
        modelo.addColumn("Ingreso");
        tbl_lote.setModel(modelo);
        cmb_elaboracion.setCalendar(c2);
        cmb_ingreso.setCalendar(c2);
        cmb_productoVariedad("");
        cmbBodega("");
        btn_aceptar.setVisible(false);
        btn_cancelar.setVisible(false);
        mostrardatostabla("");
    }
    
    void mostrardatostabla(String valor){    
        String []datos=new String[9];
        int cod;
        String sql="";
        if(valor.equals("")){
            sql="SELECT l.cod_lote, p.nom_producto, p.variedad, l.calibre, b.nom_bodega, l.kilos_inicial, l.kilos_final, l.fecha, lb.fecha_ingreso FROM lote l, lote_bodega lb, bodega b, producto p where p.cod_producto = l.cod_producto and l.cod_lote = lb.cod_lote and lb.cod_bodega = b.cod_bodega";
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
                datos[4]=rs.getString(5);
                datos[5]=rs.getString(6);
                datos[6]=rs.getString(7);
                datos[7]=rs.getString(8);
                datos[8]=rs.getString(9);
                modelo.addRow(datos);
            }
            tbl_lote.setModel(modelo);
            
        } catch (SQLException ex) {
            Logger.getLogger(producto.class.getName()).log(Level.SEVERE, null, ex);
        }   
    }
    
    public void limpiartabla(){
       // tabla.setModel(new DefaultTableModel());
        for (int i = 0; i < tbl_lote.getRowCount(); i++) {
           modelo.removeRow(i);
           i-=1;
       }
    }

    
    void cmb_productoVariedad(String valor){
        cmb_nombreProducto.removeAllItems();
        try{
            String sql="select nom_producto from producto";
            Statement st = reg.createStatement();
            ResultSet rs = st.executeQuery(sql);
            while (rs.next()){
                cmb_nombreProducto.addItem(rs.getString(1));
            }    
        }catch(Exception e){
            
        }
    }
    
    void cmb_variedad(String nombre){
        cmb_variedad.removeAllItems();
        try{
            String sql="select variedad from producto where nom_producto ='"+nombre+"'";
            Statement st = reg.createStatement();
            ResultSet rs = st.executeQuery(sql);
            while (rs.next()){
                cmb_variedad.addItem(rs.getString(1));
            }  
        }catch(Exception e){
            
        }
    }
    
    void cmbBodega(String valor){
        cmb_bodega.removeAllItems();
        try{
            String sql="select nom_bodega from bodega";
            Statement st = reg.createStatement();
            ResultSet rs = st.executeQuery(sql);
            while (rs.next()){
                String name = rs.getString(1);
                cmb_bodega.addItem(name);
            }    
        }catch(Exception e){
                
        }
    }
    
    public int getCodProducto(){
        int codigo=0;
        try{
            String sql="select cod_producto from producto where nom_producto ='"+cmb_nombreProducto.getSelectedItem()+"' and variedad='"+cmb_variedad.getSelectedItem()+"' ";
            Statement st = reg.createStatement();
            ResultSet rs = st.executeQuery(sql);
            while (rs.next()){
                codigo = Integer.parseInt(rs.getString(1));
            }   
        }catch(Exception e){
            
        }
        return codigo;
    }
    
    public int getCodBodega(){
        int codigo=0;
        try{
            String sql="select cod_bodega from bodega where nom_bodega ='"+cmb_bodega.getSelectedItem()+"' ";
            Statement st = reg.createStatement();
            ResultSet rs = st.executeQuery(sql);
            while (rs.next()){
                codigo = Integer.parseInt(rs.getString(1));
            }   
        }catch(Exception e){
            
        }
        return codigo;
    }
    
    public void eliminarLote(int fila){
        fila = tbl_lote.getSelectedRow();
        txt_lote.setText(tbl_lote.getValueAt(fila, 0).toString());
        try {
            PreparedStatement pst = reg.prepareStatement("DELETE FROM lote WHERE cod_lote="+Integer.parseInt(txt_lote.getText())+"");
            pst.executeUpdate();
            txt_lote.setText("");
            } catch (Exception e) {
               System.out.println(e.getMessage());
            }
    }
    
    public void eliminarLB(int fila){
        fila=tbl_lote.getSelectedRow();
        txt_lote.setText(tbl_lote.getValueAt(fila, 0).toString());
        try {
            PreparedStatement pst = reg.prepareStatement("DELETE FROM lote_bodega WHERE cod_lote="+Integer.parseInt(txt_lote.getText())+" and cod_bodega = "+getCodBodega()+"");
            pst.executeUpdate();
            txt_lote.setText("");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
    
    public void actualizarStock(){
        int stock = 0;
        int sw = 0;
        try{
            String sql="select stock_actual from producto where cod_producto ="+getCodProducto()+"";
            Statement st = reg.createStatement();
            ResultSet rs = st.executeQuery(sql);
            while (rs.next()){
                stock = (Integer.parseInt(rs.getString(1)));
            }
            stock = stock + Integer.parseInt(txt_kilosFinal.getText());
            String sql3 = "update producto set stock_actual ="+stock+" where cod_producto = "+getCodProducto()+"";
            PreparedStatement pst = reg.prepareStatement(sql3);
            pst.executeUpdate();
        }catch(Exception e){
            
        }
    }
    
    public void actualizarStock2(){
        
        int aux = Integer.parseInt(tbl_lote.getValueAt(fila, 6).toString());
        JOptionPane.showMessageDialog(null,aux);
        int stock = 0;
        int sw = 0;
        try{
            String sql="select stock_actual from producto where cod_producto ="+getCodProducto()+"";
            Statement st = reg.createStatement();
            ResultSet rs = st.executeQuery(sql);
            while (rs.next()){
                stock = (Integer.parseInt(rs.getString(1)));
            }
            //500
            //800
            if (aux > Integer.parseInt(txt_kilosFinal.getText())){
                stock = stock - (aux - Integer.parseInt(txt_kilosFinal.getText()));
            }else{
                stock = stock + (Integer.parseInt(txt_kilosFinal.getText()) - aux);
            }
            JOptionPane.showMessageDialog(null,stock);
            String sql3 = "update producto set stock_actual ="+stock+" where cod_producto = "+getCodProducto()+"";
            PreparedStatement pst = reg.prepareStatement(sql3);
            pst.executeUpdate();
        }catch(Exception e){
            
        }
    }
    
    public int verificarLote(){
        int codigo=0;
        try{
            String sql="select cod_lote from lote where cod_lote ="+Integer.parseInt(txt_lote.getText())+"";
            Statement st = reg.createStatement();
            ResultSet rs = st.executeQuery(sql);
            while (rs.next()){
                codigo = Integer.parseInt(rs.getString(1));
            }
        }catch(Exception e){
        }
        return codigo;
    }
    
    public int[] verificarLB(){
        int []datos=new int[2];
        int codigo=0;
        try{
            String sql="select cod_lote, cod_bodega from lote_bodega where cod_lote ="+Integer.parseInt(txt_lote.getText())+" and cod_bodega ="+getCodBodega()+"";
            Statement st = reg.createStatement();
            ResultSet rs = st.executeQuery(sql);
            while (rs.next()){
                datos[0] = Integer.parseInt(rs.getString(1));
                datos[1] = Integer.parseInt(rs.getString(2));
            }
        }catch(Exception e){
        }
        return datos;
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
        cmb_nombreProducto = new javax.swing.JComboBox();
        btn_agregarProducto = new javax.swing.JButton();
        txt_lote = new javax.swing.JTextField();
        txt_calibre = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        cmb_bodega = new javax.swing.JComboBox();
        btn_agregarBodega = new javax.swing.JButton();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        txt_kilosInicial = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        txt_kilosFinal = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        cmb_elaboracion = new com.toedter.calendar.JDateChooser();
        cmb_ingreso = new com.toedter.calendar.JDateChooser();
        cmb_variedad = new javax.swing.JComboBox();
        btn_eliminar = new javax.swing.JButton();
        btn_aceptar = new javax.swing.JButton();
        btn_modificar = new javax.swing.JButton();
        btn_cancelar = new javax.swing.JButton();
        btn_agregar = new javax.swing.JButton();
        btn_refrescar = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        tbl_lote = new javax.swing.JTable();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent evt) {
                formWindowClosing(evt);
            }
        });
        addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                formFocusGained(evt);
            }
        });

        jPanel1.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel1.setText("Lote:");
        jPanel1.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(12, 16, -1, -1));

        jLabel2.setText("Producto:");
        jPanel1.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(352, 16, -1, -1));

        cmb_nombreProducto.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmb_nombreProductoActionPerformed(evt);
            }
        });
        cmb_nombreProducto.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                cmb_nombreProductoFocusGained(evt);
            }
        });
        jPanel1.add(cmb_nombreProducto, new org.netbeans.lib.awtextra.AbsoluteConstraints(470, 13, 100, -1));

        btn_agregarProducto.setIcon(new javax.swing.ImageIcon(getClass().getResource("/iconos/add.png"))); // NOI18N
        btn_agregarProducto.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_agregarProductoActionPerformed(evt);
            }
        });
        jPanel1.add(btn_agregarProducto, new org.netbeans.lib.awtextra.AbsoluteConstraints(704, 13, 27, -1));

        txt_lote.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txt_loteKeyTyped(evt);
            }
        });
        jPanel1.add(txt_lote, new org.netbeans.lib.awtextra.AbsoluteConstraints(127, 13, 181, -1));

        txt_calibre.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txt_calibreKeyTyped(evt);
            }
        });
        jPanel1.add(txt_calibre, new org.netbeans.lib.awtextra.AbsoluteConstraints(127, 56, 181, -1));

        jLabel3.setText("Bodega");
        jPanel1.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(352, 59, -1, -1));

        cmb_bodega.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                cmb_bodegaFocusGained(evt);
            }
        });
        jPanel1.add(cmb_bodega, new org.netbeans.lib.awtextra.AbsoluteConstraints(470, 56, 198, -1));

        btn_agregarBodega.setIcon(new javax.swing.ImageIcon(getClass().getResource("/iconos/add.png"))); // NOI18N
        btn_agregarBodega.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_agregarBodegaActionPerformed(evt);
            }
        });
        jPanel1.add(btn_agregarBodega, new org.netbeans.lib.awtextra.AbsoluteConstraints(704, 56, 27, -1));

        jLabel4.setText("Calibre");
        jPanel1.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(12, 59, -1, -1));

        jLabel5.setText("Kilos inicial");
        jPanel1.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(12, 108, -1, -1));

        txt_kilosInicial.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txt_kilosInicialKeyTyped(evt);
            }
        });
        jPanel1.add(txt_kilosInicial, new org.netbeans.lib.awtextra.AbsoluteConstraints(127, 105, 181, -1));

        jLabel6.setText("Kilos final");
        jPanel1.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(352, 108, -1, -1));

        txt_kilosFinal.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txt_kilosFinalKeyTyped(evt);
            }
        });
        jPanel1.add(txt_kilosFinal, new org.netbeans.lib.awtextra.AbsoluteConstraints(470, 105, 198, -1));

        jLabel7.setText("Elaboracion:");
        jPanel1.add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(12, 160, -1, -1));

        jLabel8.setText("Ingreso bodega:");
        jPanel1.add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(352, 160, -1, -1));
        jPanel1.add(cmb_elaboracion, new org.netbeans.lib.awtextra.AbsoluteConstraints(127, 154, 181, -1));
        jPanel1.add(cmb_ingreso, new org.netbeans.lib.awtextra.AbsoluteConstraints(470, 154, 198, -1));

        jPanel1.add(cmb_variedad, new org.netbeans.lib.awtextra.AbsoluteConstraints(588, 13, 110, -1));

        btn_eliminar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/iconos/delete.png"))); // NOI18N
        btn_eliminar.setText("Eliminar");
        btn_eliminar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_eliminarActionPerformed(evt);
            }
        });
        jPanel1.add(btn_eliminar, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 200, -1, -1));

        btn_aceptar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/iconos/OK-20.png"))); // NOI18N
        btn_aceptar.setText("Aceptar");
        btn_aceptar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_aceptarActionPerformed(evt);
            }
        });
        jPanel1.add(btn_aceptar, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 200, -1, -1));

        btn_modificar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/iconos/update.png"))); // NOI18N
        btn_modificar.setText("Modificar");
        btn_modificar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_modificarActionPerformed(evt);
            }
        });
        jPanel1.add(btn_modificar, new org.netbeans.lib.awtextra.AbsoluteConstraints(290, 200, -1, -1));

        btn_cancelar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/iconos/delete.png"))); // NOI18N
        btn_cancelar.setText("Cancelar");
        btn_cancelar.setToolTipText("");
        btn_cancelar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_cancelarActionPerformed(evt);
            }
        });
        jPanel1.add(btn_cancelar, new org.netbeans.lib.awtextra.AbsoluteConstraints(410, 200, -1, -1));

        btn_agregar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/iconos/add.png"))); // NOI18N
        btn_agregar.setText("Agregar");
        btn_agregar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_agregarActionPerformed(evt);
            }
        });
        jPanel1.add(btn_agregar, new org.netbeans.lib.awtextra.AbsoluteConstraints(640, 200, -1, 30));

        btn_refrescar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/iconos/refresh-20.png"))); // NOI18N
        btn_refrescar.setText("Refrescar");
        btn_refrescar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_refrescarActionPerformed(evt);
            }
        });
        jPanel1.add(btn_refrescar, new org.netbeans.lib.awtextra.AbsoluteConstraints(520, 200, 110, 30));

        tbl_lote.setModel(new javax.swing.table.DefaultTableModel(
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
        jScrollPane1.setViewportView(tbl_lote);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, 739, Short.MAX_VALUE)
                    .addComponent(jScrollPane1))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 240, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 338, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void cmb_nombreProductoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmb_nombreProductoActionPerformed
        if (cmb_nombreProducto.getSelectedItem() ==null){
            
        }else{
            cmb_variedad(cmb_nombreProducto.getSelectedItem().toString());   
        }
    }//GEN-LAST:event_cmb_nombreProductoActionPerformed

    private void btn_agregarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_agregarActionPerformed
        if (txt_lote.getText().equals("")){
            JOptionPane.showMessageDialog(null,"Debe ingresar codigo lote.");
        }else{
            if (txt_calibre.getText().equals("")){
                JOptionPane.showMessageDialog(null,"Debe ingresar calibre.");
            }else{
                if(txt_kilosInicial.getText().equals("")){
                    JOptionPane.showMessageDialog(null,"Debe ingresar kilos inicial");
                }else{
                    if (txt_kilosFinal.getText().equals("")){
                        JOptionPane.showMessageDialog(null,"Debe ingresar kilos final.");
                    }else{
                        if (Integer.parseInt(txt_kilosInicial.getText()) < Integer.parseInt(txt_kilosFinal.getText())){
                            JOptionPane.showMessageDialog(null,"Kilos iniciales no puede ser inferior a kilos final");
                        }else{
                            int vLote = verificarLote();
                            if (vLote == Integer.parseInt(txt_lote.getText())){
                                JOptionPane.showMessageDialog(null,"Error, codigo lote repetido.");
                            }else{
                                int []vLB=new int[2];
                                vLB = verificarLB();
                                if (vLB[0] == Integer.parseInt(txt_lote.getText()) && vLB[1] == getCodBodega()){
                                    JOptionPane.showMessageDialog(null,"Error, codigos repetidos.");
                                }else{
                                    lote dto = new lote();
                                    dto.setCod_lote(Integer.parseInt(txt_lote.getText()));
                                    dto.setCod_producto(getCodProducto());
                                    dto.setCalibre(Integer.parseInt(txt_calibre.getText()));
                                    dto.setKilos_inicial(Integer.parseInt(txt_kilosInicial.getText()));
                                    dto.setKilos_final(Integer.parseInt(txt_kilosFinal.getText()));
                                    Date fecha = cmb_elaboracion.getDate();
                                    java.sql.Date sqlfecha = new java.sql.Date(fecha.getTime());
                                    dto.setFecha(sqlfecha);

                                    bodegaLote dto2 = new bodegaLote();
                                    dto2.setCod_bodega(getCodBodega());
                                    dto2.setCod_lote(Integer.parseInt(txt_lote.getText()));
                                    Date fecha2 = cmb_ingreso.getDate();
                                    java.sql.Date sqlfecha2 = new java.sql.Date(fecha.getTime());
                                    dto2.setFecha(sqlfecha2);
                                    sql = "INSERT INTO lote (cod_lote, cod_producto, calibre, fecha, kilos_inicial, kilos_final) VALUES (?,?,?,?,?,?)";
                                    try {
                                        PreparedStatement pst = reg.prepareStatement(sql);
                                        pst.setInt(1, dto.getCod_lote());
                                        pst.setInt(2, dto.getCod_producto());
                                        pst.setInt(3, dto.getCalibre());
                                        pst.setDate(4, dto.getFecha());
                                        pst.setInt(5, dto.getKilos_inicial());
                                        pst.setInt(6, dto.getKilos_final());
                                        actualizarStock();
                                        int n = pst.executeUpdate();
                                        if (n>0){
                                        }
                                    } catch (SQLException ex) {
                                        JOptionPane.showMessageDialog(null,"Error al agregar.");
                                    }
                                    sql= "INSERT INTO lote_bodega (cod_bodega, cod_lote, fecha_ingreso)VALUES (?,?,?)";
                                    try {
                                        PreparedStatement pst=reg.prepareStatement(sql);
                                        pst.setInt(1, dto2.getCod_bodega());
                                        pst.setInt(2, dto2.getCod_lote());
                                        pst.setDate(3, dto2.getFecha());
                                        int n = pst.executeUpdate();
                                        if (n>0){
                                        }                
                                    }catch (SQLException ex) {
                                        JOptionPane.showMessageDialog(null,"Error al agregar.");
                                        //sw = 1;
                                    }
                                }
                            }
                            
                        }
                    }
                }
            }
        }
                                    txt_lote.setText("");
                                    txt_calibre.setText("");
                                    txt_kilosInicial.setText("");
                                    txt_kilosFinal.setText("");
                                    cmb_elaboracion.setCalendar(c2);
                                    cmb_ingreso.setCalendar(c2);
                                    limpiartabla();
                                    mostrardatostabla("");
        
    }//GEN-LAST:event_btn_agregarActionPerformed

    private void btn_modificarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_modificarActionPerformed
        fila=tbl_lote.getSelectedRow();
        if (fila>=0){
            txt_lote.setText(tbl_lote.getValueAt(fila, 0).toString());
            cmb_nombreProducto.setSelectedItem(tbl_lote.getValueAt(fila, 1).toString());
            cmb_variedad.setSelectedItem(tbl_lote.getValueAt(fila, 2).toString());
            txt_calibre.setText(tbl_lote.getValueAt(fila, 3).toString());
            cmb_bodega.setSelectedItem(tbl_lote.getValueAt(fila, 4).toString());
            txt_kilosInicial.setText(tbl_lote.getValueAt(fila, 5).toString());
            txt_kilosFinal.setText(tbl_lote.getValueAt(fila, 6).toString());
            txt_lote.setEditable(false);
            txt_lote.setEnabled(false);
            cmb_bodega.setEnabled(false);
            btn_agregar.setVisible(false);
            btn_modificar.setVisible(false);
            btn_eliminar.setVisible(false);
            btn_agregarProducto.setVisible(false);
            btn_agregarBodega.setVisible(false);
            btn_refrescar.setVisible(false);
            btn_aceptar.setVisible(true);
            btn_cancelar.setVisible(true);
        }else{
            JOptionPane.showMessageDialog(null,"No selecciono fila");
        }
    }//GEN-LAST:event_btn_modificarActionPerformed

    private void btn_aceptarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_aceptarActionPerformed
        if (txt_lote.getText().equals("")){
            JOptionPane.showMessageDialog(null,"Debe ingresar codigo lote.");
        }else{
            if (txt_calibre.getText().equals("")){
                JOptionPane.showMessageDialog(null,"Debe ingresar calibre.");
            }else{
                if(txt_kilosInicial.getText().equals("")){
                    JOptionPane.showMessageDialog(null,"Debe ingresar kilos inicial");
                }else{
                    if (txt_kilosFinal.getText().equals("")){
                        JOptionPane.showMessageDialog(null,"Debe ingresar kilos final.");
                    }else{
                        if (Integer.parseInt(txt_kilosInicial.getText()) < Integer.parseInt(txt_kilosFinal.getText())){
                            JOptionPane.showMessageDialog(null,"Kilos iniciales no puede ser inferior a kilos final");
                        }else{
                            actualizarStock2();
                            Date fecha = cmb_elaboracion.getDate();
                            java.sql.Date sqlfecha = new java.sql.Date(fecha.getTime());
                            sql = "Update lote SET cod_producto="+getCodProducto()+",calibre="+Integer.parseInt(txt_calibre.getText())+",fecha='"+sqlfecha+"',kilos_inicial="+Integer.parseInt(txt_kilosInicial.getText())+",kilos_final="+Integer.parseInt(txt_kilosFinal.getText())+" WHERE cod_lote="+Integer.parseInt(txt_lote.getText())+"";
                            try {
                                PreparedStatement pst = reg.prepareStatement(sql);
                                pst.executeUpdate();
                                limpiartabla();
                                mostrardatostabla("");
                            } catch (Exception e) {
                                System.out.println(e.getMessage());
                                JOptionPane.showMessageDialog(null,e.getMessage());
                            }

                            Date fecha2 = cmb_ingreso.getDate();
                            java.sql.Date sqlfecha2 = new java.sql.Date(fecha2.getTime());
                            sql = "update lote_bodega set fecha_ingreso='"+sqlfecha2+"' where cod_bodega ="+getCodBodega()+" and cod_lote="+Integer.parseInt(txt_lote.getText())+" ";
                            try{
                                PreparedStatement pst = reg.prepareStatement(sql);
                                pst.executeUpdate();
                                limpiartabla();
                                mostrardatostabla("");
                            }catch (Exception e){
                                System.out.println(e.getMessage());
                                JOptionPane.showMessageDialog(null, e.getMessage());
                            }
                            int fila=tbl_lote.getSelectedRow();
                            btn_aceptar.setVisible(false);
                            btn_cancelar.setVisible(false);
                            btn_eliminar.setVisible(true);
                            btn_modificar.setVisible(true);
                            btn_agregar.setVisible(true);
                            cmb_bodega.setEnabled(true);
                            txt_lote.setEnabled(true);
                            txt_lote.setEditable(true);
                            btn_agregarProducto.setVisible(true);
                            btn_agregarBodega.setVisible(true);
                            btn_refrescar.setVisible(true);
                            txt_lote.requestFocus();
                            txt_lote.setText("");
                            txt_calibre.setText("");
                            txt_kilosInicial.setText("");
                            txt_kilosFinal.setText("");
                            limpiartabla();
                            mostrardatostabla("");
                        }
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
        txt_lote.setEnabled(true);
        txt_lote.setEditable(true);
        btn_agregarProducto.setVisible(true);
        btn_agregarBodega.setVisible(true);
        btn_refrescar.setVisible(true);
        txt_lote.requestFocus();
        txt_kilosInicial.setText("");
        txt_kilosFinal.setText("");
        txt_calibre.setText("");
        txt_lote.setText("");
        cmb_bodega.setEnabled(true);
    }//GEN-LAST:event_btn_cancelarActionPerformed

    private void btn_eliminarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_eliminarActionPerformed
        fila = tbl_lote.getSelectedRow();
        if (fila >= 0){   
            if(JOptionPane.showConfirmDialog(null, new Object[]{"Seguro que desea Eliminar fila seleccionada?"},"Eliminar",JOptionPane.OK_CANCEL_OPTION)==JOptionPane.YES_OPTION){
            //qui se pone lo que hara si le das aceptar
                txt_kilosFinal.setText("0");
                actualizarStock2();
                eliminarLB(fila);
                eliminarLote(fila);
                limpiartabla();
                mostrardatostabla("");
                txt_kilosFinal.setText("");
                /*fila=tbl_lote.getSelectedRow();
                txt_lote.setText(tbl_lote.getValueAt(fila, 0).toString());
                try {
                    PreparedStatement pst = reg.prepareStatement("DELETE FROM lote_bodega WHERE cod_lote="+Integer.parseInt(txt_lote.getText())+" and cod_bodega = "+getCodBodega()+"");
                    pst.executeUpdate();
                    limpiartabla();
                    mostrardatostabla("");
                    txt_lote.setText("");
                } catch (Exception e) {
                    System.out.println(e.getMessage());
                }
                
                txt_lote.setText(tbl_lote.getValueAt(fila, 0).toString());
                try {
                    PreparedStatement pst = reg.prepareStatement("DELETE FROM lote WHERE cod_lote="+Integer.parseInt(txt_lote.getText())+"");
                    pst.executeUpdate();
                    limpiartabla();
                    mostrardatostabla("");
                    txt_lote.setText("");
                } catch (Exception e) {
                    System.out.println(e.getMessage());
                }*/


            }else{
            //aqui se pone lo que hara si le das cancelar
            }
        }else{
            JOptionPane.showMessageDialog(null,"Debe seleccionar una fila antes de eliminar.");
        }
    }//GEN-LAST:event_btn_eliminarActionPerformed

    private void btn_agregarProductoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_agregarProductoActionPerformed
        mantenedorProductos abrirP = new mantenedorProductos();
        abrirP.setVisible(true);
    }//GEN-LAST:event_btn_agregarProductoActionPerformed

    private void btn_agregarBodegaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_agregarBodegaActionPerformed
        mantenedorBodegas abrirB = new mantenedorBodegas();
        abrirB.setVisible(true);
    }//GEN-LAST:event_btn_agregarBodegaActionPerformed

    private void txt_loteKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_loteKeyTyped
        int limite = 10;
        if (txt_lote.getText().length() == limite) {
            evt.consume();
        }
        char t = evt.getKeyChar();
        if (Character.isLetter(t)) {
            evt.consume();
            JOptionPane.showMessageDialog(null, "Ingrese solo numeros");
        }  
    }//GEN-LAST:event_txt_loteKeyTyped

    private void txt_calibreKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_calibreKeyTyped
        int limite = 1;
        if (txt_calibre.getText().length() == limite) {
            evt.consume();
        }
        char t = evt.getKeyChar();
        if (Character.isLetter(t)) {
            evt.consume();
            JOptionPane.showMessageDialog(null, "Ingrese solo numeros");
        }  
    }//GEN-LAST:event_txt_calibreKeyTyped

    private void txt_kilosInicialKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_kilosInicialKeyTyped
        int limite = 10;
        if (txt_kilosInicial.getText().length() == limite) {
            evt.consume();
        }
        char t = evt.getKeyChar();
        if (Character.isLetter(t)) {
            evt.consume();
            JOptionPane.showMessageDialog(null, "Ingrese solo numeros");
        }  
    }//GEN-LAST:event_txt_kilosInicialKeyTyped

    private void txt_kilosFinalKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_kilosFinalKeyTyped
        int limite = 10;
        if (txt_kilosFinal.getText().length() == limite) {
            evt.consume();
        }
        char t = evt.getKeyChar();
        if (Character.isLetter(t)) {
            evt.consume();
            JOptionPane.showMessageDialog(null, "Ingrese solo numeros");
        }  
    }//GEN-LAST:event_txt_kilosFinalKeyTyped

    private void formWindowClosing(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosing
        // TODO add your handling code here:
        cnx.cerrar();
    }//GEN-LAST:event_formWindowClosing

    private void formFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_formFocusGained

    }//GEN-LAST:event_formFocusGained

    private void cmb_bodegaFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_cmb_bodegaFocusGained

    }//GEN-LAST:event_cmb_bodegaFocusGained

    private void cmb_nombreProductoFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_cmb_nombreProductoFocusGained
        
    }//GEN-LAST:event_cmb_nombreProductoFocusGained

    private void btn_refrescarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_refrescarActionPerformed
        cmb_elaboracion.setCalendar(c2);
        cmb_ingreso.setCalendar(c2);
        cmb_productoVariedad("");
        cmbBodega("");
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
            java.util.logging.Logger.getLogger(loteBodega.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(loteBodega.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(loteBodega.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(loteBodega.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new loteBodega().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btn_aceptar;
    private javax.swing.JButton btn_agregar;
    private javax.swing.JButton btn_agregarBodega;
    private javax.swing.JButton btn_agregarProducto;
    private javax.swing.JButton btn_cancelar;
    private javax.swing.JButton btn_eliminar;
    private javax.swing.JButton btn_modificar;
    private javax.swing.JButton btn_refrescar;
    private javax.swing.JComboBox cmb_bodega;
    private com.toedter.calendar.JDateChooser cmb_elaboracion;
    private com.toedter.calendar.JDateChooser cmb_ingreso;
    private javax.swing.JComboBox cmb_nombreProducto;
    private javax.swing.JComboBox cmb_variedad;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable tbl_lote;
    private javax.swing.JTextField txt_calibre;
    private javax.swing.JTextField txt_kilosFinal;
    private javax.swing.JTextField txt_kilosInicial;
    private javax.swing.JTextField txt_lote;
    // End of variables declaration//GEN-END:variables
}
