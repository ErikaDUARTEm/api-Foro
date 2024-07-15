package api_foro.foro.domain.topico;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;


public record DatosRegistroTopico(

        @NotBlank
        String title,
        @NotBlank
        String message,
        @NotNull
        Long usuarioId,
        @NotNull
        Long cursoId) {

}