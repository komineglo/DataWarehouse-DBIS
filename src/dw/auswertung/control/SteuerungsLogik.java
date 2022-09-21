package dw.auswertung.control;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

import dw.alle.view.FilterFenster;
import dw.auswertung.modul.AuswertungBusinessLogik;
import dw.auswertung.modul.FactOverviewDb;
import dw.auswertung.modul.AuswertungDbException;
import dw.auswertung.modul.FactOverview;
import dw.auswertung.view.AuswertungHauptfenster;

/**
 * Diese Klasse enthält die Logik 'Was passiert, wenn auf einen Button
 * im FilterFenster gedrueckt oder oben im Menu ein Menupunkt ausgewählt wurde.'
 */
class SteuerungsLogik {
  private AuswertungHauptfenster frame;

  private AuswertungBusinessLogik bussinesLogik;

  public SteuerungsLogik(AuswertungHauptfenster frame) {
    this.frame = frame;
    bussinesLogik = frame.getBusinessLogik();
  }

    /**
     * Aktualisiert die Anzahl der Bestellungen in dem Filterfenster.
     * Wenn für Shipping_method_idr oder Payment_method_id  der Wert der Platzhalter
     * übergeben wird, dann wird für den Parameter alle Werte ermittelt. 
     * @throws AuswertungDbException 
     */
  
  	public void auswertungStartenBestellung() throws AuswertungDbException {
  		
  		FilterFenster filterFenster = frame.getFilterFenster();
  		String Shipping_method_id = filterFenster.getShipping_method_id();
  		String Payment_method_id = filterFenster.getPayment_method_id();
  		
  	// Zuerst erfahren wir, ob eine Auswertung für Quartal erwünscht ist
  		boolean istQuartal1 = filterFenster.istQuartal1();
  		boolean istQuartal2 = filterFenster.istQuartal2();
  		boolean istQuartal3 = filterFenster.istQuartal3();
  		boolean istQuartal4 = filterFenster.istQuartal4();
  		
  		FactOverview auswertungsWerte = null;
  	// Es handelt sich um eine Auswertung für einen Quartal
    	if (istQuartal1 || istQuartal2 || istQuartal3 || istQuartal4) {
    		// Ausgewähltes Jahr ermitteln
        	int jahr = filterFenster.getJahr();
        	
        	auswertungsWerte = FactOverviewDb.auswertenBestellungQuartal(
        			bussinesLogik.getDbZentrale(),
        			Shipping_method_id,
        			Payment_method_id,
        			jahr, 
        			istQuartal1, 
        			istQuartal2,
        			istQuartal3, 
        			istQuartal4);
    		
    	} else {// Auswertung Anzahl der Bestellungen nach Versandarten 
    			//und/oder Zahlungsarten für einen Zeitabschnitt 
    		
    		long vonDatum = filterFenster.getVonDatum();
      		long bisDatum = filterFenster.getBisDatum();
      		 
        	auswertungsWerte = FactOverviewDb.auswertungBestellungZeitabschnitt(
        			bussinesLogik.getDbZentrale(), 
        			Shipping_method_id, 
        			Payment_method_id, 
        			vonDatum, 
        			bisDatum);
        	

    		
    	}
  		
        	filterFenster.setAnzahlBestellung(auswertungsWerte.getAnzahlBestellung());
  	}
  	
    /**
     * Aktualisiert die Menge, Umsatz, Gewinn, Anzahl der Retourniert und Anzahl der Storniert in dem Filterfenster.
     * Wenn für Customer_id, Product_id oder Category_id  der Wert der Platzhalter
     * übergeben wird, dann wird für den Parameter alle Werte ermittelt. 
     * @throws AuswertungDbException 
     */
    public void auswertungStarten() throws AuswertungDbException {
    	
    	FilterFenster filterFenster = frame.getFilterFenster();
    	
    	// Ausgewählte Einträge ermitteln
    	int Customer_id= filterFenster.getCustomer_id();
		int Product_id = filterFenster.getProduct_id();
		String Category_id = filterFenster.getCategory_id();
		
		// Zuerst erfahren wir, ob eine Auswertung für Quartal erwünscht ist
		boolean istQuartal1 = filterFenster.istQuartal1();
		boolean istQuartal2 = filterFenster.istQuartal2();
		boolean istQuartal3 = filterFenster.istQuartal3();
		boolean istQuartal4 = filterFenster.istQuartal4();

    	FactOverview auswertungsWerte = null;
    	
    	// Es handelt sich um eine Auswertung für einen Quartal
    	if (istQuartal1 || istQuartal2 || istQuartal3 || istQuartal4) {
    		// Ausgewähltes Jahr ermitteln
        	int jahr = filterFenster.getJahr();
        	
        	auswertungsWerte = FactOverviewDb.auswertenQuartal(
        			bussinesLogik.getDbZentrale(),
        			Customer_id, 
        			Product_id,
        			Category_id, 
        			jahr, 
        			istQuartal1, 
        			istQuartal2,
        			istQuartal3, 
        			istQuartal4);
    		
    	} else { // Auswertung für einen Zeitabschnitt 
        	long vonDatum = filterFenster.getVonDatum();
        	long bisDatum = filterFenster.getBisDatum();
        	
        	auswertungsWerte = FactOverviewDb.auswertenZeitabschnitt(
        			bussinesLogik.getDbZentrale(), 
        			Customer_id, 
        			Product_id,
        			Category_id, 
        			vonDatum, 
        			bisDatum);
    	}
    	

    	// Benutzungsoberfläche aktualisieren
      filterFenster.setQuantity(auswertungsWerte.getQuantity());
      filterFenster.setUmsatz(auswertungsWerte.getUmsatz());
      filterFenster.setGewinn(auswertungsWerte.getGewinn());
      filterFenster.setRetourniert(auswertungsWerte.getAnzahl_retourniert());
      filterFenster.setStorniert(auswertungsWerte.getAnzahl_storniert());

    }

    /**
     * Anzeigen der Programminformationen
     */
    public void getProgrammInfo() {
    // Programminformation
    JOptionPane.showMessageDialog(new JFrame(),
     "Modul: DW_AUSWERTUNG\n\nErsteller: Yusuf Solyman\nMatr.-Nr.: 6034059",
     "Info", JOptionPane.INFORMATION_MESSAGE);
  }

  /**
   * Programm beenden. Vor dem Beenden des Programms wird die
   * Datenbankverbindung freigegeben.
   */
  public void exit() {
    bussinesLogik.getDbZentrale().freeConnection();
    // Programm beenden
    System.exit(0);
  }


}
