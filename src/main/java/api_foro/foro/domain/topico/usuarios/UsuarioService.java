package api_foro.foro.domain.topico.usuarios;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

@Service
public class UsuarioService {
    @Autowired
    private UsuarioRepository usuarioRepository;

    public ResponseEntity<UsuarioDTO> registarUsuario(UsuarioDTO usuarioDTO,
                                                      UriComponentsBuilder uriComponentsBuilder) {
        var usuario = new Usuario(usuarioDTO);

        Usuario usuarioConId = usuarioRepository.save(usuario);

        UsuarioDTO datosRespuestaUsuario = new UsuarioDTO(usuarioConId.getNombre(), usuarioConId.getEmail(), usuarioConId.getClave());
        URI url = uriComponentsBuilder.path("/usuarios/{id}").buildAndExpand(usuarioConId.getId()).toUri();

        return ResponseEntity.created(url).body(datosRespuestaUsuario);
    }

}
