/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import database.Database;
import java.net.Inet4Address;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import javax.swing.UIManager;

/**
 *
 * @author JH
 */
public class StartServerWindow extends javax.swing.JFrame {
   
   private Database database;
   private Connection connection = null;
   private Statement statement = null;
   private ResultSet resultSet = null;
    private MainWindow mainWindow;
   
   /**
    * Creates new form StartServerWindow
    */
   public StartServerWindow() {
      initComponents();
      this.database=new Database();
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
        jTextFieldServerName = new javax.swing.JTextField();
        jButtonRegister = new javax.swing.JButton();
        jButtonStart = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel1.setText("Name:");

        jTextFieldServerName.setText("serverTest");

        jButtonRegister.setText("Register");
        jButtonRegister.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonRegisterActionPerformed(evt);
            }
        });

        jButtonStart.setText("Start");
        jButtonStart.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonStartActionPerformed(evt);
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
                        .addComponent(jTextFieldServerName))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jButtonRegister)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 50, Short.MAX_VALUE)
                        .addComponent(jButtonStart)))
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
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButtonRegister)
                    .addComponent(jButtonStart))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButtonRegisterActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonRegisterActionPerformed
        try {
            Class.forName("com.mysql.jdbc.Driver");
            connection = DriverManager.getConnection(database.url());
            statement = connection.createStatement();
            System.out.println(jTextFieldServerName.getText());
            String query = "select * from serverTable where name='" + jTextFieldServerName.getText() + "'";
            resultSet = statement.executeQuery(query);
            if (resultSet.next()) {
                //erro ao se registrar
            } else {
               query = "INSERT INTO serverTable (`name`) VALUES ('" + jTextFieldServerName.getText() + "');";
               System.out.println(query);
               statement.executeUpdate(query);
               //ok
            }
        } catch (Exception e) {
            //erro ao se registrar
            System.out.println(e);
        }
    }//GEN-LAST:event_jButtonRegisterActionPerformed

    private void jButtonStartActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonStartActionPerformed
        try {
            Class.forName("com.mysql.jdbc.Driver");
            connection = DriverManager.getConnection(database.url());
            statement = connection.createStatement();
            System.out.println(jTextFieldServerName.getText());
            String query = "select * from serverTable where name='" + jTextFieldServerName.getText() + "' and !(address is null or address is NULL or address=0 or address='')";
            resultSet = statement.executeQuery(query);
            if (resultSet.next()) {
                //erro ao se logar
                System.out.println("tem");
            } else {
               query = "UPDATE clientTable SET `address`='" + Inet4Address.getLocalHost().getHostAddress().toString() + "' WHERE `name`='" + jTextFieldServerName.getText() + "'";
               System.out.println(query);
               statement.executeUpdate(query);
               //proxima tela
               this.setVisible(false);
               mainWindow = new MainWindow(jTextFieldServerName.getText());
               mainWindow.setVisible(true);
            }
        } catch (Exception e) {
            //erro ao se logar
            System.out.println(e);
        }
    }//GEN-LAST:event_jButtonStartActionPerformed

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
         java.util.logging.Logger.getLogger(StartServerWindow.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
      } catch (InstantiationException ex) {
         java.util.logging.Logger.getLogger(StartServerWindow.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
      } catch (IllegalAccessException ex) {
         java.util.logging.Logger.getLogger(StartServerWindow.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
      } catch (javax.swing.UnsupportedLookAndFeelException ex) {
         java.util.logging.Logger.getLogger(StartServerWindow.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
      }
      //</editor-fold>

      /* Create and display the form */
      java.awt.EventQueue.invokeLater(new Runnable() {
         public void run() {
            new StartServerWindow().setVisible(true);
         }
      });
   }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButtonRegister;
    private javax.swing.JButton jButtonStart;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JTextField jTextFieldServerName;
    // End of variables declaration//GEN-END:variables
}
