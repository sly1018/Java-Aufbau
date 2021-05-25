package intro;

// Variante 2 der Thread-Erzeugung: von Thread ableiten
public class MyEndlessThread extends Thread {

	public MyEndlessThread(String name) {
		super(name);
	}

	@Override
	public void run() {

		// solange der Thread nicht beendet wurde (solange das interrupted-Flag nicht
		// gesetzt ist)
		for (int i = 1; !isInterrupted(); i++) {
			System.out.printf("%s: Durchlauf %d %n", getName(), i);
			try {
				Thread.sleep((long) (Math.random() * 700));
			} catch (InterruptedException e) {
				// Wenn die InterruptedException ausgelöst wird,
				// wurde dem Thread die interrupt-Nachricht gesendet.
				// -> der Thread soll sich beenden
				System.out.printf("Thread %s wurde mit interrupt beendet\n", getName());
				// Die Schleife verlassen, damit wird run beendet
				break;
			}
		}
		// Hier könnte man noch Aufräumarbeiten machen
	}
}
