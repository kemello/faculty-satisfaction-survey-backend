package kg.adam.faculty_satisfaction_survey.student.domain.model;


import kg.adam.faculty_satisfaction_survey.common.enums.AcademicYear;
import kg.adam.faculty_satisfaction_survey.common.enums.Faculty;
import kg.adam.faculty_satisfaction_survey.common.enums.Gender;
import kg.adam.faculty_satisfaction_survey.common.enums.StudyMode;

public record StudentInfo(
        Gender gender,
        AcademicYear academicYear,
        Faculty faculty,
        StudyMode studyMode
) {
}
