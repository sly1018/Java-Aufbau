package mitarbeiterVerwaltungListe;

import java.time.LocalDate;
import java.util.Comparator;

public class ByTypeUndEintrittsdatum implements Comparator<Mitarbeiter> {

	@Override
	public int compare(Mitarbeiter o1, Mitarbeiter o2) {
		int ret = 0;
		if (o1 == null && o2 == null) {
			ret = 0;
		} else if (o2 == null) {
			ret = 1;
		} else if (o1 == null) {
			ret = -1;
		} else {
			ret = Integer.compare(holTypNr(o1.getClass().getName()), holTypNr(o2.getClass().getName()));
			if (ret == 0) {
				ret = o1.getEintrittsdatum().compareTo(o2.getEintrittsdatum());
			}
		}
		return ret;
	}

	public int holTypNr(String typ) {
		int ret;

		switch (typ) {
		case "Mitarbeiter" -> ret = 1;
		case "Experte" -> ret = 2;
		case "Manager" -> ret = 3;
		default -> ret = 0;
		}

		return ret;
	}
}