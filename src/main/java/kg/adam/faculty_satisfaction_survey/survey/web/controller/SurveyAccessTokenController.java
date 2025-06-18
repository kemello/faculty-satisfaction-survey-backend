package kg.adam.faculty_satisfaction_survey.survey.web.controller;

import jakarta.validation.Valid;
import kg.adam.faculty_satisfaction_survey.survey.domain.SurveyAccessTokenService;
import kg.adam.faculty_satisfaction_survey.survey.domain.model.GenerateTokenRequest;
import kg.adam.faculty_satisfaction_survey.survey.domain.model.SubmitSurveyWithTokenRequest;
import kg.adam.faculty_satisfaction_survey.survey.domain.model.TokenData;
import kg.adam.faculty_satisfaction_survey.survey.domain.model.ValidateTokenRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/survey-tokens")
public class SurveyAccessTokenController {
    private final SurveyAccessTokenService tokenService;

    public SurveyAccessTokenController(SurveyAccessTokenService tokenService) {
        this.tokenService = tokenService;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public TokenData generateToken(@Valid @RequestBody GenerateTokenRequest request) {
        return tokenService.generateToken(request);
    }

    @PostMapping("/validate")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public ResponseEntity<Void> validateToken(@Valid @RequestBody ValidateTokenRequest request) {
        tokenService.validateToken(request);
        return ResponseEntity.noContent().build();
    }
    
    @PostMapping("/submit")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public ResponseEntity<Void> submitSurveyWithToken(@Valid @RequestBody SubmitSurveyWithTokenRequest request) {
        tokenService.submitSurveyWithToken(request);
        return ResponseEntity.noContent().build();
    }
}
