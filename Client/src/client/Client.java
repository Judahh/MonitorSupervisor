/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package client;

import java.awt.GraphicsEnvironment;
import java.awt.Toolkit;

/**
 *
 * @author JH
 */
public class Client {

   /**
    * @param args the command line arguments
    */
   public static void main(String[] args) {
      while(true){
			while(GraphicsEnvironment.getLocalGraphicsEnvironment().getScreenDevices().length<2){
				Toolkit.getDefaultToolkit().beep();
			}
//			System.out.println(GraphicsEnvironment.getLocalGraphicsEnvironment().getScreenDevices().length);
		}
   }
}
