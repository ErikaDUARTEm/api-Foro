package api_foro.foro.domain.cursos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record CursoTopicoDTO(

        @NotBlank
        String name

){
}
