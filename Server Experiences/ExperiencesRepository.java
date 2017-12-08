package ca.qc.johnabbott.cs616.server;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource(collectionResourceRel = "Experiences", path = "Experiences")
public interface ExperiencesRepository extends CrudRepository<Experiences,Long>{





}