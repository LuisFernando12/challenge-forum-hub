package hub.forum.api.domain.response.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record CreateResponseDTO(
        @NotBlank
        String message,
        @NotNull
        Long topic_id,
        @NotNull
        Long author_id,
        @NotBlank
        String solution

) {
}
