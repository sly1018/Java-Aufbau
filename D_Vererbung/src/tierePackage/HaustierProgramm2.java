package tierePackage;

public class HaustierProgramm2 {

	public static void main(String[] args) {
//		Haustier[] meineTiere = new Haustier[4];
//		
//		meineTiere[0] = new Hund("Rex", 25);  // Umwandlung von Hund => Haustier
//		meineTiere[1] = new Katze("Minki", "Wollknäuel"); // Umwandlung von Katze => Haustier
//		meineTiere[2] = new Dackel("Wuffi", 12); // Umwandlung von Dackel => Hund => Haustier
//		meineTiere[3] = new Haustier("Tierlein");

		// Besser mit Initialisierungsliste
		Haustier[] meineTiere = new Haustier[] { new Hund("Rex", 25), // Umwandlung von Hund => Haustier
				new Katze("Minki", "Wollknäuel"), // Umwandlung von Katze => Haustier
				new Dackel("Wuffi", 12), // Umwandlung von Dackel => Hund => Haustier
				new Haustier("Tierlein") };

		for (Haustier einTier : meineTiere) {
			testeTier(einTier);
		}
	}

	static void testeTier(Haustier einTier) {
		System.out.printf("Test für das Tier %s\n", einTier.getKosename());
		// Klassennamen anzeigen.
		System.out.printf("Das Tier ist vom Typ %s\n", einTier.getClass().getSimpleName());
		System.out.print("zeigDich: ");
		einTier.zeigDich();

		System.out.println();
	}
}
