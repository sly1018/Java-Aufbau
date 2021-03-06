package mitarbeiterVerwaltung2;

import java.time.LocalDate;

public class MitarbeiterInterfaceProgramm {

	private static MitarbeiterListeItf gruppe1 = new MitarbeiterListe();

	public static void main(String[] args) {
		gruppe1.mitarbeiterHinzufuegen(
				new Mitarbeiter("Roman", LocalDate.of(1978, 6, 24), LocalDate.of(2004, 8, 13), 3000));
		runAnzeigen(1);

		// Test der Exception und ob das Programm weiterläuft.
		runAnzeigen(99);

		gruppe1.managerHinzufuegen("Ana", LocalDate.of(1989, 2, 12), LocalDate.of(2009, 7, 21), 3000, 1000);
		runAnzeigen(2);

		gruppe1.experteHinzufuegen("Herbert", LocalDate.of(1955, 8, 8),LocalDate.of(1998, 6, 1), 2500 , "Orthopäde");
		runAnzeigen(3);

		System.out.println();
		System.out.println("Gehalt für alle Mitarbeiter-Objekte erhöhen und anzeigen.");
		gruppe1.erhoehung(1.13f);
		runAnzeigen(1);
		runAnzeigen(2);
		runAnzeigen(3);

		System.out.println();
		System.out.println("Gehalt und Bonus für Mitarbeiter 2 erhöhen und anzeigen.");

		runErhoehungFuerAlleMitarbeiter(2, 1.13f);
		runAnzeigen(2);

		System.out.println();
		System.out.println("Sortiert nach Name anzeigen:");
		gruppe1.sortiertNachName();

		System.out.println();
		System.out.println("Sortiert nach Typ und Eintrittsdatum anzeigen:");
		gruppe1.sortiertNachTyp();

		System.out.println();
		System.out.println("Mitarbeiter 3 löschen und alle an zeigen:");
		runAbmelden(3);
		gruppe1.sortiertNachName();
	}

	public static void runAnzeigen(int id) {
		try {
			gruppe1.anzeigen(id);
		} catch (MitarbeiterException e) {
			System.out.println("Fehler: " + e);
		}
	}

	public static void runErhoehungFuerAlleMitarbeiter(int maNummer, float pct) {
		try {
			gruppe1.erhoehung(maNummer, pct);
		} catch (Exception e) {
			System.out.println("Fehler: " + e);
		}
	}

	public static void runAbmelden(int id) {
		try {
			gruppe1.abmelden(id);
		} catch (Exception e) {
			System.out.println("Fehler: " + e);
		}
	}

}
