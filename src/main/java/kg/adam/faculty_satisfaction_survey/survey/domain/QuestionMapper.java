package kg.adam.faculty_satisfaction_survey.survey.domain;


import kg.adam.faculty_satisfaction_survey.survey.domain.model.QuestionAssignmentData;
import kg.adam.faculty_satisfaction_survey.survey.domain.model.QuestionOptionData;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

class QuestionMapper {

    // Maps a single question assignment to a QuestionEntity
    static QuestionEntity toEntity(QuestionAssignmentData data, SurveyEntity survey) {
        Set<QuestionOption> options = data.options().stream()
                .map(QuestionMapper::toQuestionOption)
                .collect(Collectors.toSet());

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

    static QuestionAssignmentData toData(QuestionEntity questionEntity) {
        return new QuestionAssignmentData(
                questionEntity.getText(),
                questionEntity.getQuestionType(),
                questionEntity.getCategory(),
                questionEntity.getOrder(),
                questionEntity.getOptions().stream()
                        .map(QuestionMapper::toQuestionOptionData)
                        .collect(Collectors.toList())
        );
    }

    static QuestionOptionData toQuestionOptionData(QuestionOption questionOption) {
        return new QuestionOptionData(
                questionOption.getText(),
                questionOption.getValue(),
                questionOption.getOrder()
        );
    }

    // Maps a list of question assignmentData to a list of QuestionEntity objects
    static Set<QuestionEntity> toEntitySet(Set<QuestionAssignmentData> assignmentData, SurveyEntity survey) {
        return assignmentData.stream()
                .map((QuestionAssignmentData data) -> toEntity(data, survey))
                .collect(Collectors.toSet());
    }

    static QuestionOption toQuestionOption(QuestionOptionData optionData) {
        return new QuestionOption(
                optionData.order(),
                optionData.text(),
                optionData.value()
        );
    }

    static Set<QuestionAssignmentData> toDataSet(Set<QuestionEntity> entities) {
        return entities.stream()
                .map(QuestionMapper::toData)
                .collect(Collectors.toSet());
    }


}