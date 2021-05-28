package mitarbeiter.repository;

import java.time.LocalDate;

public class Experte extends Mitarbeiter {

	private static final long serialVersionUID = 1L;
	private String fachgebiet;

	public Experte() {
		super();
	}

	public Experte(String n, LocalDate gb, LocalDate ed, double gg, String fg) {
		super(n, gb, ed, gg);
		this.fachgebiet = fg;
	}

	public String getFachgebiet() {
		return fachgebiet;
	}

	public void setFachgebiet(String fachgebiet) {
		this.fachgebiet = fachgebiet;
	}

	@Override
	public double berechneJahresGehalt() {
		return (super.getGrundgehalt() * 15);
	}

	@Override
	public String holeMitarbeiterInfo() {
		return super.holeMitarbeiterInfo().concat(", [Fachgebiet: %s]".formatted(fachgebiet));
	}

	@Override
	public String toString() {
		return super.holeMitarbeiterInfo().concat(", [Fachgebiet: %s]".formatted(fachgebiet));
	}
}
