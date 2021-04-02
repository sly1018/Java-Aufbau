package exceptions;

public class Calculator {
	public int calculate(char op, int number1, int number2) {
		// Berechnung je nach Operator
		switch (op) {
		case '+':
			return number1 + number2;
		case '-':
			return number1 - number2;
		case '/':
			// TODO: handle division by zery
			return number1 / number2;
		case '*':
			return number1 * number2;
		default:
			System.out.println("Ungültiger Operator");
			//  return 0; // nichtnormal weiterlaufen, sondern Fehler werfen
			throw new IllegalArgumentException("Ungültiger Operator: " + op);
		}

	}
}
