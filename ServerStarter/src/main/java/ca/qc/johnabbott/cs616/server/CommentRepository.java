package ca.qc.johnabbott.cs616.server;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

/**
 * Created by Gabriel Charlebois on 2017-12-10.
 */
@RepositoryRestResource(collectionResourceRel = "comment", path = "comment")
public interface CommentRepository extends CrudRepository<Comment, Long>{
}
