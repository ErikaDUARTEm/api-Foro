package api_foro.foro.controllers;


import api_foro.foro.domain.topico.*;
import api_foro.foro.domain.cursos.Curso;
import api_foro.foro.domain.cursos.CursoRepository;
import api_foro.foro.domain.usuarios.Usuario;
import api_foro.foro.domain.usuarios.UsuarioRepository;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/topicos")
public class TopicoController {

    @Autowired
    TopicoService service;

    @PostMapping
    @Transactional
    public ResponseEntity<Topico> enviarTopico(@Valid @RequestBody DatosRegistroTopico datosRegistroTopico) {
       return service.enviarTopico(datosRegistroTopico);
    }
    @GetMapping
    public List<ListadoTopicosDTO> listarTopicos() {
        return service.listarTopicos();
    }
     @GetMapping("/{id}")
    public ResponseEntity<ListadoTopicosDTO> topicoPorId(@PathVariable Long id){
        return service.topicoPorId(id);
     }
}
