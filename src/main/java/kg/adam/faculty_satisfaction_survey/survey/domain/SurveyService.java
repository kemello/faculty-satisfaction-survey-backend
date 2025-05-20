package kg.adam.faculty_satisfaction_survey.survey.domain;

import jakarta.transaction.Transactional;
import kg.adam.faculty_satisfaction_survey.survey.domain.model.*;
import org.springframework.stereotype.Service;

import java.util.*;

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
        SurveyEntity survey = repository.findById(surveyId).orElseThrow();

        Set<QuestionEntity> questions = survey.getQuestions();
        return QuestionMapper.toDataSet(questions);
    }


    public QuestionAssignmentData findQuestionById(Long surveyId, Long questionId) {
        SurveyEntity survey = repository.findById(surveyId).orElseThrow();
        QuestionEntity question = survey.getQuestions().stream().filter(q -> {
            assert q.getId() != null;
            return q.getId().equals(questionId);
        }).findFirst().orElseThrow();
        return QuestionMapper.toData(question);
    }



    public void assignResponses(AssignResponsesRequest request) {
        SurveyEntity survey = repository.findById(request.surveyId())
                .orElseThrow(() -> new RuntimeException("Survey not found"));

        // Verify all questions exist in the survey
        request.responses().forEach(response -> {
            if (survey.getQuestions().stream()
                    .noneMatch(q -> {
                        assert q.getId() != null;
                        return q.getId().equals(response.questionId());
                    })) {
                throw new RuntimeException("Question not found in survey");
            }
        });

        List<ResponseEntity> responseEntities = ResponseMapper.toEntityList(survey, request.responses());

        responseEntities.forEach(response -> {
            survey.addResponse(response);
            // Also add to question's responses
            response.getQuestion().getResponses().add(response);
        });

        repository.save(survey);
    }


}
