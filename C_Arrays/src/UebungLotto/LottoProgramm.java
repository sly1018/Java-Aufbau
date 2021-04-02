package UebungLotto;

import java.util.Scanner;

public class LottoProgramm {

	/**
	 * Holt die 6 Tippzahlen vom Benutzer.
	 * 
	 * @return Ein Byte Array aus 6 Werten.
	 */

	public static byte[] zahlenHolen() {

		byte zahlen[] = new byte[6];

		Scanner eingabe = new Scanner(System.in);

		try {
			for (int i = 0; i < 6; i++) {
				System.out.printf("Geben Sie die %d. Zahl ihres Tipps ein: ", i + 1);
				byte tmpZahl = eingabe.nextByte();
				if (tmpZahl <= 45)
					zahlen[i] = tmpZahl;
				else {
					System.out.printf("Die Zahl %d ist nicht gültig.\n", tmpZahl);
					throw new IllegalArgumentException("Fehler im System. Schalte aus.");
				}
			}
		} catch (IllegalArgumentException e) {
			System.out.println("Es ist ein Fehler aufgetreten: " + e.getMessage());
			return zahlenHolen();
		}

		System.out.println("\n");

		return zahlen;
	}
	
	public static void mitteillen(boolean gn) {
		if (gn) {
			System.out.println("Sie haben gewonnen!");
		} else
			System.out.println("Das war leider nix.");
	}

	public static void main(String[] args) {
		Lottotipp tipp1 = new Lottotipp();
		Lottotipp tipp2 = new Lottotipp();
		Lottotipp lottoZahlen = new Lottotipp();

		System.out.println("Die Gewinnzahlen: ");
		lottoZahlen.quickTipp();
		lottoZahlen.ausgeben();

		tipp1.quickTipp();
		System.out.println("Quick Tipp: ");
		tipp1.ausgeben();

		System.out.println("Manueller Tipp: ");
		tipp2.mannuellerTipp(zahlenHolen());
		tipp2.ausgeben();

		// Gewinnprüfung
		System.out.println("Quicktipp Gewinnüberprüfung: ");
		mitteillen(tipp1.gewinnPruefung(lottoZahlen.getTippZahlen()));
		System.out.println();
		System.out.println("Manueller Tipp Gewinnüberprüfung: ");
		mitteillen(tipp2.gewinnPruefung(lottoZahlen.getTippZahlen()));

	}
}
