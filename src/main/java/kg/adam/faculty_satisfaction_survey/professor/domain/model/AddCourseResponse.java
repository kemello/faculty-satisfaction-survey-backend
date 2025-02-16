package kg.adam.faculty_satisfaction_survey.professor.domain.model;

import java.util.Set;

public record AddCourseResponse(
        Long professorId,
        String courseName
) {
}
