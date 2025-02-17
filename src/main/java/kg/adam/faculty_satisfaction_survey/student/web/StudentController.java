package kg.adam.faculty_satisfaction_survey.student.web;

import kg.adam.faculty_satisfaction_survey.student.domain.StudentService;
import kg.adam.faculty_satisfaction_survey.student.domain.model.StudentInfo;
import kg.adam.faculty_satisfaction_survey.student.domain.model.StudentData;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/students")
class StudentController {
    private final StudentService service;

    public StudentController(StudentService service) {
        this.service = service;
    }

    @PostMapping
    public StudentData save(@RequestBody StudentInfo studentEntity) {
        return service.save(studentEntity);
    }

}
