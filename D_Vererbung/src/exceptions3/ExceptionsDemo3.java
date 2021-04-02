package exceptions3;

import java.util.Scanner;

public class ExceptionsDemo3 {

	private static Scanner scanner = new Scanner(System.in);

	public static void main(String[] args) {
		System.out.println("1. Berechnung (Version 2):");
		runCalculation();

		System.out.println("2. Berechnung:");
		runCalculation();

	}

	private static void runCalculation() {
		try {
			char op;
			int number1, number2, result;
			// Operator und Operanden einlesen
			System.out.print("1. Operand: ");
			number1 = Integer.parseInt(scanner.nextLine());
			System.out.print("Welche Operation (+ - * /)? ");
			op = scanner.nextLine().charAt(0);
			System.out.print("2. Operand: ");
			number2 = Integer.parseInt(scanner.nextLine());
			// Berechnung ausführen
			Calculator calc = new Calculator();
			result = calc.calculate(op, number1, number2);

			// Ergebnis anzeigen
			System.out.printf("%d%c%d=%d %n", number1, op, number2, result);
		} catch (NumberFormatException e) {
			// Fehlerhafte Eingabe einer Zahl
			System.out.println("Fehlerhafte Eingabe: " + e.getMessage());
		} catch (ArithmeticException e) { // Division durch Null
			System.out.println("Fehler bei der Berechnung: " + e.getMessage());

		} 
		// Bei unchecked Exception ist dieser catch-Blcok optional (aber sinnvoll)
		catch (CalculationException e) {
			// Die eigene Fehlerklasse behandeln
			System.out.println("Fehler bei der Berechnung: " + e.getMessage());
			if(e.getCause() != null ) {
				System.out.println("\tGrund" + e.getCause().toString());
			}
			// e.printStackTrace();
		}
	}
}
