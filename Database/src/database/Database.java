/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package database;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

/**
 *
 * @author JH
 */
public class Database {

   private String url = "monitorsupervisor.cjjtjg8aan4o.sa-east-1.rds.amazonaws.com";
   private String port = "3306";
   private String DBName = "MonitorSupervisor";
   private String user = "MonitorS";
   private String password = "MonitorS";
   private Connection connection = null;
   private Statement statement = null;
   private ResultSet resultSet = null;
   private String serverTable = "serverTable";
   private String clientTable = "clientTable";

    public Database() {
    }

   public String getServerTable() {
      return serverTable;
   }

   public String getClientTable() {
      return clientTable;
   }
   
   public String url(){
      return  "jdbc:mysql://" + this.url + ":" + this.port + "/" + this.DBName + "?user=" + this.user + "&password=" + this.password;
   }
}
