package geometrischeFormenInterface;

// Abstrakte Klasse Form
public abstract interface GeometrischeForm {

	// Im Interface dürfen wir keine Attribute definieren
	// Konstant(public static final...) wäre erlaubt.
	// private int x, y;

	// Konstruktor ist nicht erlaubt
//	public GeometrischeForm() {
//		
//	}

	// Seit Java 8 sind statische und default-Methoden erlaubt
	static void tuEtwas() {
		System.out.println("Statische Methode in einem Interface");
	}

	/* abstract public */ double ermittleFlaeche();

	/* abstract public */double ermittleUmfang();
}
