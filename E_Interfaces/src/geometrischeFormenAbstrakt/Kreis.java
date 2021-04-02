package geometrischeFormenAbstrakt;

public class Kreis extends GeometrischeForm {
	
	private double radius;
	
	public Kreis(int x, int y, double r) {
		super(x, y);
		this.radius = r;
	}

	@Override
	public double ermittleFlaeche() {
		return Math.pow(radius, 2)*Math.PI;
	}

	@Override
	public double ermittleUmfang() {
		return 2*Math.PI*radius;
	}
	
	@Override
	public String toString() {
		return String.format("Kreis (r=%.2f), %s ", radius, super.toString());
	}

	@Override
	boolean hitTestdurchfuehren(int testX, int testY) {
		if((testX - this.getX()^2 + (testY - this.getY())^2) < this.radius)
			return true;
		else
			return false;
	}

}
