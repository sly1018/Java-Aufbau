package mitarbeiter.repository;

import java.util.List;

public interface MitarbeiterRepository {

	/**
	 * Alle Mitarbeiter laden
	 * 
	 * @return Liste von Mitarbeitern
	 * @throws MitarbeiterRepositoryException
	 */
	List<Mitarbeiter> selectAll() throws MitarbeiterRepositoryException;
	
	/**
	 * Einen Mitarbeiter anhand von dessen Id laden.
	 * @param id von einem Mitarbeiter
	 * @return Ein Mitarbeiter-Objekt
	 * @throws MitarbeiterRepositoryException
	 */
	Mitarbeiter selectById(int id) throws MitarbeiterRepositoryException;
	
	/**
	 * Einen Mitarbeiter hinzufügen
	 * @param mitarbeiter, Ein Mitarbeiter-Objekt, dass hinzugefügt wird.
	 * @return Id vom Mitarbeiter, dass hinzugefügt wurde.
	 * @throws MitarbeiterRepositoryException
	 */
	int insertMitarbeiter(Mitarbeiter mitarbeiter) throws MitarbeiterRepositoryException;
	
	/**
	 * Ein Mitarbeiter wird aktualisiert.
	 * @param mitarbeiter, das Mitarbeiter-Objekt, dass aktualisiert wird
	 * @throws MitarbeiterRepositoryException
	 */
	void updateMitarbeiter(Mitarbeiter mitarbeiter) throws MitarbeiterRepositoryException;
	
	/**
	 * Einen Mitarbeiter löschen.
	 * @param id des Mitarbeiters welches gelöscht werden soll.
	 * @throws MitarbeiterRepositoryException
	 */
	void deleteMitarbeiter(int id) throws MitarbeiterRepositoryException;

}
