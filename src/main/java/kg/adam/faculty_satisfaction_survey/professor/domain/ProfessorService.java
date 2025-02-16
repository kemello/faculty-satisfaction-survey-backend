package kg.adam.faculty_satisfaction_survey.professor.domain;

import jakarta.transaction.Transactional;
import kg.adam.faculty_satisfaction_survey.professor.domain.model.*;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
public class ProfessorService {
    private final ProfessorRepository repository;

    ProfessorService(ProfessorRepository repository)
    {
        this.repository = repository;
    }

    public ProfessorData  createProfessor(CreateProfessorRequest professor) {
        ProfessorEntity newProfessor = ProfessorMapper.toEntity(professor);
        ProfessorEntity savedProfessor = repository.save(newProfessor);

        return ProfessorMapper.toData(savedProfessor);
    }


    @Transactional
    public AddCourseResponse  addCourse(AddCourseRequest request) {
        ProfessorEntity professor = repository.findById(request.professorId()).orElseThrow();
        CourseEntity course = CourseMapper.toEntity(request.courseName(), professor, request.assignments());
        professor.addCourse(course);
        repository.save(professor);

        return new AddCourseResponse(professor.getId(), course.getName());
    }

    public ProfessorData removeCourse(Long professorId, Long courseId) {
        ProfessorEntity professor = repository.findById(professorId).orElseThrow();
        CourseEntity course = professor.getCourses().stream().filter(c -> c.getId().equals(courseId)).findFirst().orElseThrow();
        professor.removeCourse(course);
        ProfessorEntity savedProfessor = repository.save(professor);

        return ProfessorMapper.toData(savedProfessor);
    }

    public ProfessorData getProfessor(Long professorId) {
        ProfessorEntity professor = repository.findById(professorId).orElseThrow();

        return ProfessorMapper.toData(professor);
    }

    //get professor courses
    @Transactional
    public Set<CourseData> getProfessorCourses(Long professorId) {
        ProfessorEntity professor = repository.findById(professorId).orElseThrow();
        return CourseMapper.toDataList(professor.getCourses());
    }
}
