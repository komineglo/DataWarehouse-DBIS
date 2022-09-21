package dw.alle.modul;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Properties;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

/**
 * Diese Klasse verwaltet alle Benutzersitzungen zu einer Datenbank mit identischen
 * Anmeldeinformationen.
 * 
 * @verfasser: Larissa Janssen
 */
public class DbConnectionZentrale {
	private Connection con;
	private String database;
	private String user;
	private String password;
	private String jndiServiceName;

	// Der JNDI-Server befindet sich (einfachheitshalber) im Unterverzeichnis des DW-Programms
	// Hier wird die JNDI-Informationsdatei .bindings erwartet
	private static String JNDI_SERVER_PATH = new String(System.getProperty("user.dir") + "/"
		+ "jndiServer").replace('\\', '/');

	// So sieht der vollständige Aufruf des JNDI-Services
	private static final String PROVIDER_URL_ADRESSE = "file:" + JNDI_SERVER_PATH;

	/**
	 * Konstruktor für DbConnectionZentrale
	 */
	public DbConnectionZentrale(String database, String user, String password, String jndiServiceName)
			throws SQLException, ClassNotFoundException {
		this.user = user;
		this.password = password;
		this.database = database;
		this.jndiServiceName = jndiServiceName;
	}

	/**
	 * Gibt den Schemanamen zurueck
	 */
	public String getSchemaname() {
		return "DBO";
	}

	/**
	 * Gibt die Connection zur Datenbank zurueck
	 */
	public synchronized Connection getConnection() throws SQLException,
			ClassNotFoundException {
		// Wenn der DbConnectionZentrale bereits freigegeben wurde,
		// dann können daraus keine Sessions bezogen werden.
		if (con == null || con.isClosed()) {
			try {
				// JNDI-Objekt bekommen
				DataSource dataSource = getJndiService(jndiServiceName);
				// Aus dem JNDI-Objekt eine Referenz auf die DB-Connection bekommen
				con = dataSource.getConnection(user, password);
				if (con != null)
					con.setCatalog(database);

			} catch (Exception e) {
				throw new ClassNotFoundException();
			}
		}
		return con;
	}

	/**
	 * Gibt ein Connection aus dem Pool wieder frei.
	 */
	public synchronized void freeConnection() {
		try {
			System.out.println("Active DB-Verbindung wird geschlossen... ");
			con.close();
			con = null;
		} catch (Exception e) {
		}
	}

	/**
	 * Liefert true, wenn die DB-Verbindung akzeptiert wird.
	 */
	public synchronized boolean testConnection() {
		try {
			// Die Verbindung hat geklappt.
			if (getConnection() != null)
				return true;
		} catch (Exception e) {
		}
		return false;
	}

	/**
	 * Liefert das entsprechende DataSource-Object für den übergebenen Servicenamen zurück.
	 */
	public DataSource getJndiService(String serviceName) throws SQLException,
			NamingException {
		DataSource sqlserverDS = null;
		try {
			// Angabe des Ortes der Jndi-Einträge oder des Jndi-Dienstes
			Properties properties = new Properties();
			properties.setProperty(Context.INITIAL_CONTEXT_FACTORY,
					"com.sun.jndi.fscontext.RefFSContextFactory");
			properties.setProperty(Context.PROVIDER_URL,
					PROVIDER_URL_ADRESSE);

			System.out.println(PROVIDER_URL_ADRESSE);
			// JNDI-Kontext wird erzeugt.
			Context jndiContext = new InitialContext(properties);
			sqlserverDS = (DataSource) jndiContext.lookup(serviceName);
		} catch (javax.naming.NameNotFoundException e) {
			System.out.print("Bitte erstellen Sie zuerst JNDI-Servecenamen! ");
			System.out.println("Starten Sie hierfür die Klasse StudentJndiService.");
		}
		return sqlserverDS;
	}

}
