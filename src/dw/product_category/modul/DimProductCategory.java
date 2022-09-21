package dw.product_category.modul;

/**
 * Diese Klasse beschreibt ein Objekt vom Typ DimProductCategory im Data Warehouse.
 */
public class DimProductCategory {
  private String Category_id;
  private String Category_name;
  private String Category_description;
  private double Prozentualle_marge;
  private double Umsatzsteuer_anteil;

  /**
   * Konstruktor, der alle benötigten Attribute initialisiert.
   */
  public DimProductCategory(String Category_id, String Category_name, String Category_description,
		  					double Prozentualle_marge, double Umsatzsteuer_anteil) {
    this.Category_id = Category_id;
    this.Category_name = Category_name;
    this.Category_description = Category_description;
    this.Prozentualle_marge = Prozentualle_marge;
    this.Umsatzsteuer_anteil = Umsatzsteuer_anteil;
  }

  
  public String getCategory_id(){
  		return Category_id;
  }

 
  public String getCategory_name(){
    return Category_name;
  }
  
  public String getCategory_description(){
	    return Category_description;
	  }

  public double getProzentualle_marge(){
	    return Prozentualle_marge;
	  }

  public double getUmsatzsteuer_anteil(){
	    return Umsatzsteuer_anteil;
	  }


   /**
   * Die Methode toString wird überschrieben, so dass in der ComboBox nur
   *  der Category_id angezeigt wird.
   */
  public String toString() {
    if (Category_id != null)
     return Category_id
    		 + (Category_name != null ? " (" + Category_name + " )" : "" );
    else
      return super.toString();
  }

}
