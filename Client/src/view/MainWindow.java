/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import java.awt.GraphicsEnvironment;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.rmi.RemoteException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.Timer;
import rmi.ServerRMI;

/**
 *
 * @author JH
 */
public class MainWindow extends javax.swing.JFrame {

   private ServerRMI serverRMI;
   private int number;
   private int numberOfDevices;
   private String username;
   private String serverName;

   /**
    * Creates new form MainWindow
    */
   public MainWindow(ServerRMI serverRMI, String username, int number, String serverName) {
      this.serverRMI = serverRMI;
      this.numberOfDevices = 0;
      this.username = username;
      this.serverName=serverName;
      initComponents();
      jTextFieldName.setText(username);
      jTextFieldServerName.setText(serverName);
      jTextFieldNumber.setText(Integer.toString(number));
      try {
         updateData();
      } catch (RemoteException ex) {
         Logger.getLogger(MainWindow.class.getName()).log(Level.SEVERE, null, ex);
      }
      int delay = 1000; //milliseconds
      ActionListener taskPerformer = new ActionListener() {
         @Override
         public void actionPerformed(ActionEvent evt) {
            try {
               updateData();
            } catch (RemoteException ex) {
               Logger.getLogger(MainWindow.class.getName()).log(Level.SEVERE, null, ex);
            }
         }
      };
      new Timer(delay, taskPerformer).start();
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
        jLabel3 = new javax.swing.JLabel();
        jLabelCurrentNumber = new javax.swing.JLabel();
        jButtonSendChages = new javax.swing.JButton();
        jLabel4 = new javax.swing.JLabel();
        jRadioButton1 = new javax.swing.JRadioButton();
        jLabel5 = new javax.swing.JLabel();
        jTextFieldServerName = new javax.swing.JTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent evt) {
                formWindowClosing(evt);
            }
        });

        jLabel1.setText("Name:");

        jLabel2.setText("Number Of Monitors:");

        jLabel3.setText("Current Number Of Monitors:");

        jLabelCurrentNumber.setText(Integer.toString(GraphicsEnvironment.getLocalGraphicsEnvironment().getScreenDevices().length));

        jButtonSendChages.setText("Send Changes");
        jButtonSendChages.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonSendChagesActionPerformed(evt);
            }
        });

        jLabel4.setText("Status:");

        jRadioButton1.setEnabled(false);

        jLabel5.setText("Server Name:");

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
                        .addComponent(jLabel3)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabelCurrentNumber, javax.swing.GroupLayout.DEFAULT_SIZE, 172, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel4)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jRadioButton1))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jTextFieldNumber))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jButtonSendChages)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel5)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jTextFieldServerName)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel1)
                            .addComponent(jTextFieldName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel2)
                            .addComponent(jTextFieldNumber, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel3)
                            .addComponent(jLabelCurrentNumber)
                            .addComponent(jLabel4)))
                    .addComponent(jRadioButton1))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(jTextFieldServerName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButtonSendChages)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButtonSendChagesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonSendChagesActionPerformed
      try {
         serverRMI.resetClient(username, jTextFieldName.getText(), Integer.parseInt(jTextFieldNumber.getText()), Integer.parseInt(jLabelCurrentNumber.getText()), jTextFieldServerName.getText());
         this.username = jTextFieldName.getText();
      } catch (RemoteException ex) {
         Logger.getLogger(MainWindow.class.getName()).log(Level.SEVERE, null, ex);
      }
    }//GEN-LAST:event_jButtonSendChagesActionPerformed

   private void formWindowClosing(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosing
      try {
         serverRMI.logoff(username);
      } catch (RemoteException ex) {
         Logger.getLogger(MainWindow.class.getName()).log(Level.SEVERE, null, ex);
      }
   }//GEN-LAST:event_formWindowClosing
   /**
    * @param args the command line arguments
    */
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButtonSendChages;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabelCurrentNumber;
    private javax.swing.JRadioButton jRadioButton1;
    private javax.swing.JTextField jTextFieldName;
    private javax.swing.JTextField jTextFieldNumber;
    private javax.swing.JTextField jTextFieldServerName;
    // End of variables declaration//GEN-END:variables

   private void updateData() throws RemoteException {
      while (GraphicsEnvironment.getLocalGraphicsEnvironment().getScreenDevices().length != numberOfDevices) {
         refresh();
      }
   }

   private void refresh() throws RemoteException {
      numberOfDevices = GraphicsEnvironment.getLocalGraphicsEnvironment().getScreenDevices().length;
      jLabelCurrentNumber.setText(Integer.toString(numberOfDevices));
      serverRMI.refresh(username, numberOfDevices);
   }
}
