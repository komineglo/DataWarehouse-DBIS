package dw.customer.modul;

/**
 * Diese Klasse beschreibt eine Kunde.
 * 
 */
public class CustomerPlatzhalter extends DimCustomer {
	// Achtung, diese Nummer wird in der Procedure 'quartalAuswertung' vorausgesetzt!
	public static final int CUSTOMER_ID = -100;

	/**
	 * Parameterloser Konstruktor, der den Konstruktor der Superklasse mit seinen Standardwerten aufruft
	 */
	public CustomerPlatzhalter() {
		super(CUSTOMER_ID, "- Alle Kunden -", null , null , null , null , null , null , null);
	}

}
