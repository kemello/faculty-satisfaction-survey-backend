package kg.adam.faculty_satisfaction_survey.survey.domain.exception;

public class TokenExpiredException extends RuntimeException {
    public TokenExpiredException(String message) {
        super(message);
    }
}