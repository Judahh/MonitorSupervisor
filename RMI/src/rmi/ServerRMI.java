/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package rmi;

import java.rmi.Remote;
import java.rmi.RemoteException;

/**
 *
 * @author JH
 */
public interface ServerRMI extends Remote{
   public boolean login(String username, int number, int currentNumber, String address) throws RemoteException;
   public boolean logoff(String username) throws RemoteException;
   public boolean register(String username, int number, int currentNumber) throws RemoteException;
   
   public boolean resetClient(String oldUsername, String username, int number, int currentNumber, String nameServer) throws RemoteException;
   public boolean refresh(String username, int currentNumber) throws RemoteException;
}