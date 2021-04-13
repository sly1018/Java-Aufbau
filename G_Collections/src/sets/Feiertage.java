package sets;

import java.time.LocalDate;

public class Feiertage {

	private final int jahr;

	// TODO eine Collectin für die Feiertage dieses Jahres definieren
	
	public Feiertage(int jahr) {
		this.jahr = jahr;
		// Feiertage einfügen
		// feiertage.add(LocalDate.of(jahr, 1, 1));
		// feiertage.add(LocalDate.of(jahr, 1, 6));
		// feiertage.add(LocalDate.of(jahr, 5, 1));
		// feiertage.add(LocalDate.of(jahr, 8, 15));
		// feiertage.add(LocalDate.of(jahr, 10, 26));
		// feiertage.add(LocalDate.of(jahr, 11, 1));
		// feiertage.add(LocalDate.of(jahr, 12, 8));
		// feiertage.add(LocalDate.of(jahr, 12, 25));
		// feiertage.add(LocalDate.of(jahr, 12, 26));

		// // bewegliche Feiertage:
		// LocalDate tag = calcOstern();
		// // Ostermontag: 1 Tag
		// feiertage.add(tag = tag.plusDays(1));
		// // Christi Himmelfahrt
		// feiertage.add(tag = tag.plusDays(38));
		// // Pfingstmontag
		// feiertage.add(tag = tag.plusDays(11));
		// // Fronleichnam
		// feiertage.add(tag = tag.plusDays(10));
	}
	
	public int getJahr() {
		return jahr;
	}

	// KEINEN getter für das Feiertage-Set - der Aufrufer könnte sonst
	// nach Belieben Feiertage hinzufügen oder entfernen
	// public Set<Datum> getFeiertage(){
	// return feiertage;
	// }

	// Stattdessen ein Array von Datums-Objekten liefern
	public LocalDate[] getFeiertage() {
		// TODO Kopie der Tage zurückliefern
		LocalDate[] tage = new LocalDate[0];
		return tage;
	}

	public boolean istFeiertag(int tag, int monat) {
		// TODO: implementieren
		return false;
	}

	public void zeigeFeiertage() {
		// TODO: alle Feiertage anzeigen
	}

	private LocalDate calcOstern() {
		int days = (((255 - 11 * (jahr % 19)) - 21) % 30) + 21;
		int variant_bool = days > 48 ? -1 : 0;
		LocalDate ostern = LocalDate.of(jahr, 3, 1).plusDays(days + (variant_bool) + 6
				- ((jahr + jahr / 4 + days + (variant_bool) + 1) % 7));

		return ostern;
	}

}
