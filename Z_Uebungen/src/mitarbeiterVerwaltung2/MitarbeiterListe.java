package mitarbeiterVerwaltung2;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;


import mitarbeiterVerwaltung.ByTypeUndEintrittsdatumComparator;

public class MitarbeiterListe implements MitarbeiterListeItf {

	private ArrayList<Mitarbeiter> gruppe = new ArrayList<>();

	@Override
	public void mitarbeiterHinzufuegen(Mitarbeiter m) {
		gruppe.add(m);
	}

	@Override
	public void managerHinzufuegen(String name, LocalDate gb, LocalDate eintritt, double gg, double bonus) {
		Mitarbeiter mg = new Manager(name, gb, eintritt, gg, bonus);
		gruppe.add(mg);
	}

	@Override
	public void experteHinzufuegen(String name, LocalDate gb,  LocalDate eintritt, double gg, String fachgebiet) {
		Mitarbeiter e = new Experte(name, gb, eintritt, gg, fachgebiet);
		gruppe.add(e);
	}

	@Override
	public void erhoehung(float pct) {
		for (Mitarbeiter mitarbeiter : gruppe) {
			mitarbeiter.erhoeheGehalt(pct);
		}
	}

	@Override
	public void erhoehung(int maNummer, float pct) throws MitarbeiterException {
		sucheMitarbeiter(maNummer).erhoeheGehalt(pct);
	}

	@Override
	public void anzeigen(int maNummer) throws MitarbeiterException {
		int mIndex = mitarbeiterIndex(maNummer);
		if (mIndex == -1) {
			throw new MitarbeiterException("Mitarbeiter mit der Nummer["+ maNummer +"] existiert nicht");
		} else {
			Mitarbeiter m = gruppe.get(mIndex);
			System.out.printf("Mitarbeiter gefunden: %s\n", m.holeMitarbeiterInfo());
		}
	}

	@Override
	public void sortiertNachName() {
		Collections.sort(gruppe);
		
		for (Mitarbeiter mitarbeiter : gruppe) {
			System.out.printf("%s\n", mitarbeiter.holeMitarbeiterInfo());
		}
	}

	@Override
	public void sortiertNachTyp() {
		
		Collections.sort(gruppe, new ByTypeUndEintrittsdatum());
		
		for (Mitarbeiter mitarbeiter : gruppe) {
			System.out.printf("%s\n", mitarbeiter.holeMitarbeiterInfo());
		}
	}

	@Override
	public Mitarbeiter abmelden(int maNummer) throws MitarbeiterException {
		int mIndex = mitarbeiterIndex(maNummer);
		if(mIndex < 0)
			throw new MitarbeiterException("Der Mitarbeiter mit der Nummer [" + maNummer + "] existiert nicht");
		
		return gruppe.remove(mIndex);
	}

	@Override
	public Mitarbeiter sucheMitarbeiter(int maNummer) throws MitarbeiterException {
		int mIndex = mitarbeiterIndex(maNummer);
		if (mIndex >= 0) {
			return gruppe.get(mIndex);
		} else {
			throw new MitarbeiterException("Mitarbeiter mit der Nummer [" + maNummer + "] nicht gefunden.");
		}
	}

	private int mitarbeiterIndex(int nr) {
		for (int i = 0; i < gruppe.size(); i++) {
			Mitarbeiter m = gruppe.get(i);

			if (m.getMitarbeiterId() == nr) {
				return i;
			}
		}
		return -1;
	}

}
