package students.program;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.util.List;

import common.MessageBox;
import javafx.beans.binding.Bindings;
import javafx.beans.property.ListProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleListProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.CheckBoxTableCell;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import students.repository.Student;
import students.repository.StudentRepository;
import students.repository.xml.StudentXmlRepository;

public class StudentTableController {

	// Zugriff auf das Repository
	private StudentRepository repository;

	@FXML
	private Button btnAdd;

	@FXML
	private Button btnEdit;

	@FXML
	private Button btnDelete;

	@FXML
	private Button btnReload;

	// Statt ListView haben wir einen TableView
	// Für ListView und Student VBox
	// ListView<Student> lstStudents;
	@FXML
	private TableView<Student> tblStudents;

	@FXML
	VBox boxStudent;

	@FXML
	private Label lblId;
	@FXML
	private Label lblName;
	@FXML
	private Label lblAreaCode;
	@FXML
	private Label lblCity;

	// Spalten für den TableView
	@FXML
	private TableColumn<Student, Integer> colId;

	@FXML
	private TableColumn<Student, String> colName;

	@FXML
	private TableColumn<Student, LocalDate> colBirthdate;

	@FXML
	private TableColumn<Student, Boolean> colXml;

	@FXML
	private TableColumn<Student, Boolean> colHtml;

	// Alt: Feld für das Objekt das in der Liste selektiert ist
	// private Student selStudent;

	// Für das Property Binding:
	// 1) binden des ListView an eine ListProperty
	private ListProperty<Student> students;

	// 2) Binden des selektierten Student an eine ObjectProperty
	// Statt einem "normalen" Feld -> eine Observable Property definieren
	// ObjectProperty informiert ihre Listener, wenn ein anderes Objekt gesetzt wird
	private ObjectProperty<Student> selectedStudent;

	public StudentTableController() {
		// Die Properties für das Binding erzeugen
		students = new SimpleListProperty<>();
		selectedStudent = new SimpleObjectProperty<>();
	}

//	// Property, die vom FXML per Binding-Ausdruck gesetzt werden kann
//	public ListProperty<Student> getStudentList() {
//		return students;
//	}

	// Observable Property für den SelectedStudent für Verwendung im FXML
	// bereitstellen
	// a) Getter
	public Student getSelectedStudent() {
		// Den Student aus der ObjectProperty zurückliefern
		return selectedStudent.get();
	}

	// b) Setter
	public void setSelectedStudent(Student selStudent) {
		// Den Student in der ObjectProperty setzen
		selectedStudent.set(selStudent);
	}

	// c) Die Property als solches
	public ObjectProperty<Student> selectedStudentProperty() {
		return selectedStudent;
	}

	public void setRepositoryPath(String path) {
		repository = new StudentXmlRepository(path);
		reload();
	}

	@FXML
	private void initialize() {
		System.out.println("Student List initialize");
		// Anfangs-Initialisierung von disable und visible ist beim Verwenden der
		// Bindings nicht nötig
//		btnEdit.setDisable(true);
//		btnDelete.setDisable(true);
//		boxStudent.setVisible(false);
		// Handler für Änderung des Selection im ListView
		tblStudents.getSelectionModel().selectedItemProperty().addListener(
				// In der ObjectProperty das Student-Objekt setzten
				(o, oldStd, newStd) -> {
					// In der Object-Property den selektierten Studenten setzen
					// Damit wird ein Changed-Event von der Property ausgelöst
					selectedStudent.set(newStd);

				});

		// Die disable-Properties an unsere Object Property binden
		// Buttons werden disabled, wenn kein Student selektiert ist
		btnEdit.disableProperty().bind(Bindings.isNull(selectedStudent));
		btnDelete.disableProperty().bind(Bindings.isNull(selectedStudent));
		// Die visibility Property ebenfalls
		// Box wird nur angezeigt, wenn ein Student selektiert ist
		boxStudent.visibleProperty().bind(Bindings.isNotNull(selectedStudent));

		// Text-Properties der Labels binden
		// Statt Binding expression text="${controller.selectedStudent.id}"
		lblId.textProperty().bind(Bindings.selectString(selectedStudent, "id"));
		lblName.textProperty().bind(Bindings.selectString(selectedStudent, "name"));
		lblAreaCode.textProperty().bind(Bindings.selectString(selectedStudent, "areaCode"));
		lblCity.textProperty().bind(Bindings.selectString(selectedStudent, "city"));

		// Cell-Value-Factories für die Spalten: legen fest, welcher Wert für das Item
		// in der jeweiligen Spalte angezeigt werden soll
		colId.setCellValueFactory(new PropertyValueFactory<>("id"));
		colName.setCellValueFactory(new PropertyValueFactory<>("name"));
		colBirthdate.setCellValueFactory(new PropertyValueFactory<>("birthDate"));
		colXml.setCellValueFactory(new PropertyValueFactory<>("xml"));
		colHtml.setCellValueFactory(new PropertyValueFactory<>("html"));

		// Die Standard-TableCell zeigt für jeden Zellwert die toString-Implementierung
		// an
		// Für Datum und

		// Cellfactories
		colBirthdate.setCellFactory(this::createLocalDateCell);
		colXml.setCellFactory(this::createCheckBoxCell);
		colHtml.setCellFactory(this::createCheckBoxCell);

		// Die Items des ListView an unsere ListProperty binden
		// Statt items="${controller.studentList}"
		tblStudents.itemsProperty().bind(students);
	}

