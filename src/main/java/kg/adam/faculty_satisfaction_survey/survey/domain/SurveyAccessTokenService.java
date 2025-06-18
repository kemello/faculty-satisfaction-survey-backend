package kg.adam.faculty_satisfaction_survey.survey.domain;

import jakarta.transaction.Transactional;
import kg.adam.faculty_satisfaction_survey.survey.domain.exception.InvalidTokenException;
import kg.adam.faculty_satisfaction_survey.survey.domain.exception.TokenAlreadyUsedException;
import kg.adam.faculty_satisfaction_survey.survey.domain.exception.TokenExpiredException;
import kg.adam.faculty_satisfaction_survey.survey.domain.model.GenerateTokenRequest;
import kg.adam.faculty_satisfaction_survey.survey.domain.model.TokenData;
import kg.adam.faculty_satisfaction_survey.survey.domain.model.ValidateTokenRequest;
import org.springframework.stereotype.Service;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.time.LocalDateTime;
import java.util.Base64;
import java.util.HexFormat;
import java.util.UUID;

@Service
@Transactional
public class SurveyAccessTokenService {
    private final SurveyAccessTokenRepository tokenRepository;
    private final SurveyRepository surveyRepository;

    public SurveyAccessTokenService(SurveyAccessTokenRepository tokenRepository, SurveyRepository surveyRepository) {
        this.tokenRepository = tokenRepository;
        this.surveyRepository = surveyRepository;
    }

    public TokenData generateToken(GenerateTokenRequest request) {
        // Verify survey exists
        surveyRepository.findById(request.surveyId())
                .orElseThrow(() -> new IllegalArgumentException("Survey not found"));

        // Generate unique hash based on course and professor
        String hash = generateHash(request.courseId(), request.professorId());
        
        // Check if a token already exists for this combination
        if (tokenRepository.existsByHashAndSurveyId(hash, request.surveyId())) {
            throw new IllegalStateException("A token already exists for this course-professor-survey combination");
        }

        // Generate token
        String token = generateUniqueToken();
        
        // Set expiration (default 30 days if not specified)
        int validityDays = request.validityInDays() != null ? request.validityInDays() : 30;
        LocalDateTime expiresAt = LocalDateTime.now().plusDays(validityDays);

        // Create and save token entity
        SurveyAccessTokenEntity tokenEntity = new SurveyAccessTokenEntity(
                token,
                request.surveyId(),
                request.courseId(),
                request.professorId(),
                hash,
                expiresAt
        );
        
        tokenRepository.save(tokenEntity);
        
        return new TokenData(token, request.surveyId(), request.courseId(), request.professorId(), expiresAt);
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

        // Mark token as used
        tokenEntity.markAsUsed();
        tokenRepository.save(tokenEntity);
    }

    private String generateUniqueToken() {
        // Generate a random 8-character alphanumeric token
        return UUID.randomUUID().toString().replaceAll("-", "").substring(0, 8).toUpperCase();
    }

    private String generateHash(Long courseId, Long professorId) {
        try {
            String input = courseId + ":" + professorId;
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            byte[] hash = digest.digest(input.getBytes(StandardCharsets.UTF_8));
            return HexFormat.of().formatHex(hash);
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException("Failed to generate hash", e);
        }
    }
}