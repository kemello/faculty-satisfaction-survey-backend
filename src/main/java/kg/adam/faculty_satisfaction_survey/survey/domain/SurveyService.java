package kg.adam.faculty_satisfaction_survey.survey.domain;

import jakarta.transaction.Transactional;
import kg.adam.faculty_satisfaction_survey.survey.domain.model.AssignQuestionsRequest;
import kg.adam.faculty_satisfaction_survey.survey.domain.model.CreateSurveyRequest;
import kg.adam.faculty_satisfaction_survey.survey.domain.model.SurveyData;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Transactional
public class SurveyService {
    private final SurveyEntityRepository repository;

    SurveyService(SurveyEntityRepository repository) {
        this.repository = repository;
    }

    public SurveyData createSurvey(CreateSurveyRequest request) {
        SurveyEntity newSurvey = SurveyMapper.toEntity(request);
        SurveyEntity savedSurvey = repository.save(newSurvey);
        return SurveyMapper.toData(savedSurvey);
    }


    public void assignQuestions(AssignQuestionsRequest request) {
        SurveyEntity survey = repository.findById(request.surveyId()).orElseThrow();

        List<QuestionEntity> questionEntities = QuestionMapper.toEntityList(request.assignments(), survey);
        survey.setQuestions(questionEntities);
        repository.save(survey);
    }
}
