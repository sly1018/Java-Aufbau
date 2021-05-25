package tasks;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.ExecutionException;

import javafx.application.Platform;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.util.converter.NumberStringConverter;

public class TasksController {

	@FXML
	private TextField txtCount;

	@FXML
	private Label lblMessage;

	@FXML
	private ProgressBar barProgress;

	@FXML
	private HBox boxFeedback;

	@FXML
	private Label lblIteration;

	@FXML
	private Button btnStart;

	@FXML
	private Button btnCancel;

	@FXML
	private Label lblStatus;
	// Property für die das Textfeld mit der Anzahl
	private IntegerProperty count = new SimpleIntegerProperty(2000);

	// Timer aus java.util, verwendbar in allen Java Anwendungen
	private Timer myTimer;

	@FXML
	private void initialize() {
		txtCount.textProperty().bindBidirectional(count, new NumberStringConverter());
		barProgress.setProgress(0);

		// Timer erzeugen
		myTimer = new Timer("Uhrzeit-Timer");
		// ... und starten
		myTimer.schedule(
				// Die Aktion die jeweils asugeführt werden soll
				new TimerTask() {

					@Override
					public void run() {
						onTimerBreak();

					}
				},
				// Wann soll es zum ersten mal ausgeführt werden
				500,
				// In welchem Intervall
				1000);

		// Den Timer beenden, wenn das Fenster geschlossen wird
		// (Wir verwenden Platform.runLater damit das Window vollständig erzeugt und
		// initialisiert ist)
		Platform.runLater(() -> {

			lblStatus.getScene().getWindow().addEventFilter(javafx.stage.WindowEvent.WINDOW_CLOSE_REQUEST, we -> {
				System.out.println("Fenster wird geschlossen -> Timer wird beendet");
				myTimer.cancel();
			});

		});

	}

	private void onTimerBreak() {
//		System.out.printf("TimerBreak in Thread %s, Uhrzeit: %s\n", Thread.currentThread().getName(),
//				LocalTime.now().toString());
		// Der Thread in dem der Callback ausgeführt wird, ist NICHT der Hauptthread des
		// Fensters
		// Daher ist hier kein Zugriff auf die Controls erlaubt
		// lblStatus.setText(LocalTime.now().toString());

		// -> die Zugriffe auf den Hauptthread des Fensters (FX application thread)
		// "umleiten"
		Platform.runLater(() -> {
			System.out.printf("Platform.runLater in Thread %s\n", Thread.currentThread().getName());

			DateTimeFormatter fmt = DateTimeFormatter.ofLocalizedTime(FormatStyle.MEDIUM);
			lblStatus.setText(fmt.format(LocalTime.now()));
		});

	}

	private CalcTask task;

	@FXML
	private void startTask(ActionEvent event) {
		System.out.println("startTask called in Thread " + Thread.currentThread().getName());
		lblMessage.setText("Processing task...");

		// Ein Task-Objekt erzeugen, mit dem Wert der count-Property als Anzahl
		task = new CalcTask(count.get());

		// Synchron
//		double result = task.runCalculation(count.get());
//		lblMessage.setText("Task succeeded, result=" + result);

		// Asynchron
		// Fortschritt aktualisieren (Callback läuft im JavaFX application Thread)
		task.progressProperty().addListener((o, oldVal, newVal) -> {
			System.out.printf("Progress changed in Thread %s: oldVal=%.2f, newVal=%.2f",
					Thread.currentThread().getName(), oldVal, newVal);
			// Den neuen Wert im Progress bar setzen
			barProgress.setProgress(newVal.doubleValue());
		});
		// barProgress.progressProperty().bind(task.progressProperty());

		// Statt listener kann man auch Binding verwenden:
		// Die Text-Property des Labels an die MessageProperty des Tasks binden
		lblMessage.textProperty().bind(task.messageProperty());

		task.setOnSucceeded(wse -> {
			try {
				// Das Ergebnis abholen (das was der task aus call zurückgeliefert hat)
				double result = task.get();
				System.out.printf("Task Succeeded in Thread %s, Ergebnis=%.2f\n", Thread.currentThread().getName(),
						result);

				// binding im Label aufheben
				lblMessage.textProperty().unbind();
				lblMessage.setText("Task abgeschlossen, Ergebnis=%.2f".formatted(result));
			} catch (InterruptedException e) {
				// OK, nur wir selber könnten unserem Thread die interrupt-Nachricht senden
			} catch (ExecutionException e) {
				// evtl. in Alert anzeigen
				e.printStackTrace();
			}
		});

		task.setOnCancelled(wse -> lblMessage.textProperty().unbind());

		// Den Task in einem eigenen Thread starten, damit das UI reaktiv bleibt
		new Thread(task).start();

	}

	@FXML
	private void cancelTask(ActionEvent event) {
		if (task != null) {
			task.cancel();
		}
	}

}
