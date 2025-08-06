package hub.forum.api.domain.response.model;

import hub.forum.api.domain.response.dto.UpdateResponseDTO;
import hub.forum.api.domain.topic.model.Topic;
import hub.forum.api.domain.user.model.User;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name = "responses")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Response {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String message;
    @Column(name = "createdAt", insertable = false, updatable = false)
    private LocalDateTime createdAt;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User author;
    private String solution;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "topic_id")
    private Topic topic;

    public Response( String message, User user, String solution, Topic topic) {
        this.message = message;
        this.author = user;
        this.solution = solution;
        this.topic = topic;
    }

    public void update(UpdateResponseDTO updateResponseDTO) {
        if(updateResponseDTO.message() != null){
            this.message = updateResponseDTO.message();
        }
        if(updateResponseDTO.solution() != null){
            this.solution = updateResponseDTO.solution();
        }
    }
}
