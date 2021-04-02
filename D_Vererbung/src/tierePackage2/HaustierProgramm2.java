package tierePackage2;

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
				new Dackel("Wuffi", 12) }; // Umwandlung von Dackel => Hund => Haustier
		// Abstrakte Klasse nicht instantiert werden
		// new Haustier("Tierlein") };

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

		System.out.print("gibLautVonDir:");
		einTier.gibEinenLautVonDir();

		System.out.print("bewegDich: ");
		einTier.bewegDich();

		// toString:
		// expliziter Aufruf
		String info = einTier.toString();
		System.out.println("toString1: " + info);
		
		// impliziter Aufruf bei Stringverkettung
		System.out.println("toString2: " + einTier/*.toString()*/);
		
		// impliziter Aufruf bei printf
		System.out.printf("toString3: %s\n", einTier/*.toString()*/);

		System.out.println();
	}
}
