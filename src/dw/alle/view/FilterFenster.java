package dw.alle.view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.ArrayList;
import java.util.EventObject;

import javax.swing.BorderFactory;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.Border;
import javax.swing.border.TitledBorder;

import com.toedter.calendar.JDateChooser;

import dw.alle.modul.Datumformatierung;
import dw.alle.modul.EuroFormatierung;
import dw.auswertung.modul.AuswertungBusinessLogik;
import dw.customer.modul.DimCustomer;
import dw.customer.modul.CustomerPlatzhalter;
import dw.customer.modul.CustomerDbException;
import dw.customer.modul.DimCustomerDb;
import dw.shipping_method.modul.Shipping_methodPlatzhalter;
import dw.shipping_method.modul.Shipping_methodDbException;
import dw.shipping_method.modul.DimShipping_method;
import dw.shipping_method.modul.DimShipping_methodDb;
import dw.product.modul.DimProduct;
import dw.product.modul.DimProductDb;
import dw.product.modul.ProductDbException;
import dw.product.modul.ProductPlatzhalter;
import dw.payment_methode.modul.DimPayment_method;
import dw.payment_methode.modul.DimPayment_methodDb;
import dw.payment_methode.modul.Payment_methodDbException;
import dw.payment_methode.modul.Payment_methodPlatzhalter;
import dw.product_category.modul.DimProductCategory;
import dw.product_category.modul.DimProductCategoryDb;
import dw.product_category.modul.ProductCategoryDbException;
import dw.product_category.modul.ProductCategoryPlatzhalter;
import dw.time.modul.DimZeitDb;
import dw.time.modul.ZeitDbException;



/**
 * Diese Klasse ist ein Fenster mit allen Filterangaben, die zur Filterung von
 * Bestellungen verwendet wird.
 */
