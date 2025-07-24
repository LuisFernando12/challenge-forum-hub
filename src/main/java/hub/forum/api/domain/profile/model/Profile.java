package hub.forum.api.domain.profile.model;

import hub.forum.api.domain.profile.dto.ProfileDTO;
import hub.forum.api.domain.user.model.User;
import jakarta.persistence.*;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity(name = "Profile")
@Table(name = "profiles")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Profile {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    @OneToMany(mappedBy = "profile")
    private List<User> users;


    public void update(ProfileDTO updateDTO) {
        if(updateDTO.name()!= null){
            this.name = updateDTO.name();
            return;
        }
        throw new RuntimeException("Error body not null to update");
    }
}
