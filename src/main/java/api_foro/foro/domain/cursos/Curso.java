package api_foro.foro.domain.cursos;

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
