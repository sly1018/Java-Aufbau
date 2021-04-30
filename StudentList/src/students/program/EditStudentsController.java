package students.program;

import common.MessageBox;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.Stage;
import students.repository.Gender;
import students.repository.Student;

public class EditStudentsController {
	@FXML
	private TextField txtAreaCode;
	@FXML
	private TextField txtCity;
	@FXML
	private DatePicker dtpBirthdate;
	@FXML
	private RadioButton rbMale;
	@FXML
	private ToggleGroup grpGender;
	@FXML
	private RadioButton rbFemale;
	@FXML
	private RadioButton rbOther;
	@FXML
	private CheckBox cbXml;
	@FXML
	private CheckBox cbHtml;
	@FXML
	private CheckBox cbFxml;
	@FXML
	private TextArea txtComment;
	@FXML
	private ComboBox<String> cmbLanguage;
	@FXML
	private TextField txtName;
	@FXML
	private TextField txtId;
	@FXML
	private Button btnOk;
	@FXML
	private Button btnCancel;

	// Den Student merken, der bearbeitet wird,
	private Student studentResult;

	@FXML
	public void initialize() {
		System.out.println("Initialisierung...");
		cmbLanguage.getItems().add("Deutsch");
		cmbLanguage.getItems().add("Dari");
		cmbLanguage.getItems().add("Pashto");
		cmbLanguage.getItems().add("Russkiy");

		checkValid();

		txtName.textProperty().addListener((o, oldval, newval) -> checkValid());
		txtAreaCode.textProperty().addListener((o, oldval, newval) -> checkValid());
		txtCity.textProperty().addListener((o, oldval, newval) -> checkValid());
		dtpBirthdate.valueProperty().addListener((o, oldval, newval) -> checkValid());
		cmbLanguage.valueProperty().addListener((o, oldval, newval) -> checkValid());
		grpGender.selectedToggleProperty().addListener((o, oldval, newval) -> checkValid());

	}

	public void setStudent(Student editStudent) {
		// Die Werte aus dem Objekt anzeigen
		if (editStudent != null) {
			txtId.setText(Integer.toString(editStudent.getId()));
			txtName.setText(editStudent.getName());
			txtCity.setText(editStudent.getCity());
			txtComment.setText(editStudent.getComment());
			txtAreaCode.setText(Integer.toString(editStudent.getAreaCode()));

			// Checkboxen
			cbXml.setSelected(editStudent.isXml());
			cbXml.setSelected(editStudent.isHtml());
			cbXml.setSelected(editStudent.isFxml());

			// Combobox: setValue bekommt einen Parameter vom gen. Typargument, bei uns
			// String
			cmbLanguage.setValue(editStudent.getLanguage());

			// Date Picker: Property value ist vom Type LocalDate
			dtpBirthdate.setValue(editStudent.getBirthDate());

			// Radiobuttons
			if (editStudent.getGender() != null) {
				switch (editStudent.getGender()) {
				case MALE -> rbMale.setSelected(true);
				case FEMALE -> rbFemale.setSelected(true);
				default -> rbOther.setSelected(true);
				}
			} else {
				rbOther.setSelected(true);
			}
		}
	}

	public Student getStudent() {
		return studentResult;
	}

	// Event Listener on Button[#btnOk].onAction
	@FXML
	public void onOk(ActionEvent event) {
		try {
			studentResult = new Student();
			// Die Werte aus den Controls setzen
			if (txtId.getText() != null && !txtId.getText().isEmpty()) {
				studentResult.setId(Integer.parseInt(txtId.getText()));
			}

			studentResult.setAreaCode(Integer.parseInt(txtAreaCode.getText()));
			studentResult.setName(txtName.getText());
			studentResult.setCity(txtCity.getText());
			studentResult.setComment(txtComment.getText());

			studentResult.setXml(cbXml.isSelected());
			studentResult.setHtml(cbHtml.isSelected());
			studentResult.setFxml(cbFxml.isSelected());

			studentResult.setLanguage(cmbLanguage.getValue());

			studentResult.setBirthDate(dtpBirthdate.getValue());

			if (rbMale.isSelected()) {
				studentResult.setGender(Gender.MALE);
			} else if (rbFemale.isSelected()) {
				studentResult.setGender(Gender.FEMALE);
			} else {
				studentResult.setGender(Gender.OTHER);
			}
			// Das Fenster schließen (damit kehr showAndWaitZurück)
			((Stage) txtId.getScene().getWindow()).close();
		} catch (Exception e) {
			// Das Student-Objekt kann nicht verwendet werden.
			studentResult = null;
			e.printStackTrace();
			MessageBox.show("Erfassen", "Fehler beim Erfassen" + e.getMessage(), AlertType.ERROR, ButtonType.OK);

		}
	}

	// Event Listener on Button[#btnCancel].onAction
	@FXML
	public void onCancel(ActionEvent event) {
		studentResult = null;
		// Das Fenster schließen (damit kehr showAndWait zurück)
		((Stage) txtId.getScene().getWindow()).close();
	}

	private void checkValid() {
		boolean valid = isInputValid();
		if (valid) {
			btnOk.setDisable(false);
		} else
			btnOk.setDisable(true);
	}

	private boolean isInputValid() {
		boolean valid = txtName.getText() != null && !txtName.getText().isBlank() && txtAreaCode.getText() != null
				&& !txtAreaCode.getText().isBlank() && txtCity.getText() != null && !txtCity.getText().isBlank()
				&& dtpBirthdate.getValue() != null && cmbLanguage.getValue() != null
				&& grpGender.getSelectedToggle() != null;

		System.out.printf("Check Valid: isValid = %s\n", valid);
		return valid;
	}
}
