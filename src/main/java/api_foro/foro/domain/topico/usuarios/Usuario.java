package api_foro.foro.domain.topico.usuarios;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Table(name="usuarios")
@Entity
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(of= "id")
@Getter
@Setter
public class Usuario {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private List<String> cursos;
}
