package hub.forum.api.domain.topic.repository;

import hub.forum.api.domain.topic.model.Topic;
import jakarta.validation.constraints.NotBlank;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TopicRepository extends JpaRepository<Topic, Long> {
    boolean existsByTitleOrMessage(@NotBlank String title, @NotBlank String message);
}
