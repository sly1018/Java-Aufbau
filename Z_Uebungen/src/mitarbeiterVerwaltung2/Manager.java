package mitarbeiterVerwaltung2;

import java.time.LocalDate;

public class Manager extends Mitarbeiter {

	private double bonus;

	public Manager() {
		super();
	}
	
	public Manager(String n, LocalDate ed, double b) {
		super(n, ed);
		this.bonus = b;
	}

	public double getBonus() {
		return this.bonus;
	}

	public Manager(String n, LocalDate gb, LocalDate ed, double gg, double b) {
		super(n, gb, ed, gg);
		this.bonus = b;
	}

	@Override
	public void erhoeheGehalt(float ps) {
		super.erhoeheGehalt(ps);
		bonus = bonus * (ps);
	}

	@Override
	public double berechneJahresGehalt() {
		return super.berechneJahresGehalt() + bonus;
	}

	@Override
	public String holeMitarbeiterInfo() {
		return super.holeMitarbeiterInfo().concat(", [Bonus: %.2f]".formatted(bonus));
	}

}
