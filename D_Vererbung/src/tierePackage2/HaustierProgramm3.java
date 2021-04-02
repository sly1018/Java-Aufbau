package tierePackage2;

public class HaustierProgramm3 {

	public static void main(String[] args) {

		// Besser mit Initialisierungsliste
		Haustier[] meineTiere = new Haustier[] { new Hund("Rex", 25), // Umwandlung von Hund => Haustier
				new Katze("Minki", "Wollknäuel"), // Umwandlung von Katze => Haustier
				new Dackel("Wuffi", 12) }; // Umwandlung von Dackel => Hund => Haustier
		// Abstrakte Klasse nicht instantiert werden
		// new Haustier("Tierlein") };

		for (Haustier einTier : meineTiere) {
			testeInstanceOf(einTier);
		}
	}

	static void testeInstanceOf(Haustier einTier) {
		System.out.printf("Test für das Tier %s\n", einTier.getKosename());

		// Wenn das Tier mit Hund kompatibel ist (dh. Hund oder Dackel)
		if (einTier instanceof Hund) {
			System.out.println("Es ist eine Art Hund.");
			// explizite Umwandlung
			Hund einHund = (Hund) einTier;
			// Spezifische Hund-Methoden aufrufbar
			einHund.belle();
			System.out.printf("Der Hund hat %d kg\n", einHund.getGewicht());
		} else if (einTier instanceof Katze) {
			System.out.println("Es ist eine Art Katze");
			// Explizite Umwandlung und Methodenaufruf in einem
			((Katze) einTier).miaue();
		}

		// Testen ob die Instanz genau von einer Klasse ist.
		if (einTier.getClass() == Hund.class) {
			System.out.println("Objekt der Klasse Hund.");
		} else if (einTier.getClass() == Katze.class) {
			System.out.println("Objekt der Klasse Katze.");
		} else if (einTier.getClass() == Dackel.class) {
			System.out.println("Objekt der Klasse Dackel.");
		}

		System.out.println();
	}
}
