module mitarbeiterVerwaltungWithRepository {
	// XML Binding
	requires jakarta.xml.bind;

	// für JAXB öffnen
	opens mitarbeiter.repository;
	opens mitarbeiter.repository.xml;

}