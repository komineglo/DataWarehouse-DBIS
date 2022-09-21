package dw.auswertung.control;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import dw.auswertung.modul.AuswertungDbException;
import dw.auswertung.view.AuswertungHauptfenster;

/**
 * Diese Klasse ist ein Fenster mit allen Buttons, die zur Auswertung im AuswertungHauptfenster angezeigt werden.
 * @author Larissa Janssen
 * Hinweis: Es wurde von Yusuf Solyman nur eine Erweiterung zur Methode actionPerformed vorgenommen,
 * um die Reaktion beim Druck auf Button refreshListeBestellung zu ermöglichen
 */
public class AuswertungSteuerungsFenster
    extends JPanel
    implements ActionListener {

	 /**
	 * 
	 */
	
	// Diese Variable wird beim Serialisieren/Deserialisieren der Objekte dieses Typs benötigt. 
                 //  Wir machen von der Variable serialVersionUID kein Gebrauch.
	private static final long serialVersionUID = 1270639706196597412L;
	public static final String END = "  Beenden  ";
	public static final String AUSWERTEN = "  Auswertung starten";
	public static final String PROGRAMMINFO = "Info über ...";
	public static final String AUSWERTENBESTELLUNG = "Auswertung für Bestellung";

	private JButton refreshList;
	private JButton end;
	private JButton refreshListBestellung;

	private SteuerungsLogik steuerung;

	public AuswertungSteuerungsFenster(
                                   AuswertungHauptfenster frame) {
    steuerung = new SteuerungsLogik(frame);
    // Initialisierung des Fensters
    init();
  }

  /**
   * Diese Methode initialisiert die Fensterkomponenten.
   */
  private void init() {
    // Buttons initialisieren
	  refreshListBestellung = new JButton(AUSWERTENBESTELLUNG);
    refreshList = new JButton(AUSWERTEN);
    end = new JButton(END);

    // Beobachter bei Buttons registrieren
    refreshListBestellung.addActionListener(this);
    refreshList.addActionListener(this);
    end.addActionListener(this);

    add(refreshListBestellung);
    add(refreshList);
    add(end);

    setOpaque(true);
    // Rahmen ( Abstände zu den anderen Elementen )
    setBorder(BorderFactory.createEmptyBorder(10,10,10,10));

  }

  public void actionPerformed(ActionEvent e) {
    if (END.equals(e.getActionCommand())) {
      // Programm beenden
      steuerung.exit();
    }
    else if (AUSWERTEN.equals(e.getActionCommand())) {
      try {
      // Bestellungliste aktualisieren
      steuerung.auswertungStarten();
      }
      catch (AuswertungDbException ex) {
    	  JOptionPane
			.showMessageDialog(
					new JFrame(),
					"Die Auswertung konnte nicht durchgeführt werden!",
					"Fehler", JOptionPane.ERROR_MESSAGE);      }
    }
    else if (PROGRAMMINFO.equals(e.getActionCommand())) {
      // Programminformation
      steuerung.getProgrammInfo();

    }
    else if (AUSWERTENBESTELLUNG.equals(e.getActionCommand())) {
        try {
        // Bestellungliste aktualisieren
        steuerung.auswertungStartenBestellung();
        }
        catch (AuswertungDbException ex) {
      	  JOptionPane
  			.showMessageDialog(
  					new JFrame(),
  					"Die Auswertung konnte nicht durchgeführt werden!",
  					"Fehler", JOptionPane.ERROR_MESSAGE);      }
      }
  }

  /**
   * Erlaubt ein Button als Vorauswahl zu setzen
   */
  public void setDefaultButton(AuswertungHauptfenster frame, String name) {
    if (name.equals(END))
      // Voreinstellung für aktiven Button
      frame.getRootPane().setDefaultButton(end);
  }
}
