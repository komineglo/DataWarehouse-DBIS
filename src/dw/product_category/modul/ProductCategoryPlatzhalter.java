package dw.product_category.modul;

/**
 * Diese Klasse beschreibt eine Produktkategorie.
 */
public class ProductCategoryPlatzhalter extends DimProductCategory {
	// Achtung, diese Bezeichnung wird in der Procedure 'quartalAuswertung' vorausgesetzt!
	public static final String CATEGORY_ID = "";

	/**
	 * Parameterloser Konstruktor, der den Konstruktor der Superklasse mit seinen Standardwerten aufruft
	 */
	public ProductCategoryPlatzhalter() {
		super(CATEGORY_ID, "- Alle Produktkategorien -" , "" , 0, 0 );
	}

}
