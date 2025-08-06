package hub.forum.api.domain.response.dto;

import hub.forum.api.domain.response.model.Response;

import java.time.LocalDateTime;

public record ReturnResponseDTO(
        Long id,
        String message,
        LocalDateTime createdAt,
        Long author_id,
        Long topic_id,
        String solution
) {
    public ReturnResponseDTO(Response responseDB) {
        this(
                responseDB.getId(),
                responseDB.getMessage(),
                responseDB.getCreatedAt()!= null ? responseDB.getCreatedAt(): LocalDateTime.now(),
                responseDB.getAuthor().getId(),
                responseDB.getTopic().getId(),
                responseDB.getSolution()
        );
    }
}
