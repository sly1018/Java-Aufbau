package geometrischeFormenInterface;

public class Kreis implements GeometrischeForm {
	
	private double radius;
	
	public Kreis() {}
	
	public Kreis(double r) {
		this.radius = r;
	}

	@Override
	public double ermittleFlaeche() {
//		double f = Math.pow(radius, 2)*Math.PI;
//		System.out.printf("Die Fläche vom Kreis beträgt: %.2f\n", f);
		return Math.pow(radius, 2)*Math.PI;
	}

	@Override
	public double ermittleUmfang() {
//		double u = 2*Math.PI*radius;
//		System.out.printf("Der Umfang vom Kreis beträgt: %.2f\n", u);
		return 2*Math.PI*radius;
	}

}
