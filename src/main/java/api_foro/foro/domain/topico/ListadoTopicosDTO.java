package api_foro.foro.domain.topico;

import api_foro.foro.domain.cursos.CursoDTO;
import api_foro.foro.domain.usuarios.UsuarioDTO;
import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import org.springframework.cglib.core.Local;

import java.time.LocalDate;

public record ListadoTopicosDTO(

        String title,

        String message,

        @JsonFormat(pattern = "yyyy-MM-dd")
        LocalDate fechaCreacion,

        Estatus estatus,

        UsuarioDTO usuarioId,

        CursoDTO cursoId) {

    public static ListadoTopicosDTO from(Topico topico) {
        return new ListadoTopicosDTO(
                topico.getTitulo(),
                topico.getMensaje(),
                topico.getFechaCreacion(),
                topico.getEstatus(),
                new UsuarioDTO( topico.getUsuario().getNombre(), topico.getUsuario().getEmail()),
                new CursoDTO(topico.getCurso().getId(), topico.getCurso().getName(), topico.getCurso().getCategory())
        );
    }
}
