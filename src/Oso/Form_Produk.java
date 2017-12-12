package Oso;

import java.awt.event.KeyEvent;
import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import sun.misc.Cleaner;

/**
 *
 * @author jose
 */
public class Form_Produk extends javax.swing.JFrame {
koneksi conn = new koneksi();

private Object [][] dataProduk=null;
private String [] label={"Id Produk","Id Kategori","Nama Produk","Unit Cost","Id Supplier"};


    public Form_Produk() {
        initComponents();
        conn.setKoneksi();
        nonaktif();
        BacaTabelProduk();
        isiNamaKategori();
        isiNamaSupplier();
        fieldKategori.setVisible(false);
        fieldSupplier.setVisible(false);
    }

    void isiNamaKategori(){
        try{
           String sql="Select *From kategori";
            conn.rs=conn.st.executeQuery(sql);
            while (conn.rs.next()){
                comboBoxKategori.addItem(conn.rs.getString("nama_kategori"));
           }
        }catch(SQLException e){
            System.out.println("Koneksi Gagal"+ e.toString());
        }
    }
   
     void isiNamaSupplier(){
       try{
           String sql="Select *From supplier ";
            conn.rs=conn.st.executeQuery(sql);
            while (conn.rs.next()){
                comboBoxSupplier.addItem(conn.rs.getString("nama_supplier"));
            }
          }catch(SQLException e){
            System.out.println("Koneksi Gagal"+ e.toString());
        }
    }
     
    private String idProduk()
    {
        String no=null;
    try{
        conn.setKoneksi();
        String sql = "Select right(id_produk,3)+1 from produk ";
        ResultSet rs = conn.st.executeQuery(sql);
        if (rs.next()){
            rs.last();
            no = rs.getString(1);
            while (no.length()<3){
                no="00"+no;
                no="B"+no;
            fieldProduk.setText(no);    
            }
        }else{
            no="B001";
            fieldProduk.setText(no);    
        }
    }catch (Exception e){     
    }return no;
    }
    
   private void BacaTabelProduk(){
   try{
            String sql="Select *From produk order by id_produk";
            conn.rs=conn.st.executeQuery(sql);
            ResultSetMetaData m=conn.rs.getMetaData();
            int kolom=m.getColumnCount();
            int baris=0;
            while(conn.rs.next()){
                baris=conn.rs.getRow();
            }
            dataProduk=new Object[baris][kolom];
            int x=0;
            conn.rs.beforeFirst();
            while(conn.rs.next()){
                dataProduk[x][0]=conn.rs.getString("id_produk");
                dataProduk[x][1]=conn.rs.getString("id_kategori");
                dataProduk[x][2]=conn.rs.getString("nama_produk");
                dataProduk[x][3]=conn.rs.getString("unitcost");
                dataProduk[x][4]=conn.rs.getString("id_supplier");
                x++;
            }
            tabelProduk.setModel(new DefaultTableModel(dataProduk,label));
        }
        catch(SQLException e){
            JOptionPane.showMessageDialog(null, e);
        }
    }

     private void BacaTabelProduk2(){
        try{
            String sql="select *from produk where nama_produk like '%" +fieldCariProduk.getText()+ "%' ";
            conn.rs=conn.st.executeQuery(sql);
            ResultSetMetaData m=conn.rs.getMetaData();
            int kolom=m.getColumnCount();
            int baris=0;
            while(conn.rs.next()){
                baris=conn.rs.getRow();
            }
             dataProduk=new Object[baris][kolom];
            int x=0;
            conn.rs.beforeFirst();
            while(conn.rs.next()){
                dataProduk[x][0]=conn.rs.getString("id_produk");
                dataProduk[x][1]=conn.rs.getString("id_kategori");
                dataProduk[x][2]=conn.rs.getString("nama_produk");
                dataProduk[x][3]=conn.rs.getString("unitcost");
                dataProduk[x][4]=conn.rs.getString("id_supplier");
                 x++;
            }
            tabelProduk.setModel(new DefaultTableModel(dataProduk,label));
        }
        catch(SQLException e){
            JOptionPane.showMessageDialog(null, e);
        }
    }
      
     void isiNamaKategori2(){
        try{
            conn.setKoneksi();
             String sql="Select *From kategori where  id_kategori='"+fieldKategori.getText()+"'";
            conn.rs=conn.st.executeQuery(sql);
            if (conn.rs.next()){
                 comboBoxKategori.setSelectedItem(conn.rs.getString("nama_kategori"));
             }
        }catch(SQLException e){
            System.out.println("Koneksi Gagal"+ e.toString());
        }
    }
     
