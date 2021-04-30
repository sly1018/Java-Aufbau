package calcFX;

import javafx.event.ActionEvent;
import javafx.scene.Node;

public class HandlerCalc {

	private ViewCalc meinView;

	public HandlerCalc(ViewCalc vc) {
		this.meinView = vc;
	}

	// Variablen für Operanden und Ergebnis.
	int ergebnis = 0, operand, zwischenErgebnis;
	// Char für Operator.
	char vorherigerOperator = ' ';
	// String für Zahlenkombination.
	String zahlenKombi = new String("");

	public void verarbeiteZiffer(ActionEvent ae) {
		try {
			Node source = (Node) ae.getSource();
			String action = source.getUserData().toString();
			System.out.printf("Action von Objekt mit UserDate " + action);

			zahlenKombi = zahlenKombi + action;
			operand = Integer.parseInt(zahlenKombi);

			meinView.lblOperand.setText(zahlenKombi);
			meinView.lblBerechnung.setText(meinView.lblBerechnung.getText() + action);

		} catch (Exception e) {
			zeigeFehler(e, "Fehler beim verarbeiten der Ziffer");
		}
	}

	public void verarbeiteOperator(ActionEvent ae) {
		try {
			System.out.println("\nverarbeiteOperator");

			Node source = (Node) ae.getSource();
			String action = source.getUserData().toString();
			System.out.printf("Action von Objekt mit UserDate " + action);

			berechne();

			vorherigerOperator = action.charAt(0);

			meinView.lblBerechnung.setText(
					(zwischenErgebnis == ergebnis) ? (ergebnis + action) : (meinView.lblBerechnung.getText() + action));

			meinView.lblOperand.setText("");
			meinView.lblErgebnis.setText(Integer.toString(zwischenErgebnis));

			zahlenKombi = "";

		} catch (Exception e) {
			zeigeFehler(e, "Fehler beim verarbeiten des Operators");
		}
	}

	private void berechne() {

		switch (vorherigerOperator) {
		case ' ' -> zwischenErgebnis = Integer.parseInt(zahlenKombi);

		case '+' -> addiere();
		case '-' -> subtrahiere();
		case '*' -> multipliziere();
		case '/' -> dividiere();
		case '%' -> moduliere();
		case '=' -> {

			ergebnis = zwischenErgebnis;
			meinView.lblErgebnis.setText(String.valueOf(ergebnis));
			zahlenKombi = String.valueOf(ergebnis);

			meinView.lblBerechnung.setText("");

			vorherigerOperator = ' ';
			berechne();

		}
		default -> throw new IllegalArgumentException("Unexpected value: " + vorherigerOperator);
		}
	}

	private void addiere() {
		zwischenErgebnis += operand;
	}

	private void subtrahiere() {
		zwischenErgebnis -= operand;
	}

	private void multipliziere() {
		zwischenErgebnis *= operand;
	}

	private void dividiere() {
		zwischenErgebnis /= operand;
	}

	private void moduliere() {
		zwischenErgebnis %= operand;
	}

	public void setzeZurück(ActionEvent ae) {

		System.out.println("Aufruf von setzeZurück()");

		// Labels zurücksetzen
		meinView.lblOperand.setText("");
		meinView.lblBerechnung.setText("");
		meinView.lblErgebnis.setText("");

		// Integer
		zwischenErgebnis = 0;
		// Character
		vorherigerOperator = ' ';
		// String
		zahlenKombi = "";
	}

	private void zeigeFehler(Exception e, String titel) {
		System.out.println("Fehler");
		e.printStackTrace();
	}

}
