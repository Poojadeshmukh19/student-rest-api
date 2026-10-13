package edu.learning_spring_boot.dto.response;

import java.time.Instant;

public record ApiResponseDto<T>(
        boolean success,
        String msg,
        int status,
        T data,
        Instant timestamp,
        String path
) {
    public static <T> ApiResponseDto<T> success(
            String msg,
            int status,
            T data,
            String path
    ){
        return new ApiResponseDto<>(
                true,
                msg,
                status,
                data,
                Instant.now(),
                path
        );
    }
}
