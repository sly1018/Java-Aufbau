package exceptions3;

// Eigene Exception Klasse definieren:
// Die Klasse ist eine checked Exception, dh sie kann ohne weiteres geworfen werden
public class CalculationException extends RuntimeException {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	// CalculationException mit Fehlertext initialisieren
	public CalculationException(String message) {
		// Fehlertext an die Basisklasse weiterreichen
		super(message);
	}

	// CalculationException mit Fehlertext und Auslöser initialisieren
	public CalculationException(String message, Exception cause) {
		super(message, cause);
	}
}
