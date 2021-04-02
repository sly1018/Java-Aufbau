package tierePackage2;

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

	// Implementierung der abstrakten MEthoden ist Pflicht, wenn die Klasse nicht
	// selbst auch abstrakt ist
	@Override
	public void gibEinenLautVonDir() {
		// Die eigene Methode aufrufen
		miaue();
	}

	@Override
	public void bewegDich() {
		System.out.printf("%s macht einen Katzenbuckel.\n", getKosename());
	}
	
	@Override
	public String toString() {
		return "%s, Spielezeug=%s".formatted(super.toString(), getSpielzeug());
	}


}
