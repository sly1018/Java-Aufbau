package listen.personen;

public interface PersonenGruppeItf {

	/**
	 * 
	 * @param p die Person, die angemeldet werden soll
	 */
	void anmelden(Person p);

	/**
	 * Eine Person anmelden. Es wird eine Personobjekt erzeugt und im Array am
	 * nächsten freien Platz hinzugefügt
	 * @param name der Name der Person
	 * @param alter Das Alter der Person
	 */

	void anmelden(String name, int alter);

	void alleAnzeigen();

	void anzeigen(int nr);

	/**
	 * Eine Person abmelden. Die Person wird aus dem Array entfernt. Dahinterliegende Objekte werden
	 * nach vorne verschoben.
	 * @param nr Die Nummer der Person die abgemeldet wird
	 * @return	Das Person-Objekt
	 */

	Person abmelden(int nr);
	
	Person suchen(int nr);

}