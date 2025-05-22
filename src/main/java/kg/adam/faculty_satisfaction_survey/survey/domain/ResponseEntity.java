package kg.adam.faculty_satisfaction_survey.survey.domain;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import org.springframework.data.annotation.CreatedDate;

import java.time.LocalDateTime;

@Entity
@Table(name = "survey_response")
class ResponseEntity {
    @EmbeddedId
    private ResponseId id;

    @NotBlank(message = "Response content cannot be empty")
    @Column(name = "content", nullable = false, length = Integer.MAX_VALUE)
    private String content;

    @MapsId("questionId")
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "question_id")
    private QuestionEntity question;

    @MapsId("surveyId") // Maps to ResponseId.surveyId
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "survey_id")
    private SurveyEntity survey;


    @Column(name = "response_at")
    @CreatedDate
    private LocalDateTime responseAt = LocalDateTime.now();


    ResponseEntity(String content, ResponseId id, SurveyEntity survey, QuestionEntity question) {
        this.content = content;
        this.id = id;
        this.survey = survey;
        this.question = question;
    }

    protected ResponseEntity() {
    }

    //getters and setters
    public ResponseId getId() {
        return id;
    }

    public void setId(ResponseId id) {
        this.id = id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String response) {
        this.content = response;
    }

    public LocalDateTime getResponseAt() {
        return responseAt;
    }

    public void setResponseAt(LocalDateTime responseAt) {
        this.responseAt = responseAt;
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

}