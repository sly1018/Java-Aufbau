package menues;

import java.awt.event.ActionEvent;
import java.io.File;

import javax.swing.JFileChooser;
import javax.swing.JOptionPane;

public class IntroHandler {

	private IntroFenster2 meinFenster;

	private JFileChooser fileDialog;

	public IntroHandler(IntroFenster2 fenster) {
		meinFenster = fenster;
		// File-Auswahldialog mit dem akt. Verzeichnes aus Ausgangspunkt
		fileDialog = new JFileChooser(".");
	}

	public void onOpen(ActionEvent evt) {
		System.out.println("onOpen, command = " + evt.getActionCommand());
		String selFile = selectOpenFilename();
		if (selFile != null) {
			System.out.println("File ausgewählt: Pfad =" + selFile);
			// Info in der Listbox hinzufügen
			meinFenster.modelEvents.addElement("File öffnen: " + selFile);
		} else {
			System.out.println("Es wurde kein File ausgewählt.");
		}
	}

	public void onSaveAs(ActionEvent evt) {
		System.out.println("onSaveAs, command = " + evt.getActionCommand());
		String selFile = selectSaveFilename();
		if (selFile != null) {
			System.out.println("File ausgewählt: Pfad =" + selFile);
			meinFenster.modelEvents.addElement("File speichern: " + selFile);
		} else {
			System.out.println("Es wurde kein File ausgewählt.");
		}
	}

	public void onExit(ActionEvent evt) {
		System.out.println("onExit, command = " + evt.getActionCommand());
		int auswahl = JOptionPane.showConfirmDialog(meinFenster, "Möchtest du wirklich beenden?", "Beenden",
				JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
		if (auswahl == JOptionPane.YES_OPTION) {
			// Das Hauptfenster schließen
			meinFenster.dispose();
		} else {
			System.out.println("Beenden abgebrochen");
			meinFenster.modelEvents.addElement("Beenden wurde abgebrochen");
		}
	}

	private String selectOpenFilename() {
		int auswahl = fileDialog.showOpenDialog(meinFenster);
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
				JOptionPane.showConfirmDialog(meinFenster, "Das File existiert nicht. Bitte wähle ein anderes",
						"Öffnen", JOptionPane.DEFAULT_OPTION, JOptionPane.ERROR_MESSAGE);
				return selectOpenFilename();
			}

		} else {
			System.out.println("Der Benutzer hat Abbrechen gedrückt.");
			return null; // Kein File ausgwählt.
		}
	}

	private String selectSaveFilename() {
		int auswahl = fileDialog.showSaveDialog(meinFenster);
		// Wenn der Dialog mit OK beendet wurde
		if (auswahl == JFileChooser.APPROVE_OPTION) {
			File file = fileDialog.getSelectedFile();
			System.out.println("Der Benutzer hat ein File ausgewählt: " + file.getAbsolutePath());
			// Wenn es nicht existiert -> zurückliefern
			if (!file.exists()) {
				// Als absoluten Pfad zurückliefern.
				return file.getAbsolutePath();
			} else {
				int auswahl2 = JOptionPane.showConfirmDialog(meinFenster,
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

}
