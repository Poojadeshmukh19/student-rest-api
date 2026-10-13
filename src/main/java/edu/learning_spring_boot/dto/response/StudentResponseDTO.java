package edu.learning_spring_boot.dto.response;

import lombok.Data;

@Data
public class StudentResponseDTO {
    private Long id;
    private String name;
    private String email;
    private Integer age;
}
