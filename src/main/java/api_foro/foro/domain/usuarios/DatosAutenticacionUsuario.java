package api_foro.foro.domain.usuarios;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;

public record DatosAutenticacionUsuario(
        @NotNull
        @Email
        String email,
        @NotNull
        String clave
) {
}
