package geometrischeFormenInterface;

public class Rechteck implements GeometrischeForm {
	
	private double laenge;
	private double breite;
	
	public Rechteck() {}
	
	public Rechteck(double laenge, double breite) {
		super();
		this.laenge = laenge;
		this.breite = breite;
	}

	@Override
	public double ermittleFlaeche() {
		// System.out.printf("Die Fläche vom Rechteck beträgt %.2f\n", (laenge*breite));
		return laenge*breite;
	}

	@Override
	public double ermittleUmfang() {
		// System.out.printf("Der Umfang vom Rechteck beträgt %.2f\n", (2*laenge+2*breite));
		return (double) (2*laenge+2*breite);
	}

}
