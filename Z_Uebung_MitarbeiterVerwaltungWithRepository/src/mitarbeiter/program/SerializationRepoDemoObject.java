package mitarbeiter.program;

import java.io.File;
import java.io.IOException;
import java.time.LocalDate;
import java.util.List;
import java.util.Map;

import mitarbeiter.repository.Experte;
import mitarbeiter.repository.Manager;
import mitarbeiter.repository.Mitarbeiter;
import mitarbeiter.repository.MitarbeiterRepositoryException;
import mitarbeiter.repository.serialization.MitarbeiterVerwaltung;

public class SerializationRepoDemoObject {

	static MitarbeiterVerwaltung mVerwaltung = new MitarbeiterVerwaltung("mitarbeiter.dat");

	public static void main(String[] args) {

		// Gespeicherte Daten laden
		try {
			File mFile = new File("mitarbeiter.dat");
			// File nicht vorhanden?
			if (!mFile.exists()) {
				initialSaveData();
			} else {
				// Alle Daten laden testen
				System.out.println("Daten laden:");
				mVerwaltung.loadData();
				mVerwaltung.showAll();

				// Einen Mitarbeiter per Id auswählen testen
				System.out.println("\nEinen Mitarbeiter auswählen anhand von Id und anzeigen:");
				Mitarbeiter m = new Mitarbeiter();
				m = mVerwaltung.selectById(2);
				System.out.println(m.toString());

				// Einen Mitarbeiter ändern
				System.out.println("\nEinen Mitarbeiter ändern und anzeigen:");
				Experte e = (Experte) m;
				e.setFachgebiet("Marketing");
				mVerwaltung.updateMitarbeiter(e);
				m = mVerwaltung.selectById(2);
				System.out.println(m.toString());

				// Alle anzeigen vor der Löschung
				System.out.println();
				mVerwaltung.showAll();

				// Einen Mitarbeiter löschen
				System.out.println("\nEinen Mitarbeiter löschen und alle anzeigen:");
				mVerwaltung.deleteMitarbeiter(2);
				mVerwaltung.showAll();
			}
		} catch (ClassNotFoundException | IOException | MitarbeiterRepositoryException e) {
			System.out.println("Ein Fehler ist aufgetreten");
			e.printStackTrace();
		}

	}

	private static void initialSaveData() throws MitarbeiterRepositoryException, IOException {
		// Mitarbeiter anlegen testen
		mVerwaltung.insertMitarbeiter(
				new Mitarbeiter("Lana", LocalDate.of(1989, 2, 26), LocalDate.of(2009, 8, 8), 2300.0));
		mVerwaltung.insertMitarbeiter(
				new Experte("Ana", LocalDate.of(1990, 2, 13), LocalDate.of(2009, 8, 26), 2450.0, "BWL"));
		mVerwaltung.insertMitarbeiter(
				new Manager("Gustl", LocalDate.of(1986, 4, 21), LocalDate.of(2008, 8, 1), 2100.0, 300));

		System.out.println("Folgende Mitarbeiter wurden gespeichert");
		mVerwaltung.showAll();
		mVerwaltung.saveData();

	}

}
