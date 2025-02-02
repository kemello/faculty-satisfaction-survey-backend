package kg.adam.faculty_satisfaction_survey.response;

import jakarta.persistence.*;
import kg.adam.faculty_satisfaction_survey.survey.domain.QuestionEntity;
import kg.adam.faculty_satisfaction_survey.survey.domain.SurveyEntity;
import kg.adam.faculty_satisfaction_survey.common.QuestionType;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import java.time.Instant;

@Entity
@Table(name = "survey_response")
public class ResponseEntity {
    @EmbeddedId
    private ResponseId id;

    @MapsId("surveyId")
    @ManyToOne(fetch = FetchType.LAZY, optional = false,
    cascade = {CascadeType.ALL})
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JoinColumn(name = "survey_id", nullable = false)
    private SurveyEntity survey;

    @MapsId("questionId")
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JoinColumn(name = "question_id", nullable = false)
    private QuestionEntity question;

    @Column(name = "response", nullable = false, length = Integer.MAX_VALUE)
    private String response;

    @ColumnDefault("CURRENT_TIMESTAMP")
    @Column(name = "response_at")
    private Instant responseAt;

    @Enumerated(EnumType.STRING)
    @Column(name = "response_type",
            columnDefinition = "question_type_enum not null",
            nullable = false)
    private QuestionType responseType;

    public ResponseId getId() {
        return id;
    }

    public void setId(ResponseId id) {
        this.id = id;
    }

    public SurveyEntity getSurvey() {
        return survey;
    }

    public void setSurvey(SurveyEntity survey) {
        this.survey = survey;
    }

    public QuestionEntity getQuestion() {
        return question;
    }

    public void setQuestion(QuestionEntity question) {
        this.question = question;
    }

    public String getResponse() {
        return response;
    }

    public void setResponse(String response) {
        this.response = response;
    }

    public Instant getResponseAt() {
        return responseAt;
    }

    public void setResponseAt(Instant responseAt) {
        this.responseAt = responseAt;
    }

    public QuestionType getResponseType() {
        return responseType;
    }

    public void setResponseType(QuestionType responseType) {
        this.responseType = responseType;
    }
}