package maps.simpleMap;

import java.util.Map;
import java.util.Scanner;
import java.util.TreeMap;

public class MapDemo1 {

	static Scanner input = new Scanner(System.in);

	public static void main(String[] args) {

		// Zuordnung von Mitarbeiter-Nr zu Name des Mitarbeiters.
//		Map<Integer, String> alleMitarbeiter = new HashMap<>();
		Map<Integer, String> alleMitarbeiter = new TreeMap<>();

		// Einträge hinzufügen.
		alleMitarbeiter.put(15, "Peter Maier");
		alleMitarbeiter.put(51, "Micky Maus");
		alleMitarbeiter.put(24, "Kristiana Hansson");
		alleMitarbeiter.put(23, "Minnie Maus");

		printTable("Alle Mitarbeiter", alleMitarbeiter);

		// Mitarbeiter mit Nr 15 wurde falsch geschrieben -> ausbessern.
		// put ersetzt den Eintrag, wenn der Key schon drin ist
		alleMitarbeiter.put(15, "Peter Mayer");
		printTable("Alle Mitarbeiter nach der Korrektur", alleMitarbeiter);

		System.out.println("Welchen Mitarbeiter suchen?");

		Integer nr = input.nextInt();
		// Den Mitarbeiter holen.
		String maName = alleMitarbeiter.get(nr);
		// Wenn maName != null, gibt es ein Element mit dem Schlüssel
		if (maName != null) {
			System.out.println("Mitarbeiter gefunden: " + maName);
		} else {
			System.out.println("Mitarbeiter existiert nicht.");
		}

		// Mitarbeiter löschen
		System.out.println("Welchen Mitarbeiter löschen?");
		nr = input.nextInt();

		// Wenn die Nummer vorhanden ist -> löschen.
		if (alleMitarbeiter.containsKey(nr)) {
			alleMitarbeiter.remove(nr);
			System.out.printf("Mitarbeiter entfernt, die Map enthält jeetzt %d Einträge\n", alleMitarbeiter.size());
		} else {
			System.out.println("Mitarbeiter existiert nicht.");
		}

		printTable_Entries("Nach dem Löschen: ", alleMitarbeiter);

	}

	static void printTable(String text, Map<Integer, String> mitarbeiter) {
		System.out.println(text);

		// Überschrift
		System.out.println("Nr.   Name");
		// Für jedes Key-Element:
		for (Integer key : mitarbeiter.keySet()) {
			// Den Value dazu holen
			String maName = mitarbeiter.get(key);
			System.out.printf("%5d %s\n", key, maName);
		}

		System.out.println();
	}

	static void printTable_Entries(String text, Map<Integer, String> mitarbeiter) {
		System.out.println(text);

		// Überschrift
		System.out.println("Nr.   Name");

		// Für jeden Eintrag
		for (Map.Entry<Integer, String> entry : mitarbeiter.entrySet()) {
			Integer key = entry.getKey();
			String maName = entry.getValue();
			System.out.printf("%5d %s\n", key, maName);
		}

//		for (Integer key : mitarbeiter.keySet()) {
//			// Den Value dazu holen
//			String maName = mitarbeiter.get(key);
//			System.out.printf("%5d %s\n", key, maName);
//		}

		System.out.println();
	}

}
