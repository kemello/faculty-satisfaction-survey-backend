package kg.adam.faculty_satisfaction_survey.professor.domain;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
interface ProfessorRepository extends JpaRepository<ProfessorEntity, Long> {
    Optional<ProfessorEntity> findByName(String name);

    @Query(nativeQuery = true,
    value = """
            select p.* from public.course_assignment ca
            left join public.course c on c.id = ca.course_id
            left join public.professor p on p.id = c.professor_id
            where faculty = 'IST'
            """)
    List<ProfessorEntity> findAllByFaculty(String faculty);
}