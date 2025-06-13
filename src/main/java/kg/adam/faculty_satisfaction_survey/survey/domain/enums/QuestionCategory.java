package kg.adam.faculty_satisfaction_survey.survey.domain.enums;

public enum QuestionCategory {
    FOR_STUDENT("FOR_STUDENT"),
    FOR_PROFESSOR("FOR_PROFESSOR");

    private final String value;

    QuestionCategory(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
