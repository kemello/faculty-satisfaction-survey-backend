package kg.adam.faculty_satisfaction_survey.professor.domain;

import jakarta.persistence.*;
import kg.adam.faculty_satisfaction_survey.common.BaseEntity;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import org.springframework.data.annotation.CreatedDate;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "course")
class CourseEntity extends BaseEntity<Long> {
    @Id
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "name", nullable = false)
    private String name;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JoinColumn(name = "professor_id", nullable = false)
    private ProfessorEntity professor;

    @ElementCollection
    @CollectionTable(name = "course_assignment", joinColumns = @JoinColumn(name = "course_id"))
    private Set<CourseAssignment> assignments = new HashSet<>();

    @Column(name = "created_at", updatable = false)
    @CreatedDate
    private LocalDateTime createdAt;

    public CourseEntity(Long id) {
        this.id = id;
    }

    protected CourseEntity() {}

    // Getters and setters

    public Set<CourseAssignment> getAssignments() {
        return assignments;
    }

    public void setAssignments(Set<CourseAssignment> assignments) {
        this.assignments = assignments;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

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

    public ProfessorEntity getProfessor() {
        return professor;
    }

    public void setProfessor(ProfessorEntity professor) {
        this.professor = professor;
    }
}