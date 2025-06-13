package kg.adam.faculty_satisfaction_survey.student.domain;

import jakarta.persistence.*;
import kg.adam.faculty_satisfaction_survey.common.BaseEntity;
import kg.adam.faculty_satisfaction_survey.common.enums.AcademicYear;
import kg.adam.faculty_satisfaction_survey.common.enums.Faculty;
import kg.adam.faculty_satisfaction_survey.common.enums.Gender;
import kg.adam.faculty_satisfaction_survey.common.enums.StudyMode;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "student")
class StudentEntity extends BaseEntity<Long> {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "student_id_seq")
    private Long id;

    @Enumerated(EnumType.STRING)
    @Column(name = "academic_year", columnDefinition = "academic_year_enum not null", nullable = false)
    private AcademicYear academicYear;

    @Enumerated(EnumType.STRING)
    @Column(name = "faculty", columnDefinition = "faculty_enum not null", nullable = false)
    private Faculty faculty;

    @Enumerated(EnumType.STRING)
    @Column(name = "mode_of_study", columnDefinition = "study_mode_enum not null")
    private StudyMode modeOfStudy;

    @Enumerated(EnumType.STRING)
    @Column(name = "gender", columnDefinition = "gender_enum not null")
    private Gender gender;

    @Column(updatable = false)
    private LocalDateTime createdAt = LocalDateTime.now();

    @ElementCollection
    @CollectionTable(
            name = "student_course",
            joinColumns = @JoinColumn(name = "student_id")
    )
    private Set<StudentCourseEnrollment> enrollments = new HashSet<>();

        public StudentEntity(AcademicYear academicYear, Faculty faculty, StudyMode modeOfStudy, Gender gender) {
        this.academicYear = academicYear;
        this.faculty = faculty;
        this.modeOfStudy = modeOfStudy;
        this.gender = gender;
    }

    protected StudentEntity() {
    }

    //aggregate behavior
    public void enrollInCourse(Long courseId) {
        enrollments.add(new StudentCourseEnrollment(courseId));
    }

    public void unenrollFromCourse(Long courseId) {
        enrollments.removeIf(enrollment -> enrollment.getCourseId().equals(courseId));
    }


    //getters and setters


    public AcademicYear getAcademicYear() {
        return academicYear;
    }

    public void setAcademicYear(AcademicYear academicYear) {
        this.academicYear = academicYear;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public Set<StudentCourseEnrollment> getEnrollments() {
        return enrollments;
    }

    public void setEnrollments(Set<StudentCourseEnrollment> enrollments) {
        this.enrollments = enrollments;
    }

    public Faculty getFaculty() {
        return faculty;
    }

    public void setFaculty(Faculty faculty) {
        this.faculty = faculty;
    }

    public Gender getGender() {
        return gender;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public StudyMode getModeOfStudy() {
        return modeOfStudy;
    }

    public void setModeOfStudy(StudyMode modeOfStudy) {
        this.modeOfStudy = modeOfStudy;
    }

}