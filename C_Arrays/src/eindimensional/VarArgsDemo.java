package eindimensional;

public class VarArgsDemo {

	public static void main(String[] args) {
		double[] nummer = {3.5, 2.4, 4.2};
		double dschnt = durchschnittBerechnen(nummer);
		System.out.printf("Der Durchschnitt ist %.2f\n", dschnt);
		
		dschnt = durchschnittBerechnen(new double[] {7.5, 3.6});
		System.out.printf("Der Durchschnitt ist %.2f\n", dschnt);
		
		dschnt = durchschnittBerechnen(3.2, 6.5, 8.1, 9.8);
		// Der Compiler macht daraus folgendes Statement:
		// dschnt = durchschnittBerechnen(new double[] {3.2, 6.5, 8.1, 9.8})
		System.out.printf("Der Durchschnitt ist %.2f\n", dschnt);

	}
	
	// statt (double[] werte) => (double ... werte)
	// => Methode mit variabler Argumentenliste
	public static double durchschnittBerechnen(double ... werte) {
		if(werte.length > 0) {
			double sum = 0;
			for (int i = 0; i < werte.length; i++) {
				sum += werte[i];
			}
			return sum / werte.length;
		}
		throw new IllegalArgumentException("Das Array muss mind. 1 Element enthalten.");
	}
}
