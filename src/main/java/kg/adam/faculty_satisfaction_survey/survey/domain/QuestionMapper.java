package kg.adam.faculty_satisfaction_survey.survey.domain;


import kg.adam.faculty_satisfaction_survey.common.enums.QuestionCategory;
import kg.adam.faculty_satisfaction_survey.common.enums.QuestionType;
import kg.adam.faculty_satisfaction_survey.survey.domain.model.QuestionAssignmentData;
import kg.adam.faculty_satisfaction_survey.survey.domain.model.QuestionData;
import kg.adam.faculty_satisfaction_survey.survey.domain.model.QuestionOptionData;

import java.util.List;
import java.util.stream.Collectors;

class QuestionMapper {

    // Maps a single question assignment to a QuestionEntity
    static QuestionEntity toEntity(QuestionAssignmentData data, SurveyEntity survey) {
        List<QuestionOption> options = data.options().stream()
                .map(QuestionMapper::toQuestionOption)
                .toList();
        QuestionEntity questionEntity = new QuestionEntity(
                data.questionText(),
                data.questionType(),
                data.category(),
                data.order(),
                survey
        );

        options.forEach(questionEntity::addOption);
        return questionEntity;
    }

    // Maps a list of question assignmentData to a list of QuestionEntity objects
    static List<QuestionEntity> toEntityList(List<QuestionAssignmentData> assignmentData, SurveyEntity survey) {
        return assignmentData.stream()
                .map((QuestionAssignmentData data) -> toEntity(data, survey))
                .collect(Collectors.toList());
    }

    static QuestionOption toQuestionOption(QuestionOptionData optionData) {
        return new QuestionOption(
                optionData.order(),
                optionData.text(),
                optionData.value()
        );
    }

}