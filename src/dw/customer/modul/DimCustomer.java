package dw.customer.modul;

/**
 * Diese Klasse beschreibt ein Objekt vom Typ DimCustomer im Data Warehouse
 */
public class DimCustomer {
  private int Customer_id;
  private String Lastname;
  private String Firstname;
  private String Gender;
  private String Date_of_birth;
  private String Street;
  private String Postcode;
  private String City;
  private String Email;

  /**
   * Konstruktor, der alle benötigten Attribute initialisiert.
   */
  public DimCustomer(int Customer_id , String Lastname, String Firstname, String Gender, String Date_of_birth,
		  String Street, String Postcode, String City, String Email) {
    this.Customer_id = Customer_id;
    this.Lastname = Lastname;
    this.Firstname = Firstname;
    this.Gender = Gender;
    this.Date_of_birth = Date_of_birth;
    this.Street = Street;
    this.Postcode = Postcode;
    this.City = City;
    this.Email = Email;
  }

  /**
   * Liefert den Nachnamen zurück
   */
  public int getCustomer_id(){
  		return Customer_id;
  }
  
  public String getLastname(){
		return Lastname;
}

  
  public String getFirstname(){
		return Firstname;
}

  
  public String getGender(){
		return Gender;
}

  
  public String getDate_of_birth(){
		return Date_of_birth;
}

  
  public String getStreet(){
		return Street;
}

  
  public String getPostcode(){
		return Postcode;
}
  
  public String getCity(){
		return City;
}
  
  public String getEmail(){
		return Email;
}



   /**
   * Die Methode toString wird überschrieben, so dass in der ComboBox nur
   * der Nachname der Kunde angezeigt wird.
   */
  public String toString() {
    if (Lastname != null)
    	// Beim Platzhalter sollen der Vornahme und die Nummer unsichtbar bleiben
     return Lastname 
       + (Firstname != null ? ", " + Firstname : "") 
       + (Customer_id > 0 ? " (Customer_ID: " + Customer_id + ")" : "");
    else
      return super.toString();
  }

}
