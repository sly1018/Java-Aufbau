package lottoNeuMichaela;

import java.util.Arrays;

public class LottoProgramm {

	public static void main(String[] args) {
		int[] gewinnzahlen = { 11, 12, 13, 14, 15, 16 };

		for (int i = 0; i < 10; i++) {

			Lottotipp tipp1 = new Lottotipp();
			tipp1.quicktipp();
			tipp1.ausgeben();

			int[] richtige = tipp1.gewinnPruefung(gewinnzahlen);
			System.out.println("Richtige: " + Arrays.toString(richtige));
		}
		
		Lottotipp tipp2 = new Lottotipp();
		tipp2.quicktipp();
		tipp2.ausgeben();
		
		// Das Array holen.
		int[] tippzahlen = tipp2.getTippZahlen();
		// Wert eines Elements ändern.
		tippzahlen[0]++;
		System.out.println("Kopie der Tippzahlen: " + Arrays.toString(tippzahlen));
		tipp2.ausgeben();

	}

}
