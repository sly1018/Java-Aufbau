package tierePackage;

public class HaustierProgramm {

	public static void main(String[] args) {
		//Hund-Objekt erzeugen
		Hund rex = new Hund("Rex", 25);
		//Methoden der Basisklasse
		//rex.setKosename("Rex");
		rex.zeigDich();
		
		//Methoden der abgeleiteten Klasse
		//rex.setGewicht(25);
		rex.belle();
		
		// Typ-Kompatibilität
		Haustier einTier;
		// Basisklasse-Referenz verweist auf Objekt der abgeleiteten Klasse.
		// Es wird nur die Referenz umgewandelt, nicht das Objekt
		einTier = rex;
		einTier.zeigDich();
		System.out.println("Kosename: " + einTier.getKosename());
	}

}
