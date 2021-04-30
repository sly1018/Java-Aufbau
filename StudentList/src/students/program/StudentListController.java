package students.program;

import java.util.List;

import common.MessageBox;
import javafx.beans.binding.Bindings;
import javafx.beans.property.ListProperty;
import javafx.beans.property.ObjectProperty;
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
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import students.repository.Student;
import students.repository.StudentRepository;
import students.repository.xml.StudentXmlRepository;

public class StudentListController {

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

	// Für ListView und Student VBox
	@FXML
	ListView<Student> lstStudents;

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

	// Alt: Feld für das Objekt das in der Liste selektiert ist
	// private Student selStudent;

	// Für das Property Binding:
	// 1) binden des ListView an eine ListProperty
	private ListProperty<Student> students;

	// 2) Binden des selektierten Student an eine ObjectProperty
	// Statt einem "normalen" Feld -> eine Observable Property definieren
	// ObjectProperty informiert ihre Listener, wenn ein anderes Objekt gesetzt wird
	private ObjectProperty<Student> selectedStudent;

	public StudentListController() {
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
		lstStudents.getSelectionModel().selectedItemProperty().addListener(
				// In der ObjectProperty das Student-Objekt setzten
				(o, oldStd, newStd) -> {
					// In der Object-Property den selektierten Studenten setzen
					// Damit wird ein Changed-Event von der Property ausgelöst
					selectedStudent.set(newStd);

					// Änderungen werden über Bindings automatisch angezeigt
//					// Änderung der Selection behandeln
//					// Das selektierte Objekt merken
//					selStudent = newStd;
//					if (selStudent != null) {
//						lblId.setText(Integer.toString(selStudent.getId()));
//						lblName.setText(selStudent.getName());
//						lblAreaCode.setText(Integer.toString(selStudent.getAreaCode()));
//						lblCity.setText(selStudent.getCity());
//					} else {
//						lblId.setText("");
//						lblName.setText("");
//						lblAreaCode.setText("");
//						lblCity.setText("");
//					}
//
//					// Den Edit- und Delete-Button disablen, wewnn kein Student selektiert ist
//					btnEdit.setDisable(selStudent == null);
//					btnDelete.setDisable(selStudent == null);
//					// Die Student-Box verbergen, wenn kein Student selektiert ist
//					boxStudent.setVisible(selStudent != null);

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

		// Die Standard-ListCell zeigt für jedes Item die toString-Implementierung an
		// Student.toString ist zu umfassend -> Unsere eigene ListCellFactory setzen
		// setCellFactory bekommt eine Callback-Implementierung, die ein ListCell-Objekt
		// liefert
		// Unsere Methode createStudentListCell passt zur Signatur dieses Callbacks
		lstStudents.setCellFactory(this::createStudentListCell);

		// Die Items des ListView an unsere ListProperty binden
		lstStudents.itemsProperty().bind(students);
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

	// Methode, deren Signatur zum Callback für die ListCellFactory passt
	// -> kann als Method-reference bei setCellFactory verwendet werden
	private ListCell<Student> createStudentListCell(ListView<Student> lstView) {
		System.out.println("createStudentListCell - CellFactory wird erzeugt");
		// Eigene ListCell-Ableitung zurückliefern
		return new ListCell<Student>() {
			@Override
			protected void updateItem(Student item, boolean empty) {
				// Basis-Implementierung ausführen (Styles etc richtig setzen)
				super.updateItem(item, empty);
				// Das Aussehen anpassen
				if (empty || item == null) {
					setText("");
				} else {
					setText(String.format("%s (%s %s, ID=%d)", item.getName(), item.getAreaCode(), item.getCity(),
							item.getId()));
				}
			}
		};
	}

}
