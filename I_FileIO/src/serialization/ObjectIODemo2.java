package serialization;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.time.LocalDate;
import java.util.Scanner;

import serialization.model.Auto;
import serialization.model.Fahrrad;
import serialization.model.FahrzeugVerwaltung;
import serialization.model.Fahrzeuge;
import serialization.model.LKW;

public class ObjectIODemo2 {
	private static Scanner scanner = new Scanner(System.in);

	static FahrzeugVerwaltung fzVerwaltung = new FahrzeugVerwaltung("Fahrzeuge2.dat");

	public static void main(String[] args) {
		// Gespeicherte Daten laden
		try {

			File fzFile = new File("Fahrzeuge2.dat");
			// File nicht vorhanden?
			if (!fzFile.exists()) {
				// Erstspeicherung
				initialSaveData();
			} else {
				// Sonst: Laden, anzeigen, usw...
				fzVerwaltung.loadData();
				fzVerwaltung.showAll();
				fzVerwaltung.add(new Auto("Porsche", 50000, LocalDate.now(), 300, "Schwarz"));
				System.out.println("Nach Add Porsche: ");
				fzVerwaltung.showAll();
				fzVerwaltung.saveData();
			}
		} catch (ClassNotFoundException | IOException e) {
			System.out.println("Es ist ein Fehler aufgetreten:");
			e.printStackTrace();
		}

	}

	private static void initialSaveData() throws IOException {
		fzVerwaltung.add(new Auto("Mercedes", 30000, LocalDate.of(2019, 12, 15), 110, "Silber"));
		fzVerwaltung.add(new LKW("MAN", 150000, LocalDate.of(2020, 10, 2), 320, "Rot", 8000, 100, 3));
		fzVerwaltung.add(new Fahrrad("KTM", 2000, LocalDate.of(2020, 5, 21), 27, "Mountainbike"));

		System.out.println("Folgende Fahrzeuge werden gespeichert");
		fzVerwaltung.showAll();
		fzVerwaltung.saveData();

	}

//	private static Fahrzeuge readData(String fileName) {
//		// Objekte einlesen
//		try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(fileName))) {
//
//			// Das Fahrzeuge-Objekt lesen
//			Object obj1 = ois.readObject();
//			if (!(obj1 instanceof Fahrzeuge)) {
//				System.out.println("Ungültiges File, erwartet wurde ein Fahrzeug-Objekt");
//				return null;
//			}
//
//			Fahrzeuge fahrzeugVerwaltung = (Fahrzeuge) obj1;
//			System.out.println("Vom File gelesen:");
//			fahrzeugVerwaltung.showAll();
//
//			// Den String lesen
//			Object obj2 = ois.readObject();
//			System.out.println("Außerdem vom File gelesen:");
//			System.out.println(obj2);
//
//			return fahrzeugVerwaltung;
//		} catch (Exception e) {
//			e.printStackTrace();
//			return null;
//		}
//	}
}
