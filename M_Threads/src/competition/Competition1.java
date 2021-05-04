package competition;

import java.util.Arrays;
import java.util.Comparator;

public class Competition1 {

	public static void main(String[] args) {

		// Die Läufer im Wettkampf
		Runner[] runnerList = { new Runner("Tick"), new Runner("Trick"), new Runner("Track") };
//		// Synchrone Ausführung
//		for (Runner runner : runnerList) {
//			runner.run();
//		}

		// Asynchrone Ausführung in mehreren Threads
		// Für jeden Läufer einen Thread erzeugen und starten
		Thread[] runnerThreads = new Thread[runnerList.length];
		for (int i = 0; i < runnerThreads.length; i++) {
			Runnable task = runnerList[i];
			// Thread-Objekt mit dem Runner als Runnable erzeugen
			// Wenn der Thread gestartet wird, führt er die run-Methode des Runner-Objekts
			// aus
			runnerThreads[i] = new Thread(task);
			// Synchron
			// runnerThreads[i].run();
			// Asynchron, start legt es in einen eigenen Thread
			runnerThreads[i].start();
		}

		System.out.println("Alle Läufer sind gestartet...");

		// Warten bis alle im Ziel sind
		try {
			// Thread.sleep(3000);
			for (Thread thread : runnerThreads) {
				// Auf das Ende des Threads warten
				thread.join();
			}
		} catch (InterruptedException e) {
			// Die InterruptedException kann nur auftreten, wenn irgendjemand
			// den Thread mit interrupt() beendet
			// -> da niemand den Main-Thread vorzeitig beendet hier einfach nur anzeigen
			System.out.println("InterruptedException in Main");
		}

		// Anzeigen welche Platzierungen die Läufer erreicht haben
		System.out.println("Ergebnis: ");
		// Sortieren nach der Platzierung
		Arrays.sort(runnerList, Comparator.comparing(Runner::getPlace));
		for (Runner runner : runnerList) {
			System.out.printf("%s: %d. Platz\n", runner.getName(), runner.getPlace());
		}

		System.out.println("Main ist zu Ende!");
	}

}
