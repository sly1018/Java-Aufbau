package nonGeneric;

/*
 * Schreibe eine Klasse für einen Object-Stack mit folgender Funktionalität:

Erzeugung des Stapels unter Angabe der Kapazität
push: ein Element wird zuoberst auf den Stapel gestellt. 
	Wenn die Kapazität erschöpft ist, soll eine Exception geworfen werden.
pop: das oberste Element wird zurückgeliefert (und vom Stapel entfernt). 
	Wenn kein oberstes Element existiert, soll eine Exception geworfen werden
peek: das oberste Element wird zurückgeliefert (und am Stapel belassen).
size: die Anzahl der Elemente am Stapel wird zurückgeliefert
 */
public class ObjectStack {
	private Object[] elements;

	// Index für das jeweils oberste Element
	// -1 bedeutet kein Element am Stack
	// (Anzahl Elemente -1) bedeutet "der Stack ist voll"
	private int topIndex = -1;

	public ObjectStack(int count) {
		// Object Array in der passenden Größe erzeugen.
		elements = new Object[count];
	}

	/**
	 * Ein Element zuoberst auf den Stapel legen
	 * 
	 * @param element
	 */
	public void push(Object element) {
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
	public Object pop() {
		if (topIndex == -1) {
			throw new IllegalStateException("Der Stack ist leer.");
		}
		// Das Element holen.
		Object elem = elements[topIndex];
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
	public Object peek() {
		return topIndex == -1 ? null : elements[topIndex];
	}

	/**
	 * Anzahl der Elemente am Stack
	 * 
	 * @return
	 */
	public int size() {
		return topIndex + 1;
	}
}
