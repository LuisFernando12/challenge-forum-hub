package hub.forum.api.domain.course.model;

import hub.forum.api.domain.course.dto.UpdateCourseDTO;
import hub.forum.api.domain.course.enums.Category;
import hub.forum.api.domain.topic.model.Topic;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Table(name = "courses")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Course {
    @Id @GeneratedValue(strategy =  GenerationType.IDENTITY)
    private Long id;
    private String name;
    @Enumerated(EnumType.STRING)
    private Category category;
    @OneToMany(mappedBy = "course")
    private List<Topic> topic;

    public void update(UpdateCourseDTO updateCourseDTO) {
        this.name = updateCourseDTO.name();
    }
}
