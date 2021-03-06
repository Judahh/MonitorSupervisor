/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import java.awt.GraphicsEnvironment;
import java.net.Inet4Address;
import java.net.UnknownHostException;
import java.rmi.RemoteException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import rmi.ServerRMI;

/**
 *
 * @author JH
 */
public class LoginWindow extends javax.swing.JFrame {

   private ServerRMI serverRMI;
   private MainWindow mainWindow;
   private String serverName;

   /**
    * Creates new form LoginWindow
    */
   public LoginWindow(ServerRMI serverRMI, String serverName) {
      initComponents();
      this.serverName=serverName;
      this.serverRMI = serverRMI;
   }

   /**
    * This method is called from within the constructor to initialize the form.
    * WARNING: Do NOT modify this code. The content of this method is always
    * regenerated by the Form Editor.
    */
   @SuppressWarnings("unchecked")
   // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
   private void initComponents() {

      jLabel1 = new javax.swing.JLabel();
      jTextFieldName = new javax.swing.JTextField();
      jLabel2 = new javax.swing.JLabel();
      jTextFieldNumber = new javax.swing.JTextField();
      jButtonRegister = new javax.swing.JButton();
      jButtonLogin = new javax.swing.JButton();

      setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

      jLabel1.setText("Name:");

      jTextFieldName.setText("testMonitor");

      jLabel2.setText("Number Of Monitors:");

      jTextFieldNumber.setText(Integer.toString(GraphicsEnvironment.getLocalGraphicsEnvironment().getScreenDevices().length));

      jButtonRegister.setText("Register");
      jButtonRegister.addActionListener(new java.awt.event.ActionListener() {
         public void actionPerformed(java.awt.event.ActionEvent evt) {
            jButtonRegisterActionPerformed(evt);
         }
      });

      jButtonLogin.setText("Login");
      jButtonLogin.addActionListener(new java.awt.event.ActionListener() {
         public void actionPerformed(java.awt.event.ActionEvent evt) {
            jButtonLoginActionPerformed(evt);
         }
      });

      javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
      getContentPane().setLayout(layout);
      layout.setHorizontalGroup(
         layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
         .addGroup(layout.createSequentialGroup()
            .addContainerGap()
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
               .addGroup(layout.createSequentialGroup()
                  .addComponent(jLabel1)
                  .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                  .addComponent(jTextFieldName))
               .addGroup(layout.createSequentialGroup()
                  .addComponent(jLabel2)
                  .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                  .addComponent(jTextFieldNumber))
               .addGroup(layout.createSequentialGroup()
                  .addComponent(jButtonRegister)
                  .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 50, Short.MAX_VALUE)
                  .addComponent(jButtonLogin)))
            .addContainerGap())
      );
      layout.setVerticalGroup(
         layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
         .addGroup(layout.createSequentialGroup()
            .addContainerGap()
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
               .addComponent(jLabel1)
               .addComponent(jTextFieldName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
               .addComponent(jLabel2)
               .addComponent(jTextFieldNumber, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
               .addComponent(jButtonRegister)
               .addComponent(jButtonLogin))
            .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
      );

      pack();
   }// </editor-fold>//GEN-END:initComponents

    private void jButtonRegisterActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonRegisterActionPerformed
       try {
          if (this.serverRMI.register(jTextFieldName.getText(), Integer.parseInt(jTextFieldNumber.getText()), GraphicsEnvironment.getLocalGraphicsEnvironment().getScreenDevices().length)) {
             JOptionPane.showMessageDialog(null, "Register Ok!");
          } else {
             JOptionPane.showMessageDialog(null, "Register Error!");
          }
       } catch (RemoteException ex) {
          JOptionPane.showMessageDialog(null, "Register Error!");
          Logger.getLogger(LoginWindow.class.getName()).log(Level.SEVERE, null, ex);
       }
    }//GEN-LAST:event_jButtonRegisterActionPerformed

    private void jButtonLoginActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonLoginActionPerformed
       try {
          if (this.serverRMI.login(jTextFieldName.getText(), Integer.parseInt(jTextFieldNumber.getText()), GraphicsEnvironment.getLocalGraphicsEnvironment().getScreenDevices().length, Inet4Address.getLocalHost().getHostAddress().toString())) {

             this.setVisible(false);
             mainWindow = new MainWindow(serverRMI, jTextFieldName.getText(), Integer.parseInt(jTextFieldNumber.getText()), serverName);
             mainWindow.setVisible(true);
          }else{
             JOptionPane.showMessageDialog(null, "Login Error!");
          }
       } catch (RemoteException ex) {
          JOptionPane.showMessageDialog(null, "Login Error!");
          Logger.getLogger(LoginWindow.class.getName()).log(Level.SEVERE, null, ex);
       } catch (UnknownHostException ex) {
          JOptionPane.showMessageDialog(null, "Login Error!");
          Logger.getLogger(LoginWindow.class.getName()).log(Level.SEVERE, null, ex);
       }
    }//GEN-LAST:event_jButtonLoginActionPerformed
   /**
    * @param args the command line arguments
    */
   // Variables declaration - do not modify//GEN-BEGIN:variables
   private javax.swing.JButton jButtonLogin;
   private javax.swing.JButton jButtonRegister;
   private javax.swing.JLabel jLabel1;
   private javax.swing.JLabel jLabel2;
   private javax.swing.JTextField jTextFieldName;
   private javax.swing.JTextField jTextFieldNumber;
   // End of variables declaration//GEN-END:variables
}
