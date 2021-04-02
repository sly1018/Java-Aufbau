package timeApi;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.util.Scanner;

public class LocalDateFormatierung {

	public static void main(String[] args) {
		LocalDate heute = LocalDate.now();
		// Für DE dd.MM.yyyy
		DateTimeFormatter fmt = DateTimeFormatter.ofLocalizedDate(FormatStyle.MEDIUM);
		// Zeichenfolge aus dem Datum erzeugen
		System.out.printf("Heute (toString): %s\n", heute);
		// Formatierte Zeichenfolge
		System.out.printf("Heute (formatter): %s\n", fmt.format(heute));

		Scanner eingabe = new Scanner(System.in);
		System.out.println("Gib bitte ein Datum ein: ");
		String strDate = eingabe.nextLine();
		// Aus der Zeichenfolge ein LocalDate-Objekt erzeugen.
		// Die Zeichenfolge muss zum Formatter passen.
		LocalDate date2 = LocalDate.parse(strDate, fmt);

		System.out.printf("Dein Datum (toString): %s\n", date2);
		// Formatierte Zeichenfolge
		System.out.printf("Dein Datum (formatter): %s\n", fmt.format(date2));
	}

}
