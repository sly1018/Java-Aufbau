package optionaleUebungen1;

import java.util.Scanner;

public class PrimfaktorenZerlegung {

	public static void main(String[] args) {
		// Felder
		int zahl;
		int pz;
		Scanner eingabe = new Scanner(System.in);

		// Zuweisung von pz. Erste Primzahl
		pz = 2;

		System.out.printf("Bitte geben sie eine Zahl eine die Sie in Primfaktoren zerlegt sehen wollen: ");
		zahl = eingabe.nextInt();

		while (zahl > 1) {
			while ((zahl % pz) == 0) {
				zahl = zahl / pz;
				System.out.printf("%s ", pz);
				if (zahl != 1)
					System.out.printf(" * ");

			}
			pz++;
		}
	}

}
