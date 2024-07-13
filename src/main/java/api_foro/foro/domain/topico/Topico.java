package api_foro.foro.domain.topico;

import api_foro.foro.domain.topico.cursos.Curso;
import api_foro.foro.domain.topico.usuarios.Usuario;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import jakarta.persistence.Table;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Table(name="topicos")
@Entity
@NoArgsConstructor
@EqualsAndHashCode(of= "id")
@Getter
@Setter
public class Topico {

   @Id
   @GeneratedValue(strategy = GenerationType.IDENTITY)
   private Long id;
   private String title;
   private String message;
   private String date;
   @Enumerated(EnumType.STRING)
   private Estatus estatus;

   @JsonProperty("curso_id")
   @ManyToOne
   @JoinColumn(name = "curso_id")
   private Curso curso;

   @ManyToOne
   @JoinColumn(name = "usuario_id")
   @JsonProperty("usuario_id")
   private Usuario usuario;

   @ElementCollection
   private List<String> respuestas = new ArrayList<>();

   public Topico(Long id, String title, String message, String date, Estatus estatus, Curso curso, Usuario usuario, List<String> respuestas) {
      this.id = id;
      this.title = title;
      this.message = message;
      this.date = date;
      this.estatus = estatus;
      this.curso = curso;
      this.usuario = usuario;
      this.respuestas = respuestas;
   }


}
