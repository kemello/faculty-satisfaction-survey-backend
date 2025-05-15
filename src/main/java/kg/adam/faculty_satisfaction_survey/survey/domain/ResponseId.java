package kg.adam.faculty_satisfaction_survey.survey.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import org.hibernate.Hibernate;

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

    ResponseId(String anonymousId, Long questionId) {
        this.anonymousId = anonymousId;
        this.questionId = questionId;
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

    // Equals and hashCode

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        ResponseId entity = (ResponseId) o;
        return Objects.equals(this.anonymousId, entity.anonymousId) &&
                Objects.equals(this.questionId, entity.questionId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(anonymousId, questionId);
    }

}