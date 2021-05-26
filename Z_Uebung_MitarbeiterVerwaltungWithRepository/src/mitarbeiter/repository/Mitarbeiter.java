package mitarbeiter.repository;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

public class Mitarbeiter implements Comparable<Mitarbeiter>, MitarbeiterGruppeItf, Serializable {

	private static int zaehler;

	private final int mitarbeiterId;
	private String name;
	private LocalDate gbDatum;
	private LocalDate eintrittsDatum;
	private double grundgehalt;

	static {
		zaehler = 1;
	}

	// Die Nummernzählung ins File zu speichern
	public static int getZahler() {
		return zaehler;
	}

	public static void initZaehler(int nr) {
		zaehler = nr;
		System.out.println("Init: zaehler =" + zaehler);
	}

	public Mitarbeiter() {
		this.mitarbeiterId = zaehler++;
	}

	public int getMitarbeiterId() {
		return mitarbeiterId;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setGbDatum(LocalDate gbDatum) {
		this.gbDatum = gbDatum;
	}

	public void setEintrittsDatum(LocalDate eintrittsDatum) {
		this.eintrittsDatum = eintrittsDatum;
	}

	public void setGrundgehalt(double grundgehalt) {
		this.grundgehalt = grundgehalt;
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
		if (o2 == null) {
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
	
	@Override
	public String toString() {
		return "[Mitarbeiter Id: %d], [Name: %s], [Geburtsdatum: %s], [Eintrittsdatum: %s], [Grundgehalt: %.2f]"
				.formatted(mitarbeiterId, name, gbDatum, eintrittsDatum, grundgehalt);
	}

}
