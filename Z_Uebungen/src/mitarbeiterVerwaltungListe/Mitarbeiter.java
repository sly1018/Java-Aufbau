package mitarbeiterVerwaltungListe;

import java.time.LocalDate;
import java.time.Period;
import java.time.temporal.ChronoUnit;

public class Mitarbeiter implements Comparable<Mitarbeiter>, MitarbeiterGruppeItf {

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

	@Override
	public double getGrundgehalt() {
		return grundgehalt;
	}

	@Override
	public String getName() {
		return name;
	}
	
	@Override
	public LocalDate getEintrittsdatum() {
		return eintrittsDatum;
	}

	public Mitarbeiter(String n, LocalDate gb, LocalDate ed, double gg) {
		this.mitarbeiterId = zaehler++;
		this.name = n;
		this.gbDatum = gb;
		this.eintrittsDatum = ed;
		this.grundgehalt = gg;
	}

	@Override
	public double berechneMonatsGehalt() {
		return grundgehalt;
	}

	@Override
	public double berechneJahresGehalt() {
		return (grundgehalt * 14);
	}

	@Override
	public void erhoeheGehalt(float prozentsatz) {
		grundgehalt = grundgehalt * prozentsatz;
	}

	@Override
	public long berechneAnstellungsDauer() {
		LocalDate heute = LocalDate.now();
		long rueWert = ChronoUnit.DAYS.between(eintrittsDatum, heute);
		return rueWert;
	}

	@Override
	public String holeMitarbeiterInfo() {
		return "[Mitarbeiter Id: %d], [Name: %s], [Geburtsdatum: %s], [Eintrittsdatum: %s], [Grundgehalt: %.2f]"
				.formatted(mitarbeiterId, name, gbDatum, eintrittsDatum, grundgehalt);
	}

	@Override
	public int compareTo(Mitarbeiter o2) {
		if(o2 == null) {
			return 1;
		}
		int ret = this.name.compareTo(o2.name);
		System.out.printf("Vergleiche %s mit %s: %d\n", this.name, o2.name, ret);

		return ret;
	}

	@Override
	public int compareTo(MitarbeiterGruppeItf o2) {
		// TODO Auto-generated method stub
		return 0;
	}

}
