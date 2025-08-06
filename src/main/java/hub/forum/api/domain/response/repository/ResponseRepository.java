package hub.forum.api.domain.response.repository;

import hub.forum.api.domain.response.model.Response;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ResponseRepository extends JpaRepository<Response, Long> {
}
