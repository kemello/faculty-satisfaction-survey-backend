package kg.adam.faculty_satisfaction_survey.professor.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import kg.adam.faculty_satisfaction_survey.common.enums.AcademicYear;
import kg.adam.faculty_satisfaction_survey.common.enums.Faculty;
import kg.adam.faculty_satisfaction_survey.common.enums.StudyMode;

@Embeddable
class CourseAssignment {

    @Enumerated(EnumType.STRING)
    private Faculty faculty;

    @Enumerated(EnumType.STRING)
    @Column(name = "academic_year", columnDefinition = "academic_year_enum not null")
    private AcademicYear academicYear;

    @Enumerated(EnumType.STRING)
    private StudyMode studyMode;

    public CourseAssignment(Faculty faculty, AcademicYear academicYear, StudyMode studyMode) {
        this.faculty = faculty;
        this.academicYear = academicYear;
        this.studyMode = studyMode;
    }
    protected CourseAssignment() {
    }

    //getters

    public AcademicYear getAcademicYear() {
        return academicYear;
    }

    public Faculty getFaculty() {
        return faculty;
    }

    public StudyMode getStudyMode() {
        return studyMode;
    }
}