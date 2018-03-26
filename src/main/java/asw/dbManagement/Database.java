package asw.dbManagement;

import asw.entities.Incidence;

public interface Database {
	
	/**
	 * Add a new incidence.
	 * @param incidence
	 */
    void sendInci(Incidence incidence);
}
