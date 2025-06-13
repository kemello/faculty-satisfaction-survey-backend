package kg.adam.faculty_satisfaction_survey.survey.domain.exception;


public class QuestionNotFoundException extends RuntimeException {
    public QuestionNotFoundException(String message) {
        super(message);
    }

    public static QuestionNotFoundException forId(Long questionId) {
        return new QuestionNotFoundException("Question not found with id: " + questionId);
    }

    public static QuestionNotFoundException inSurvey(Long questionId, Long surveyId) {
        return new QuestionNotFoundException("Question with id " + questionId
                + " not found in survey " + surveyId);
    }
}