package api_foro.foro.domain.topico;

import api_foro.foro.domain.topico.cursos.Curso;
import api_foro.foro.domain.topico.usuarios.Usuario;
import api_foro.foro.domain.topico.usuarios.UsuarioDTO;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;


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

      private String titulo;

      private String mensaje;

      @Column(name = "fecha_creacion")
      private LocalDate fechaCreacion;

      @Enumerated(EnumType.STRING)
      private Estatus estatus;

      @ManyToOne
      @JoinColumn(name = "usuario_id")
      private Usuario usuario;

      @ManyToOne
      @JoinColumn(name = "curso_id")
      private Curso curso;

      public Topico(DatosRespuestaTopico datosRespuestaTopico) {
            this.titulo = datosRespuestaTopico.title();
            this.mensaje = datosRespuestaTopico.message();

            LocalDateTime ahora = LocalDateTime.now();
            DateTimeFormatter formateador = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
            String horaFormateada = ahora.format(formateador);
            this.fechaCreacion = LocalDate.parse(horaFormateada, formateador);

            this.estatus = Estatus.PENDIENTE;

      }
}

