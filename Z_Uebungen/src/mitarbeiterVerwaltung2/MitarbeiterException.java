package mitarbeiterVerwaltung2;

public class MitarbeiterException extends Exception {

	private static final long serialVersionUID = 1L;

	public MitarbeiterException(String message) {
		super(message);
	}

	public MitarbeiterException(String message, Exception cause) {
		super(message, cause);
	}

}
