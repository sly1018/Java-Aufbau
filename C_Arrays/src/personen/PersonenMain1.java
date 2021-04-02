package personen;

import java.util.Scanner;

public class PersonenMain1 {

	public static void main(String[] args) {
		// Ein Array von 5 Person-Referenzen erzeugen
		// jedes Element wird mit null initialisiert
		Person[] gruppe = new Person[5];
		// pTmp hat den Wert null
		Person pTmp = gruppe[0];
		
		gruppe[0] = new Person("Max", 10);
		gruppe[2] = new Person("Moritz", 8);
		gruppe[3] = new Person("Susi", 9);
		
		for (int i = 0; i < gruppe.length; i++) {
			Person tmpPerson = gruppe[i];
			// Wenn an dieser Stelle ein Objekt steht
			if(tmpPerson != null) {
				System.out.printf("Am Index %d steht %s\n", i, tmpPerson.toString());
			} else {
				System.out.printf("Am Index %d steht kein Objekt\n", i);
			}
		}
		
		Scanner eingabe = new Scanner(System.in);
		System.out.println("Weiter mit Enter");
		eingabe.nextLine();
		
		// Fehlerhafter Zugriff, ohne Überprüfung auf null
		for (int i = 0; i < gruppe.length; i++) {
			Person tmpPerson = gruppe[i];
			// Wenn an dieser Stelle ein Objekt steht
				System.out.printf("Am Index %d steht %s\n", i, tmpPerson.toString());
		}
		
	}

}
