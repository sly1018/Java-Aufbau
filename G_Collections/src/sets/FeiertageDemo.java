package sets;

import java.time.LocalDate;

public class FeiertageDemo {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		Feiertage feiertage;
		try {
			feiertage = new Feiertage(2020);

			feiertage.zeigeFeiertage();

			LocalDate einTag = LocalDate.of(feiertage.getJahr(), 12, 25);
			if (feiertage.istFeiertag(einTag.getDayOfMonth(), einTag.getMonthValue())) {
				System.out.printf("%s ist ein Feiertag %n", einTag);
			} else {
				System.out.printf("%s ist leider kein Feiertag %n", einTag);
			}

			// Feiertage-Auflistung
			LocalDate[] alleFeiertage = feiertage.getFeiertage();
			for (LocalDate datum : alleFeiertage) {
				if (feiertage.istFeiertag(datum.getDayOfMonth(), datum.getMonthValue())) {
					System.out.printf("OK, %s IST ein Feiertag%n", datum);
				} else {
					System.out.printf("FEHLER, %s sollte ein Feiertag sein%n",
							datum);
				}

				datum = datum.plusDays(2);

				if (feiertage.istFeiertag(datum.getDayOfMonth(), datum.getMonthValue())) {
					System.out.printf("FEHLER, %s sollte KEIN Feiertag sein%n",
							datum);
				} else {
					System.out.printf("OK, %s IST KEIN Feiertag%n", datum);
				}
			}

		} catch (Exception e) {
			System.out.println(e.toString());
		}

	}

}
