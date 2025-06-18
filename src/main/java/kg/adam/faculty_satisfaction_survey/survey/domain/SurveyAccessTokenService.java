package kg.adam.faculty_satisfaction_survey.survey.domain;

import jakarta.transaction.Transactional;
import kg.adam.faculty_satisfaction_survey.common.enums.AcademicYear;
import kg.adam.faculty_satisfaction_survey.common.enums.Faculty;
import kg.adam.faculty_satisfaction_survey.common.enums.StudyMode;
import kg.adam.faculty_satisfaction_survey.survey.domain.exception.InvalidTokenException;
import kg.adam.faculty_satisfaction_survey.survey.domain.exception.TokenAlreadyUsedException;
import kg.adam.faculty_satisfaction_survey.survey.domain.exception.TokenExpiredException;
import kg.adam.faculty_satisfaction_survey.survey.domain.model.*;
import org.springframework.stereotype.Service;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.time.LocalDateTime;
import java.util.HexFormat;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@Transactional
public class SurveyAccessTokenService {
    private final SurveyAccessTokenRepository tokenRepository;
    private final SurveyRepository surveyRepository;
    private final SurveyService surveyService;

    public SurveyAccessTokenService(SurveyAccessTokenRepository tokenRepository, 
                                   SurveyRepository surveyRepository,
                                   SurveyService surveyService) {
        this.tokenRepository = tokenRepository;
        this.surveyRepository = surveyRepository;
        this.surveyService = surveyService;
    }

    public TokenData generateToken(GenerateTokenRequest request) {
        // Verify survey exists
        surveyRepository.findById(request.surveyId())
                .orElseThrow(() -> new IllegalArgumentException("Survey not found"));

        // Generate token
        String token = generateUniqueToken();
        
        // Set expiration (default 30 days if not specified)
        int validityDays = request.validityInDays() != null ? request.validityInDays() : 30;
        LocalDateTime expiresAt = LocalDateTime.now().plusDays(validityDays);

        // Create and save token entity
        SurveyAccessTokenEntity tokenEntity = new SurveyAccessTokenEntity(
                token,
                request.surveyId(),
                expiresAt
        );
        
        tokenRepository.save(tokenEntity);
        
        return new TokenData(token, request.surveyId(), expiresAt);
    }

    public void validateToken(ValidateTokenRequest request) {
        SurveyAccessTokenEntity tokenEntity = tokenRepository.findByToken(request.token())
                .orElseThrow(() -> new InvalidTokenException("Invalid token"));

        // Check if token is for the requested survey
        if (!tokenEntity.getSurveyId().equals(request.surveyId())) {
            throw new InvalidTokenException("Token is not valid for this survey");
        }

        // Check if token is expired
        if (tokenEntity.isExpired()) {
            throw new TokenExpiredException("Token has expired");
        }

        // Check if token is already used
        if (tokenEntity.isUsed()) {
            throw new TokenAlreadyUsedException("Token has already been used");
        }
    }
    
    @Transactional
    public void submitSurveyWithToken(SubmitSurveyWithTokenRequest request) {
        // First validate the token
        SurveyAccessTokenEntity tokenEntity = tokenRepository.findByToken(request.token())
                .orElseThrow(() -> new InvalidTokenException("Invalid token"));

        // Check if token is for the requested survey
        if (!tokenEntity.getSurveyId().equals(request.surveyId())) {
            throw new InvalidTokenException("Token is not valid for this survey");
        }

        // Check if token is expired
        if (tokenEntity.isExpired()) {
            throw new TokenExpiredException("Token has expired");
        }

        // Check if token is already used
        if (tokenEntity.isUsed()) {
            throw new TokenAlreadyUsedException("Token has already been used");
        }
        
        // Submit the responses
        surveyService.assignResponses(new AssignResponsesRequest(request.surveyId(), request.responses()));
        
        // Generate submission hash
        String submissionHash = generateSubmissionHash(
                request.token(),
                request.faculty(),
                request.academicYear(),
                request.studyMode(),
                request.responses()
        );
        
        // Mark token as used with the submission hash
        tokenEntity.markAsUsed(submissionHash);
        tokenRepository.save(tokenEntity);
    }

    private String generateUniqueToken() {
        // Generate a random 8-character alphanumeric token
        return UUID.randomUUID().toString().replaceAll("-", "").substring(0, 8).toUpperCase();
    }

    private String generateSubmissionHash(String token, Faculty faculty, AcademicYear academicYear, 
                                         StudyMode studyMode, List<ResponseAssignmentData> responses) {
        try {
            // Extract professor IDs from responses
            String professorIds = responses.stream()
                    .map(ResponseAssignmentData::professorId)
                    .distinct()
                    .sorted()
                    .map(String::valueOf)
                    .collect(Collectors.joining(","));
            
            // Create input string for hash
            String input = token + ":" + 
                           faculty.name() + ":" + 
                           academicYear.name() + ":" + 
                           studyMode.name() + ":" + 
                           professorIds;
            
            // Generate hash
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            byte[] hash = digest.digest(input.getBytes(StandardCharsets.UTF_8));
            return HexFormat.of().formatHex(hash);
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException("Failed to generate hash", e);
        }
    }
}