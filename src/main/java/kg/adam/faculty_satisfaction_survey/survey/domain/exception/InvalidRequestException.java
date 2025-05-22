package kg.adam.faculty_satisfaction_survey.survey.domain.exception;

public class InvalidRequestException extends RuntimeException {
    private InvalidRequestException(String message) {
        super(message);
    }

    public static InvalidRequestException withMessage(String message) {
        return new InvalidRequestException(message);
    }

    public static InvalidRequestException duplicateOrder() {
        return new InvalidRequestException("Duplicate question/option order detected");
    }
}
