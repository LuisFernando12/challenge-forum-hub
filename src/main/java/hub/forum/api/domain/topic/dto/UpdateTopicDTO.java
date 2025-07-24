package hub.forum.api.domain.topic.dto;

import hub.forum.api.domain.topic.enums.Status;

public record UpdateTopicDTO(
        String title,
        String message,
        Status status
) {
}
