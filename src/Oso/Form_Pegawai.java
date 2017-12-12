package Oso;

import java.sql.*;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author jose
 */
public class Form_Pegawai extends javax.swing.JFrame {

    koneksi conn = new koneksi();
    private Object [][] datapegawai=null;
    private String[]label={"Id Pegawai","Username","Password","Departemen","Manajer"};
    /**
     * Creates new form Form_Pegawai
     */
    public Form_Pegawai() {
        initComponents();
        conn.setKoneksi();
        BacaTabelPegawai();
    }

    private String idPegawai()
{
String no=null;
    try{
        String sql = "Select right(id_pegawai,3)+1 from pegawai ";
        ResultSet rs = conn.st.executeQuery(sql);
        if (rs.next()){
            rs.last();
            no = rs.getString(1);
            while (no.length()<3){
                no="00"+no;
                no="P"+no;
            fieldIdPegawai.setText(no);    
            }
        }else{
            no="P001";
            fieldIdPegawai.setText(no);    
        }
    }catch (Exception e){     
    }return no;
}
    
private void BacaTabelPegawai(){
try{
    String sql="Select *From pegawai order by id_pegawai";
    conn.rs=conn.st.executeQuery(sql);
    ResultSetMetaData m=conn.rs.getMetaData();
    int kolom=m.getColumnCount();
    int baris=0;
    while(conn.rs.next()){
        baris=conn.rs.getRow();
        }
        datapegawai=new Object[baris][kolom];
        int x=0;
        conn.rs.beforeFirst();
        while(conn.rs.next()){
        datapegawai[x][0]=conn.rs.getString("id_pegawai");
        datapegawai[x][1]=conn.rs.getString("username");
        datapegawai[x][2]=conn.rs.getString("password");
        datapegawai[x][3]=conn.rs.getString("departemen");
        datapegawai[x][4]=conn.rs.getString("manajer");
        x++;
        }
        tabelPegawai.setModel(new DefaultTableModel(datapegawai,label));
        }
        catch(SQLException e){
            JOptionPane.showMessageDialog(null, e);
        }
    }

private void BacaTabelPegawai2(){
        try{
            String sql="select *from pegawai where username like '%" +fieldCariPegawai.getText()+ "%' ";
            conn.rs=conn.st.executeQuery(sql);
            ResultSetMetaData m=conn.rs.getMetaData();
            int kolom=m.getColumnCount();
            int baris=0;
            while(conn.rs.next()){
                baris=conn.rs.getRow();
            }
             datapegawai=new Object[baris][kolom];
            int x=0;
            conn.rs.beforeFirst();
            while(conn.rs.next()){
               datapegawai[x][0]=conn.rs.getString("id_pegawai");
                datapegawai[x][1]=conn.rs.getString("username");
                datapegawai[x][2]=conn.rs.getString("password");
                datapegawai[x][3]=conn.rs.getString("departemen");
                datapegawai[x][4]=conn.rs.getString("manajer");
                 x++;
            }
            tabelPegawai.setModel(new DefaultTableModel(datapegawai,label));
        }
        catch(SQLException e){
            JOptionPane.showMessageDialog(null, e);
        }
    }
          
        private void setTable(){
        int row=tabelPegawai.getSelectedRow();
        fieldIdPegawai.setText((String)tabelPegawai.getValueAt(row,0));
        fieldUsername.setText((String)tabelPegawai.getValueAt(row,1));
        fieldPassword.setText((String)tabelPegawai.getValueAt(row,2));
        fieldDepartment.setText((String)tabelPegawai.getValueAt(row,3));
        fieldManajer.setText((String)tabelPegawai.getValueAt(row,4));
        }
        
     private void BersihField(){
        fieldIdPegawai.setText("");
        fieldUsername.setText("");
        fieldDepartment.setText("");
        fieldPassword.setText("");
        fieldManajer.setText("");
        fieldCariPegawai.setText("");
        }
       
      private void aktif(){
        fieldIdPegawai.setEnabled(true);
        fieldUsername.setEnabled(true);
        fieldDepartment.setEnabled(true);
        fieldPassword.setEnabled(true);
        fieldManajer.setEnabled(true);
      }
      
