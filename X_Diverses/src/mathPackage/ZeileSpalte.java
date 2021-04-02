package mathPackage;

import java.util.Iterator;
import java.util.Random;

public class ZeileSpalte {

	public static void main(String[] args) {
		final int rowCount = 4, colCount = 3;

		char[][] field = new char[rowCount][colCount];

		for (int i = 0; i < field.length; i++) {
			for (int j = 0; j < field[i].length; j++) {
				field[i][j] = 'o';
			}
			System.out.println();
		}

		final int cellCount = rowCount * colCount;

		Random random = new Random();
		for (int i = 0; i < 5; i++) {
			// Zufallszahlen zwischen 0 und cellCount (exkl.) holen
			int cellIndex = random.nextInt(cellCount);
			// Mit Divsion die Zeile ermitteln
			int rowIndex = cellIndex / colCount;
			// Mit Divisionsrest die Spalte ermitteln
			int colIndex = cellIndex % colCount;
			field[rowIndex][colIndex] = 'x';
			System.out.printf("Zellindex %d entspricht Zeile %d/Spalte %d\n", cellIndex, rowIndex, colIndex);
		}

		for (int i = 0; i < field.length; i++) {
			for (int j = 0; j < field[i].length; j++) {
				System.out.print(field[i][j]);
			}
			System.out.println();
		}
	}

}
