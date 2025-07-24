package hub.forum.api.domain.course.repository;

import hub.forum.api.domain.course.model.Course;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CourseRepository extends JpaRepository<Course, Long> {
}
