package mitarbeiter.program;

import java.io.File;
import java.io.IOException;
import java.time.LocalDate;

import jakarta.xml.bind.JAXBException;
import mitarbeiter.repository.Experte;
import mitarbeiter.repository.Manager;
import mitarbeiter.repository.Mitarbeiter;
import mitarbeiter.repository.MitarbeiterRepositoryException;
import mitarbeiter.repository.xml.MitarbeiterVerwaltungXML;

public class TestXmlRepository {

	static MitarbeiterVerwaltungXML mVerwaltung = new MitarbeiterVerwaltungXML("mitarbeiter.xml");

	public static void main(String[] args) {

		// Gespeicherte Daten laden
		try {
			File mFile = new File("mitarbeiter.xml");
			// File nicht vorhanden?
			if (!mFile.exists()) {
				// Erst-Speicherung
				initialSaveData();
			} else {
				mVerwaltung.loadData();
				mVerwaltung.showAll();

			}
		} catch (IOException | JAXBException | MitarbeiterRepositoryException e) {
			System.out.println("Es ist ein Fehler aufgetreten:");
			e.printStackTrace();
		}

	}

	private static void initialSaveData() throws MitarbeiterRepositoryException, IOException, JAXBException {
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
