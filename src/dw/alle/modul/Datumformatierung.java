package dw.alle.modul;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Locale;
import java.util.TimeZone;

/**
 * Hilfsklasse für die Formatierung der Felder, Umwandlung in ein anderes Format;
 * und zur Hilfestellung für die Behandlung von aufgetretenen Datenbankfehlern.
 *
 * @author Larissa Janssen
 * 
 */
public class Datumformatierung {

  /**
   * Liefert das Datum im Format "dd.MM.yyyy" zurück.
   * @param time long
   * @return String
   */
  public static String getDdMMyyyy(long time) {
    java.util.Date d = new java.util.Date(time);
    TimeZone z = TimeZone.getDefault();

    Calendar c = Calendar.getInstance(z, Locale.GERMANY);
    SimpleDateFormat sd = new SimpleDateFormat("dd.MM.yyyy");
    sd.setCalendar(c);
    return sd.format(d);
  }

  /**
   * Wandelt Datum in einen Longwert.
   */
  public static long getDdMMyyyy(String sDate) throws ParseException {
    if ( (sDate == null) || sDate.trim().equals("")) {
      return 0;
    }
    SimpleDateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy");
    GregorianCalendar date = new GregorianCalendar();
    date.setTime(dateFormat.parse(sDate));
    return date.getTime().getTime();
  }

  /**
   * Wandelt String in ein Datum.
   */
  public static Date getDate(String sDate) throws ParseException {
    if ( (sDate == null) || sDate.trim().equals("")) {
      return null;
    }
    SimpleDateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy");
    GregorianCalendar date = new GregorianCalendar();
    date.setTime(dateFormat.parse(sDate));
    return date.getTime();
  }

  /**
   * Wandel Date-Objekt in einen String im Format 'YYYY-MM-DD'
   */
    public static String getYyyyMMdd(java.sql.Date time) {
      TimeZone z = TimeZone.getDefault();

      Calendar c = Calendar.getInstance(z, Locale.GERMANY);
      SimpleDateFormat sd = new SimpleDateFormat("yyyy-MM-dd");
      sd.setCalendar(c);
      return sd.format(time);
    }

    /**
     * Wandelt Long-Objekt in eine Date-Objekt
     */
    public static java.sql.Date getDate(long time) {
      return new java.sql.Date(time);
    }
}
