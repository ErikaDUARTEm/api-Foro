package api_foro.foro.domain.topico.cursos;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface CursoRepository extends JpaRepository<Curso, Long> {

}
