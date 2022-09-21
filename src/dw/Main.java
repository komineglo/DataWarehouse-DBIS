package dw;

import dw.alle.view.LoginFenster;
/**
 * ein Auswertungsprogramm für ein Onlineshop. 
 * Die Auswertungen greifen auf den Bestand des Data Warehouse zurück

 */
public class Main {

    /**
     * Die Anwendung wird hier gestartet. Das ist der Hauptthread des
     * Programms.
     */
    public static void main(final String[] args) {
      javax.swing.SwingUtilities.invokeLater(new Runnable() {

        public void run() {
          new LoginFenster(args);
        }
      });
  }
}


