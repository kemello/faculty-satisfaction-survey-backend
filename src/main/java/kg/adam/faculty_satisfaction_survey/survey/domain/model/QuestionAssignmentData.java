package kg.adam.faculty_satisfaction_survey.survey.domain.model;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import kg.adam.faculty_satisfaction_survey.survey.domain.enums.QuestionCategory;
import kg.adam.faculty_satisfaction_survey.survey.domain.enums.QuestionType;

import java.util.List;

public record QuestionAssignmentData(
        @NotNull Long id,
        @NotBlank String questionText,
        @NotNull QuestionType questionType,
        @NotNull QuestionCategory category,
        @Positive Integer order,
        @Valid List<@Valid QuestionOptionData> options
) {
}
