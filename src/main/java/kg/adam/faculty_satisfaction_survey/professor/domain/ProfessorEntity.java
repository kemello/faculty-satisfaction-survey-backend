package kg.adam.faculty_satisfaction_survey.professor.domain;

import jakarta.persistence.*;
import kg.adam.faculty_satisfaction_survey.common.BaseEntity;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "professor")
class ProfessorEntity extends BaseEntity<Long> {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "professor_id_seq")
    private Long id;

    @Column(name = "name", nullable = false, length = 100)
    private String name;

    @Column(name = "avatar_url")
    private String avatarUrl;

    @OneToMany(mappedBy = "professor", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<CourseEntity> courses = new HashSet<>();

    @Column(name = "created_at", nullable = false, updatable = false)
    @CreatedDate
    private LocalDateTime createdAt = LocalDateTime.now();

    @Column(name = "updated_at")
    @LastModifiedDate
    private LocalDateTime updatedAt;

    public ProfessorEntity(Long id) {
        this.id = id;
    }

    protected ProfessorEntity() {
    }


    // Aggregate behaviour
    void addCourse(CourseEntity course) {
        course.setProfessor(this);
        this.courses.add(course);
    }

    void removeCourse(CourseEntity course) {
        courses.remove(course);
    }


    //getters and setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAvatarUrl() {
        return avatarUrl;
    }

    public void setAvatarUrl(String avatarUrl) {
        this.avatarUrl = avatarUrl;
    }

    public Set<CourseEntity> getCourses() {
        return courses;
    }

    public void setCourses(Set<CourseEntity> courses) {
        this.courses = courses;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(LocalDateTime updatedAt) {
        this.updatedAt = updatedAt;
    }
}