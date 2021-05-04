package competition;

import java.util.Comparator;
import java.util.stream.Stream;

public class Competition2 {

	public static void main(String[] args) {
		// Die Läufer im Wettkampf
		Runner[] runnerList = { new Runner("Tick"), new Runner("Trick"), new Runner("Track") };

		// Thread-Objekte erzeugen
		Thread[] runnerThreads = Stream.of(runnerList)
				// Statt Lambda Expression
				// .map(r -> new Thread(r))
				// Constructor reference
				.map(Thread::new)
				// Statt Lambda Expression
				// .toArray(size -> new Thread[size]);
				// Constructor reference zum Thread[]-Konstruktor
				.toArray(Thread[]::new);

		// ... und alle startetn.
		Stream.of(runnerThreads).forEach(t -> t.start());

		// Warten bis alle im Ziel sind
		Stream.of(runnerThreads).forEach(t -> {
			try {
				t.join();
			} catch (InterruptedException e) {
				System.out.println("InterruptedException");
			}
		});

		// Ergebnis anzeigen
		System.out.println("Ergebnis");
		Stream.of(runnerList)
				// Statt Lambda Expression
				// .sorted(Comparator.comparing(r ->r.getPlace()))
				// Method reference
				.sorted(Comparator.comparing(Runner::getPlace))
				.forEach(r -> System.out.printf("%s: %d. Platz\n", r.getName(), r.getPlace()));

	}

}
