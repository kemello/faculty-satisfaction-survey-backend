package kg.adam.faculty_satisfaction_survey.survey.domain.model;

import java.util.List;

public record AssignQuestionsRequest(
        Long surveyId,
        List<QuestionAssignmentData> assignments

) {
}
