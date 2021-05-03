module studentList {
	// XML Binding
	requires jakarta.xml.bind;

	// Java FX
	requires javafx.base;
	requires javafx.controls;
	requires javafx.fxml;
	requires javafx.graphics;

	// JDBC API
	requires java.sql;

	// für JAXB öffnen
	opens students.repository;
	opens students.repository.xml;
	// Für Java FX
	opens students.program;
}