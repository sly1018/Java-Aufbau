package menues;

import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.DefaultListModel;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JScrollPane;
import javax.swing.KeyStroke;

public class IntroFenster2 extends JFrame {

	private static final long serialVersionUID = 1L;

	final JMenuItem miOpen, miSaveAs, miExit;

	final JList<String> lbEvents;
	final DefaultListModel<String> modelEvents;

	public final static String ACTION_OPEN = "OPEN", ACTION_SAVEAS = "SAVEAS", ACTION_EXIT = "EXIT";

	public IntroFenster2() {
		super("Filedialog etc.");
		setSize(400, 300);

		IntroHandler meinHandler = new IntroHandler(this);

		// Statt Button-Panel ein Menü hinzufügen.
		JMenu mnuDatei = new JMenu("Datei");
		mnuDatei.setMnemonic(KeyEvent.VK_D);

//		miOpen = new JMenuItem("Öffnen");
//		mnuDatei.add(miOpen);
		// Tastenkombination aus O bei gedrückter CTRL-Taste
		KeyStroke stroke = KeyStroke.getKeyStroke('O', InputEvent.CTRL_DOWN_MASK);
		mnuDatei.add(miOpen = createMenuItem("Öffnen", ACTION_OPEN, KeyEvent.VK_F, stroke));
		miOpen.addActionListener(meinHandler::onOpen);

		mnuDatei.add(miSaveAs = createMenuItem("Speichern unter", ACTION_SAVEAS, KeyEvent.VK_U, null));
		miSaveAs.addActionListener(meinHandler::onSaveAs);

		mnuDatei.add(miExit = createMenuItem("Beenden", ACTION_EXIT, KeyEvent.VK_E, null));
		miExit.addActionListener(meinHandler::onExit);

		// ...

		// Die Menüleiste erzeugen
		JMenuBar menuBar = new JMenuBar();
		// Das Datei-Menü hinzufügen
		menuBar.add(mnuDatei);
		// Im Frame die Menüleiste anzeigen
		setJMenuBar(menuBar);

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
				// Beim Exit-Menü-Item einen Klick auslösen
				miExit.doClick();
			}
		});
	}

	private JMenuItem createMenuItem(String text, String action, int mnemomic, KeyStroke shortcut) {
		JMenuItem mi = new JMenuItem(text, mnemomic);
		mi.setActionCommand(action);
		// Tastaturshortcut setzen, falls vorhanden
		if (shortcut != null) {
			mi.setAccelerator(shortcut);
		}

		return mi;
	}

	public static void main(String[] args) {
		new IntroFenster2().setVisible(true);
	}

}
