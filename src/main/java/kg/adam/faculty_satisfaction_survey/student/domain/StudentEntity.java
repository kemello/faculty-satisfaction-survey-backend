package kg.adam.faculty_satisfaction_survey.student.domain;

import jakarta.persistence.*;
import kg.adam.faculty_satisfaction_survey.academic.domain.CourseEntity;
import kg.adam.faculty_satisfaction_survey.common.AcademicYear;
import kg.adam.faculty_satisfaction_survey.common.Faculty;
import kg.adam.faculty_satisfaction_survey.common.Gender;
import kg.adam.faculty_satisfaction_survey.common.StudyMode;
import org.hibernate.annotations.ColumnDefault;

import java.time.Instant;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "student")
public class StudentEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "student_id_gen")
    @SequenceGenerator(name = "student_id_gen", sequenceName = "student_id_seq", allocationSize = 1)
    @Column(name = "id", nullable = false)
    private Long id;

    @Enumerated(EnumType.STRING)
    @Column(name = "academic_year", columnDefinition = "academic_year_enum not null", nullable = false)
    private AcademicYear academicYear;

    @Enumerated(EnumType.STRING)
    @Column(name = "faculty", columnDefinition = "faculty_enum not null", nullable = false)
    private Faculty faculty;

    @ColumnDefault("CURRENT_TIMESTAMP")
    @Column(name = "created_at")
    private Instant createdAt;

    @ColumnDefault("CURRENT_TIMESTAMP")
    @Column(name = "updated_at")
    private Instant updatedAt;

    @Enumerated(EnumType.STRING)
    @Column(name = "mode_of_study", columnDefinition = "study_mode_enum not null")
    private StudyMode modeOfStudy;

    @Enumerated(EnumType.STRING)
    @Column(name = "gender", columnDefinition = "gender_enum not null")
    private Gender gender;

    @ManyToMany(mappedBy = "students")
    private Set<CourseEntity> courses = new HashSet<>();

    public AcademicYear getAcademicYear() {
        return academicYear;
    }

    public void setAcademicYear(AcademicYear academicYear) {
        this.academicYear = academicYear;
    }

    public Set<CourseEntity> getCourses() {
        return courses;
    }

    public void setCourses(Set<CourseEntity> courses) {
        this.courses = courses;
    }

    public Instant getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Instant createdAt) {
        this.createdAt = createdAt;
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

    public Instant getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(Instant updatedAt) {
        this.updatedAt = updatedAt;
    }


}