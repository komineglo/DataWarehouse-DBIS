package dw.time.modul;

import dw.alle.modul.Datum;

/**
 * Diese Klasse beschreibt ein Objekt vom Typ DimZeit (Dimension Zeit) im Data Warehouse.
 *
 * @author Larissa Janssen
 * 
 */
public class DimZeit {

  private long nummer;
  private Datum datum;
  private int monat;
  private int quartal;
  private int jahr;

  /**
   * Konstruktor, der alle benötigten Attribute initialisiert.
   */
  public DimZeit(long nummer, long datum,
		  int monat, int quartal, int jahr) {
    this.nummer = nummer;
 	this.datum = new Datum(datum);
    this.monat = monat;
    this.quartal = quartal;
    this.jahr = jahr;
  }

  /**
   * Liefert den Monat zurück
   */
  public int getMonat(){
  		return monat;
  }

  /**
   * Liefert den Quartal zurück
   */
  public int getQuartal(){
    return quartal;
  }

  /**
   * Liefert das Jahr zurück
   */
  public int getJahr(){
    return jahr;
  }

  /**
   * Liefert das Datum zurück
   */
  public Datum getDatum(){
    return datum;
  }

  /**
   * Liefert die Nummer zurück
   */
  public long getNummer(){
    return nummer;
  }

}
