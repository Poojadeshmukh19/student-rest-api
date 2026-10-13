package edu.learning_spring_boot.dto.request;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class UpdateStudentAge {
    @NotNull
    @Min(value = 1, message = "age must be positive")
    @Max(value = 100, message = "age must be valid")
    private Integer age;
}
