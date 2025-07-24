package hub.forum.api.domain.topic.dto;

import hub.forum.api.domain.topic.enums.Status;
import hub.forum.api.domain.topic.model.Topic;

public record ResponseTopicDTO(
        Long id,
        String title,
        String message,
        Long author_id,
        Long course_id,
        Status status
) {
    public ResponseTopicDTO(Topic topicDB) {
        this(
            topicDB.getId(),
            topicDB.getTitle(),
            topicDB.getMessage(),
            topicDB.getAuthor().getId(),
            topicDB.getCourse().getId(),
            topicDB.getStatus()
        );
    }
}
