package dw.auswertung.modul;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Types;

import dw.alle.modul.DbConnectionZentrale;
import dw.product.modul.ProductPlatzhalter;
import dw.customer.modul.CustomerPlatzhalter;
import dw.payment_methode.modul.Payment_methodPlatzhalter;
import dw.product_category.modul.ProductCategoryPlatzhalter;
import dw.shipping_method.modul.Shipping_methodPlatzhalter;

/**
 * Diese Persistent-Entityklasse dient zum Auslesen der Faktentabelle im Data Warehouse.
 */
public class FactOverviewDb {

	/**
	 * Liefert das Ergebnis einer Auswertung für den ausgewählten Zeitabschnitt.
	 * Wenn für Shipping_method_id und Payment_method_id die Platzhalterwerte
	 * übergeben werden, dann wird für die Attribute kein Filter in der
	 * WHERE-Clausel hinzugefügt.
	 */
	public static FactOverview auswertungBestellungZeitabschnitt(
			DbConnectionZentrale dbZentrale,
			String Shipping_method_id,
			String Payment_method_id,
			long vonDatum,
			long bisDatum) 
					throws AuswertungDbException {
		
		PreparedStatement stmnt = null;
		ResultSet rs = null;
		
		Connection connect = null;
		
		try {
			connect = dbZentrale.getConnection();
			stmnt = connect.prepareStatement("SELECT COUNT(DISTINCT FO.ORDER_ID) AS ANZAHL_BESTELLUNGEN FROM "
											+ dbZentrale.getSchemaname()
											+ ".FACT_OVERVIEW FO, "
											+ dbZentrale.getSchemaname()
											+ ".DIM_TIME DT, "
											+ dbZentrale.getSchemaname()
											+ ".DIM_CUSTOMERORDER DCO "
											
											+ "WHERE "
											+ " DT.DATUM >= ? AND DT.DATUM <= ? "
											
											+ (Shipping_method_id != null
											&& !Shipping_method_id
													.equals(Shipping_methodPlatzhalter.SHIPPING_METHOD_ID) ? " AND DCO.SHIPPING_METHOD_ID = ? "
											: "")
										
											+ (Payment_method_id != null
											&& !Payment_method_id
													.equals(Payment_methodPlatzhalter.PAYMENT_METHOD_ID) ? " AND DCO.PAYMENT_METHOD_ID = ? "
											: "")
											
										
											// Join zwischen der Dimension Zeit und der
											// Fakt Tabelle
											+ " AND FO.ZEITNUMMER = DT.ZEITNUMMER"
											+ " AND FO.ORDER_ID = DCO.ORDER_ID "
											
					);
			
			stmnt.setDate(1, new Date(vonDatum));
			stmnt.setDate(2, new Date(bisDatum));
			// Weil die restlichen Platzhalter optional sind,
			// wird hier ein Index eigeführt.
			int idx = 3;

			if (Shipping_method_id != Shipping_methodPlatzhalter.SHIPPING_METHOD_ID) {
				stmnt.setString(idx, Shipping_method_id);
				idx++;
			}

			if ( Payment_method_id !=  Payment_methodPlatzhalter.PAYMENT_METHOD_ID) {
				stmnt.setString(idx, Payment_method_id);
				idx++;
			}
					
			rs = stmnt.executeQuery();
			if (rs.next()) {
				// Das Ergebnis wird sofort geliefert
				return new FactOverview(Shipping_method_id, Payment_method_id, 0,
						rs.getInt("ANZAHL_BESTELLUNGEN"));	
			
			} 
		}catch (Exception e) {
			e.printStackTrace();
			throw new AuswertungDbException();
		} finally {
			if (rs != null)
				try {
					rs.close();
				} catch (Exception ex) {
				}
			if (stmnt != null)
				try {
					stmnt.close();
				} catch (Exception ex) {
				}
			stmnt = null;

		}
		
		
		
		return new FactOverview(Shipping_method_id, Payment_method_id, 0, 0);
	}
	
	/**
	 * Zeigt wie eine Procedure (DW_QUARTALAUSWERTUNG_BESTELLUNG) in Java ausgelesen werden kann.
	 * Liefert das Ergebnis einer Auswertung für einen oder mehrere Quartale eines Jahres zurück.
	 * Wenn für Shipping_method_id und Payment_method_id die Platzhalterwerte
	 * übergeben werden, dann wird für die Attribute kein Filter in der
	 * WHERE-Clausel der Procedure hinzugefügt.
	 */
	
