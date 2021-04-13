package maps.treeMaps;

import java.util.Scanner;

// Für die Verwendung in TreeMap -> Comparable implementieren
public class PersonalNr implements Comparable<PersonalNr> {
	private final int abteilung, nummer;

	public PersonalNr(int abteilung, int nr) {
		this.abteilung = abteilung;
		this.nummer = nr;
	}

	// getter für Abteilung
	public int getAbteilung() {
		return abteilung;
	}

	// getter für Nummer
	public int getNummer() {
		return nummer;
	}

	@Override
	public String toString() {
		return String.format("(%d-%d)", abteilung, nummer);
	}

	// Comparable-Implementierung
	@Override
	public int compareTo(PersonalNr o) {
		if (o == null) {
			return 1;
		}
		// Zuerst nach Abteilungs-Nummer
		int ret = Integer.compare(this.abteilung, o.abteilung);
		if (ret == 0) {
			// Dann nach Nummer sortieren
			ret = Integer.compare(this.nummer, o.nummer);
		}

		return ret;
	}

}
