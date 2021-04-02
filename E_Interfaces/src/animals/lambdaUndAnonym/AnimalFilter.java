package animals.lambdaUndAnonym;

// Dieses Interface verwenden wir für die Tier-Filterung
// Implementierende Klassen können in ihrer isTrueFor-Implementierung festlege, 
// ob das übergebene Animal-Objekt im Ergebnis enthalten sein soll oder nicht
public interface AnimalFilter {
	boolean isTrueFor(Animal a);

	// Darf keine weiteren abstrakten Methoden enthalten.
}
