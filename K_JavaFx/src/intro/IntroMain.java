package intro;

import java.net.URL;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

// unsere Applikationsklasse muss von der Java-FX-Applikationsklasse ableiten
public class IntroMain extends Application {

	public static void main(String[] args) {
		// Die Methode erzeugt ein Objekt von unserer Applikationsklasse,
		// bereitet alles vor und ruft dann unsere start-Implementierung auf
		launch(args);
	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		// den View erzeugen
		IntroView root = new IntroView();
		// Scene erstellen
		Scene scene = new Scene(root, 300, 200);
		// Das Stylesheet bekanntgeben
		URL styleUrl = getClass().getResource("intro.css");
		scene.getStylesheets().add(styleUrl.toExternalForm());
		// und im Hauptfenstere setzen
		primaryStage.setScene(scene);
		// Titel setzen
		primaryStage.setTitle("Erstes Java FX Fenster");
		// Und das Hauptfenster anzeigen
		primaryStage.show();
	}

}