      void isiNamaSupplier2(){
              try{
           String sql="Select *From supplier where id_supplier='"+fieldSupplier.getText()+"'";
            conn.rs=conn.st.executeQuery(sql);
            if(conn.rs.next()){
                comboBoxSupplier.setSelectedItem(conn.rs.getString("nama_supplier"));
            }
        }catch(SQLException e){
            System.out.println("Koneksi Gagal"+ e.toString());
        }
    }
       
     private void setTable(){
        int row=tabelProduk.getSelectedRow();
        fieldProduk.setText((String)tabelProduk.getValueAt(row,0));
        fieldKategori.setText((String)tabelProduk.getValueAt(row,1));
        fieldNamaProduk.setText((String)tabelProduk.getValueAt(row,2));
        fieldUnitCost.setText((String)tabelProduk.getValueAt(row,3));
        fieldSupplier.setText((String)tabelProduk.getValueAt(row,4));
        }
        
     private void BersihField(){
        fieldUnitCost.setText("");
        fieldProduk.setText("");
        fieldNamaProduk.setText("");
        fieldNamaProduk.setText("");
        fieldCariProduk.setText("");
        }
       
      private void aktif(){
        fieldProduk.setEnabled(true);
        fieldNamaProduk.setEnabled(true);
        fieldUnitCost.setEnabled(true);
        comboBoxKategori.setEnabled(true);
        comboBoxSupplier.setEnabled(true);
      }
      
       private void nonaktif(){
        fieldProduk.setEnabled(false);
        fieldNamaProduk.setEnabled(false);
        fieldUnitCost.setEnabled(false);
        comboBoxKategori.setEnabled(false);
        comboBoxSupplier.setEnabled(false);
        buttonEdit.setEnabled(false);
        buttonUpdate.setEnabled(false);
        buttonHapus.setEnabled(false);
        buttonSimpan.setEnabled(false);
       }
       
        
     private void SimpanData(){
        try{
            String sql="insert into produk values('"+fieldProduk.getText()+"','"+fieldKategori.getText()+"','"+fieldNamaProduk.getText()+"','"+fieldUnitCost.getText()+"','"+fieldSupplier.getText()+"')";
            conn.st.executeUpdate(sql);
            JOptionPane.showMessageDialog(null,"Data berhasil disimpan");
            BersihField();
            BacaTabelProduk();
         }
        catch(SQLException e){
            JOptionPane.showMessageDialog(null,e);
        }
    }
     
     private void EditData(){
        try{
            String sql="Update produk set id_produk='"+fieldProduk.getText()+"',id_kategori='"+fieldKategori.getText()+"',id_supplier='"+fieldSupplier.getText()+"',nama_produk='"+fieldNamaProduk.getText()+"',unitcost='"+fieldUnitCost.getText()+"' where id_produk='"+fieldProduk.getText()+"'";
            conn.st.executeUpdate(sql);
            JOptionPane.showMessageDialog(null,"Data berhasil diupdate");
            BersihField();
            BacaTabelProduk();
            conn.st.close();
            }
        catch(SQLException e){
            JOptionPane.showMessageDialog(null,e);
        }    
    }

        private void HapusData(){
        try{
            String sql="Delete from produk where id_produk='"+fieldProduk.getText()+"'";
            conn.st.executeUpdate(sql);
            JOptionPane.showMessageDialog(null,"Data berhasil dihapus");
            BersihField();
            BacaTabelProduk();
            conn.st.close();
        }
        catch(SQLException e){
            JOptionPane.showMessageDialog(null, e);
        }
    }
    
