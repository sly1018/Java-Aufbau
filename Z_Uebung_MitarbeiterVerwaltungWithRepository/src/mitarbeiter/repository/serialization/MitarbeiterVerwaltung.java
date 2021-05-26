package mitarbeiter.repository.serialization;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import mitarbeiter.repository.Mitarbeiter;
import mitarbeiter.repository.MitarbeiterRepository;
import mitarbeiter.repository.MitarbeiterRepositoryException;

public class MitarbeiterVerwaltung implements Serializable, MitarbeiterRepository {

	private static final long serialVersionUID = 1L;
	private Map<Integer, Mitarbeiter> mitarbeiterMap = new HashMap<>();

	private String fileName;

	public MitarbeiterVerwaltung(String fileName) {
		this.fileName = fileName;
	}

	public void showAll() {
		mitarbeiterMap.values().forEach(System.out::println);
	}

	@Override
	public Map<Integer, Mitarbeiter> selectAll() throws MitarbeiterRepositoryException {
		return mitarbeiterMap;
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

	public void loadData() throws ClassNotFoundException, IOException, MitarbeiterRepositoryException {
		try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(fileName))) {
			// Mitarbeiter-Objekt lesen
			Object obj1 = ois.readObject();
			mitarbeiterMap = (Map<Integer, Mitarbeiter>) obj1;

			System.out.printf("%d Mitarbeiter geladen \n", mitarbeiterMap.size());
			Mitarbeiter.initZaehler(ois.readInt());
		}
	}

	public void saveData() throws IOException, MitarbeiterRepositoryException {
		try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(fileName))) {
			// Mitarbeiter-Objekt in den Stream schreiben
			oos.writeObject(mitarbeiterMap);
			// Den aktuellen Zähler ins File schreiben
			oos.writeInt(Mitarbeiter.getZahler());

			System.out.println("Serialisierung erfolgreich!");
		}
	}

}
