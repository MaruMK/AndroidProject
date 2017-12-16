package ca.qc.johnabbott.cs616.server;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

<<<<<<< HEAD
@RepositoryRestResource(collectionResourceRel = "drug", path = "drug")
public interface DrugRepository extends CrudRepository<Drug, Long> {
=======
/**
 * Created by Gabriel Charlebois on 2017-12-10.
 */
@RepositoryRestResource(collectionResourceRel = "drug", path = "drug")
public interface DrugRepository  extends CrudRepository<Drug,Long> {
>>>>>>> master
}
