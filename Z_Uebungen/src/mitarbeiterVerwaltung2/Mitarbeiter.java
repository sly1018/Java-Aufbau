package mitarbeiterVerwaltung2;

import java.time.LocalDate;
import java.time.Period;
import java.time.temporal.ChronoUnit;

public class Mitarbeiter implements Comparable<Mitarbeiter> {

	private static int zaehler;

	private final int mitarbeiterId;
	private String name;
	private LocalDate gbDatum;
	private LocalDate eintrittsDatum;
	private double grundgehalt;

	static {
		zaehler = 1;
	}

	public Mitarbeiter() {
		this.mitarbeiterId = zaehler++;
	}

	public Mitarbeiter(String n, LocalDate ed) {
		this.mitarbeiterId = zaehler++;
		this.name = n;
		this.eintrittsDatum = ed;
	}

	public Mitarbeiter(String n, LocalDate gb, LocalDate ed, double gg) {
		this.mitarbeiterId = zaehler++;
		this.name = n;
		this.gbDatum = gb;
		this.eintrittsDatum = ed;
		this.grundgehalt = gg;
	}

	public double berechneMonatsGehalt() {
		return grundgehalt;
	}

	public double berechneJahresGehalt() {
		return (grundgehalt * 14);
	}

	public void erhoeheGehalt(float prozentsatz) {
		grundgehalt = grundgehalt * prozentsatz;
	}

	public long berechneAnstellungsDauer() {
		LocalDate heute = LocalDate.now();
		long rueWert = ChronoUnit.DAYS.between(eintrittsDatum, heute);
		return rueWert;
	}

	public String holeMitarbeiterInfo() {
		return "[Mitarbeiter Id: %d], [Name: %s], [Geburtsdatum: %s], [Eintrittsdatum: %s], [Grundgehalt: %.2f]"
				.formatted(mitarbeiterId, name, gbDatum, eintrittsDatum, grundgehalt);
	}

	@Override
	public int compareTo(Mitarbeiter o2) {
		if (o2 == null) {
			return 1;
		}
		int ret = this.name.compareTo(o2.name);
		System.out.printf("Vergleiche %s mit %s: %d\n", this.name, o2.name, ret);

		return ret;
	}

	public double getGrundgehalt() {
		return grundgehalt;
	}

	public String getName() {
		return name;
	}

	public LocalDate getEintrittsdatum() {
		return eintrittsDatum;
	}

	public int getMitarbeiterId() {
		return mitarbeiterId;
	}

}
