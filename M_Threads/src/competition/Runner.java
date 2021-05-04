package competition;

import java.util.Random;

public class Runner implements Runnable {

	private static int nextPlace = 1;
	// synch-Objekt für den Zugriff auf das statische Feld:
	private static final Object syncObject = new Object();

	private String name;

	// Für die Platzierung
	private int place;

	public Runner(String name) {
		this.name = name;
	}

	public int getPlace() {
		return place;
	}

	public String getName() {
		return name;
	}

	@Override
	public void run() {
		System.out.printf("run-Methode für %s in Thread %s\n", name, Thread.currentThread().getName());
		Random random = new Random();

		for (int i = 1; i <= 5; i++) {
			System.out.printf("%s beginnt mit Runde %d\n", name, i);
			try {
				Thread.sleep(random.nextInt(500));
			} catch (InterruptedException e) {
				// Die InterruptedException kann nur auftreten, wenn irgendjemand
				// den Thread mit interrupt() beendeet
				// -> da der Wettkampf nicht vorzeitig beendet wird, hier einfach nur anzeigen
				System.out.println("InterruptedException erhalten");
			}
		}

		synchronized (syncObject) {
			System.out.printf("%s ist fertig!\n", name);
			// Die Platzierung aholen
			finish();
		}
	}

	// synchronized method geht nicht weil wir pro Instanz einen Thread haben
	private void finish() {
		// Einen kritischen Abschnit für den Zugriff auf das statische Feld verwenden
		// nur 1 Thread darf gleichzeitg diesen Code-Abschnitt ausführen,
		// andere müssen ggf warten bis der vorherige Threada fertig ist
		synchronized (syncObject) {

			place = nextPlace;
			System.out.printf("%s erreicht Platz %d\n", name, place);

			try {
				Thread.sleep(200);
			} catch (InterruptedException e) {
				System.out.println("InterruptedException");
			}
			nextPlace++;
		}
	}

}
