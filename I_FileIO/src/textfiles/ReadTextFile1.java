package textfiles;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.nio.charset.Charset;

public class ReadTextFile1 {

	public static void main(String[] args) {
		blockweiseLesen("Textfile1.txt");

		// liefert nicht den korrekten Inhalt (wegen Encoding)
		// blockweiseLesen("Textfile1_ANSI.txt")

		zeilenweiseLesen("Textfile1.txt");
	}

	static void blockweiseLesen(String filename) {
		// Das File zum Lesen öffnen, mit UTF-8 Kodierung
		try (FileReader reader = new FileReader(filename, Charset.forName("UTF-8"))) {
			char[] buffer = new char[16]; // Sehr klein, damit die Schleife mehrmals ausgeführt wird.
			int count;
			// Speicherplatz für das Gelesene
			StringBuilder content = new StringBuilder();
			// Solange min. 1 Zeichen gelesen wurde.
			while ((count = reader.read(buffer)) > 0) {
				System.out.printf("%d Zeichen glesen\n", count);
				// Die Zeichen im StringBuilder anhängen
				content.append(buffer, 0, count);
			}

			// alles -> Ausgeben
			System.out.println("Vom File gelesen: ");
			System.out.println(content.toString());

		} catch (IOException e) {
			System.out.println("Fehler beim einlesen: " + e);
		}
	}

	static void zeilenweiseLesen(String filename) {
		try (BufferedReader reader = new BufferedReader(new FileReader(filename, Charset.forName("UTF-8")))) {
			String line;
			String content = "";
			// null == EOF (end of file)
			while ((line = reader.readLine()) != null) {
				System.out.println("Zeile: " + line);
				content += line;
			}
			System.out.println("Gelesen: ");
			System.out.println(content);
		} catch (IOException e) {
			System.out.println("Fehler beim Einlesen: " + e);
		}
	}
}
