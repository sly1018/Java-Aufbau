package mitarbeiterVerwaltung2;

import java.time.LocalDate;

/**
 * Diese Klasse kapselt eine Gruppe von Mitarbeiter-Objekte in einem Array
 * 
 * @author Sulaiman Khawar
 *
 */
public interface MitarbeiterListeItf {

	/**
	 * Fügt eine/-n MitarbeiterIn zu der Liste hinzu.
	 * 
	 * @param m Das Mitarbeiter-Objekt der hinzugefügt werden soll.
	 */
	void mitarbeiterHinzufuegen(Mitarbeiter m);

	/**
	 * Ein Objekt vom Typ Manager hinzufügen.
	 * 
	 * @param name     Name des/der Managers-/in.
	 * @param eintritt Eintrittsdatum des/der Mitarbeiters/-In.
	 * @param bonus    Bonus das dem/der Manager/-in zusteht.
	 */
	void managerHinzufuegen(String name, LocalDate gb, LocalDate eintritt, double gg ,double bonus);

	/**
	 * Ein Objekt vom Typ Experte hinzufügen.
	 * 
	 * @param name       Name des Objekts.
	 * @param eintritt   Eintrittsdatum des Objekts.
	 * @param fachgebiet Fachgebiet des Objekts.
	 */
	void experteHinzufuegen(String name, LocalDate gb, LocalDate eintritt, double gg, String fachgebiet);

	/**
	 * Eine Erhöhung der Gehälter aller in der Liste befindlichen Objekte.
	 * 
	 * @param pct Prozentsatz zur Erhöhung.
	 */
	void erhoehung(float pct);

	/**
	 * Eine Erhöhung eines Gehaltes von einem Mitarbeiter-Objekt.
	 * 
	 * @param maNummer Mitarbeiternummer.
	 * @param pct      Prozentsatz zur Erhöhung.
	 * @throws MitarbeiterException 
	 */
	void erhoehung(int maNummer, float pct) throws MitarbeiterException;

	/**
	 * Informationen eines Mitarbeiter-Objektes anzeigen.
	 * 
	 * @param maNummer Die Mitarbeiternummer zur Identifikation.
	 * @throws MitarbeiterException 
	 */
	void anzeigen(int maNummer) throws MitarbeiterException;

	/**
	 * Sortieren der Objekte nach den Namen der Mitarbeiter-Objekte.
	 */
	void sortiertNachName();

	/**
	 * Sortieren der Objekte in der Liste nach dem Typ der Mitarbeiter-Objekte.
	 */
	void sortiertNachTyp();

	/**
	 * Ein Objekt aus der Liste entfernen.
	 * 
	 * @param maNummer Die Mitarbeiternummer des Objekts zur Identifikation.
	 * @return Das abgemeldete Objekt für etwaige weitere Nutzung.
	 * @throws MitarbeiterException 
	 */
	Mitarbeiter abmelden(int maNummer) throws MitarbeiterException;

	/**
	 * Ein Mitarbeiter-Objekt in der Liste suchen.
	 * 
	 * @param maNummer Die Mitarbeiternummer zur Identifikation.
	 * @return Das Mitarbeiter-Objekt das gesucht wurde in der Liste.
	 * @throws MitarbeiterException 
	 */
	Mitarbeiter sucheMitarbeiter(int maNummer) throws MitarbeiterException;
}
