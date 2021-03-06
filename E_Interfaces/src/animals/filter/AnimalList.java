package animals.filter;

/**
 * diese Klasse enthält ein Array von Animal-Objekten.
 * 
 * @author Michaela
 *
 */
public class AnimalList {

	private Animal[] allAnimals = new Animal[] { new Animal("Löwe", "Panthera leo", 250, false),
			new Animal("Waschbär", "Procyon", 12, false), new Animal("Bison", "Bison bison", 950, true),
			new Animal("Elefant", "Loxodonta", 4000, true), new Animal("Eisbär", "Ursus maritimus", 650, false),
			new Animal("Emu", "Dromaius novaehollandiae", 37, false)

	};

	// Alle Tiere anzeigen.
	public void showAll() {
		for (int i = 0; i < allAnimals.length; i++) {
			System.out.println(allAnimals[i]);
		}
		System.out.println();
	}

	// Nur die Tiere anzeigen die dem Filter entsprechen.
	public void showAnimals(AnimalFilter myFilter) {
		for (int i = 0; i < allAnimals.length; i++) {
			// Wenn das Filter-Objekt für dieses Tier true liefert
			// Wir führen hier einen Callback aus: die IsTrueFor-Methode
			// das Filter-Objekt das vom
			// Aufrufer übergeben wurde, wird ausgeführt
			if (myFilter.isTrueFor(allAnimals[i])) {
				System.out.println(allAnimals[i]);
			}
		}
		System.out.println();
	}
}
