package uebung1;

import java.util.Scanner;

public class Tagesnummer {

	public static int getMonatTage(int m, boolean sj) {
		int reValue = 0;

		switch (m) {
		case 1, 3, 5, 7, 8, 10, 12 -> reValue = 31;
		case 2 -> {
			if (sj)
				reValue = 29;
			else
				reValue = 28;
		}
		case 4, 6, 9, 11 -> reValue = 30;
		}
		return reValue;
	}

	public static boolean istSchaltjahr(int j) {
		boolean rueWert;
		// schaltjahr ist es wenn ohne rest durch 4 teilbar, durch 100 nicht teilbar
		// oder durch 400 teilbar
		if ((j % 4 == 0) && ((j % 100 != 0) || (j % 400 == 0)))
			rueWert = true;
		else
			rueWert = false;

		return rueWert;
	}

	public static void main(String[] args) {

		// fields
		int restTage;
		int monat = 1;
		int monatsTage;
		int jahr;
		boolean schaltJahr;

		Scanner input = new Scanner(System.in);

		// benutzer eingabe jahr, 4 Stellen zulässig
		System.out.println("Bitte geben Sie ein Jahr ein:");
		jahr = input.nextInt();
		schaltJahr = istSchaltjahr(jahr);

		if (schaltJahr)
			System.out.println("Bitte geben sie die Tage ein, zwischen 1 und 366.");
		else
			System.out.println("Bitte geben sie die Tage ein, zwischen 1 und 365.");

		restTage = input.nextInt();

		monatsTage = getMonatTage(1, schaltJahr);

		while (restTage > monatsTage) {

			restTage = restTage - monatsTage;
			monat++;
			monatsTage = getMonatTage(monat, schaltJahr);

		}

		System.out.println(restTage + "." + monat);

	}

}
