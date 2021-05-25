package tasks;

import java.util.concurrent.ThreadLocalRandom;

import javafx.concurrent.Task;

// Task ist eine Basisklasse, die die Benachrichtigung über Fortschritt und Status
// sowie eine Möglichkeit zum Abbrrechen des Tasks enthält
public class CalcTask extends Task<Double> {

	private int count;

	public CalcTask(int count) {
		this.count = count;
	}

	// Synchrone Variante, ohne Fortschritt oder Cancel
	public double runCalculation(int count) {
		double sum = 0;
		for (int i = 1; i <= count; i++) {
			double value = calcValue();
			sum += value;
		}

		return sum;
	}

	@Override
	protected Double call() throws Exception {
		System.out.printf("Calctask.call in Thread %s\n", Thread.currentThread().getName());
		double sum = 0;
		for (int i = 1; i <= count; i++) {
			// In jedem Durchlauf prüfen, ob der Task abgebrochen wurde
			if (isCancelled()) {
				updateMessage("Task wurde bei Schritt %d abgebrochen".formatted(i));
				// Aus der Schleife raus -> Task beenden
				break;
			}

			double value = calcValue();
			sum += value;

			// Den Fortschritt aktualisieren (es sind i von count Schritten erledigt)
			// damit wird die progressProperty deses Tasks aktualisiert
			updateProgress(i, count);
			// Status-Message anpassen
			// Damit wird die messageProperty dieses Tasks aktualisiert
			updateMessage("Schritt %d von %d, Summe bisher: %.2f".formatted(i, count, sum));
		}

		return sum;
	}

	private double calcValue() {
		double result = 0;
		// System.out.println("calculating next value ...");
		ThreadLocalRandom random = ThreadLocalRandom.current();
		for (int i = 0; i < 1000000; i++) {
			if (i % 2 == 0)
				result += random.nextDouble(50);
			else
				result -= random.nextDouble(50);
		}
		return result;
	}

}
