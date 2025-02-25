package kg.adam.faculty_satisfaction_survey.survey.domain.model;

import kg.adam.faculty_satisfaction_survey.common.enums.QuestionCategory;
import kg.adam.faculty_satisfaction_survey.common.enums.QuestionType;

public record QuestionAssignmentData(
        String questionText,
        QuestionType questionType,
        QuestionCategory category,
        Integer orderNumber
) {
}
