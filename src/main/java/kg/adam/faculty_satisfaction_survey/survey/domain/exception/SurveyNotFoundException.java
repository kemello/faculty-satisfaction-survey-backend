package kg.adam.faculty_satisfaction_survey.survey.domain.exception;


public class SurveyNotFoundException extends RuntimeException {
    private SurveyNotFoundException(String message) {
        super(message);
    }

    public static SurveyNotFoundException forId(Long surveyId) {
        return new SurveyNotFoundException("Survey not found with id: " + surveyId);
    }

    public static SurveyNotFoundException forName(String surveyName) {
        return new SurveyNotFoundException("Survey not found with name: " + surveyName);
    }
}
