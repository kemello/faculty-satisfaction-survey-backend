package kg.adam.faculty_satisfaction_survey.student.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import jakarta.persistence.Table;

import java.time.LocalDateTime;

@Embeddable
class StudentCourseEnrollment {

    @Column(name = "course_id", nullable = false)
    private Long courseId;

    @Column(name = "enrolled_at", updatable  = false)
    private LocalDateTime enrolledAt;

    StudentCourseEnrollment(Long courseId)
    {
        this.courseId = courseId;
        this.enrolledAt = LocalDateTime.now();
    }

    protected StudentCourseEnrollment() {}

    public Long getCourseId()
    {
        return courseId;
    }

}
