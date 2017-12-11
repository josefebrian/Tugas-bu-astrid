package Oso;

import java.sql.*;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import javax.swing.table.TableColumn;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author ijash
 */
public class Form_Pesanan extends javax.swing.JFrame {
    koneksi conn=new koneksi();
     private Object [][] datasementara=null;
     private String[]labelsementara={"Id Produk","Jumlah"};
    /**
     * Creates new form Form_Pesanan
     */
    public Form_Pesanan() {
        initComponents();
        fieldProduk.setEnabled(false);
        setTanggal();
        conn.setKoneksi();
        nonaktif();
    }

    public String KodeProduk;

    public String getKodeProduk() {
        return KodeProduk;
    }
    
    void setTanggal(){
    java.util.Date skrg = new java.util.Date();
    java.text.SimpleDateFormat kal = new java.text.SimpleDateFormat("yyyy-MM-dd");
    fieldTanggal.setText(kal.format(skrg));
    }

      public String getnoPS()
    {
        String urutan=null;
        try{
conn.rs=conn.st.executeQuery("select right(id_pesanan,3)+1 from pesanan as Nomor order by id_pesanan desc");
            if(conn.rs.next())
            {
                urutan=conn.rs.getString(1);
                while(urutan.length()<3)
                    urutan="0"+urutan;
                urutan="PS-"+urutan;
            }else
            {
                urutan="PS-"+"001";
            }
        }catch(Exception e){
            JOptionPane.showMessageDialog(null,"No PS Error : "+e);
        }
         return urutan;
    }

   
   private void BacaTabelSementara(){
        try{
            String sql="Select *From sementara order by id_produk";
            conn.rs=conn.st.executeQuery(sql);
            ResultSetMetaData m=conn.rs.getMetaData();
            int kolom=m.getColumnCount();
            int baris=0;
            while(conn.rs.next()){
                baris=conn.rs.getRow();
            }
            datasementara=new Object[baris][kolom];
            int x=0;
            conn.rs.beforeFirst();
            while(conn.rs.next()){
                datasementara[x][0]=conn.rs.getString("id_produk");
                datasementara[x][1]=conn.rs.getString("qty");
                x++;
            }
            tabelPesanan.setModel(new DefaultTableModel(datasementara,labelsementara));
        }
        catch(SQLException e){
            JOptionPane.showMessageDialog(null, e);
        }
    }
      

private void SimpanSementara(){
        try{
            String sql="insert into sementara values('"+fieldProduk.getText()+"','"+fieldJumlah.getText()+"')";
            conn.st.executeUpdate(sql);
            BacaTabelSementara();
        }
        catch(SQLException e){
            JOptionPane.showMessageDialog(null,e);
        }
    }
         

private void HapusSementara(){
              int row=tabelPesanan.getSelectedRow();
        try{
            String sql="Delete from sementara where id_produk='"+(String)tabelPesanan.getValueAt(row,0)+"'";
            conn.st.executeUpdate(sql);
            BacaTabelSementara();
        }
        catch(SQLException e){
            JOptionPane.showMessageDialog(null, e);
        }
    }
      
private void hapusSementara2(){
           try{
            String sql="Delete from sementara ";
            conn.st.executeUpdate(sql);
            BacaTabelSementara();
        }
        catch(SQLException e){
            JOptionPane.showMessageDialog(null, e);
        }    
      }   
          
void simpanPesanan(){
        try{
            String sql = "insert into pesanan values ('"+fieldPesanan.getText() +"','"+fieldTanggal.getText()+"')";
            conn.st.executeUpdate(sql);
            
            String detail = "insert item_pesanan select '" +fieldPesanan.getText() +"',id_produk,qty from sementara ";
            conn.st.executeUpdate(detail);
            
            
            JOptionPane.showMessageDialog(this,"Berhasil disimpan","Informasi", JOptionPane.INFORMATION_MESSAGE);
        }catch(SQLException e){
            System.out.println("conneksi gagal"+ e.toString());
        }
          }
         
private void BersihField(){
       fieldProduk.setText("");
       fieldJumlah.setText("");
        }
       