       private void nonaktif(){
        fieldIdPegawai.setEnabled(false);
        fieldUsername.setEnabled(false);
        fieldDepartment.setEnabled(false);
        fieldManajer.setEnabled(false);
        fieldPassword.setEnabled(false);
        buttonEdit.setEnabled(false);
        buttonUpdate.setEnabled(false);
        buttonHapus.setEnabled(false);
        buttonSimpan.setEnabled(false);
       }
       
        
     private void SimpanData(){
        try{
            String sql="insert into pegawai values('"+fieldIdPegawai.getText()+"','"+fieldUsername.getText()+"','"+fieldPassword.getText()+"','"+fieldDepartment.getText()+"','"+fieldManajer.getText()+"')";
            conn.st.executeUpdate(sql);
            JOptionPane.showMessageDialog(null,"Data berhasil disimpan");
            BersihField();
            BacaTabelPegawai();
        }
        catch(SQLException e){
            JOptionPane.showMessageDialog(null,e);
        }
    }

    private void EditData(){
       try{
         String sql="Update pegawai set id_pegawai='"+fieldIdPegawai.getText()+"',username='"+fieldUsername.getText()+"',password='"+fieldPassword.getText()+"',departemen='"+fieldDepartment.getText()+"',manajer='"+fieldManajer.getText()+"' where id_pegawai='"+fieldIdPegawai.getText()+"'";
            conn.st.executeUpdate(sql);
            JOptionPane.showMessageDialog(null,"Data berhasil diupdate");
            BersihField();
            BacaTabelPegawai();
            }
        catch(SQLException e){
            JOptionPane.showMessageDialog(null,e);
        }    
    }

   private void HapusData(){
        try{
            String sql="Delete from pegawai where id_pegawai='"+fieldIdPegawai.getText()+"'";
            conn.st.executeUpdate(sql);
            JOptionPane.showMessageDialog(null,"Data berhasil dihapus");
            BersihField();
            BacaTabelPegawai();
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
        panelInputDataPegawai = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        fieldIdPegawai = new javax.swing.JTextField();
        fieldUsername = new javax.swing.JTextField();
        fieldPassword = new javax.swing.JTextField();
        fieldDepartment = new javax.swing.JTextField();
        fieldManajer = new javax.swing.JTextField();
        panelTabelDataPegawai = new javax.swing.JPanel();
        jLabel6 = new javax.swing.JLabel();
        fieldCariPegawai = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        tabelPegawai = new javax.swing.JTable();
        buttonTambah = new javax.swing.JButton();
        buttonUpdate = new javax.swing.JButton();
        buttonSimpan = new javax.swing.JButton();
        buttonBatal = new javax.swing.JButton();
        buttonEdit = new javax.swing.JButton();
        buttonHapus = new javax.swing.JButton();
        buttonTutup = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowActivated(java.awt.event.WindowEvent evt) {
                formWindowActivated(evt);
            }
        });
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        panelInputDataPegawai.setBorder(javax.swing.BorderFactory.createTitledBorder("Input Data Pegawai"));

        jLabel1.setText("Id Pegawai");

        jLabel2.setText("Username");

        jLabel3.setText("Password");

        jLabel4.setText("Departemen");

        jLabel5.setText("Manajer");

