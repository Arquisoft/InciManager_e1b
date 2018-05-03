package asw.dbManagement;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import asw.entities.Incidence;

@Service
public class MongoDatabase implements Database{
	
	@Autowired
    private IncidenceRepository incidences;
	

	@Override
	public void sendInci(Incidence incidence) {
		incidences.save(incidence);
	}


}
