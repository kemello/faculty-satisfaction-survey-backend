package kg.adam.faculty_satisfaction_survey.survey.domain;

import kg.adam.faculty_satisfaction_survey.survey.domain.model.CreateSurveyRequest;
import kg.adam.faculty_satisfaction_survey.survey.domain.model.SurveyData;

class SurveyMapper {
    static SurveyEntity toEntity(CreateSurveyRequest request) {
        return new SurveyEntity(request.name(), request.description(), request.endDate(), request.startDate());
    }

    public static SurveyData toData(SurveyEntity entity) {
        return new SurveyData(entity.getId(), entity.getName(), entity.getDescription(), entity.getStartDate(), entity.getEndDate());
    }
}
