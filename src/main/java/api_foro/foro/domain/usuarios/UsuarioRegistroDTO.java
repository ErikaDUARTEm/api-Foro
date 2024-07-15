package api_foro.foro.domain.usuarios;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;


public record UsuarioRegistroDTO(

        @NotNull(message = "Nombre es obligatorio")
        String nombre,
        @Email
        @NotBlank(message = "Email es obligatorio")
        String email,
        @NotNull(message = "Clave es obligatorio")
        String clave


) {

}
