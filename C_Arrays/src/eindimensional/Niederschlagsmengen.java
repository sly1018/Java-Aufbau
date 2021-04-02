package eindimensional;

import java.util.Arrays;
import java.util.Random;

public class Niederschlagsmengen {

	public static void main(String[] args) {
		// Niederschlagswerte in ein Array pro Monat

		double[] werte = new double[12];
		Random random = new Random();
		
		// int tmp = random.nextInt(45); // liefer Werte zwischen 0 und 45

		// an jedem Index einen Wert setzen
		for (int i = 0; i < werte.length; i++) {
			// Zufallswert holen
			double wert = random.nextDouble() * 100;
			// und im Array eintragen
			werte[i] = wert;
		}

		// alle Werte anzeigen
		for (double wert : werte) {
			System.out.printf("%.1f", wert);
		}

		System.out.println();

		// Monat mit dem wenigsten Niederschlag suchen

		int iMin = 0;

		// Wenn der neue aktuelle Wert kleiner ist -> neues Minumun merken
		for (int i = 0; i < werte.length; i++) {
			if (werte[i] < werte[iMin]) {
				iMin = i;
				if (werte[i] == 0) {
					break;
				}
			}
		}
		System.out.printf("Das Minimum war im Monat %d mit %.1f mm\n", iMin + 1, werte[iMin]);

		String[] monatsNamen = { "Jänner", "Februar", "März", "April", "Juni", "Juli", "August", "September", "Oktober",
				"November", "Dezember" };
		System.out.printf("Das Minimum war im %s mit %.1f mm\n", monatsNamen[iMin], werte[iMin]);

		
		// Hilfsklasse für Arrays
		// das Array sortieren
		Arrays.sort(werte);
		
		System.out.println(Arrays.toString(werte));
	}
}
