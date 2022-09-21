package dw.auswertung.modul;

import dw.alle.modul.DbConnectionZentrale;

/**
 * Diese Klasse implementiert die Gesch�ftslogik
 * die beim �ndern, L�schen oder Hinzuf�gen einer Bestellung
 * angewendet wird. Ausserdem benachrichtigt diese Klasse alle
 * Beobachter, die sich f�r die Bestellungen�nderungen interessieren.
 * In diese Anwendung sind es Benutzungsoberfl�chenklassen:
 * KundeFenster und BestellungenTabelleFenster.
 * @author Larissa Janssen
 */
public class AuswertungBusinessLogik {

  private DbConnectionZentrale dbZentrale;

  public AuswertungBusinessLogik (DbConnectionZentrale dbZentrale){
    this.dbZentrale = dbZentrale;
  }

  /**
   * Liefert die Datenbankzentrale zur�ck.
   */
  public DbConnectionZentrale getDbZentrale() {
    return dbZentrale;
  }

  }
