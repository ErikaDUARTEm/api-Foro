package api_foro.foro.domain.topico;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TopicoRepository extends JpaRepository<Topico, Long> {
    @Query("SELECT t FROM Topico t ORDER BY t.fechaCreacion ASC")
    List<Topico> listarTopicos();

    @Modifying
    @Query("DELETE FROM Topico t WHERE t.id=:id")
    DatosActualizarTopico deleteTopico(Long id);
}
