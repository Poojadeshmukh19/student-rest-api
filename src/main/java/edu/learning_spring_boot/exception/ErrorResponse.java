package edu.learning_spring_boot.exception;

import java.time.Instant;

public record ErrorResponse(
        boolean success,
        String errorCode,
        String msg,
        Instant timestamp,
        String path
) {
    public static ErrorResponse error(
            String errorCode,
            String msg,
            String path
    ){
        return new ErrorResponse(
                false,
                errorCode,
                msg,
                Instant.now(),
                path
        );
    }
}
