package kg.adam.faculty_satisfaction_survey.professor.domain;

import kg.adam.faculty_satisfaction_survey.professor.domain.model.CourseAssignmentData;
import kg.adam.faculty_satisfaction_survey.professor.domain.model.CourseData;

import java.util.Set;
import java.util.stream.Collectors;

class CourseMapper {
    static CourseEntity toEntity(String courseName, ProfessorEntity professor ,
                                 Set<CourseAssignmentData> assignmentsData) {
        Set<CourseAssignment> assignments = assignmentsData.stream()
                .map(req -> new CourseAssignment(req.faculty(), req.academicYear(), req.studyMode()))
                .collect(Collectors.toSet());
        return new CourseEntity(courseName, professor, assignments);
    }

    static CourseData toData(CourseEntity course) {
        return new CourseData(
                course.getId(),
                course.getName(),
                course.getProfessor().getName(),
                course.getAssignments().stream().map(CourseMapper::toCourseAssignmentData).collect(Collectors.toSet()),
                course.getCreatedAt()

        );
    }

    static CourseAssignmentData toCourseAssignmentData(CourseAssignment assignment) {
        return new CourseAssignmentData(
                assignment.getFaculty(),
                assignment.getAcademicYear(),
                assignment.getStudyMode()
        );
    }

    static Set<CourseData> toDataList(Set<CourseEntity> courses) {
        return courses.stream().map(CourseMapper::toData).collect(Collectors.toSet());
    }
}
