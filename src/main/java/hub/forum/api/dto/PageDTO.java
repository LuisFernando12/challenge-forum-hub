package hub.forum.api.dto;

import hub.forum.api.domain.topic.dto.ResponseTopicDTO;
import org.springframework.data.domain.Page;

import java.util.List;

public record PageDTO<T>(
        List<T> content,
        Integer page,
        Integer size,
        Long totalElements,
        Integer totalPages
) {
    public static <T> PageDTO<T> from(Page<T> page){
        return new PageDTO<>(
                page.getContent(),
                page.getNumber(),
                page.getSize(),
                page.getTotalElements(),
                page.getTotalPages()
        );
    }

}
