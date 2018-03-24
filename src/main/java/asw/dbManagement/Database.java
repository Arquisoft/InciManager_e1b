package asw.dbManagement;

import asw.Incidence;

public interface Database {
	
	/**
	 * Add a new incidence.
	 * @param incidence
	 */
    void sendInci(Incidence incidence);
}
