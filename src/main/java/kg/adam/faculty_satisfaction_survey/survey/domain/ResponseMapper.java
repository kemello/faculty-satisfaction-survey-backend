package kg.adam.faculty_satisfaction_survey.survey.domain;

import kg.adam.faculty_satisfaction_survey.survey.domain.model.ResponseAssignmentData;

import java.util.List;
import java.util.TreeSet;
import java.util.UUID;
import java.util.stream.Collectors;

class ResponseMapper {
//    static ResponseData toData(ResponseEntity response) {
//        return new ResponseData(response.getId(), response.getContent());
//    }

    static ResponseEntity toEntity(ResponseAssignmentData data, SurveyEntity survey, QuestionEntity question) {
        String anonymousId = UUID.randomUUID().toString();
        ResponseId id = new ResponseId(
                anonymousId,
                question.getId(),
                survey.getId()
        );

        return new ResponseEntity(data.content(), id, survey, question);
    }

//    static TreeSet<ResponseData> toData(TreeSet<ResponseEntity> entities) {
//        TreeSet<ResponseData> responses = new TreeSet<>();
//        for (ResponseEntity entity : entities) {
//            responses.add(toData(entity));
//        }
//        return responses;
//    }

    static List<ResponseEntity> toEntityList(SurveyEntity survey, List<ResponseAssignmentData> assignmentData) {
        return assignmentData.stream()
                .map(data -> {
                    QuestionEntity question = survey.getQuestions().stream()
                            .filter(q -> q.getId().equals(data.questionId()))
                            .findFirst()
                            .orElseThrow();
                    return toEntity(data, survey, question);
                })
                .collect(Collectors.toList());
    }

    static List<ResponseAssignmentData> toDataList(List<ResponseEntity> responseEntities) {
        return responseEntities.stream()
                .map(entity -> new ResponseAssignmentData(
                        entity.getQuestion().getId(),
                        entity.getContent()))
                .collect(Collectors.toList());
    }
}
