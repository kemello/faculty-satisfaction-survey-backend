package kg.adam.faculty_satisfaction_survey.survey.domain;

import jakarta.persistence.LockModeType;
import kg.adam.faculty_satisfaction_survey.survey.domain.enums.SurveyStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
interface SurveyRepository extends JpaRepository<SurveyEntity, Long> {

    @Query("SELECT r FROM ResponseEntity r WHERE r.id.professorId = :professorId")
    List<ResponseEntity> findByProfessorId(@Param("professorId") Long professorId);


    // In SurveyRepository.java
    @Lock(LockModeType.PESSIMISTIC_WRITE)
    List<SurveyEntity> findAllByStatus(SurveyStatus status);
}