package kg.adam.faculty_satisfaction_survey.professor.domain.model;

import java.util.Set;

public record AddCourseRequest(
        String courseName,
        Long professorId,
        Set<CourseAssignmentData> assignments
) {
}
