package timeApi;

import java.time.LocalDate;
import java.time.Period;
import java.time.temporal.ChronoUnit;
import java.util.Scanner;

public class LocalDateDemo {

	public static void main(String[] args) {
		// Das aktuelle Datum
		LocalDate heute = LocalDate.now();
		// Ein Datum aus den Einzel-Bestandteilen erstellen
		LocalDate gbDatum = LocalDate.of(2001, 6, 30);
		// Ein Datu aus einer Zeichenfolge im ISO-Format parsen
		Scanner eingabe = new Scanner(System.in);
		System.out.println("Bitte ein Datum eingeben (yyyy-MM-dd): ");
		String strDate = eingabe.nextLine();
		LocalDate eingabeDatum = LocalDate.parse(strDate);

		System.out.printf("Heute: %s, gbDatum: %s, eingabeDatum: %s\n", heute, gbDatum, eingabeDatum);

		// Zeitspanne zwischen 2 Datumswerte berechnen.
		// Variante 1: Period
		Period spanne1 = Period.between(gbDatum, heute);

		System.out.printf("Zwischen %s und %s liegen %d Jahre, %d Monate und %d Tage\n", gbDatum, heute,
				spanne1.getYears(), spanne1.getMonths(), spanne1.getDays());
		System.out.printf("Zwischen %s und %s liegt die Period %s\n", gbDatum, heute, spanne1);

		// Variante 2: ChronoUnit
		long tageDazwischen = ChronoUnit.DAYS.between(gbDatum, heute);
		System.out.printf("Zwischen %s und %s liegen %d Tage\n", gbDatum, heute, tageDazwischen);

		long monateDazwischen = ChronoUnit.MONTHS.between(gbDatum, heute);
		System.out.printf("Zwischen %s und %s liegen %d Monate\n", gbDatum, heute, monateDazwischen);

		long jahreDazwischen = ChronoUnit.YEARS.between(gbDatum, heute);
		System.out.printf("Zwischen %s und %s liegen %d Jahre\n", gbDatum, heute, jahreDazwischen);

		// Vergleichen wir Geburtstag und gebDatum2
		int ret = gbDatum.compareTo(eingabeDatum);
		// Das Ergebnis anzeigen(ist beim sortieren nicht nötig)
		if (ret < 0) {
			System.out.printf("%s ist kleiner als %s\n", gbDatum, eingabeDatum);
		} else if (ret > 0) {
			System.out.printf("%s ist größer als %s\n", gbDatum, eingabeDatum);
		} else {
			System.out.printf("%s und %s sind gleich\n", gbDatum, eingabeDatum);
		}

		eingabe.close();
	}

}
