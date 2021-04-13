package calc3;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

public class CalcRaster extends JFrame {

	private static final long serialVersionUID = 1L;

	private JButton[] ziffern, operatoren;

	// Die Koordinaten, Breite und Höhe festen Werten zuweisen.
	int x = 15, y = 50, width = 100, height = 25;

	// Labels
	final JLabel lblKopf, lblOperand, lblBerechnung, lblErgebnis;

	// Variablen für Operanden und Ergebnis.
	int ergebnis = 0, operand, zwischenErgebnis;

	// String für Zahlenkombination.
	String zahlenKombi = new String("");

	// Char für Operator.
	char vorherigerOperator = ' ';

	public CalcRaster() {

		super("Rechner");
		setSize(250, 500);

		setLayout(null);

		// Eine Überschrift.
		lblKopf = new JLabel("Rechner");
		Font font = new Font("Arial", Font.BOLD, 30);
		lblKopf.setFont(font);
		lblKopf.setBounds(x + 35, y, width + 25, height);
		lblKopf.setBackground(new Color(220, 220, 220));
		lblKopf.setOpaque(true);
		add(lblKopf);

		String[] zfr = { "0", "1", "2", "3", "4", "5", "6", "7", "8", "9" };
		ziffern = new JButton[zfr.length];

		System.out.println("Ziffern:");
		for (int m = 0; m < ziffern.length; m++) {
			ziffern[m] = erstelleButton(zfr[m], zfr[m]);
			ziffern[m].addActionListener(this::verarbeiteZiffer);
			// Debug Zwecke
			System.out.println(ziffern[m].getText());
		}

		System.out.println();

		System.out.println("Operatoren:");
		String[] ops = { "+", "-", "*", "/", "%", "=", "C" };
		operatoren = new JButton[ops.length];

		for (int n = 0; n < operatoren.length; n++) {
			operatoren[n] = erstelleButton(ops[n], ops[n]);

			if (operatoren[n].getText().equals("="))
				operatoren[n].addActionListener(this::zeigeErgebnis);

			if (operatoren[n].getText().equals("C"))
				operatoren[n].addActionListener(this::setzeZurück);

			else
				operatoren[n].addActionListener(this::verarbeiteOperator);

			System.out.println(operatoren[n].getText());
		}

		JButton[][] raster = { { operatoren[0], operatoren[1], operatoren[2], operatoren[3] },
				{ ziffern[7], ziffern[8], ziffern[9], operatoren[4] },
				{ ziffern[4], ziffern[5], ziffern[6], operatoren[5] },
				{ ziffern[1], ziffern[2], ziffern[3], operatoren[6] }, { null, ziffern[0], null, null } };

		//System.out.println("raster-lenght: " + raster.length);
		
		for (int i = 0; i < raster.length; i++) {
			System.out.println("Erste for-Schleife");
			for (int j = 0; j < raster[j].length; j++) {
				System.out.println("Zweite for-Schleife");
				JButton btn = raster[i][j];
				if (btn != null) {
					btn.setLocation(x, y + 50);
					btn.setSize(width - 50, height);
					add(btn);

					System.out.println(btn.getText());
				}
				x = x + 50;
			}
			x = 15;
			y = y + 25;
		}
		y = 50;

		// Labels
		// Operand
		lblOperand = new JLabel("");
		lblOperand.setBackground(new Color(255, 255, 255));
		lblOperand.setOpaque(true);
		lblOperand.setBounds(x + 50, y + 2 * (height + 100), width, height);
		add(lblOperand);

		// Berechnung
		lblBerechnung = new JLabel("");
		lblBerechnung.setBackground(new Color(255, 255, 255));
		lblBerechnung.setOpaque(true);
		lblBerechnung.setBounds(x + 50, y + 2 * (height + 125), width, height);
		add(lblBerechnung);

		// Ergebnis
		lblErgebnis = new JLabel("");
		lblErgebnis.setBackground(new Color(255, 255, 255));
		lblErgebnis.setOpaque(true);
		lblErgebnis.setBounds(x + 50, y + 2 * (height + 150), width, height);
		add(lblErgebnis);

		setDefaultCloseOperation(DISPOSE_ON_CLOSE);

	}

	private JButton erstelleButton(String text, String action) {
		JButton btn = new JButton(text);
		btn.setActionCommand(action);
		return btn;
	}

