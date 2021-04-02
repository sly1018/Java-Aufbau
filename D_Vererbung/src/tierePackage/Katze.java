package tierePackage;

public class Katze extends Haustier {

	private String spielzeug;

	public Katze(String name, String spielzeug) {
		super(name);
		this.spielzeug = spielzeug;
		System.out.println("Konstruktor von Katze");
	}

	public final String getSpielzeug() {
		return spielzeug;
	}

	public final void setSpielzeug(String spielzeug) {
		this.spielzeug = spielzeug;
	}

	@Override
	public void zeigDich() {
		super.zeigDich();
		System.out.printf("Ich bin eine Katze und spiele gerne mit %s\n", spielzeug);
	}
	
	// Eigene Funktionalität
	public void miaue() {
		System.out.printf("%s macht miauuuuu!\n", getKosename());
	}

}
