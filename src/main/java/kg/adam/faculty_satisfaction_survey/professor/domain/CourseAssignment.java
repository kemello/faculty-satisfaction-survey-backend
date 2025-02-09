package kg.adam.faculty_satisfaction_survey.professor.domain;

import jakarta.persistence.Embeddable;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Table;
import kg.adam.faculty_satisfaction_survey.common.enums.AcademicYear;
import kg.adam.faculty_satisfaction_survey.common.enums.Faculty;
import kg.adam.faculty_satisfaction_survey.common.enums.StudyMode;

@Embeddable
class CourseAssignment {


    @Enumerated(EnumType.STRING)
    private Faculty faculty;

    @Enumerated(EnumType.STRING)
    private AcademicYear academicYear;

    @Enumerated(EnumType.STRING)
    private StudyMode modeOfStudy;
}