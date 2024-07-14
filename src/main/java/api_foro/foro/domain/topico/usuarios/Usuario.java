package api_foro.foro.domain.topico.usuarios;
import api_foro.foro.domain.topico.Topico;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Table(name = "usuarios")
@Entity(name = "Usuario")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Usuario  {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nombre;
    private String email;
    private String clave;

    @OneToMany(mappedBy = "usuario", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    List<Topico> topicos;

    public Usuario(UsuarioDTO usuario) {
        this.nombre = usuario.nombre();
        this.email = usuario.email();
        this.clave = usuario.clave();

    }


}