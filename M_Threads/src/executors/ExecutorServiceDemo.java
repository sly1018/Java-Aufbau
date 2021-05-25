package executors;

import java.time.Duration;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class ExecutorServiceDemo {

	public static void main(String[] args) {
		Instant start = Instant.now();

		// Thread-Pool erzeugen (JRE entscheidet, wievele Thread gestartet werden)
		ExecutorService threadPool = Executors.newCachedThreadPool();

		List<Future<Double>> futureResults = new ArrayList<>();

		for (int i = 1; i <= 20; i++) {
			// eine Berechnung erzeugen
			Calculation calc = new Calculation();
			// ... und beim Executor-Service anmelden
			// Damit wird die Call-Implementierung in einem Thread-Pool-Thread ausgeführt
			Future<Double> futureResult = threadPool.submit(calc);
			// ... und das Future für später merken
			futureResults.add(futureResult);
		}

		System.out.println("Alle Berechnungen angemeldet");

		double sum = 0;
		// Ergebnisse abholen: Liste mit den Futures durchgehen
		for (Future<Double> future : futureResults) {
			// Das eine Ergebnis abholen
			try {
				double result = future.get();
				System.out.println("Ergebnis erhalten: " + result);
				sum += result;
			} catch (InterruptedException e) {
				// OK, kann hier nicht vorkommen
			} catch (ExecutionException e) {
				// Falls in der Berechnung ein Fehler passiert ist
				e.printStackTrace();
			}

		}

		System.out.println("Result: " + sum);

		Instant end = Instant.now();
		long ms = Duration.between(start, end).toMillis();
		System.out.printf("Time elapsed: %d ms\n", ms);
		// Den Thread-Pool beenden
		threadPool.shutdown();

	}

}
