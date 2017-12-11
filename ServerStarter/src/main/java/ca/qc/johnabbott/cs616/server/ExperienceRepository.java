package ca.qc.johnabbott.cs616.server;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.Collection;

/**
 * Created by Connor King on 2017-12-8.
 */
@RepositoryRestResource(collectionResourceRel = "experience", path = "experience")
public interface ExperienceRepository extends CrudRepository<Experience,Long>{
}