	public static FactOverview auswertenBestellungQuartal(
			DbConnectionZentrale dbZentrale,
			String Shipping_method_id,
			String Payment_method_id,
			int jahr,
			boolean istQuartal1,
			boolean istQuartal2,
			boolean istQuartal3,
			boolean istQuartal4)
			throws AuswertungDbException {
		CallableStatement stmnt = null;

		Connection connect = null;
		int Anzahl_bestellung = 0;
		try {
			connect = dbZentrale.getConnection();

			stmnt = connect
					.prepareCall("{ call DW_QUARTALAUSWERTUNG_BESTELLUNG(?,?,?,?,?,?,?,?) }");
			// OUT-Parameter registrieren
			
			stmnt.registerOutParameter(8, Types.INTEGER);

			// Werte setzen
			stmnt.setString(1, Shipping_method_id);
			stmnt.setString(2, Payment_method_id);
			stmnt.setInt(3, jahr);
			// Wenn 1. Quartal ausgewählt wird, dann die Zahl 1 übergeben,
			// ansonsten Zahl > 4
			stmnt.setInt(4, istQuartal1 ? 1 : 5);
			// Wenn 2. Quartal ausgewählt wird, dann die Zahl 2 übergeben,
			// ansonsten Zahl > 4
			stmnt.setInt(5, istQuartal2 ? 2 : 5);
			// Wenn 3. Quartal ausgewählt wird, dann die Zahl 3 übergeben,
			// ansonsten Zahl > 4
			stmnt.setInt(6, istQuartal3 ? 3 : 5);
			// Wenn 4. Quartal ausgewählt wird, dann die Zahl 4 übergeben,
			// ansonsten Zahl > 4
			stmnt.setInt(7, istQuartal4 ? 4 : 5);

			stmnt.execute();

			// Werte der Procedure auslesen
			
			Anzahl_bestellung = stmnt.getInt(8);
			
		} catch (Exception e) {
			e.printStackTrace();
			throw new AuswertungDbException();
		} finally {
			if (stmnt != null)
				try {
					stmnt.close();
				} catch (Exception ex) {
				}
			stmnt = null;

		}

		return new FactOverview(Shipping_method_id, Payment_method_id, 0 , Anzahl_bestellung);

	}
	
	/**
	 * Liefert das Ergebnis einer Auswertung für den ausgewählten Zeitabschnitt.
	 * Wenn für Customer_id, Product_id und Category_id die Platzhalterwerte
	 * übergeben werden, dann wird für die Attribute kein Filter in der
	 * WHERE-Clausel hinzugefügt.
	 */
	public static FactOverview auswertenZeitabschnitt(
			DbConnectionZentrale dbZentrale,
			int Customer_id,
			int Product_id,
			String Category_id,
			long vonDatum,
			long bisDatum)
			throws AuswertungDbException {
		PreparedStatement stmnt = null;
		ResultSet rs = null;

		Connection connect = null;
		try {
			connect = dbZentrale.getConnection();

			stmnt = connect
					.prepareStatement("SELECT SUM(QUANTITY) AS QUANTITY,"
							+ "		SUM(UMSATZ) AS UMSATZ,"
							+ "		SUM(GEWINN) AS GEWINN,"
							+ "		SUM(ANZAHL_RETOURNIERT) AS RETOURNIERT,"
							+ "		SUM(ANZAHL_STORNIERT) AS STORNIERT"
							+ " FROM "
							+ dbZentrale.getSchemaname()
							+ ".FACT_OVERVIEW FO, "
							+ dbZentrale.getSchemaname()
							+ ".DIM_TIME DT"
							// d.h. Wenn die Produktkategorie ausgewählt
							// wurde, ist die Verknüpfung zur Dimension Product notwendig 
							+ (Category_id != null
									&& !Category_id
											.equals(ProductCategoryPlatzhalter.CATEGORY_ID) ? 
													("," + dbZentrale.getSchemaname() + ".DIM_PRODUCT DP ") 
													
									: "")
							
							
							+ " WHERE"
							// Prüfen, ob der Filter gesetzt werden soll und
							// wenn ja,
							// dann setzten.
							+ " DT.DATUM >= ? AND DT.DATUM <= ?"

							// d.h. Wenn kein Kunde ausgewählt wurde, wird der
							// Filter ignoriert
							+ (Customer_id != 0 && Customer_id != CustomerPlatzhalter.CUSTOMER_ID ? " AND FO.CUSTOMER_ID = ? "
									: "")
							+ (Product_id != 0 && Product_id != ProductPlatzhalter.PRODUCT_ID ? " AND FO.PRODUCT_ID = ?"
									: "")
						
							// d.h. Wenn die Produktkategorie nicht ausgewählt
							// wurde, wird der Filter ignoriert
							+ (Category_id != null
									&& !Category_id
											.equals(ProductCategoryPlatzhalter.CATEGORY_ID) ? " AND DP.CATEGORY_ID = ? "
									: "")
							
				
						

							// Join zwischen der Dimension Zeit und der
							// Faktentabelle
							+ " AND FO.ZEITNUMMER = DT.ZEITNUMMER"
							// Join zwischen der Dimension Artikel und der
							// Faktentabelle. Ist nur dann notwendig, wenn die Dimension Dim_Artikel 
							// eingebunden ist.
							+ (Category_id != null
									&& !Category_id
											.equals(ProductCategoryPlatzhalter.CATEGORY_ID) ? 
													" AND FO.PRODUCT_ID = DP.PRODUCT_ID"
									: "")
							
							);

			stmnt.setDate(1, new Date(vonDatum));
			stmnt.setDate(2, new Date(bisDatum));
			// Weil die restlichen Platzhalter optional sind,
			// wird hier ein Index eigeführt.
			int idx = 3;

			if (Customer_id != CustomerPlatzhalter.CUSTOMER_ID) {
				stmnt.setInt(idx, Customer_id);
				idx++;
			}

			if (Product_id != ProductPlatzhalter.PRODUCT_ID) {
				stmnt.setInt(idx, Product_id);
				idx++;
			}
			
			
			if (Category_id != null
					&& !Category_id
							.equals(ProductCategoryPlatzhalter.CATEGORY_ID)) {
				stmnt.setString(idx, Category_id);
				idx++;
			}
			
			
			
		
			


			rs = stmnt.executeQuery();
			if (rs.next()) {
				// Das Ergebnis wird sofort geliefert
				return new FactOverview(Customer_id, Product_id, 0,  Category_id, 
						rs.getInt("QUANTITY"), rs.getDouble("UMSATZ"), rs.getDouble("GEWINN"),
						rs.getInt("RETOURNIERT"), rs.getInt("STORNIERT"));				

			}
		} catch (Exception e) {
			e.printStackTrace();
			throw new AuswertungDbException();
		} finally {
			if (rs != null)
				try {
					rs.close();
				} catch (Exception ex) {
				}
			if (stmnt != null)
				try {
					stmnt.close();
				} catch (Exception ex) {
				}
			stmnt = null;

		}

		// Wenn die Methode hier angekommen ist, dann heißt es, dass
		// keine Daten ermittelt werden konnten.
		return new FactOverview(Customer_id, Product_id, 0, Category_id ,0, 0, 0 , 0 , 0);

	}

