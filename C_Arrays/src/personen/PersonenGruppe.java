package personen;

/**
 * Diese Klasse kapselt eine Gruppe von Personen-Objekten in einem Array
 * 
 * @author Sulaiman
 *
 */

public class PersonenGruppe {
	// Array für eine Personen-Gruppe
	private Person[] gruppe = new Person[5];
	// die Anzahl der angemeldeten Personen in der Gruppe
	private int anzahl = 0;

	/**
	 * 
	 * @param p die Person, die angemeldet werden soll
	 */
	public void anmelden(Person p) {
		// Wenn kein Platz mehr ist
		if (anzahl == gruppe.length) {
			throw new IllegalStateException("Die Gruppe ist schon voll!");
		}

		// die Person eintragen
		gruppe[anzahl++] = p;
	}

//	public void anmelden(String name, int alter) {
//		// neues Person Objekt erzeugen
//		Person p = new Person(name, alter);
//		// und anmelden
//		anmelden(p);
//	}
	
	/**
	 * Eine Person anmelden. Es wird eine Personobjekt erzeugt und im Array am
	 * nächsten freien Platz hinzugefügt
	 * @param name der Name der Person
	 * @param alter Das Alter der Person
	 */

	public void anmelden(String name, int alter) {
		anmelden(new Person(name, alter));
	}

	public void alleAnzeigen() {
		System.out.printf("In der Gruppe sind %d Personen:\n", anzahl);
		// Nur soviele Personen anzeigen wie angemeldet wurden
		for (int i = 0; i < anzahl; i++) {
			System.out.printf("\t%s\n", gruppe[i].toString());
		}
	}

	public void anzeigen(int nr) {
		int pIndex = personenIndex(nr);
		if (pIndex == -1) {
			System.out.printf("Person mit Nummer %d nicht vorhanden\n", nr);
		} else {
			Person p =  gruppe[pIndex];
			System.out.printf("Person gefunden: %s\n", p.toString());
		}
	}
	
	/**
	 * Eine Person abmelden. Die Person wird aus dem Array entfernt. Dahinterliegende Objekte werden
	 * nach vorne verschoben.
	 * @param nr Die Nummer der Person die abgemeldet wird
	 * @return	Das Person-Objekt
	 */

	public Person abmelden(int nr) {
		int pIndex = personenIndex(nr);
		// Nicht gefunden => Exception werfen
		if (pIndex == -1) {
			throw new IllegalArgumentException("Person mit Nummer " + nr + " nicht vorhanden!");
		}

		Person geloescht = gruppe[pIndex];
		// Die im Array dahinter liegenden um einen Platz nach vorne verschieben
		for (int i = pIndex + 1; i < anzahl; i++) {
			gruppe[i - 1] = gruppe[i];
		}

		// die Anzahl vermindern und das Element im Array initialisieren
		gruppe[--anzahl] = null;
		// Die gelöschte Person zurückliefern.
		return geloescht;
	}

	private int personenIndex(int nr) {
		// -1 bedeutet "nicht gefunden"
		// Im verwendeten Bereich suchen
		for (int i = 0; i < anzahl; i++) {
			// Wenn die Nummer passt
			if (gruppe[i].getNr() == nr) {
				return i;
			}
		}
		// -1 bedeutet nicht gefunden
		return -1;
	}
}
