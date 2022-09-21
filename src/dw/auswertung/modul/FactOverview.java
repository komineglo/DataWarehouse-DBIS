package dw.auswertung.modul;

/**
 * Diese Klasse beschreibt die Tabelle Fact_Overview, die alle zu auswertende 
 * bzw. die zur Auswertung relevante Attribute beinhaltet.
 * 
 */
public class FactOverview {

  private int Customer_id;
  private int Product_id;
  private int Zeitnummer;
  private String Category_id;
  private String Shipping_method_id;
  private String Payment_method_id;
  private int Quantity;
  private double Umsatz;
  private double Gewinn;
  private int Anzahl_retourniert;
  private int Anzahl_storniert;
  private int Anzahl_Bestellung;
  /**
   * Konstruktor, der alle benötigten Attribute initialisiert, der in 
   * diesem Fall für Auswertung von Menge, Umsatz, Gewinn, Anzahl_retourniert und Anzahl_storniert
   * nach Kundenauswahl, Artikeln und/oder Produktkategorien verwendent wird.
   */
  public FactOverview(int Customer_id , int Product_id , int Zeitnummer , String Category_id, 
		  int Quantity, double Umsatz , double Gewinn, int Anzahl_retourniert, int Anzahl_storniert) {
    this.Customer_id = Customer_id;
    this.Product_id = Product_id;
    this.Zeitnummer = Zeitnummer; 
    this.Category_id = Category_id;
    this.Quantity = Quantity;
    this.Umsatz = Umsatz;
    this.Gewinn = Gewinn;
    this.Anzahl_retourniert = Anzahl_retourniert;
    this.Anzahl_storniert = Anzahl_storniert;
    
  }
  /*
   * Konstruktor, der alle benötigten Attribute initialisiert, der in disem Fall für 
   * Bestimmung der Anzahl von Bestellungen nach Versandarten und/oder Zahlungsarten bedient.
   */
  public FactOverview(String Shipping_method_id,String Payment_method_id, int Zeitnummer, int Anzahl_Bestellung) {
	  this.Shipping_method_id =Shipping_method_id;
	  this.Payment_method_id = Payment_method_id;
	  this.Zeitnummer = Zeitnummer ;
	  this.Anzahl_Bestellung = Anzahl_Bestellung;
	  
  }
  
 

  
  public int getCustomer_id(){
  		return Customer_id;
  }
  
  public int getProduct_id(){
		return Product_id;
}
  
  
  
  public int getZeitnummer(){
		return Zeitnummer;
}
  
  
  public String getCategory_id(){
		return Category_id;
}
  
  public String getShipping_method_id(){
		return Shipping_method_id;
}
  public String getPayment_method_id() {
	  
	  return Payment_method_id;
  }
  
  public int getQuantity(){
		return Quantity;
}
  
  public double getUmsatz(){
		return Umsatz;
}
  
  public double getGewinn(){
		return Gewinn;
}
  
  public int getAnzahl_retourniert(){
		return Anzahl_retourniert;
}
  
  public int getAnzahl_storniert(){
		return Anzahl_storniert;
}

  public int getAnzahlBestellung() {
	  
	  return Anzahl_Bestellung;
  }
}
