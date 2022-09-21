package dw.product.modul;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import dw.alle.modul.DbConnectionZentrale;

	/**
	 * Diese Persistent-Entityklasse dient zum Auslesen der Dimension Product im Data Warehouse.
	 */
public class DimProductDb {

	  public static final String MAINTABLE = "DIM_PRODUCT";

	  /**
	   * Liefert alle Objekte aus der Dimension Product zurück.
	   */
	  public static java.util.ArrayList<DimProduct> getAll(DbConnectionZentrale dbZentrale) throws
	    ProductDbException {

	    PreparedStatement stmnt = null;
	    ResultSet rs = null;
	    java.util.ArrayList<DimProduct> productListe = new java.util.ArrayList<DimProduct>();
	    Connection connect = null;
	    try {
	      connect = dbZentrale.getConnection();
	      stmnt = connect.prepareStatement(
	          "SELECT * FROM " +
	          dbZentrale.getSchemaname() +
	          "." + MAINTABLE +
	          " ORDER BY PRODUCT_NAME");
	      rs = stmnt.executeQuery();
	     
	      while (rs.next()) {
	    	  DimProduct product = new DimProduct(
	          rs.getInt("Product_id"),
	          rs.getString("Product_name"),
	          rs.getString("Product_description"),
	          rs.getString("Manufacturer"),
	          rs.getFloat("Product_length"),
	          rs.getFloat("Product_width"),
	          rs.getFloat("Product_height"),
	          rs.getFloat("Product_weight"),
	          rs.getDouble("Produkt_Brutto_Preis"),
	          rs.getDouble("Produkt_Netto_Preis"),
	          rs.getDouble("Erzielte_Gewinn_Pro_Produkt")
	          
	          );
	        productListe.add(product);
	      }
	    }
	
	    catch (Exception e) {
	      e.printStackTrace();
	      throw new ProductDbException();
	    }
	    finally {
	      if (rs != null)
	        try {
	          rs.close();
	        }
	        catch (Exception ex) {
	        }
	      if (stmnt != null)
	        try {
	          stmnt.close();
	        }
	        catch (Exception ex) {
	        }
	        stmnt = null;

	    }

	    return productListe;
	  }
	


}
