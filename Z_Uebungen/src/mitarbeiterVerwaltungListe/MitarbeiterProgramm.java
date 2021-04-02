package mitarbeiterVerwaltungListe;

import java.time.LocalDate;
import java.util.Arrays;

public class MitarbeiterProgramm {

	public static void main(String[] args) {

		MitarbeiterGruppeItf[] angestellte = new MitarbeiterGruppeItf[] {
				new Mitarbeiter("Lana", LocalDate.of(1989, 2, 26), LocalDate.of(2009, 7, 21), 2500.0),
				new Mitarbeiter("Sergio", LocalDate.of(1989, 3, 11), LocalDate.of(2018, 9, 23), 2700.0),
				new Manager("Andrea", LocalDate.of(1979, 5, 19), LocalDate.of(2020, 8, 1), 10000.0, 5000.0),
				new Experte("Fabio", LocalDate.of(1972, 6, 13), LocalDate.of(2010, 7, 1), 20000.0, "Scouting") };

		System.out.println();
		
		zeigeMitarbeiter("Unsortiert", angestellte);
		System.out.println();
		testeSonstigeMethoden(angestellte);
		
		System.out.println("Sortierung\n");
		Arrays.sort(angestellte);
		zeigeMitarbeiter("Sortiert", angestellte);
		
		
		
	}
	
	static void zeigeMitarbeiter(String info, MitarbeiterGruppeItf[] alleMitarbeiter) {
		System.out.println(info);
		for (MitarbeiterGruppeItf mitarbeiter : alleMitarbeiter) {
			System.out.printf("%s\n", mitarbeiter.holeMitarbeiterInfo());
		}
	}
	
	static void testeSonstigeMethoden(MitarbeiterGruppeItf[] angestellte) {
		for (int i = 0; i < angestellte.length; i++) {
			System.out.printf("Seit %d Tagen im Betrieb tätig.\n", angestellte[i].berechneAnstellungsDauer());
			System.out.printf("Das Jahresgehalt von %s beträgt %.2f\n", angestellte[i].getName(),
					angestellte[i].berechneJahresGehalt());
			angestellte[i].erhoeheGehalt(1.6f);
			System.out.printf("Nach der Gehaltserhöhung beträgt das neue Monatsgehalt von Mitarbeiter %s: %.2f\n",
					angestellte[i].getName(), angestellte[i].berechneMonatsGehalt());
			System.out.println();
		}
	}
}
