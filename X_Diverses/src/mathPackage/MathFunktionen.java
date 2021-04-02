package mathPackage;

public class MathFunktionen {

	public static void main(String[] args) {
		int a, b, c;
		a = 3;
		b = 4;
		c = 5;
		// pow-Methode zum prüfen ob rechtwinkliges Dreieck(Pythagoras)
		// ^ ist Nicht Potenzoperator sondern XOR
		if ((a ^2) + (b ^2) == (c ^2)) {
			System.out.printf("Was auch immer wir hier testen, ^ ist der der XOR Operator");
		} else {
			System.out.printf("Bedingung nicht erfüllt.");
		}
		
		if ((a*a) + (b*b) == (c*c)) {
			System.out.println("Es ist eien rechtwinkliges Dreieck!");
		} else {
			System.out.println("Es ist Kein rechtwinkliges Dreieck!");
		}
		
		if (Math.pow(a,  2) + Math.pow(b, 2) == Math.pow(c, 2)) {
			System.out.println("Es ist ein rechtwinkliges Dreieck!");
		} else {
			System.out.println("Es ist kein rechtwinkliges Dreieck.");
		}
	}

}
