package animals.sort;

import java.util.Arrays;
import java.util.Comparator;

public class SortAnimals {

	public static void main(String[] args) {
		Animal[] allAnimals = new Animal[] { new Animal("Löwe", "Panthera leo", 250, false),
				new Animal("Waschbär", "Procyon", 12, false), new Animal("Bison", "Bison bison", 950, true),
				new Animal("Elefant", "Loxodonta", 4000, true), new Animal("Eisbär", "Ursus maritimus", 650, false),
				new Animal("Emu", "Dromaius novaehollandiae", 37, false) };

		zeigeTiere("Tiere unsortiert: ", allAnimals);

		Arrays.sort(allAnimals);

		zeigeTiere("Tiere sortiert: ", allAnimals);

		ByWeightComparator comparator = new ByWeightComparator();
		Arrays.sort(allAnimals, comparator);
		zeigeTiere("Sortiert nach Gewicht", allAnimals);
		
		Comparator<Animal> cmp = new ByHerbivorComparator();
		Arrays.sort(allAnimals, cmp);
		zeigeTiere("Sortiert nach Pflanzenfresser und lat. Namen: ", allAnimals);
	}

	static void zeigeTiere(String info, Animal[] allAnimals) {
		System.out.println(info);
		// Alle Tiere anzeigen
		for (Animal animal : allAnimals) {
			System.out.println(animal);
		}
		System.out.println();
	}

}
