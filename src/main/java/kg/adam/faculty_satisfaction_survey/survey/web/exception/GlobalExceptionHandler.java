package kg.adam.faculty_satisfaction_survey.survey.web.exception;

import kg.adam.faculty_satisfaction_survey.survey.domain.exception.InvalidRequestException;
import kg.adam.faculty_satisfaction_survey.survey.domain.exception.QuestionNotFoundException;
import kg.adam.faculty_satisfaction_survey.survey.domain.exception.ResponseNotFoundException;
import kg.adam.faculty_satisfaction_survey.survey.domain.exception.SurveyNotFoundException;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.net.URI;
import java.time.Instant;

class GlobalExceptionHandler extends ResponseEntityExceptionHandler {
    private static final URI NOT_FOUND_TYPE = URI.create("http://api.faculty-survey.com/not-found");
    private static final URI BAD_REQUEST_TYPE = URI.create("http://api.faculty-survey.com/bad-request");
    private static final URI VALIDATION_ERROR_TYPE = URI.create("http://api.faculty-survey.com/validation-error");
    private static final URI INTERNAL_ERROR_TYPE = URI.create("http://api.faculty-survey.com/server-error");
    private static final String SERVICE_NAME = "faculty-satisfaction-survey";

    // Custom not found exceptions
    @ExceptionHandler({SurveyNotFoundException.class, QuestionNotFoundException.class, ResponseNotFoundException.class})
    ProblemDetail handleNotFoundException(RuntimeException ex) {
        ProblemDetail problemDetail = ProblemDetail.forStatusAndDetail(
                HttpStatus.NOT_FOUND, ex.getMessage());
        problemDetail.setTitle("Resource Not Found");
        problemDetail.setType(NOT_FOUND_TYPE);
        problemDetail.setProperty("service", SERVICE_NAME);
        problemDetail.setProperty("timestamp", Instant.now());
        return problemDetail;
    }

    // Handle business logic violations
    @ExceptionHandler(InvalidRequestException.class)
    ProblemDetail handleInvalidRequest(InvalidRequestException ex) {
        ProblemDetail problemDetail = ProblemDetail.forStatusAndDetail(
                HttpStatus.BAD_REQUEST, ex.getMessage());
        problemDetail.setTitle("Invalid Request");
        problemDetail.setType(BAD_REQUEST_TYPE);
        problemDetail.setProperty("service", SERVICE_NAME);
        problemDetail.setProperty("timestamp", Instant.now());
        return problemDetail;
    }

    // Catch-all for other exceptions
    @ExceptionHandler(Exception.class)
    ProblemDetail handleUnhandledException(Exception ex) {
        ProblemDetail problemDetail = ProblemDetail.forStatusAndDetail(
                HttpStatus.INTERNAL_SERVER_ERROR, "An unexpected error occurred");
        problemDetail.setTitle("Internal Server Error");
        problemDetail.setType(INTERNAL_ERROR_TYPE);
        problemDetail.setProperty("service", SERVICE_NAME);
        problemDetail.setProperty("timestamp", Instant.now());
        problemDetail.setProperty("error_message", ex.getMessage());
        return problemDetail;
    }
}
