package fileStatisticsWithStreamAPI;

import java.io.File;
import java.time.Instant;
import java.util.ArrayList;
import java.util.IntSummaryStatistics;
import java.util.Iterator;
import java.util.List;
import java.util.LongSummaryStatistics;
import java.util.Map;
import java.util.Optional;
import java.util.Scanner;
import java.util.TreeMap;
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
		this.showNewestOldestFilePerExtensionWithOptional();
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
		for (String ext : dataMap.keySet()) {
			System.out.printf("%s\n", ext);
			List<FileData> files = dataMap.get(ext);
			LongSummaryStatistics summary = files.stream().mapToLong(f -> f.getSize()).summaryStatistics();
			System.out.printf("[sum=%d], [avg=%.2f], [max=%d]\n", summary.getSum(), summary.getAverage(),
					summary.getMax());
		}
	}

	public void showNewestOldestFilePerExtensionWithOptional() {
		for (String ext : dataMap.keySet()) {
			System.out.printf("%s\n", ext);
			List<FileData> files = dataMap.get(ext);

			Stream.of(files).sorted();
		}
	}

}
