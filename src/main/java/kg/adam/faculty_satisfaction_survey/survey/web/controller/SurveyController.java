package kg.adam.faculty_satisfaction_survey.survey.web.controller;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;
import kg.adam.faculty_satisfaction_survey.survey.domain.SurveyService;
import kg.adam.faculty_satisfaction_survey.survey.domain.model.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("/api/surveys")
class SurveyController {
    private final SurveyService service;

    SurveyController(SurveyService service) {
        this.service = service;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    ResponseEntity<SurveyData> createSurvey(@Valid @RequestBody CreateSurveyRequest request) {
        return new ResponseEntity<>(service.createSurvey(request), HttpStatus.CREATED);
    }

    @PostMapping("/assign-questions")
    ResponseEntity<Void> assignQuestions(@Valid @RequestBody AssignQuestionsRequest request) {
        service.assignQuestions(request);
        return ResponseEntity.accepted().build();
    }

    @GetMapping("/{surveyId}/questions")
    ResponseEntity<Set<QuestionAssignmentData>> getQuestions(
            @PathVariable @Min(1) Long surveyId) {
        return ResponseEntity.ok(service.getQuestions(surveyId));
    }

    @GetMapping("/{surveyId}/responses")
    List<ResponseAssignmentData> getResponses(@PathVariable Long surveyId) {
        return service.getResponses(surveyId);
    }

    @PostMapping("/assign-responses")
    ResponseEntity<Void> assignResponses(@Valid @RequestBody AssignResponsesRequest request) {
        service.assignResponses(request);
        return ResponseEntity.accepted().build();
    }


    @PostMapping("/{surveyId}/activate")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    void activateSurvey(@PathVariable Long surveyId) {
        service.activateSurvey(surveyId);
    }

    @GetMapping("/active")
    ResponseEntity<List<SurveyData>> getActiveSurveys() {
        return ResponseEntity.ok(service.getAllActiveSurveys());
    }
}
