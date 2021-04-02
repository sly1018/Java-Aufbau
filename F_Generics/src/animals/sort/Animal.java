package animals.sort;

import nonGeneric.ObjectStackDemo2;

/**
 * Klasse für ein (Zoo-)Tier mit mehreren Eigenschaften wie Name, Lateinischer
 * Name oder Gewicht
 * 
 * @author Michaela
 *
 */
public class Animal implements Comparable<Animal> {
	private String name, latinName;

	private int weight;

	private boolean herbivore;

	public Animal(String name, String lateinischerName, int gewicht, boolean pflanzenfresser) {
		this.name = name;
		this.latinName = lateinischerName;
		this.weight = gewicht;
		this.herbivore = pflanzenfresser;
	}

	public String getName() {
		return name;
	}

	public String getLatinName() {
		return latinName;
	}

	public int getWeight() {
		return weight;
	}

	public boolean isHerbivore() {
		return herbivore;
	}

	@Override
	public String toString() {
		return String.format("%s, %s - %d kg - Vegetarier: %s", name, latinName, weight, herbivore ? "ja" : "nein");
	}

	// Standardsortierreihenfolge für Animal-Objekte
	@Override
	public int compareTo(Animal o2) {
		// Das 2. Objekt auf null prüfen
		if(o2 == null) {
			// Ein Objekt ist größer als Null-Referenz
			return 1;
		}
		// Sortieren nach Name
		int ret = this.name.compareTo(o2.name);
		System.out.printf("Vergleiche %s mit %s: %d\n", this.name, o2.name, ret);

		return ret;
	}

}
