package geometrischeFormenAbstrakt;

public abstract class GeometrischeForm {

	// in einer abstrakten Klassen dürfen wir Attribute definieren:
	private int x, y;

	// In einer abstrakten Klasse dürfen wir auch Konstruktoren definieren
	public GeometrischeForm(int x, int y) {
		this.x = x;
		this.y = y;
	}

	// In einer abstrakten Klassen dürfen wir beliebige Methoden definieren.
	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}

	abstract public double ermittleFlaeche();

	abstract public double ermittleUmfang();
	
	abstract boolean hitTestdurchfuehren(int testX, int testY);
	
	@Override
	public String toString() {
		return "Position %d/%d".formatted(x, y);
	}
}
