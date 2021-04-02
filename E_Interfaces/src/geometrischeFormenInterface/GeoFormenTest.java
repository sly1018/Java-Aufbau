package geometrischeFormenInterface;

public class GeoFormenTest {

	public static void main(String[] args) {
		System.out.println("Formendemo mit Interface");
		GeometrischeForm[] formen = {
				new Rechteck(5,3), // Umwandlung von Rechteck nach Interface GeoForm
				new Kreis(15) // Umwandlung von Kreis nach Interface GeoForm
		};
		
		for (GeometrischeForm formObjekt : formen) {
			System.out.printf("Test %s %n", formObjekt);
			System.out.printf("Fläche: %.2f; Umfang: %.2f\n", formObjekt.ermittleFlaeche(), formObjekt.ermittleUmfang());
		}
		GeometrischeForm.tuEtwas();
		System.out.println("Formendemo beendet.");
	}

}
