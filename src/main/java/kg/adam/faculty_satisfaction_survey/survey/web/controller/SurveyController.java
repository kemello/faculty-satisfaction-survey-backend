package kg.adam.faculty_satisfaction_survey.survey.web.controller;

import jakarta.validation.Valid;
import kg.adam.faculty_satisfaction_survey.survey.domain.SurveyService;
import kg.adam.faculty_satisfaction_survey.survey.domain.model.*;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

@RestController
@RequestMapping("/api/surveys")
class SurveyController {
    private final SurveyService service;

    SurveyController(SurveyService service) {
        this.service = service;
    }

    @PostMapping
    SurveyData createSurvey(@Valid @RequestBody CreateSurveyRequest request) {
        return service.createSurvey(request);
    }

    @PostMapping("/assign-questions")
    void assignQuestions(@Valid @RequestBody AssignQuestionsRequest request) {
        service.assignQuestions(request);
    }

    @GetMapping("/{surveyId}/questions")
    Set<QuestionAssignmentData> getQuestions(@PathVariable Long surveyId) {
        return service.getQuestions(surveyId);
    }

    @PostMapping("/assign-responses")
    void assignResponses(@Valid @RequestBody AssignResponsesRequest request) {
        System.out.println(request);
        service.assignResponses(request);
    }

}
