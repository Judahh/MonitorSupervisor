/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import database.Database;
import java.net.Inet4Address;
import java.net.UnknownHostException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.JOptionPane;
import javax.swing.UIManager;
import rmi.ServerRMI;

/**
 *
 * @author JH
 */
public class ConnectToServerWindow extends javax.swing.JFrame {

   private ServerRMI serverRMI;
   private Database database;
   private Connection connection = null;
   private Statement statement = null;
   private ResultSet resultSet = null;
   private LoginWindow loginWindow;

   /**
    * Creates new form ConnectToServerWindow
    */
   public ConnectToServerWindow() {
      initComponents();
      this.database = new Database();
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
      jButtonConnect = new javax.swing.JButton();
      jTextFieldServerName = new javax.swing.JTextField();

      setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

      jLabel1.setText("Server:");

      jButtonConnect.setText("Connect");
      jButtonConnect.addActionListener(new java.awt.event.ActionListener() {
         public void actionPerformed(java.awt.event.ActionEvent evt) {
            jButtonConnectActionPerformed(evt);
         }
      });

      jTextFieldServerName.setText("serverTest");

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
                  .addComponent(jTextFieldServerName, javax.swing.GroupLayout.DEFAULT_SIZE, 140, Short.MAX_VALUE))
               .addGroup(layout.createSequentialGroup()
                  .addComponent(jButtonConnect)
                  .addGap(0, 0, Short.MAX_VALUE)))
            .addContainerGap())
      );
      layout.setVerticalGroup(
         layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
         .addGroup(layout.createSequentialGroup()
            .addContainerGap()
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
               .addComponent(jLabel1)
               .addComponent(jTextFieldServerName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
            .addComponent(jButtonConnect)
            .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
      );

      pack();
   }// </editor-fold>//GEN-END:initComponents

   private void jButtonConnectActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonConnectActionPerformed
      try {
         Registry registry = LocateRegistry.getRegistry(getServerAddress(), 9000);
         serverRMI = (ServerRMI) registry.lookup("ServerRMI");

         this.setVisible(false);
         loginWindow = new LoginWindow(serverRMI, jTextFieldServerName.getText());
         loginWindow.setVisible(true);
      } catch (Exception e) {
         JOptionPane.showMessageDialog(null, "Connect error!");
         System.out.println(e);
      }
   }//GEN-LAST:event_jButtonConnectActionPerformed

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
         UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
      } catch (ClassNotFoundException ex) {
         java.util.logging.Logger.getLogger(LoginWindow.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
      } catch (InstantiationException ex) {
         java.util.logging.Logger.getLogger(LoginWindow.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
      } catch (IllegalAccessException ex) {
         java.util.logging.Logger.getLogger(LoginWindow.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
      } catch (javax.swing.UnsupportedLookAndFeelException ex) {
         java.util.logging.Logger.getLogger(LoginWindow.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
      }
      //</editor-fold>

      /* Create and display the form */
      java.awt.EventQueue.invokeLater(new Runnable() {
         public void run() {
            new ConnectToServerWindow().setVisible(true);
         }
      });
   }
   // Variables declaration - do not modify//GEN-BEGIN:variables
   private javax.swing.JButton jButtonConnect;
   private javax.swing.JLabel jLabel1;
   private javax.swing.JTextField jTextFieldServerName;
   // End of variables declaration//GEN-END:variables

   private String getServerAddress() throws ClassNotFoundException, SQLException, UnknownHostException {
      Class.forName("com.mysql.jdbc.Driver");
      connection = DriverManager.getConnection(database.url());
      statement = connection.createStatement();
      System.out.println(jTextFieldServerName.getText());
      String query = "select * from serverTable where name='" + jTextFieldServerName.getText() + "' and !(address is null or address is NULL or address=0 or address='')";
      resultSet = statement.executeQuery(query);
      if (resultSet.next()) {
         return resultSet.getNString("address");
      }
      return null;
   }
}
