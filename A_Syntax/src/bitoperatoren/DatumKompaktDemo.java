package bitoperatoren;

public class DatumKompaktDemo {

	public static void main(String[] args) {
		DatumKompakt datum = new DatumKompakt(15, 3, 2021);
		System.out.println(datum);

		datum = new DatumKompakt(29, 11, 2099);

		System.out.println(datum);
	}

}
