package xml.model;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStreamWriter;
import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

import jakarta.xml.bind.JAXBContext;
import jakarta.xml.bind.JAXBException;
import jakarta.xml.bind.Marshaller;
import jakarta.xml.bind.Unmarshaller;

// - ist ein Marker Interface enthält keine abstrakten Methoden
public class FahrzeugVerwaltung implements Serializable {

	// Version von unserem Dokument-Format
	private static final long serialVersionUID = 1L;
	private Map<Integer, Fahrzeug> fahrzeuge = new HashMap<>();

	private String fileName;

	public FahrzeugVerwaltung(String fileName) {
		this.fileName = fileName;
	}

	public Map<Integer, Fahrzeug> getFahrzeuge() {
		return fahrzeuge;
	}

	// ein Fahrzeug in der Map einfügen
	public void add(Fahrzeug fz) {
		// Zuerst die Nummer anpassen
		fahrzeuge.put(fz.getNr(), fz);
	}

	public void showAll() {
		fahrzeuge.values().forEach(System.out::println);
	}

	public void loadData() throws IOException, JAXBException {
		try (InputStreamReader reader = new InputStreamReader(new FileInputStream(fileName), "utf-8")) {
			// JAXBContext für das Speichern und Laden von einem Fahrzeuge-Objekt erzeugen
			JAXBContext xmlContext = JAXBContext.newInstance(Fahrzeuge.class);
			// Objekt zur Deserialisierung
			Unmarshaller deserializer = xmlContext.createUnmarshaller();

			Object obj = deserializer.unmarshal(reader);
			// Umwandeln in Fahrzeuge
			Fahrzeuge fz = (Fahrzeuge) obj;
			// Die Map leeren
			fahrzeuge.clear();
			// Die Fahrzeuge in unsere Map füllen
			fz.getFahrzeuge().forEach(f -> fahrzeuge.put(f.getNr(), f));
			// Den Nummernzähler initialisieren
			Fahrzeug.initNextNr(fz.getNextNummer());

		}

	}

	public void saveData() throws IOException, JAXBException {
		try (OutputStreamWriter writer = new OutputStreamWriter(new FileOutputStream(fileName), "UTF-8")) {
			// JAXBContext für das Speichern und Laden von einem Fahrzeuge-Objekt erzeugen
			JAXBContext xmlContext = JAXBContext.newInstance(Fahrzeuge.class);
			// Objekt für die Serialisierung holen
			Marshaller serializer = xmlContext.createMarshaller();
			// Formatierung aktivieren
			serializer.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);

			// Das Objekt das gespeichert werden soll, konfigurieren
			Fahrzeuge fz = new Fahrzeuge();
			fz.getFahrzeuge().addAll(fahrzeuge.values());
			// Den Nummer-Zähler
			fz.setNextNummer(Fahrzeug.getNextNr());

			// Das ganze Fahrzeuge-Objekt speichern
			serializer.marshal(fz, writer);

			System.out.println("Daten gespeichert");
		}
	}

}
