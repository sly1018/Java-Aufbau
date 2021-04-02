package intro2;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

public class IntroFenster2 extends JFrame {

	private static final long serialVersionUID = 1L;

	final JButton btnOpen, btnSaveAs, btnExit;

	final JList<String> lbEvents;
	final DefaultListModel<String> modelEvents;

	public final static String ACTION_OPEN = "OPEN", ACTION_SAVEAS = "SAVEAS", ACTION_EXIT = "EXIT";

	public IntroFenster2() {
		super("Filedialog etc.");
		setSize(400, 300);

		IntroHandler meinHandler = new IntroHandler(this);

		// Ein Panel mit Flow-Layout erstellen
		JPanel pnlButtons = new JPanel(new FlowLayout(FlowLayout.CENTER));
		// Die Buttons erzeugen und einfügen
		pnlButtons.add(btnOpen = new JButton("Öffnen"));
		btnOpen.setActionCommand(ACTION_OPEN);
		// Als ActionListener Methodenreferenz zu einer passenden Methode angeben.
		btnOpen.addActionListener(meinHandler::onOpen);

		pnlButtons.add(btnSaveAs = new JButton("Speicher unter"));
		btnSaveAs.setActionCommand(ACTION_SAVEAS);
		btnSaveAs.addActionListener(meinHandler::onSaveAs);

		pnlButtons.add(btnExit = new JButton("Beenden"));
		btnExit.setActionCommand(ACTION_EXIT);
		btnExit.addActionListener(meinHandler::onExit);

		// Das Panel an passender Position einfügen.
		add(pnlButtons, BorderLayout.NORTH);

		// In einem ScrollPane eine Listbox anzeigen.
		// Ein ListModel erzeugen, dass das einfügen von Zeichenfolgen erlaubt
		modelEvents = new DefaultListModel<>();
		// Die Listbox soll dieses ListModel verwenden.
		lbEvents = new JList<String>(modelEvents);
		JScrollPane scroll = new JScrollPane(lbEvents);
		add(scroll/* , BorderLayout.CENTER */);
		modelEvents.addElement("Fenster-Erzeugung abgeschlossen.");

		// Damit unsere Frage beim beenden angezeigt wird -> nichts automatisch
		// ausführen
		// setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
		// Stattdessen das close selber auslösen, wenn es nötig ist
		// Mit anonyme Implementierung von WindowAdapter(Hilfsklasse fürs implementieren
		// von WindowListener)
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				// Beim Exit-Button einen Klick auslösen
				btnExit.doClick();
			}
		});
	}

	public static void main(String[] args) {
		new IntroFenster2().setVisible(true);
	}

}
