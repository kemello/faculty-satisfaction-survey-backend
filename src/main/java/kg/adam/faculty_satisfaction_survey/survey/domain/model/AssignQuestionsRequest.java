package kg.adam.faculty_satisfaction_survey.survey.domain.model;

import java.util.Set;

public record AssignQuestionsRequest(
        Long surveyId,
        Set<QuestionAssignmentData> assignments

) {
}
