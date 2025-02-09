package kg.adam.faculty_satisfaction_survey.survey.domain;

import jakarta.persistence.*;
import kg.adam.faculty_satisfaction_survey.common.enums.QuestionType;
import org.springframework.data.annotation.CreatedDate;

import java.time.LocalDateTime;

@Entity
@Table(name = "survey_response")
class ResponseEntity {
    @EmbeddedId
    private ResponseId id;

    @Column(name = "response", nullable = false, length = Integer.MAX_VALUE)
    private String response;

    @Column(name = "response_at")
    @CreatedDate
    private LocalDateTime responseAt;

    @Enumerated(EnumType.STRING)
    @Column(name = "response_type",
            columnDefinition = "question_type_enum not null",
            nullable = false)
    private QuestionType responseType;


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

    public QuestionType getResponseType() {
        return responseType;
    }

    public void setResponseType(QuestionType responseType) {
        this.responseType = responseType;
    }
}