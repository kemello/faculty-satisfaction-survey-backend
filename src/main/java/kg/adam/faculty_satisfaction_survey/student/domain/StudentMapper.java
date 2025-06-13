package kg.adam.faculty_satisfaction_survey.student.domain;

import kg.adam.faculty_satisfaction_survey.student.domain.model.StudentData;
import kg.adam.faculty_satisfaction_survey.student.domain.model.StudentInfo;

class StudentMapper {
    static StudentEntity toEntity(StudentInfo info) {

        return new StudentEntity(
                info.academicYear(),
                info.faculty(),
                info.studyMode(),
                info.gender()
        );
    }

    public static StudentData toData(StudentEntity savedStudent) {
        return new StudentData(
                savedStudent.getId(),
                savedStudent.getAcademicYear(),
                savedStudent.getFaculty(),
                savedStudent.getModeOfStudy(),
                savedStudent.getGender(),
                savedStudent.getCreatedAt()
        );
    }
}
