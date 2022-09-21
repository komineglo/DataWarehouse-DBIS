package dw.alle.modul;

import dw.alle.modul.Datumformatierung;

/**
 * Klasse, die für alle Datumsattribute verwendet werden kann.
 * @verfasser:    Larissa Janssen
 */
public class Datum implements Cloneable {
  public final static long NO_TIME = 0;
  private long date = NO_TIME;

  /**
   * Konstruktor zum Erzeugen eines Datumsobjektes.
   */
  public Datum(long date) {
    this.date = date;
  }

  /**
   * Liefert Datum als long-Wert zurück.
   */
  public long getAsLong(){
    return date;
  }

  /**
   * Liefert Datum als String-Wert im angegebenen Format zurück.
   */
  public String getDdMMyyyy(){
    return Datumformatierung.getDdMMyyyy(date);
  }

  /**
   * Setzt ein neues Datum.
   */
  public void setDate(long date){
    this.date = date;
  }

  /**
   * Implementierung der Methode clone() des Interfaces Clonable
   */
  public Object clone() throws CloneNotSupportedException {
    return super.clone();
  }

}
