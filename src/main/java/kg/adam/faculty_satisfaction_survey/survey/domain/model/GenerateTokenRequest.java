package kg.adam.faculty_satisfaction_survey.survey.domain.model;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

public record GenerateTokenRequest(
    @NotNull(message = "Survey ID is required")
    @Positive(message = "Survey ID must be positive")
    Long surveyId,
    
    @NotNull(message = "Course ID is required")
    @Positive(message = "Course ID must be positive")
    Long courseId,
    
    @NotNull(message = "Professor ID is required")
    @Positive(message = "Professor ID must be positive")
    Long professorId,
    
    Integer validityInDays
) {}