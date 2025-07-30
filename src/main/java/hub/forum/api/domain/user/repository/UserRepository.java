package hub.forum.api.domain.user.repository;

import hub.forum.api.domain.user.model.User;
import jakarta.validation.constraints.NotBlank;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
    User findByEmail(String tokenSubject);
    boolean existsByEmail(@NotBlank String email);
}
