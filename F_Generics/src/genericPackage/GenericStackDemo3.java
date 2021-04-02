package genericPackage;

public class GenericStackDemo3 {

	public static void main(String[] args) {
		GenericStack<Integer> myStack = new GenericStack/* <Integer> */(5);

		// Beim reinstellen der Werte prüft der Compiler den Element-Typen.
		myStack.push(18151);
		myStack.push(18139);
		myStack.push(18288);
		// Fehlerhafter Aufruf: wird jetzt vom Compiler entdeckt
		myStack.push(18147);
		myStack.push(18531);

		// Beim rausholen der Werte wandelt der Compiler automatisch um.
		// Bei primitives wird auch unboxing ausgeführt.
		int topValue = myStack.peek()/*intValue()*/;
		System.out.println("Oberstes Element: " + topValue);

		while (myStack.size() > 0) {
			int value = myStack.pop()/*intValue()*/;
			System.out.println("Element abgeholt: " + value);
		}

		try {
			// Nochmaliger Aufruf von pop würde eine Exception auslösen.
			topValue = myStack.pop();
			System.out.println("Element abgeholt: " + topValue);
		} catch (IllegalStateException e) {
			System.out.println("Fehler: " + e.getMessage());
		}
		// Liefert null zurück, wenn der Stack leer ist
		Integer tmpValue1 = myStack.peek();
		System.out.println("Oberstes Element: " + tmpValue1);
		
		// Abrufen und Unboxing in einem -> NullPointerException wenn der Stack leer ist.
		int tmpValue2 = myStack.peek()/*intValue()*/;
		System.out.println("Oberstes Element: " + tmpValue2);
	}

//	static void addString(ObjectStack myStack, Object value) {
//		
//	}

}
