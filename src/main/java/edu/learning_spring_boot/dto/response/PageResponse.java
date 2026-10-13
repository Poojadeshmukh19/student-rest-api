package edu.learning_spring_boot.dto.response;

import org.springframework.data.domain.Page;

import java.util.List;

public record PageResponse<T>(
        List<T> content,
        int page,
        int size,
        long totalEle,
        int totalPage,
        boolean isFirst,
        boolean isLast,
        boolean isEmpty
) {
    public static<T> PageResponse<T> of(Page<T> page){
        return new PageResponse<>(
                page.getContent(),
                page.getNumber(),
                page.getSize(),
                page.getTotalElements(),
                page.getTotalPages(),
                page.isFirst(),
                page.isLast(),
                page.isEmpty()
        );

    }
}
