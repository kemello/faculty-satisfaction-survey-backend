package kg.adam.faculty_satisfaction_survey.survey.domain.model;

public record ResponseAssignmentData(
        Long questionId,
        Long professorId,
        String content
) {
}
