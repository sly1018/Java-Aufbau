package students.program;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class StudentTableMain extends Application {

	public static void main(String[] args) {
		launch(args);

	}

	@Override
	public void start(Stage primaryStage) throws Exception {

		FXMLLoader loader = new FXMLLoader(getClass().getResource("/students/views/StudentTableView.fxml"));
		Parent root = loader.load();

		// den Controller holen
		StudentTableController controller = loader.getController();
		// und das Repository setzen
		controller.setRepositoryPath("Repository.xml");

		Scene scene = new Scene(root, 800, 700);
		// das CSS-Stylesheet laden und setzen
		scene.getStylesheets().add(getClass().getResource("/students/views/studentStyles.css").toExternalForm());
		// das ganze anzeigen
		primaryStage.setScene(scene);
		primaryStage.setTitle("TableView Demo");
		primaryStage.show();

	}

}
