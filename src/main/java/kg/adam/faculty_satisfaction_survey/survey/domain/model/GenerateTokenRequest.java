package kg.adam.faculty_satisfaction_survey.survey.domain.model;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

public record GenerateTokenRequest(
    @NotNull(message = "Survey ID is required")
    @Positive(message = "Survey ID must be positive")
    Long surveyId,
    
    Integer validityInDays
) {}