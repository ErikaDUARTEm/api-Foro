package api_foro.foro.controllers;

import api_foro.foro.domain.topico.cursos.Category;
import api_foro.foro.domain.topico.cursos.Curso;
import api_foro.foro.domain.topico.cursos.CursoDTO;
import api_foro.foro.domain.topico.cursos.CursoRepository;
import api_foro.foro.domain.topico.usuarios.Usuario;
import api_foro.foro.domain.topico.usuarios.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/curso")
public class CursoController {

    @Autowired
    private CursoRepository cursoRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @PostMapping
    public ResponseEntity<Curso> createCurso(@RequestBody CursoDTO cursoRequest) {

        if (cursoRequest.name() == null || cursoRequest.category() == null) {
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }

        Curso curso = new Curso();
        curso.setName(cursoRequest.name());
        curso.setCategory(cursoRequest.category());

        // Guardar el curso en la base de datos
        Curso savedCurso = cursoRepository.save(curso);

        return new ResponseEntity<>(savedCurso, HttpStatus.CREATED);
    }

}
