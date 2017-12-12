package Oso;

import java.sql.*;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author ijash
 */
public class Form_Supplier extends javax.swing.JFrame {

    koneksi conn=new koneksi();
    private Object [][] datasupplier=null;
    private String[]label={"Id Supplier","Nama Supplier"};
    /**
     * Creates new form Form_Supplier
     */
    public Form_Supplier() {
        initComponents();
        conn.setKoneksi();
        BacaTabelSupplier();
    }

    private void BacaTabelSupplier(){
        try{
            String sql="Select *From supplier order by id_supplier";
            conn.rs=conn.st.executeQuery(sql);
            ResultSetMetaData m=conn.rs.getMetaData();
            int kolom=m.getColumnCount();
            int baris=0;
            while(conn.rs.next()){
                baris=conn.rs.getRow();
            }
            datasupplier=new Object[baris][kolom];
            int x=0;
            conn.rs.beforeFirst();
            while(conn.rs.next()){
                datasupplier[x][0]=conn.rs.getString("id_supplier");
                datasupplier[x][1]=conn.rs.getString("nama_supplier");
                x++;
            }
            tabelSupplier.setModel(new DefaultTableModel(datasupplier,label));
        }
        catch(SQLException e){
            JOptionPane.showMessageDialog(null, e);
        }
    }

     private void BacaTabelSupplier2(){
        try{
            String sql="select *from supplier where nama_supplier like '%" +fieldCariSupplier.getText()+ "%' ";
            conn.rs=conn.st.executeQuery(sql);
            ResultSetMetaData m=conn.rs.getMetaData();
            int kolom=m.getColumnCount();
            int baris=0;
            while(conn.rs.next()){
                baris=conn.rs.getRow();
            }
            datasupplier=new Object[baris][kolom];
            int x=0;
            conn.rs.beforeFirst();
            while(conn.rs.next()){
                datasupplier[x][0]=conn.rs.getString("id_supplier");
                datasupplier[x][1]=conn.rs.getString("nama_supplier");
                x++;
            }
            tabelSupplier.setModel(new DefaultTableModel(datasupplier,label));
        }
        catch(SQLException e){
            JOptionPane.showMessageDialog(null, e);
        }
    }
          

        private void setTable(){
        int row=tabelSupplier.getSelectedRow();
        fieldKodeSupplier.setText((String)tabelSupplier.getValueAt(row,0));
        fieldNamaSupplier.setText((String)tabelSupplier.getValueAt(row,1));
        }
        
         private String kdsupp()
    {
        String no=null;
    try{
        String sql = "Select right(id_supplier,3)+1 from supplier ";
        ResultSet rs = conn.st.executeQuery(sql);
        if (rs.next()){
            rs.last();
            no = rs.getString(1);
            while (no.length()<3){
                no="00"+no;
                no="SP"+no;
                fieldKodeSupplier.setText(no);
            }
        }else{
            no="SP001";
            fieldKodeSupplier.setText(no);
        }
    }catch (Exception e){     
    }return no;
}
     private void BersihField(){
        fieldKodeSupplier.setText("");
        fieldNamaSupplier.setText("");
        }
       
      private void aktif(){
        fieldKodeSupplier.setEnabled(true);
        fieldNamaSupplier.setEnabled(true);
      }
      
       private void nonaktif(){
        fieldKodeSupplier.setEnabled(false);
        fieldNamaSupplier.setEnabled(false);
        buttonEdit.setEnabled(false);
        buttonUpdate.setEnabled(false);
        buttonHapus.setEnabled(false);
        buttonSimpan.setEnabled(false);
       }
       
        
     private void SimpanData(){
        try{
            String sql="insert into supplier values('"+fieldKodeSupplier.getText()+"','"+fieldNamaSupplier.getText()+"')";
            conn.st.executeUpdate(sql);
            JOptionPane.showMessageDialog(null,"Data berhasil disimpan");
            BersihField();
            BacaTabelSupplier();
        }
        catch(SQLException e){
            JOptionPane.showMessageDialog(null,e);
        }
    }

    private void EditData(){
        try{
            String sql="Update supplier set id_supplier='"+fieldKodeSupplier.getText()+"',nama_supplier='"+fieldNamaSupplier.getText()+"' where id_supplier='"+fieldKodeSupplier.getText()+"'";
            conn.st.executeUpdate(sql);
            JOptionPane.showMessageDialog(null,"Data berhasil diupdate");
            BersihField();
            BacaTabelSupplier();
            }
        catch(SQLException e){
            JOptionPane.showMessageDialog(null,e);
        }    
    }