	private void verarbeiteZiffer(ActionEvent evt) {
		try {
			String z = evt.getActionCommand();
			// Ausgabe an der Konsole Für Debug Zwecke
			System.out.printf("Ziffer %s wird verarbeitet\n", z);

			zahlenKombi = zahlenKombi + z;

			operand = Integer.parseInt(zahlenKombi);

			lblOperand.setText(zahlenKombi);
			lblBerechnung.setText(lblBerechnung.getText() + z);

			// Für Debug Zwecke, Ausgabe an der Konsole alle Variablen und ihre Werte.
			System.out.printf(
					"[ergebnis: %d], [operand: %d], [zwischenErgebnis: %d], [zahlenKombi: %s], [vorherigerOperator: %c]\n",
					ergebnis, operand, zwischenErgebnis, zahlenKombi, vorherigerOperator);

		} catch (Exception e) {
			zeigeFehler(e, "Fehler beim verarbeiten der Ziffer");
		}
	}

	private void verarbeiteOperator(ActionEvent evt) {
		try {
			String op = evt.getActionCommand();
			System.out.printf("Operator %s wird verarbeitet\n", op);

			System.out.println("Vor der Berechnung:");
			System.out.printf(
					"[ergebnis: %d], [operand: %d], [zwischenErgebnis: %d], [zahlenKombi: %s], [vorherigerOperator: %c]\n",
					ergebnis, operand, zwischenErgebnis, zahlenKombi, vorherigerOperator);

			berechne();

			vorherigerOperator = op.charAt(0);

			// Für Debug Zwecke, Ausgabe an der Konsole alle Variablen und ihre Werte.
			System.out.println("Nach der Berechnung:");
			System.out.printf(
					"[ergebnis: %d], [operand: %d], [zwischenErgebnis: %d], [zahlenKombi: %s], [vorherigerOperator: %c]\n",
					ergebnis, operand, zwischenErgebnis, zahlenKombi, vorherigerOperator);

			lblBerechnung.setText(lblBerechnung.getText() + op);
			lblOperand.setText("");
			lblErgebnis.setText(Integer.toString(zwischenErgebnis));

			zahlenKombi = "";

		} catch (Exception e) {
			zeigeFehler(e, "Fehler beim verarbeiten des Operators");
		}
	}

	private void zeigeFehler(Exception e, String titel) {
		System.err.println("Fehler in ButtonsFenster.actionPerformed: ");
		e.printStackTrace();
		JOptionPane.showConfirmDialog(this, e.getMessage(), titel, JOptionPane.DEFAULT_OPTION,
				JOptionPane.ERROR_MESSAGE);
	}

	private void zeigeErgebnis(ActionEvent evt) {
		try {
			
			berechne();
			ergebnis = zwischenErgebnis;

			// Zurücksetzen
			setzeZurück(evt);

			lblErgebnis.setText(String.valueOf(ergebnis));
			zahlenKombi = String.valueOf(ergebnis);

		} catch (Exception e) {
			zeigeFehler(e, "Fehler beim verarbeiten der Rechnung");
		}

	}

	private void berechne() {

		switch (vorherigerOperator) {
		case ' ' -> zwischenErgebnis = Integer.parseInt(zahlenKombi);
		case '+' -> addiere();
		case '-' -> subtrahiere();
		case '*' -> multipliziere();
		case '/' -> dividiere();
		case '%' -> moduliere();
		case '=' -> vorherigerOperator = ' ';
		default -> throw new IllegalArgumentException("Unexpected value: " + vorherigerOperator);
		}
	}

	private void addiere() {
		zwischenErgebnis += operand;
	}

	private void subtrahiere() {
		zwischenErgebnis -= operand;
	}

	private void multipliziere() {
		zwischenErgebnis *= operand;
	}

	private void dividiere() {
		zwischenErgebnis /= operand;
	}

	private void moduliere() {
		zwischenErgebnis %= operand;
	}

	private void setzeZurück(ActionEvent evt) {
		// Labels
		lblOperand.setText("");
		lblBerechnung.setText("");
		lblErgebnis.setText("");
		// Integer
		zwischenErgebnis = 0;
		// Character
		vorherigerOperator = ' ';
		// String
		zahlenKombi = "";
	}

}
