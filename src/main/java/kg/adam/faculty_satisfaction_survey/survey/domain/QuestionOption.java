package kg.adam.faculty_satisfaction_survey.survey.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;

@Embeddable
class QuestionOption {

    @NotBlank(message = "Option text cannot be empty")
    @Column(name = "text", nullable = false)
    private String text;

    @NotBlank(message = "Option value cannot be empty")
    @Column(name = "value", nullable = false)
    private String value;

    @Positive(message = "Option order must be positive number")
    @Column(name = "option_order", nullable = false)
    private Integer order;

    public QuestionOption(Integer order, String text, String value) {
        this.order = order;
        this.text = text;
        this.value = value;
    }

    protected QuestionOption() {
    }

    public Integer getOrder() {
        return order;
    }

    public void setOrder(Integer order) {
        this.order = order;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
