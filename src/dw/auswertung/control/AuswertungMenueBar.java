package dw.auswertung.control;

import java.awt.event.ActionListener;

import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;


/**
 * Diese Klasse erzeugt ein Fenster mit allen Menuepunkten.
 * @author Larissa Janssen 
 * Hinweis: Es wurde von Yusuf Solyman nur ein neue Komponent
 *  zum Menu datei unter dem Namen bestellungListeVersand hinzugefügt
 */
public class AuswertungMenueBar {

  private JMenuBar menuBar;
  private JMenu datei, hilfe;
  private JMenuItem progrBeenden;
  private JMenuItem bestellungListe;
  private JMenuItem programInfo;
  private JMenuItem bestellungListeVersand;

  /**
   * Im Konstruktor wird die Übergabe eines Beobachters erwartet,
   * der die Funktionalität der Menüpunkte steuert.
   * In dieser Anwendung ist es die Klasse BestellungenListeSteuerungsFenster.
   */
  public AuswertungMenueBar(ActionListener beobachter) {
    init(beobachter);
  }

  /**
   * Diese Methode initialisiert die Fensterkomponenten.
   */
  private void init(ActionListener beobachter) {
    // MenuBar initialisieren
    menuBar = new JMenuBar();

    // ***************************
    // Menu Datei initialisieren
    // ***************************
    datei = new JMenu("Datei");
    menuBar.add(datei);

    // Einfügen der Trennlinie
    datei.addSeparator();
    progrBeenden = new JMenuItem(AuswertungSteuerungsFenster.END);
    progrBeenden.addActionListener(beobachter);
    datei.add(progrBeenden);
    // Einfügen der Trennlinie
    datei.addSeparator();
    bestellungListe = new JMenuItem(AuswertungSteuerungsFenster.AUSWERTEN);
    bestellungListe.addActionListener(beobachter);
    datei.add(bestellungListe);
    
    datei.addSeparator();
    bestellungListeVersand = new JMenuItem(AuswertungSteuerungsFenster.AUSWERTENBESTELLUNG);
    datei.add(bestellungListeVersand);

    // *******************************
    // Menu Info initialisieren
    // *******************************
    hilfe = new JMenu("Hilfe");
    hilfe.getAccessibleContext().setAccessibleDescription(
            "Programmhilfermationen ...");
    menuBar.add(hilfe);
    programInfo = new JMenuItem(AuswertungSteuerungsFenster.PROGRAMMINFO);
    programInfo.addActionListener(beobachter);
    hilfe.add(programInfo);

  }

  /**
   * Liefert ein bereits initialisiertes Menu mit allen Untermenüpunkten.
   */
  public JMenuBar getMenueBar() {
    return menuBar;
  }

}
