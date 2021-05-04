package students.test;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import students.repository.Gender;
import students.repository.Student;
import students.repository.StudentRepository;
import students.repository.StudentRepositoryException;
import students.repository.db.StudentDbRepository;
import students.repository.xml.StudentXmlRepository;

public class TestDbRepository {

	public static void main(String[] args) {

		StudentRepository repo = new StudentDbRepository("jdbc:mariadb://localhost/CourseDB", "root", "");
		// StudentRepository repo = new StudentXmlRepository("Repository.xml");

		// testSelect(repo);

		// testInsert(repo);

		// testDelete(repo);

		testUpdateStudents(repo, false);

		//testUpdateStudents(repo, true);
	}

	private static void testSelect(StudentRepository repo) {
		try {
			Student std1 = repo.selectById(1);
			System.out.println("Student geladen: " + std1);

			Student std2 = repo.selectById(100);
			System.out.println("Student geladen: " + std2);
		} catch (StudentRepositoryException e) {
			System.out.println("Fehler beim Laden: " + e.getMessage());
			if (e.getCause() != null) {
				System.out.println("\t" + e.getCause().getMessage());
			}
		}

		try {
			Student std1 = new Student();
			std1.setName("Michaela");
			std1.setAreaCode(1020);
			std1.setCity("Wien");
			std1.setComment("hallo");
			std1.setBirthDate(LocalDate.of(2001, 6, 15));
			std1.setHtml(true);
			std1.setGender(Gender.FEMALE);
			std1.setLanguage("English");
			int studentId = repo.insertStudent(std1);
			System.out.println("Id des neuen Studenten: " + studentId);

		} catch (StudentRepositoryException e) {
			System.out.println("Fehler beim Laden: " + e.getMessage());
			if (e.getCause() != null) {
				System.out.println("\t" + e.getCause().getMessage());
			}
		}
	}

	private static void testInsert(StudentRepository repo) {
		try {
			Student std1 = repo.selectById(1);
			System.out.println("Student geladen: " + std1);

			Student std2 = repo.selectById(100);
			System.out.println("Student geladen: " + std2);
		} catch (StudentRepositoryException e) {
			System.out.println("Fehler beim Laden: " + e.getMessage());
			if (e.getCause() != null) {
				System.out.println("\t" + e.getCause().getMessage());
			}
		}

		try {
			Student std1 = new Student();
			std1.setName("Michaela");
			std1.setAreaCode(1020);
			std1.setCity("Wien");
			std1.setComment("hallo");
			std1.setBirthDate(LocalDate.of(2001, 6, 15));
			std1.setHtml(true);
			std1.setGender(Gender.FEMALE);
			std1.setLanguage("English");
			int studentId = repo.insertStudent(std1);
			System.out.println("Id des neuen Studenten: " + studentId);

		} catch (StudentRepositoryException e) {
			System.out.println("Fehler beim Laden: " + e.getMessage());
			if (e.getCause() != null) {
				System.out.println("\t" + e.getCause().getMessage());
			}
		}
	}

	private static void testDelete(StudentRepository repo) {
		try {
			repo.deleteStudent(3);
			System.out.println("Student gelöscht");
		} catch (Exception e) {
			System.out.println("Fehler beim Laden: " + e.getMessage());
			if (e.getCause() != null) {
				System.out.println("\t" + e.getCause().getMessage());
			}
		}
	}

	private static void testUpdateStudents(StudentRepository repo, boolean withError) {
		try {
			// Alle Studenten laden
			List<Student> allStudents = repo.selectAll();
			// Bei jedem Objekt etwas ändern
			for (Student student : allStudents) {
				String text = String.format("Student überprüft von Michaela an %s", LocalDateTime.now());
				if (student.getComment() != null) {
					student.setComment(student.getComment() + "\n" + text);
				} else {
					student.setComment(text);
				}
			}
			// Wenn wir einen Fehler simulieren sollen
			if (withError) {
				// Den Namen des letzen Datensatzes auf null setzen
				allStudents.get(allStudents.size() - 1).setName(null);
			}

			// Alle aufeinmal speichern
			repo.updateStudents(allStudents);

			System.out.printf("Aktualisierung für %d Objekte erfolgreich\n", allStudents.size());

		} catch (Exception e) {
			System.err.println("Fehler beim Update der Student-Liste");
			e.printStackTrace();
		}
	}
}