	/**
	 * Zeigt wie eine Procedure (DW_QUARTALAUSWERTUNG) in Java ausgelesen werden kann.
	 * Liefert das Ergebnis einer Auswertung für einen oder mehrere Quartale eines Jahres zurück.
	 * Wenn für Customer_id, Product_id und Category_id die Platzhalterwerte
	 * übergeben werden, dann wird für die Attribute kein Filter in der
	 * WHERE-Clausel der Procedure hinzugefügt.
	 */
	public static FactOverview auswertenQuartal(
			DbConnectionZentrale dbZentrale,
			int Customer_id,
			int Product_id,
			String Category_id,
			int jahr,
			boolean istQuartal1,
			boolean istQuartal2,
			boolean istQuartal3,
			boolean istQuartal4)
			throws AuswertungDbException {
		CallableStatement stmnt = null;

		Connection connect = null;
		int Quantity = 0;
		double Umsatz = 0;
		double Gewinn = 0;
		int Anzahl_Retourniert= 0;
		int Anzhal_Storniert = 0;
		try {
			connect = dbZentrale.getConnection();

			stmnt = connect
					.prepareCall("{ call DW_QUARTALAUSWERTUNG(?,?,?,?,?,?,?,?,?,?,?,?,?) }");
			// OUT-Parameter registrieren
			
			stmnt.registerOutParameter(9, Types.INTEGER);
			stmnt.registerOutParameter(10, Types.DOUBLE);
			stmnt.registerOutParameter(11, Types.DOUBLE);
			stmnt.registerOutParameter(12, Types.INTEGER);
			stmnt.registerOutParameter(13, Types.INTEGER);

			// Werte setzen
			stmnt.setInt(1, Customer_id);
			stmnt.setInt(2, Product_id);
			stmnt.setString(3, Category_id);
			stmnt.setInt(4, jahr);
			// Wenn 1. Quartal ausgewählt wird, dann die Zahl 1 übergeben,
			// ansonsten Zahl > 4
			stmnt.setInt(5, istQuartal1 ? 1 : 5);
			// Wenn 2. Quartal ausgewählt wird, dann die Zahl 2 übergeben,
			// ansonsten Zahl > 4
			stmnt.setInt(6, istQuartal2 ? 2 : 5);
			// Wenn 3. Quartal ausgewählt wird, dann die Zahl 3 übergeben,
			// ansonsten Zahl > 4
			stmnt.setInt(7, istQuartal3 ? 3 : 5);
			// Wenn 4. Quartal ausgewählt wird, dann die Zahl 4 übergeben,
			// ansonsten Zahl > 4
			stmnt.setInt(8, istQuartal4 ? 4 : 5);

			stmnt.execute();

			// Werte der Procedure auslesen
			
			Quantity = stmnt.getInt(9);
			Umsatz = stmnt.getDouble(10);
			Gewinn = stmnt.getDouble(11);
			Anzahl_Retourniert = stmnt.getInt(12);
			Anzhal_Storniert = stmnt.getInt(13);

		} catch (Exception e) {
			e.printStackTrace();
			throw new AuswertungDbException();
		} finally {
			if (stmnt != null)
				try {
					stmnt.close();
				} catch (Exception ex) {
				}
			stmnt = null;

		}

		return new FactOverview(Customer_id, Product_id, 0,  Category_id, Quantity, Umsatz,
				Gewinn, Anzahl_Retourniert, Anzhal_Storniert);

	}
	
}
