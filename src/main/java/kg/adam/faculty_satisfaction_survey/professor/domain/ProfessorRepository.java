package kg.adam.faculty_satisfaction_survey.professor.domain;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
interface ProfessorRepository extends JpaRepository<ProfessorEntity, Long> {
    Optional<ProfessorEntity> findByName(String name);
}