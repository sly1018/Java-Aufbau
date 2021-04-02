package listen.personen;

import java.util.ArrayList;
// import java.util.Iterator;
import java.util.List;

// BusinessLogic
public class PersonenGruppeListe implements PersonenGruppeItf {

	// Liste für belibig viele Personen
	private List<Person> gruppe = new ArrayList<>();

	@Override
	public void anmelden(Person p) {
		// Die Person am Ende einfügen.
		gruppe.add(p);
	}

	@Override
	public void anmelden(String name, int alter) {
		// Den anderen Overload aufrufen.
		anmelden(new Person(name, alter));
	}

	@Override
	public void alleAnzeigen() {
		System.out.printf("In der Gruppe sind %d Personen:\n", gruppe.size());

		// Mit foreach über alle Elemente iterieren und anzeigen
		for (Person person : gruppe) {
			System.out.printf("\t%s\n", person);
		}
	}

	@Override
	public void anzeigen(int nr) {
		// Den Index der gesuchen Person holen.
		int pIndex = personenIndex(nr);
		if (pIndex == -1) {
			// throw new IllegalArgumentException("Person mit Nummer " + nr + "existiert
			// nicht");
			System.out.printf("Person mit Nummer %d existiert nicht\n", nr);
		} else {
			Person p = gruppe.get(pIndex);
			System.out.printf("Person gefunden: %s\n", p);
		}
	}

	@Override
	public Person abmelden(int nr) {
		// Den Index der Person holen. int
		int pIndex = personenIndex(nr);
		if (pIndex < 0) {
			throw new IllegalArgumentException("Die Person mit der Nummer " + nr + " existiert nicht");
		}
		return gruppe.remove(pIndex);
	}

	public Person suchen(int nr) {
		// Den Index zur Nummer suchen
		int pIndex = personenIndex(nr);
		// Wenn vorhanden -> zurückliefern
		if (pIndex >= 0) {
			return gruppe.get(pIndex);
		}
		// Sonst: Fehler auslösen
		else {
			throw new IllegalArgumentException("Person mit der Nummer " + nr + "existiert nicht.");
		}
	}

//	@Override
//	public Person abmelden(int nr) {
//		// Variante 2: abmelden mit Iterator.
//		Iterator<Person> iterator = gruppe.iterator();
//		// Solange ein nächstes Element verfügbar ist.
//		while (iterator.hasNext()) {
//			// Das Element holen.
//			Person p = iterator.next();
//			// Wenn es die gesuchte Person ist.
//			if (p.getNr() == nr) {
//				// Entfernen und zurückliefern.
//				iterator.remove();
//				return p;
//			}
//		}
//		// Wenn wir hierher kommen, haben wir die Person nicht gefunden.
//		throw new IllegalArgumentException("Person mit Nummer " + nr + " existiert nicht.");
//	}

	private int personenIndex(int nr) {
		// Alle Elemente über den Index iterieren
		for (int i = 0; i < gruppe.size(); i++) {
			// Statt Index-Zugriff mit Operator[] (gruppe[i]
			// Mit gruppe.get(i) die Person an diesem Index holen
			Person p = gruppe.get(i);
			// Wenn die Nummer gleich der Nummer dieser Person ist.
			if (p.getNr() == nr) {
				return i;
			}
		}
		// Nummer kam nicht vor => -1 zurückliefern
		return -1;
	}

}
