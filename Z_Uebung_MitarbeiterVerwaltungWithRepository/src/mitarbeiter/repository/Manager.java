package mitarbeiter.repository;

import java.time.LocalDate;

public class Manager extends Mitarbeiter {

	private static final long serialVersionUID = 1L;
	private double bonus;

	public Manager() {
		super();
	}

	public Manager(String n, LocalDate gb, LocalDate ed, double gg, double b) {
		super(n, gb, ed, gg);
		this.bonus = b;
	}

	public double getBonus() {
		return this.bonus;
	}

	public void setBonus(double bonus) {
		this.bonus = bonus;
	}

	@Override
	public void erhoeheGehalt(float ps) {
		super.erhoeheGehalt(ps);
		bonus = bonus * (1 + ps);
	}

	@Override
	public double berechneJahresGehalt() {
		return super.berechneJahresGehalt() + bonus;
	}

	@Override
	public String holeMitarbeiterInfo() {
		return super.holeMitarbeiterInfo().concat(", [Bonus: %.2f]".formatted(bonus));
	}

	@Override
	public String toString() {
		return super.holeMitarbeiterInfo().concat(", [Bonus: %.2f]".formatted(bonus));
	}

}
