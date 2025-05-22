package kg.adam.faculty_satisfaction_survey.survey.domain.exception;

public class ResponseNotFoundException extends RuntimeException {
    private ResponseNotFoundException(String message) {
        super(message);
    }

    public static ResponseNotFoundException forCompositeId(String anonymousId, Long questionId, Long surveyId) {
        return new ResponseNotFoundException("Response not found for anonymousId: " + anonymousId 
            + ", questionId: " + questionId + ", surveyId: " + surveyId);
    }
}
