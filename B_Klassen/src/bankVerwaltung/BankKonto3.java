package bankVerwaltung;

public class BankKonto3 {

	// Attribute
	private static int kontoNum; // static inkrementieren

	private int kNum; // vom objekt
	private double kontoStand;
	private String kontoInhaber;

	private final KontoTyp kontoTyp; // Sparkonto oder Gehaltskonto

	private float zinsSatz; // Sparkonto wird festgelegt, Gehaltskonto konstant 0,1%
	private double rahmen; // Bei Gehaltskonto wird festgelegt. Sparkonto kein Rahmen.

	// static initializer
	static {
		kontoNum = 1;
	}

	// Konstruktor für Sparkonto
	public BankKonto3(double erstAnlage, String ki, float i) {
		kontoTyp = KontoTyp.SPARBUCH;
		kontoStand = erstAnlage;
		kontoInhaber = ki;
		zinsSatz = i;
		rahmen = 0;
		kNum = kontoNum++;
	}

	// Konstruktor für Gehaltskonto
	public BankKonto3(double ersteAnlage, String ki, double r) {
		kontoTyp = KontoTyp.GEHALTSKONTO;
		kontoStand = ersteAnlage;
		kontoInhaber = ki;
		zinsSatz = 0.001f;
		rahmen = r;
		kNum = kontoNum++;
	}

	private BankKonto3(KontoTyp kt, double ks, double r, float i, final String ki) {
		this.kontoTyp = kt;
		this.kontoStand = ks;
		this.kontoInhaber = ki;
		this.zinsSatz = i;
		this.rahmen = r;

		kNum = kontoNum++;
	}

	public BankKonto3() {
		this(KontoTyp.GEHALTSKONTO, 0, 0, 0, null);
	}

	public void eroeffneGehaltsKonto(String name, double r) {
		this.kontoInhaber = name;
		this.rahmen = r;
	}

	public void einzahlen(double einzahlungsBetrag) {
		if (einzahlungsBetrag < 15000) {
			System.out.printf("Ein Betrag von %.2f wird eingezahlt auf Konto: %d\n", einzahlungsBetrag, this.kNum);
			kontoStand = kontoStand + einzahlungsBetrag;
		} else
			throw new IllegalArgumentException("Betrag " + einzahlungsBetrag + "ist zu hoch. Einzahlung nicht durchgeführt.\n");
	}

	public void abheben(double abziehungsBetrag) {
		if (abziehungsBetrag <= (kontoStand + rahmen)) {
			kontoStand = kontoStand - abziehungsBetrag;
			System.out.printf("Der Betrag %.2f wurde ausgezahlt von Konto: %d\n", abziehungsBetrag, this.kNum);
		} else
			throw new IllegalArgumentException("Betrag " + abziehungsBetrag + "ist zu hoch. Auszahlung unterbrochen.\n");
	}

	public void infosAnzeigen() {
		System.out.printf(
				"[Name: %s], [Rahmen: %.2f], [Kontonummer: %s], [Kontotyp: %s], [Zinssatz: %.3f], [Kontostand: %.2f] \n",
				this.kontoInhaber, this.rahmen, this.kNum, this.kontoTyp, this.zinsSatz, this.kontoStand);
	}

	// Getter, setter und toString Methoden

	public int getKontoNummer() {
		return kNum;
	}

	public double getKontoStand() {
		return kontoStand;
	}

	public String toString() {
		return String.format("Bankkonto {nr=%3s, kontostand=%.2f}", kNum, kontoStand);
	}

}
