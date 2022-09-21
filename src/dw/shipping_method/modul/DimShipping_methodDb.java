package dw.shipping_method.modul;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import dw.alle.modul.DbConnectionZentrale;

public class DimShipping_methodDb {
	
	public static final String MAINTABLE = "FLAKE_SHIPPING_METHOD";
	
	public static java.util.ArrayList<DimShipping_method> getAll(DbConnectionZentrale dbZentrale) throws
	Shipping_methodDbException {

    PreparedStatement stmnt = null;
    ResultSet rs = null;
    java.util.ArrayList<DimShipping_method> shipping_methodListe = new java.util.ArrayList<DimShipping_method>();
    Connection connect = null;
    try {
      connect = dbZentrale.getConnection();
      stmnt = connect.prepareStatement(
          "SELECT * FROM " +
          dbZentrale.getSchemaname() +
          "." + MAINTABLE +
          " ORDER BY SHIPPING_METHOD_ID");
      rs = stmnt.executeQuery();
     
      while (rs.next()) {
    	  DimShipping_method shipping_method = new DimShipping_method(
          rs.getString("Shipping_method_id"),
          rs.getString("Shipping_method_description"),
          rs.getDouble("Shipping_fee")
          
          );
    	  
    	  shipping_methodListe.add(shipping_method);
   	      }
   	    }
   	
   	    catch (Exception e) {
   	      e.printStackTrace();
   	      throw new Shipping_methodDbException();
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

   	    return shipping_methodListe;
   	  }	
    }