    /**
     * This method is called from within the connstructor to initialize the form.
     * WARNING: Do NOT modify this code. The conntent of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        panelUtama = new javax.swing.JPanel();
        panelInput = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        fieldProduk = new javax.swing.JTextField();
        comboBoxKategori = new javax.swing.JComboBox<>();
        comboBoxSupplier = new javax.swing.JComboBox<>();
        fieldNamaProduk = new javax.swing.JTextField();
        fieldUnitCost = new javax.swing.JTextField();
        fieldKategori = new javax.swing.JTextField();
        fieldSupplier = new javax.swing.JTextField();
        buttonTutup = new javax.swing.JButton();
        buttonTambah = new javax.swing.JButton();
        buttonSimpan = new javax.swing.JButton();
        buttonEdit = new javax.swing.JButton();
        buttonUpdate = new javax.swing.JButton();
        buttonBatal = new javax.swing.JButton();
        buttonHapus = new javax.swing.JButton();
        panelTabel = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tabelProduk = new javax.swing.JTable();
        jLabel6 = new javax.swing.JLabel();
        fieldCariProduk = new javax.swing.JTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowActivated(java.awt.event.WindowEvent evt) {
                formWindowActivated(evt);
            }
        });
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        panelInput.setBorder(javax.swing.BorderFactory.createTitledBorder("Input Data Produk"));

        jLabel1.setText("Id Produk");

        jLabel2.setText("Kategori");

        jLabel3.setText("Supplier");

        jLabel4.setText("Nama Produk");

        jLabel5.setText("Unit Cost");

        comboBoxKategori.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "=PILIH=" }));
        comboBoxKategori.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                comboBoxKategoriActionPerformed(evt);
            }
        });

        comboBoxSupplier.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "=PILIH=" }));
        comboBoxSupplier.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                comboBoxSupplierActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout panelInputLayout = new javax.swing.GroupLayout(panelInput);
        panelInput.setLayout(panelInputLayout);
        panelInputLayout.setHorizontalGroup(
            panelInputLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelInputLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panelInputLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel1)
                    .addComponent(jLabel2)
                    .addComponent(jLabel3)
                    .addComponent(jLabel5)
                    .addComponent(jLabel4))
                .addGap(37, 37, 37)
                .addGroup(panelInputLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(fieldNamaProduk, javax.swing.GroupLayout.PREFERRED_SIZE, 211, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(fieldProduk, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(panelInputLayout.createSequentialGroup()
                        .addGroup(panelInputLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(comboBoxSupplier, 0, 149, Short.MAX_VALUE)
                            .addComponent(comboBoxKategori, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(panelInputLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(fieldKategori)
                            .addComponent(fieldSupplier, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 108, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addComponent(fieldUnitCost, javax.swing.GroupLayout.PREFERRED_SIZE, 89, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(70, Short.MAX_VALUE))
        );
        panelInputLayout.setVerticalGroup(
            panelInputLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelInputLayout.createSequentialGroup()
                .addGap(10, 10, 10)
                .addGroup(panelInputLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(fieldProduk, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(panelInputLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelInputLayout.createSequentialGroup()
                        .addGroup(panelInputLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(comboBoxKategori, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel2))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(panelInputLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(comboBoxSupplier, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel3))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(panelInputLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(fieldNamaProduk, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel4))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(panelInputLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(fieldUnitCost, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel5))
                        .addGap(29, 29, 29))
                    .addGroup(panelInputLayout.createSequentialGroup()
                        .addComponent(fieldKategori, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(fieldSupplier, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
        );

        buttonTutup.setText("Tutup");
        buttonTutup.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonTutupActionPerformed(evt);
            }
        });

        buttonTambah.setText("Tambah");
        buttonTambah.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonTambahActionPerformed(evt);
            }
        });

        buttonSimpan.setText("Simpan");
        buttonSimpan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonSimpanActionPerformed(evt);
            }
        });

        buttonEdit.setText("Edit");
        buttonEdit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonEditActionPerformed(evt);
            }
        });

        buttonUpdate.setText("Update");
        buttonUpdate.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonUpdateActionPerformed(evt);
            }
        });

        buttonBatal.setText("Batal");
        buttonBatal.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonBatalActionPerformed(evt);
            }
        });

        buttonHapus.setText("Hapus");
        buttonHapus.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonHapusActionPerformed(evt);
            }
        });

        panelTabel.setBorder(javax.swing.BorderFactory.createTitledBorder("Tabel Data Produk"));

        tabelProduk.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        tabelProduk.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tabelProdukMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tabelProduk);

        jLabel6.setText("Cari Produk");

        fieldCariProduk.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                fieldCariProdukKeyTyped(evt);
            }
        });

        javax.swing.GroupLayout panelTabelLayout = new javax.swing.GroupLayout(panelTabel);
        panelTabel.setLayout(panelTabelLayout);
        panelTabelLayout.setHorizontalGroup(
            panelTabelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelTabelLayout.createSequentialGroup()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 554, Short.MAX_VALUE)
                .addContainerGap())
            .addGroup(panelTabelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel6)
                .addGap(18, 18, 18)
                .addComponent(fieldCariProduk, javax.swing.GroupLayout.PREFERRED_SIZE, 93, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        panelTabelLayout.setVerticalGroup(
            panelTabelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelTabelLayout.createSequentialGroup()
                .addContainerGap(10, Short.MAX_VALUE)
                .addGroup(panelTabelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(fieldCariProduk, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel6))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 159, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        javax.swing.GroupLayout panelUtamaLayout = new javax.swing.GroupLayout(panelUtama);
        panelUtama.setLayout(panelUtamaLayout);
        panelUtamaLayout.setHorizontalGroup(
            panelUtamaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelUtamaLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panelUtamaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelUtamaLayout.createSequentialGroup()
                        .addComponent(panelInput, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(buttonTutup))
                    .addGroup(panelUtamaLayout.createSequentialGroup()
                        .addComponent(buttonTambah)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(buttonSimpan)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(buttonEdit)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(buttonUpdate)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(buttonBatal)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(buttonHapus))
                    .addComponent(panelTabel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(54, Short.MAX_VALUE))
        );
        panelUtamaLayout.setVerticalGroup(
            panelUtamaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelUtamaLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panelUtamaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(buttonTutup, javax.swing.GroupLayout.PREFERRED_SIZE, 178, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(panelInput, javax.swing.GroupLayout.PREFERRED_SIZE, 187, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(panelUtamaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(buttonTambah)
                    .addComponent(buttonSimpan)
                    .addComponent(buttonEdit)
                    .addComponent(buttonUpdate)
                    .addComponent(buttonBatal)
                    .addComponent(buttonHapus))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(panelTabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        getContentPane().add(panelUtama, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 630, 480));

        setBounds(0, 0, 643, 510);
    }// </editor-fold>//GEN-END:initComponents

    private void formWindowActivated(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowActivated
        BersihField();
        nonaktif();
    }//GEN-LAST:event_formWindowActivated

    private void buttonTutupActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonTutupActionPerformed
        
        dispose();
    }//GEN-LAST:event_buttonTutupActionPerformed

    private void buttonTambahActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonTambahActionPerformed
        
        idProduk();
        aktif();
        buttonBatal.setEnabled(true);
        buttonTambah.setEnabled(false);
        buttonSimpan.setEnabled(true);
    }//GEN-LAST:event_buttonTambahActionPerformed

    private void comboBoxKategoriActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_comboBoxKategoriActionPerformed
        
        try{
            conn.setKoneksi();
           String sql="Select *From kategori where nama_kategori='"+comboBoxKategori.getSelectedItem()+"'";
            conn.rs=conn.st.executeQuery(sql);
            System.out.println(sql);
                     
           if (conn.rs.next()){
                fieldKategori.setText(conn.rs.getString("id_kategori"));
               }
           else{
               System.out.println("Gagal");
           }
           }catch(SQLException e){
            System.out.println("Koneksi Gagal"+ e.toString());
        }
    
    }//GEN-LAST:event_comboBoxKategoriActionPerformed

    private void comboBoxSupplierActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_comboBoxSupplierActionPerformed
        
        try{
          conn.setKoneksi();
          String sql="Select *From supplier where nama_supplier='"+comboBoxSupplier.getSelectedItem()+"'";
            conn.rs=conn.st.executeQuery(sql);
            if (conn.rs.next()){
                fieldSupplier.setText(conn.rs.getString("id_supplier"));  
          }
        }catch(SQLException e){
            System.out.println("Koneksi Gagal"+ e.toString());
        } 
    }//GEN-LAST:event_comboBoxSupplierActionPerformed

    private void buttonBatalActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonBatalActionPerformed
        
        nonaktif();
        BersihField();
        buttonTambah.setEnabled(true);
        comboBoxKategori.setSelectedItem("=PILIH=");
        comboBoxSupplier.setSelectedItem("=PILIH=");
        try {
            conn.st.close();
        } catch (SQLException ex) {
            Logger.getLogger(Form_Produk.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_buttonBatalActionPerformed

    private void buttonSimpanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonSimpanActionPerformed
        
        if (fieldProduk.getText().isEmpty() || fieldNamaProduk.getText().isEmpty() || fieldUnitCost.getText().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Lengkapi Data", "Konfirmasi", JOptionPane.INFORMATION_MESSAGE);
            buttonTambah.setEnabled(true);
        } else {
            buttonTambah.setEnabled(true);
            buttonTutup.setEnabled(true);
            SimpanData();
            comboBoxKategori.setSelectedItem("=PILIH=");
            comboBoxSupplier.setSelectedItem("=PILIH=");
              try {
            conn.st.close();
        } catch (SQLException ex) {
            Logger.getLogger(Form_Produk.class.getName()).log(Level.SEVERE, null, ex);
        }
           }
    }//GEN-LAST:event_buttonSimpanActionPerformed

    private void fieldCariProdukKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_fieldCariProdukKeyTyped
        
     conn.setKoneksi();
     BacaTabelProduk2();
    }//GEN-LAST:event_fieldCariProdukKeyTyped

    private void tabelProdukMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tabelProdukMouseClicked
        
    setTable();
    isiNamaKategori2();
    isiNamaSupplier2();  
    buttonHapus.setEnabled(true);
    buttonEdit.setEnabled(true);
    buttonTambah.setEnabled(false);
    }//GEN-LAST:event_tabelProdukMouseClicked

    private void buttonHapusActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonHapusActionPerformed
        
         if (JOptionPane.showConfirmDialog(this, "yakin mau dihapus?", "connfirmasi", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
            HapusData();
            buttonTambah.setEnabled(true);
            nonaktif();
            BersihField();
             comboBoxKategori.setSelectedItem("=PILIH=");
             comboBoxSupplier.setSelectedItem("=PILIH=");
       } else {

            JOptionPane.showMessageDialog(this, "Data Batal Dihapus", "Konfirmasi", JOptionPane.INFORMATION_MESSAGE);
            buttonTambah.setEnabled(true);
             nonaktif();
            BersihField();
             comboBoxKategori.setSelectedItem("=PILIH=");
             comboBoxSupplier.setSelectedItem("=PILIH=");
     
            return;
        }
        formWindowActivated(null);
    }//GEN-LAST:event_buttonHapusActionPerformed

    private void buttonEditActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonEditActionPerformed
        
      aktif();
     fieldProduk.setEnabled(false);
     buttonEdit.setEnabled(false);
     buttonUpdate.setEnabled(true);
     buttonBatal.setEnabled(true);
     buttonHapus.setEnabled(false);
     buttonTambah.setEnabled(false);
    }//GEN-LAST:event_buttonEditActionPerformed

    private void buttonUpdateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonUpdateActionPerformed
        
    buttonUpdate.setEnabled(false);
    buttonTambah.setEnabled(true);
    EditData();
    comboBoxKategori.setSelectedItem("=PILIH=");
    comboBoxSupplier.setSelectedItem("=PILIH="); 
    }//GEN-LAST:event_buttonUpdateActionPerformed

    /**
     * @param args the command line arguments
     */
//    public static void main(String args[]) {
//        /* Set the Nimbus look and feel */
//        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
//        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
//         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
//         */
//        try {
//            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
//                if ("Nimbus".equals(info.getName())) {
//                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
//                    break;
//                }
//            }
//        } catch (ClassNotFoundException ex) {
//            java.util.logging.Logger.getLogger(Form_Produk.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        } catch (InstantiationException ex) {
//            java.util.logging.Logger.getLogger(Form_Produk.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        } catch (IllegalAccessException ex) {
//            java.util.logging.Logger.getLogger(Form_Produk.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
//            java.util.logging.Logger.getLogger(Form_Produk.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        }
//        //</editor-fold>
//
//        /* Create and display the form */
//        java.awt.EventQueue.invokeLater(new Runnable() {
//            public void run() {
//                new Form_Produk().setVisible(true);
//            }
//        });
//    }
    
