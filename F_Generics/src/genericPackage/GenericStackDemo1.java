package genericPackage;

public class GenericStackDemo1 {

	public static void main(String[] args) {
		GenericStack<String> courseStack = new GenericStack<String>(5);
		addString(courseStack, "Java Basis");
		addString(courseStack, "Java Aufbau");
		addString(courseStack, "Software Engineering");
		addString(courseStack, "Projektphase");
		addString(courseStack, "Prüfung");
		// Ein Elment mehr hinzufügen als Platz ist
		// die Exception wird in der addString Methoden behandelt ->
		// das Programm läuft nachher normal weiter
		addString(courseStack, "Arbeitssuche");

		while (courseStack.size() > 0) {
			removeString(courseStack);
		}
		// noch eines entfernen -> Exception
		removeString(courseStack);
	}

	static void addString(GenericStack<String> myStack, String value) {
		try {
			// Compiler verlangt hier ein String-Argument, andere Datentypen sind nicht
			// erlaubt
			myStack.push(value);
			// Syntaktisch ist keine (explizite) Typumwandlung nötig, wird vom Compiler
			// automatisch durchgeführt
			String topValue = myStack.peek();
			System.out.printf("Element hinzugefügt, oberstes Element ist jetzt: %s\n", topValue);
		} catch (IllegalStateException e) {
			System.out.println("Fehler beim hinzufügen: " + e.getMessage());
		}
	}

	static void removeString(GenericStack<String> myStack) {
		try {
			String value = myStack.pop();
			String topValue = myStack.peek();
			System.out.printf("Element %s entfernt, oberstes Element ist jetzt: %s\n ", value, topValue);
		} catch (IllegalStateException e) {
			System.out.println("Fehler beim rausholen: " + e.getMessage());
		}
	}
}
