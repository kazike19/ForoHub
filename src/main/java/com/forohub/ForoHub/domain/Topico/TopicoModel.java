package com.forohub.ForoHub.domain.Topico;

import com.forohub.ForoHub.domain.Curso.CursoModel;
import com.forohub.ForoHub.domain.Respuesta.RespuestaModel;
import com.forohub.ForoHub.domain.Usuario.UsuarioModel;
import jakarta.persistence.*;
import jakarta.validation.Valid;
import lombok.*;

import java.time.LocalDateTime;
import java.util.List;

@Table(name = "topicos")
@Entity(name = "Topico")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class TopicoModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Setter
    @Column(name = "titulo", nullable = false, length = 100)
    private String titulo;

    @Setter
    @Column(name = "mensaje", nullable = false, length = 100)
    private String mensaje;

    @Column(name = "fecha_creacion", nullable = false)
    private LocalDateTime fechaCreacion;

    @Column(name = "status", nullable = false)
    private boolean status; // disponible o no disponible

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "usuario_id", nullable = false)
    private UsuarioModel autor;

    @Setter
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "curso_id", nullable = false)
    private CursoModel curso;

    @OneToMany(mappedBy = "topico", cascade = CascadeType.ALL, fetch = FetchType.LAZY, orphanRemoval = true)
    private List<RespuestaModel> respuestas;

    public TopicoModel(UsuarioModel autor,DatosPosteoTopico datos, CursoModel curso) {
        this.id = null;
        this.autor = autor;
        this.status = true;
        this.titulo = datos.titulo();
        this.mensaje = datos.mensaje();
        this.fechaCreacion = LocalDateTime.now();
        this.curso = curso;
    }

    public void eliminar() {
        this.status = false;
    }

    public void actualizarTopico(@Valid DatosActualizarTopico datos) {

        if(datos.titulo() != null){
            this.titulo = datos.titulo();
        }

        if(datos.mensaje() != null){
            this.mensaje = datos.mensaje();
        }

    }

}