package api_foro.foro.domain.topico.cursos;

import jakarta.persistence.*;
import lombok.*;

import java.security.PrivateKey;

@Table(name="cursos")
@Entity
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(of= "id")
@Getter
@Setter
public class Curso {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String category;
}
