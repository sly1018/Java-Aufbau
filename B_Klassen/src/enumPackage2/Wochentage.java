package enumPackage2;

public enum Wochentage {
	// es gibt nur diese 7 Objekte, weitere können nicht erzeugt werden 
	// Die Referenzen sind alle public static final
	MONTAG(1), DIENSTAG(2), MITTWOCH(3), DONNERSTAG(4), FREITAG(5), SAMSTAG(6), SONNTAG(7);
	
	// Instanzattribut für die Nummer von jedem Objekt
	final private int tagesNummer;
	
	private Wochentage(int tNr) {
		tagesNummer = tNr;
	}
	
	public int getTagesNummer() {
		return tagesNummer;
	}
	
	public boolean isWochenende() {
		// Test ob das aktuelle Objekt ein Wochentag ist.
		switch (this) {
		// Für Samstag und Sonntag true liefer
		case SAMSTAG, SONNTAG: {
			return true;
		}
		// Alle anderen Tage false liefern
		default:
			return false;
		}
	}
}