package api_foro.foro.domain.topico.cursos;

import api_foro.foro.domain.topico.usuarios.Usuario;
import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.*;

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
    @Column(name = "name")
    private String name;
    @Enumerated(EnumType.STRING)
    private Category category;

}
