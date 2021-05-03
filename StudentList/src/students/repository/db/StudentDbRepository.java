package students.repository.db;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import students.repository.Gender;
import students.repository.Student;
import students.repository.StudentRepository;
import students.repository.StudentRepositoryException;

public class StudentDbRepository implements StudentRepository {

	// Informationen zur Connection
	private String dbUrl, userName, password;

	// Konstuktor der die passenden Informationen erhält
	public StudentDbRepository(String dbUrl, String userName, String password) {
		this.dbUrl = dbUrl;
		this.userName = userName;
		this.password = password;
	}

	@Override
	public List<Student> selectAll() throws StudentRepositoryException {

		// TODO alle Student-Datensätze aus der DB laden

		// name, areaCode, city, birthDate , gender, language, xml, html, fxml, comment
		try (Connection conn = DriverManager.getConnection(dbUrl, userName, password)) {
			Statement stmt = conn.createStatement();
			// Vorsicht bei größerren Datenmengen - wir erzeugen ohne etwas zu checken für
			// jeden Datensatzt ein Objekt, im Zweifelsfall auch für 1000000 Objekte!
			String query = "select id, name, areaCode, city, birthDate , gender, language, xml, html, fxml, comment from Students";
			ResultSet result = stmt.executeQuery(query);
			// Liste für den Returnwert
			List<Student> allStudents = new ArrayList<>();

			while (result.next()) {
				// Aus dem Datensatz ein Student-Objekt einlesen und in der Liste hinzufügen
				allStudents.add(readStudent(result));

			}
			// Die Student-Liste zurückliefern
			return allStudents;
		} catch (SQLException e) {
			System.err.println("Fehler beim Abrufen der Daten");
			// e.printStackTrace();
			// Exception weiter werfen
			throw new StudentRepositoryException("Fehler beim Zugriff auf die Datenbank", e);
		}
	}

	@Override
	public Student selectById(int id) throws StudentRepositoryException {
		// Den Student-Datensat mit der angegebenen ID aus der DB laden
		try (Connection conn = DriverManager.getConnection(dbUrl, userName, password)) {
			String query = "select id, name, areaCode, city, birthDate , gender, language, xml, html, fxml, comment from Students where id = ?";
			System.out.println("selectById:" + query);
			// Ein Statement, das Parameter unterstütz
			PreparedStatement stmt = conn.prepareStatement(query);
			// Jetzt Wert für den Parameter setzen (Nummerierung beginnt bei 1)
			stmt.setInt(1, id);
			// Dann erst ausführen
			ResultSet result = stmt.executeQuery();
			// Wenn es keinen nächsten Datensatz gibt -> Exception werfen
			if (!result.next()) {
				throw new StudentRepositoryException("Student mit ID" + id + " existiert nicht");
			}

			Student entity = readStudent(result);
			return entity;

		} catch (Exception e) {
			System.err.println("Fehler beim Laden eines Student-Datensatzes");
			e.printStackTrace();
			throw new StudentRepositoryException("Fehler beim Laden eines Studenten-Datensatzes", e);
		}

	}

	@Override
	public int insertStudent(Student student) throws StudentRepositoryException {
		// insert into Students (name, areaCode, city, birthDate , gender, language,
		// xml, html, fxml, comment)

		try (Connection conn = DriverManager.getConnection(dbUrl, userName, password)) {
			String sql = "insert into Students (name, areaCode, city, birthDate , gender, language, xml, html, fxml, comment)"
					+ " values(?,?,?,?,?,?,?,?,?,?) ";

			// Statement erzeugen, das nach dem Ergebnis auch ein Resultset mit dem neuen
			// Keywert zurückliefert
			PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);

			// Als 1. Parameter den Namen setzen
			stmt.setString(1, student.getName());
			// Als 2. die PLZ setzen
			stmt.setInt(2, student.getAreaCode());
			// usw
			stmt.setString(3, student.getCity());
			// LocalDate: in Date umgewandelt
			stmt.setDate(4, Date.valueOf(student.getBirthDate()));
			// enum -> in Zeichenfolge umgewandelt
			stmt.setString(5, student.getGender().toString());
			stmt.setString(6, student.getLanguage());

			// booleans
			stmt.setBoolean(7, student.isXml());
			stmt.setBoolean(8, student.isHtml());
			stmt.setBoolean(9, student.isFxml());

			stmt.setString(10, student.getComment());
			// Den Befehl ausführen, Ergebnis ist Anzahl der betroffenen Datensätze
			// -> muss 1 sein, wenn alles passt
			int rowAffected = stmt.executeUpdate();
			if (rowAffected != 1) {
				System.out.println("Keine Datensätze vom Insert betroffen ????");
				throw new StudentRepositoryException("Es sind keine Datensätze vom Insert betroffen.");
			}

			// Wenn alles gut gegangen ist, den Key abholen
			ResultSet keys = stmt.getGeneratedKeys();
			if (keys.next()) {
				int id = keys.getInt(1);
				System.out.printf("Objekt eingefügt neue ID=%d\n", id);
				return id;
			} else {
				// Das sollte nicht passieren
				System.err.printf("Objekt eingefügt, neue ID nicht bekannt!");
				throw new StudentRepositoryException(
						"Der Datensatz wurde eingefügt, die ID konnte aber nicht ermittelt werden");
			}
		} catch (SQLException e) {
			System.err.println("Fehler beim Einfügen eines Student-Datensatzes");
			e.printStackTrace();
			throw new StudentRepositoryException("Fehler beim Einfügen eines Studenten-Datensatzes", e);
		}
	}

	@Override
	public void updateStudent(Student student) throws StudentRepositoryException {
		// TODO Auto-generated method stub
		throw new RuntimeException("Noch nicht implementiert");
//		try (Connection conn = DriverManager.getConnection(dbUrl, userName, password)) {
//
//		} catch (Exception e) {
//			System.err.println("Fehler beim Aktualisieren eines Student-Datensatzes");
//			e.printStackTrace();
//			throw new StudentRepositoryException("Fehler beim Aktualisieren eines Studenten-Datensatzes", e);
//		}

	}

	@Override
	public void deleteStudent(int id) throws StudentRepositoryException {
		// TODO Auto-generated method stub
		throw new RuntimeException("Noch nicht implementiert");
//		try (Connection conn = DriverManager.getConnection(dbUrl, userName, password)) {
//
//		} catch (Exception e) {
//			System.err.println("Fehler beim Löschen eines Student-Datensatzes");
//			e.printStackTrace();
//			throw new StudentRepositoryException("Fehler beim Löschen eines Studenten-Datensatzes", e);
//		}

	}

	private Student readStudent(ResultSet result) throws SQLException {
		Student entity = new Student();
		// alle Werte in die Properties schreiben
		entity.setId(result.getInt("id"));
		entity.setName(result.getString("name"));
		entity.setAreaCode(result.getInt("areaCode"));
		entity.setCity(result.getString("city"));
		entity.setLanguage(result.getString("language"));
		entity.setComment(result.getString("comment"));
		// booleans
		entity.setXml(result.getBoolean("xml"));
		entity.setHtml(result.getBoolean("html"));
		entity.setFxml(result.getBoolean("fxml"));
		// LocalDate: Über Date
		entity.setBirthDate(result.getDate("birthDate").toLocalDate());
		// Gender-Enum: aus der Zeichenfolge ermitteln
		String strGender = result.getString("gender");
		entity.setGender(Gender.valueOf(strGender));

		return entity;
	}

}
