package introFxml;

import java.net.URL;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

// unsere Applikationsklasse muss von der JavaFX-Applikationsklasse ableiten
public class IntroFxmlMain extends Application {

	public static void main(String[] args) {
		// die Methode erzeugt ein Objket von unserer Applikationsklasse,
		// bereitet alles vor und ruft dann unsere start-Implementierung auf
		launch(args);

	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		// Einen URL zum FXML-File holen(das FXML-File liegt im selben Package wie die
		// Applicationklasse)
		// Den Control-Tree aus diesem FXML-File laden
		URL fxmlUrl = getClass().getResource("IntroView.fxml");
		Parent root = (Parent) FXMLLoader.load(fxmlUrl);

		// Scene erstellen
		Scene scene = new Scene(root, 300, 200);
		// und im Hauptfenster setzen
		primaryStage.setScene(scene);
		// titel setzten
		primaryStage.setTitle("Erstes Java FX Fenster");
		// und das hauptfenster anzeigen
		primaryStage.show();

	}

}
