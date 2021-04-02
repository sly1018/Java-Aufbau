package mitarbeiterVerwaltung;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.Comparator;

public class MitarbeiterProgramm {

	public static void main(String[] args) {

		Mitarbeiter[] angestellte = new Mitarbeiter[] {
				new Mitarbeiter("Lana", LocalDate.of(1989, 2, 26), LocalDate.of(2009, 7, 21), 2500.0),
				new Mitarbeiter("Sergio", LocalDate.of(1989, 3, 11), LocalDate.of(2018, 9, 23), 2700.0),
				new Manager("Andrea", LocalDate.of(1979, 5, 19), LocalDate.of(2020, 8, 1), 10000.0, 5000.0),
				new Experte("Fabio", LocalDate.of(1972, 6, 13), LocalDate.of(2010, 7, 1), 20000.0, "Scouting") };

		System.out.println();
		
		zeigeMitarbeiter("Unsortiert", angestellte);
		System.out.println();
		testeSonstigeMethoden(angestellte);
		
		System.out.println("Sortierung");
		System.out.println("##########\n");
		Arrays.sort(angestellte);
		zeigeMitarbeiter("Sortiert nach Name", angestellte);
		
		ByTypeUndEintrittsdatumComparator cmp = new ByTypeUndEintrittsdatumComparator();
		Arrays.sort(angestellte, cmp);
		zeigeMitarbeiter("\nSortiert nach Typ und Eintrittsdatum: ", angestellte);
		
		Comparator<Mitarbeiter> comparator = new ByTypeUndEintrittsdatumComparator();
		Arrays.sort(angestellte, comparator);
		zeigeMitarbeiter("Sortiert nochmal nach Typ und ED: ", angestellte);
		
		
	}
	
	static void zeigeMitarbeiter(String info, Mitarbeiter[] alleMitarbeiter) {
		System.out.println(info);
		for (Mitarbeiter mitarbeiter : alleMitarbeiter) {
			System.out.printf("%s\n", mitarbeiter.holeMitarbeiterInfo());
		}
	}
	
	static void testeSonstigeMethoden(Mitarbeiter[] angestellte) {
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
