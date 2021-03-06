package intro;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

public class ErstesFenster {

	public static void main(String[] args) {
		// Swing mit "Spaghetti-Code"

		JFrame fenster = new JFrame("Mein erstes Swingfenster (modular)");
		fenster.setSize(400, 300);
		// Absolute Positionierung, dh wir müssen selber Größe und Position der
		// Steuerelemente festlegen.
		fenster.setLayout(null);

		// Weitere Steuerelemente.
		JLabel lblName = new JLabel("Dein Name");
		// setLocation und setBounds in einem.
		lblName.setBounds(30, 50, 70, 25);
		JTextField txtName = new JTextField();
		txtName.setBounds(110, 50, 150, 25);
		JButton btnAction = new JButton("Klick mich!");
		btnAction.setBounds(110, 100, 100, 25);

		JLabel lblInfo = new JLabel();
		lblInfo.setBounds(30, 140, 200, 50);

		// Beim Button einen ActionListener registrieren
		btnAction.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// Den Namen aus der Textbox holen
				String name = txtName.getText();
				// Ausgabe für Debug-Zwecke, der Enduser sieht das nicht.
				System.out.println("Dein Name: " + name);
				lblInfo.setText("Dein Name: %s".formatted(name));
			}
		});

		fenster.add(lblName);
		fenster.add(txtName);
		fenster.add(btnAction);
		fenster.add(lblInfo);

		// Das Fenster wirklich schließen wenn "Close" betätigt wird.
		// Weil es das Hauptfenster ist, wird damit das Programm beendet.
		fenster.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

		// Das Fenster anzeigen, das Programm läuft weiter bis das Fenster geschlossen
		// wird.
		fenster.setVisible(true);
	}

}
