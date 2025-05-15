package kg.adam.faculty_satisfaction_survey.survey.domain.model;

import kg.adam.faculty_satisfaction_survey.common.enums.QuestionCategory;
import kg.adam.faculty_satisfaction_survey.common.enums.QuestionType;

import java.util.List;

public record QuestionAssignmentData(
        String questionText,
        QuestionType questionType,
        QuestionCategory category,
        Integer order,
        List<QuestionOptionData> options
) {
}
