package kg.adam.faculty_satisfaction_survey.survey.domain.model;

import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;

public record CreateSurveyRequest(
        @NotBlank(message = "Survey name is required")
        String name,

        String description,

        @NotNull(message = "Start date is required")
        @FutureOrPresent(message = "Start date must be today or future")
        LocalDate startDate,

        @NotNull(message = "End date is required")
        @Future(message = "End date must be in future")
        LocalDate endDate
) {
}
