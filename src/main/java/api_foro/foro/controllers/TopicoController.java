package api_foro.foro.controllers;


import api_foro.foro.domain.topico.*;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;

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
    public ResponseEntity<ListadoTopicosDTO> topicoPorId(@Valid @PathVariable Long id){
        return service.topicoPorId(id);
     }

    @PutMapping("/{id}")
    public ResponseEntity<DatosActualizarTopico> actualizarTopico(
            @Valid @RequestBody DatosActualizarTopico datosActualizarTopico,
            @PathVariable Long id,
            UriComponentsBuilder uriComponentsBuilder) {
        return service.actualizarTopico(datosActualizarTopico, id, uriComponentsBuilder);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteTopico(@PathVariable Long id) {
        return service.deleteTopico(id);
    }
}
