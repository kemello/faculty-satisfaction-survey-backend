package kg.adam.faculty_satisfaction_survey.survey.domain.exception;

public class TokenAlreadyUsedException extends RuntimeException {
    public TokenAlreadyUsedException(String message) {
        super(message);
    }
}