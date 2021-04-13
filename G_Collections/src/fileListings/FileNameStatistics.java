package fileListings;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

public class FileNameStatistics {

	// Passende Zuordnung für File-Extension zu Liste von Filenamen
	// Statt einzelner Listen
	// private List<String> textFiles, javaFiles, classFiles;
	private Map<String, List<String>> dataMap = new TreeMap<>();

	public FileNameStatistics(String dirName) {
		// rekursiv das Verzeichnis verarbeiten
		File file = new File(dirName);
		this.readData(file);
		this.showAll();
	}

	// Ein Verzeichnis verarbeiten
	private void readData(File dir) {
		// Alle Files im Verzeichnis auflisten
		File[] files = dir.listFiles();

		// Schleife
		for (File file : files) {

			// Wenn es ein File ist
			if (file.isFile()) {
				// 1) Extension herausfinden
				String fileExtension = getFileExtension(file);
				String fileName = file.getName();
				// 2) Wenn die Datamap noch keine Liste für die Extension enthält -> neue
				// (leere)
				// Liste hinzufügen
				if (dataMap.get(fileExtension) == null) {
					dataMap.put(fileExtension, new ArrayList<String>());
				}
				// 3) In der Liste für die Extension den Filename hinzufügen
				dataMap.get(fileExtension).add(fileName);

			}

			// Sonst: Wenn es ein Verzeichnis ist
			
			else {
				readData(file);
			}
		}
	}

	public void showAll() {
		// Für alle Extensions alle Files anzeigen
		for (Map.Entry<String, List<String>> entry : dataMap.entrySet()) {
			String key = entry.getKey();
			List<String> fileNames = entry.getValue();
			System.out.printf("%s %s\n", key, fileNames);
		}
	}

	private String getFileExtension(File file) {
		String name = file.getName();
		int lastIndexOf = name.lastIndexOf(".");
		if (lastIndexOf == -1) {
			return ""; // Leere Extension
		}
		return name.substring(lastIndexOf);
	}

}
