package animals.lambdaUndAnonym;

import java.util.Comparator;

public class AnonymeKlasseDemo {

	public static void main(String[] args) {
		AnimalList animals = new AnimalList();
		animals.showAll();

		// Anonyme Interface Implementierung:
		// Es wird eine (anynome) Klasse definieret und 1 Instanz von dieser Klasse
		// erzeugt
		AnimalFilter filter1 = new AnimalFilter() {
			// Implementierung für die isTrueFor-Methode
			@Override
			public boolean isTrueFor(Animal a) {
				return a.isHerbivore();
			}

			// Zusätzliche Funktionalität
			@Override
			public String toString() {
				// TODO Auto-generated method stub
				return "Filterung nach Pflanzenfresser";
			}
		}; // Strichpunkt schließt die Deklaration ab

		System.out.println("Pflanzenfresser:");
		System.out.println("Filterobjekt: " + filter1);
		animals.showAnimals(filter1);

		// Anonyme Klasse ohne Hilfsvariable
		System.out.println("Fleischfresser:");

		animals.showAnimals(new AnimalFilter() {

			@Override
			public boolean isTrueFor(Animal a) {
				return !a.isHerbivore();
			}

		});

		// Tiere sortieren mit Comparator
		animals.sortiere(new Comparator<Animal>() {

			@Override
			public int compare(Animal o1, Animal o2) {
				return o2.getWeight() - o1.getWeight();
			}
		});

		System.out.println("Tiere sortiert:");
		animals.showAll();
	}

}
