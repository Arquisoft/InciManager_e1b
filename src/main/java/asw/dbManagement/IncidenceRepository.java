package asw.dbManagement;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;

import asw.entities.Incidence;

public interface IncidenceRepository extends MongoRepository<Incidence,ObjectId> {
	
}
