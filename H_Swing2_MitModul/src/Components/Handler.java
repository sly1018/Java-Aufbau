package Components;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.List;

import javax.swing.JOptionPane;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

public class Handler implements ActionListener, ItemListener, DocumentListener {

	private ElementFenster meinFenster;

	public Handler(ElementFenster fenster) {
		this.meinFenster = fenster;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// Mit Switch verzweigen (Achtung: case-sensitive)
		switch (e.getActionCommand()) {
		case "OK" -> onOK();
		case "CANCEL" -> onCancel();
		default -> throw new IllegalArgumentException("Unexpected value: " + e.getActionCommand());
		}
	}

	private void onOK() {
		System.out.println("OK Button ausgelöst");
		// Ein Teilnehmer-Objekt erzeugen und mit den eingegebenen Werten
		// initialisieren.
		Teilnehmer tnNeu = new Teilnehmer();
		tnNeu.setZuname(meinFenster.txtZuname.getText());
		tnNeu.setVorname(meinFenster.txtVorname.getText());
		tnNeu.setPlz(meinFenster.txtPLZ.getText());
		tnNeu.setOrt(meinFenster.txtOrt.getText());
		tnNeu.setStrasse(meinFenster.txtStrasse.getText());

		// Radiobuttons für Geschlecht
		Teilnehmer.Geschlecht geschlecht;
//		if (meinFenster.rbMann.isSelected()) {
//			geschlecht = Teilnehmer.Geschlecht.MAENNLICH;
//		} else {
//			geschlecht = Teilnehmer.Geschlecht.WEIBLICH;
//		}

		// Kürzer
		geschlecht = meinFenster.rbMann.isSelected() ? Teilnehmer.Geschlecht.MAENNLICH : Teilnehmer.Geschlecht.WEIBLICH;
		tnNeu.setGeschlecht(geschlecht);

		// Checkboxen und Listboxen
		if (meinFenster.cbWindows.isSelected()) {
			List<String> werte = meinFenster.lbWindowsVersionen.getSelectedValuesList();
			tnNeu.setWindowsKenntnisse(werte.toString());
		}
		if (meinFenster.cbUnix.isSelected()) {
			List<String> werte = meinFenster.lbUnixVersionen.getSelectedValuesList();
			tnNeu.setUnixKenntnisse(werte.toString());
		}

		tnNeu.setProgrammierKenntnisse(meinFenster.cbProgrammierung.isSelected());

		// Textarea analog Textfield
		tnNeu.setSpezialKenntnisse(meinFenster.taVorkenntnisse.getText());

		String info = tnNeu.toString();
		System.out.println("Daten erfasst: ");
		System.out.println(info);

		JOptionPane.showConfirmDialog(meinFenster, info, "Daten erfasst", JOptionPane.DEFAULT_OPTION,
				JOptionPane.INFORMATION_MESSAGE);
	}

	private void onCancel() {
		System.out.println("Cancel Button ausgelöst");
		// Alle Eingaben löschen
		meinFenster.txtZuname.setText("");
		meinFenster.txtVorname.setText("");
		meinFenster.txtPLZ.setText("");
		meinFenster.txtOrt.setText("");
		meinFenster.txtStrasse.setText("");

		// Default-Radio-Button selektieren, der andere wird automatisch de-selektiert
		meinFenster.rbMann.setSelected(true);

		meinFenster.cbWindows.setSelected(false);
		meinFenster.cbUnix.setSelected(false);
		meinFenster.cbProgrammierung.setSelected(false);

		meinFenster.lbWindowsVersionen.clearSelection();
		meinFenster.lbUnixVersionen.clearSelection();

		meinFenster.taVorkenntnisse.setText("");
	}

	// Methode aus ItemListener überschreiben.
	@Override
	public void itemStateChanged(ItemEvent e) {
		// Herausfinden, ob ein StateChange auf "Selected" stattgefunden hat.
		boolean isSelected = e.getStateChange() == ItemEvent.SELECTED;
		// Wenn es die Checkbox für Windows ist -> die Windows-Liste enablen
		if (e.getSource() == meinFenster.cbWindows) {
			meinFenster.lbWindowsVersionen.setEnabled(isSelected);
		}
		// Wenn es die Checkbox für Unix ist -> die Unix-Liste enablen
		if (e.getSource() == meinFenster.cbUnix) {
			meinFenster.lbUnixVersionen.setEnabled(isSelected);
		}
	}

	// Überschreibungen für die DocumentListener-Methoden
	@Override
	public void insertUpdate(DocumentEvent e) {
		checkValid();
	}

	@Override
	public void removeUpdate(DocumentEvent e) {
		checkValid();
	}

	@Override
	public void changedUpdate(DocumentEvent e) {
		checkValid();
	}

	private void checkValid() {
		boolean valid = !meinFenster.txtVorname.getText().isEmpty() && !meinFenster.txtZuname.getText().isEmpty()
				&& !meinFenster.txtPLZ.getText().isEmpty() && !meinFenster.txtOrt.getText().isEmpty()
				&& !meinFenster.txtStrasse.getText().isEmpty();

		System.out.println("checkValid: gültig = " + valid);

		// Je nach Gültigkeit enablen oder disablen.
		meinFenster.btnOk.setEnabled(valid);
	}

}
