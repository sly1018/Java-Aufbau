package zeitPackage;

public class Uhrzeit {
	// Attribute (Feld, Membervariable) für die Stunde und Minute
	// enthalten den Status eines Uhrzeit-Objekts
	private int m_stunde, m_minute; // m steht für member, privat nur die Klasse selber darauf zugreifen

	// Konstruktor
	public Uhrzeit(int stunde, int minute) {
		// an die setzen-Methode delegieren
		setzen(stunde, minute);
	}
	
	// Default Konstruktor
	public Uhrzeit() {
		// den anderen Konstruktor aufrufen
		this(12, 0);
	}

	// Methode um neue Werte ins Objekt zu schreiben
	public void setzen(int stunde, int minute) {
		if (stunde < 0 || stunde >= 24) {
//			System.out.println("Ungültiger Wert für die Stunde " + stunde);
//			return;
			throw new IllegalArgumentException("Ungültiger Wert für die Stunde " + stunde);
		}
		if (minute < 0 || minute >= 60) {
//			System.out.println("Ungültiger Wert für Minute " + minute);
//			return;
			throw new IllegalArgumentException("Ungültiger Wert für Minuten " + minute);
		}
		// die beiden Werte ins Objekt übernehmen
		m_stunde = stunde;
		m_minute = minute;
	}

	// die Uhrzeit an der Konsole anzeigen
	public void anzeigen() {
		System.out.printf("%02d:%02d", m_stunde, m_minute);
	}

	// Lesezugriff auf die Attribute erfolg über die Hilfsmethoden
	// get-Methode (=getter) für die Stunde
	public int getStunde() {
		return m_stunde;
	}

	// get-Methode für die Minuten
	public int getMinute() {
		return m_minute;
	}

	public int berechneDifferenz(Uhrzeit zeit2) {
		int minuten = m_stunde * 60 + m_minute;
		int minuten2 = zeit2.m_stunde * 60 + zeit2.m_minute;
		return minuten2 - minuten;
	}
}
