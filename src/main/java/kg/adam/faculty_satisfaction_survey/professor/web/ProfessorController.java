package kg.adam.faculty_satisfaction_survey.professor.web;

import jakarta.validation.Valid;
import kg.adam.faculty_satisfaction_survey.common.enums.AcademicYear;
import kg.adam.faculty_satisfaction_survey.common.enums.Faculty;
import kg.adam.faculty_satisfaction_survey.common.enums.StudyMode;
import kg.adam.faculty_satisfaction_survey.professor.domain.ProfessorService;
import kg.adam.faculty_satisfaction_survey.professor.domain.model.*;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
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
    Set<ProfessorData> getProfessorsByAssignment(
            @RequestParam Faculty faculty,
            @RequestParam AcademicYear academicYear,
            @RequestParam StudyMode studyMode
    ) {
        CourseAssignmentData data = new CourseAssignmentData(faculty, academicYear, studyMode);
        System.out.println(faculty);
        System.out.println(academicYear);
        System.out.println(studyMode);
        return service.getProfessorsByAssignment(data);
    }

    // In ProfessorController.java
    @PostMapping("/batch")
    @ResponseStatus(HttpStatus.CREATED)
    List<ProfessorData> createProfessorsBatch(
            @Valid @RequestBody BatchCreateProfessorRequest request
    ) {
        return service.createProfessorsBatch(request);
    }

}


