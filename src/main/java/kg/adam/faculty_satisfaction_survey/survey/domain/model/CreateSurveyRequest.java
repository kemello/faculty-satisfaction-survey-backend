package kg.adam.faculty_satisfaction_survey.survey.domain.model;

import java.time.LocalDate;

public record CreateSurveyRequest(
        String name,
        String description,
        LocalDate startDate,
        LocalDate endDate
) {
}
