package dw.time.modul;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import dw.alle.modul.DbConnectionZentrale;

/**
 * Diese Persistent-Entityklasse dient zum Auslesen der Dimension Time im Data Warehouse.
 * @author Larissa Janssen
 */
public class DimZeitDb {

	private static final String MAINTABLE = "DIM_TIME";

  /**
   * Liefert alle Jahre aus der Dimension Zeit zurück.
   */
	public static ArrayList<String> getAllJahre(DbConnectionZentrale dbZentrale)
			throws ZeitDbException {
		PreparedStatement stmnt = null;
		ResultSet rs = null;
		ArrayList<String> jahreListe = new ArrayList<String>();
		Connection connect = null;
		try {
			connect = dbZentrale.getConnection();

			stmnt = connect
					.prepareStatement("SELECT DISTINCT JAHR FROM "
							+ dbZentrale.getSchemaname()
							+ "."
							+ MAINTABLE
							+ " ORDER BY JAHR");

			rs = stmnt.executeQuery();
			while (rs.next()) {
				jahreListe.add(rs.getString("JAHR"));
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw new ZeitDbException();
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

		return jahreListe;

	}
}
