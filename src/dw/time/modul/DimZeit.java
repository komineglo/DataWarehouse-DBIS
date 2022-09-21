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
   * Konstruktor, der alle ben�tigten Attribute initialisiert.
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
   * Liefert den Monat zur�ck
   */
  public int getMonat(){
  		return monat;
  }

  /**
   * Liefert den Quartal zur�ck
   */
  public int getQuartal(){
    return quartal;
  }

  /**
   * Liefert das Jahr zur�ck
   */
  public int getJahr(){
    return jahr;
  }

  /**
   * Liefert das Datum zur�ck
   */
  public Datum getDatum(){
    return datum;
  }

  /**
   * Liefert die Nummer zur�ck
   */
  public long getNummer(){
    return nummer;
  }

}
