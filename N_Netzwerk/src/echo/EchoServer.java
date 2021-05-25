package echo;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class EchoServer {

	public static void main(String[] args) {
		System.out.println("Server wird gestartet");

		try {
			// Socket für eingehende Verbindungen erzeugen, mit einer
			// Portnummer, die kein Wellknown Port ist
			ServerSocket server = new ServerSocket(54321);
			boolean keepRunning = true;
			// Solange nicht die Aufforderung zum Beenden eingelangt ist, auf Clients warten

			while (keepRunning) {
				System.out.println("Warte auf Clients...");
				// auf den nächsten Client warten (accept blockiert, bis
				// sich ein Client verbunden hat
				Socket client = server.accept();
				// wenn der Server mehrere Clients parallel bedienen soll,
				// muss jeder Client in einem eigenen Thread laufen, zB mit einem
				// ExecutorService

				System.out.printf("Ein Client hat sich verbunden: %s\n", client.getInetAddress());
				// Streams zum Lesen und Schreiben holen
				InputStream receiveStream = client.getInputStream();
				OutputStream sendStream = client.getOutputStream();
				// ... un mit Char-Stream verknüpfen
				BufferedReader reader = new BufferedReader(new InputStreamReader(receiveStream, "UTF-8"));
				BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(sendStream, "UTF-8"));
				try {
					String line;
					while ((line = reader.readLine()) != null) {
						System.out.printf("Vom Client empfangen: %s\n", line);
						// die Zeile zurückschicken
						writer.write("ECHO: " + line);
						writer.newLine();
						// sicherstellen, dass die Daten jetzt wirklich geschickt werden
						writer.flush();
						// Wenn es der Befehl zum Beenden ist
						if (line.equalsIgnoreCase("quit")) {
							keepRunning = false;
						}

					}
				} catch (Exception e) {
					System.err.println("Fehler in Client-Kommunikation");
					e.printStackTrace();
				}
				System.out.println("Der Client hat keine weiteren Daten gesendet");
				client.close();
			} // while (keepRunning)
				// Wenn das Programm beendet wird, auch den Server-Socket schließen
			server.close();
		} catch (Exception e) {
			System.err.println("Fehler im EchoServer:");
			e.printStackTrace();
		}

	}

}
