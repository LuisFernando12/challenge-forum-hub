package hub.forum.api.domain.topic.model;

import hub.forum.api.domain.course.model.Course;
import hub.forum.api.domain.response.model.Response;
import hub.forum.api.domain.topic.dto.UpdateTopicDTO;
import hub.forum.api.domain.topic.enums.Status;
import hub.forum.api.domain.user.model.User;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Entity(name = "Topic")
@Table(name = "topics")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Topic {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;
    private String message;
    @Enumerated(EnumType.STRING)
    private Status status;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User author;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "course_id")
    private Course course;
    @OneToMany(mappedBy = "topic", cascade = CascadeType.ALL)
    private List<Response> response;
    @Column(name = "createdAt", insertable = false, updatable = false)
    private LocalDateTime createdAt;

    public Topic(String title, String message, Status status, User userDB, Course courseDB) {
        this.title = title;
        this.message = message;
        this.status = status;
        this.author = userDB;
        this.course = courseDB;
    }

    public void update(UpdateTopicDTO updateTopicDTO) {
        if(updateTopicDTO.message()!=null){
            this.message = updateTopicDTO.message();
        }
        if(updateTopicDTO.title()!= null){
            this.title = updateTopicDTO.title();
        }
        if(updateTopicDTO.status()!=null){
            this.status = updateTopicDTO.status();
        }
    }
}
