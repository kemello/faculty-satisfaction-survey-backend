package kg.adam.faculty_satisfaction_survey.common;

/**
 * Enum representing faculty options for a student.
 */
public enum Faculty {
    IST("ИСТ"),
    ECONOMICS("Экономика"),
    MANAGEMENT("Менеджмент"),
    TOURISM("Туризм"),
    GENERAL_MEDICINE("ЛД"),
    BUSINESS_ADMINISTRATION("УБ");

    private final String name;

    Faculty(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}