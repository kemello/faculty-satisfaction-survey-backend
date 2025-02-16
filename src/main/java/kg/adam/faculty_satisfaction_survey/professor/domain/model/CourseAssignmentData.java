package kg.adam.faculty_satisfaction_survey.professor.domain.model;

import jakarta.persistence.Column;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import kg.adam.faculty_satisfaction_survey.common.enums.AcademicYear;
import kg.adam.faculty_satisfaction_survey.common.enums.Faculty;
import kg.adam.faculty_satisfaction_survey.common.enums.StudyMode;

public record CourseAssignmentData(

        @Enumerated(EnumType.STRING)
        Faculty faculty,

        @Enumerated(EnumType.STRING)
        AcademicYear academicYear,

        @Enumerated(EnumType.STRING)
        StudyMode studyMode
) {
}
