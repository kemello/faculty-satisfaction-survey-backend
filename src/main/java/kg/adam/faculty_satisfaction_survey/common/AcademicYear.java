package kg.adam.faculty_satisfaction_survey.common;

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

    public static AcademicYear fromDbValue(String value) {
        for (AcademicYear year : values()) {
            if (year.dbValue.equals(value)) {
                return year;
            }
        }
        throw new IllegalArgumentException("Unknown database value: " + value);
    }
}