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

    @Column(name = "course_id", nullable = false)
    private Long courseId;

    @Column(name = "professor_id", nullable = false)
    private Long professorId;

    @Column(name = "hash", nullable = false)
    private String hash;

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
    SurveyAccessTokenEntity(String token, Long surveyId, Long courseId, Long professorId, String hash, LocalDateTime expiresAt) {
        this.token = token;
        this.surveyId = surveyId;
        this.courseId = courseId;
        this.professorId = professorId;
        this.hash = hash;
        this.expiresAt = expiresAt;
    }

    protected SurveyAccessTokenEntity() {
    }

    // Methods
    public void markAsUsed() {
        this.used = true;
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

    public Long getCourseId() {
        return courseId;
    }

    public Long getProfessorId() {
        return professorId;
    }

    public String getHash() {
        return hash;
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