package dw.payment_methode.modul;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import dw.alle.modul.DbConnectionZentrale;

public class DimPayment_methodDb {
	
public static final String MAINTABLE = "FLAKE_PAYMENT_METHOD";
	
	public static java.util.ArrayList<DimPayment_method> getAll(DbConnectionZentrale dbZentrale) throws
	Payment_methodDbException {

    PreparedStatement stmnt = null;
    ResultSet rs = null;
    java.util.ArrayList<DimPayment_method> payment_methodListe = new java.util.ArrayList<DimPayment_method>();
    Connection connect = null;
    try {
      connect = dbZentrale.getConnection();
      stmnt = connect.prepareStatement(
          "SELECT * FROM " +
          dbZentrale.getSchemaname() +
          "." + MAINTABLE +
          " ORDER BY PAYMENT_METHOD_ID");
      rs = stmnt.executeQuery();
     
      while (rs.next()) {
    	  DimPayment_method payment_method = new DimPayment_method(
          rs.getString("Payment_method_id"),
          rs.getString("Payment_description")
          );
    	  
    	  payment_methodListe.add(payment_method);
   	      }
   	    }
   	
   	    catch (Exception e) {
   	      e.printStackTrace();
   	      throw new Payment_methodDbException();
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

   	    return payment_methodListe;
   	  }	

}
