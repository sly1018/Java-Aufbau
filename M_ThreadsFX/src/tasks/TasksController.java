package tasks;

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

	@FXML
	private void initialize() {
		txtCount.textProperty().bindBidirectional(count, new NumberStringConverter());
		barProgress.setProgress(0);
	}

	@FXML
	private void startTask(ActionEvent event) {
		System.out.println("startTask called in Thread " + Thread.currentThread().getName());
		lblMessage.setText("Processing task...");

		CalcTask task = new CalcTask();

		double result = task.runCalculation(count.get());
		lblMessage.setText("Task succeeded, result=" + result);

	}

	@FXML
	private void cancelTask(ActionEvent event) {
	}

}
