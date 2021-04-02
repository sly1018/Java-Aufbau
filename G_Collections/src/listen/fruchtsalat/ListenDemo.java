package listen.fruchtsalat;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;

public class ListenDemo {

	static Scanner scanner = new Scanner(System.in);

	public static void main(String[] args) {
		// Implementierende Klasse kann dem Basisinterface zugewiesen werden,
		// wenn das Typargument übereinstimmt.
		List<String> liste1 = new ArrayList/* <String> */();
		macheFruchtsalat(liste1);
	}

	static void macheFruchtsalat(List<String> fruechte) {
		// Elemente am Ende der Liste hinzufügen
		fruechte.add("Banane");
		fruechte.add("Apfel");
		fruechte.add("Birne");
		fruechte.add("Kiwi");

		// Iteration über den Index
		for (int i = 0; i < fruechte.size(); i++) {
			// Das Element am Index holen
			String frucht = fruechte.get(i);
			System.out.printf("%d. Frucht: %s\n", i + 1, frucht);
		}

		System.out.println("Welche Frucht dazugeben?");
		String input = scanner.nextLine();

		// Einfügen wenn noch nicht drin
		if (fruechte.contains(input)) {
			System.out.println(input + "ist scho drin");
		} else {
			// Am Beginn hinzufügen, alle anderen werden nach hinten verschoben
			fruechte.add(0, input);
		}
		System.out.println("Alle Früchte: ");
		// Iteration mit foreach
		for (String frucht : fruechte) {
			System.out.println(frucht);
		}

		System.out.println("Welche Frucht möchtest du entfernen?");
		input = scanner.nextLine();
		boolean entfernt;
		// Entfernen Variante 1: mit remove -> der Vergleich wird mit equals vorgenommen
		if (fruechte.remove(input)) {
			System.out.println(input + "wurde entfernt");
			entfernt = true;
		} else {
			entfernt = false;
			System.out.println(input + "war nicht drin");
		}

		if (!entfernt) {
			// Entfernen der Variante 2: mit Iterator
			Iterator<String> iterator = fruechte.iterator();
			// Solange der Iterator ein weiteres Element zur Verfügung hat
			while (iterator.hasNext()) {
				// Auf dieses Element positionieren und es holen
				String frucht = iterator.next();

				// System.out.println(frucht);
				// Wenn Eingabe und Element gleich sind
				if (frucht.equalsIgnoreCase(input)) {
					// Das Element über den Iterator entfernen
					iterator.remove();
					System.out.println(frucht + "wurde entfernt");
				} else {
					System.out.println(frucht + "bleibt drin");
				}
			}
		}

		System.out.println("Früchte nach dem Löschen: ");
		for (String frucht : fruechte) {
			System.out.println(frucht);
		}

		Collections.sort(fruechte);

		// toString von Listenklasse
		System.out.println("Früchte sortiert: ");
		System.out.println(fruechte/* toString() */);
	}

}
