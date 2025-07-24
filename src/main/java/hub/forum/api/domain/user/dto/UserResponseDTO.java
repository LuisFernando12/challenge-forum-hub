package hub.forum.api.domain.user.dto;

import hub.forum.api.domain.user.model.User;

public record UserResponseDTO(
        Long id,
        String name,
        String email,
        String profile
) {
    public UserResponseDTO(User userDB) {
        this(userDB.getId(), userDB.getName(), userDB.getEmail(), userDB.getProfile().getName());
    }
}
