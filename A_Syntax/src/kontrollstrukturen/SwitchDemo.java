package kontrollstrukturen;

import java.util.Scanner;

public class SwitchDemo {
	// main
	public static void main(String[] args) {
		Scanner input = new Scanner(System.in);

		System.out.println("Welche Spielkarte?");
		String spielkarte = input.nextLine(); // Benutzer muss ENTER eingeben
		String kartenFarbe = getKartenFarbe_1(spielkarte);
		System.out.printf("Die Farbe der Spielkarte %s ist %s\n", spielkarte, kartenFarbe);
		
		kartenFarbe = getKartenFarbe_2(spielkarte);
		System.out.printf("Die Farbe der Spielkarte %s ist %s\n", spielkarte, kartenFarbe);
		
		// am ende von main
		input.close();
	}

	// test methoden
	static String getKartenFarbe_1(String karte) {
		String farbe;
		switch (karte) {
		case "Karo":
		case "Herz":
			farbe = "Rot";
			break;
		case "Pik":
		case "Treff":
			farbe = "Schwarz";
			break;
		default:
			// Ungültig -> Fehler auslösen, damit geht die Ausführung bei einem passen
			// catch-block weiter
			throw new IllegalArgumentException("Ungültige Karte: " + karte);
		// ab hier unreachable
		// break
		}
		// die Farbe zurückliefern, durch throw gültig initialisiert
		return farbe;
	}

	static String getKartenFarbe_2(String karte) {
		String farbe;
		switch (karte) {
		case "Karo", "Herz" -> farbe = "ROT"; // am ende kein break notwendig
		case "Pik", "Treff" -> farbe = "SCHWARZ";

		default -> throw new IllegalArgumentException("Ungültige Karte: " + karte);
		}
		return farbe;
	}

}
