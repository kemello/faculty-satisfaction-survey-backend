package kg.adam.faculty_satisfaction_survey.survey.domain;

import jakarta.persistence.*;
import kg.adam.faculty_satisfaction_survey.common.BaseEntity;
import org.springframework.data.annotation.CreatedDate;

import java.time.LocalDateTime;

@Entity
@Table(name = "survey_access_token")
class SurveyAccessTokenEntity extends BaseEntity<Long> {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "survey_token_id_seq")
    private Long id;

    @Column(name = "token", nullable = false, unique = true)
    private String token;

    @Column(name = "survey_id", nullable = false)
    private Long surveyId;

    @Column(name = "submission_hash", nullable = true)
    private String submissionHash;

    @Column(name = "is_used", nullable = false)
    private boolean used = false;

    @Column(name = "created_at", nullable = false)
    @CreatedDate
    private LocalDateTime createdAt = LocalDateTime.now();

    @Column(name = "expires_at", nullable = false)
    private LocalDateTime expiresAt;

    @Column(name = "used_at")
    private LocalDateTime usedAt;

    // Constructor
    SurveyAccessTokenEntity(String token, Long surveyId, LocalDateTime expiresAt) {
        this.token = token;
        this.surveyId = surveyId;
        this.expiresAt = expiresAt;
    }

    protected SurveyAccessTokenEntity() {
    }

    // Methods
    public void markAsUsed(String submissionHash) {
        this.used = true;
        this.submissionHash = submissionHash;
        this.usedAt = LocalDateTime.now();
    }

    public boolean isExpired() {
        return LocalDateTime.now().isAfter(expiresAt);
    }

    // Getters and setters
    @Override
    public Long getId() {
        return id;
    }

    public String getToken() {
        return token;
    }

    public Long getSurveyId() {
        return surveyId;
    }

    public String getSubmissionHash() {
        return submissionHash;
    }

    public boolean isUsed() {
        return used;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public LocalDateTime getExpiresAt() {
        return expiresAt;
    }

    public LocalDateTime getUsedAt() {
        return usedAt;
    }
}