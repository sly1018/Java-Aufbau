package bankVerwaltung;

/**
 * Diese Klasse kapselt eine Gruppe von BankKonto-Objekten in einem Array
 * 
 * @author Sulaiman
 *
 */
public class BankVerwaltung {

	private BankKonto3[] konten = new BankKonto3[5];
	private int anzahl = 0;

	/**
	 * Bankkonten werden dem Array hinzugefügt.
	 * 
	 * @param bk das Bankkonto, dass hinzugefügt werden soll.
	 */
	public int hinzufuegen(BankKonto3 bk) {
		if (anzahl == konten.length) {
			throw new IllegalStateException("Anzahl möglicher Konten ausgeschöpft.");
		}
		konten[anzahl++] = bk;
		System.out.printf("Konto mit der Kontonummer : %d hinzugefügt.\n", bk.getKontoNummer());
		return bk.getKontoNummer();
	}

	/**
	 * Jedesmal wenn ein Konto hinzugefügt wird, werden alle Konten in dieser Gruppe
	 * angezeigt.
	 */

	public void alleAnzeigen() {
		System.out.printf("Folgende %d Bankkonten sind dabei: \n", anzahl);
		for (int i = 0; i < anzahl; i++) {
			System.out.printf("\t%s\n", konten[i].toString());
		}
		System.out.println();
	}

	/**
	 * 
	 * @param kn Die Kontonummer vom Konto, dass geschlossen werden soll.
	 * @return Das gelöschte Konto wird zurückgegeben.
	 */
	public BankKonto3 schliessen(int kn) {
		int bIndex = kontenIndex(kn);
		double auszahlungsBetrag;

		if (bIndex == -1) {
			throw new IllegalArgumentException("Bankkonto mit der Nummer " + kn + " nicht vorhanden.");
		}

		BankKonto3 geloescht = konten[bIndex];
		auszahlungsBetrag = konten[bIndex].getKontoStand();
		System.out.printf("\nKontostand von Konto %d: %.2f\n", konten[bIndex].getKontoNummer(),auszahlungsBetrag);
		System.out.printf("Abzüglich 5€ Bearbeitungsgebühr wird die Auszahlung durchgeführt.\n");
		konten[bIndex].abheben(auszahlungsBetrag-5.0);
		
		for (int i = bIndex + 1; i < anzahl; i++) {
			konten[i - 1] = konten[i];
		}
		konten[--anzahl] = null;

		return geloescht;
	}
	
	public void ueberweisen(int von, int zu, double betrag) {
		int vonIndex = kontenIndex(von);
		int zuIndex = kontenIndex(zu);
		
		BankKonto3 abzuhebendesKonto = konten[vonIndex];
		BankKonto3 zuUeberweisendesKonto = konten[zuIndex];
		
		abzuhebendesKonto.abheben(betrag);
		zuUeberweisendesKonto.einzahlen(betrag);
	}
	

	/**
	 * Ermittelt das passende Konto im Bereich.
	 * 
	 * @param nr Die Kontonummer
	 * @return Der Index oder -1 für nicht gefunden.
	 */

	private int kontenIndex(int nr) {
		for (int i = 0; i < anzahl; i++) {
			if (konten[i].getKontoNummer() == nr) {
				return i;
			}
		}
		return -1;
	}

}
