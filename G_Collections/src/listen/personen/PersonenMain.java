package listen.personen;

public class PersonenMain {
	// die PersonenGruppe des Programms - ist static, dh es gibt nur eine Gruppe im
	// gesamten Programm
	// Die ursprüngliche Implementierung mit dem Array
	// private static PersonenGruppeItf gruppe1 = new PersonenGruppenArray();

	// Die neue Implementierung mit der Liste
	private static PersonenGruppeItf gruppe1 = new PersonenGruppeListe();

	public static void main(String[] args) {

		gruppe1.anmelden(new Person("Max", 10));
		gruppe1.anmelden(new Person("Moritz", 8));
		// den anderen Overload von anmelden aufrufen
		gruppe1.anmelden("Susi", 9);

		// Alle anzeigen
		gruppe1.alleAnzeigen();

//		gruppe1.anmelden("Karli", 9);
//		gruppe1.anmelden("Karo", 9);
//		gruppe1.anmelden("Kurti", 10);

		testeAnmeldung("Karli", 7);
		testeAnmeldung("Karo", 9);
		testeAnmeldung("Kurti", 10);

		gruppe1.alleAnzeigen();
		// Teste abmelden.
		gruppe1.anzeigen(3);
		testeAbmeldung(3);
		gruppe1.anzeigen(3);
		gruppe1.alleAnzeigen();

		// Person löschen die mehr vorhanden ist
		testeAbmeldung(3);

	}

	static void testeAbmeldung(int nr) {
		try {
			System.out.printf("Melde Person mit der Nr %d ab...\n", nr);
			Person geloescht = gruppe1.abmelden(nr);
			System.out.printf("...Person gelöscht: %s\n", geloescht.toString());
		} catch (Exception e) {
			System.out.println("...Fehler beim abmelden: " + e.toString());
		}
	}

	static void testeAnmeldung(String name, int alter) {
		try {
			// Die Methode ausführen in der ein Fehler auftreten kann
			gruppe1.anmelden(name, alter);
			System.out.printf("Person angemeldet: Name=%s\n", name);
		} catch (Exception e) {
			System.out.println("Fehler bei der Anmeldung: " + e.toString());
		}
	}

	// Analog testeAbmeldung

	static void testSuchen(int nr) {
		try {
			Person p = gruppe1.suchen(nr);
			System.out.printf("Person gefunden: Alter %d\n", p.getAlter());
		} catch (Exception e) {
			System.out.println("Fehler: Person nicht gefunden");
		}
	}
}
