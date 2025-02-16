package kg.adam.faculty_satisfaction_survey.professor.domain.model;


import java.time.LocalDateTime;
import java.util.Set;

public record CourseData(
        Long id,
        String name,
        String professor,
        Set<CourseAssignmentData> assignments,
        LocalDateTime createdAt
) {
}
