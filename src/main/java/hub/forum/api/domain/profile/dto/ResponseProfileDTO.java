package hub.forum.api.domain.profile.dto;

import hub.forum.api.domain.profile.model.Profile;

public record ResponseProfileDTO(
        Long id,
        String name
) {
    public ResponseProfileDTO(Profile profileDB) {
        this(profileDB.getId(), profileDB.getName());
    }
}
