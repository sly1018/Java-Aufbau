package tierePackage2;

/**
 * Basisklasse für verschiedene Arten von Haustier
 * 
 * @author Sulaiman
 *
 */
abstract public class Haustier {
	private String kosename;

	// protected: nur abgeleitetee Klassen (und Klassen im selben Package) sehen
	// diesen Konstruktor
	protected Haustier(String name) {
		kosename = name;
		System.out.println("Konstruktor von Haustier");
	}

	/**
	 * Getter für die Eigenschaft kosename
	 * 
	 * @return
	 */
	public final String getKosename() {
		return kosename;
	}

	/**
	 * Setter für die Eigenschaft kosename
	 * 
	 * @param kosename
	 */
	public final void setKosename(String kosename) {
		this.kosename = kosename;
	}

	/**
	 * Anzeigen von Informationen zum Haustier
	 */
	public void zeigDich() {
		System.out.printf("Hallo, mein Name ist %s\n", getKosename());
	}

//	public void gibEinenLautVonDir() {
//		System.out.println("??? welches Geräusch soll ich machen?");
//	}

	abstract public void gibEinenLautVonDir();

	abstract public void bewegDich();

	@Override
	public String toString() {
		// return String.format("Kosename=%s", getKosename());
		return "%s: Kosename=%s".formatted(getClass().getSimpleName(), getKosename());
	}

}
