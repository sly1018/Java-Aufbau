package programm;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;

public class jdbcStatementsDemo {

	public static void main(String[] args) {
		testSimpleStatement();
	}

	static void testSimpleStatement() {
		String connUrl = "jdbc:mariadb://localhost/zoo";
		// Verbindung zur DB herstellen, dafür muss ein passender Treiber am
		// classpath/modulepath vorhanden sein
		try (Connection conn = DriverManager.getConnection(connUrl, "root", "")) {
			// Statement erzeugen
			Statement sqlCommand = conn.createStatement();
			// Einen Befehl ausführen
			ResultSet result = sqlCommand
					.executeQuery("select tierId, name, imZooSeit, pflanzenfresser, gewicht from tiere");
			System.out.println("Ergebnis der Abfrage: ");
			// Solange ein nächster Datensatz zu lesen ist
			while (result.next()) {
				// Die Werte dieses Datensatzes lesen
				// Variante 1: mit den Ordinal-Werten der Spalten
//				int tierId = result.getInt(1); // Den Wert aus Spalte 1 als int lesen
//				String name = result.getString(2); // Den Wert aus Spalte 2 als String lesen
//				LocalDate imZooSeit = result.getDate(3).toLocalDate(); // Den Wert aus Spalte 3 als Date lesen
//				boolean pflanzenfresser = result.getBoolean(4); // Den Wert aus Spalte 4 als Boolean lesen

				// Variante 2: mit Spaltennamen(sicherer!)
				int tierId = result.getInt("tierId"); // Den Wert aus Spalte tierId als int lesen
				String name = result.getString("name"); // Den Wert aus Spalte name als String lesen
				LocalDate imZooSeit = result.getDate("imZooSeit").toLocalDate(); // Den Wert aus Spalte imZooSeit als
																					// Date lesen
				boolean pflanzenfresser = result.getBoolean("pflanzenfresser"); // Den Wert aus Spalte pflanzenfresser
																				// als Boolean lesen
				Integer gewicht = result.getInt("gewicht");
				// Wenn der zuletzt gelesene Wert NULL war
				if (result.wasNull()) {
					// Dann den Wert mit null setzen
					gewicht = null;
				}

				System.out.printf("%s: id=%d, imZooseit=%s, Pflanzenfresser: %b, Gewicht: %s\n", name, tierId,
						imZooSeit, pflanzenfresser, gewicht != null ? gewicht + "kg" : "n.V.");
				
				// Ist eigentlich nicht nötig, weil das inderekt über das 
				// Schließen der Connection passiert
				result.close();
				sqlCommand.close();
			}
		} catch (SQLException e) {
			System.err.println("Fehler beim Abrufen der Daten:");
			// Den Fehler anzeigen
			e.printStackTrace();
		}
	}

}
