package hub.forum.api.domain.course.dto;

import jakarta.validation.constraints.NotBlank;

public record UpdateCourseDTO(
        @NotBlank
        String name
) {
}
