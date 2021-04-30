package animals.streamAPI;

import java.util.Comparator;
import java.util.IntSummaryStatistics;
import java.util.Optional;
import java.util.OptionalDouble;
import java.util.OptionalInt;
import java.util.stream.Stream;

public class intStreamDemo {

	public static void main(String[] args) {

		Animal[] allAnimals = new Animal[] { new Animal("Löwe", "Panthera leo", 250, false),
				new Animal("Waschbär", "Procyon", 12, false), new Animal("Bison", "Bison bison", 950, true),
				new Animal("Elefant", "Loxodonta", 4000, true), new Animal("Eisbär", "Ursus maritimus", 650, false),
				new Animal("Emu", "Dromaius novaehollandiae", 37, false) };

		// Nur das Gewicht verarbeiten
		// map liefert zu einem Animal Objekt ein anderes Objekt (nicht einen primit.
		// Wert)
		Stream.of(allAnimals).map(Animal::getWeight).min(Comparator.naturalOrder()).ifPresentOrElse(
				w -> System.out.println("Min Gewicht: " + w), () -> System.out.println("Kein Tier vorhanden"));

		// mapToInt liefert zu einem Animal-Objekt einen int Wert (einen primitiven
		// Wert)
		OptionalInt optInt = Stream.of(allAnimals).mapToInt(Animal::getWeight).max();
		if (optInt.isPresent()) {
			System.out.println("max. Gewicht: " + optInt.getAsInt());
		}
		// Für primit. Stream gibt es sum und average-Methoden
		int sumGewicht = Stream.of(allAnimals).mapToInt(Animal::getWeight).sum();
		System.out.println("Summe: " + sumGewicht);

		OptionalDouble avgGewicht = Stream.of(allAnimals).mapToInt(Animal::getWeight).average();

		if (avgGewicht.isPresent()) {
			System.out.println("Durchschnitt: " + avgGewicht.getAsDouble());
		}

		IntSummaryStatistics summary = Stream.of(allAnimals).mapToInt(Animal::getWeight).summaryStatistics();
		System.out.printf("Statistik: count=%d, min=%d, max=%d, sum=%d, avg=%.2f \n", summary.getCount(),
				summary.getMin(), summary.getMax(), summary.getSum(), summary.getAverage());
	}

}
