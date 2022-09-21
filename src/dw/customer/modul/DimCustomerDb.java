package dw.customer.modul;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import dw.alle.modul.DbConnectionZentrale;

/**
 * Diese Persistent-Entityklasse dient zum Auslesen der Dimension im Data Warehouse.
 */
public class DimCustomerDb {

  public static final String MAINTABLE = "Dim_Customer";

  /**
   * Liefert alle Objekte aus der Dimension Kunde zurück.
   */
  public static java.util.ArrayList<DimCustomer> getAll(DbConnectionZentrale dbZentrale) throws
    CustomerDbException {

    PreparedStatement stmnt = null;
    ResultSet rs = null;
    java.util.ArrayList<DimCustomer> customerListe = new java.util.ArrayList<DimCustomer>();
    Connection connect = null;
    try {
      connect = dbZentrale.getConnection();
      stmnt = connect.prepareStatement(
          "SELECT * FROM " +
          dbZentrale.getSchemaname() +
          "." + MAINTABLE +
          " ORDER BY LASTNAME, FIRSTNAME");
      rs = stmnt.executeQuery();
      while (rs.next()) {
        DimCustomer customer = new DimCustomer(
          rs.getInt("Customer_id"),
          rs.getString("Lastname"),
          rs.getString("Firstname"),
          rs.getString("Gender"),
          rs.getString("Date_of_birth"),
          rs.getString("Street"),
          rs.getString("Postcode"),
          rs.getString("City"),
          rs.getString("Email")
          
          );
        customerListe.add(customer);
      }
    }
    catch (Exception e) {
      e.printStackTrace();
      throw new CustomerDbException();
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

    return customerListe;
  }
}
