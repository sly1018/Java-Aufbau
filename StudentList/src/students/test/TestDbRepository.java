package students.test;

import java.time.LocalDate;

import students.repository.Gender;
import students.repository.Student;
import students.repository.StudentRepository;
import students.repository.StudentRepositoryException;
import students.repository.db.StudentDbRepository;

public class TestDbRepository {

	public static void main(String[] args) {

		StudentRepository repo = new StudentDbRepository("jdbc:mariadb://localhost/CourseDB", "root", "");

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

}
