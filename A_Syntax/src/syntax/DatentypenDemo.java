package syntax;

public class DatentypenDemo {

	public static void main(String[] args) {
		long lWert = 10_000_000_000L; // Suffix notwendig weil der Wert größer ist als der maximale int-Wert, Pflicht
		System.out.println("lWert: " + lWert);
		long lWert2 = 10_000L; // Suffix L ist optional
		System.out.println("lWert2: " + lWert2);
		float fWert = 234.567F; // Suffix F ist Pflicht, weil der Wert sonst von Typ double ist. Sonst
								// Typumwandlung(implizite & explizite)
		System.out.println("fWert: " + fWert);

		int iWert = 123456789;
		System.out.println("iWert: " + iWert);

		// implizite Typumwandlung von int nach float
		// Umwandlung in float, hier geht Genauiggkeit verloren, weil float nur 7-8 Stellen genau ist
		float fWert2 = iWert;
		System.out.println("fWert2: " + fWert2);
		
		// Escape-Sequenz für Unicode
		System.out.println("Alpha: \u03b1");
		
		var text = "Hallo Welt";
		System.out.println(text);
		
		String info = iWert%2 == 0 ? "gerade" : "ungerade";
		System.out.println("info : " + info);
		
		System.out.printf("%d als float ist %.1f\n", iWert, fWert2);
		System.out.printf("%.1f resultiert aus %d", iWert, fWert2); // Fehler: Argument passt nicht zum Platzhalter
		
	}

}
