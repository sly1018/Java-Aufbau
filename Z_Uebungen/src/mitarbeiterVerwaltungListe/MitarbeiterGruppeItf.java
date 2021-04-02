package mitarbeiterVerwaltungListe;

import java.time.LocalDate;

public interface MitarbeiterGruppeItf {

	double getGrundgehalt();

	String getName();

	LocalDate getEintrittsdatum();

	double berechneMonatsGehalt();

	double berechneJahresGehalt();

	void erhoeheGehalt(float prozentsatz);

	long berechneAnstellungsDauer();

	String holeMitarbeiterInfo();

	int compareTo(MitarbeiterGruppeItf o2);

}