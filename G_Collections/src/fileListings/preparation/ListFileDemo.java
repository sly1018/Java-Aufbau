package fileListings.preparation;

import java.io.File;
import java.time.Instant;

public class ListFileDemo {

	public static void main(String[] args) {

		String dirName = args.length == 0 ? "." : args[0];

		showContent(new File(dirName));

	}

	static void showContent(File dir) {
		System.out.printf("Verzeichnis %s:\n", dir.getAbsolutePath());

		File[] files = dir.listFiles();

		for (File file : files) {
			// Wenn es ein File ist
			if (file.isFile()) {
				String filePath = file.getAbsolutePath();

				String name = file.getName();

				// Größe in Byte
				long size = file.length();

				long lastModifiedMs = file.lastModified();
				Instant lastModified = Instant.ofEpochMilli(lastModifiedMs);

				System.out.printf("\tFile %s - %d Byte - %s - %s\n", name, size, lastModified, filePath);
			} else if (file.isDirectory()) {
				// System.out.printf("\tUnterverzeichnis %s\n", file.getName());
				// Rekursiv aufrufen
				showContent(file);
			}
		}
	}

}
