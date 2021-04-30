module studentList {

	requires jakarta.xml.bind;
	

	requires javafx.base;
	requires javafx.controls;
	requires javafx.fxml;
	requires javafx.graphics;
	
	// für JAXB öffnen
	opens students.repository;
	opens students.repository.xml;
	// Für Java FX
	opens students.program;
}