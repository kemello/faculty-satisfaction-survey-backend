package kg.adam.faculty_satisfaction_survey.survey.domain.model;

import kg.adam.faculty_satisfaction_survey.common.enums.QuestionCategory;
import kg.adam.faculty_satisfaction_survey.common.enums.QuestionType;

public record QuestionData (
        Long surveyId,
        String questionText,
        QuestionType questionType,
        QuestionCategory category,
        Integer order
){
}
