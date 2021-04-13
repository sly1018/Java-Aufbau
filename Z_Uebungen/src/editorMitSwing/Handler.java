package editorMitSwing;

import java.awt.event.ActionEvent;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.charset.Charset;

import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

public class Handler implements DocumentListener {

	private Fenster fenster;
	private JFileChooser fileDialog;

	public Handler(Fenster f) {
		fenster = f;
		fileDialog = new JFileChooser(".");
	}

	public void onNew(ActionEvent evt) {
		System.out.println("onNew, command = " + evt.getActionCommand());

		if (fenster.statusTextArea == true)
			speichernAbfragen();
		else
			fenster.txtArea.setText(null);
	}

	public void onOpen(ActionEvent evt) {
		System.out.println("onOpen, command = " + evt.getActionCommand());

		String selFile = selectOpenFilename();
		if (selFile != null) {
			System.out.println("File ausgewählt: Pfad =" + selFile);
			fenster.txtArea.append(ausFileBlockweiseLesen(selFile));
			fenster.statusTextArea = false;
		} else {
			System.out.println("Es wurde kein File ausgewählt.");
		}
	}

	public void onSaveAs(ActionEvent evt) {
		System.out.println("onSaveAs, command = " + evt.getActionCommand());

		String selFile = selectSaveFilename();
		if (selFile != null) {
			System.out.println("File ausgewählt: Pfad =" + selFile);
			inFileSpeichern(selFile, fenster.txtArea.getText());
			fenster.statusTextArea = false;
		} else {
			System.out.println("Es wurde kein File ausgewählt.");
		}
	}

	public void onExit(ActionEvent evt) {
		System.out.println("onExit, command = " + evt.getActionCommand());

		int auswahl = JOptionPane.showConfirmDialog(fenster, "Möchtest du wirklich beenden?", "Beenden",
				JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
		if (auswahl == JOptionPane.YES_OPTION) {
			// Das Hauptfenster schließen
			fenster.dispose();
		} else {
			System.out.println("Beenden abgebrochen");
		}
	}

	private String selectOpenFilename() {
		int auswahl = fileDialog.showOpenDialog(fenster);
		// Wenn der Dialog mit OK beendet wurde
		if (auswahl == JFileChooser.APPROVE_OPTION) {
			File file = fileDialog.getSelectedFile();
			System.out.println("Der Benutzer hat ein File ausgewählt: " + file.getAbsolutePath());
			// Wenn das File existiert -> zurückliefern
			if (file.exists()) {
				// Als absoluten Pfad zurückliefern
				return file.getAbsolutePath();
			} else {
				// File existiert nicht -> Fehler anzeigen und Methode rekursiv aufrufen.
				JOptionPane.showConfirmDialog(fenster, "Das File existiert nicht. Bitte wähle ein anderes", "Öffnen",
						JOptionPane.DEFAULT_OPTION, JOptionPane.ERROR_MESSAGE);
				return selectOpenFilename();
			}

		} else {
			System.out.println("Der Benutzer hat Abbrechen gedrückt.");
			return null; // Kein File ausgwählt.
		}
	}

	private String selectSaveFilename() {
		int auswahl = fileDialog.showSaveDialog(fenster);
		// Wenn der Dialog mit OK beendet wurde
		if (auswahl == JFileChooser.APPROVE_OPTION) {
			File file = fileDialog.getSelectedFile();
			System.out.println("Der Benutzer hat ein File ausgewählt: " + file.getAbsolutePath());
			// Wenn es nicht existiert -> zurückliefern
			if (!file.exists()) {
				// Als absoluten Pfad zurückliefern.
				return file.getAbsolutePath();
			} else {
				int auswahl2 = JOptionPane.showConfirmDialog(fenster,
						"Das File existiert, möchtest Du es überschreiben?", "Speichern", JOptionPane.YES_NO_OPTION,
						JOptionPane.QUESTION_MESSAGE);
				if (auswahl2 == JOptionPane.YES_OPTION) {
					System.out.println("File existiert, Benutzer möchte es überschreiben.");
					return file.getAbsolutePath();
				} else {
					// Methode rekursiv aufrufen
					return selectSaveFilename();
				}
			}

		} else {
			System.out.println("Der Benutzer hat Abbrechen gedrückt.");
			return null; // Kein File ausgwählt.
		}
	}

	/**
	 * 
	 * @param filename Name der Datei aus der gelesen werden soll.
	 * @return Ein String wird zurückgeliefert, mit dem Inhalt aus der Textdatei.
	 */
	static String ausFileBlockweiseLesen(String filename) {
		String string = new String();
		// Das File zum Lesen öffnen, mit UTF-8 Kodierung
		try (FileReader reader = new FileReader(filename, Charset.forName("UTF-8"))) {
			char[] buffer = new char[1024]; // Sehr klein, damit die Schleife mehrmals ausgeführt wird.
			int count;
			// Speicherplatz für das Gelesene
			StringBuilder content = new StringBuilder();
			// Solange min. 1 Zeichen gelesen wurde.
			while ((count = reader.read(buffer)) > 0) {
				System.out.printf("%d Zeichen gelesen\n", count);
				// Die Zeichen im StringBuilder anhängen
				content.append(buffer, 0, count);
			}

			string = content.toString();

		} catch (IOException e) {
			System.out.println("Fehler beim einlesen: " + e);
		}

		return string;
	}

	/**
	 * Speichert Inhalt von JTextArea in ein File ab.
	 * 
	 * @param filename Absoluter Pfad des zu speichernden Datei.
	 * @param content  Inhalt der JTextArea in Form von String.
	 */
	static void inFileSpeichern(String filename, String content) {
		System.out.printf("filename: %s", filename);
		System.out.printf("content: %s", content);

		try {

			BufferedWriter writer = new BufferedWriter(new FileWriter(filename));

			// Inhalt der JTextArea ins File schreiben
			writer.write(content);
			// writer.write('\n');
			writer.newLine();
			// Den Writer schließen.
			writer.close();

		} catch (IOException e) {
			System.out.println("Fehler beim Speichern: " + e);
		}

	}

	@Override
	public void insertUpdate(DocumentEvent e) {
		setStatus();
	}

	@Override
	public void removeUpdate(DocumentEvent e) {
		setStatus();

	}

	@Override
	public void changedUpdate(DocumentEvent e) {
		setStatus();

	}

	private void setStatus() {
		fenster.statusTextArea = true;
	}

	public void speichernAbfragen() {
		int auswahl = JOptionPane.showConfirmDialog(fenster, "Möchtest du die Datei speichern??", "Speichern",
				JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
		if (auswahl == JOptionPane.YES_OPTION) {
			String selFile = selectSaveFilename(); 
			if (selFile != null) {
				inFileSpeichern(selFile, fenster.txtArea.getText());
				fenster.txtArea.setText(null);
			}
		} else {
			System.out.println("Datei wurde nicht gespeichert.");
			fenster.txtArea.setText(null);
		}
	}
}
