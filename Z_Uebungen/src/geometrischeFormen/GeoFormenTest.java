package geometrischeFormen;

import java.util.Scanner;

public class GeoFormenTest {

	public static void hitTestDurchfuehren() {
		Scanner eingabe = new Scanner(System.in);
		System.out.println("Bitte geben Sie die Koordinaten ein für ein Hit-Test(x,y):");
		int x = eingabe.nextInt();
		int y = eingabe.nextInt();
		
		
	}

	public static void main(String[] args) {

		GeometrischeForm[] formen = { new Rechteck(5, 3), new Kreis(15) };

		for (GeometrischeForm formObjekt : formen) {
			System.out.printf("Test %s %n", formObjekt.toString());
			System.out.printf("Fläche: %.2f; Umfang: %.2f\n", formObjekt.ermittleFlaeche(),
					formObjekt.ermittleUmfang());
		}
		hitTestDurchfuehren();
	}

}
