package kg.adam.faculty_satisfaction_survey.professor.domain.model;


import jakarta.validation.Valid;
import jakarta.validation.constraints.NotEmpty;
import java.util.List;

public record BatchCreateProfessorRequest(
    @NotEmpty(message = "At least one professor required")
    @Valid List<CreateProfessorRequest> professors
) {}
