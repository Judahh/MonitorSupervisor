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
   public boolean login(String username, int number, int currentNumber) throws RemoteException;
   public boolean logoff(String username, String password) throws RemoteException;
   public boolean register(String username, String password) throws RemoteException;
   
   public boolean resetClient(String oldUsername, String username, int number, int currentNumber) throws RemoteException;
   public boolean setClient(String username, int number, int currentNumber) throws RemoteException;
   public boolean setNumber(String username, int number) throws RemoteException;
   public boolean setCurrentNumber(String username, int currentNumber) throws RemoteException;
   public boolean refresh(String username, int currentNumber) throws RemoteException;
}