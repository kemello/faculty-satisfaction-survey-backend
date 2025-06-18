package kg.adam.faculty_satisfaction_survey.survey.domain.model;

import java.time.LocalDateTime;

public record TokenData(
    String token,
    Long surveyId,
    Long courseId,
    Long professorId,
    LocalDateTime expiresAt
) {}