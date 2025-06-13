package kg.adam.faculty_satisfaction_survey.professor.domain;

import jakarta.transaction.Transactional;
import kg.adam.faculty_satisfaction_survey.professor.domain.model.*;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

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
    public AddCourseResponse addCourse(AddCourseRequest request) {
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

    @Transactional
    public Set<ProfessorData> getProfessorsByAssignment(CourseAssignmentData data) {
//        return repository.findAll().stream()
//                .filter(p -> p.getCourses().stream()
//                        .anyMatch(c -> c.getAssignments().stream()
//                                .anyMatch(a -> a.getFaculty().equals(data.faculty()) &&
//                                        a.getAcademicYear().equals(data.academicYear()) &&
//                                        a.getStudyMode().equals(data.studyMode()))))
//                .map(ProfessorMapper::toData)
//                .collect(Collectors.toSet());
        return repository.findAllByFaculty(data.faculty().name()).stream()
                .map(ProfessorMapper::toData)
                .collect(Collectors.toSet());
    }

    @Transactional
    public List<ProfessorData> createProfessorsBatch(BatchCreateProfessorRequest request) {
        List<ProfessorEntity> entities = request.professors().stream()
                .map(ProfessorMapper::toEntity)
                .toList();

        List<ProfessorEntity> saved = repository.saveAll(entities);

        return saved.stream()
                .map(ProfessorMapper::toData)
                .toList();
    }

}