      private void aktif(){
        fieldProduk.setEnabled(true);
        fieldJumlah.setEnabled(true);
        
      }
      
       private void nonaktif(){
        fieldTanggal.setEnabled(false);
        fieldPesanan.setEnabled(false);
        fieldProduk.setEnabled(false);
        fieldJumlah.setEnabled(false);
        buttonSimpan.setEnabled(false);
        
       }
    
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel2 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        fieldTanggal = new javax.swing.JTextField();
        jPanel1 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        fieldPesanan = new javax.swing.JTextField();
        fieldProduk = new javax.swing.JTextField();
        fieldJumlah = new javax.swing.JTextField();
        buttonKurang = new javax.swing.JToggleButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        tabelPesanan = new javax.swing.JTable();
        buttonBrowse = new javax.swing.JToggleButton();
        jPanel3 = new javax.swing.JPanel();
        buttonSimpan = new javax.swing.JButton();
        buttonBatal = new javax.swing.JButton();
        butrtonTutup = new javax.swing.JButton();
        buttonTambah = new javax.swing.JButton();

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 100, Short.MAX_VALUE)
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 100, Short.MAX_VALUE)
        );

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel1.setFont(new java.awt.Font("Dialog", 1, 48)); // NOI18N
        jLabel1.setText("FORM PESANAN");

        jLabel2.setText("Tanggal");

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder("Input Pesanan"));

        jLabel3.setText("Id Pesanan");

        jLabel4.setText("Id Produk");

        jLabel5.setText("Jumlah");

        fieldJumlah.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                fieldJumlahActionPerformed(evt);
            }
        });

        buttonKurang.setText("-");
        buttonKurang.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonKurangActionPerformed(evt);
            }
        });

        tabelPesanan.setModel(new javax.swing.table.DefaultTableModel(
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
        jScrollPane1.setViewportView(tabelPesanan);

        buttonBrowse.setText("...");
        buttonBrowse.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonBrowseActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel1Layout.createSequentialGroup()
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jLabel5)
                                .addComponent(jLabel4))
                            .addGap(24, 24, 24)
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(fieldProduk, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 141, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(fieldJumlah, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 141, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                            .addComponent(jLabel3)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                            .addComponent(fieldPesanan, javax.swing.GroupLayout.PREFERRED_SIZE, 141, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(buttonKurang)))
                .addContainerGap(40, Short.MAX_VALUE))
            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel1Layout.createSequentialGroup()
                    .addGap(266, 266, 266)
                    .addComponent(buttonBrowse, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGap(227, 227, 227)))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(33, 33, 33)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(fieldPesanan, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(fieldProduk, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel4))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(fieldJumlah, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel5))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 108, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(buttonKurang, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(18, Short.MAX_VALUE))
            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel1Layout.createSequentialGroup()
                    .addGap(45, 45, 45)
                    .addComponent(buttonBrowse, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(192, Short.MAX_VALUE)))
        );

        jPanel3.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));

        buttonSimpan.setText("Simpan");
        buttonSimpan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonSimpanActionPerformed(evt);
            }
        });

        buttonBatal.setText("Batal");
        buttonBatal.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonBatalActionPerformed(evt);
            }
        });

        butrtonTutup.setText("Tutup");
        butrtonTutup.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                butrtonTutupActionPerformed(evt);
            }
        });

        buttonTambah.setText("Tambah");
        buttonTambah.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonTambahActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(buttonTambah, javax.swing.GroupLayout.PREFERRED_SIZE, 118, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(buttonSimpan, javax.swing.GroupLayout.PREFERRED_SIZE, 102, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(buttonBatal, javax.swing.GroupLayout.PREFERRED_SIZE, 99, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(butrtonTutup, javax.swing.GroupLayout.PREFERRED_SIZE, 174, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(17, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(buttonBatal)
                    .addComponent(buttonTambah)
                    .addComponent(buttonSimpan)
                    .addComponent(butrtonTutup))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(115, 115, 115)
                        .addComponent(jLabel1))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(70, 70, 70)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel2)
                                .addGap(18, 18, 18)
                                .addComponent(fieldTanggal, javax.swing.GroupLayout.PREFERRED_SIZE, 141, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(6, 6, 6)
                                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                .addContainerGap(31, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(23, 23, 23)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(fieldTanggal, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(60, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void buttonKurangActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonKurangActionPerformed
        // TODO add your handling code here:
        HapusSementara();
    }//GEN-LAST:event_buttonKurangActionPerformed

    private void buttonBrowseActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonBrowseActionPerformed
        // TODO add your handling code here:
        boolean closable = true;
        DataProduk dataProduk = new DataProduk(null, closable);
        dataProduk.pesanan = this;
        dataProduk.setVisible(true);
        dataProduk.setResizable(true);
        fieldProduk.setText(KodeProduk);
        fieldPesanan.setEnabled(false); 
    }//GEN-LAST:event_buttonBrowseActionPerformed

    private void buttonTambahActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonTambahActionPerformed
        // TODO add your handling code here:
        aktif();
        buttonBatal.setEnabled(true);
        buttonSimpan.setEnabled(true);
        buttonTambah.setEnabled(false);
        fieldProduk.setEnabled(true);
        fieldProduk.requestFocus();
        fieldPesanan.setText(getnoPS());
    }//GEN-LAST:event_buttonTambahActionPerformed

    private void fieldJumlahActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_fieldJumlahActionPerformed
        // TODO add your handling code here:
        try{
       String sql="select *from sementara where id_produk='" +fieldProduk.getText()+ "'";
       conn.rs=conn.st.executeQuery(sql);
       if(conn.rs.next())
      {
        JOptionPane.showMessageDialog(this,"Kode Sudah Dipilih...","Informasi", JOptionPane.INFORMATION_MESSAGE);
        buttonBrowse.requestFocus();
        fieldProduk.setText("");
      }
       else
       {
         SimpanSementara();
        if (JOptionPane.showConfirmDialog(this, "Mau Tambah Barang?", "connfirmasi", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
            fieldProduk.requestFocus();
           fieldProduk.setText("");
           fieldJumlah.setText("");
        } else {
            BersihField();
            buttonSimpan.requestFocus();
            return;
        }
       
       }
     }
        catch(SQLException e){
            JOptionPane.showMessageDialog(null, e);
        }
    }//GEN-LAST:event_fieldJumlahActionPerformed

    private void buttonBatalActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonBatalActionPerformed
        // TODO add your handling code here:
        hapusSementara2();
        buttonSimpan.setEnabled(false);
        buttonTambah.setEnabled(true);
        fieldPesanan.setText("");
        BersihField();
        nonaktif();
    }//GEN-LAST:event_buttonBatalActionPerformed

    private void buttonSimpanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonSimpanActionPerformed
        // TODO add your handling code here:
        simpanPesanan();
        hapusSementara2();
        buttonSimpan.setEnabled(false);
        buttonTambah.setEnabled(true);
        BersihField();
        fieldPesanan.setText("");
        nonaktif();
    }//GEN-LAST:event_buttonSimpanActionPerformed

    private void butrtonTutupActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_butrtonTutupActionPerformed
        // TODO add your handling code here:
        dispose();
    }//GEN-LAST:event_butrtonTutupActionPerformed

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
            java.util.logging.Logger.getLogger(Form_Pesanan.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Form_Pesanan.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Form_Pesanan.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Form_Pesanan.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Form_Pesanan().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton butrtonTutup;
    private javax.swing.JButton buttonBatal;
    private javax.swing.JToggleButton buttonBrowse;
    private javax.swing.JToggleButton buttonKurang;
    private javax.swing.JButton buttonSimpan;
    private javax.swing.JButton buttonTambah;
    private javax.swing.JTextField fieldJumlah;
    private javax.swing.JTextField fieldPesanan;
    private javax.swing.JTextField fieldProduk;
    private javax.swing.JTextField fieldTanggal;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable tabelPesanan;
    // End of variables declaration//GEN-END:variables
}
