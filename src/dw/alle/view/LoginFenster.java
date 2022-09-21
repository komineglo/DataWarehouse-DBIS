package dw.alle.view;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import dw.alle.modul.DbConnectionZentrale;
import dw.auswertung.view.AuswertungHauptfenster;

/**
 * Diese Klasse ist ein Anmeldefenster zum Auswerten von Bestellungen.
 * @author Larissa Janssen
 */
public class LoginFenster
    extends JFrame implements ActionListener {

	 // Diese Variable wird beim Serialisieren/Deserialisieren der Objekte dieses Typs benötigt. 
                 //  Wir machen von der Variable serialVersionUID kein Gebrauch.
	private static final long serialVersionUID = 8327267276500517048L;
	private DbConnectionZentrale dbZentrale = null;
  private String[] params;
  public final static String OK = "OK"; // Speichern und Fenster schließen
  public final static String CLOSE = "Beenden";

  // Inhaltsfelder
  private JTextField userF;
  private JPasswordField passwortF;
  private JTextField datenbankF;
  private JTextField jndiServiceNameF;

  // Schaltfächen
  private JButton closeB;
  private JButton okB;

  public LoginFenster(String[] params) {
    super("Benutzeranmeldung");

    this.params = params;

    // Initialisierung des Bestellungenpanels
    init();
  }

  /**
   * Initialisierung des Fensters
   */
  private void init() {

    // Inhaltsbezeichnungen
    JLabel userL = new JLabel("Benutzer");
    JLabel passwortL = new JLabel("Passwort");
    JLabel datenbankL = new JLabel("Datenbank");
    JLabel jndiServiceNameL = new JLabel("JNDI-Service");

    userF = new JTextField(20);
    passwortF = new JPasswordField(20);
    datenbankF = new JTextField("", 20);
    jndiServiceNameF = new JTextField("", 20);

    // Werte für die Felder werden gesetzt
    setArguments();

    // Bezeichnungspanel
    JPanel labelPane = new JPanel(new GridLayout(4, 1));
    labelPane.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));

    labelPane.add(userL);
    labelPane.add(passwortL);
    labelPane.add(datenbankL);
    labelPane.add(jndiServiceNameL);

    // Eingabefelderpanel
    JPanel fieldPane = new JPanel(new GridLayout(4, 1));
    fieldPane.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));
    fieldPane.add(userF);
    fieldPane.add(passwortF);
    fieldPane.add(datenbankF);
    fieldPane.add(jndiServiceNameF);
    
    closeB = new JButton(CLOSE);
    okB = new JButton(OK);

    closeB.addActionListener(this);
    okB.addActionListener(this);

    JPanel buttonsPane = new JPanel();

    buttonsPane.add(okB);
    buttonsPane.add(closeB);

    // Voreinstellung für aktiven Button
    getRootPane().setDefaultButton(okB);

    getContentPane().add(labelPane, BorderLayout.CENTER);
    getContentPane().add(fieldPane, BorderLayout.LINE_END);
    getContentPane().add(buttonsPane, BorderLayout.SOUTH);

    setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    Point xy = getLocation();
    setLocation(xy.x + 250, xy.y + 250);
    setSize(370, 225);
    setVisible(true);
  }

  public void actionPerformed(ActionEvent e) {
    if (OK.equals(e.getActionCommand())) {
      boolean isOk = testConnection(userF.getText(),
                                    new String(passwortF.getPassword()),
                                    datenbankF.getText(),
                                    jndiServiceNameF.getText());
      if (!isOk) {
        JOptionPane.showMessageDialog(new JFrame(),
          "Die Anmeldung konnte nicht durchgeführt werden!" +
          "\n\nBitte überprüfen Sie Ihre Angaben!",
          "Anmeldung: fehlgeschlagen",
          JOptionPane.ERROR_MESSAGE);
      }
      else {
        // Das Hauptfenster für die Systemverwaltung wird geöffnet.
        new AuswertungHauptfenster(dbZentrale);
        // Alle nicht mehr benötigten Komponenten dieses Fensters
        // werden zerstört.
        dispose();
      }
    }
    else if (CLOSE.equals(e.getActionCommand())) {
      System.exit(0);
    }
  }

  /**
   * Setzt übergebene Argumente
   */
  private void setArguments() {
    // Wenn keine Argumente übergeben wurden, dann werden
    // einfach Standardtestdaten übernommen.
    if (params.length == 0) {
        String[] _params = new String[4];
        _params[0] = "User=db6034059";
        _params[1] = "Password=DB82bs8ehm";
        _params[2] = "Datenbank=db6034059";
        _params[3] = "JndiServiceName=studentenDS";
        params = _params;
    }

    for (int i = 0; i < params.length; i++) {
      if (!params[i].equals("")) {
        if (params[i].indexOf("User=") != -1) {
          String user = getParam("User=", params[i]);
          userF.setText(user);
        }
        else
        if (params[i].indexOf("Password=") != -1) {
          String password = getParam("Password=", params[i]);
          passwortF.setText(password);
        }
        else
        if (params[i].indexOf("Datenbank=") != -1) {
          String db = getParam("Datenbank=", params[i]);
          datenbankF.setText(db);
        }
        else
            if (params[i].indexOf("JndiServiceName=") != -1) {
              String db = getParam("JndiServiceName=", params[i]);
              jndiServiceNameF.setText(db);
            }
      }
    }
  }

  /**
   * Liefert übergebenes Wort zurück.
   */
  private String getParam(String key, String line) {
    String param = "";
    int begin = line.indexOf(key) + key.length();
    int end = line.length();
    param = line.substring(begin, end);
    return param;
  }

  /**
   * Anwendung wird gestartet
   */
  private boolean testConnection(String user, String passwort, String datenbank, String jndiServiceName) {
    boolean istVerbunden = false;
      try {
        dbZentrale = new DbConnectionZentrale(
                datenbank,
                user,
                passwort,
                "studentenDS"
                );
        istVerbunden = dbZentrale.testConnection();

      }
      catch (Exception e) {
      	e.printStackTrace();
        istVerbunden = false;
      }
    return istVerbunden;
  }
}
