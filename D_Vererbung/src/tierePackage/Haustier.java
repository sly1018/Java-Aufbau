package tierePackage;

/**
 * Basisklasse für verschiedene Arten von Haustier
 * 
 * @author Sulaiman
 *
 */
public class Haustier {
	private String kosename;

	public Haustier(String name) {
		kosename=name;
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

}
