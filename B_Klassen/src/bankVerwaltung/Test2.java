package bankVerwaltung;

public class Test2 {

	public static void main(String[] args) {
		BankKonto2 bk = new BankKonto2("Max", 1000.0); // name und rahmen
		BankKonto2 bk2 = new BankKonto2("Moritz", 1009.0);
		
		bk.infosAnzeigen();
		bk2.infosAnzeigen();
	}

}
