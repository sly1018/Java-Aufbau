package bankVerwaltung;

import java.util.Scanner;

public class Test3 {

	public static void main(String[] args) {

		// Sparkonto
		BankKonto3 sparKonto = new BankKonto3(1000.0, "Karo", 0.03f);
		sparKonto.infosAnzeigen();
		
		// Sparkonto ohne Ersteinlage
		BankKonto3 sparKonto2 = new BankKonto3(0, "Mimi", 0.04f);
		sparKonto2.infosAnzeigen();
		
		// Gehaltskonto
		BankKonto3 gehaltsKonto = new BankKonto3(2000.0, "Max", 1000.0);
		gehaltsKonto.infosAnzeigen();
		
		//eröffne Gehaltskonto mit Methode (2 Parameter).
		BankKonto3 sparKonto3 = new BankKonto3();
		sparKonto3.eroeffneGehaltsKonto("Oli", 5000.0);
		sparKonto3.infosAnzeigen();
		
		// Scanner für Benutzereingabe.
		Scanner eingabe = new Scanner(System.in);
		
		System.out.println("Geben Sie den Betrag ein für die Einzahlung auf das Konto von Karo: ");
		double einzahlung = eingabe.nextDouble();
		sparKonto.einzahlen(einzahlung);
		sparKonto.infosAnzeigen();
		
		System.out.println("Welchen Betrag wollen Sie vom Konto von Max auszahlen?");
		double auszahlung = eingabe.nextDouble();
		gehaltsKonto.abheben(auszahlung);
		gehaltsKonto.infosAnzeigen();
	}

}
