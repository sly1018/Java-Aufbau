package geometrischeFormenAbstrakt;

public class Rechteck extends GeometrischeForm {

	private double laenge;
	private double breite;

	public Rechteck(int x, int y, double laenge, double breite) {
		super(x, y);
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

	@Override
	boolean hitTestdurchfuehren(int testX, int testY) {
		int x2 = this.getX() + (int) this.breite;
		int y2 = this.getY() + (int) this.laenge;

		if (testX > this.getX() && testX < x2 && testY > this.getY() && testY < y2)
			return true;
		else
			return false;
	}

}
