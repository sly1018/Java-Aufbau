package enumPackage;

public enum Wochentage {
	// es gibt nur diese 7 Objekte, weitere können nicht erzeugt werden 
	// Die Referenzen sind alle public static final
	MONTAG, DIENSTAG, MITTWOCH, DONNERSTAG, FREITAG, SAMSTAG, SONNTAG;
	
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