package geometrischeFormen;

public class Rechteck extends GeometrischeForm {

	private double laenge;
	private double breite;

	public Rechteck() {
	}

	public Rechteck(double laenge, double breite) {
		super();
		this.laenge = laenge;
		this.breite = breite;
	}

	@Override
	public double ermittleFlaeche() {
		return laenge * breite;
	}

	@Override
	public double ermittleUmfang() {
		return (double) (2 * laenge + 2 * breite);
	}
	
	@Override
	public String toString() {
		return String.format("Rechteck (l=%.2f, b=%.2f), %s", laenge, breite, super.toString());
	}

}
