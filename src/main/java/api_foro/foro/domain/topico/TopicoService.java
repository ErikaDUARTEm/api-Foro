package api_foro.foro.domain.topico;

import api_foro.foro.domain.cursos.Curso;
import api_foro.foro.domain.cursos.CursoRepository;
import api_foro.foro.domain.usuarios.Usuario;
import api_foro.foro.domain.usuarios.UsuarioRepository;
import api_foro.foro.infra.errores.ValidacionDeIntegridad;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class TopicoService {
    @Autowired
    private TopicoRepository topicoRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private CursoRepository cursoRepository;

    public ResponseEntity<Topico> enviarTopico( DatosRegistroTopico datosRegistroTopico) {
        // Verificar que los IDs no sean nulos
        if (datosRegistroTopico.usuarioId() == null || datosRegistroTopico.cursoId() == null) {
            return ResponseEntity.badRequest().body(null); // Usuario ID o Curso ID es obligatorio
        }

        // Buscar el usuario y curso por ID
        Optional<Usuario> usuarioOpt = usuarioRepository.findById(datosRegistroTopico.usuarioId());
        Long cursoId = datosRegistroTopico.cursoId();
        Curso curso = cursoRepository.findById(cursoId).orElse(null);

        if (!usuarioOpt.isPresent() || curso == null) {
            return ResponseEntity.notFound().build(); // Manejar escenarios donde no se encuentra el usuario o el curso
        }

        // Crear una nueva entidad Topico y asignar valores
        Topico topico = new Topico();
        topico.setTitulo(datosRegistroTopico.title());
        topico.setMensaje(datosRegistroTopico.message());
        topico.setCurso(curso);
        topico.setUsuario(usuarioOpt.get()); // Asignar el usuario encontrado

        topico.setFechaCreacion(LocalDate.now()); // Asignar la fecha actual
        topico.setEstatus(Estatus.PENDIENTE); // Asignar un estatus predeterminado

        // Guardar la entidad Topico en la base de datos
        Topico savedTopico = topicoRepository.save(topico);

        return ResponseEntity.ok(savedTopico);
    }
    @Transactional
    public List<ListadoTopicosDTO> listarTopicos() {
        List<Topico> topicos = topicoRepository.listarTopicos();
        return topicos.stream()
                .map(ListadoTopicosDTO::from)
                .collect(Collectors.toList());
    }

    public ResponseEntity<ListadoTopicosDTO> topicoPorId(Long id) {
        if (topicoRepository.findById(id).isEmpty()){
            throw new ValidacionDeIntegridad("El tópico no fue encontrado. Verifique el id.");
        }

        Topico topico = topicoRepository.getReferenceById(id);

        var datosTopico = ListadoTopicosDTO.from(topico);

        return ResponseEntity.ok(datosTopico);
    }

    public ResponseEntity<DatosActualizarTopico> actualizarTopico( DatosActualizarTopico datosActualizarTopico, Long id, UriComponentsBuilder uriComponentsBuilder) {
        Optional<Topico> optionalTopico = topicoRepository.findById(id);
        if (!optionalTopico.isPresent()) {
            throw new ValidacionDeIntegridad("El tópico no fue encontrado. Verifique el id.");
        }
        Topico topico = optionalTopico.get();

        Optional<Usuario> optionalUsuario = usuarioRepository.findById(datosActualizarTopico.usuarioId());
        if (!optionalUsuario.isPresent()) {
            throw new ValidacionDeIntegridad("El usuario no fue encontrado.");
        }
        Usuario usuario = optionalUsuario.get();

        Optional<Curso> optionalCurso = cursoRepository.findById(datosActualizarTopico.cursoId());
        if (!optionalCurso.isPresent()) {
            throw new ValidacionDeIntegridad("El curso no fue encontrado.");
        }
        Curso curso = optionalCurso.get();

        topico.setTitulo(datosActualizarTopico.title());
        topico.setMensaje(datosActualizarTopico.message());
        topico.setUsuario(usuario);
        topico.setCurso(curso);

        topicoRepository.save(topico);

        DatosActualizarTopico datosRespuestaTopico = new DatosActualizarTopico(
                topico.getTitulo(),
                topico.getMensaje(),
                topico.getUsuario().getId(),
                topico.getCurso().getId()
        );

        URI url = uriComponentsBuilder.path("/topicos/{id}").buildAndExpand(topico.getId()).toUri();

        return ResponseEntity.created(url).body(datosRespuestaTopico);
    }

    public ResponseEntity<DatosActualizarTopico> eliminarTopico(Long id) {
        Topico topico = topicoRepository.findById(id)
                .orElseThrow(() -> new ValidacionDeIntegridad("El tópico no fue encontrado. Verifique el id."));

        topicoRepository.delete(topico);

        DatosActualizarTopico datosRespuestaTopico = new DatosActualizarTopico(
                topico.getTitulo(),
                topico.getMensaje(),
                topico.getUsuario().getId(),
                topico.getCurso().getId()
        );

        return ResponseEntity.ok(datosRespuestaTopico);
    }

}
