package textfiles;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.charset.Charset;
import java.util.Scanner;

public class WriteTextfile1 {

	static Scanner input = new Scanner(System.in);

	public static void main(String[] args) {

		mitZeilenUmbrueche();

		// den Scanner schließen
		input.close();
	}

	static void ohneZeilenUmbrueche() {
		System.out.println("Text zeilenweise eingeben, Beenden mit Leerzeile");

		try {
			FileWriter writer = new FileWriter("Textfile1.txt");
			String line;
			while ((line = input.nextLine()) != null && !line.isEmpty()) {
				// Die Zeile ins File schreiben
				writer.write(line);
			}
			// Den Writer schließen.
			writer.close();
		} catch (IOException e) {
			System.out.println("Fehler beim Speichern: " + e);
		}
	}

	static void mitZeilenUmbruecheAlt() {
		System.out.println("Text zeilenweise eingeben, Beenden mit Leerzeile");

		try {

			// FileWriter fWriter = new FileWriter("Textfile1.txt");
			// Verkettung von 2 Streams.
			BufferedWriter writer = new BufferedWriter(new FileWriter("Textfile1.txt"));
			String line;
			while ((line = input.nextLine()) != null && !line.isEmpty()) {
				// Die Zeile ins File schreiben
				writer.write(line);
				// writer.write('\n');
				writer.newLine();
			}
			// Den Writer schließen.
			writer.close();
		} catch (IOException e) {
			System.out.println("Fehler beim Speichern: " + e);
		}
	}

	static void mitZeilenUmbrueche() {
		System.out.println("Text zeilenweise eingeben, Beenden mit Leerzeile");
		try (BufferedWriter writer = new BufferedWriter(new FileWriter("Textfile1.txt", Charset.forName("UTF-8")))) {
			
			String line;
		
			while ((line = input.nextLine()) != null && !line.isEmpty()) {
				// Die Zeile ins File schreiben
				writer.write(line);
				// writer.write('\n');
				writer.newLine();
			}
			// Den Writer schließen.
		//	writer.close();
		} catch (IOException e) {
			System.out.println("Fehler beim Speichern: " + e);
		}
	}

}
