package hub.forum.api.infra.token.dto;

import hub.forum.api.domain.profile.model.Profile;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public record TokenDTO(
        @NotBlank
        @Email
        String subject,
        @NotBlank
        String name,
        @NotBlank
        String role
) {
}
