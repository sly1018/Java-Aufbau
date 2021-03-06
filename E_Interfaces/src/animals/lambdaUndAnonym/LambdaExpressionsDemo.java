package animals.lambdaUndAnonym;

import java.util.Comparator;

public class LambdaExpressionsDemo {

	public static void main(String[] args) {
		AnimalList animals = new AnimalList();
		animals.showAll();

		// Statt anonymer Klasse

//		AnimalFilter filter1 = new AnimalFilter() {
//			// Implementierung für die isTrueFor-Methode
//			@Override
//			public boolean isTrueFor(Animal a) {
//				return a.isHerbivore();
//			}
//		}; // Strichpunkt schließt die Deklaration ab

		AnimalFilter filter1 = (a) -> {
			// Implementierung der Methode isTrueFor ohne Methoden-Gerüst
			return a.isHerbivore();
		};

		System.out.println("Pflanzenfresser:");
		System.out.println("Filterobjekt: " + filter1.getClass().getName());
		animals.showAnimals(filter1);

		// Lambda Expression ohne Hilfsvariable
		System.out.println("Fleischfresser:");
		// Klammern um Argumente und Blockklammern dürfen hier weggelassen werden.
		animals.showAnimals(a -> !a.isHerbivore());

		// Tiere sortieren mit Comparator
		// Statt anonymer Klasse
//		animals.sortiere(new Comparator<Animal>() {
//
//			@Override
//			public int compare(Animal o1, Animal o2) {
//				return o2.getWeight() - o1.getWeight();
//			}
//		});

		// Lambda Expression
		animals.sortiere((a1, a2) -> a2.getWeight() - a1.getWeight());

		System.out.println("Tiere sortiert:");
		animals.showAll();

		// Nach Pflanzenfresser und Gewicht sortiert.
		animals.sortiere((a1, a2) -> {
			int ret = Boolean.compare(a1.isHerbivore(), a2.isHerbivore());
			// Wenn gleich -> nach Gewicht aufsteigend
			if (ret == 0)
				ret = Integer.compare(a1.getWeight(), a2.getWeight());
			return ret;
		});
		System.out.println("Nach Pflanzenfresser und Gewicht sortiert:");
		animals.showAll();
	}

}
