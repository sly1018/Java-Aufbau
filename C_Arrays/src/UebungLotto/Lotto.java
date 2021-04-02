package UebungLotto;

import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;

public class Lotto {
	// static array?
	
	public static void main(String[] args) {
		int[] tipps = new int[6];
		Random random = new Random();
		
		for (int i = 0; i < tipps.length; i++) {
			int tipp = random.nextInt(45);
			tipps[i] = tipp;
		}
		
		Arrays.sort(tipps);
		
		System.out.println(Arrays.toString(tipps));
	}
	
//	public static void manuellEingeben(int[] tipp) {
//		Scanner eingabe = new Scanner(System.in);
//		
//		System.out.printf("Bitte geben Sie 6 Zahlen nacheinander ein.");
//	}
	
	public static void manuellEingeben() {
		
		
		Scanner eingabe = new Scanner(System.in);
		
		System.out.printf("Bitte geben Sie 6 Tipps nacheinander ein.");
	}

}
