package kg.adam.faculty_satisfaction_survey.professor.domain;

import kg.adam.faculty_satisfaction_survey.common.enums.Faculty;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class ProfessorRepositoryTest {

    @Autowired
    private ProfessorRepository repository;

    @Test
    void findAllByFaculty() {
        String faculty = Faculty.IST.name();
        List<ProfessorEntity> result = assertDoesNotThrow(() -> repository.findAllByFaculty(faculty));
        result.forEach(System.out::println);
    }
}