package kg.adam.faculty_satisfaction_survey.survey.domain;


import kg.adam.faculty_satisfaction_survey.common.enums.QuestionCategory;
import kg.adam.faculty_satisfaction_survey.common.enums.QuestionType;
import kg.adam.faculty_satisfaction_survey.survey.domain.model.QuestionAssignmentData;
import kg.adam.faculty_satisfaction_survey.survey.domain.model.QuestionData;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

class QuestionMapper {
    static QuestionEntity toEntity(String questionText, QuestionType questionType, QuestionCategory category,
                                   SurveyEntity survey) {
        return new QuestionEntity(questionText, questionType, category, survey);
    }

    public static List<QuestionEntity> toEntityList(List<QuestionAssignmentData> assignments, SurveyEntity survey) {

        return assignments.stream()
                .map(assignment -> QuestionMapper.toEntity(
                        assignment.questionText(),
                        assignment.questionType(),
                        assignment.category(),
                        survey
                ))
                .collect(Collectors.toList());
        }

}
