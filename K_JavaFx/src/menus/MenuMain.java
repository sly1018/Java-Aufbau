package menus;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class MenuMain extends Application {

	@Override
	public void start(Stage primaryStage) throws Exception {

		try {
			// den Root des Scene Graph aus dem FXML-File laden
//			Parent root = (Parent) 
//					FXMLLoader.load(getClass().getResource("MenuWindow.fxml"));
			// FXMLLoader Instanz erzeugen
			FXMLLoader myLoader = new FXMLLoader(getClass().getResource("MenuWindow.fxml"));
			// Damit den View laden
			Parent root = myLoader.load();
			MenuWindowController myController = myLoader.getController();

			// Über das FXMLLoader bekommen wir Zugriff auf das Controller-Objekt
			// final MenuWindowController controller = loader.getController();

			Scene scene = new Scene(root, 400, 300);
			// das CSS-Stylesheet wird im FXML-File gesetzt
			// scene.getStylesheets().add(getClass().getResource("menu.css").toExternalForm());
			// das ganze anzeigen
			primaryStage.setScene(scene);
			primaryStage.setTitle("Menu demo");

			primaryStage.setOnCloseRequest(we -> {
				// Verhindern dass das Event weiter verarbeitet wird
				we.consume();

				// die Close-Methode von Controller aufrufen
				myController.close();
			});

			primaryStage.show();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		launch(args);

	}

}
