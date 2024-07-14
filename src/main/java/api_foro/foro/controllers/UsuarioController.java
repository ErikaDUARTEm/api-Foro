package api_foro.foro.controllers;

import api_foro.foro.domain.topico.cursos.Curso;
import api_foro.foro.domain.topico.cursos.CursoRepository;
import api_foro.foro.domain.topico.usuarios.Usuario;
import api_foro.foro.domain.topico.usuarios.UsuarioDTO;
import api_foro.foro.domain.topico.usuarios.UsuarioRepository;
import api_foro.foro.domain.topico.usuarios.UsuarioService;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
@RequestMapping("/usuario")
public class UsuarioController {

    @Autowired
    private UsuarioService service;

    @PostMapping
    @Transactional
    public ResponseEntity<UsuarioDTO> registrarUsuario(@Valid @RequestBody UsuarioDTO usuarioDTO, UriComponentsBuilder uriComponentsBuilder){
        return service.registarUsuario(usuarioDTO, uriComponentsBuilder);
    }

}
