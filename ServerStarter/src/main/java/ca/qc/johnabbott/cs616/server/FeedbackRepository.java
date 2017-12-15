package ca.qc.johnabbott.cs616.server;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

/**
 * Created by Gabriel Charlebois on 2017-12-14.
 */
@RepositoryRestResource(collectionResourceRel = "feedback", path = "feedback")
public interface FeedbackRepository extends CrudRepository<Feedback, Long>{
}
