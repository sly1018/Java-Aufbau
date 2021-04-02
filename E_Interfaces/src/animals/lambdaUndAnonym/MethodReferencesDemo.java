package animals.lambdaUndAnonym;

public class MethodReferencesDemo {

	public static void main(String[] args) {
		AnimalList animals = new AnimalList();
		System.out.println("Fleischfresser (2x)");

		// Variante 1: Statische Methode in Klasse
		// Lambda-Expression, die unsere satic Methode aufruft.
		animals.showAnimals(a -> MethodReferencesDemo.istFleischfresser(a));
		// Kann ersetzt werden durch eine Methodenreferenz.
		animals.showAnimals(MethodReferencesDemo::istFleischfresser);

		System.out.println("Schwere Tiere (2x)");
		// Variante 2: Instanzmethode in Klasse.
		AnimalHelper helperObject = new AnimalHelper();
		// Lambda-Expression die Instanzmethode aufruft.
		animals.showAnimals(a -> helperObject.isHeavyAnimal(a));
		// Kann ersetzt werden durch eine Methodenreferenz.
		animals.showAnimals(helperObject::isHeavyAnimal);

		System.out.println("Leichte Tiere: ");
		animals.showAnimals(helperObject::isLightAnimal);

		System.out.println("Vegetarier (2x)");
		// Variante 3: Instanzmethode in Klasse, mit arbiträrem Objekt
		// Lambda-Expression, die mit dem Argument als aktuelles Objekt eine Methode
		// aufruft
		animals.showAnimals(a -> a.isHerbivore());
		// Kann ersetzt werden durch eine Methodenreferenz.
		animals.showAnimals(Animal::isHerbivore);
	}

	// Methode deren Signatur bis auf den Namen zur AnimalFilter.isTrueFor passt.
	static boolean istFleischfresser(Animal animal) {
		return !animal.isHerbivore();
	}
}
