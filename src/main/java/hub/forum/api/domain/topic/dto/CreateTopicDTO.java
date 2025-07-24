package hub.forum.api.domain.topic.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record CreateTopicDTO(
        @NotBlank
        String title,
        @NotBlank
        String message,
        @NotNull
        Long author_id,
        @NotNull
        Long course_id
) {
}