public class FilterFenster extends JPanel implements ItemListener,
		ActionListener {

	// Diese Variable wird beim Serialisieren/Deserialisieren der Objekte dieses
	// Typs benötigt.
	// Wir machen von der Variable serialVersionUID kein Gebrauch.
	private static final long serialVersionUID = -871408556493376768L;

	// Inhaltsfelder
	// In dieser einer Variable ist das Datumsfeld und Kalender werden zusammen präsentiert als eine Einheit
	private JDateChooser vonDatumF = null;
	// In dieser einer Variable ist das Datumsfeld und Kalender werden zusammen präsentiert als eine Einheit
	private JDateChooser bisDatumF = null;

	private JComboBox customerBox;
	private JComboBox productBox;
	private JComboBox productcategoryBox;
	private JComboBox shipping_methodBox;
	private JComboBox payment_methodBox;
	private JComboBox jahreBox;
	private JCheckBox quartal1CheckBox;
	private JCheckBox quartal2CheckBox;
	private JCheckBox quartal3CheckBox;
	private JCheckBox quartal4CheckBox;
	private JLabel quantityValueL;
	private JLabel umsatzValueL;
	private JLabel gewinnValueL;
	private JLabel retourniertValueL;
	private JLabel storniertValueL;
	private JLabel anzahlBestellungL;
	private ArrayList<String> jahre;

	public FilterFenster(AuswertungBusinessLogik bussinesLogik) {
		super(new GridLayout(0, 1));

		ArrayList<DimCustomer> customer = null;
		ArrayList<DimProduct> product = null;
		ArrayList<DimProductCategory> productcategory = null;

		ArrayList<DimShipping_method> shipping_method = null;
		ArrayList<DimPayment_method> payment_method = null;

		try {
			// Alle Kunden aus der Datenbank laden
			customer = DimCustomerDb.getAll(bussinesLogik.getDbZentrale());
			// Platzhalter für alle Kunden
			customer.add(0, new CustomerPlatzhalter());

			// Alle Artikel aus der Datenbank laden
			product = DimProductDb.getAll(bussinesLogik.getDbZentrale());
			// Platzhalter für alle Artikel
			product.add(0, new ProductPlatzhalter());
			
			// Alle Produktkategorien aus der Datenbank laden
			productcategory = DimProductCategoryDb.getAll(bussinesLogik
					.getDbZentrale());
			// Platzhalter für alle Produktkategorien
			productcategory.add(0, new ProductCategoryPlatzhalter());
			

			// Alle Versandart aus der Datenbank laden
			shipping_method = DimShipping_methodDb.getAll(bussinesLogik.getDbZentrale());
			// Platzhalter für alle Versandart
			shipping_method.add(0, new Shipping_methodPlatzhalter());
			
			// Alle Zahlungsarten aus der Datenbank laden
			payment_method = DimPayment_methodDb.getAll(bussinesLogik.getDbZentrale());
			// Platzhalter für alle Zahlungsarten
			payment_method.add(0, new Payment_methodPlatzhalter());

		
			// Alle Jahre aus der Datenbank laden
			jahre = DimZeitDb.getAllJahre(bussinesLogik.getDbZentrale());

		} catch (CustomerDbException e) {
			JOptionPane.showMessageDialog(new JFrame(),
					"Beim Auslesen von Kundendaten sind Fehler aufgetreten!", "Fehler",
					JOptionPane.ERROR_MESSAGE);

		} catch (ProductDbException e) {
			JOptionPane.showMessageDialog(new JFrame(),
					"Beim Auslesen von Artikeln sind Fehler aufgetreten!", "Fehler",
					JOptionPane.ERROR_MESSAGE);

		}  catch (ProductCategoryDbException e) {
			JOptionPane.showMessageDialog(new JFrame(),
					"Beim Auslesen von Produktkategorien sind Fehler aufgetreten!",
					"Fehler", JOptionPane.ERROR_MESSAGE);

		} catch (Shipping_methodDbException e) {
			JOptionPane.showMessageDialog(new JFrame(),
					"Beim Auslesen von Versandarten sind Fehler aufgetreten!", "Fehler",
					JOptionPane.ERROR_MESSAGE);

		} catch (Payment_methodDbException e) {
			JOptionPane.showMessageDialog(new JFrame(),
					"Beim Auslesen von Zahlungsart sind Fehler aufgetreten!", "Fehler",
					JOptionPane.ERROR_MESSAGE);

		} catch (ZeitDbException e) {
			JOptionPane
					.showMessageDialog(
							new JFrame(),
							"Beim Auslesen von Jahren aus der Dimension Zeit sind Fehler aufgetreten!",
							"Fehler", JOptionPane.ERROR_MESSAGE);

		}
		// Initialisierung des Panels
		init(customer, product,  productcategory, shipping_method, payment_method, jahre);
	}

	/**
	 * Initialisierung des Fensters
	 */
	private void init(
			ArrayList<DimCustomer> customer, 
			ArrayList<DimProduct> product, 
			ArrayList<DimProductCategory> productcategory,
			ArrayList<DimShipping_method> shipping_method,
			ArrayList<DimPayment_method> payment_method,
			ArrayList<String> jahre) {
		// Inhalte

		String vonDatum = getBeginnDatum(jahre);
		// Wenn das Von-Datum vorbelegt, dann dieses Datum übernehmen
		if (vonDatum.trim() != "") {
			try {
				vonDatumF = new JDateChooser(Datumformatierung.getDate(vonDatum));
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else
			// Wenn kein Von-Datum vorbelegt ist, dann leeren Konstruktor aufrufen
			vonDatumF = new JDateChooser();

		String bisDatum = getEndDatum(jahre);
		// Wenn das Bis-Datum vorbelegt, dann dieses Datum übernehmen
		if (bisDatum.trim() != "") {
			try {
				bisDatumF = new JDateChooser(Datumformatierung.getDate(bisDatum));
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else
			// Wenn kein Bis-Datum vorbelegt ist, dann leeren Konstruktor aufrufen
			bisDatumF = new JDateChooser();

		quantityValueL = new JLabel("- Auswertung starten -");
		umsatzValueL = new JLabel("- Auswertung starten -");
		gewinnValueL = new JLabel("- Auswertung starten -");
		retourniertValueL = new JLabel("- Auswertung starten -");
		storniertValueL = new JLabel("- Auswertung starten -");
		anzahlBestellungL = new JLabel("- Auswertung starten -");

		// Initialisiert Combobox mit Kundendaten
		initCustomerBox(customer);
		
		// Initialisiert Combobox mit Artikeln
		initProductBox(product);
		
		// Initialisiert Combobox mit Produktkategorien
		initProductCategoryBox(productcategory);
		
		// Initialisiert Combobox mit Versandarten
		initShipping_methodBox(shipping_method);
		
		// Initialisiert Combobox mit Zahlungsarten
		initPayment_methodBox(payment_method);
		
		// Initialisiert Combobox mit Jahren
		initJahreBox(jahre);

		quartal1CheckBox = new JCheckBox("1. Quartal");
		quartal1CheckBox.setSelected(false);
		quartal2CheckBox = new JCheckBox("2. Quartal");
		quartal2CheckBox.setSelected(false);
		quartal3CheckBox = new JCheckBox("3. Quartal");
		quartal3CheckBox.setSelected(false);
		quartal4CheckBox = new JCheckBox("4. Quartal");
		quartal4CheckBox.setSelected(false);

		quartal1CheckBox.addItemListener(this);
		quartal2CheckBox.addItemListener(this);
		quartal3CheckBox.addItemListener(this);
		quartal4CheckBox.addItemListener(this);

		JPanel pane1 = new JPanel(new GridLayout(1, 0));
		
		
		// Bezeichnungspanel
		JPanel labelPane1 = new JPanel(new GridLayout(0, 1));
		
		

		labelPane1.add(new JLabel("Produktkategorie"));
		labelPane1.add(new JLabel("Produkt"));
		labelPane1.add(new JLabel("Kundenauswahl"));
		
		

		labelPane1.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
		

		// Eingabefelderpanel
		JPanel fieldPane1 = new JPanel(new GridLayout(0, 1));
		
		fieldPane1.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
		fieldPane1.add(productcategoryBox);
		fieldPane1.add(productBox);
		fieldPane1.add(customerBox);
		
		pane1.add(labelPane1, BorderLayout.CENTER);
		pane1.add(fieldPane1, BorderLayout.LINE_END);
		pane1.setBorder(BorderFactory.createEmptyBorder(5,5, 5, 5));
		
		
		
		
		JPanel pane5 = new JPanel(new GridLayout(1, 0));
		
		JPanel labelPane5 = new JPanel(new GridLayout(0, 1));
		labelPane5.add(new JLabel("Versandart"));
		labelPane5.add(new JLabel("Zahlungsart"));
		labelPane5.add(new JLabel("Bestellungen nach Versand und/oder Zahlung"));
		
		JPanel fieldPane5 = new JPanel(new GridLayout(0 ,1));
		fieldPane5.add(shipping_methodBox);
		fieldPane5.add(payment_methodBox);
		fieldPane5.add(anzahlBestellungL);
		
		Border blackline = BorderFactory.createLineBorder(Color.black);
		Border empty = BorderFactory.createEmptyBorder(5, 5, 5, 5);
		TitledBorder title = BorderFactory.createTitledBorder(blackline,
				"Bestellungsauswerten nach Versand- und Zahlungsart");
		title.setTitleJustification(TitledBorder.LEFT);
		// Compound borders
		Border compound = BorderFactory.createCompoundBorder(empty, title);
		
		pane5.add(labelPane5, BorderLayout.CENTER);
		pane5.add(fieldPane5, BorderLayout.LINE_END);
		pane5.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
		pane5.setBorder(compound);

		
		
		
		JPanel pane2 = new JPanel(new GridLayout(1, 0));
		
		JPanel labelPanel2 = new JPanel(new GridLayout(0, 1));
		labelPanel2.add(new JLabel("Verkaufte Menge"));
		labelPanel2.add(new JLabel("Summe der Umsatz"));
		labelPanel2.add(new JLabel("Summe der Gewinn"));
		labelPanel2.add(new JLabel("Anzahl retournierter Artikelen"));
		labelPanel2.add(new JLabel("Anzahl stornierter Bestellungen"));
		
		
		labelPanel2.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
		
		
		JPanel ergebnisPanel2 = new JPanel(new GridLayout (0, 1));
		ergebnisPanel2.add(quantityValueL);
		ergebnisPanel2.add(umsatzValueL);
		ergebnisPanel2.add(gewinnValueL);
		ergebnisPanel2.add(retourniertValueL);
		ergebnisPanel2.add(storniertValueL);
		
		
		
		blackline = BorderFactory.createLineBorder(Color.black);
		empty = BorderFactory.createEmptyBorder(5, 5, 5, 5);
		title = BorderFactory.createTitledBorder(blackline,
				"Ergebnisse");
		title.setTitleJustification(TitledBorder.LEFT);
		// Compound borders
		compound = BorderFactory.createCompoundBorder(empty, title);
		
		pane2.add(labelPanel2, BorderLayout.CENTER);
		pane2.add(ergebnisPanel2, BorderLayout.LINE_END);
		pane2.setBorder(compound);
		
		
		JPanel pane3 = new JPanel(new GridLayout(1, 0));

		// Bezeichnungspanel
		JPanel labelPane3 = new JPanel(new GridLayout(0, 1));

		labelPane3.add(new JLabel("Jahr"));
		labelPane3.add(new JLabel(""));
		labelPane3.add(new JLabel(""));
		labelPane3.add(new JLabel(""));
		labelPane3.add(new JLabel(""));
		labelPane3.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));

		// Eingabefelderpanel
		JPanel fieldPane3 = new JPanel(new GridLayout(0, 1));
		fieldPane3.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
		fieldPane3.add(jahreBox);
		fieldPane3.add(quartal1CheckBox);
		fieldPane3.add(quartal2CheckBox);
		fieldPane3.add(quartal3CheckBox);
		fieldPane3.add(quartal4CheckBox);

		blackline = BorderFactory.createLineBorder(Color.black);
		empty = BorderFactory.createEmptyBorder(5, 5, 5, 5);
		title = BorderFactory.createTitledBorder(blackline,
				"Auswertung für Quartal");
		title.setTitleJustification(TitledBorder.LEFT);
		// Compound borders
		compound = BorderFactory.createCompoundBorder(empty, title);
		

		pane3.add(labelPane3, BorderLayout.CENTER);
		pane3.add(fieldPane3, BorderLayout.LINE_END);
		pane3.setBorder(compound);

		
		JPanel pane4 = new JPanel(new GridLayout(1, 0));

		// Bezeichnungspanel
		JPanel labelPane4 = new JPanel(new GridLayout(0, 1));
		labelPane4.add(new JLabel("Von (MM.DD.YYYY)"));
		labelPane4.add(new JLabel("Bis (MM.DD.YYYY)"));
		labelPane4.add(new JLabel(""));

		labelPane4.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));

		// Eingabefelderpanel
		JPanel fieldPane4 = new JPanel(new GridLayout(0, 1));
		fieldPane4.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
		fieldPane4.add(vonDatumF);
		fieldPane4.add(bisDatumF);
		fieldPane4.add(new JLabel(""));

		blackline = BorderFactory.createLineBorder(Color.black);
		empty = BorderFactory.createEmptyBorder(5, 5, 5, 5);
		title = BorderFactory.createTitledBorder(blackline,
				"Auswertung für Zeitabschnitt");
		title.setTitleJustification(TitledBorder.LEFT);
		// Compound borders
		compound = BorderFactory.createCompoundBorder(empty, title);

		pane4.add(labelPane4, BorderLayout.CENTER);
		pane4.add(fieldPane4, BorderLayout.LINE_END);
		pane4.setBorder(compound);
		
		add(pane1, BorderLayout.NORTH);
		add(pane2, BorderLayout.CENTER);
		add(pane5, BorderLayout.SOUTH);
		add(pane3, BorderLayout.AFTER_LAST_LINE);
		add(pane4, BorderLayout.AFTER_LAST_LINE);
		

	}

	/**
	 * Initialisiert die Combobox mit Kundendaten
	 * 
	 * @param kunden
	 */
	private void initCustomerBox(ArrayList<DimCustomer> customer) {
		if (customer == null)
			customer = new ArrayList<DimCustomer>();
		if (customer.size() > 0) {
			customerBox = new JComboBox(customer.toArray());
			customerBox.setSelectedIndex(0);
		} else
			customerBox = new JComboBox();
	}

	/**
	 * Initialisiert die Combobox mit Arktikeldaten
	 * 
	 * @param arktikel
	 */
	private void initProductBox(ArrayList<DimProduct> product) {
		if (product == null)
			product = new ArrayList<DimProduct>();
		if (product.size() > 0) {
			productBox = new JComboBox(product.toArray());
			productBox.setSelectedIndex(0);
		} else
			productBox = new JComboBox();
	}
	

	/**
	 * Initialisiert die Combobox mit Produktkategoriendaten
	 * 
	 * @param produktkategorien
	 */
	private void initProductCategoryBox(
			ArrayList<DimProductCategory> productcategory) {
		if (productcategory == null)
			productcategory = new ArrayList<DimProductCategory>();
		if (productcategory.size() > 0) {
			productcategoryBox = new JComboBox(productcategory.toArray());
			productcategoryBox.setSelectedIndex(0);
		} else
			productcategoryBox = new JComboBox();
	}
	
	// Initialisiert die Combobox mit Versandarten
	// @param Versandarten
	private void initShipping_methodBox(
			ArrayList<DimShipping_method> shipping_method) {
		if (shipping_method == null)
			shipping_method = new ArrayList<DimShipping_method>();
		if (shipping_method.size() > 0) {
			shipping_methodBox = new JComboBox(shipping_method.toArray());
			shipping_methodBox.setSelectedIndex(0);
		} else
			shipping_methodBox = new JComboBox();
	}
	
	// Initialisiert die Combobox mit Zahlungsarten
	// @param Zahlungsarten
		private void initPayment_methodBox(
				ArrayList<DimPayment_method> payment_method) {
			if (payment_method == null)
				payment_method = new ArrayList<DimPayment_method>();
			if (payment_method.size() > 0) {
				payment_methodBox = new JComboBox(payment_method.toArray());
				payment_methodBox.setSelectedIndex(0);
			} else
				payment_methodBox = new JComboBox();
		}
	
	/**
	 * Initialisiert die Combobox mit Jahren
	 * 
	 * @param Jahren
	 */
	private void initJahreBox(ArrayList<String> jahren) {
		if (jahren == null)
			jahren = new ArrayList<String>();
		if (jahren.size() > 0) {
			jahreBox = new JComboBox(jahren.toArray());
			jahreBox.setSelectedIndex(0);
		} else
			jahreBox = new JComboBox();
		jahreBox.addActionListener(this);
	}

	/**
	 * Liefert die Vorbelegung für das Beginndatum für die Auswertung
	 * 
	 * @param kunden
	 */
	private String getBeginnDatum(ArrayList<String> jahre) {
		if (jahre != null && jahre.size() > 0)
			return "01.01." + jahre.get(0);
		else
			return "";
	}

	/**
	 * Liefert die Vorbelegung für das Enddatum für die Auswertung
	 * 
	 * @param kunden
	 */
	private String getEndDatum(ArrayList<String> jahre) {
		if (jahre != null && jahre.size() > 0)
			return "31.12." + jahre.get(0);
		else
			return "";
	}

	/**
	 * Anzeige der Daten aktualisieren
	 */
	public void refresh(EventObject e) {
		vonDatumF.setDate(null);
		bisDatumF.setDate(null);
		quantityValueL.setText("");
		umsatzValueL.setText("");
		gewinnValueL.setText("");
		retourniertValueL.setText("");
		storniertValueL.setText("");
		anzahlBestellungL.setText("");
		if (customerBox.getSelectedIndex() > 0)
			customerBox.setSelectedIndex(0);
		if (productBox.getSelectedIndex() > 0)
			productBox.setSelectedIndex(0);
		if (productcategoryBox.getSelectedIndex() > 0)
			productcategoryBox.setSelectedIndex(0);
		if (shipping_methodBox.getSelectedIndex()> 0)
			shipping_methodBox.setSelectedIndex(0);
		if (payment_methodBox.getSelectedIndex()> 0)
			payment_methodBox.setSelectedIndex(0);
		if (jahreBox.getSelectedIndex() > 0)
			jahreBox.setSelectedIndex(0);
		quartal1CheckBox.setSelected(false);
		quartal2CheckBox.setSelected(false);
		quartal3CheckBox.setSelected(false);
		quartal4CheckBox.setSelected(false);
	}

	/**
	 * Liefert den Inhalt des Datumsfeldes Von
	 */
	public long getVonDatum() {
		if (vonDatumF != null)
			try {
				return vonDatumF.getDate().getTime();
			} catch (Exception e) {
				JOptionPane
						.showMessageDialog(
								new JFrame(),
								"Datum 'Von' ist falsch.\n\nBitte beachten Sie das angegebene Datumsformat!",
								"Fehler", JOptionPane.ERROR_MESSAGE);
			}
		return 0;
	}

	/**
	 * Liefert den Inhalt des Datumsfeldes Bis
	 */
	public long getBisDatum() {
		if (bisDatumF != null) {
			try {
				return bisDatumF.getDate().getTime();
			} catch (Exception e) {
				JOptionPane
						.showMessageDialog(
								new JFrame(),
								"Datum 'Bis' ist falsch.\n\nBitte beachten Sie das angegebene Datumsformat!",
								"Fehler", JOptionPane.ERROR_MESSAGE);
			}
		}
		return 0;
	}

	/**
	 * Liefert den selektierten Wert der Kundenummer
	 */
	public int getCustomer_id() {
		int customer_id = 0;
		if (customerBox != null)
			customer_id = ((DimCustomer) customerBox.getSelectedItem()).getCustomer_id();
		return customer_id;
	}

	/**
	 * Liefert den Wert des selektierten Artikels
	 */
	public int getProduct_id() {
		int Product_id = 0;
		if (productBox != null)
			Product_id = ((DimProduct) productBox.getSelectedItem()).getProduct_id();
		return Product_id;
	}
	

	/**
	 * Liefert den Wert der selektierten Produktkategorie
	 */
	public String getCategory_id() {
		String Category_id = null;
		if (productcategoryBox != null)
			Category_id = ((DimProductCategory) productcategoryBox.getSelectedItem()).getCategory_id();
		return Category_id;
	}
	

	/**
	 * Liefert den Wert des selektierten Versandart
	 */
	public String getShipping_method_id() {
		String Shipping_method_id = null;
		if (shipping_methodBox != null)
			Shipping_method_id = ((DimShipping_method) shipping_methodBox.getSelectedItem()).getShipping_method_id();
		return Shipping_method_id;
	}
	
	/**
	 * Liefert den Wert des selektierten Zahlungsart
	 */
	public String getPayment_method_id() {
		String Payment_method_id = null;
		if (payment_methodBox != null)
			Payment_method_id = ((DimPayment_method) payment_methodBox.getSelectedItem()).getPayment_method_id();
		return Payment_method_id;
	}

	/**
	 * Liefert den selektierten Jahr für die Quartalauswertung
	 */
	public int getJahr() {
		int jahr = 0;
		if (jahreBox != null)
			jahr = Integer.parseInt(jahreBox.getSelectedItem().toString());
		return jahr;
	}

	/**
	 * Liefert true, wenn der 1. Quartal ausgewählt ist.
	 */
	public boolean istQuartal1() {
		if (quartal1CheckBox != null)
			return quartal1CheckBox.isSelected();
		else
			return false;
	}

	/**
	 * Liefert true, wenn der 2. Quartal ausgewählt ist.
	 */
	public boolean istQuartal2() {
		if (quartal2CheckBox != null)
			return quartal2CheckBox.isSelected();
		else
			return false;
	}

	/**
	 * Liefert true, wenn der 3. Quartal ausgewählt ist.
	 */
	public boolean istQuartal3() {
		if (quartal3CheckBox != null)
			return quartal3CheckBox.isSelected();
		else
			return false;
	}

	/**
	 * Liefert true, wenn der 4. Quartal ausgewählt ist.
	 */
	public boolean istQuartal4() {
		if (quartal4CheckBox != null)
			return quartal4CheckBox.isSelected();
		else
			return false;
	}
	
	/**
	 * Setzt die Mende
	 */
	public void setQuantity(int quantity) {
		if (quantityValueL != null)
			quantityValueL.setText(String.valueOf(quantity));
	}

	/**
	 * Setzt die Umsatz
	 */
	public void setUmsatz(double umsatz) {
		if (umsatzValueL != null)
			umsatzValueL.setText(EuroFormatierung.getCurrencyFormat(umsatz));
	}
	/**
	 * Setzt die Gewinn
	 */
	
	public void setGewinn(double gewinn) {
		if (gewinnValueL != null)
			gewinnValueL.setText(EuroFormatierung.getCurrencyFormat(gewinn));
	}
	
	/**
	 * Setzt die Retourniert
	 */
	public void setRetourniert(int retourniert) {
		if (retourniertValueL != null)
			retourniertValueL.setText(String.valueOf(retourniert));
	}

	
	/**
	 * Setzt die Storniert
	 */
	public void setStorniert(int storniert) {
		if (storniertValueL != null)
			storniertValueL.setText(String.valueOf(storniert));
	}
	
	/**
	 * Setzt die Anzahl der Bestellungen
	 */
	
	public void setAnzahlBestellung(int anzahlBestellung) {
		if (anzahlBestellungL != null)
			anzahlBestellungL.setText(String.valueOf(anzahlBestellung));
		
	
	}



	

	/**
	 * Prüfen: Wenn mindestens ein Checkbox aktiviert ist, dann wird die Abfrage
	 * für Quartal durchgeführt. Wenn alle Checkbox unselektiert sind, dann wird
	 * die Abfrage für einen Zeitabschnitt stattfinden.
	 * 
	 * @param e
	 */
	public void itemStateChanged(ItemEvent e) {

		// Aktiviert die Datumsfelder
		pruefenDatumsFelder();
	}

	// Setzt Datumsfelder dann, wenn kein Quartal ausgewählt wurde.
	private void pruefenDatumsFelder() {
		if (!quartal1CheckBox.isSelected() && !quartal2CheckBox.isSelected()
				&& !quartal3CheckBox.isSelected() && !quartal4CheckBox.isSelected()) {
			int selectedJahr = getJahr();
			try {
			if (selectedJahr < 1) {
				vonDatumF.setDate(Datumformatierung.getDate(getBeginnDatum(jahre)));
				bisDatumF.setDate(Datumformatierung.getDate(getEndDatum(jahre)));
			} else {
				vonDatumF.setDate(Datumformatierung.getDate("01.01." + selectedJahr));
				bisDatumF.setDate(Datumformatierung.getDate("31.12." + selectedJahr));
			}
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else {
			vonDatumF.setDate(null);
			bisDatumF.setDate(null);
		}
	}

	/**
	 * Wenn ein Jahr gewählt wurde, dann werden die Uhrzeiten angepasst. Es wird
	 * geprüft, ob die Datumsfelder angezeigt werden sollen.
	 */
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == jahreBox) {
			pruefenDatumsFelder();
		}
	}

}
