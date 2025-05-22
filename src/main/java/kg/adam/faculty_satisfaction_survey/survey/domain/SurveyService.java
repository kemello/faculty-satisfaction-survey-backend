package kg.adam.faculty_satisfaction_survey.survey.domain;

import jakarta.transaction.Transactional;
import kg.adam.faculty_satisfaction_survey.survey.domain.exception.QuestionNotFoundException;
import kg.adam.faculty_satisfaction_survey.survey.domain.exception.SurveyNotFoundException;
import kg.adam.faculty_satisfaction_survey.survey.domain.model.*;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
@Transactional
public class SurveyService {
    private final SurveyRepository repository;

    SurveyService(SurveyRepository repository) {
        this.repository = repository;
    }

    public SurveyData createSurvey(CreateSurveyRequest request) {
        SurveyEntity newSurvey = SurveyMapper.toEntity(request);
        SurveyEntity savedSurvey = repository.save(newSurvey);
        return SurveyMapper.toData(savedSurvey);
    }

    // 1. Extract common survey fetching logic
    private SurveyEntity getSurveyById(Long surveyId) {
        return repository.findById(surveyId)
                .orElseThrow(() -> SurveyNotFoundException.forId(surveyId));
    }

    private QuestionEntity getQuestionInSurvey(SurveyEntity survey, Long questionId) {
        return survey.getQuestions().stream()
                .filter(q -> Objects.equals(q.getId(), questionId))
                .findFirst()
                .orElseThrow(() -> QuestionNotFoundException.inSurvey(questionId, survey.getId()));
    }


    public void assignQuestions(AssignQuestionsRequest request) {
        SurveyEntity survey = getSurveyById(request.surveyId());


        // Clear existing questions (this properly manages orphan removal)
        survey.setQuestions(new HashSet<>()); // Replaces null check and clear


        Set<QuestionEntity> questions = QuestionMapper.toEntitySet(request.assignments(), survey);

        questions.forEach(survey::addQuestion);
        repository.save(survey);
    }

    public Set<QuestionAssignmentData> getQuestions(Long surveyId) {
        SurveyEntity survey = getSurveyById(surveyId);

        Set<QuestionEntity> questions = survey.getQuestions();
        return QuestionMapper.toDataSet(questions);
    }


    public QuestionAssignmentData findQuestionById(Long surveyId, Long questionId) {
        SurveyEntity survey = getSurveyById(surveyId);

        QuestionEntity question = survey.getQuestions().stream().filter(q -> {
            assert q.getId() != null;
            return q.getId().equals(questionId);
        }).findFirst().orElseThrow();
        return QuestionMapper.toData(question);
    }



    public void assignResponses(AssignResponsesRequest request) {
        SurveyEntity survey = getSurveyById(request.surveyId());

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
