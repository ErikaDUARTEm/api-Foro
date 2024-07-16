package api_foro.foro.controllers;

import api_foro.foro.domain.usuarios.DatosAutenticacionUsuario;
import api_foro.foro.domain.usuarios.Usuario;
import api_foro.foro.infra.security.DatosTokenJWT;
import api_foro.foro.infra.security.TokenService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/login")
public class AutenticacionController {

    @Autowired
    private AuthenticationManager manager;

    @Autowired
    private TokenService tokenService;

    @PostMapping
    public ResponseEntity  realizarLogin(@RequestBody @Valid DatosAutenticacionUsuario datos) {
        var authenticationToken = new UsernamePasswordAuthenticationToken(datos.email(), datos.clave());
        var authentication = manager.authenticate(authenticationToken);

        var tokenJWT = tokenService.generarToken((Usuario) authentication.getPrincipal());

        return ResponseEntity.ok(new DatosTokenJWT(tokenJWT));
    }

}
