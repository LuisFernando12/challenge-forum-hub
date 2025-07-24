package hub.forum.api.domain.profile.repository;

import hub.forum.api.domain.profile.model.Profile;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProfileRepository extends JpaRepository<Profile, Long> {
}
