package intro;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

// Ausführung von der Commandline (modular)
// -p ... --module-path ... Pfad zu den Module-Class-Files
// -m ... Main Klasse (im Format Modulname\Klassenname)
// java -p H_Swing2_MitModul\bin -m swingDemos/intro.ErstesFensterNeu
// Das Hauptfenster von JFrame ableiten, 
public class ErstesFensterNeu extends JFrame {

	private static final long serialVersionUID = 1L;

	public ErstesFensterNeu() {
		super("Ein weiteres Swingfenster (modular)");

		this.setSize(400, 300);

		this.setLayout(null);

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
		// Statt anonymer Implementierung.
//		btnAction.addActionListener(new ActionListener() {
//
//			@Override
//			public void actionPerformed(ActionEvent e) {
//				// Den Namen aus der Textbox holen
//				String name = txtName.getText();
//				// Ausgabe für Debug-Zwecke, der Enduser sieht das nicht.
//				System.out.println("Dein Name: " + name);
//				lblInfo.setText("Dein Name: %s".formatted(name));
//			}
//		});

		// Lambda Expression
		btnAction.addActionListener(

				e -> {
					// Den Namen aus der Textbox holen
					String name = txtName.getText();
					// Ausgabe für Debug-Zwecke, der Enduser sieht das nicht.
					System.out.println("Dein Name: " + name);
					lblInfo.setText("Dein Name: %s".formatted(name));
				});

		this.add(lblName);
		this.add(txtName);
		this.add(btnAction);
		this.add(lblInfo);

		// Das Fenster wirklich schließen wenn "Close" betätigt wird.
		// Weil es das Hauptfenster ist, wird damit das Programm beendet.
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

	}

	public static void main(String[] args) {
		// Swing mit eigener Hauptfensterklasse
		ErstesFensterNeu fenster = new ErstesFensterNeu();
		// Steuerelemente festlegen.

		// Das Fenster anzeigen, das Programm läuft weiter bis das Fenster geschlossen
		// wird.
		fenster.setVisible(true);
	}

}
