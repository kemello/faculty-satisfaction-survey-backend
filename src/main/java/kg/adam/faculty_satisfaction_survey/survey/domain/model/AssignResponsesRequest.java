package kg.adam.faculty_satisfaction_survey.survey.domain.model;

import java.util.List;
import java.util.TreeSet;

public record AssignResponsesRequest(
        Long surveyId,
        List<ResponseAssignmentData> responses
) {
}
