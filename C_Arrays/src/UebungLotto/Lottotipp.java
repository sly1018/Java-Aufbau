package UebungLotto;

import java.util.Random;

public class Lottotipp {
	// Attribute
	private byte[] tippZahlen = new byte[6];

	public Lottotipp(byte[] t) {
		tippZahlen = t;
	}

	public Lottotipp() { // TODO: Implementiere mich.
	}

	public void quickTipp() {
		Random random = new Random();

		for (int i = 0; i < 6; i++) {
			int tipp = random.nextInt(45);
			tippZahlen[i] = (byte) tipp;
		}
	}

	public void mannuellerTipp(byte[] tz) {
		tippZahlen = tz;
	}

	public void ausgeben() {
		// Zahlen ausgeben
		for (int i = 0; i < tippZahlen.length; i++) {
			byte index = (byte) ((byte) i + 1);
			System.out.printf("%d. Zahl vom Tipp: %d\n", index, tippZahlen[i]);
		}
		System.out.println("\n");
	}

	public boolean gewinnPruefung(byte[] gewinnZahlen) {
		boolean gv = true;

		for (int i = 0; i < gewinnZahlen.length; i++) {
			if (tippZahlen[i] == gewinnZahlen[i]) {
				System.out.printf("Am Index %d mit der Zahl [%d] ist der Tipp [%d] richtig.\n", i + 1, gewinnZahlen[i], tippZahlen[i]);
				gv = true;
			} else {
				gv = false;
				System.out.printf("Am Index %d mit der Zahl [%d] ist der Tipp [%d] falsch.\n", i + 1, gewinnZahlen[i], tippZahlen[i]);
			}
		}
		return gv;
	}

	public byte[] getTippZahlen() {
		return tippZahlen;
	}

}
