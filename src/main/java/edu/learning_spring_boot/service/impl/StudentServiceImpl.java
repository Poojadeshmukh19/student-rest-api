package edu.learning_spring_boot.service.impl;

import edu.learning_spring_boot.dto.request.StudentRequestDTO;
import edu.learning_spring_boot.dto.request.UpdateStudentAge;
import edu.learning_spring_boot.dto.response.PageResponse;
import edu.learning_spring_boot.dto.response.StudentResponseDTO;
import edu.learning_spring_boot.entity.Student;
import edu.learning_spring_boot.mapper.StudentMapper;
import edu.learning_spring_boot.repository.StudentRepository;
import edu.learning_spring_boot.service.StudentService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class StudentServiceImpl implements StudentService {
    private StudentRepository studentRepository;
    private StudentMapper studentMapper;

    public StudentServiceImpl(StudentRepository studentRepository,
                              StudentMapper studentMapper) {
        this.studentRepository = studentRepository;
        this.studentMapper = studentMapper;
    }

    @Override
    public StudentResponseDTO findStudentbyId(Long id) {
        Student student = studentRepository.findById(id)
                .orElseThrow(
                        ()-> new RuntimeException(
                                "Student not found for the given id")
                );
        return studentMapper.toDto(student);
    }

    @Override
    public List<StudentResponseDTO> findAllStudent() {
        List<Student> students = studentRepository.findAll();
        return students.stream()
                .map(studentMapper::toDto)
                .toList();
    }

    @Override
    public PageResponse<StudentResponseDTO> getStudents(Pageable pageable) {
        Page<Student> page = studentRepository.findAll(pageable);
        return PageResponse.of(
                page.map(studentMapper::toDto)
        );
    }

    @Override
    public StudentResponseDTO addStudent(StudentRequestDTO studentRequestDTO) {
        Student student = studentMapper.toEntity(studentRequestDTO);
        Student saved = studentRepository.save(student);

        return studentMapper.toDto(saved);
    }

    @Override
    @Transactional
    public StudentResponseDTO updateStudent(Long id, StudentRequestDTO studentRequestDTO) {
        Student student = studentRepository.findById(id)
                .orElseThrow(
                        ()->new RuntimeException("Student not found for the given id")
                );
        studentMapper.updateStudent(studentRequestDTO,student);

        return studentMapper.toDto(student);
    }

    @Override
    @Transactional
    public StudentResponseDTO updateStudentAge(Long id, UpdateStudentAge updateStudentAge) {
        Student student = studentRepository.findById(id)
                .orElseThrow(
                        ()->new RuntimeException("Student not found for the given id")
                );
        student.setAge(updateStudentAge.getAge());

        return studentMapper.toDto(student);
    }

    @Override
    public void deleteStudentById(Long id) {
        studentRepository.deleteById(id);
    }
}
