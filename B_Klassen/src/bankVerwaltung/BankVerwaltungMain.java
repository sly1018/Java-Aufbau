package bankVerwaltung;

import java.util.Scanner;

public class BankVerwaltungMain {

	static BankKonto3 benutzerEingabeKonto() {
		BankKonto3 rueWert;
		Scanner eingabe = new Scanner(System.in);
		double ea;
		String name;

		System.out.println("Wollen Sie ein Gehaltskonto (1) oder ein Sparkonto (2) eröffnen?");
		int kontoTyp = eingabe.nextInt();

		switch (kontoTyp) {
		case 1 -> {
			System.out.println("Geben Sie folgende Daten ein: Erstbetrag, Name von Kontoinhaber, Rahmen");
			ea = eingabe.nextDouble();
			name = eingabe.next();
			double r = eingabe.nextDouble();
			rueWert = new BankKonto3(ea, name, r);
		}
		case 2 -> {
			System.out.println(
					"Geben Sie folgende Daten ein: Erstbetrag, Name des Kontoinhabers und den Zinssatz(mit Komma): ");
			ea = eingabe.nextDouble();
			name = eingabe.next();
			float i = eingabe.nextFloat();
			rueWert = new BankKonto3(ea, name, i);
		}

		default -> throw new IllegalArgumentException("Unexpected value: " + kontoTyp);
		}

		return rueWert;
	}

	static int holeUserEingabe() {
		int rueWert;
		Scanner eingabe = new Scanner(System.in);

		System.out.println("Geben Sie ein welche Funktion ausgeführt werden soll: \n");
		System.out.println(
				"Funktionen: 1-Kontos anzeigen, 2-Konto hinzufügen, 3-Konto schließen, 4-Überweisen, 5-Automatischer Test, 6-Verlassen:");
		rueWert = eingabe.nextInt();

		return rueWert;
	}

	static int menueAnzeigen() {
		Scanner eingabe = new Scanner(System.in);
		int benutzerEingabe = holeUserEingabe();

		switch (benutzerEingabe) {
		case 1 -> kontos1.alleAnzeigen();

		case 2 -> kontos1.hinzufuegen(benutzerEingabeKonto());

		case 3 -> {
			System.out.println("Bitte geben Sie die Kontonummer von dem Konto an, dass schließen wollen");
			kontos1.schliessen(eingabe.nextInt());
		}
		case 4 -> {
			System.out.println("Geben Sie die betroffenen Konten ein und den Betrag, der überwiesen werden soll.");
			int srcKonto = eingabe.nextInt();
			int dstKonto = eingabe.nextInt();
			double betrag = eingabe.nextDouble();
			kontos1.ueberweisen(srcKonto, dstKonto, betrag);
		}
		case 5 -> autoTesten();

		}

		return benutzerEingabe;
	}

	private static BankVerwaltung kontos1 = new BankVerwaltung();

	public static void main(String[] args) {
		int benutzerEingabe;
		do {
			benutzerEingabe = menueAnzeigen();
		} while (benutzerEingabe != 6);
		System.out.println("Auf Wiedersehen!");
	}

	static void autoTesten() {
		kontos1.hinzufuegen(new BankKonto3(400.0, "Hans", 0.04f)); // Sparkonto
		kontos1.hinzufuegen(new BankKonto3(2500.0, "Michele", 1000.0)); // Gehaltskonto
		kontos1.alleAnzeigen();

		System.out.println("\nEin Betrag von 200 wird von Konto 2 (Gehaltskonto) auf Konto 1 (Sparkonto) überwiesen.");
		kontos1.ueberweisen(2, 1, 200);
		kontos1.alleAnzeigen();

		// Konto 1 wird gechlossen.
		System.out.println("\n");
		System.out.println("Schließen von Konto 1 wird durchgeführt...");
		kontos1.schliessen(1);
		kontos1.alleAnzeigen();

		System.out.println("\n");
		testeHinzufuegen(595.0, "Lana", 0.04f);
		kontos1.alleAnzeigen();
		testeUeberweisen(2, 3, 300.00);
		kontos1.alleAnzeigen();
		System.out.println("\nKontoschließung wird durchgeführt...");
		testeSchliessen(3);
		System.out.println("\n");
		kontos1.alleAnzeigen();

		kontos1.hinzufuegen(benutzerEingabeKonto());
		kontos1.alleAnzeigen();
	}

	static void testeSchliessen(int nr) {
		try {
			BankKonto3 geschlossen = kontos1.schliessen(nr);
			System.out.printf("Geschlossenes Konto: %s\n", geschlossen.toString());
		} catch (Exception e) {
			System.out.println("Fehler beim schließen: " + e.toString());
		}
	}

	static void testeHinzufuegen(double ea, String n, float i) {
		try {
			int kn = kontos1.hinzufuegen(new BankKonto3(ea, n, i));
			System.out.printf("Konto mit der Kontonummer %d hinzugefügt.\n", kn);
		} catch (Exception e) {
			System.out.println("Fehler beim hinzufüen " + e.toString());
		}
	}

	static void testeUeberweisen(int von, int zu, double betrag) {
		try {
			kontos1.ueberweisen(von, zu, betrag);
		} catch (Exception e) {
			System.out.println("Fehler beim überweisen: " + e.toString());
		}
	}

}
