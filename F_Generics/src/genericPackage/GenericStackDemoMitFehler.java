package genericPackage;

public class GenericStackDemoMitFehler {

	public static void main(String[] args) {
		// Raw Type Deklaratiion -> Compiler hat keine Möglichkeit zu prüfen
		// Die zugehörige Warnung sollten wir nicht normalerweise nicht ignorieren
		// GenericStack is a raw type. References to generic type GenericStack<E> should
		// be parameterized GenericStackDemo3.java /F_Generics/src/genericPackage line 6
		// Java Problem

		GenericStack myStack = new GenericStack<String>(5);

		// Beim reinstellen der Werte passiert eine implizite Typumwandlung.
		myStack.push("18151");
		myStack.push("18139");
		myStack.push("18288");
		// Fehlerhafter Aufruf: Ich sollte die Zahl als String einfühlen
		// Diesen Fehler entdecke ich aber nicht hier, sondern erst zur Laufzeit
		myStack.push(18147);
		myStack.push("18531");
		
		myStack.dumpElements();
		myStack.compareElements();

		// Beim rausholen der Werte ist ein explizite Typumwandlung nötig.
		String topValue = (String) myStack.peek();
		System.out.println("Oberstes Element: " + topValue);

		while (myStack.size() > 0) {
			String value = (String) myStack.pop();
			System.out.println("Element abgeholt: " + value);
		}

		try {
			// Nochmaliger Aufruf von pop würde eine Exception auslösen.
			topValue = (String) myStack.pop();
			System.out.println("Element abgeholt: " + topValue);
		} catch (IllegalStateException e) {
			System.out.println("Fehler: " + e.getMessage());
		}
	}

//	static void addString(ObjectStack myStack, Object value) {
//		
//	}

}
