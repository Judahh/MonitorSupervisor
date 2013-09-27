/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package server;

import database.Database;
import java.rmi.AccessException;
import java.rmi.AlreadyBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import rmi.ServerRMI;
import view.MainWindow;

/**
 *
 * @author JH
 */
public class Server extends UnicastRemoteObject implements ServerRMI {

   private ServerRMI rmi;
   private Registry registry;
   private database.Database database;
   private String name;
   private MainWindow mainWindow;
   private Connection connection = null;
   private Statement statement = null;
   private ResultSet resultSet = null;

   public Server(String name, MainWindow mainWindow) throws RemoteException {
      super();
      try {
         this.name = name;
         this.mainWindow = mainWindow;
         this.database = new Database();
         //Exporta o objeto remoto  
         this.rmi = (ServerRMI) UnicastRemoteObject
                 .exportObject(this, 0);
         //Liga o stub do objeto remoto no registro  
         this.registry = LocateRegistry.getRegistry(9000);
         //Dá um nome pra ele no registro  
         this.registry.bind("ServerRMI", rmi);
      } catch (AlreadyBoundException ex) {
         Logger.getLogger(ServerRMI.class.getName()).log(Level.SEVERE, null, ex);
      } catch (AccessException ex) {
         Logger.getLogger(ServerRMI.class.getName()).log(Level.SEVERE, null, ex);
      } catch (RemoteException Re) {
         System.out.println(Re.getMessage());
      }
   }

   @Override
   public boolean login(String username, int number, int currentNumber, String address) throws RemoteException {
      try {
         Class.forName("com.mysql.jdbc.Driver");
         connection = DriverManager.getConnection(database.url());
         statement = connection.createStatement();
         System.out.println(username);
         String query = "select * from clientTable where name='" + username + "' and (address is null or address is NULL or address=0 or address='') and serverName='" + this.name + "'";
         System.out.println(query);
         resultSet = statement.executeQuery(query);
         if (resultSet.next()) {
            query = "UPDATE clientTable SET `address`='" + address + "', `number`='" + number + "', `currentNumber`='" + currentNumber + "' WHERE `name`='" + username + "'";
            System.out.println(query);
            statement.executeUpdate(query);
            mainWindow.setElement(username, number, currentNumber);
            return true;
         }
      } catch (Exception e) {
         System.out.println(e);
      }
      return false;
   }

   @Override
   public boolean logoff(String username) throws RemoteException {
      try {
         Class.forName("com.mysql.jdbc.Driver");
         connection = DriverManager.getConnection(database.url());
         statement = connection.createStatement();
         System.out.println(username);
         String query = "select * from clientTable where name='" + username + "' and !(address is null or address is NULL or address=0 or address='')";
         System.out.println(query);
         resultSet = statement.executeQuery(query);
         if (resultSet.next()) {
             query = "UPDATE clientTable SET `address`=null WHERE `name`='" + username + "'";
            System.out.println(query);
            statement.executeUpdate(query);
            mainWindow.logoffElement(username);    
            return true;
         }
      } catch (Exception e) {
         System.out.println(e);
      }
      return false;
   }

   @Override
   public boolean register(String username, int number, int currentNumber) throws RemoteException {
       try {
         Class.forName("com.mysql.jdbc.Driver");
         connection = DriverManager.getConnection(database.url());
         statement = connection.createStatement();
         System.out.println(username);
         String query = "select * from clientTable where name='" + username + "'";
         resultSet = statement.executeQuery(query);
         if (resultSet.next()) {
            return false;
         } else {
             
            query = "INSERT INTO clientTable (`name`, `number`, `currentNumber`, `serverName`) VALUES ('" + username + "', '" + number + "', '" + currentNumber + "', '" + name + "');";//TODO: colocar se é deste server (chave extrangeira)
            System.out.println(query);
            statement.executeUpdate(query);
            mainWindow.registerElement(username);
            return true;
         }
      } catch (Exception e) {
         System.out.println(e);
      }
      return false;
   }

   @Override
   public boolean resetClient(String oldUsername, String username, int number, int currentNumber, String nameServer) throws RemoteException {
      try {
         Class.forName("com.mysql.jdbc.Driver");
         connection = DriverManager.getConnection(database.url());
         statement = connection.createStatement();
         System.out.println(username);
         String query = "select * from clientTable where name='" + oldUsername + "'";
         resultSet = statement.executeQuery(query);
         if (resultSet.next()) {
            return false;
         } else {
            query = "UPDATE clientTable SET `name`='" + username + "', `number`='" + number + "', `currentNumber`='" + currentNumber + "', `nameServer`='" + nameServer + "' WHERE `name`='" + oldUsername + "'";
            System.out.println(query);
            statement.executeUpdate(query);
            mainWindow.registerElement(username);
            return true;
         }
      } catch (Exception e) {
         System.out.println(e);
      }
      return false;
   }

   @Override
   public boolean refresh(String username, int currentNumber) throws RemoteException {
      try {
         Class.forName("com.mysql.jdbc.Driver");
         connection = DriverManager.getConnection(database.url());
         statement = connection.createStatement();
         System.out.println(username);
         String query = "select * from clientTable where name='" + username + "' and !(address is null or address is NULL or address=0 or address='') and serverName='" + this.name + "'";
         System.out.println(query);
         resultSet = statement.executeQuery(query);
         if (resultSet.next()) {
            query = "UPDATE clientTable SET `currentNumber`='" + currentNumber + "' WHERE `name`='" + username + "'";//TODO: colocar se é deste server (chave extrangeira)
            System.out.println(query);
            statement.executeUpdate(query);
            mainWindow.refreshElement(username, currentNumber);
            return true;
         }
      } catch (Exception e) {
         System.out.println(e);
      }
      return false;
   }
}
