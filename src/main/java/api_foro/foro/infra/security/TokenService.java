package api_foro.foro.infra.security;

import api_foro.foro.domain.usuarios.Usuario;
import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;
import org.springframework.stereotype.Service;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import org.springframework.beans.factory.annotation.Value;

@Service
public class TokenService {

    //@Value("${api.security.secret}")
   // private String apiSecret;
    private String apiSecret = "123456";
    public String generarToken(Usuario usuario) {

        System.out.println("token en ge" + apiSecret);
        try {
            System.out.println(apiSecret);
            var algoritmo = Algorithm.HMAC256(apiSecret);
            System.out.println("Generando token con issuer: foro");
            return JWT.create()
                    .withIssuer("foro")
                    .withSubject(usuario.getEmail())
                    .withClaim("id", usuario.getId())
                    .withExpiresAt(fechaExpiracion())
                    .sign(algoritmo);
        } catch (JWTCreationException exception){
            throw new RuntimeException("error al generar el  token jwt", exception);
        }
    }
   // public String getSubject(String token) {
      /*  System.out.println("token en getSubtject " + token);
        System.out.println("Clave secreta al verificar token: " + apiSecret);
        try {
            System.out.println(apiSecret);
            var algoritmo = Algorithm.HMAC256(apiSecret);
            System.out.println("Verificando token con issuer: foro " + token);
            return JWT.require(algoritmo)
                    .withIssuer("foro")
                    .build()
                    .verify(token)
                    .getSubject();
        } catch (JWTVerificationException exception) {
            throw new RuntimeException("Token JWT inválido o expirado!");
        }
        }
        */



        public String getSubject(String token) {
            System.out.println("Token recibido: " + token);
            System.out.println("Clave secreta al verificar token: " + apiSecret);

            try {
                Algorithm algorithm = Algorithm.HMAC256(apiSecret);
                System.out.println("Verificando token con issuer: foro " + token);
                System.out.println(algorithm);
                return JWT.require(algorithm)
                        .withIssuer("foro")
                        .build()
                        .verify(token)
                        .getSubject();
            } catch (JWTVerificationException exception) {
                throw new RuntimeException("Token JWT inválido o expirado!");
            }
        }



    private Instant fechaExpiracion() {
        Instant expiration = LocalDateTime.now().plusHours(2).toInstant(ZoneOffset.of("-03:00"));
        System.out.println("Fecha de expiración del token: " + expiration);
        return expiration;
    }



}
