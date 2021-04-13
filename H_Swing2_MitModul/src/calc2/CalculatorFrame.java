package calc2;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

public class CalculatorFrame extends JFrame {

	private static final long serialVersionUID = 1L;

	// GUI
	final JButton btnKlick1, btnKlick2, btnKlick3, btnKlick4, btnKlick5, btnKlick6, btnKlick7, btnKlick8, btnKlick9,
			btnKlick10, btnKlick11, btnKlick12, btnKlick13, btnKlick14, btnKlick15, btnKlick16, btnKlick17;
	final JLabel lblKopf, lblOperand, lblBerechnung, lblErgebnis;

	// Variablen für Operanden und Ergebnis.
	int ergebnis = 0, operand, zwischenErgebnis;

	// String für Zahlenkombination.
	String zahlenKombi = new String("");

	// Char für Operator.
	char vorherigerOperator = ' ';

	public CalculatorFrame() {
		super("Rechner");
		setSize(250, 500);

		setLayout(null);

		// Die Koordinaten, Breite und Höhe festen Werten zuweisen.
		int x = 15, y = 50, width = 100, height = 25;

		// Eine Überschrift.
		lblKopf = new JLabel("Rechner");
		Font font = new Font("Arial", Font.BOLD, 30);
		lblKopf.setFont(font);
		lblKopf.setBounds(x + 35, y, width + 25, height);
		lblKopf.setBackground(new Color(220, 220, 220));
		lblKopf.setOpaque(true);
		add(lblKopf);

		// Die Buttons für Ziffern und Operatoren. Button mit ActionListener und
		// hinzufügen zum Frame.
		// Erste Reihe
		// Addition
		btnKlick1 = erstelleButton("+", "+", x, y + 50, width - 50, height);
		btnKlick1.addActionListener(this::verarbeiteOperator);
		add(btnKlick1);
		// Subtraktion
		btnKlick2 = erstelleButton("-", "-", x + 50, y + 50, width - 50, height);
		btnKlick2.addActionListener(this::verarbeiteOperator);
		add(btnKlick2);
		// Multiplikation
		btnKlick3 = erstelleButton("*", "*", x + 100, y + 50, width - 50, height);
		btnKlick3.addActionListener(this::verarbeiteOperator);
		add(btnKlick3);
		// Division
		btnKlick4 = erstelleButton("/", "/", x + 150, y + 50, width - 50, height);
		btnKlick4.addActionListener(this::verarbeiteOperator);
		add(btnKlick4);

		// Zweite Reihe
		// Sieben
		btnKlick5 = erstelleButton("7", "7", x, y + 75, width - 50, height);
		btnKlick5.addActionListener(this::verarbeiteZiffer);
		add(btnKlick5);
		// Acht
		btnKlick6 = erstelleButton("8", "8", x + 50, y + 75, width - 50, height);
		btnKlick6.addActionListener(this::verarbeiteZiffer);
		add(btnKlick6);
		// Neun
		btnKlick7 = erstelleButton("9", "9", x + 100, y + 75, width - 50, height);
		btnKlick7.addActionListener(this::verarbeiteZiffer);
		add(btnKlick7);
		// Modulo
		btnKlick8 = erstelleButton("%", "%", x + 150, y + 75, width - 50, height);
		btnKlick8.addActionListener(this::verarbeiteOperator);
		add(btnKlick8);

		// Dritte Reihe
		// Vier
		btnKlick9 = erstelleButton("4", "4", x, y + 100, width - 50, height);
		btnKlick9.addActionListener(this::verarbeiteZiffer);
		add(btnKlick9);
		// Fünf
		btnKlick10 = erstelleButton("5", "5", x + 50, y + 100, width - 50, height);
		btnKlick10.addActionListener(this::verarbeiteZiffer);
		add(btnKlick10);
		// Neun
		btnKlick11 = erstelleButton("6", "6", x + 100, y + 100, width - 50, height);
		btnKlick11.addActionListener(this::verarbeiteZiffer);
		add(btnKlick11);
		// Gleich
		btnKlick12 = erstelleButton("=", "=", x + 150, y + 100, width - 50, height);
		btnKlick12.addActionListener(this::zeigeErgebnis);
		add(btnKlick12);

		// Vierte Reihe
		// Eins
		btnKlick13 = erstelleButton("1", "1", x, y + 125, width - 50, height);
		btnKlick13.addActionListener(this::verarbeiteZiffer);
		add(btnKlick13);
		// Zwei
		btnKlick14 = erstelleButton("2", "2", x + 50, y + 125, width - 50, height);
		btnKlick14.addActionListener(this::verarbeiteZiffer);
		add(btnKlick14);
		// Drei
		btnKlick15 = erstelleButton("3", "3", x + 100, y + 125, width - 50, height);
		btnKlick15.addActionListener(this::verarbeiteZiffer);
		add(btnKlick15);
		// C
		btnKlick16 = erstelleButton("C", "C", x + 150, y + 125, width - 50, height);
		btnKlick16.addActionListener(this::setzeZurück);
		add(btnKlick16);

		// Fünfte Reihe
		btnKlick17 = erstelleButton("0", "0", x + 50, y + 150, width - 50, height);
		btnKlick17.addActionListener(this::verarbeiteZiffer);
		add(btnKlick17);

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

	private JButton erstelleButton(String text, String action, int x, int y, int w, int h) {
		JButton btn = new JButton(text);
		btn.setActionCommand(action);
		btn.setLocation(x, y);
		btn.setSize(w, h);

		return btn;
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

	private void verarbeiteZiffer(ActionEvent evt) {
		try {
			String z = evt.getActionCommand();
			// Ausgabe an der Konsole Für Debug Zwecke
			System.out.printf("Ziffer %s wird verarbeitet\n", z);

			zahlenKombi = zahlenKombi + z;

			operand = Integer.parseInt(zahlenKombi);

			lblOperand.setText(zahlenKombi);
			lblBerechnung.setText(lblBerechnung.getText() + z);

			/*
			 * // Variablen für Operanden und Ergebnis. int ergebnis = 0, operand,
			 * zwischenErgebnis;
			 * 
			 * // String für Zahlenkombination. String zahlenKombi = new String("");
			 * 
			 * // Char für Operator. char vorherigerOperator = ' ';
			 */

			// Für Debug Zwecke, Ausgabe an der Konsole alle Variablen und ihre Werte.
			System.out.printf(
					"[ergebnis: %d], [operand: %d], [zwischenErgebnis: %d], [zahlenKombi: %s], [vorherigerOperator: %c]\n",
					ergebnis, operand, zwischenErgebnis, zahlenKombi, vorherigerOperator);

		} catch (Exception e) {
			zeigeFehler(e, "Fehler beim verarbeiten der Ziffer");
		}
	}

	private void zeigeErgebnis(ActionEvent evt) {
		try {
			berechne();
			ergebnis = zwischenErgebnis;

			// Zurücksetzen
			setzeZurück(evt);

			lblErgebnis.setText(String.valueOf(ergebnis));

		} catch (Exception e) {
			zeigeFehler(e, "Fehler beim verarbeiten der Rechnung");
		}

	}

	private void zeigeFehler(Exception e, String titel) {
		System.err.println("Fehler in ButtonsFenster.actionPerformed: ");
		e.printStackTrace();
		JOptionPane.showConfirmDialog(this, e.getMessage(), titel, JOptionPane.DEFAULT_OPTION,
				JOptionPane.ERROR_MESSAGE);
	}

	private void berechne() {

		switch (vorherigerOperator) {
		case ' ' -> zwischenErgebnis = Integer.parseInt(zahlenKombi);
		case '+' -> addiere();
		case '-' -> subtrahiere();
		case '*' -> multipliziere();
		case '/' -> dividiere();
		case '%' -> moduliere();
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
		zwischenErgebnis = operand = 0;
		// Character
		vorherigerOperator = ' ';
		// String
		zahlenKombi = "";

	}
}
