package calcFX;

import java.net.URL;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class CalcProgramm extends Application {

	public static void main(String[] args) {
		launch(args);
	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		// den View erzeugen
		ViewCalc root = new ViewCalc();
		
		// Scene erstellen
		Scene scene = new Scene(root, 300, 250);
		
		// Das Styling bekanntgeben
		URL styleUrl = getClass().getResource("Styling.css");
		scene.getStylesheets().add(styleUrl.toExternalForm());
		
		// und im Hauptfenstere setzen
		primaryStage.setScene(scene);
		// Titel setzen
		primaryStage.setTitle("Rechner");
		// Und das Hauptfenster anzeigen
		primaryStage.show();
	}

}
