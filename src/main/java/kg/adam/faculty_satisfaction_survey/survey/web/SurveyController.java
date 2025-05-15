package kg.adam.faculty_satisfaction_survey.survey.web;

import kg.adam.faculty_satisfaction_survey.survey.domain.SurveyService;
import kg.adam.faculty_satisfaction_survey.survey.domain.model.AssignQuestionsRequest;
import kg.adam.faculty_satisfaction_survey.survey.domain.model.CreateSurveyRequest;
import kg.adam.faculty_satisfaction_survey.survey.domain.model.SurveyData;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/surveys")
class SurveyController {
    private final SurveyService service;

    SurveyController(SurveyService service) {
        this.service = service;
    }

    @PostMapping
    SurveyData createSurvey(@RequestBody CreateSurveyRequest request) {
        return service.createSurvey(request);
    }

    @PostMapping("/assign-questions")
    void assignQuestions(@RequestBody AssignQuestionsRequest request) {
        service.assignQuestions(request);
    }

}
