package hub.forum.api.domain.course.dto;

import hub.forum.api.domain.course.enums.Category;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record CreateCourseDTO(
        @NotBlank
        String name,
        @NotNull
        Category category
) {
}