        private void HapusData(){
        try{
            String sql="Delete from supplier where id_supplier='"+fieldKodeSupplier.getText()+"'";
            conn.st.executeUpdate(sql);
            JOptionPane.showMessageDialog(null,"Data berhasil dihapus");
            BersihField();
            BacaTabelSupplier();
        }
        catch(SQLException e){
            JOptionPane.showMessageDialog(null, e);
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

        panelUtama = new javax.swing.JPanel();
        panelInputDataSupplier = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        fieldKodeSupplier = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        fieldNamaSupplier = new javax.swing.JTextField();
        panelTabelDataSupplier = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        fieldCariSupplier = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        tabelSupplier = new javax.swing.JTable();
        buttonTambah = new javax.swing.JButton();
        buttonBatal = new javax.swing.JButton();
        buttonHapus = new javax.swing.JButton();
        buttonUpdate = new javax.swing.JButton();
        buttonSimpan = new javax.swing.JButton();
        buttonEdit = new javax.swing.JButton();
        buttonTutup = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowActivated(java.awt.event.WindowEvent evt) {
                formWindowActivated(evt);
            }
        });
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        panelInputDataSupplier.setBorder(javax.swing.BorderFactory.createTitledBorder("Input Data Supplier"));

        jLabel1.setText("Kode Supplier");

        jLabel2.setText("Nama Supplier");

