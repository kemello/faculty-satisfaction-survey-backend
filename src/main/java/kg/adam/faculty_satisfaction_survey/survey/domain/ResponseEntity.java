package kg.adam.faculty_satisfaction_survey.survey.domain;

import jakarta.persistence.*;
import kg.adam.faculty_satisfaction_survey.common.enums.QuestionType;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import org.springframework.data.annotation.CreatedDate;

import java.time.LocalDateTime;

@Entity
@Table(name = "survey_response")
class ResponseEntity {
    @EmbeddedId
    private ResponseId id;

    @Column(name = "response", nullable = false, length = Integer.MAX_VALUE)
    private String response;

//    @ManyToOne(fetch = FetchType.LAZY)
//    @JoinColumn(name = "question_id", nullable = false)
//    @OnDelete(action = OnDeleteAction.CASCADE)
//    private QuestionEntity question;

    @Column(name = "response_at")
    @CreatedDate
    private LocalDateTime responseAt;




    //getters and setters
    public ResponseId getId() {
        return id;
    }

    public void setId(ResponseId id) {
        this.id = id;
    }

    public String getResponse() {
        return response;
    }

    public void setResponse(String response) {
        this.response = response;
    }

    public LocalDateTime getResponseAt() {
        return responseAt;
    }

    public void setResponseAt(LocalDateTime responseAt) {
        this.responseAt = responseAt;
    }

//    public QuestionEntity getQuestion() {
//        return question;
//    }
//
//    public void setQuestion(QuestionEntity question) {
//        this.question = question;
//    }
}