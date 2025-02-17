package kg.adam.faculty_satisfaction_survey.professor.web;

import kg.adam.faculty_satisfaction_survey.professor.domain.ProfessorService;
import kg.adam.faculty_satisfaction_survey.professor.domain.model.*;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

@RestController
@RequestMapping("/api/professors")
class ProfessorController {
    private final ProfessorService service;

    ProfessorController(ProfessorService service) {
        this.service = service;
    }

    @PostMapping
    ProfessorData createProfessor(@RequestBody CreateProfessorRequest  professor)
    {
        return service.createProfessor(professor);
    }

    //add course
    @PostMapping("/assign-to-course")
    AddCourseResponse addCourse(@RequestBody AddCourseRequest request)
    {
        return service.addCourse(request);
    }

    //get courses
    @GetMapping("/{professorId}/courses")
    Set<CourseData> getProfessorCoursesById(@PathVariable Long professorId)
    {
        return service.getProfessorCourses(professorId);
    }

    //get professors based on student info
    @GetMapping("/by-assignments")
    Set<ProfessorData> getProfessorsByAssignment(@RequestBody CourseAssignmentData data)
    {
        return service.getProfessorsByAssignment(data);
    }
}
