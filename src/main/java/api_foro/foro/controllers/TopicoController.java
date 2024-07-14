package api_foro.foro.controllers;


import api_foro.foro.domain.topico.DatosRespuestaTopico;
import api_foro.foro.domain.topico.Estatus;
import api_foro.foro.domain.topico.Topico;
import api_foro.foro.domain.topico.TopicoRepository;
import api_foro.foro.domain.topico.cursos.Category;
import api_foro.foro.domain.topico.cursos.Curso;
import api_foro.foro.domain.topico.cursos.CursoRepository;
import api_foro.foro.domain.topico.usuarios.Usuario;
import api_foro.foro.domain.topico.usuarios.UsuarioRepository;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.Optional;

@RestController
@RequestMapping("/topicos")
public class TopicoController {

    @Autowired
    private TopicoRepository topicoRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private CursoRepository cursoRepository;

    @PostMapping
    @Transactional
    public ResponseEntity<Topico> enviarTopico(@Valid @RequestBody DatosRespuestaTopico datosRespuestaTopico) {
        // Verificar que los IDs no sean nulos
        if (datosRespuestaTopico.usuarioId() == null || datosRespuestaTopico.cursoId() == null) {
            return ResponseEntity.badRequest().body(null); // Usuario ID o Curso ID es obligatorio
        }

        // Buscar el usuario y curso por ID
        Optional<Usuario> usuarioOpt = usuarioRepository.findById(datosRespuestaTopico.usuarioId());
        Long cursoId = datosRespuestaTopico.cursoId();
        Curso curso = cursoRepository.findById(cursoId).orElse(null);

        if (!usuarioOpt.isPresent() || curso == null) {
            return ResponseEntity.notFound().build(); // Manejar escenarios donde no se encuentra el usuario o el curso
        }

        // Crear una nueva entidad Topico y asignar valores
        Topico topico = new Topico();
        topico.setTitulo(datosRespuestaTopico.title());
        topico.setMensaje(datosRespuestaTopico.message());
        topico.setCurso(curso);
        topico.setUsuario(usuarioOpt.get()); // Asignar el usuario encontrado

        topico.setFechaCreacion(LocalDate.now()); // Asignar la fecha actual
        topico.setEstatus(Estatus.PENDIENTE); // Asignar un estatus predeterminado

        // Guardar la entidad Topico en la base de datos
        Topico savedTopico = topicoRepository.save(topico);

        return ResponseEntity.ok(savedTopico);
    }
}
