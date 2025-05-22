package kg.adam.faculty_satisfaction_survey.survey.domain.model;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;

import java.util.List;
import java.util.TreeSet;

public record AssignResponsesRequest(
        @NotNull(message = "Survey ID is required")
        Long surveyId,

        @NotNull(message = "At least one response must be provided")
        List<@Valid ResponseAssignmentData> responses
) {
}
