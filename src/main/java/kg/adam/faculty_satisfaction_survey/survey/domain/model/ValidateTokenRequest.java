package kg.adam.faculty_satisfaction_survey.survey.domain.model;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

public record ValidateTokenRequest(
    @NotBlank(message = "Token is required")
    String token,
    
    @NotNull(message = "Survey ID is required")
    @Positive(message = "Survey ID must be positive")
    Long surveyId
) {}