package edu.learning_spring_boot.mapper;

import edu.learning_spring_boot.dto.request.StudentRequestDTO;
import edu.learning_spring_boot.dto.response.StudentResponseDTO;
import edu.learning_spring_boot.entity.Student;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class StudentMapper {
   private final ModelMapper mapper;

   public Student toEntity(StudentRequestDTO studentRequestDto){
       return mapper.map(studentRequestDto, Student.class);
   }

   public StudentResponseDTO toDto(Student student){
       return mapper.map(student, StudentResponseDTO.class);
   }

   public void updateStudent(StudentRequestDTO studentRequestDTO,Student student){
       mapper.map(studentRequestDTO,student);
   }
}
