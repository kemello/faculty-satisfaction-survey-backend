package kg.adam.faculty_satisfaction_survey.survey.domain.model;

public record ResponseData(
        Long surveyId,
        AssignResponsesRequest response
) {
}
