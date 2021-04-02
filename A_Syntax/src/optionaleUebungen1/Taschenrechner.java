package optionaleUebungen1;

import java.util.Scanner;

public class Taschenrechner {

	public static int subtrahiere(int r, int z) {
		return (r - z);
	}

	public static int addiere(int r, int z) {
		return (r + z);
	}

	public static int multipliziere(int r, int z) {
		return (r * z);
	}

	public static int dividiere(int r, int z) {
		return (r / z);
	}

	public static void main(String[] args) {
		// Felder
		int zahl1;
		int resultat = 0;
		char operator;
		// Scanner Objekt für Eingabe
		Scanner eingabe = new Scanner(System.in);

		do {
			System.out.println("Bitte geben Sie einen Operator ein: ");
			operator = eingabe.next().charAt(0);

			if (operator != '=') {
				System.out.println("Bitte geben Sie eine Zahl ein: ");
				zahl1 = eingabe.nextInt();

				switch (operator) {
				case '-' -> resultat = subtrahiere(resultat, zahl1);
				case '+' -> resultat = addiere(resultat, zahl1);
				case '*' -> resultat = multipliziere(resultat, zahl1);
				case '/' -> resultat = dividiere(resultat, zahl1);
				}
			}
		} while (operator != '=');

		System.out.printf("Das Ergebnis betraegt: %d", resultat);
	}

}
