package genericPackage;

public class GenericStackDemo2 {

	public static void main(String[] args) {
		GenericStack<String> myStack = new GenericStack<String>(5);

		// Beim reinstellen der Werte prüft der Compiler den Element-Typen.
		myStack.push("18151");
		myStack.push("18139");
		myStack.push("18288");
		// Fehlerhafter Aufruf: wird jetzt vom Compiler entdeckt
		// myStack.push(18147);
		myStack.push("18531");

		// Beim rausholen der Werte wandelt der Compiler automatisch um.
		String topValue = myStack.peek();
		System.out.println("Oberstes Element: " + topValue);

		while (myStack.size() > 0) {
			String value = myStack.pop();
			System.out.println("Element abgeholt: " + value);
		}

		try {
			// Nochmaliger Aufruf von pop würde eine Exception auslösen.
			topValue = myStack.pop();
			System.out.println("Element abgeholt: " + topValue);
		} catch (IllegalStateException e) {
			System.out.println("Fehler: " + e.getMessage());
		}
	}

//	static void addString(ObjectStack myStack, Object value) {
//		
//	}

}