    public static void guiStart() {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                System.out.println(info.getName());
                if ("Nimbus".equals(info.getName())) {
                    
                   // javax.swing.UIManager.setLookAndFeel("com.sun.java.swing.plaf.gtk.GTKLookAndFeel");
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Form_Produk.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Form_Produk.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Form_Produk.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Form_Produk.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Form_Produk().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton buttonBatal;
    private javax.swing.JButton buttonEdit;
    private javax.swing.JButton buttonHapus;
    private javax.swing.JButton buttonSimpan;
    private javax.swing.JButton buttonTambah;
    private javax.swing.JButton buttonTutup;
    private javax.swing.JButton buttonUpdate;
    private javax.swing.JComboBox<String> comboBoxKategori;
    private javax.swing.JComboBox<String> comboBoxSupplier;
    private javax.swing.JTextField fieldCariProduk;
    private javax.swing.JTextField fieldKategori;
    private javax.swing.JTextField fieldNamaProduk;
    private javax.swing.JTextField fieldProduk;
    private javax.swing.JTextField fieldSupplier;
    private javax.swing.JTextField fieldUnitCost;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JPanel panelInput;
    private javax.swing.JPanel panelTabel;
    private javax.swing.JPanel panelUtama;
    private javax.swing.JTable tabelProduk;
    // End of variables declaration//GEN-END:variables
}
