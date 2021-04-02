package mitarbeiterVerwaltungListe;

import java.time.LocalDate;

public class Experte extends Mitarbeiter {

	private String fachgebiet;
	
	public Experte() {
		super();
	}
	
	public Experte(String n, LocalDate gb, LocalDate ed, double gg, String fg) {
		super(n, gb,ed, gg);
		this.fachgebiet = fg;
	}
	
	@Override
	public double berechneJahresGehalt() {
		return (super.getGrundgehalt()*15);
	}
	
	@Override
	public String holeMitarbeiterInfo() {
		return super.holeMitarbeiterInfo().concat(", [Fachgebiet: %s]".formatted(fachgebiet));
	}
}
