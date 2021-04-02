package calc;

import java.awt.event.ActionEvent;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

public class CalculatorFrame extends JFrame {

	private static final long serialVersionUID = 1L;

	final JButton btnKlick1 /*
							 * , btnKlick2, btnKlick3, btnKlick4, btnKlick5, btnKlick6, btnKlick7,
							 * btnKlick8, btnKlick9, btnKlick10, btnKlick11, btnKlick12, btnKlick13,
							 * btnKlick14, btnKlick15, btnKlick16, btnKlick17
							 */;
	final JLabel lblKopf, lblInfo/* , lblInfo2, lblInfo3 */;

	public CalculatorFrame() {
		super("Einfacher Taschenrechner");
		setSize(280, 600);
		setLayout(null);
		int x = 30, y = 50, width = 100, height = 25;

		btnKlick1 = erstelleButton("+", "Plus", x, y + 50, width - 50, height);
		btnKlick1.addActionListener(this::verarbeiteOperator);
		add(btnKlick1);

		lblInfo = new JLabel("");
		lblInfo.setBounds(x, y + 2 * (height + 10), 2 * width + 10, height);
		add(lblInfo);

		lblKopf = new JLabel("Rechner");
		lblKopf.setBounds(30, 50, 100, 25);
		// lblKopf
		add(lblKopf);

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
