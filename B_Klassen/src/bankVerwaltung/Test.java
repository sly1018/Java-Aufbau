package bankVerwaltung;

public class Test {
	public static void main(String[] args) {
		BankKonto bk = new BankKonto();
		bk.eroeffneSparbuch("Max", 1.2f , 1000);
		
		bk.infosAnzeigen();
	}

}
