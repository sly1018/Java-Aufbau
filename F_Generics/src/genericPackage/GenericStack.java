package genericPackage;

/*
 * Schreibe eine Klasse für einen generischen Stack mit folgender Funktionalität:

Erzeugung des Stapels unter Angabe der Kapazität
push: ein Element wird zuoberst auf den Stapel gestellt. 
	Wenn die Kapazität erschöpft ist, soll eine Exception geworfen werden.
pop: das oberste Element wird zurückgeliefert (und vom Stapel entfernt). 
	Wenn kein oberstes Element existiert, soll eine Exception geworfen werden
peek: das oberste Element wird zurückgeliefert (und am Stapel belassen).
size: die Anzahl der Elemente am Stapel wird zurückgeliefert
 */

/**
 * Eine generische Stack-Klasse
 * 
 * @author Sulaiman
 *
 * @param <E> der Typ der Elemente an Stack
 */
// So würde eine blabla aussehen 
/*public class GenericStack<E extends Comparable<E>>*/

public class GenericStack<E> {
	// Dekleration eines generischen Arrays is möglich, aber nicht die Erzeugung
	// private E[] genElements;
	// Bei generischer Klasse mit Array von Elmenten, muss man trotzdem ein
	// Object-Array verwenden.
	private Object[] elements;

	// Index für das jeweils oberste Element
	// -1 bedeutet kein Element am Stack
	// (Anzahl Elemente -1) bedeutet "der Stack ist voll"
	private int topIndex = -1;

	public GenericStack(int count) {
		// Object Array in der passenden Größe erzeugen.
		elements = new Object[count];
		// Wäre schön, geht aber nicht
		// genElements = new E[count];
	}

	/**
	 * Ein Element zuoberst auf den Stapel legen
	 * 
	 * @param element
	 */
	public void push(E element) {
		// Wenn der Stapel voll ist -> Exception
		if (topIndex == elements.length - 1) {
			throw new IllegalStateException("Der Stack ist voll.");
		}
		// Den TopIndex inkrementieren und das Element im Array ablegen.
		elements[++topIndex] = element;
	}

	/**
	 * Das oberste Element zurückliefern und vom Stapel entfernen. Wirft eine
	 * Exception, wenn der Stack leer ist.
	 * 
	 * @return
	 */
	public E pop() {
		if (topIndex == -1) {
			throw new IllegalStateException("Der Stack ist leer.");
		}
		// Das Element holen, dabei müssen wir einen sogenannten "unchecked cast" machen
		// dh. das JRE(und damit wir) haben keine Möglichkeit zu prüfen, ob das Element
		// vom Typ "E" ist
		@SuppressWarnings("unchecked")
		E elem = (E) elements[topIndex];
		// Den Platz im Array löschen und den topIndex dekrementieren.
		elements[topIndex--] = null;
		// Das Element zurückliefern.
		return elem;
	}

	/**
	 * Das oberste Element zurückliefern (oder null, wenn der Stack leer ist)
	 * 
	 * @return
	 */
	public E peek() {
		if (topIndex == -1)
			return null;
		else {
			@SuppressWarnings("unchecked")
			E elem = (E) elements[topIndex];
			return elem;
		}
	}

	/**
	 * Anzahl der Elemente am Stack
	 * 
	 * @return
	 */
	public int size() {
		return topIndex + 1;
	}

	void dumpElements() {
		System.out.printf("The stack contains %d Elements\n", size());
		// Für alle Array-Positionen, die mit einem Element belegt sind
		for (int i = 0; i <= topIndex; i++) {
			@SuppressWarnings("unchecked")
			E elem = (E) elements[i];
			// Funktionalität aus Object kann ich auf jeden Fall verwenden
			System.out.printf("\tTyp=%s: %s\n", elem.getClass(), elem.toString());
		}
	}

//	void compareElements() {
//		System.out.println("Comparing Elements...");
//		for (int i = 0; i < topIndex; i++) {
//			@SuppressWarnings("unchecked")
//			E elem1 = (E) elements[i];
//			E elem2 = (E) elements[i + 1];
//			// Die Mehtode comparTo kann ich nur aufrufen, wenn
//			// das Typargument nicht mit <E extends Comparable<E>> eingeschränkt wird
//			int ret = elem1.compareTo(elem2); // Nur von Klasse möglich die dieses Interface implementiert
//			System.out.printf("Vergleich %s und %s => %d\n", elem1, elem2, ret);
//		}
//	}
}
