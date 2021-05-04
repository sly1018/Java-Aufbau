package competition;

public class Competition0 {

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
			runnerThreads[i] = new Thread(task);
			// die Threads als daemon-Threads ausführen
			runnerThreads[i].setDaemon(true);
			// Wenn der Thread gestart wird, fürht er die run-Methode des Runner-Objects aus
			runnerThreads[i].start();
		}

		System.out.println("Alle Läufer sind gestartet...");

		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			System.out.println("InterruptedException");
		}

		System.out.println("Main ist zu Ende!");
	}

}
