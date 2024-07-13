package api_foro.foro.domain.topico;

import java.util.List;

public record DatosRespuestaTopico(
        String title,
        String message,
        String usuarioId,
        String cursoId
) {
}
