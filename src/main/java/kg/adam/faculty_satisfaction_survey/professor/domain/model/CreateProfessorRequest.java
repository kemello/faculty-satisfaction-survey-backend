package kg.adam.faculty_satisfaction_survey.professor.domain.model;

public record CreateProfessorRequest(
        String name,
        String avatarUrl
) {
}