package kg.adam.faculty_satisfaction_survey.student.domain;

import kg.adam.faculty_satisfaction_survey.student.domain.model.StudentInfo;
import kg.adam.faculty_satisfaction_survey.student.domain.model.StudentData;
import org.springframework.stereotype.Service;

@Service
public class StudentService {
    private final StudentRepository studentRepository;

    public StudentService(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    public StudentData save(StudentInfo info) {
        StudentEntity newStudent = StudentMapper.toEntity(info);
        StudentEntity savedStudent = studentRepository.save(newStudent);

        return StudentMapper.toData(savedStudent);
    }

    public StudentEntity findById(Long id) {
        return studentRepository.findById(id).orElse(null);
    }


}
