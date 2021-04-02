package zeitPackage;

import java.util.InputMismatchException;
import java.util.Scanner;

public class UhrzeitProgramm2 {

	// Benutzer darf Werte eingeben
	private static Scanner eingabe = new Scanner(System.in);

	public static void main(String[] args) {
		Uhrzeit beginn, ende;
		beginn = zeitEinlesen("Eingabe Beginnzeit");
		ende = zeitEinlesen("Eingabe Endezeit");

		System.out.println("Dauer von ");
		beginn.anzeigen();
		System.out.print(" bis ");
		ende.anzeigen();
		
		// Dauer berechnen
		int dauer = beginn.berechneDifferenz(ende);
		System.out.printf("\nDauer: %d Minuten\n", dauer);
	}

	static Uhrzeit zeitEinlesen(String info) {
		System.err.println(info);

		try {
			int s, m;
			System.out.print("Stunde: ");
			s = eingabe.nextInt();
			System.out.print("Minute: ");
			m = eingabe.nextInt();
			// den Eingabepuffer leeren
			eingabe.nextLine();

			// den "spezialisierten Kontruktor verwenden
			Uhrzeit zeit = new Uhrzeit(s, m);
			// zeit.setzen(s, m);
			return zeit;

		} catch (InputMismatchException e) {
			System.out.println("Es ist ein Fehler aufgetreten.");
			eingabe.nextLine();
			// die Methode rekursiv noch einmal aufrufen
			return zeitEinlesen(info);

		} catch (IllegalArgumentException e) {
			System.out.println("Es ist ein Fehler aufgetreten: " + e.getMessage());
			eingabe.nextLine();
			// die Methode rekursiv noch einmal aufrufen
			return zeitEinlesen(info);
		}
	}

}
