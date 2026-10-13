package edu.learning_spring_boot.service;

import edu.learning_spring_boot.dto.request.StudentRequestDTO;
import edu.learning_spring_boot.dto.request.UpdateStudentAge;
import edu.learning_spring_boot.dto.response.PageResponse;
import edu.learning_spring_boot.dto.response.StudentResponseDTO;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface StudentService {
    //Business Logic Plan.
    public StudentResponseDTO findStudentbyId(Long id);

    public List<StudentResponseDTO> findAllStudent();

    PageResponse<StudentResponseDTO> getStudents(Pageable pageable);

    public StudentResponseDTO addStudent(StudentRequestDTO studentRequestDTO);

    public StudentResponseDTO updateStudent(
            Long id,
            StudentRequestDTO studentRequestDTO
    );

    public StudentResponseDTO updateStudentAge(Long id,UpdateStudentAge updateStudentAge);

    public void deleteStudentById(Long id);
}
