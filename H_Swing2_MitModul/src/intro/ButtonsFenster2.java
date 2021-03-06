package intro;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

public class ButtonsFenster2 extends JFrame {

	private static final long serialVersionUID = 1L;
	// Attribute für unsere Controls(=Components = Steuerelemente)
	final JButton btnKlick1, btnKlick2, btnKlick3;
	final JLabel lblInfo;

	public ButtonsFenster2() {
		super("Viele Buttons (2)...");
		setSize(280, 600);
		// Absolute Positionierung
		setLayout(null);
		int x = 30, y = 50, width = 100, height = 25;

		btnKlick1 = createButton("Ziffer 1", "1", x, y, width, height);
		// Methodenreferenz als Eventhandler
		btnKlick1.addActionListener(this::verarbeiteZiffer);
		add(btnKlick1);

		// Rechts daneben
		btnKlick2 = createButton("Ziffer 2", "2", x + width + 10, y, width, height);
		btnKlick2.addActionListener(this::verarbeiteZiffer);
		add(btnKlick2);

		// 2. Zeile
		btnKlick3 = createButton("Operator A", "A", x, y + height + 10, width, height);
		btnKlick3.addActionListener(this::verarbeiteOperator);
		add(btnKlick3);

		lblInfo = new JLabel("");
		lblInfo.setBounds(x, y + 2 * (height + 10), 2 * width + 10, height);
		Font font = new Font("Arial", Font.BOLD, 20);
		lblInfo.setFont(font);
		add(lblInfo);

		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
	}

	private JButton createButton(String text, String action, int x, int y, int w, int h) {
		JButton btn = new JButton(text);
		btn.setActionCommand(action);
		btn.setLocation(x, y);
		btn.setSize(w, h);

		return btn;
	}

//	@Override
//	public void actionPerformed(ActionEvent evt) {
//		// Eine Handler-Methode für alle Buttons
//		try {
//			switch (evt.getActionCommand()) {
//			case "1":
//			case "2":
//				verarbeiteZiffer(evt.getActionCommand());
//				break;
//			case "A":
//				verarbeiteOperator(evt.getActionCommand());
//			default:
//				break;
//			}
//		} catch (Exception e) {
//			System.err.println("Fehler in ButtonsFenster.actionPerformed:");
//			e.printStackTrace();
//			// Messagebox anzeigen
//			JOptionPane.showConfirmDialog(this, // Parent
//					e.getMessage(), "Fehler in actionPerfomed", // Message und Titel
//					JOptionPane.DEFAULT_OPTION, // Welche Buttons angezeigt werden sollen
//					JOptionPane.ERROR_MESSAGE); // Art der Message( Fehler, Info oder...)
//		}
//	}

	// Als mögliche Handler-Methode für ActionListener definieren
	private void verarbeiteZiffer(ActionEvent evt) {
		try {
			String ziffer = evt.getActionCommand();
			int zahl = Integer.parseInt(ziffer);
			System.out.printf("Verarbeite die Ziffer %d\n", zahl);
			lblInfo.setText(lblInfo.getText() + zahl);
		} catch (NumberFormatException e) {
			// TODO Auto-generated catch block
			zeigeFehler(e, "Fehler beim verarbeiten der Ziffere");
		}
	}

	private void verarbeiteOperator(ActionEvent evt) {
		try {
			String op = evt.getActionCommand();
			System.out.printf("Verarbeite den Operator %s\n", op);
			lblInfo.setText(lblInfo.getText() + op);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			zeigeFehler(e, "Fehler beim verarbeiten des Operators");
		}
	}

	private void zeigeFehler(Exception e, String titel) {
		System.err.println("Fehler in ButtonsFenster.actionPerformed: ");
		e.printStackTrace();
		// Messagebox anzeigen
		JOptionPane.showConfirmDialog(this, // Parent
				e.getMessage(), titel, // Message und Titel
				JOptionPane.DEFAULT_OPTION, // Welche Buttons angezeigt werden sollen
				JOptionPane.ERROR_MESSAGE); // Art der Message( Fehler, Info oder...)
	}
}
