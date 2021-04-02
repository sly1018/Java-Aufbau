package nonGeneric;

public class ObjectStackDemo {

	public static void main(String[] args) {
		ObjectStack myStack = new ObjectStack(5);

		// Beim reinstellen der Werte passiert eine implizite Typumwandlung.
		myStack.push("Java Basis");
		myStack.push("Java Aufbau");
		myStack.push("Software Engineering");
		myStack.push("Projektphase");
		myStack.push("Prüfung");
		
		// Beim rausholen der Werte ist ein explizite Typumwandlung nötig.
		String topValue = (String) myStack.peek();
		System.out.println("Oberstes Element: " + topValue);
		
		while(myStack.size() > 0) {
			String value = (String) myStack.pop();
			System.out.println("Element abgeholt: " + value);
		}
		
		// Nochmaliger Aufruf von pop würde eine Exception auslösen.
		topValue = (String)myStack.pop();
		System.out.println("Element abgeholt: " + topValue);
	}
	
//	static void addString(ObjectStack myStack, Object value) {
//		
//	}

}
