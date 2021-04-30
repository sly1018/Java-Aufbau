package sets.fruchtsalat;

import java.util.Collections;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Scanner;
import java.util.Set;
import java.util.TreeSet;

public class SetDemo_Fruchtsalat {

	static Scanner scanner = new Scanner(System.in);

	public static void main(String[] args) {
//		erzeugeFruchtsalat(new HashSet<>());
		// erzeugeFruchtsalat(new TreeSet<>());
		 erzeugeFruchtsalat(new TreeSet<>(String.CASE_INSENSITIVE_ORDER));
	}

	static void erzeugeFruchtsalat(Set<String> fruechte) {
		// Elemente einfügen
		// Elemente am Ende der Liste hinzufügen
		fruechte.add("Banane");
		fruechte.add("Apfel");
		fruechte.add("Birne");
		fruechte.add("Kiwi");

		for (String frucht : fruechte) {
			System.out.println(frucht);
		}

		System.out.println("Welche Frucht dazugeben?");
		String input = scanner.nextLine();
		
		if(fruechte.contains(input)) {
			System.out.printf("%s ist drin\n", input);
		} else {
			System.out.printf("%s ist nicht drin\n", input);
		}
		
		
		if (fruechte.add(input)) {
			System.out.printf("%s wurde hinzugefügt\n", input);
		} else {
			System.out.printf("%s war schon drin\n", input);
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

		// Sortieren des Set ist nicht möglich - entweder ist es ungeordnet oder bereits sortiert
		//Collections.sort(fruechte);

		// toString der Set-Klasse
		System.out.println("Früchte sortiert: ");
		System.out.println(fruechte/* toString() */);

	}

}
