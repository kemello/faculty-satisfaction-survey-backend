package kg.adam.faculty_satisfaction_survey.survey.domain;

import jakarta.transaction.Transactional;
import kg.adam.faculty_satisfaction_survey.survey.domain.model.AssignQuestionsRequest;
import kg.adam.faculty_satisfaction_survey.survey.domain.model.CreateSurveyRequest;
import kg.adam.faculty_satisfaction_survey.survey.domain.model.SurveyData;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
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

        // Clear existing questions (this properly manages orphan removal)
        if (survey.getQuestions() == null) {
            survey.setQuestions(new ArrayList<>());
        } else {
            survey.getQuestions().clear();
        }


        List<QuestionEntity> questionEntities = QuestionMapper.toEntityList(request.assignments(), survey);
        for (QuestionEntity question : questionEntities) {
            survey.addQuestion(question);
        }

        repository.save(survey);
    }

}
