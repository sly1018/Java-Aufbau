package editorMitSwing2;

import java.awt.Color;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.KeyStroke;

public class Fenster extends JFrame {

	private static final long serialVersionUID = 1L;
	
	// Menü Punkte
	final JMenuItem miNew, miOpen, miSaveAs, miExit, miSave;
	
	JTextArea txtArea;
	
	boolean statusTextArea;

	
	public final static String ACTION_OPEN = "OPEN", ACTION_SAVEAS = "SAVEAS", ACTION_EXIT = "EXIT", ACTION_NEW = "NEW", ACTION_SAVE="SAVE";
	public Fenster() {
		setSize(400, 300);
		this.getContentPane().setBackground(Color.WHITE);
		statusTextArea = false;
		
		Handler handler = new Handler(this);
		
		// Menü
		JMenu mnuDatei = new JMenu("Datei");
		mnuDatei.setMnemonic(KeyEvent.VK_D); // Shortcut für Menüpunkt Datei.
			
		KeyStroke strokeNeu = KeyStroke.getKeyStroke('N', InputEvent.CTRL_DOWN_MASK);
		mnuDatei.add(miNew = createMenuItem("Neu", ACTION_NEW, KeyEvent.VK_N, strokeNeu));
		miNew.addActionListener(handler::onNew);
		
		KeyStroke strokeOeffnen = KeyStroke.getKeyStroke('O', InputEvent.CTRL_DOWN_MASK);
		mnuDatei.add(miOpen = createMenuItem("Öffnen", ACTION_OPEN, KeyEvent.VK_F, strokeOeffnen));
		miOpen.addActionListener(handler::onOpen);

		mnuDatei.add(miSave = createMenuItem("Speichern", ACTION_SAVE, KeyEvent.VK_S, null));
		miSave.addActionListener(handler::onSave);
		
		mnuDatei.add(miSaveAs = createMenuItem("Speichern unter", ACTION_SAVEAS, KeyEvent.VK_U, null));
		miSaveAs.addActionListener(handler::onSaveAs);

		mnuDatei.add(miExit = createMenuItem("Beenden", ACTION_EXIT, KeyEvent.VK_E, null));
		miExit.addActionListener(handler::onExit);
		
		
		
		// Die Menüleiste erzeugen
		JMenuBar menuBar = new JMenuBar();
		// Das Datei-Menü hinzufügen
		menuBar.add(mnuDatei);
		// Im Frame die Menüleiste anzeigen
		setJMenuBar(menuBar);
		
		// Textfeld
		txtArea = new JTextArea();
		// Listener für JTextArea
		txtArea.getDocument().addDocumentListener(handler);
		
		// Scrollpane
		JScrollPane scroll = new JScrollPane(txtArea);
		// Zum Fenster hinzufügen
		add(scroll);
		
		setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
		
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
		new Fenster().setVisible(true);
	}

}
