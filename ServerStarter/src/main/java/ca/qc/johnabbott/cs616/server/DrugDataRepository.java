package ca.qc.johnabbott.cs616.server;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

/**
 * Created by Gabriel Charlebois on 2017-12-11.
 */
@RepositoryRestResource(collectionResourceRel = "data", path = "data")
public interface DrugDataRepository extends CrudRepository<DrugData, Long>{
}
