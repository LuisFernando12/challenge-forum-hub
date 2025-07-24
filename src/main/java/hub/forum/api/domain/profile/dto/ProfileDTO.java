package hub.forum.api.domain.profile.dto;

import jakarta.validation.constraints.NotBlank;

public record ProfileDTO(@NotBlank  String name ) {
}
