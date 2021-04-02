package enumPackage;

import java.util.Scanner;

public class EnumDemo {

	public static void main(String[] args) {
		System.out.printf("Unsere Kurstage sind %s und %s\n", Wochentage.MONTAG, Wochentage.DIENSTAG);
		teste(Wochentage.DIENSTAG);
		teste(Wochentage.SAMSTAG);
		
		Wochentage[] alleTage = Wochentage.values();
		for (int i = 0; i < alleTage.length; i++) {
			System.out.printf("Index %d: %s (ordinal=%d)\n", i, alleTage[i], alleTage[i].ordinal());
		}
		
		// Eine Zeichenfolge einlesen
		Scanner eingabe = new Scanner(System.in);
		System.out.println("Welcher Wochentag?");
		String strTage = eingabe.nextLine();
		// ... einen enum-wert daraus ermitteln (in Großbuchstaben)
		Wochentage wTag = Wochentage.valueOf(strTage.toUpperCase());
		teste(wTag);
		
		eingabe.close();
		
	}

	static void teste(Wochentage wTag) {
		System.out.printf("Wochentag %s: \n", wTag);
		switch (wTag) {
		case MONTAG, DIENSTAG -> System.out.println("Wir haben Java Kurs :)");

		default -> System.out.printf("An %s ist kein Java-Kurs\n", wTag);
		}
		
		if(wTag.isWochenende())
			System.out.println("Es ist Wochenende.");
		else
			System.out.println("Es ist ein Arbeitstag.");
		
		System.out.println("\n");
	}

}
