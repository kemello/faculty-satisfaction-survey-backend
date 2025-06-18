package kg.adam.faculty_satisfaction_survey.survey.domain;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
interface SurveyAccessTokenRepository extends JpaRepository<SurveyAccessTokenEntity, Long> {
    Optional<SurveyAccessTokenEntity> findByToken(String token);
    boolean existsByHashAndSurveyId(String hash, Long surveyId);
}