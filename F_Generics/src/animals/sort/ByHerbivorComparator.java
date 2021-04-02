package animals.sort;

import java.util.Comparator;

public class ByHerbivorComparator implements Comparator<Animal> {

	@Override
	public int compare(Animal o1, Animal o2) {
		int ret = 0;
		// Beide null.
		if (o1 == null && o2 == null) {
			ret = 0;
		}
		// Nur das 2. null.
		else if (o2 == null) {
			ret = 1;
		}
		// Nur das 1. Null.
		else if (o1 == null) {
			ret = -1;
		}
		// Keines der beiden null
		else {
			ret = Boolean.compare(o1.isHerbivore(), o2.isHerbivore());
			// Wenn beide Pflanzenfresser oder beide Fleischfresser sind.
			if (ret == 0) {
				// -> jetzt noch nach lat. Namen vergleichen.
				ret = o1.getLatinName().compareTo(o2.getLatinName());
			}
		}
		return ret;
	}

}
