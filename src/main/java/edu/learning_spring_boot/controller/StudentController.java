package edu.learning_spring_boot.controller;

import edu.learning_spring_boot.dto.request.StudentRequestDTO;
import edu.learning_spring_boot.dto.request.UpdateStudentAge;
import edu.learning_spring_boot.dto.response.ApiResponseDto;
import edu.learning_spring_boot.dto.response.StudentResponseDTO;
import edu.learning_spring_boot.service.StudentService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springdoc.core.annotations.ParameterObject;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
//http://localhost:8080/api/v1/students
@RequestMapping("api/v1/students")
@RequiredArgsConstructor
public class StudentController {
    private final StudentService studentService;

    //handler methods

    //http://localhost:8080/api/v1/students/1
    @GetMapping("/{id}")
    public ResponseEntity<ApiResponseDto<StudentResponseDTO>> findStudentById(
            @PathVariable Long id,
            HttpServletRequest request
            ){
        return ResponseEntity.ok(
                ApiResponseDto.success(
                        "Student fetched successfully",
                        HttpStatus.OK.value(),
                        studentService.findStudentbyId(id),
                        request.getRequestURI()
                )
        );
    }

    @GetMapping
    //http://localhost:8080/api/v1/students
    public ResponseEntity<?> findAllStudent( // instead of ApiResponseDto<List<StudentResponseDto>> ---> we give <?>
            HttpServletRequest request
    ){
        return ResponseEntity.ok(
                ApiResponseDto.success(
                        "Students fetched successfully",
                        200,
                        studentService.findAllStudent(),
                        request.getRequestURI()
                )
        );
    }

    @GetMapping("/list")
    ResponseEntity<?> getStudents(
            @ParameterObject
            @PageableDefault(
                    size = 2,
                    page = 0,
                    sort = "name",
                    direction = Sort.Direction.ASC
            )
            Pageable pageable
    ){
        return ResponseEntity.ok(
                studentService.getStudents(pageable)
        );
    }

    @PostMapping
    public ResponseEntity<?> addStudent(
            @RequestBody
            @Valid
            StudentRequestDTO studentRequestDTO,
            HttpServletRequest request){
       return ResponseEntity.status(HttpStatus.CREATED.value())
               .body(
                       ApiResponseDto.success(
                               "Student added successfully",
                               HttpStatus.CREATED.value(),
                               studentService.addStudent(studentRequestDTO),
                               request.getRequestURI()
                       )
               );
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateStudent(
            @PathVariable Long id,
            @RequestBody
            @Valid
            StudentRequestDTO studentRequestDTO,
            HttpServletRequest request) {

        return ResponseEntity.status(HttpStatus.OK.value())
                .body(
                        ApiResponseDto.success(
                                "Student updated successfully",
                                HttpStatus.OK.value(),
                                studentService.updateStudent(id, studentRequestDTO),
                                request.getRequestURI()
                        )
                );
    }

    @PatchMapping("/{id}")
    public ResponseEntity<?> updateStudentAge(
            @PathVariable Long id,
            @RequestBody
            @Valid
            UpdateStudentAge updateStudentAge,
            HttpServletRequest request) {

        return ResponseEntity.status(HttpStatus.OK.value())
                .body(
                        ApiResponseDto.success(
                                "Student age updated successfully",
                                HttpStatus.OK.value(),
                                studentService.updateStudentAge(id, updateStudentAge),
                                request.getRequestURI()
                        )
                );
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteStudentById(
            @PathVariable
            Long id){
        studentService.deleteStudentById(id);
        return ResponseEntity.noContent().build();
    }
}
