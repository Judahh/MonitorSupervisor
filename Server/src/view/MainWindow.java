/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import database.Database;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Vector;
import javax.swing.DefaultListModel;
import javax.swing.JList;
import server.Server;

/**
 *
 * @author JH
 */
public class MainWindow extends javax.swing.JFrame {

   private String serverName;
   private Server server;
   private DefaultListModel modelList;
   private Database database;
   private Connection connection = null;
   private Statement statement = null;
   private ResultSet resultSet = null;

   /**
    * Creates new form MainWindow
    */
   public MainWindow(String serverName) throws RemoteException, ClassNotFoundException, SQLException {
      initComponents();
      this.database = new Database();
      this.serverName = serverName;
      this.server = new Server(serverName, this);
      Registry registry = LocateRegistry.createRegistry(9000);
      registry.rebind("ServerRMI", server);
      getAllElements();
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
      jRadioButton1 = new javax.swing.JRadioButton();
      jScrollPane1 = new javax.swing.JScrollPane();
      modelList = new DefaultListModel();
      jList = new JList(modelList);

      setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

      jLabel1.setText("General status:");

      jList.setModel(modelList);
      jScrollPane1.setViewportView(jList);

      javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
      getContentPane().setLayout(layout);
      layout.setHorizontalGroup(
         layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
         .addGroup(layout.createSequentialGroup()
            .addContainerGap()
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
               .addComponent(jScrollPane1)
               .addGroup(layout.createSequentialGroup()
                  .addComponent(jLabel1)
                  .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                  .addComponent(jRadioButton1)
                  .addGap(0, 285, Short.MAX_VALUE)))
            .addContainerGap())
      );
      layout.setVerticalGroup(
         layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
         .addGroup(layout.createSequentialGroup()
            .addContainerGap()
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
               .addComponent(jRadioButton1)
               .addComponent(jLabel1))
            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 255, Short.MAX_VALUE)
            .addContainerGap())
      );

      pack();
   }// </editor-fold>//GEN-END:initComponents
   /**
    * @param args the command line arguments
    */
   // Variables declaration - do not modify//GEN-BEGIN:variables
   private javax.swing.JLabel jLabel1;
   private javax.swing.JList jList;
   private javax.swing.JRadioButton jRadioButton1;
   private javax.swing.JScrollPane jScrollPane1;
   // End of variables declaration//GEN-END:variables

   public void getAllElements() throws ClassNotFoundException, SQLException {
      Class.forName("com.mysql.jdbc.Driver");
      connection = DriverManager.getConnection(database.url());
      statement = connection.createStatement();
      String query = "select * from clientTable;";
      resultSet = statement.executeQuery(query);
      writeValues(resultSet);
   }

   private void writeValues(ResultSet resultSet) throws SQLException {
      modelList.clear();
      while (resultSet.next()) {
         String temp = new String();
         if (resultSet.getString("number") == null) {
            temp = resultSet.getString("name") + "-" + resultSet.getString("currentNumber") + " of " + resultSet.getString("number");
         }else{
            temp = resultSet.getString("name");
         }
         modelList.addElement(temp);
      }
   }

   public void setElement(String username, int number, int currentNumber) {
      for (int index = 0; index < modelList.size(); index++) {
         String temp=modelList.getElementAt(index).toString().split("-")[0];
         if(username.equals(temp)){
            modelList.setElementAt(username+"-"+Integer.toString(currentNumber)+" of "+Integer.toString(number), index);
            break;
         }
      }
   }

   public void resetElement(String oldUsername, String username, int number, int currentNumber) {
      for (int index = 0; index < modelList.size(); index++) {
         String temp=modelList.getElementAt(index).toString().split("-")[0];
         if(oldUsername.equals(temp)){
            modelList.setElementAt(username+"-"+Integer.toString(currentNumber)+" of "+Integer.toString(number), index);
            break;
         }
      }
   }
   
   public void logoffElement(String username) {
      for (int index = 0; index < modelList.size(); index++) {
         String temp=modelList.getElementAt(index).toString().split("-")[0];
         if(username.equals(temp)){
            modelList.setElementAt(username, index);
            break;
         }
      }
   }

   public void registerElement(String username) {
      modelList.addElement(username);
   }

   public void refreshElement(String username, int currentNumber) {
      for (int index = 0; index < modelList.size(); index++) {
         String temp[]=modelList.getElementAt(index).toString().split("-");
         if(username.equals(temp[0])){
            String number;
            if(temp.length>1){
               number=temp[1].split(" of ")[1];
               modelList.setElementAt(username+"-"+Integer.toString(currentNumber)+" of "+number, index);
            }else{
               modelList.setElementAt(username+"-"+Integer.toString(currentNumber), index);
            }
            break;
         }
      }
   }
}
