package kg.adam.faculty_satisfaction_survey.survey.domain;

import jakarta.persistence.*;

import java.util.UUID;

@Entity
@Table(name = "survey_question_assignment")
class SurveyQuestionAssignment {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;  // Unique across entire system

    // Reference to the Survey (many-to-one side)
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "survey_id", nullable = false)
    private SurveyEntity survey;

    // Reference to the Question
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "question_id", nullable = false)
    private QuestionEntity question;

    // Defines the order of questions in the survey
    @Column(name = "order_number")
    private Integer orderNumber;

    protected SurveyQuestionAssignment() {}


    // Getters and Setters

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public Integer getOrderNumber() {
        return orderNumber;
    }

    public void setOrderNumber(Integer orderNumber) {
        this.orderNumber = orderNumber;
    }

    public QuestionEntity getQuestion() {
        return question;
    }

    public void setQuestion(QuestionEntity question) {
        this.question = question;
    }

    public SurveyEntity getSurvey() {
        return survey;
    }

    public void setSurvey(SurveyEntity survey) {
        this.survey = survey;
    }
}