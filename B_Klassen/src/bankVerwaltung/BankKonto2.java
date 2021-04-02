package bankVerwaltung;

public class BankKonto2 {
	
	// Attribute
		private static int kontoNum;  // static inkrementieren
		
		private int kNum; // vom objekt
		private final char kontoTyp;
		private double kontoStand;
		private double rahmen;
		private float zinsSatz;
		private String kontoInhaber;
		
		// static initializer
		static {
			kontoNum = 1;
		}

		/*// Konstruktor
		public BankKonto2(String name, float i, double erstAnlage) { // todo mit this implementieren
			this(name, i, erstAnlage);
		}*/
		
		public BankKonto2(String name, double r) {
			
			kontoTyp = 'G';
			kontoInhaber = name; // todo mit this
			rahmen = r;
			
			kNum = kontoNum++;
		}
		
		private BankKonto2(int kn, char kt, double ks, double r, float i, final String ki) {
			kontoNum = kn;
			kontoTyp = kt;

			this.kontoInhaber = ki;
			this.zinsSatz = i;
			this.rahmen = r;
			//weiter implementieren
		}
		
		
		// Methoden
		public void eroeffneSparbuch(String name, float i, double erstAnlage) { 
			kontoInhaber = name;
			zinsSatz = i;
			kontoStand = erstAnlage;
		}

		public void eroeffneGehaltsKonto(String name, double r) {
			kontoInhaber = name;
			rahmen = r;
		}
/*
		einzahlen(double einzahlungsBetrag) {
			// implementiere mich
		}
		
		abheben(double abziehungsBetrag) {
			// implementiere mich
		} */
		
		public void infosAnzeigen() {
//			System.out.printf("Name: %s, Zinssatz: %f, Kontostand: %.2f", this.kontoInhaber, this.zinsSatz, this.kontoStand);
			//System.out.printf("Name: %s, Rahmen: %f\n", this.kontoInhaber, this.rahmen);
			System.out.printf("Name: %s, Rahmen: %f, Kontonummer: %s\n", this.kontoInhaber, this.rahmen, this.kNum);
		}

}
