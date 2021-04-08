/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package isa.client;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.Socket;
import javax.swing.JOptionPane;

/**
 *
 * @author User
 */
public class FormMenu extends javax.swing.JFrame {

    Socket socket;

    BufferedReader in;
    DataOutputStream out;

    String userLogin;

    public FormMenu() {
        initComponents();
    }

    public FormMenu(Socket socket, String userLogin) {
        initComponents();

        this.socket = socket;
        this.userLogin = userLogin;
        
        try {
            in = new BufferedReader(new InputStreamReader(this.socket.getInputStream()));
            out = new DataOutputStream(this.socket.getOutputStream());
        } catch (Exception e) {
            System.out.println("Error Constructor : " + e);
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        btnMenuTransfer = new javax.swing.JButton();
        btnMenuInfoSaldo = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel1.setText("MENU");

        btnMenuTransfer.setText("TRANSFER");
        btnMenuTransfer.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnMenuTransferActionPerformed(evt);
            }
        });

        btnMenuInfoSaldo.setText("INFO SALDO");
        btnMenuInfoSaldo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnMenuInfoSaldoActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(44, 44, 44)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnMenuInfoSaldo)
                    .addComponent(btnMenuTransfer)
                    .addComponent(jLabel1))
                .addContainerGap(229, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(42, 42, 42)
                .addComponent(jLabel1)
                .addGap(18, 18, 18)
                .addComponent(btnMenuTransfer)
                .addGap(18, 18, 18)
                .addComponent(btnMenuInfoSaldo)
                .addContainerGap(73, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnMenuTransferActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnMenuTransferActionPerformed
        // TODO add your handling code here:
        FormTransfer f = new FormTransfer(this.socket, this.userLogin);
        f.setVisible(true);
    }//GEN-LAST:event_btnMenuTransferActionPerformed

    private void btnMenuInfoSaldoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnMenuInfoSaldoActionPerformed
        // TODO add your handling code here:
        String command = "INFOSALDO" + "[_]" + userLogin;

        try {
            out.writeBytes(command + "\n");

            String output = in.readLine();
            JOptionPane.showMessageDialog(rootPane, output);
        } catch (Exception e) {
            System.out.println("Error Info Saldo : " + e);
        }
    }//GEN-LAST:event_btnMenuInfoSaldoActionPerformed

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
            java.util.logging.Logger.getLogger(FormMenu.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(FormMenu.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(FormMenu.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(FormMenu.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new FormMenu().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnMenuInfoSaldo;
    private javax.swing.JButton btnMenuTransfer;
    private javax.swing.JLabel jLabel1;
    // End of variables declaration//GEN-END:variables
}
