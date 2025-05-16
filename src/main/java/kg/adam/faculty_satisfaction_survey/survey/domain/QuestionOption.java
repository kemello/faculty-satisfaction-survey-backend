package kg.adam.faculty_satisfaction_survey.survey.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;

@Embeddable
class QuestionOption {

    @Column(name = "text", nullable = false)
    private String text;

    @Column(name = "value", nullable = false)
    private String value;

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
