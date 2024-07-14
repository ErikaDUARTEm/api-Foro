package api_foro.foro.domain.topico.cursos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record CursoDTO (

        @NotNull
        Long id,

        @NotBlank
        String name,

        @NotBlank
        Category category
){

}
