package kg.adam.faculty_satisfaction_survey.survey.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;

import java.io.Serial;
import java.io.Serializable;
import java.util.Objects;

@Embeddable
class ResponseId implements Serializable {
    @Serial
    private static final long serialVersionUID = -3_614_409_108_256_441_769L;

    @Column(name = "anonymous_id", nullable = false)
    private String anonymousId;

    @Column(name = "question_id", nullable = false)
    private Long questionId;

    @Column(name = "survey_id" ,  nullable = false)
    private Long surveyId;


    ResponseId(String anonymousId, Long questionId, Long surveyId) {
        this.anonymousId = anonymousId;
        this.questionId = questionId;
        this.surveyId = surveyId;
    }

    protected ResponseId () {
    }

    // Getters and setters


    public String getAnonymousId() {
        return anonymousId;
    }

    public void setAnonymousId(String anonymousId) {
        this.anonymousId = anonymousId;
    }

    public Long getQuestionId() {
        return questionId;
    }

    public void setQuestionId(Long questionId) {
        this.questionId = questionId;
    }

    public Long getSurveyId() {
        return surveyId;
    }

    public void setSurveyId(Long responseId) {
        this.surveyId = responseId;
    }

    // Equals and hashCode


    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        ResponseId that = (ResponseId) o;
        return Objects.equals(anonymousId, that.anonymousId) &&
                Objects.equals(questionId, that.questionId) && Objects.equals(surveyId, that.surveyId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(anonymousId, questionId, surveyId);
    }
}