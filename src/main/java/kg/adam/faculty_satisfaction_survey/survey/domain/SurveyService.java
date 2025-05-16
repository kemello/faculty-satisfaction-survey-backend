package kg.adam.faculty_satisfaction_survey.survey.domain;

import jakarta.transaction.Transactional;
import kg.adam.faculty_satisfaction_survey.survey.domain.model.*;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

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
            survey.setQuestions(new HashSet<>());
        } else {
            survey.getQuestions().clear();
        }


        Set<QuestionEntity> questionEntities = QuestionMapper.toEntitySet(request.assignments(), survey);
        for (QuestionEntity question : questionEntities) {
            survey.addQuestion(question);
        }

        repository.save(survey);
    }

    public Set<QuestionAssignmentData> getQuestions(Long surveyId) {
        SurveyEntity survey = repository.findById(1L).orElseThrow();

        Set<QuestionEntity> questions = survey.getQuestions();
        return QuestionMapper.toDataSet(questions);
    }
}
