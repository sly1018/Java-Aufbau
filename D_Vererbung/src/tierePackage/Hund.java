package tierePackage;

/**
 * Klasse Hund, erbt von Haustier
 * 
 * @author Sulaiman
 *
 */
public class Hund extends Haustier {

	private int gewicht;

	/**
	 * Konstruktor
	 */
	public Hund(String kName, int gewicht) {
		// Default-Konstruktor der Basisklasse steht nicht zur Verfügung.
		// -> impliziter super-Aufruf ist nicht möglich.
		// super();
		// Stattdessen braucht wir einen expliziten super-Aufruf mit passenden
		// Argumenten
		super(kName);
		this.gewicht = gewicht;

		System.out.println("Konstruktor von Hund.");
	}

	/**
	 * Getter für die Eigenschaft gewicht.
	 */
	public final int getGewicht() {
		return gewicht;
	}

	/**
	 * Setter für die Eigenschaft gewicht.
	 */
	public final void setGewicht(int gewicht) {
		this.gewicht = gewicht;
	}
	
	// Nicht erlaubt weil final.
//	public String getKosename() {
//		return "xx";
//	}

	/**
	 * Der Hund gibt ein Bellen von sich.
	 */
	public void belle() {
		System.out.printf("%s macht wau wau\n", getKosename());
	}
	
	@Override
	public void zeigDich() {
		// System.out.println("Hey, mein Name ist " + getKosename());
		// Basisklassen-Implementierung aufrufen
		super.zeigDich();
		System.out.printf("Ich bin ein Hund und habe %d kg\n", getGewicht());
	}

}
