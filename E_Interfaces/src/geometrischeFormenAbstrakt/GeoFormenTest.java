package geometrischeFormenAbstrakt;

public class GeoFormenTest {

	public static void main(String[] args) {
		System.out.println("Formendemo mit abstrakter Klasse");
		GeometrischeForm[] formen = {
				new Rechteck(5,3, 20, 30),
				new Kreis(1, -7, 15)
		};
		
		for (GeometrischeForm formObjekt : formen) {
			System.out.printf("Test %s %n", formObjekt);
			System.out.printf("Fläche: %.2f; Umfang: %.2f%n%n", formObjekt.ermittleFlaeche(), formObjekt.ermittleUmfang());
			if(formObjekt.hitTestdurchfuehren(12, 8)) {
				System.out.println("Der Punkt liegt auf der Form.");
			} else {
				System.out.println("Der Punkt liegt nicht auf der Form.");
			}
		}
	}

}