	@FXML
	public void addStudent() {
		try {
			// Den Dialog ohne existierenden Objekt anzeigen
			EditStudentWindow dlg = new EditStudentWindow();
			Student entity = dlg.showModal();
			if (entity != null) {
				System.out.println("Neuer Student: " + entity);
				repository.insertStudent(entity);
				// Kompletten Reload, damit die neue ID auch in der Liste angezeigt wird
				reload();
			}
		} catch (Exception e) {
			e.printStackTrace();
			MessageBox.show("Neuer Student", "Fehler beim erfassen" + e.getMessage(), AlertType.ERROR, ButtonType.OK);
		}

	}

	@FXML
	public void editStudent() {
		try {
			// Neu:lokale Variable
			Student selStudent = getSelectedStudent();
			// Den selektierten Studenten bearbeiten
			System.out.println("Selektierter Student: " + selStudent);
			// Den Dialog mit dem selektierten Studenten anzeigen
			EditStudentWindow dlg = new EditStudentWindow(selStudent);
			Student entity = dlg.showModal();
			if (entity != null) {
				System.out.println("Geänderter Student: " + entity);
				// Im Repository ersetzen
				repository.updateStudent(entity);
				// Im ListView aktualisieren, aber direkt über die List-Property, nicht über die
				// Items des Listview
				// Sondern direkt über die List-Property -> der List View wird über das Binding
				// über Änderungen in der List-Property informiert
				// alter Code:
				// Den Index des original-Objekts holen
//				int index = lstStudents.getItems().indexOf(selStudent);
//				// An diesem Index das geänderte Objekt setzen
//				lstStudents.getItems().set(index, entity);

				// Neuer Code
				// Den Index des original-Objekts holen
				int index = students.indexOf(getSelectedStudent());
				// An diesem Index das geänderte Objekt setzen
				students.set(index, entity);

			}
		} catch (Exception e) {
			e.printStackTrace();
			MessageBox.show("Student bearbeiten", "Fehler beim Erfassen" + e.getMessage(), AlertType.ERROR,
					ButtonType.OK);
		}
	}

	@FXML
	public void deleteStudent() {
		try {
			System.out.println("Student löschen: " + getSelectedStudent());
			// Im repository löschen
			// repository.deleteStudent(selStudent.getId());
			// Aus den Items löschen
			// lstStudents.getItems().remove(selStudent);

			// Im repository löschen
			repository.deleteStudent(getSelectedStudent().getId());
			// Neu: aus der List-Property löschen
			students.remove(getSelectedStudent());
		} catch (Exception e) {
			e.printStackTrace();
			MessageBox.show("Student löschen ", "Fehler beim löschen" + e.getMessage(), AlertType.ERROR, ButtonType.OK);
		}
	}

	@FXML
	public void reload() {
		try {
			List<Student> tmpList = repository.selectAll();
			System.out.printf("%d Entities geladen\n", tmpList.size());

			// Die Studenten im Hauptfenster anzeigen:
			// Alte: Items löschen und einfügen
//			lstStudents.getItems().clear();
//			for (Student student : tmpList) {
//				lstStudents.getItems().add(student);
//			}

			// Neu: der List-Property eine neue Collection verpassen
			this.students.set(FXCollections.observableArrayList(tmpList));

		} catch (Exception e) {
			e.printStackTrace();
			MessageBox.show("Reload", "Fehler beim Laden" + e.getMessage(), AlertType.ERROR, ButtonType.OK);
		}
	}

	@FXML
	public void onClicked(MouseEvent me) {
		System.out.println("Mouse clicked");

		// Wenn die Maus 2x geklickt wurde
		if (me.getClickCount() == 2) {
			editStudent();
		}
	}

	@FXML
	public void onKey(KeyEvent ke) {
		System.out.printf("Key pressed KeyCode=%s\n", ke.getCode());

		switch (ke.getCode()) {
		case ENTER -> editStudent();
		case DELETE -> {
			// Nach Rückfrage löschen
			if (MessageBox.show("Student löschen", "Den selektiierten Datensatz löschen?", AlertType.CONFIRMATION,
					ButtonType.OK, ButtonType.CANCEL) == ButtonType.OK)
				deleteStudent();
		}
		default -> System.out.println("Keine Aktion zugeordnet");
		}

	}

	// TableCell-Factory-Methode für die Anzeige von einem LocalDate mit
	// Regionaleinstellungen
	private TableCell<Student, LocalDate> createLocalDateCell(TableColumn<Student, LocalDate> col) {
		return new TableCell<Student, LocalDate>() {
			@Override
			protected void updateItem(LocalDate value, boolean empty) {
				// Basisklasse aufrufen
				super.updateItem(value, empty);

				if (empty || value == null) {
					setText("");
				} else {
					setText(DateTimeFormatter.ofLocalizedDate(FormatStyle.MEDIUM).format(value));
				}
			}
		};
	}

	private TableCell<Student, Boolean> createCheckBoxCell(TableColumn<Student, Boolean> col) {
		// Eine TableCell, die als Checkbox angezeigt wird
		return new CheckBoxTableCell<Student, Boolean>(index -> {
			// Bei Bedarf den Wert für die jeweilige Zelle abrufen
			return new SimpleBooleanProperty(col.getCellData(index));
		});
	}

}
