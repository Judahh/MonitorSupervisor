/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

/**
 *
 * @author JH
 */
public class MainWindow extends javax.swing.JFrame {

   /**
    * Creates new form MainWindow
    */
   public MainWindow() {
      initComponents();
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
      jList1 = new javax.swing.JList();

      setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

      jLabel1.setText("General status:");

      jList1.setModel(new javax.swing.AbstractListModel() {
         String[] strings = { "Item 1", "Item 2", "Item 3", "Item 4", "Item 5" };
         public int getSize() { return strings.length; }
         public Object getElementAt(int i) { return strings[i]; }
      });
      jScrollPane1.setViewportView(jList1);

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
   private javax.swing.JList jList1;
   private javax.swing.JRadioButton jRadioButton1;
   private javax.swing.JScrollPane jScrollPane1;
   // End of variables declaration//GEN-END:variables

   public void setElement(String username, int number, int currentNumber) {
      throw new UnsupportedOperationException("Not yet implemented");
   }

   public void logoffElement(String username) {
      throw new UnsupportedOperationException("Not yet implemented");
   }

   public void registerElement(String username) {
      throw new UnsupportedOperationException("Not yet implemented");
   }

   public void refreshElement(String username, int currentNumber) {
      throw new UnsupportedOperationException("Not yet implemented");
   }
}
