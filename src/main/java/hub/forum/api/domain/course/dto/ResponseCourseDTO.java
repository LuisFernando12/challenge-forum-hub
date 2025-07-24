package hub.forum.api.domain.course.dto;

import hub.forum.api.domain.course.enums.Category;
import hub.forum.api.domain.course.model.Course;

public record ResponseCourseDTO(
        Long id,
        String name,
        Category category
){
    public ResponseCourseDTO(Course courseDB) {
        this(courseDB.getId(), courseDB.getName(), courseDB.getCategory());
    }
}
