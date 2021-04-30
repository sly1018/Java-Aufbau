package calcFX;

import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;

public class ViewCalc extends GridPane {

	private Button[] ziffern, operatoren;

	Label lblOperand, lblBerechnung, lblErgebnis;

	public ViewCalc() {
		// Handler mit Referenz auf unser Fenster
		HandlerCalc handler = new HandlerCalc(this);

		// Abstand
		setHgap(10);
		setVgap(10);

		// Abstand
		setPadding(new Insets(20));

		// Controls erzeugen
		int row = 0;
		Label lblKopf = new Label("Rechner mit FX");
		lblKopf.setId("lblKopf");
		add(lblKopf, 0, row, 4, 1);
		row++;

		// String Array für Ziffern
		String[] zfr = { "0", "1", "2", "3", "4", "5", "6", "7", "8", "9" };
		ziffern = new Button[zfr.length];

		// Button Array füllen mit Ziffern-String und passenden ActionEvent
		for (int m = 0; m < ziffern.length; m++) {
			ziffern[m] = erstelleButton(zfr[m]);
			ziffern[m].setOnAction(handler::verarbeiteZiffer);
		}

		// String Array für die Operatoren
		String[] ops = { "+", "-", "*", "/", "%", "=", "C" };
		operatoren = new Button[ops.length]; // Initialiserung der Größe des Operatoren-Arrays

		// Befüllen des Operatoren-Button-Array mit Namen aus dem
		// Operatoren-String-Array
		for (int n = 0; n < operatoren.length; n++) {
			operatoren[n] = erstelleButton(ops[n]);

			if (operatoren[n].getText().equals("C"))
				operatoren[n].setOnAction(handler::setzeZurück);
			else
				operatoren[n].setOnAction(handler::verarbeiteOperator);
		}

		// Raster erstellen
		Button[][] raster = { { operatoren[0], operatoren[1], operatoren[2], operatoren[3] },
				{ ziffern[7], ziffern[8], ziffern[9], operatoren[4] },
				{ ziffern[4], ziffern[5], ziffern[6], operatoren[5] },
				{ ziffern[1], ziffern[2], ziffern[3], operatoren[6] }, { null, ziffern[0], null, null } };

		for (int i = 0; i < raster.length; i++) {
			for (int j = 0; j < raster[j].length; j++) {
				Button btn = raster[i][j];
				if (btn != null) {
					add(btn, j, i + row);
				}
			}
		}

		// Labels hinzufügen zum Grid
		lblOperand = new Label("");
		lblOperand.setId("lblOperand");
		add(lblOperand, 4, 1);

		lblBerechnung = new Label("");
		lblBerechnung.setId("lblBerechnung");
		add(lblBerechnung, 4, 2);

		lblErgebnis = new Label("");
		lblErgebnis.setId("lblErgebnis");
		add(lblErgebnis, 4, 3);
	}

	private Button erstelleButton(String text) {
		Button btn = new Button(text);
		btn.setUserData(text);
		return btn;
	}

}
