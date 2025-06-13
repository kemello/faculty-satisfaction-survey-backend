package kg.adam.faculty_satisfaction_survey.survey.domain.model;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

import java.util.Set;

public record AssignQuestionsRequest(
        @NotNull(message = "Survey ID is required")
        Long surveyId,

        @NotEmpty(message = "At least one question must be provided")
        Set<QuestionAssignmentData> assignments

) {
}
