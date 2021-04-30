package animals.funcInterfaces;

import java.util.Comparator;
import java.util.function.Predicate;

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
		animals.showAnimals(Predicate.not(Animal::isHerbivore));

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
		//animals.sortiere((a1, a2) -> a2.getWeight() - a1.getWeight());
		//
		animals.sortiere(Comparator.comparing(Animal::getWeight));
		System.out.println("Tiere sortiert nach Gewicht:");
		animals.showAll();

		// Nach Pflanzenfresser und Gewicht sortiert.
		// Satt Lambda Expression mit eigener Berechnung
//		animals.sortiere((a1, a2) -> {
//			int ret = Boolean.compare(a1.isHerbivore(), a2.isHerbivore());
//			// Wenn gleich -> nach Gewicht aufsteigend
//			if (ret == 0)
//				ret = Integer.compare(a1.getWeight(), a2.getWeight());
//			return ret;
//		});
		
		// animals.sortiere(Comparator.comparing((Animal a)-> a.getWeight()).reversed());
		animals.sortiere(Comparator.comparing(Animal::getWeight).reversed());
		System.out.println("Tiere sortiert nach Gewicht absteigen.:");
		animals.showAll();
	}

}
