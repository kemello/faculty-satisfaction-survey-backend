package kg.adam.faculty_satisfaction_survey.survey.domain;

import jakarta.persistence.*;

import java.util.UUID;

@Embeddable
class SurveyQuestionAssignment {
    // Reference to the Survey (many-to-one side)

    private Long questionId;

    @Column(name = "order_number")
    private Integer orderNumber;

    protected SurveyQuestionAssignment() {}

}