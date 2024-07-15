package api_foro.foro.domain.usuarios;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

@Service
public class UsuarioService {
    @Autowired
    private UsuarioRepository usuarioRepository;

    public ResponseEntity<UsuarioRegistroDTO> registarUsuario(UsuarioRegistroDTO usuarioRegistroDTO,
                                                      UriComponentsBuilder uriComponentsBuilder) {
        var usuario = new Usuario(usuarioRegistroDTO);

        Usuario usuarioConId = usuarioRepository.save(usuario);

        UsuarioRegistroDTO datosRespuestaUsuario = new UsuarioRegistroDTO(usuarioConId.getNombre(), usuarioConId.getEmail(), usuarioConId.getClave());
        URI url = uriComponentsBuilder.path("/usuarios/{id}").buildAndExpand(usuarioConId.getId()).toUri();

        return ResponseEntity.created(url).body(datosRespuestaUsuario);
    }

}
