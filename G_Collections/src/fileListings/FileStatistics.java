package fileListings;

import java.io.File;
import java.time.Instant;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

public class FileStatistics {

	// Passende Zuordnung für File-Extension zu Liste von Filenamen
	// Statt einzelner Listen
	// private List<String> textFiles, javaFiles, classFiles;
	private Map<String, List<FileData>> dataMap = new TreeMap<>(String.CASE_INSENSITIVE_ORDER);

	public FileStatistics(String dirName) {
		// rekursiv das Verzeichnis verarbeiten
		File file = new File(dirName);
		this.readData(file);
		//this.showAll();
		this.showNewestOldest();
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
				// String fileName = file.getName();
		
				// 2) Wenn die Datamap noch keine Liste für die Extension enthält -> neue
				// (leere)
				// Liste hinzufügen
				if (dataMap.get(fileExtension) == null) {
					dataMap.put(fileExtension, new ArrayList<FileData>());
				}
				// 3) In der Liste für die Extension den Filename hinzufügen
				FileData fileData = new FileData(file.getName(), file.getAbsolutePath(), file.length(), Instant.ofEpochMilli(file.lastModified()));
				dataMap.get(fileExtension).add(fileData);

			}

			// Sonst: Wenn es ein Verzeichnis ist

			else {
				readData(file);
			}
		}
	}

	public void showAll() {
		// Für alle Extensions alle Files anzeigen
		for (String ext : dataMap.keySet()) {
			List<FileData> files = dataMap.get(ext);
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

	public void showFiles(String ext) {

	}

	public void showSizes() {

	}

	public void showNewestOldest() {
		for (String ext : dataMap.keySet()) {

			FileData minFile = null, maxFile = null;
			List<FileData> files = dataMap.get(ext);
			for (FileData fileData : files) {
				if (minFile == null || fileData.getLastModified().isBefore(minFile.getLastModified())) {
					minFile = fileData;
				}
			}
			System.out.printf("%s\n", ext);
			System.out.printf("\tÄltestes: %s", minFile);
			System.out.printf("\tNeuestes: %s", maxFile);
		}
	}

}
