package api_foro.foro.domain.usuarios;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;


public record UsuarioTopicoDTO(

        @NotNull()
        String nombre
) {

}
