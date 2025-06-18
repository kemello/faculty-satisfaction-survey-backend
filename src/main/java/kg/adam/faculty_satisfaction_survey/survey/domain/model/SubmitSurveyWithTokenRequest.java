package kg.adam.faculty_satisfaction_survey.survey.domain.model;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import kg.adam.faculty_satisfaction_survey.common.enums.AcademicYear;
import kg.adam.faculty_satisfaction_survey.common.enums.Faculty;
import kg.adam.faculty_satisfaction_survey.common.enums.StudyMode;

import java.util.List;

public record SubmitSurveyWithTokenRequest(
    @NotBlank(message = "Token is required")
    String token,
    
    @NotNull(message = "Survey ID is required")
    Long surveyId,
    
    @NotNull(message = "Faculty is required")
    Faculty faculty,
    
    @NotNull(message = "Academic year is required")
    AcademicYear academicYear,
    
    @NotNull(message = "Study mode is required")
    StudyMode studyMode,
    
    @NotEmpty(message = "At least one response must be provided")
    List<@Valid ResponseAssignmentData> responses
) {}