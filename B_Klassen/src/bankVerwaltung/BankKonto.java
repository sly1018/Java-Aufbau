package bankVerwaltung;

public class BankKonto {
	// Attribute
	private int kontoNum;
	private char kontoTyp;
	private double kontoStand;
	private double rahmen;
	private float zinsSatz;
	private String kontoInhaber;

	/*// Methoden
	public void eroeffneSparbuch(String name, float i, double erstAnlage) {
		this.kontoInhaber = name;
		this.zinsSatz = i;
		this.kontoStand = erstAnlage;
	}*/
	// Methoden
	public void eroeffneSparbuch(String name, float i, double erstAnlage) {
		kontoInhaber = name;
		zinsSatz = i;
		kontoStand = erstAnlage;
	}

	/* eroeffneGehaltsKonto(String name, double r) {
		// implementiere mich
	}

	einzahlen(double einzahlungsBetrag) {
		// implementiere mich
	}
	
	abheben(double abziehungsBetrag) {
		// implementiere mich
	} */
	
	public void infosAnzeigen() {
		System.out.printf("Name: %s, Zinssatz: %f, Kontostand: %.2f", this.kontoInhaber, this.zinsSatz, this.kontoStand);
	}
}