        javax.swing.GroupLayout panelInputDataPegawaiLayout = new javax.swing.GroupLayout(panelInputDataPegawai);
        panelInputDataPegawai.setLayout(panelInputDataPegawaiLayout);
        panelInputDataPegawaiLayout.setHorizontalGroup(
            panelInputDataPegawaiLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelInputDataPegawaiLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panelInputDataPegawaiLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(panelInputDataPegawaiLayout.createSequentialGroup()
                        .addComponent(jLabel5)
                        .addGap(116, 116, 116)
                        .addComponent(fieldManajer))
                    .addGroup(panelInputDataPegawaiLayout.createSequentialGroup()
                        .addComponent(jLabel4)
                        .addGap(86, 86, 86)
                        .addComponent(fieldDepartment, javax.swing.GroupLayout.PREFERRED_SIZE, 195, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(panelInputDataPegawaiLayout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addGap(96, 96, 96)
                        .addComponent(fieldIdPegawai, javax.swing.GroupLayout.PREFERRED_SIZE, 118, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(panelInputDataPegawaiLayout.createSequentialGroup()
                        .addComponent(jLabel2)
                        .addGap(102, 102, 102)
                        .addComponent(fieldUsername, javax.swing.GroupLayout.PREFERRED_SIZE, 195, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(panelInputDataPegawaiLayout.createSequentialGroup()
                        .addComponent(jLabel3)
                        .addGap(104, 104, 104)
                        .addComponent(fieldPassword, javax.swing.GroupLayout.PREFERRED_SIZE, 195, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(93, Short.MAX_VALUE))
        );
        panelInputDataPegawaiLayout.setVerticalGroup(
            panelInputDataPegawaiLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelInputDataPegawaiLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panelInputDataPegawaiLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(fieldIdPegawai, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(panelInputDataPegawaiLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(fieldUsername, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(panelInputDataPegawaiLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(fieldPassword, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(panelInputDataPegawaiLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(fieldDepartment, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(panelInputDataPegawaiLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(fieldManajer, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(18, Short.MAX_VALUE))
        );

        panelTabelDataPegawai.setBorder(javax.swing.BorderFactory.createTitledBorder("Tabel Data Pegawai"));

        jLabel6.setText("Cari Pegawai");

        fieldCariPegawai.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                fieldCariPegawaiKeyTyped(evt);
            }
        });

        tabelPegawai.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        tabelPegawai.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tabelPegawaiMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tabelPegawai);

        javax.swing.GroupLayout panelTabelDataPegawaiLayout = new javax.swing.GroupLayout(panelTabelDataPegawai);
        panelTabelDataPegawai.setLayout(panelTabelDataPegawaiLayout);
        panelTabelDataPegawaiLayout.setHorizontalGroup(
            panelTabelDataPegawaiLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelTabelDataPegawaiLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panelTabelDataPegawaiLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelTabelDataPegawaiLayout.createSequentialGroup()
                        .addComponent(jLabel6)
                        .addGap(28, 28, 28)
                        .addComponent(fieldCariPegawai, javax.swing.GroupLayout.PREFERRED_SIZE, 206, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 757, Short.MAX_VALUE))
                .addContainerGap())
        );
        panelTabelDataPegawaiLayout.setVerticalGroup(
            panelTabelDataPegawaiLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelTabelDataPegawaiLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panelTabelDataPegawaiLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(fieldCariPegawai, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel6))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 86, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        buttonTambah.setText("Tambah");
        buttonTambah.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonTambahActionPerformed(evt);
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

        buttonBatal.setText("Batal");
        buttonBatal.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonBatalActionPerformed(evt);
            }
        });

        buttonEdit.setText("Edit");
        buttonEdit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonEditActionPerformed(evt);
            }
        });

        buttonHapus.setText("Hapus");
        buttonHapus.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonHapusActionPerformed(evt);
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
                .addGroup(panelUtamaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelUtamaLayout.createSequentialGroup()
                        .addGap(141, 141, 141)
                        .addComponent(panelInputDataPegawai, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(panelUtamaLayout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(panelTabelDataPegawai, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(panelUtamaLayout.createSequentialGroup()
                        .addGap(215, 215, 215)
                        .addGroup(panelUtamaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(buttonUpdate, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(buttonTambah, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGroup(panelUtamaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(panelUtamaLayout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(buttonSimpan))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelUtamaLayout.createSequentialGroup()
                                .addGap(2, 2, 2)
                                .addComponent(buttonBatal, javax.swing.GroupLayout.PREFERRED_SIZE, 86, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(panelUtamaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(buttonHapus)
                            .addComponent(buttonEdit, javax.swing.GroupLayout.PREFERRED_SIZE, 76, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(buttonTutup)))
                .addContainerGap(15, Short.MAX_VALUE))
        );
        panelUtamaLayout.setVerticalGroup(
            panelUtamaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelUtamaLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(panelInputDataPegawai, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(panelTabelDataPegawai, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(panelUtamaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelUtamaLayout.createSequentialGroup()
                        .addGroup(panelUtamaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(buttonTambah)
                            .addComponent(buttonSimpan))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(panelUtamaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(buttonUpdate)
                            .addComponent(buttonBatal)))
                    .addGroup(panelUtamaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addGroup(panelUtamaLayout.createSequentialGroup()
                            .addComponent(buttonEdit)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(buttonHapus))
                        .addComponent(buttonTutup, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addContainerGap(52, Short.MAX_VALUE))
        );

        getContentPane().add(panelUtama, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 800, 490));

        setBounds(0, 0, 808, 517);
    }// </editor-fold>//GEN-END:initComponents

    private void formWindowActivated(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowActivated
        
        BersihField();
        nonaktif();
    }//GEN-LAST:event_formWindowActivated

    private void buttonTutupActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonTutupActionPerformed
        
        dispose();
    }//GEN-LAST:event_buttonTutupActionPerformed

    private void buttonTambahActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonTambahActionPerformed
        
        BersihField();
    idPegawai();
    aktif();
    fieldIdPegawai.setEnabled(true);
    fieldIdPegawai.requestFocus();
    buttonBatal.setEnabled(true);
    buttonTambah.setEnabled(false);
    buttonSimpan.setEnabled(true);   
    }//GEN-LAST:event_buttonTambahActionPerformed

    private void buttonBatalActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonBatalActionPerformed
        
        nonaktif();
        BersihField();
        buttonTambah.setEnabled(true);
    }//GEN-LAST:event_buttonBatalActionPerformed

    private void buttonSimpanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonSimpanActionPerformed
        
        if (fieldIdPegawai.getText().isEmpty() || fieldPassword.getText().isEmpty() || fieldUsername.getText().isEmpty() || fieldDepartment.getText().isEmpty()) {
        JOptionPane.showMessageDialog(this, "Lengkapi Data", "Konfirmasi", JOptionPane.INFORMATION_MESSAGE);
        buttonTambah.setEnabled(true);
      } else {
        buttonTambah.setEnabled(true);
        buttonTutup.setEnabled(true);
        SimpanData();
      }
    }//GEN-LAST:event_buttonSimpanActionPerformed

    private void fieldCariPegawaiKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_fieldCariPegawaiKeyTyped
        
        BacaTabelPegawai2();
    }//GEN-LAST:event_fieldCariPegawaiKeyTyped

    private void tabelPegawaiMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tabelPegawaiMouseClicked
        
    setTable();
    buttonHapus.setEnabled(true);
    buttonEdit.setEnabled(true);
    buttonTambah.setEnabled(false);
    }//GEN-LAST:event_tabelPegawaiMouseClicked

    private void buttonHapusActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonHapusActionPerformed
        
        if (JOptionPane.showConfirmDialog(this, "yakin mau dihapus?", "konfirmasi", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
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
        
        aktif();
        fieldIdPegawai.setEnabled(false);
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
//            java.util.logging.Logger.getLogger(Form_Pegawai.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        } catch (InstantiationException ex) {
//            java.util.logging.Logger.getLogger(Form_Pegawai.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        } catch (IllegalAccessException ex) {
//            java.util.logging.Logger.getLogger(Form_Pegawai.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
//            java.util.logging.Logger.getLogger(Form_Pegawai.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        }
//        //</editor-fold>
//
//        /* Create and display the form */
//        java.awt.EventQueue.invokeLater(new Runnable() {
//            public void run() {
//                new Form_Pegawai().setVisible(true);
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
    private javax.swing.JTextField fieldCariPegawai;
    private javax.swing.JTextField fieldDepartment;
    private javax.swing.JTextField fieldIdPegawai;
    private javax.swing.JTextField fieldManajer;
    private javax.swing.JTextField fieldPassword;
    private javax.swing.JTextField fieldUsername;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JPanel panelInputDataPegawai;
    private javax.swing.JPanel panelTabelDataPegawai;
    private javax.swing.JPanel panelUtama;
    private javax.swing.JTable tabelPegawai;
    // End of variables declaration//GEN-END:variables
}
