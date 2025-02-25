package kg.adam.faculty_satisfaction_survey.survey.domain;

import jakarta.persistence.*;
import kg.adam.faculty_satisfaction_survey.common.BaseEntity;
import kg.adam.faculty_satisfaction_survey.common.enums.QuestionCategory;
import kg.adam.faculty_satisfaction_survey.common.enums.QuestionType;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import org.springframework.data.annotation.CreatedDate;

import java.time.LocalDateTime;

@Entity
@Table(name = "survey_question")
class QuestionEntity extends BaseEntity<Long> {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "question_id_seq")
    private Long id;

    @Column(name = "text", nullable = false)
    private String text;

    @Column(name = "created_at")
    @CreatedDate
    private LocalDateTime createdAt;

    @Enumerated(EnumType.STRING)
    private QuestionType questionType;

    @Enumerated(EnumType.STRING)
    @Column(name = "question_category", nullable = false)
    private QuestionCategory category;

    @Column(name = "question_order", nullable = false)
    private Integer order;

    @ManyToOne
    @JoinColumn(name = "survey_id", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    private SurveyEntity survey;

    public QuestionEntity(String text, QuestionType questionType, QuestionCategory category, SurveyEntity survey) {
        this.text = text;
        this.questionType = questionType;
        this.category = category;
        this.survey = survey;
    }

    protected QuestionEntity() {
    }


    // getters and setters


    public QuestionCategory getCategory() {
        return category;
    }

    public void setCategory(QuestionCategory category) {
        this.category = category;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getOrder() {
        return order;
    }

    public void setOrder(Integer order) {
        this.order = order;
    }

    public QuestionType getQuestionType() {
        return questionType;
    }

    public void setQuestionType(QuestionType questionType) {
        this.questionType = questionType;
    }

    public SurveyEntity getSurvey() {
        return survey;
    }

    public void setSurvey(SurveyEntity survey) {
        this.survey = survey;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
}