package dw.product_category.modul;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import dw.alle.modul.DbConnectionZentrale;

/**
 * Diese Persistent-Entityklasse dient zum Auslesen der Dimension ProductCategory im Data Warehouse.
 */
public class DimProductCategoryDb {

  public static final String MAINTABLE = "FLAKE_PRODUCT_CATEGORY";

  /**
   * Liefert alle Objekte aus der Dimension Produktkategorie zurück.
   */
  public static java.util.ArrayList<DimProductCategory> getAll(DbConnectionZentrale dbZentrale) throws
    ProductCategoryDbException {

    PreparedStatement stmnt = null;
    ResultSet rs = null;
    java.util.ArrayList<DimProductCategory> productcategoryListe = new java.util.ArrayList<DimProductCategory>();
    Connection connect = null;
    try {
      connect = dbZentrale.getConnection();
      stmnt = connect.prepareStatement(
          "SELECT * FROM " +
          dbZentrale.getSchemaname() +
          "." + MAINTABLE +
          " ORDER BY CATEGORY_ID");
      rs = stmnt.executeQuery();
      while (rs.next()) {
        DimProductCategory productcategory = new DimProductCategory(
          rs.getString("Category_id"),
          rs.getString("Category_name"),
          rs.getString("Category_description"),
          rs.getDouble("Prozentualle_marge"),
          rs.getDouble("Umsatzsteuer_anteil")
          );
        productcategoryListe.add(productcategory);
      }
    }
    catch (Exception e) {
      e.printStackTrace();
      throw new ProductCategoryDbException();
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

    return productcategoryListe;
  }
}
