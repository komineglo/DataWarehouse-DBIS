package dw.auswertung.modul;

import dw.alle.modul.DbConnectionZentrale;

/**
 * Diese Klasse implementiert die Geschäftslogik
 * die beim Ändern, Löschen oder Hinzufügen einer Bestellung
 * angewendet wird. Ausserdem benachrichtigt diese Klasse alle
 * Beobachter, die sich für die Bestellungenänderungen interessieren.
 * In diese Anwendung sind es Benutzungsoberflächenklassen:
 * KundeFenster und BestellungenTabelleFenster.
 * @author Larissa Janssen
 */
public class AuswertungBusinessLogik {

  private DbConnectionZentrale dbZentrale;

  public AuswertungBusinessLogik (DbConnectionZentrale dbZentrale){
    this.dbZentrale = dbZentrale;
  }

  /**
   * Liefert die Datenbankzentrale zurück.
   */
  public DbConnectionZentrale getDbZentrale() {
    return dbZentrale;
  }

  }
