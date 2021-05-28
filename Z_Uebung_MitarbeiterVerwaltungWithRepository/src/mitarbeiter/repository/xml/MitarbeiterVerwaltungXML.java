package mitarbeiter.repository.xml;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import jakarta.xml.bind.JAXBContext;
import jakarta.xml.bind.JAXBException;
import jakarta.xml.bind.Marshaller;
import jakarta.xml.bind.Unmarshaller;
import mitarbeiter.repository.Mitarbeiter;
import mitarbeiter.repository.MitarbeiterRepository;
import mitarbeiter.repository.MitarbeiterRepositoryException;

public class MitarbeiterVerwaltungXML implements Serializable, MitarbeiterRepository {

	private static final long serialVersionUID = 1L;
	private Map<Integer, Mitarbeiter> mitarbeiterMap = new HashMap<>();

	private String fileName;

	public MitarbeiterVerwaltungXML(String fileName) {
		this.fileName = fileName;
	}

	public void showAll() {
		mitarbeiterMap.values().forEach(System.out::println);
	}

	@Override
	public List<Mitarbeiter> selectAll() throws MitarbeiterRepositoryException {
		// TODO: map to list
		return null;
	}

	@Override
	public Mitarbeiter selectById(int id) throws MitarbeiterRepositoryException {

		if (mitarbeiterMap.containsKey(id)) {
			return mitarbeiterMap.get(id);
		} else {
			throw new MitarbeiterRepositoryException("Mitarbeiter mit der Nummer:" + id + " wurde nicht gefunden.");
		}
	}

	@Override
	public int insertMitarbeiter(Mitarbeiter mitarbeiter) throws MitarbeiterRepositoryException {
		mitarbeiterMap.put(mitarbeiter.getMitarbeiterId(), mitarbeiter);

		return mitarbeiter.getMitarbeiterId();
	}

	@Override
	public void updateMitarbeiter(Mitarbeiter mitarbeiter) throws MitarbeiterRepositoryException {
		mitarbeiterMap.replace(mitarbeiter.getZahler(), mitarbeiter);
	}

	@Override
	public void deleteMitarbeiter(int id) throws MitarbeiterRepositoryException {
		mitarbeiterMap.remove(id);
	}

	public void loadData() throws IOException, JAXBException {
		try (InputStreamReader reader = new InputStreamReader(new FileInputStream(fileName), "utf-8")) {
			JAXBContext xmlContext = JAXBContext.newInstance(MitarbeiterCollection.class);
			// Objekt zur Deserialisierung
			Unmarshaller deserializer = xmlContext.createUnmarshaller();

			Object obj = deserializer.unmarshal(reader);

			MitarbeiterCollection mc = (MitarbeiterCollection) obj;
			mitarbeiterMap.clear();

			mc.getMitarbeiter().forEach(m -> mitarbeiterMap.put(m.getMitarbeiterId(), m));

			Mitarbeiter.initZaehler(mc.getNextMitarbeiterId());
		}
	}

	public void saveData() throws IOException, JAXBException {
		try (OutputStreamWriter writer = new OutputStreamWriter(new FileOutputStream(fileName), "UTF-8")) {
			JAXBContext xmlContext = JAXBContext.newInstance(MitarbeiterCollection.class);
			Marshaller serializer = xmlContext.createMarshaller();
			// Formatierung aktivieren
			serializer.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);

			MitarbeiterCollection mc = new MitarbeiterCollection();
			mc.getMitarbeiter().addAll(mitarbeiterMap.values());
			mc.setNextNummer(Mitarbeiter.getZahler());

			serializer.marshal(mc, writer);

			System.out.println("Daten gespeichert");
		}
	}
}
