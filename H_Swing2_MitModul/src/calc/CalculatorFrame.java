package calc;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

public class CalculatorFrame extends JFrame {

	private static final long serialVersionUID = 1L;

	final JButton btnKlick1, btnKlick2, btnKlick3, btnKlick4/*
															 * , btnKlick5, btnKlick6, btnKlick7, btnKlick8, btnKlick9,
															 * btnKlick10, btnKlick11, btnKlick12, btnKlick13,
															 * btnKlick14, btnKlick15, btnKlick16, btnKlick17
															 */;
	final JLabel lblKopf, lblInfo/* , lblInfo2, lblInfo3 */;

	public CalculatorFrame() {
		super("Einfacher Taschenrechner");
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
		// Addition
		btnKlick1 = erstelleButton("+", "Addition", x, y + 50, width - 50, height);
		btnKlick1.addActionListener(this::verarbeiteOperator);
		add(btnKlick1);
		// Subtraktion
		btnKlick2 = erstelleButton("-", "Subtraktion", x + 50, y + 50, width - 50, height);
		btnKlick2.addActionListener(this::verarbeiteOperator);
		add(btnKlick2);
		// Multiplikation
		btnKlick3 = erstelleButton("*", "Multiplikation", x + 100, y + 50, width - 50, height);
		btnKlick3.addActionListener(this::verarbeiteOperator);
		add(btnKlick3);
		// Division
		btnKlick4 = erstelleButton("/", "Division", x + 150, y + 50, width - 50, height);
		btnKlick4.addActionListener(this::verarbeiteOperator);
		add(btnKlick4);

		lblInfo = new JLabel("Temp");
		lblInfo.setBounds(x + 75, y + 2 * (height + 20), 2 * width + 10, height);
		add(lblInfo);

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
			lblInfo.setText(lblInfo.getText() + op);
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
}
