package kg.adam.faculty_satisfaction_survey.common.enums;

/**
 * Enum representing academic year options for a student.
 */
public enum AcademicYear {
    FIRST_YEAR("1"),
    SECOND_YEAR("2"),
    THIRD_YEAR("3"),
    FOURTH_YEAR("4"),
    FIFTH_YEAR("5");

    private final String dbValue;

    AcademicYear(String dbValue) {
        this.dbValue = dbValue;
    }

    public String getDbValue() {
        return dbValue;
    }

}