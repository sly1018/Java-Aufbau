package zeitPackage;

public class UhrzeitProgramm {

	public static void main(String[] args) {
		// Referenzen für Beginn und Ende definieren
		Uhrzeit beginn, ende;
		// Objekte erzeugen
		beginn = new Uhrzeit();
		ende = new Uhrzeit();
		//Werte setzen
		beginn.setzen(8, 45);
		ende.setzen(12, 30);
		
		System.out.println("Dauer von ");
		beginn.anzeigen();
		System.out.print(" bis ");
		ende.anzeigen();
		
		// Werte lesen
		System.out.printf("\nBeginn:  %d:%d\n", beginn.getStunde(), beginn.getMinute());
		
	}

}
