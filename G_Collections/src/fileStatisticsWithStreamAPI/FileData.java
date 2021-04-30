package fileStatisticsWithStreamAPI;

import java.time.Instant;

public class FileData {

	// Attribute für name, path, size und lastModified
	String name, path;
	long size;
	Instant lastModified;

	// Passender Konstruktor, Werte über Konstruktor, können auch final definiert
	// werden.
	public FileData() {
	}

	public FileData(String n, String p, long s, Instant lm) {
		this.name = n;
		this.path = p;
		this.size = s;
		this.lastModified = lm;
	}

	// getter für alle Attribute
	public String getName() {
		return name;
	}

	public String getPath() {
		return path;
	}

	public long getSize() {
		return size;
	}

	public Instant getLastModified() {
		return lastModified;
	}

	// toString
	@Override
	public String toString() {
		return "\tname: [" + name + "]\n\t\t\t" + "path: [" + path + "]\n\t\t\t" + "size: [" + size + "]\n\t\t\t" + "lastModified: ["
				+ lastModified + "]\n\t\t\t";
	}

}
