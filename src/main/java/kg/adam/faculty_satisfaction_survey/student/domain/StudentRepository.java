package kg.adam.faculty_satisfaction_survey.student.domain;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
interface StudentRepository extends JpaRepository <StudentEntity, Long> {
}
