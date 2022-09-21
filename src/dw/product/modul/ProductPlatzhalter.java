package dw.product.modul;

//Diese Klasse beschreibt ein Produkt

public class ProductPlatzhalter extends DimProduct {
	// Achtung, diese Nummer wird in der Procedure 'quartalAuswertung' vorausgesetzt!
		public static final int PRODUCT_ID = -100;

		/**
		 * Parameterloser Konstruktor, der den Konstruktor der Superklasse mit seinen Standardwerten aufruft
		 */
		public ProductPlatzhalter() {
			super(PRODUCT_ID, "- Alle Artikel -", "" , "" , 0 , 0 , 0 , 0 , 0 , 0 , 0);
		}


}
