package kg.adam.faculty_satisfaction_survey.professor.domain;

import kg.adam.faculty_satisfaction_survey.professor.domain.model.CreateProfessorRequest;
import kg.adam.faculty_satisfaction_survey.professor.domain.model.ProfessorData;

class ProfessorMapper {
    static ProfessorEntity toEntity(CreateProfessorRequest request) {
        ProfessorEntity newProfessor = new ProfessorEntity();
        newProfessor.setName(request.name());
        newProfessor.setAvatarUrl(request.avatarUrl());
        return newProfessor;
    }

    static ProfessorData toData(ProfessorEntity savedProfessor) {
        return new ProfessorData(
                savedProfessor.getId(),
                savedProfessor.getName(),
                savedProfessor.getAvatarUrl(),
                savedProfessor.getCreatedAt()
        );
    }
}
