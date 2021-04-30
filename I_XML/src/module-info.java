module xmlSerializationDemo {
	// Das Modul aus der JAXB Library verwenden
	requires jakarta.xml.bind;

	// Das Package für Reflection öffnen
	// Reflection-Zugriff auf unsere Fahrzeug-Klassen erlauben
	// Sonst passiert ein java.lang.IllegalAccessException, zb.: cannot access class
	// xml.adapters.LocalDateAdapter (in module xmlSerializationDemo) because module
	// xmlSerializationDemo does not export xml.adapters to module
	// com.sun.xml.bind.core
	opens xml.model;
	opens xml.adapters;
}