        javax.swing.GroupLayout panelInputDataSupplierLayout = new javax.swing.GroupLayout(panelInputDataSupplier);
        panelInputDataSupplier.setLayout(panelInputDataSupplierLayout);
        panelInputDataSupplierLayout.setHorizontalGroup(
            panelInputDataSupplierLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelInputDataSupplierLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panelInputDataSupplierLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel1)
                    .addComponent(jLabel2))
                .addGap(61, 61, 61)
                .addGroup(panelInputDataSupplierLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(fieldKodeSupplier)
                    .addComponent(fieldNamaSupplier, javax.swing.GroupLayout.DEFAULT_SIZE, 244, Short.MAX_VALUE))
                .addContainerGap(109, Short.MAX_VALUE))
        );
        panelInputDataSupplierLayout.setVerticalGroup(
            panelInputDataSupplierLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelInputDataSupplierLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(panelInputDataSupplierLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(fieldKodeSupplier, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(panelInputDataSupplierLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel2)
                    .addComponent(fieldNamaSupplier, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
        );

        panelTabelDataSupplier.setBorder(javax.swing.BorderFactory.createTitledBorder("Tabel Data Supplier"));

        jLabel3.setText("Cari Supplier");

        fieldCariSupplier.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                fieldCariSupplierKeyTyped(evt);
            }
        });

        tabelSupplier.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        tabelSupplier.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tabelSupplierMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tabelSupplier);

        javax.swing.GroupLayout panelTabelDataSupplierLayout = new javax.swing.GroupLayout(panelTabelDataSupplier);
        panelTabelDataSupplier.setLayout(panelTabelDataSupplierLayout);
        panelTabelDataSupplierLayout.setHorizontalGroup(
            panelTabelDataSupplierLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelTabelDataSupplierLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panelTabelDataSupplierLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 892, Short.MAX_VALUE)
                    .addGroup(panelTabelDataSupplierLayout.createSequentialGroup()
                        .addComponent(jLabel3)
                        .addGap(18, 18, 18)
                        .addComponent(fieldCariSupplier, javax.swing.GroupLayout.PREFERRED_SIZE, 196, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        panelTabelDataSupplierLayout.setVerticalGroup(
            panelTabelDataSupplierLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelTabelDataSupplierLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(panelTabelDataSupplierLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(fieldCariSupplier, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 174, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(89, 89, 89))
        );

        buttonTambah.setText("Tambah");
        buttonTambah.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonTambahActionPerformed(evt);
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

        buttonUpdate.setText("Update");
        buttonUpdate.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonUpdateActionPerformed(evt);
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

        buttonTutup.setText("Tutup");
        buttonTutup.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonTutupActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout panelUtamaLayout = new javax.swing.GroupLayout(panelUtama);
        panelUtama.setLayout(panelUtamaLayout);
        panelUtamaLayout.setHorizontalGroup(
            panelUtamaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelUtamaLayout.createSequentialGroup()
                .addComponent(panelTabelDataSupplier, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
            .addGroup(panelUtamaLayout.createSequentialGroup()
                .addGroup(panelUtamaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelUtamaLayout.createSequentialGroup()
                        .addGap(173, 173, 173)
                        .addComponent(panelInputDataSupplier, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(panelUtamaLayout.createSequentialGroup()
                        .addGap(171, 171, 171)
                        .addComponent(buttonTambah)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(panelUtamaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(panelUtamaLayout.createSequentialGroup()
                                .addComponent(buttonSimpan, javax.swing.GroupLayout.PREFERRED_SIZE, 86, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(buttonEdit, javax.swing.GroupLayout.PREFERRED_SIZE, 86, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(buttonUpdate, javax.swing.GroupLayout.PREFERRED_SIZE, 86, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(buttonBatal, javax.swing.GroupLayout.PREFERRED_SIZE, 86, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(panelUtamaLayout.createSequentialGroup()
                                .addGap(6, 6, 6)
                                .addComponent(buttonTutup, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(buttonHapus, javax.swing.GroupLayout.PREFERRED_SIZE, 86, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        panelUtamaLayout.setVerticalGroup(
            panelUtamaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelUtamaLayout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addComponent(panelInputDataSupplier, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(panelTabelDataSupplier, javax.swing.GroupLayout.PREFERRED_SIZE, 235, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(panelUtamaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(buttonTambah)
                    .addComponent(buttonHapus)
                    .addComponent(buttonBatal)
                    .addComponent(buttonUpdate)
                    .addComponent(buttonEdit)
                    .addComponent(buttonSimpan))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(buttonTutup)
                .addGap(0, 11, Short.MAX_VALUE))
        );

        getContentPane().add(panelUtama, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 910, 430));

        setBounds(0, 0, 926, 467);
    }// </editor-fold>//GEN-END:initComponents

    private void formWindowActivated(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowActivated
        // TODO add your handling code here:
        BersihField();
        nonaktif();
    }//GEN-LAST:event_formWindowActivated

    private void buttonTutupActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonTutupActionPerformed
        // TODO add your handling code here:
        dispose();
    }//GEN-LAST:event_buttonTutupActionPerformed

    private void buttonTambahActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonTambahActionPerformed
        // TODO add your handling code here:
        aktif();
        BersihField();
        kdsupp();
        fieldKodeSupplier.setEnabled(true);
        fieldKodeSupplier.requestFocus();
        buttonBatal.setEnabled(true);
        buttonTambah.setEnabled(false);
        buttonSimpan.setEnabled(true);
    }//GEN-LAST:event_buttonTambahActionPerformed

    private void buttonBatalActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonBatalActionPerformed
        // TODO add your handling code here:
        nonaktif();
        BersihField();
        buttonTambah.setEnabled(true);
    }//GEN-LAST:event_buttonBatalActionPerformed

    private void buttonSimpanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonSimpanActionPerformed
        // TODO add your handling code here:
        if (fieldKodeSupplier.getText().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Lengkapi Data", "Konfirmasi", JOptionPane.INFORMATION_MESSAGE);
            buttonTambah.setEnabled(true);
        } else {
            buttonTambah.setEnabled(true);
            buttonTutup.setEnabled(true);
            SimpanData();
        }
    }//GEN-LAST:event_buttonSimpanActionPerformed

    private void fieldCariSupplierKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_fieldCariSupplierKeyTyped
        // TODO add your handling code here:
        BacaTabelSupplier2();
    }//GEN-LAST:event_fieldCariSupplierKeyTyped

    private void tabelSupplierMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tabelSupplierMouseClicked
        // TODO add your handling code here:
    setTable();
    buttonHapus.setEnabled(true);
    buttonEdit.setEnabled(true);
    buttonTambah.setEnabled(false);
    }//GEN-LAST:event_tabelSupplierMouseClicked

    private void buttonHapusActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonHapusActionPerformed
        // TODO add your handling code here:
        if (JOptionPane.showConfirmDialog(this, "yakin mau dihapus?", "connfirmasi", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
       HapusData();
       buttonTambah.setEnabled(true);
       } else {
       JOptionPane.showMessageDialog(this, "Data Batal Dihapus", "Konfirmasi", JOptionPane.INFORMATION_MESSAGE);
       buttonTambah.setEnabled(true);
       return;
       }
       formWindowActivated(null);
    }//GEN-LAST:event_buttonHapusActionPerformed

    private void buttonEditActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonEditActionPerformed
        // TODO add your handling code here:
        fieldNamaSupplier.setEnabled(true);
        buttonEdit.setEnabled(false);
        buttonUpdate.setEnabled(true);
        buttonBatal.setEnabled(true);
        buttonHapus.setEnabled(false);
        buttonTambah.setEnabled(false);
    }//GEN-LAST:event_buttonEditActionPerformed

    private void buttonUpdateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonUpdateActionPerformed
        // TODO add your handling code here:
        buttonUpdate.setEnabled(false);
        buttonTambah.setEnabled(true);
        EditData();
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
//            java.util.logging.Logger.getLogger(Form_Supplier.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        } catch (InstantiationException ex) {
//            java.util.logging.Logger.getLogger(Form_Supplier.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        } catch (IllegalAccessException ex) {
//            java.util.logging.Logger.getLogger(Form_Supplier.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
//            java.util.logging.Logger.getLogger(Form_Supplier.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        }
//        //</editor-fold>
//
//        /* Create and display the form */
//        java.awt.EventQueue.invokeLater(new Runnable() {
//            public void run() {
//                new Form_Supplier().setVisible(true);
//            }
//        });
//    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton buttonBatal;
    private javax.swing.JButton buttonEdit;
    private javax.swing.JButton buttonHapus;
    private javax.swing.JButton buttonSimpan;
    private javax.swing.JButton buttonTambah;
    private javax.swing.JButton buttonTutup;
    private javax.swing.JButton buttonUpdate;
    private javax.swing.JTextField fieldCariSupplier;
    private javax.swing.JTextField fieldKodeSupplier;
    private javax.swing.JTextField fieldNamaSupplier;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JPanel panelInputDataSupplier;
    private javax.swing.JPanel panelTabelDataSupplier;
    private javax.swing.JPanel panelUtama;
    private javax.swing.JTable tabelSupplier;
    // End of variables declaration//GEN-END:variables
}
