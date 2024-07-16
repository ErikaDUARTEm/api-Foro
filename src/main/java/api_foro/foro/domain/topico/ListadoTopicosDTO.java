package api_foro.foro.domain.topico;

import api_foro.foro.domain.cursos.CursoTopicoDTO;
import api_foro.foro.domain.usuarios.UsuarioTopicoDTO;
import com.fasterxml.jackson.annotation.JsonFormat;

import java.time.LocalDate;

public record ListadoTopicosDTO(

        String title,

        String message,

        @JsonFormat(pattern = "yyyy-MM-dd")
        LocalDate fechaCreacion,

        Estatus estatus,

        UsuarioTopicoDTO usuarioId,

        CursoTopicoDTO cursoId) {


    public static ListadoTopicosDTO from(Topico topico) {
        return new ListadoTopicosDTO(
                topico.getTitulo(),
                topico.getMensaje(),
                topico.getFechaCreacion(),
                topico.getEstatus(),
                new UsuarioTopicoDTO(topico.getUsuario().getNombre()),
                new CursoTopicoDTO(topico.getCurso().getName())
        );
    }
}
