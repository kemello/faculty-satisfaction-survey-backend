package kg.adam.faculty_satisfaction_survey.professor.domain.model;

import java.time.LocalDateTime;

public record ProfessorData(
        Long id,
        String name,
        String avatarUrl,
        LocalDateTime createdAt
) {
}
