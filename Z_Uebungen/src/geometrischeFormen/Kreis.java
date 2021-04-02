package geometrischeFormen;

public class Kreis extends GeometrischeForm {

	private double radius;

	public Kreis() {
	}

	public Kreis(double r) {
		this.radius = r;
	}

	@Override
	public double ermittleFlaeche() {
		return Math.pow(radius, 2) * Math.PI;
	}

	@Override
	public double ermittleUmfang() {
		return 2 * Math.PI * radius;
	}
	
	@Override
	public String toString() {
		return String.format("Kreis (r=%.2f), %s ", radius, super.toString());
	}

}
