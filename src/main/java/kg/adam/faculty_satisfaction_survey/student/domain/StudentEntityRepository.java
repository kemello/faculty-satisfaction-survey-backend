package kg.adam.faculty_satisfaction_survey.student.domain;

import org.springframework.data.jpa.repository.JpaRepository;

interface StudentEntityRepository extends JpaRepository<StudentEntity, Long> {
}