package kg.adam.faculty_satisfaction_survey.survey.domain;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
interface SurveyEntityRepository extends JpaRepository<SurveyEntity, Long> {
}