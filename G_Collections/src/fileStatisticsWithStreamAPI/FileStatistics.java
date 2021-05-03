package fileStatisticsWithStreamAPI;

import java.io.File;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneOffset;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.LongSummaryStatistics;
import java.util.Map;
import java.util.Optional;
import java.util.TreeMap;
import java.util.stream.Collector;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class FileStatistics {

	private Map<String, List<FileData>> dataMap = new TreeMap<>(String.CASE_INSENSITIVE_ORDER);

	public FileStatistics(String dirName) {
		// rekursiv das Verzeichnis verarbeiten
		File file = new File(dirName);
		this.readData(file);

		// this.showAll();
		// this.showNewestOldest();
		// this.showNewestOldest(".class");
		// this.showFiles(".java");
		// this.showFiles(".class");
		// this.showSizes();
		// this.showTotalSizePerExtension();
		// this.showNewestOldestFilePerExtensionWithOptional();
		// this.showMostCommonExtension();
		// this.showExtensionWithBiggestFile();
		// this.showExtensionWithNewestFile();
		this.showAllFilesSortedWithDate();

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
				FileData fileData = new FileData(file.getName(), file.getAbsolutePath(), file.length(),
						Instant.ofEpochMilli(file.lastModified()));
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
			System.out.printf("%s\n", ext);
			for (FileData fileData : files) {
				System.out.println(fileData.toString());
			}
			System.out.println();
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
		List<FileData> files = dataMap.get(ext);
		for (FileData fileData : files) {
			System.out.println(fileData.toString());
		}
	}

	public void showSizes() {

		int count = 0;

		for (String ext : dataMap.keySet()) {
			List<FileData> files = dataMap.get(ext);
			for (FileData fileData : files) {
				count++;
			}
			System.out.printf("%d %s files.\n", count, ext);
		}

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

			for (FileData fileData : files) {
				if (maxFile == null || fileData.getLastModified().isAfter(minFile.getLastModified()))
					maxFile = fileData;
			}

			System.out.printf("%s\n", ext);
			System.out.printf("\tÄltestes: %s\n", minFile);
			System.out.printf("\tNeuestes: %s\n", maxFile);
			System.out.println(
					"----------------------------------------------------------------------------------------------");
		}
	}

	public void showNewestOldest(String ext) {

		List<FileData> files = dataMap.get(ext);

		FileData minFile = null, maxFile = null;
		for (FileData fileData : files) {
			if (minFile == null || fileData.getLastModified().isBefore(minFile.getLastModified())) {
				minFile = fileData;
			}
		}

		for (FileData fileData : files) {
			if (maxFile == null || fileData.getLastModified().isAfter(minFile.getLastModified()))
				maxFile = fileData;
		}

		System.out.printf("%s\n", ext);
		System.out.printf("\tÄltestes: %s\n", minFile);
		System.out.printf("\tNeuestes: %s\n", maxFile);

	}

	public void showTotalSizePerExtension() {

		dataMap.keySet().forEach(ext -> {
			System.out.printf("%s\n", ext);
			LongSummaryStatistics summary = dataMap.get(ext).stream().mapToLong(f -> f.getSize()).summaryStatistics();
			System.out.printf("[sum=%d], [avg=%.2f], [max=%d]\n", summary.getSum(), summary.getAverage(),
					summary.getMax());
		});
	}

	public void showNewestOldestFilePerExtensionWithOptional() {
		for (String ext : dataMap.keySet()) {
			System.out.printf("%s\n", ext);
			List<FileData> files = dataMap.get(ext);

			files.sort(Comparator.comparing(FileData::getLastModified));

			// Optional erzeugen und das erste Element ausgeben.
			Optional<FileData> optional = files.stream().findFirst();
			System.out.println("Ältestes File: " + optional.get());

			// Mit Hilfe von Optional das letzte Element ausgeben.
			optional = files.stream().reduce((first, second) -> second);
			System.out.println("Neuestes File: " + optional.get());

		}
	}

	public void showMostCommonExtension() {
		int size;
		int mostCommonCount = 0;
		String nameOfExtension = "";

		for (String ext : dataMap.keySet()) {
			List<FileData> files = dataMap.get(ext);
			size = (int) files.stream().count();
			if (size > mostCommonCount) {
				mostCommonCount = size;
				nameOfExtension = ext;
			}
		}

		System.out.printf("Extension: %s, Anzahl Dateien: %d", nameOfExtension, mostCommonCount);
	}

	public void showExtensionWithBiggestFile() {

		HashMap<String, Long> hmap = new HashMap<String, Long>();

		for (String ext : dataMap.keySet()) {
			List<FileData> files = dataMap.get(ext);
			files.sort(Comparator.comparing(FileData::getSize));

			Optional<FileData> optional = files.stream().reduce((first, second) -> second);

			hmap.put(ext, optional.get().getSize());
		}

		long max = Collections.max(hmap.values());
		Optional<String> optEx = hmap.entrySet().stream().filter(entry -> entry.getValue() == max)
				.map(entry -> entry.getKey()).findFirst();
		System.out.printf("max: %d, ext: %s", max, optEx.get());
	}

	public void showExtensionWithNewestFile() {

		HashMap<String, LocalDate> hmap = new HashMap<String, LocalDate>();

		for (String ext : dataMap.keySet()) {
			List<FileData> files = dataMap.get(ext);
			files.sort(Comparator.comparing(FileData::getLastModified));

			Optional<FileData> optional = files.stream().reduce((first, second) -> second);

			hmap.put(ext, LocalDate.ofInstant(optional.get().getLastModified(), ZoneOffset.UTC));
		}

		LocalDate newestFile = Collections.max(hmap.values());
		Optional<String> optEx = hmap.entrySet().stream().filter(entry -> entry.getValue() == newestFile)
				.map(entry -> entry.getKey()).findFirst();
		System.out.printf(" Date of newest File: %s, ext: %s", newestFile, optEx.get());
	}

	public void showAllFilesSortedWithDate() {

		for (String ext : dataMap.keySet()) {
			System.out.printf("%s\n", ext);
			List<FileData> files = dataMap.get(ext);
			files.sort(Comparator.comparing(FileData::getLastModified));
			files.forEach(System.out::println);
		}

	}

}
