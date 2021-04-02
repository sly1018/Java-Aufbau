package personen;

public class PersonenMain2 {
	// die PersonenGruppe des Programms - ist static, dh es gibt nur eine Gruppe im
	// gesamten Programm
	private static PersonenGruppe gruppe1 = new PersonenGruppe();

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
			Person geloescht = gruppe1.abmelden(nr);
			System.out.printf("Person gelöscht: %s\n", geloescht.toString());
		} catch (Exception e) {
			System.out.println("Fehler beim abmelden: " + e.toString());
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
}
