package exceptions2;

public class Calculator {
	public int calculate(char op, int number1, int number2)
			// Beim Aufruf der Methode calculate kann eine CalculationException auftreten
			// => der Aufrufer muss die CalculationException fangen oder selber eine
			// throw-Dekleration angeben
			throws CalculationException {

		// Berechnung je nach Operator
		switch (op) {
		case '+':
			return number1 + number2;
		case '-':
			return number1 - number2;
		case '/':
			// TODO: handle division by zery
			try {
				return number1 / number2;
			} catch (ArithmeticException e) {
				// Wenn eine ArithmeticException auftritt => einpacken und weiterwerfen
				throw new CalculationException("Fehler in der Division", e);
			}
		case '*':
			return number1 * number2;
		default:
			System.out.println("Ungültiger Operator");
			// return 0; // nichtnormal weiterlaufen, sondern Fehler werfen
			throw new CalculationException("Ungültiger Operator: " + op);
		}

	}
}
