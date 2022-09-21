package dw.auswertung.view;

import java.awt.BorderLayout;
import java.awt.Point;

import javax.swing.JFrame;

import dw.alle.modul.DbConnectionZentrale;
import dw.alle.view.FilterFenster;
import dw.auswertung.control.AuswertungSteuerungsFenster;
import dw.auswertung.control.AuswertungMenueBar;
import dw.auswertung.modul.AuswertungBusinessLogik;

/**
 * Diese Klasse stellt die Übersicht aller Bestellungen dar.
 * @author Larissa Janssen
 * Hinweis: Es wurde von Yusuf Solyman nur den Titel des Dialoges geändert
 * und Buttonansicht für (Auswertung für Bestellung) vorgenommen.
 */
public class AuswertungHauptfenster extends JFrame {

  private AuswertungSteuerungsFenster aktionsFenster1;
  private AuswertungSteuerungsFenster aktionsFenster2;
  private FilterFenster filterFenster;
  private AuswertungMenueBar menueFenster;
  private AuswertungBusinessLogik businessLogik;
  public AuswertungHauptfenster(DbConnectionZentrale dbZentrale) {
    // Hauptfensterdekoration
    super("Auswertung: Online Shop");

    businessLogik = new AuswertungBusinessLogik(dbZentrale);

    // Fenster mit Filterangaben
    filterFenster = new FilterFenster(businessLogik);

    // Buttonsansicht
    aktionsFenster1 = new AuswertungSteuerungsFenster(this);
    aktionsFenster1.setDefaultButton(this, AuswertungSteuerungsFenster.AUSWERTEN);
    
    aktionsFenster2 = new AuswertungSteuerungsFenster(this);
    aktionsFenster2.setDefaultButton(this, AuswertungSteuerungsFenster.AUSWERTENBESTELLUNG);

    // Menubar erstellen und dem Fenster zuweisen
    menueFenster = new AuswertungMenueBar(aktionsFenster1);
    menueFenster = new AuswertungMenueBar(aktionsFenster2);
    setJMenuBar(menueFenster.getMenueBar());

    // Komponenten zum Container hinzufügen
    getContentPane().add(filterFenster, BorderLayout.CENTER);
    getContentPane().add(aktionsFenster1, BorderLayout.PAGE_END);
    getContentPane().add(aktionsFenster2, BorderLayout.PAGE_END);

    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    // Hauptfensterkoordinaten bestimmen
    Point xy = getLocation();
    setLocation(xy.x + 150, xy.y + 10);
    setSize(800, 750);
    setVisible(true);
  }

  /**
   * Liefert TabellenFenster der Bestellungen zurück.
   */
  public FilterFenster getFilterFenster() {
    return filterFenster;
  }
  /**
   * Liefert BusinessLogik der Bestellungen zurück.
   */
  public AuswertungBusinessLogik getBusinessLogik() {
    return businessLogik;
  }
}
