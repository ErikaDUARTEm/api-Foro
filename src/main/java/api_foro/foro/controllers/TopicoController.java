package api_foro.foro.controllers;

import api_foro.foro.domain.topico.DatosRespuestaTopico;
import api_foro.foro.domain.topico.TopicoRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowire;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/topicos")
public class TopicoController {

    @Autowired
    TopicoRepository topicoRepository;

    @PostMapping
    @Transactional
    public void enviarTopico(@RequestBody DatosRespuestaTopico datosRespuestaTopico){
        System.out.println(datosRespuestaTopico);
    }
}
